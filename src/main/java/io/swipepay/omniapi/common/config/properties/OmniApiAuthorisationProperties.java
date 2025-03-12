package io.swipepay.omniapi.common.config.properties;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class OmniApiAuthorisationProperties {
	private Boolean expirationEnabled;
	private String expirationTimeout;
	
	public OmniApiAuthorisationProperties() {
		
	}
	
	public Boolean getExpirationEnabled() {
		return expirationEnabled;
	}

	public void setExpirationEnabled(Boolean expirationEnabled) {
		this.expirationEnabled = expirationEnabled;
	}

	public String getExpirationTimeout() {
		return expirationTimeout;
	}

	public void setExpirationTimeout(String expirationTimeout) {
		this.expirationTimeout = expirationTimeout;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}