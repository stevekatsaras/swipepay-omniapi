package io.swipepay.omniapi.crypto.payload;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swipepay.omniapi.common.payload.OmniApiJsonRequest;
import io.swipepay.omniapi.crypto.payload.dto.CryptoDto;

public class CryptoRequest extends OmniApiJsonRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "crypto")
	private CryptoDto cryptoDto;	
	
	public CryptoRequest() {
		
	}

	public CryptoDto getCryptoDto() {
		return cryptoDto;
	}

	public void setCryptoDto(CryptoDto cryptoDto) {
		this.cryptoDto = cryptoDto;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}