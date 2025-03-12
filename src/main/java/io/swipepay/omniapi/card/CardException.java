package io.swipepay.omniapi.card;

import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.OmniApiException;

public class CardException extends OmniApiException {
	private static final long serialVersionUID = 1L;

	public CardException(Status status, String message, Throwable cause) {
		super(status, message, cause);
	}
}