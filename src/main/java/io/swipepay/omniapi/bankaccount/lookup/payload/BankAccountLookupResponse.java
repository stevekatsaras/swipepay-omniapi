package io.swipepay.omniapi.bankaccount.lookup.payload;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swipepay.omniapi.bankaccount.lookup.payload.dto.BankAccountLookupDto;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.payload.OmniApiJsonResponse;

public class BankAccountLookupResponse extends OmniApiJsonResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "bankAccount")
	private BankAccountLookupDto bankAccountLookupDto;
	
	public BankAccountLookupResponse(Status status) {
		super(status);
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