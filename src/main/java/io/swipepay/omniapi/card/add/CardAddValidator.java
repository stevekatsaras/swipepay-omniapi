package io.swipepay.omniapi.card.add;

import java.time.LocalDateTime;
import java.util.UUID;

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
public class CardAddValidator extends CardValidator {
	
	@Autowired
	private CardSupport cardSupport;
	
	@Autowired
	private CryptoClient cryptoClient;
	
	@Autowired
	private RequestValidator requestValidator;
	
	public PaymentCard validate(
			CardAddRequest cardAddRequest, 
			PaymentCard paymentCard, 
			String clientIpAddress) 
					throws ValidationException {
		
		MerchantProfile merchantProfile = paymentCard.getMerchantProfile();
		try {
			requestValidator.validateClientIpAddress(clientIpAddress);
			requestValidator.validateMerchantProfile(cardAddRequest.getMerchantProfile(), merchantProfile);
			
			String cardName = validateCardName(cardAddRequest.getName(), true);
			String cardEmail = validateCardEmail(cardAddRequest.getEmail());
			MerchantProfileCard merchantProfileCard = validateCardPan(cardAddRequest.getPan(), true, merchantProfile);
			String cardBin = cardSupport.bin(cardAddRequest.getPan());
			String cardPan = cardSupport.mask(cardAddRequest.getPan(), "X");
			String cardNumber = cryptoClient.encrypt(merchantProfile.getDataKey(), cardAddRequest.getPan());
			validateCardExpiry(cardAddRequest.getExpiryMonth(), cardAddRequest.getExpiryYear(), true);
			String cardExpiryMonth	= cardAddRequest.getExpiryMonth();
			String cardExpiryYear = cardAddRequest.getExpiryYear();
			validateCardCvv(cardAddRequest.getCvv(), merchantProfileCard);
			
			paymentCard.setCode("card_" + UUID.randomUUID().toString().replaceAll("-", ""));
			paymentCard.setCardName(cardName);
			paymentCard.setCardEmail(cardEmail);
			paymentCard.setCardBin(cardBin);
			paymentCard.setCardPan(cardPan);
			paymentCard.setCardNumber(cardNumber);
			paymentCard.setCardExpiryMonth(cardExpiryMonth);
			paymentCard.setCardExpiryYear(cardExpiryYear);
			paymentCard.setEnabled(true);
			paymentCard.setModified(LocalDateTime.now());
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