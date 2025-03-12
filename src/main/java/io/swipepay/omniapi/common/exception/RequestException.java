package io.swipepay.omniapi.common.exception;

import io.swipepay.omniapi.common.enums.Status;

public class RequestException extends OmniApiException {
	private static final long serialVersionUID = 1L;

	public RequestException(Status status, String message, Throwable cause) {
		super(status, message, cause);
	}

}