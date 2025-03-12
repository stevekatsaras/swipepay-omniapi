package io.swipepay.omniapi.card.edit;

import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.swipepay.omniapi.card.CardValidator;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.paymentcard.PaymentCard;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.ValidationException;
import io.swipepay.omniapi.common.validator.RequestValidator;

@Component
public class CardEditValidator extends CardValidator {
	
	@Autowired
	private RequestValidator requestValidator;
	
	public PaymentCard validate(
			CardEditRequest cardEditRequest, 
			PaymentCard paymentCard, 
			String clientIpAddress) 
					throws ValidationException {
		
		MerchantProfile merchantProfile = paymentCard.getMerchantProfile();
		try {
			requestValidator.validateClientIpAddress(clientIpAddress);
			requestValidator.validateMerchantProfile(cardEditRequest.getMerchantProfile(), merchantProfile);
			
			String cardName = validateCardName(cardEditRequest.getName(), false);
			String cardEmail = validateCardEmail(cardEditRequest.getEmail());
			validateCardExpiry(cardEditRequest.getExpiryMonth(), cardEditRequest.getExpiryYear(), false);
			String cardExpiryMonth	= cardEditRequest.getExpiryMonth();
			String cardExpiryYear = cardEditRequest.getExpiryYear();
			Boolean enabled = cardEditRequest.getEnabled();
			
			paymentCard = validatePaymentCard(cardEditRequest.getCode(), merchantProfile, false);
			
			if (StringUtils.isNotBlank(cardName))
				paymentCard.setCardName(cardName);
			if (StringUtils.isNotBlank(cardEmail))
				paymentCard.setCardEmail(cardEmail);
			if (StringUtils.isNotBlank(cardExpiryMonth))
				paymentCard.setCardExpiryMonth(cardExpiryMonth);
			if (StringUtils.isNotBlank(cardExpiryYear))
				paymentCard.setCardExpiryYear(cardExpiryYear);
			if (enabled != null)
				paymentCard.setEnabled(enabled);
			
			paymentCard.setModified(LocalDateTime.now());
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