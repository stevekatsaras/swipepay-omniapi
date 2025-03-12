package io.swipepay.omniapi.payment;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swipepay.omniapi.common.entity.paymentcard.PaymentCard;
import io.swipepay.omniapi.common.entity.paymenttxcc.PaymentTxCc;
import io.swipepay.omniapi.common.entity.paymenttxccmetadata.PaymentTxCcMetadata;

public class PaymentData {
//	private PaymentMethod paymentMethod;
//	
//	// payment cc 
//	
//	private PaymentTxCc paymentCc;
//	private LinkedList<PaymentTxCcMetadata> paymentCcMetadata;
//	private PaymentCard paymentCcToken;
//	
//	// stored transactions that have a relationship to the primary transaction
//	// use cases include:
//	// 1. cc capture (related to preauth)
//	// 2. cc refund request (related to payment/preauth)
//	
//	private LinkedHashMap<String, PaymentData> relatedPaymentData;
//	
//	public PaymentData() {
//		relatedPaymentData = new LinkedHashMap<String, PaymentData>();
//	}
//
//	public PaymentMethod getPaymentMethod() {
//		return paymentMethod;
//	}
//
//	public void setPaymentMethod(PaymentMethod paymentMethod) {
//		this.paymentMethod = paymentMethod;
//	}
//
//	public PaymentTxCc getPaymentCc() {
//		return paymentCc;
//	}
//
//	public void setPaymentCc(PaymentTxCc paymentCc) {
//		this.paymentCc = paymentCc;
//	}
//
//	public LinkedList<PaymentTxCcMetadata> getPaymentCcMetadata() {
//		return paymentCcMetadata;
//	}
//
//	public void setPaymentCcMetadata(LinkedList<PaymentTxCcMetadata> paymentCcMetadata) {
//		this.paymentCcMetadata = paymentCcMetadata;
//	}
//
//	public PaymentCard getPaymentCcToken() {
//		return paymentCcToken;
//	}
//
//	public void setPaymentCcToken(PaymentCard paymentCcToken) {
//		this.paymentCcToken = paymentCcToken;
//	}
//
//	public LinkedHashMap<String, PaymentData> getRelatedPaymentData() {
//		return relatedPaymentData;
//	}
//
//	public void setRelatedPaymentData(LinkedHashMap<String, PaymentData> relatedPaymentData) {
//		this.relatedPaymentData = relatedPaymentData;
//	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}