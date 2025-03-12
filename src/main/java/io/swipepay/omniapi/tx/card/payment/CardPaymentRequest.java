package io.swipepay.omniapi.tx.card.payment;

import java.io.Serializable;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swipepay.omniapi.common.payload.OmniApiJsonRequest;

public class CardPaymentRequest extends OmniApiJsonRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private String merchantProfile;
	private String code;
	private String reference;
	private String amount;
	private String currency;
	private String name;
	private String email;
	private String pan;
	private String expiryMonth;
	private String expiryYear;
	private String cvv;
	private String cardToken;
	private LinkedHashMap<String, String> metadata;
	
	public CardPaymentRequest() {
		
	}
	
	public String getMerchantProfile() {
		return merchantProfile;
	}

	public void setMerchantProfile(String merchantProfile) {
		this.merchantProfile = merchantProfile;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getExpiryMonth() {
		return expiryMonth;
	}

	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	public String getExpiryYear() {
		return expiryYear;
	}

	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getCardToken() {
		return cardToken;
	}

	public void setCardToken(String cardToken) {
		this.cardToken = cardToken;
	}

	public LinkedHashMap<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(LinkedHashMap<String, String> metadata) {
		this.metadata = metadata;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}