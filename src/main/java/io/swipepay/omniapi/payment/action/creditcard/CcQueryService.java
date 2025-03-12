package io.swipepay.omniapi.payment.action.creditcard;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.swipepay.omniapi.common.entity.merchantprofilepaymentgateway.MerchantProfilePaymentGateway;
import io.swipepay.omniapi.common.entity.paymenttxcc.PaymentTxCc;
import io.swipepay.omniapi.common.entity.paymenttxccmetadata.PaymentTxCcMetadata;
import io.swipepay.omniapi.common.entity.paymenttxccmetadata.PaymentTxCcMetadataRepository;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.payload.dto.StatusDto;
import io.swipepay.omniapi.common.support.CalendarSupport;
import io.swipepay.omniapi.payment.PaymentData;
import io.swipepay.omniapi.payment.payload.PaymentRequest;
import io.swipepay.omniapi.payment.payload.PaymentResponse;
import io.swipepay.omniapi.payment.payload.dto.PaymentDto;

@Service
public class CcQueryService extends CcTxService {
	
	@Autowired
	private CalendarSupport calendarSupport;
	
	@Autowired
	private PaymentTxCcMetadataRepository paymentCcMetadataRepository;

	@Override
	public void saveNewTx(PaymentRequest paymentRequest, PaymentData paymentData) {
		
	}

	@Override
	public void savePendingTx(PaymentRequest paymentRequest, PaymentData paymentData) {
		
	}
	
	@Override
	public void saveProcessedTx(PaymentRequest paymentRequest, PaymentData paymentData) {
		
	}

	@Override
	public void saveFailedTx(PaymentRequest paymentRequest, PaymentData paymentData) {
		
	}

	@Override
	public Boolean routeTx(PaymentRequest paymentRequest, PaymentData paymentData) {
		return null;
	}

	@Override
	public MerchantProfilePaymentGateway routeTxViaGateway(PaymentRequest paymentRequest, PaymentData paymentData) {
		return null;
	}
	
	@Override
	public void finaliseTxViaGateway(PaymentData paymentData, MerchantProfilePaymentGateway merchantProfilePaymentGateway, LinkedList<String> txExceptions) {
		
	}

	@Override
	public PaymentTxCc buildPaymentCc(PaymentRequest paymentRequest, PaymentData paymentData, String cryptoTextData) {
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public LinkedList<PaymentTxCcMetadata> buildPaymentCcMetadata(PaymentRequest paymentRequest, PaymentData paymentData) {
//		return paymentCcMetadataRepository.findByPaymentCc(paymentData.getPaymentCc());
		return null;
	}

	@Override
	public PaymentResponse buildPaymentResponse(PaymentData paymentData) {
//		PaymentTxCc paymentCc = paymentData.getPaymentCc();
//		LinkedList<PaymentTxCcMetadata> paymentCcMetadata = paymentData.getPaymentCcMetadata();
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
//				paymentCc.getPaymentCcTokenCode());
//		
//		LinkedHashMap<String, String> metadata = null;
//		
//		if (CollectionUtils.isNotEmpty(paymentCcMetadata)) {
//			metadata = new LinkedHashMap<String, String>();
//			
//			for(PaymentTxCcMetadata nvp : paymentCcMetadata) {
//				metadata.put(nvp.getMdKey(), nvp.getMdValue());
//			}
//		}
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
//				paymentCc.getCode(), 
//				paymentCc.getTxSuccess(), 
//				paymentCc.getCaptured(), 
//				null, 
//				null, 
//				null, 
//				paymentCc.getRefunded(), 
//				null, 
//				null, 
//				null, 
//				orderDto, 
//				creditCardDto, 
//				metadata, 
//				resultDto);
//		
//		PaymentResponse paymentResponse = new PaymentResponse(new StatusDto(Instant.now().toEpochMilli(), Status.RS_0000));
//		paymentResponse.setPaymentDto(paymentDto);
//		return paymentResponse;
		return null;
	}
	
}