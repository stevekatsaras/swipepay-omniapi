package io.swipepay.omniapi.common.payload;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class OmniApiCsvRequest extends ApiRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//TODO add a merchant profile section for this
	
	public OmniApiCsvRequest() {
		
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}