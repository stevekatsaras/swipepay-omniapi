package io.swipepay.omniapi.crypto;

import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.OmniApiException;

public class CryptoException extends OmniApiException {
	private static final long serialVersionUID = 1L;

	public CryptoException(Status status, String message, Throwable cause) {
		super(status, message, cause);
	}
}