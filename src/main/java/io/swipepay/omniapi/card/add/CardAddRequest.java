package io.swipepay.omniapi.card.add;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swipepay.omniapi.common.payload.OmniApiJsonRequest;

public class CardAddRequest extends OmniApiJsonRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private String merchantProfile;
	private String name;
	private String email;
	private String pan;
	private String expiryMonth;
	private String expiryYear;
	private String cvv;

	public CardAddRequest() {
		
	}
	
	public String getMerchantProfile() {
		return merchantProfile;
	}

	public void setMerchantProfile(String merchantProfile) {
		this.merchantProfile = merchantProfile;
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}