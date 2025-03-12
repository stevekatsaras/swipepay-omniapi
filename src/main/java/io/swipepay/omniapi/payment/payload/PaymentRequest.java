package io.swipepay.omniapi.payment.payload;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swipepay.omniapi.common.payload.OmniApiJsonRequest;
import io.swipepay.omniapi.payment.payload.dto.PaymentDto;

public class PaymentRequest extends OmniApiJsonRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "payment")
	private PaymentDto paymentDto;
	
	public PaymentRequest() {
		
	}

	public PaymentDto getPaymentDto() {
		return paymentDto;
	}

	public void setPaymentDto(PaymentDto paymentDto) {
		this.paymentDto = paymentDto;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}