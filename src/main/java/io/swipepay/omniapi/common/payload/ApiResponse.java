package io.swipepay.omniapi.common.payload;

import java.io.Serializable;
import java.time.Instant;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.payload.dto.StatusDto;

public class ApiResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "status")
	private StatusDto statusDto;
	
	public ApiResponse(Status status) {
		statusDto = new StatusDto(Instant.now().toEpochMilli(), status);
	}
	
	public StatusDto getStatusDto() {
		return statusDto;
	}

	public void setStatusDto(StatusDto statusDto) {
		this.statusDto = statusDto;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}