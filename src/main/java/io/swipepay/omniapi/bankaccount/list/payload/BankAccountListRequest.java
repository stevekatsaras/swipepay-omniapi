package io.swipepay.omniapi.bankaccount.list.payload;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swipepay.omniapi.common.payload.OmniApiJsonRequest;

public class BankAccountListRequest extends OmniApiJsonRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public BankAccountListRequest() {
		
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}