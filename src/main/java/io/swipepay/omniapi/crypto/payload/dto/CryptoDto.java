package io.swipepay.omniapi.crypto.payload.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({
	"cipher-data-key", 
	"cipher-text-data", 
	"plain-text-data"})
public class CryptoDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "cipher-data-key")
	private String cipherDataKey;
	
	@JsonProperty(value = "cipher-text-data")
	private String cipherTextData;
	
	@JsonProperty(value = "plain-text-data")
	@ToStringExclude
	private String plainTextData;
	
	public CryptoDto() {
		
	}
	
	public CryptoDto(String cipherDataKey, String cipherTextData, String plainTextData) {
		this.cipherDataKey = cipherDataKey;
		this.cipherTextData = cipherTextData;
		this.plainTextData = plainTextData;
	}

	public String getCipherDataKey() {
		return cipherDataKey;
	}

	public void setCipherDataKey(String cipherDataKey) {
		this.cipherDataKey = cipherDataKey;
	}

	public String getCipherTextData() {
		return cipherTextData;
	}

	public void setCipherTextData(String cipherTextData) {
		this.cipherTextData = cipherTextData;
	}

	public String getPlainTextData() {
		return plainTextData;
	}

	public void setPlainTextData(String plainTextData) {
		this.plainTextData = plainTextData;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}