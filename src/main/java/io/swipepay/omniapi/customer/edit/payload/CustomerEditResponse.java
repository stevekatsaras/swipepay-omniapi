package io.swipepay.omniapi.customer.edit.payload;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.payload.OmniApiJsonResponse;
import io.swipepay.omniapi.customer.edit.payload.dto.CustomerEditDto;

public class CustomerEditResponse extends OmniApiJsonResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "customer")
	private CustomerEditDto customerEditDto;
	
	public CustomerEditResponse(Status status) {
		super(status);
	}
	
	public CustomerEditDto getCustomerEditDto() {
		return customerEditDto;
	}

	public void setCustomerEditDto(CustomerEditDto customerEditDto) {
		this.customerEditDto = customerEditDto;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}