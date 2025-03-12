package io.swipepay.omniapi.common.payload;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swipepay.omniapi.common.enums.Status;

public class OmniApiJsonResponse extends ApiResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public OmniApiJsonResponse(Status status) {
		super(status);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}