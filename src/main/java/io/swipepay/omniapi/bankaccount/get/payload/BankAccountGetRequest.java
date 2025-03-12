package io.swipepay.omniapi.bankaccount.get.payload;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swipepay.omniapi.bankaccount.get.payload.dto.BankAccountGetDto;
import io.swipepay.omniapi.common.payload.OmniApiJsonRequest;

public class BankAccountGetRequest extends OmniApiJsonRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "bankAccount")
	private BankAccountGetDto bankAccountGetDto;
	
	public BankAccountGetRequest() {
		
	}
	
	public BankAccountGetDto getBankAccountGetDto() {
		return bankAccountGetDto;
	}

	public void setBankAccountGetDto(BankAccountGetDto bankAccountGetDto) {
		this.bankAccountGetDto = bankAccountGetDto;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}