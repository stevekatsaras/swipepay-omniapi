package io.swipepay.omniapi.common.exception;

import io.swipepay.omniapi.common.enums.Status;

public class OmniApiException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Status status;
	
	public OmniApiException(Status status, String message, Throwable cause) {
		super(message, cause);
		this.status = status;
	}
	
	public Status getStatus() {
		return status;
	}
}