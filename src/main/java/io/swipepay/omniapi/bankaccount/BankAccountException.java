package io.swipepay.omniapi.bankaccount;

import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.OmniApiException;

public class BankAccountException extends OmniApiException {
	private static final long serialVersionUID = 1L;

	public BankAccountException(Status status, String message, Throwable cause) {
		super(status, message, cause);
	}
}