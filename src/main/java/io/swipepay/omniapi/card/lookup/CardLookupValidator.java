package io.swipepay.omniapi.card.lookup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.swipepay.omniapi.card.CardValidator;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.merchantprofilecard.MerchantProfileCard;
import io.swipepay.omniapi.common.entity.paymentcard.PaymentCard;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.ValidationException;
import io.swipepay.omniapi.common.support.CardSupport;
import io.swipepay.omniapi.common.validator.RequestValidator;
import io.swipepay.omniapi.crypto.CryptoClient;
import io.swipepay.omniapi.crypto.CryptoException;

@Component
public class CardLookupValidator extends CardValidator {
	
	@Autowired
	private CardSupport cardSupport;
	
	@Autowired
	private CryptoClient cryptoClient;
	
	@Autowired
	private RequestValidator requestValidator;
	
	public PaymentCard validate(
			CardLookupRequest cardLookupRequest, 
			PaymentCard paymentCard, 
			String clientIpAddress) 
					throws ValidationException {
		
		MerchantProfile merchantProfile = paymentCard.getMerchantProfile();
		try {
			requestValidator.validateClientIpAddress(clientIpAddress);
			requestValidator.validateMerchantProfile(cardLookupRequest.getMerchantProfile(), merchantProfile);
			
			MerchantProfileCard merchantProfileCard = validateCardPan(cardLookupRequest.getPan(), true, merchantProfile);
			String cardBin = cardSupport.bin(cardLookupRequest.getPan());
			String cardPan = cardSupport.mask(cardLookupRequest.getPan(), "X");
			String cardNumber = cryptoClient.encrypt(merchantProfile.getDataKey(), cardLookupRequest.getPan());
			validateCardExpiry(cardLookupRequest.getExpiryMonth(), cardLookupRequest.getExpiryYear(), true);
			String cardExpiryMonth	= cardLookupRequest.getExpiryMonth();
			String cardExpiryYear = cardLookupRequest.getExpiryYear();
			
			paymentCard.setCardBin(cardBin);
			paymentCard.setCardPan(cardPan);
			paymentCard.setCardNumber(cardNumber);
			paymentCard.setCardExpiryMonth(cardExpiryMonth);
			paymentCard.setCardExpiryYear(cardExpiryYear);
			paymentCard.setMerchantProfileCard(merchantProfileCard);
		}
		catch (CryptoException exception) {
			throw new ValidationException(
					Status.RS_0023, 
					"Validation failed because of an internal crypto error", 
					exception);
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