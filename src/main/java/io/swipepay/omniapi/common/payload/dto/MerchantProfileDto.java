package io.swipepay.omniapi.common.payload.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"code"})
public class MerchantProfileDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String code;
	
	public MerchantProfileDto() {
		
	}

	public MerchantProfileDto(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
