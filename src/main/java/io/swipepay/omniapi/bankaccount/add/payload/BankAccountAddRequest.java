package io.swipepay.omniapi.bankaccount.add.payload;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swipepay.omniapi.bankaccount.add.payload.dto.BankAccountAddDto;
import io.swipepay.omniapi.common.payload.OmniApiJsonRequest;

public class BankAccountAddRequest extends OmniApiJsonRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "bankAccount")
	private BankAccountAddDto bankAccountAddDto;
	
	public BankAccountAddRequest() {
		
	}
	
	public BankAccountAddDto getBankAccountAddDto() {
		return bankAccountAddDto;
	}

	public void setBankAccountAddDto(BankAccountAddDto bankAccountAddDto) {
		this.bankAccountAddDto = bankAccountAddDto;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}