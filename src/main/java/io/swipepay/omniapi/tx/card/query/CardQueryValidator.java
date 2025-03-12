package io.swipepay.omniapi.tx.card.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.paymenttxcc.PaymentTxCc;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.ValidationException;
import io.swipepay.omniapi.common.validator.RequestValidator;
import io.swipepay.omniapi.tx.TxValidator;

@Component
public class CardQueryValidator extends TxValidator {
	
	@Autowired
	private RequestValidator requestValidator;
	
	public PaymentTxCc validate(
			CardQueryRequest cardQueryRequest, 
			PaymentTxCc paymentTxCc, 
			String clientIpAddress) 
					throws ValidationException {
		
		MerchantProfile merchantProfile = paymentTxCc.getMerchantProfile();
		try {
			requestValidator.validateClientIpAddress(clientIpAddress);
			requestValidator.validateMerchantProfile(cardQueryRequest.getMerchantProfile(), merchantProfile);
			
			paymentTxCc = validatePaymentTxCc(cardQueryRequest.getCode(), merchantProfile);
		}
		catch (ValidationException exception) {
			throw exception;
		}
		catch (Exception exception) {
			throw new ValidationException(
					Status.RS_0005, 
					"Validation failed due to an internal server error", 
					exception);
		}
		return paymentTxCc;
	}
}