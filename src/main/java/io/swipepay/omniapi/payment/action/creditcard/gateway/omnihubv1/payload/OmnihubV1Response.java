package io.swipepay.omniapi.payment.action.creditcard.gateway.omnihubv1.payload;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swipepay.omniapi.payment.action.creditcard.gateway.omnihubv1.payload.dto.ResponseDto;

public class OmnihubV1Response implements Serializable {
	private static final long serialVersionUID = 1L;
	private Boolean successful;
	private ResponseDto response;
	private List<String> errors;
	private Boolean test;
	
	public OmnihubV1Response() {
		
	}
	
	public Boolean getSuccessful() {
		return successful;
	}

	public void setSuccessful(Boolean successful) {
		this.successful = successful;
	}

	public ResponseDto getResponse() {
		return response;
	}

	public void setResponse(ResponseDto response) {
		this.response = response;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public Boolean getTest() {
		return test;
	}

	public void setTest(Boolean test) {
		this.test = test;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}