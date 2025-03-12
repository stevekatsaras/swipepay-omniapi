package io.swipepay.omniapi.bankaccount.edit.payload;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swipepay.omniapi.bankaccount.edit.payload.dto.BankAccountEditDto;
import io.swipepay.omniapi.common.payload.OmniApiJsonRequest;

public class BankAccountEditRequest extends OmniApiJsonRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "bankAccount")
	private BankAccountEditDto bankAccountEditDto;
	
	public BankAccountEditRequest() {
		
	}
	
	public BankAccountEditDto getBankAccountEditDto() {
		return bankAccountEditDto;
	}

	public void setBankAccountEditDto(BankAccountEditDto bankAccountEditDto) {
		this.bankAccountEditDto = bankAccountEditDto;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}