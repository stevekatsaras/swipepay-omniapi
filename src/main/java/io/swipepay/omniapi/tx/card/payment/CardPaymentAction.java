package io.swipepay.omniapi.tx.card.payment;

import java.time.LocalDateTime;
import java.util.LinkedList;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.swipepay.omniapi.common.aspect.durationtime.DurationTimeTxCc;
import io.swipepay.omniapi.common.config.properties.OmniApiPaymentProperties;
import io.swipepay.omniapi.common.entity.merchantprofilepaymentcfg.MerchantProfilePaymentCfg;
import io.swipepay.omniapi.common.entity.merchantprofilepaymentcfg.MerchantProfilePaymentCfgRepository;
import io.swipepay.omniapi.common.entity.merchantprofilepaymentgateway.MerchantProfilePaymentGateway;
import io.swipepay.omniapi.common.entity.merchantprofilepaymentgateway.MerchantProfilePaymentGatewayRepository;
import io.swipepay.omniapi.common.entity.paymentgateway.enums.PaymentGatewayCode;
import io.swipepay.omniapi.common.entity.paymentresponsecc.PaymentResponseCc;
import io.swipepay.omniapi.common.entity.paymentresponsecc.PaymentResponseCcRepository;
import io.swipepay.omniapi.common.entity.paymenttxcc.PaymentTxCc;
import io.swipepay.omniapi.common.entity.paymenttxcc.PaymentTxCcRepository;
import io.swipepay.omniapi.common.entity.paymenttxcc.enums.PaymentTxCcEvent;
import io.swipepay.omniapi.common.entity.paymenttxcc.enums.PaymentTxCcEnvironment;
import io.swipepay.omniapi.common.entity.paymenttxccmetadata.PaymentTxCcMetadataRepository;
import io.swipepay.omniapi.tx.GatewayException;
import io.swipepay.omniapi.tx.card.TxCard;
import io.swipepay.omniapi.tx.card.payment.gateway.Omnihubv1117Payment;

@Service
public class CardPaymentAction extends TxCard {
	
	@Autowired
	private CardPaymentSimulator cardPaymentSimulator;
	
	@Autowired
	private MerchantProfilePaymentCfgRepository merchantProfilePaymentCfgRepository;
	
	@Autowired
	private MerchantProfilePaymentGatewayRepository merchantProfilePaymentGatewayRepository;
	
	@Autowired
	private PaymentResponseCcRepository paymentResponseCcRepository;
	
	@Autowired
	private PaymentTxCcRepository paymentTxCcRepository;
	
	@Autowired
	private PaymentTxCcMetadataRepository paymentTxCcMetadataRepository;
	
	@Autowired
	private OmniApiPaymentProperties omniApiPaymentProperties;
	
	@Autowired
	private Omnihubv1117Payment omnihubv1117Payment;
	
	@Transactional(readOnly = false)
	public void saveNewTx(PaymentTxCc paymentTxCc) {
		paymentTxCc.setModified(LocalDateTime.now());
		paymentTxCcRepository.saveAndFlush(paymentTxCc);
		
		paymentTxCcMetadataRepository.save(paymentTxCc.getPaymentTxCcMetadata());
		paymentTxCcMetadataRepository.flush();
	}
	
	@Transactional(readOnly = false)
	public void savePendingTx(PaymentTxCc paymentTxCc) {
		paymentTxCc.setEvent(PaymentTxCcEvent.Pending.name());
		paymentTxCc.setModified(LocalDateTime.now());
		paymentTxCcRepository.saveAndFlush(paymentTxCc);
	}
	
	@Transactional(readOnly = false)
	public void saveFailedTx(PaymentTxCc paymentTxCc) {
		paymentTxCc.setEvent(PaymentTxCcEvent.Failed.name());
		paymentTxCc.setModified(LocalDateTime.now());
		paymentTxCcRepository.saveAndFlush(paymentTxCc);
	}
	
	@Transactional(readOnly = false)
	public void saveProcessedTx(PaymentTxCc paymentTxCc) {
		MerchantProfilePaymentCfg merchantProfilePaymentCfg = merchantProfilePaymentCfgRepository
				.findByMerchantProfile(
						paymentTxCc.getMerchantProfile());
		
		paymentTxCc.setEvent(PaymentTxCcEvent.Processed.name());
		paymentTxCc.setReceiptNo(RandomStringUtils.randomNumeric(merchantProfilePaymentCfg.getReceiptLength()));
		if (paymentTxCc.getTxSuccess()) {
			paymentTxCc.setCaptured(true);
			paymentTxCc.setCapturedAmountTotal(paymentTxCc.getAmount());
			paymentTxCc.setRefundedAmountRemaining(paymentTxCc.getAmount());
		}
		paymentTxCc.setModified(LocalDateTime.now());
		paymentTxCcRepository.saveAndFlush(paymentTxCc);
	}
	
	@DurationTimeTxCc
	public Boolean routeTx(PaymentTxCc paymentTxCc) {
		Boolean processed = false;
		if (BooleanUtils.isTrue(omniApiPaymentProperties.getGatewayForceSimulation())) {
			processed = cardPaymentSimulator.simulate(paymentTxCc);
		}
		else if (StringUtils.equals(paymentTxCc.getEnvironment(), PaymentTxCcEnvironment.Test.name())) {
			processed = cardPaymentSimulator.simulate(paymentTxCc);
		}
		else if (StringUtils.equals(paymentTxCc.getEnvironment(), PaymentTxCcEnvironment.Live.name())) {
			processed = routeTxViaGateway(paymentTxCc);
		}
		return processed;
	}
	
	@Transactional(readOnly = true)
	public Boolean routeTxViaGateway(PaymentTxCc paymentTxCc) {
		Boolean processed = false;
		LinkedList<String> txExceptions = new LinkedList<String>();
		
		LinkedList<MerchantProfilePaymentGateway> merchantProfilePaymentGateways = merchantProfilePaymentGatewayRepository
				.findByMerchantProfileAndEnabledIsTrueAndPaymentGatewayDoesCcOrderByPriorityAsc(
						paymentTxCc.getMerchantProfile(), 
						true); // does cc
		
		for (MerchantProfilePaymentGateway merchantProfilePaymentGateway : merchantProfilePaymentGateways) {
			PaymentGatewayCode paymentGatewayCode = Enum.valueOf(PaymentGatewayCode.class, merchantProfilePaymentGateway.getPaymentGateway().getCode());
			try {
				switch (paymentGatewayCode) {
				case omnihubv1117:
					omnihubv1117Payment.send(paymentTxCc, merchantProfilePaymentGateway);
					processed = true;
					break;
				default:
					break;
				}
			}
			catch (GatewayException exception) {
				txExceptions.add(exception.getMessage() + " - Cause: " + exception.getCause().getMessage());
			}
		}
		finaliseTxViaGateway(paymentTxCc, txExceptions);
		return processed;
	}
	
	@Transactional(readOnly = true)
	public void finaliseTxViaGateway(PaymentTxCc paymentTxCc, LinkedList<String> txExceptions) {
		if (!txExceptions.isEmpty()) {
			PaymentResponseCc paymentResponseCc = paymentResponseCcRepository.findByCode("100");
			
			paymentTxCc.setTxSuccess(false);
			paymentTxCc.setTxResult("Declined");
			paymentTxCc.setTxResponseCode(paymentResponseCc.getCode());
			paymentTxCc.setTxResponseText(paymentResponseCc.getText());
			paymentTxCc.setPaymentResponseCc(paymentResponseCc);
			paymentTxCc.setTxExceptions(txExceptions.toString());
		}
	}
}