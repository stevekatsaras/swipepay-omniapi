package io.swipepay.omniapi.bankaccount.list.payload;

import java.io.Serializable;
import java.util.LinkedList;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swipepay.omniapi.bankaccount.list.payload.dto.BankAccountListDto;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.payload.OmniApiJsonResponse;

public class BankAccountListResponse extends OmniApiJsonResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "bankAccounts")
	private LinkedList<BankAccountListDto> bankAccountListDtos;

	public BankAccountListResponse(Status status) {
		super(status);
	}
	
	public LinkedList<BankAccountListDto> getBankAccountListDtos() {
		return bankAccountListDtos;
	}

	public void setBankAccountListDtos(LinkedList<BankAccountListDto> bankAccountListDtos) {
		this.bankAccountListDtos = bankAccountListDtos;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
