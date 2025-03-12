package io.swipepay.omniapi.common.payload.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swipepay.omniapi.common.enums.Status;

@JsonPropertyOrder({
	"timestamp", 
	"code", 
	"text"})
public class StatusDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long timestamp;
	private String code;
	private String text;
	
	public StatusDto(Long timestamp, Status status) {
		this.timestamp = timestamp;
		this.code = status.getCode();
		this.text = status.getText();
	}
	
	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}