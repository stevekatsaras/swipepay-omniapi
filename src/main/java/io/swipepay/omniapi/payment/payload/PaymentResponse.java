package io.swipepay.omniapi.payment.payload;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swipepay.omniapi.common.payload.OmniApiJsonResponse;
import io.swipepay.omniapi.common.payload.dto.StatusDto;
import io.swipepay.omniapi.payment.payload.dto.PaymentDto;

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({
	"status", 
	"payment"})
public class PaymentResponse extends OmniApiJsonResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "payment")
	private PaymentDto paymentDto;
	
	public PaymentResponse(StatusDto status) {
		super(null);
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