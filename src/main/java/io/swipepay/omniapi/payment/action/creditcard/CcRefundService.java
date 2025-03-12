package io.swipepay.omniapi.payment.action.creditcard;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.UUID;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.swipepay.omniapi.common.config.properties.OmniApiPaymentProperties;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.merchantprofilepaymentcfg.MerchantProfilePaymentCfg;
import io.swipepay.omniapi.common.entity.merchantprofilepaymentgateway.MerchantProfilePaymentGateway;
import io.swipepay.omniapi.common.entity.paymentgateway.PaymentGateway;
import io.swipepay.omniapi.common.entity.paymentgateway.enums.PaymentGatewayCode;
import io.swipepay.omniapi.common.entity.paymentresponsecc.PaymentResponseCcRepository;
import io.swipepay.omniapi.common.entity.paymenttxcc.PaymentTxCc;
import io.swipepay.omniapi.common.entity.paymenttxcc.PaymentTxCcRepository;
import io.swipepay.omniapi.common.entity.paymenttxcc.enums.PaymentTxCcEvent;
import io.swipepay.omniapi.common.entity.paymenttxcc.enums.PaymentTxCcEnvironment;
import io.swipepay.omniapi.common.entity.paymenttxccmetadata.PaymentTxCcMetadata;
import io.swipepay.omniapi.common.enums.RelatedPayment;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.payload.dto.StatusDto;
import io.swipepay.omniapi.common.support.CalendarSupport;
import io.swipepay.omniapi.payment.PaymentData;
import io.swipepay.omniapi.payment.action.creditcard.gateway.Simulator;
import io.swipepay.omniapi.payment.action.creditcard.gateway.omnihubv1.OmnihubV1RefundGateway;
import io.swipepay.omniapi.payment.payload.PaymentRequest;
import io.swipepay.omniapi.payment.payload.PaymentResponse;
import io.swipepay.omniapi.payment.payload.dto.PaymentDto;
import io.swipepay.omniapi.tx.GatewayException;

@Service
public class CcRefundService extends CcTxService {
	
	@Autowired
	private CalendarSupport calendarSupport;
	
	@Autowired
	private OmnihubV1RefundGateway omnihubV1RefundGateway;
	
	@Autowired
	private OmniApiPaymentProperties omniApiPaymentProperties;
	
	@Autowired
	private PaymentTxCcRepository paymentCcRepository;
	
	@Autowired
	private PaymentResponseCcRepository paymentResponseRepository;
	
	@Autowired
	private Simulator simulator;

	@Override
	@Transactional(readOnly = false)
	public void saveNewTx(PaymentRequest paymentRequest, PaymentData paymentData) {
//		PaymentTxCc paymentCc = buildPaymentCc(paymentRequest, paymentData, StringUtils.EMPTY);
//		paymentCcRepository.saveAndFlush(paymentCc);
//		
//		paymentData.setPaymentCc(paymentCc);
	}

	@Override
	@Transactional(readOnly = false)
	public void savePendingTx(PaymentRequest paymentRequest, PaymentData paymentData) {
//		PaymentTxCc paymentCc = paymentData.getPaymentCc();
//		paymentCc.setEvent(PaymentTxCcEvent.Pending.name());
//		paymentCc.setModified(LocalDateTime.now());
//		paymentCcRepository.saveAndFlush(paymentCc);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void saveProcessedTx(PaymentRequest paymentRequest, PaymentData paymentData) {
//		MerchantProfilePaymentCfg merchantProfilePaymentCfg = paymentData.getMerchantProfilePaymentCfg();
//		
//		// get the purchase transaction
//
//		PaymentTxCc purchasePaymentCc = paymentData
//				.getRelatedPaymentData()
//				.get(RelatedPayment.purchase.name())
//				.getPaymentCc();
//
//		PaymentTxCc paymentCc = paymentData.getPaymentCc();
//		paymentCc.setEvent(PaymentTxCcEvent.Processed.name());
//		paymentCc.setReceiptNo(RandomStringUtils.randomNumeric(merchantProfilePaymentCfg.getReceiptLength()));
//		
//		if (paymentCc.getTxSuccess()) {
//			Long refundedAmountTotal = purchasePaymentCc.getRefundedAmountTotal() + paymentCc.getAmount();
//			Long refundedAmountRemaining = purchasePaymentCc.getRefundedAmountRemaining() - paymentCc.getAmount();			
//
//			purchasePaymentCc.setRefunded(true);
//			purchasePaymentCc.setRefundedAmountTotal(refundedAmountTotal);
//			purchasePaymentCc.setRefundedAmountRemaining(refundedAmountRemaining);
//		}
//		
//		paymentCc.setModified(LocalDateTime.now());
//		paymentCcRepository.saveAndFlush(paymentCc);
//		
//		purchasePaymentCc.setModified(LocalDateTime.now());
//		paymentCcRepository.saveAndFlush(purchasePaymentCc);
	}

