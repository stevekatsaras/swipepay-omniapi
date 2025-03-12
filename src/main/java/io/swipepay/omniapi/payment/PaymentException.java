package io.swipepay.omniapi.payment;

import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.OmniApiException;

public class PaymentException extends OmniApiException {
	private static final long serialVersionUID = 1L;

	public PaymentException(Status status, String message, Throwable cause) {
		super(status, message, cause);
	}
}