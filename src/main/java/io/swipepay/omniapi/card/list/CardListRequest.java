package io.swipepay.omniapi.card.list;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swipepay.omniapi.common.payload.OmniApiJsonRequest;

public class CardListRequest extends OmniApiJsonRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private String merchantProfile;
	
	public CardListRequest() {
		
	}

	public String getMerchantProfile() {
		return merchantProfile;
	}

	public void setMerchantProfile(String merchantProfile) {
		this.merchantProfile = merchantProfile;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}