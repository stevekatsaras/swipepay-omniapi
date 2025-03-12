package io.swipepay.omniapi.card.get;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.swipepay.omniapi.card.CardValidator;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.paymentcard.PaymentCard;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.ValidationException;
import io.swipepay.omniapi.common.validator.RequestValidator;

@Component
public class CardGetValidator extends CardValidator {
	
	@Autowired
	private RequestValidator requestValidator;
	
	public PaymentCard validate(
			CardGetRequest cardGetRequest, 
			PaymentCard paymentCard, 
			String clientIpAddress) 
					throws ValidationException {
		
		MerchantProfile merchantProfile = paymentCard.getMerchantProfile();
		try {
			requestValidator.validateClientIpAddress(clientIpAddress);
			requestValidator.validateMerchantProfile(cardGetRequest.getMerchantProfile(), merchantProfile);
			
			paymentCard = validatePaymentCard(cardGetRequest.getCode(), merchantProfile, false);
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
		return paymentCard;
	}
}