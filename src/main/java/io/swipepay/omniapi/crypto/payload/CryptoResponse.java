package io.swipepay.omniapi.crypto.payload;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.payload.OmniApiJsonResponse;
import io.swipepay.omniapi.crypto.payload.dto.CryptoDto;

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({
	"status", 
	"crypto"})
public class CryptoResponse extends OmniApiJsonResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "crypto")
	private CryptoDto cryptoDto;

	public CryptoResponse(Status status) {
		super(status);
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