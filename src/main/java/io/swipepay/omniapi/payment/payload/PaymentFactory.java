package io.swipepay.omniapi.payment.payload;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.RequestException;

public class PaymentFactory {
	
	public static PaymentRequest buildRequest(String jsonPayload) throws RequestException {
		PaymentRequest paymentRequest = null;
		try {
			paymentRequest = new ObjectMapper().readValue(jsonPayload, PaymentRequest.class);
			
			// was the json unmarshalled correctly?
			
			if (paymentRequest == null) {
				throw new RequestException(
						Status.RS_0004, 
						"Deserialization of the JSON payload was successful, yet the PaymentRequest object is NULL", 
						null);
			}
		}
		catch (RequestException exception) {
			throw exception;
		}
		catch (Exception exception) {
			throw new RequestException(
					Status.RS_0004, 
					"Deserialization failed because the JSON payload does not conform to the PaymentRequest class", 
					exception);
		}
		return paymentRequest;
	}
}