package io.swipepay.omniapi.payment.action.creditcard.gateway.omnihubv1.payload;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OmnihubV1Request implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "transaction_id")
	private String transactionId;
	
	private String amount;
	private String currency;
	private String reference;
	
	@JsonProperty(value = "customer_ip")
	private String customerIp;
	
	@JsonProperty(value = "card_holder")
	private String cardHolder;
	
	@ToStringExclude
	@JsonProperty(value = "card_number")
	private String cardNumber;
	
	@JsonProperty(value = "card_expiry")
	private String cardExpiry;
	
	private String cvv;
	private String capture;
	
	public OmnihubV1Request() {
		
	}
	
	public OmnihubV1Request(
			String transactionId, 
			String amount, 
			String currency, 
			String reference, 
			String customerIp,
			String cardHolder, 
			String cardNumber, 
			String cardExpiry, 
			String cvv, 
			String capture) {

		this.transactionId = transactionId;
		this.amount = amount;
		this.currency = currency;
		this.reference = reference;
		this.customerIp = customerIp;
		this.cardHolder = cardHolder;
		this.cardNumber = cardNumber;
		this.cardExpiry = cardExpiry;
		this.cvv = cvv;
		this.capture = capture;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getAmount() {
		return amount;
	}
	
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getCustomerIp() {
		return customerIp;
	}

	public void setCustomerIp(String customerIp) {
		this.customerIp = customerIp;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardExpiry() {
		return cardExpiry;
	}

	public void setCardExpiry(String cardExpiry) {
		this.cardExpiry = cardExpiry;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getCapture() {
		return capture;
	}

	public void setCapture(String capture) {
		this.capture = capture;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}