package io.swipepay.omniapi.common.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swipepay.omniapi.bankaccount.BankAccountException;
import io.swipepay.omniapi.card.CardException;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.AuthoriseException;
import io.swipepay.omniapi.common.exception.RequestException;
import io.swipepay.omniapi.common.exception.ValidationException;
import io.swipepay.omniapi.common.payload.ApiResponse;
import io.swipepay.omniapi.crypto.CryptoException;
import io.swipepay.omniapi.customer.CustomerException;
import io.swipepay.omniapi.tx.GatewayException;
import io.swipepay.omniapi.tx.TxException;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	// HTTP 401
	
	@ExceptionHandler(AuthoriseException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ApiResponse handle(AuthoriseException exception, HttpServletRequest request) {
		return new ApiResponse(exception.getStatus());
	}
	
	// Catch all (default to HTTP 500)
	
	@ExceptionHandler(RequestException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiResponse handle(RequestException exception, HttpServletRequest request) {
		return new ApiResponse(exception.getStatus());
	}
	
	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiResponse handle(ValidationException exception, HttpServletRequest request) {
		return new ApiResponse(exception.getStatus());
	}
	
	@ExceptionHandler(CryptoException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiResponse handle(CryptoException exception, HttpServletRequest request) {
		return new ApiResponse(exception.getStatus());
	}

	@ExceptionHandler(TxException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiResponse handle(TxException exception, HttpServletRequest request) {
		return new ApiResponse(exception.getStatus());
	}
	
	@ExceptionHandler(GatewayException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiResponse handle(GatewayException exception, HttpServletRequest request) {
		return new ApiResponse(exception.getStatus());
	}
	
	@ExceptionHandler(BankAccountException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiResponse handle(BankAccountException exception, HttpServletRequest request) {
		return new ApiResponse(exception.getStatus());
	}
	
	@ExceptionHandler(CardException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiResponse handle(CardException exception, HttpServletRequest request) {
		return new ApiResponse(exception.getStatus());
	}
	
	@ExceptionHandler(CustomerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiResponse handle(CustomerException exception, HttpServletRequest request) {
		return new ApiResponse(exception.getStatus());
	}		
	
	
	
	
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiResponse handle(Exception exception, HttpServletRequest request) {
		return new ApiResponse(Status.RS_0500);
	}
}