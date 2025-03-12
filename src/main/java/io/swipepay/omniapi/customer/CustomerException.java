package io.swipepay.omniapi.customer;

import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.OmniApiException;

public class CustomerException extends OmniApiException {
	private static final long serialVersionUID = 1L;

	public CustomerException(Status status, String message, Throwable cause) {
		super(status, message, cause);
	}
}