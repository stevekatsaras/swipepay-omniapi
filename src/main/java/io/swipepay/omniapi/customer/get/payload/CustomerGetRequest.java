package io.swipepay.omniapi.customer.get.payload;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swipepay.omniapi.common.payload.OmniApiJsonRequest;
import io.swipepay.omniapi.customer.get.payload.dto.CustomerGetDto;

public class CustomerGetRequest extends OmniApiJsonRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "customer")
	private CustomerGetDto customerGetDto;
	
	public CustomerGetRequest() {
		
	}
	
	public CustomerGetDto getCustomerGetDto() {
		return customerGetDto;
	}

	public void setCustomerGetDto(CustomerGetDto customerGetDto) {
		this.customerGetDto = customerGetDto;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}