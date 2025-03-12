package io.swipepay.omniapi.customer.list.payload;

import java.io.Serializable;
import java.util.LinkedList;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.payload.OmniApiJsonResponse;
import io.swipepay.omniapi.customer.list.payload.dto.CustomerListDto;

public class CustomerListResponse extends OmniApiJsonResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "customers")
	private LinkedList<CustomerListDto> customerListDtos;
	
	public CustomerListResponse(Status status) {
		super(status);
	}

	public LinkedList<CustomerListDto> getCustomerListDtos() {
		return customerListDtos;
	}

	public void setCustomerListDtos(LinkedList<CustomerListDto> customerListDtos) {
		this.customerListDtos = customerListDtos;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}