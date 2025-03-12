package io.swipepay.omniapi.batch.payload;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BatchRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public BatchRequest() {
		
	}
	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}