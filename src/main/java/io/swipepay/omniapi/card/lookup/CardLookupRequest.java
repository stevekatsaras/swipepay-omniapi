package io.swipepay.omniapi.card.lookup;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swipepay.omniapi.common.payload.OmniApiJsonRequest;

public class CardLookupRequest extends OmniApiJsonRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private String merchantProfile;
	private String pan;
	private String expiryMonth;
	private String expiryYear;
	
	public CardLookupRequest() {
		
	}
	
	public String getMerchantProfile() {
		return merchantProfile;
	}

	public void setMerchantProfile(String merchantProfile) {
		this.merchantProfile = merchantProfile;
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}