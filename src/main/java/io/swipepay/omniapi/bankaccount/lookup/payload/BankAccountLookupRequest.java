package io.swipepay.omniapi.bankaccount.lookup.payload;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swipepay.omniapi.bankaccount.lookup.payload.dto.BankAccountLookupDto;
import io.swipepay.omniapi.common.payload.OmniApiJsonRequest;

public class BankAccountLookupRequest extends OmniApiJsonRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "bankAccount")
	private BankAccountLookupDto bankAccountLookupDto;
	
	public BankAccountLookupRequest() {
		
	}
	
	public BankAccountLookupDto getBankAccountLookupDto() {
		return bankAccountLookupDto;
	}

	public void setBankAccountLookupDto(BankAccountLookupDto bankAccountLookupDto) {
		this.bankAccountLookupDto = bankAccountLookupDto;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}