	@Override
	@Transactional(readOnly = false)
	public void saveFailedTx(PaymentRequest paymentRequest, PaymentData paymentData) {
		
		// get the purchase transaction
		
//		PaymentTxCc purchasePaymentCc = paymentData
//				.getRelatedPaymentData()
//				.get(RelatedPayment.purchase.name())
//				.getPaymentCc();
//				
//		PaymentTxCc paymentCc = paymentData.getPaymentCc();
//		paymentCc.setEvent(PaymentTxCcEvent.Failed.name());
//		paymentCc.setModified(LocalDateTime.now());
//		paymentCcRepository.saveAndFlush(paymentCc);
//				
//		purchasePaymentCc.setModified(LocalDateTime.now());
//		paymentCcRepository.saveAndFlush(purchasePaymentCc);
	}

	@Override
	public Boolean routeTx(PaymentRequest paymentRequest, PaymentData paymentData) {
//		Boolean processed = false;
//		PaymentTxCc paymentCc = paymentData.getPaymentCc();
//		
//		// if 'gateway-force-simulation' is true, force all transactions to use simulator
//		
//		if (BooleanUtils.isTrue(omniApiPaymentProperties.getGatewayForceSimulation())) {
//			simulator.simulate(paymentRequest, paymentData);
//			processed = true;
//		}		
//		
//		// if payment is set to 'Test', we will use our simulator
//		
//		else if (StringUtils.equals(paymentCc.getStatus(), PaymentTxCcStatus.Test.name())) {
//			simulator.simulate(paymentRequest, paymentData);
//			processed = true;
//		}
//		
//		// if payment is set to 'Live', let's forward this to the relevant gateway(s)
//		
//		else if (StringUtils.equals(paymentCc.getStatus(), PaymentTxCcStatus.Live.name())) {
////			MerchantProfilePaymentGateway merchantProfilePaymentGateway = routeTxViaGateway(paymentRequest, paymentData);
////			if (merchantProfilePaymentGateway != null) {
////				paymentCc.setPaymentGatewayCode(merchantProfilePaymentGateway.getPaymentGateway().getCode());
////				paymentCc.setPaymentGateway(merchantProfilePaymentGateway.getPaymentGateway());
////				processed = true;
////			}
//		}
//		
//		return processed;
		return null;
	}

	@Override
	public MerchantProfilePaymentGateway routeTxViaGateway(PaymentRequest paymentRequest, PaymentData paymentData) {
		LinkedList<String> txExceptions = new LinkedList<String>();
		
		// process the refund request down the same gateway that the purchase transaction performed in
		
//		MerchantProfilePaymentGateway merchantProfilePaymentGateway = paymentData.getMerchantProfilePaymentGateway();
//		PaymentGateway paymentGateway = merchantProfilePaymentGateway.getPaymentGateway();
//		PaymentGatewayCode paymentGatewayCode = Enum.valueOf(PaymentGatewayCode.class, paymentGateway.getCode());
//		try {
//			switch (paymentGatewayCode) {
//			case omnihubv1:
//				omnihubV1RefundGateway.send(merchantProfilePaymentGateway, paymentRequest, paymentData);
//				break;
//			default:
//				break;
//			}
//		}
//		catch (GatewayException exception) {
//			txExceptions.add(exception.getMessage() + " - Cause: " + exception.getCause().getMessage());
//			merchantProfilePaymentGateway = null;
//		}
//		
//		finaliseTxViaGateway(paymentData, merchantProfilePaymentGateway, txExceptions);
//		return merchantProfilePaymentGateway;
		return null;
	}
	
	@Override
	@Transactional(readOnly = true)
	public void finaliseTxViaGateway(PaymentData paymentData, MerchantProfilePaymentGateway merchantProfilePaymentGateway, LinkedList<String> txExceptions) {
//		if (!txExceptions.isEmpty()) {
//			PaymentTxCc paymentCc = paymentData.getPaymentCc();
//			if (merchantProfilePaymentGateway == null) {
//				
//				io.swipepay.omniapi.common.entity.paymentresponsecc.PaymentResponseCc paymentResponse = paymentResponseRepository
//						.findByCodeAndPaymentMethod(
//								"100", 
//								paymentData.getPaymentMethod());
//				
//				paymentCc.setTxSuccess(false);
//				paymentCc.setTxResult("Declined");
//				paymentCc.setTxResponseCode(paymentResponse.getCode());
//				paymentCc.setTxResponseText(paymentResponse.getText());
//				paymentCc.setPaymentResponse(paymentResponse);
//			}
//			paymentCc.setTxExceptions(txExceptions.toString());
//		}		
	}
	
