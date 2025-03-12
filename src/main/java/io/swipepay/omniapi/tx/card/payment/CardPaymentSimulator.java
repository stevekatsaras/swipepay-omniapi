package io.swipepay.omniapi.tx.card.payment;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.swipepay.omniapi.common.entity.paymentresponsecc.PaymentResponseCc;
import io.swipepay.omniapi.common.entity.paymentresponsecc.PaymentResponseCcRepository;
import io.swipepay.omniapi.common.entity.paymenttxcc.PaymentTxCc;
import io.swipepay.omniapi.common.support.CalendarSupport;

@Component
public class CardPaymentSimulator {
	
	@Autowired
	private CalendarSupport calendarSupport;
	
	@Autowired
	private PaymentResponseCcRepository paymentResponseCcRepository;
	
	@Transactional(readOnly = true)
	public Boolean simulate(PaymentTxCc paymentTxCc) {
		String wholeCentsAmount = Long.toString(paymentTxCc.getAmount());
		String responseCode = wholeCentsAmount.substring(Math.max(wholeCentsAmount.length() - 2, 0));
		
		String[] approvedCodes = {"00", "08", "11", "16", "77"};
		Boolean approved = Arrays.asList(approvedCodes).contains(responseCode);
		
		PaymentResponseCc paymentResponseCc = paymentResponseCcRepository.findByCode(responseCode);
		
		paymentTxCc.setTxSuccess(approved);
		paymentTxCc.setTxResult(((approved) ? "Approved" : "Declined"));
		paymentTxCc.setTxId(RandomStringUtils.randomAlphanumeric(12));
		paymentTxCc.setTxResponseCode(paymentResponseCc.getCode());
		paymentTxCc.setTxResponseText(paymentResponseCc.getText());
		paymentTxCc.setPaymentResponseCc(paymentResponseCc);

		if (paymentTxCc.getTxSuccess()) {
			paymentTxCc.setTxSettlementDate(calendarSupport.format(LocalDateTime.now(), "yyyy-MM-dd"));
			paymentTxCc.setTxLogDate(LocalDateTime.now());
		}
		
		return true;
	}
}