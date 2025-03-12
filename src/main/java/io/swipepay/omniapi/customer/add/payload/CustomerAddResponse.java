package io.swipepay.omniapi.customer.add.payload;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.payload.OmniApiJsonResponse;
import io.swipepay.omniapi.customer.add.payload.dto.CustomerAddDto;

public class CustomerAddResponse extends OmniApiJsonResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "customer")
	private CustomerAddDto customerAddDto;
	
	public CustomerAddResponse(Status status) {
		super(status);
	}
	
	public CustomerAddDto getCustomerAddDto() {
		return customerAddDto;
	}

	public void setCustomerAddDto(CustomerAddDto customerAddDto) {
		this.customerAddDto = customerAddDto;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}