	@Override
	public PaymentTxCc buildPaymentCc(PaymentRequest paymentRequest, PaymentData paymentData, String cryptoTextData) {
//		OrderDto orderDto = paymentRequest.getPaymentDto().getOrderDto();
//		
//		MerchantProfile merchantProfile = paymentData.getMerchantProfile();
//
//		// the related purchase payment data associated with this refund
//		
//		PaymentTxCc purchasePaymentCc = paymentData
//				.getRelatedPaymentData()
//				.get(RelatedPayment.purchase.name())
//				.getPaymentCc();
//		
//		// build the refund transaction
//		
//		PaymentType paymentType = paymentData.getPaymentType();
//		
//		PaymentTxCc paymentCc = new PaymentTxCc(
//				"cc_" + UUID.randomUUID().toString().replaceAll("-", ""), 
//				orderDto.getReference(),
//				null, 
//				purchasePaymentCc.getCurrencyCode(), 
//				purchasePaymentCc.getCcName(), 
//				purchasePaymentCc.getCcEmail(), 
//				purchasePaymentCc.getCcBin(), 
//				purchasePaymentCc.getCcPan(), 
//				purchasePaymentCc.getCcNumber(), 
//				null, 
//				purchasePaymentCc.getCcExpiryMonth(), 
//				purchasePaymentCc.getCcExpiryYear(), 
//				paymentType.getCode(), 
//				merchantProfile.getStatus(), 
//				PaymentTxCcEvent.New.name(), 
//				paymentRequest.getClientIpAddress(), 
//				null, 
//				null, 
//				null, 
//				null, 
//				null, 
//				null, 
//				null, 
//				null, 
//				null, 
//				null, 
//				null, 
//				null, 
//				null, 
//				null, 
//				null, 
//				null, 
//				null, 
//				null, 
//				null, 
//				purchasePaymentCc.getCode(), 
//				null, 
//				LocalDateTime.now(), 
//				merchantProfile, 
//				purchasePaymentCc.getCurrency(), 
//				purchasePaymentCc.getCard(), 
//				paymentType, 
//				null, 
//				null, 
//				null);
//		
//		
//		// if amount was supplied in the refund request
//		
//		if (StringUtils.isNotBlank(orderDto.getAmount())) {
//			paymentCc.setAmount(Long.parseLong(orderDto.getAmount()));
//		}
//		
//		// no amount supplied, refund the remaining amount
//		
//		else {
//			paymentCc.setAmount(purchasePaymentCc.getRefundedAmountRemaining());
//		}
//		
//		return paymentCc;
		return null;
	}

	@Override
	public LinkedList<PaymentTxCcMetadata> buildPaymentCcMetadata(PaymentRequest paymentRequest, PaymentData paymentData) {
		return null;
	}

	@Override
	public PaymentResponse buildPaymentResponse(PaymentData paymentData) {
//		PaymentTxCc paymentCc = paymentData.getPaymentCc();
//		
//		// get purchase transaction
//		
//		PaymentTxCc purchasePaymentCc = paymentData
//				.getRelatedPaymentData()
//				.get(RelatedPayment.purchase.name())
//				.getPaymentCc();
//		
//		OrderDto orderDto = new OrderDto(
//				paymentCc.getReference(), 
//				Long.toString(paymentCc.getAmount()), 
//				paymentCc.getCurrencyCode());
//		
//		CreditCardDto creditCardDto = new CreditCardDto(
//				paymentCc.getCcName(), 
//				paymentCc.getCcEmail(), 
//				paymentCc.getCcPan(), 
//				paymentCc.getCcExpiryMonth(), 
//				paymentCc.getCcExpiryYear(), 
//				null, 
//				null, 
//				null);
//		
//		ResultDto resultDto = new ResultDto(
//				paymentCc.getReceiptNo(), 
//				paymentCc.getTxResult(), 
//				paymentCc.getTxResponseCode(), 
//				paymentCc.getTxResponseText(), 
//				null, 
//				null, 
//				null);
//		
//		// if successful, set the settlement/log dates
//		
//		if (BooleanUtils.isTrue(paymentCc.getTxSuccess())) {
//			resultDto.setSettlementDate(paymentCc.getTxSettlementDate());
//			resultDto.setLogDate(calendarSupport.format(paymentCc.getTxLogDate(), "yyyy-MM-dd HH:mm:ss"));
//		}
//		
//		// if not successful, set the errors
//		
//		else {
//			resultDto.setErrors(paymentCc.getTxErrors());				
//		}
//		
//		PaymentDto paymentDto = new PaymentDto(
//				paymentData.getPaymentMethod().getCode(), 
//				paymentCc.getPaymentTypeCode(), 
//				purchasePaymentCc.getCode(), 
//				paymentCc.getTxSuccess(), 
//				null, 
//				null, 
//				null, 
//				null, 
//				purchasePaymentCc.getRefunded(), 
//				Long.toString(purchasePaymentCc.getRefundedAmountTotal()), 
//				Long.toString(purchasePaymentCc.getRefundedAmountRemaining()), 
//				paymentCc.getCode(), 
//				orderDto, 
//				creditCardDto, 
//				null, 
//				resultDto);
//		
//		PaymentResponse paymentResponse = new PaymentResponse(new StatusDto(Instant.now().toEpochMilli(), Status.RS_0000));
//		paymentResponse.setPaymentDto(paymentDto);
//		return paymentResponse;
		return null;
	}
	
}