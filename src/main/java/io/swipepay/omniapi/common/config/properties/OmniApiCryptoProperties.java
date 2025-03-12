package io.swipepay.omniapi.common.config.properties;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class OmniApiCryptoProperties {
	private String authAccessKey;
	private String authSecretKey;
	private String kmsCmkRegion;
	private String kmsCmkId;
	private String kmsDataKeyAlg;
	private String kmsDataKeySpec;
	
	public OmniApiCryptoProperties() {
		
	}
	
	public String getAuthAccessKey() {
		return authAccessKey;
	}

	public void setAuthAccessKey(String authAccessKey) {
		this.authAccessKey = authAccessKey;
	}

	public String getAuthSecretKey() {
		return authSecretKey;
	}

	public void setAuthSecretKey(String authSecretKey) {
		this.authSecretKey = authSecretKey;
	}

	public String getKmsCmkRegion() {
		return kmsCmkRegion;
	}

	public void setKmsCmkRegion(String kmsCmkRegion) {
		this.kmsCmkRegion = kmsCmkRegion;
	}

	public String getKmsCmkId() {
		return kmsCmkId;
	}

	public void setKmsCmkId(String kmsCmkId) {
		this.kmsCmkId = kmsCmkId;
	}

	public String getKmsDataKeyAlg() {
		return kmsDataKeyAlg;
	}

	public void setKmsDataKeyAlg(String kmsDataKeyAlg) {
		this.kmsDataKeyAlg = kmsDataKeyAlg;
	}

	public String getKmsDataKeySpec() {
		return kmsDataKeySpec;
	}

	public void setKmsDataKeySpec(String kmsDataKeySpec) {
		this.kmsDataKeySpec = kmsDataKeySpec;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}