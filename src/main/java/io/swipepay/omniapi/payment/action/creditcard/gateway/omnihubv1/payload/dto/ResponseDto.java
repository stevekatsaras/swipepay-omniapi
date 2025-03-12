package io.swipepay.omniapi.payment.action.creditcard.gateway.omnihubv1.payload.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String authorization;
	private String id;
	
	@JsonProperty("card_number")
	private String cardNumber;
	
	@JsonProperty("card_holder")
	private String cardHolder;
	
	@JsonProperty("card_expiry")
	private String cardExpiry;

	@JsonProperty("card_type")
	private String cardType;

	@JsonProperty("card_category")
	private String cardCategory;
	
	@JsonProperty("card_subcategory")
	private String cardSubcategory;
	
	private String amount;
	
	@JsonProperty("decimal_amount")
	private String decmialAmount;
	
	private Boolean successful;
	private String message;
	private String reference;
	private String currency;
	
	@JsonProperty("transaction_id")
	private String transactionId;
	
	@JsonProperty("settlement_date")
	private String settlementDate;
	
	@JsonProperty("transaction_date")
	private String transactionDate;
	
	@JsonProperty("response_code")
	private String responseCode;
	
	private Boolean captured;
	
	@JsonProperty("captured_amount")
	private String capturedAmount;
	
	private String rrn;
	
	@JsonProperty("cvv_match")
	private String cvvMatch;
	
	public ResponseDto() {
		
	}
	
	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

	public String getCardExpiry() {
		return cardExpiry;
	}

	public void setCardExpiry(String cardExpiry) {
		this.cardExpiry = cardExpiry;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardCategory() {
		return cardCategory;
	}

	public void setCardCategory(String cardCategory) {
		this.cardCategory = cardCategory;
	}

	public String getCardSubcategory() {
		return cardSubcategory;
	}

	public void setCardSubcategory(String cardSubcategory) {
		this.cardSubcategory = cardSubcategory;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDecmialAmount() {
		return decmialAmount;
	}

	public void setDecmialAmount(String decmialAmount) {
		this.decmialAmount = decmialAmount;
	}

	public Boolean getSuccessful() {
		return successful;
	}

	public void setSuccessful(Boolean successful) {
		this.successful = successful;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(String settlementDate) {
		this.settlementDate = settlementDate;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public Boolean getCaptured() {
		return captured;
	}

	public void setCaptured(Boolean captured) {
		this.captured = captured;
	}

	public String getCapturedAmount() {
		return capturedAmount;
	}

	public void setCapturedAmount(String capturedAmount) {
		this.capturedAmount = capturedAmount;
	}

	public String getRrn() {
		return rrn;
	}

	public void setRrn(String rrn) {
		this.rrn = rrn;
	}

	public String getCvvMatch() {
		return cvvMatch;
	}

	public void setCvvMatch(String cvvMatch) {
		this.cvvMatch = cvvMatch;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}