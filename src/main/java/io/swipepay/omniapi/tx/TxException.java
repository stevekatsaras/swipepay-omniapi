package io.swipepay.omniapi.tx;

import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.OmniApiException;

public class TxException extends OmniApiException {
	private static final long serialVersionUID = 1L;

	public TxException(Status status, String message, Throwable cause) {
		super(status, message, cause);
	}
}