package io.swipepay.omniapi.payment.action.creditcard.gateway;

import java.time.LocalDateTime;
import java.util.Arrays;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.swipepay.omniapi.common.entity.paymentresponsecc.PaymentResponseCc;
import io.swipepay.omniapi.common.entity.paymentresponsecc.PaymentResponseCcRepository;
import io.swipepay.omniapi.common.entity.paymenttxcc.PaymentTxCc;
import io.swipepay.omniapi.common.enums.RelatedPayment;
import io.swipepay.omniapi.common.support.CalendarSupport;
import io.swipepay.omniapi.payment.PaymentData;
import io.swipepay.omniapi.payment.payload.PaymentRequest;

@Service
public class Simulator {
	
	@Autowired
	private CalendarSupport calendarSupport;
	
	@Autowired
	private PaymentResponseCcRepository paymentResponseRepository;
	
	@Transactional(readOnly = true)
	public void simulate(PaymentRequest paymentRequest, PaymentData paymentData) {
//		PaymentTxCc paymentCc = paymentData.getPaymentCc();
//		
//		String wholeCentsAmount = Long.toString(paymentCc.getAmount());
//		String responseCode = wholeCentsAmount.substring(Math.max(wholeCentsAmount.length() - 2, 0));
//		
//		String[] approvedCodes = {"00", "08", "11", "16", "77"};
//		Boolean approved = Arrays.asList(approvedCodes).contains(responseCode);
//		
//		PaymentResponseCc paymentResponse = paymentResponseRepository.findByCodeAndPaymentMethod(
//				responseCode, 
//				paymentData.getPaymentMethod());
//		
//		paymentCc.setTxSuccess(approved);
//		paymentCc.setTxResult(((approved) ? "Approved" : "Declined"));
//		paymentCc.setTxId(RandomStringUtils.randomAlphanumeric(12));
//		paymentCc.setTxResponseCode(paymentResponse.getCode());
//		paymentCc.setTxResponseText(paymentResponse.getText());
//		paymentCc.setPaymentResponse(paymentResponse);
//		
//		if (paymentCc.getTxSuccess()) {
//			
//			// all payment types settle except for preauth
//			
//			if (!StringUtils.equals(paymentCc.getPaymentTypeCode(), PaymentTypeCode.preauth.name())) {
//				paymentCc.setTxSettlementDate(calendarSupport.format(LocalDateTime.now(), "yyyy-MM-dd"));
//			}
//			paymentCc.setTxLogDate(LocalDateTime.now());
//			
//			// capture and refund requests have related payment transactions (ie. payment/preauth).
//			// need to set the settlement date
//			
//			if (StringUtils.equals(paymentCc.getPaymentTypeCode(), PaymentTypeCode.capture.name()) || 
//					StringUtils.equals(paymentCc.getPaymentTypeCode(), PaymentTypeCode.refund.name())) {
//				
//				PaymentTxCc relatedPaymentCc = paymentData
//						.getRelatedPaymentData()
//						.get(RelatedPayment.purchase.name())
//						.getPaymentCc();
//				
//				relatedPaymentCc.setTxSettlementDate(paymentCc.getTxSettlementDate());
//			}
//		}
	}
}