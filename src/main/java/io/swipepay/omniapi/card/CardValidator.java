package io.swipepay.omniapi.card;

import java.text.ParseException;
import java.util.LinkedList;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.merchantprofilecard.MerchantProfileCard;
import io.swipepay.omniapi.common.entity.merchantprofilecard.MerchantProfileCardRepository;
import io.swipepay.omniapi.common.entity.paymentcard.PaymentCard;
import io.swipepay.omniapi.common.entity.paymentcard.PaymentCardRepository;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.ValidationException;
import io.swipepay.omniapi.common.support.CardSupport;

@Component
public class CardValidator {

	@Autowired
	private CardSupport cardSupport;
	
	@Autowired
	private MerchantProfileCardRepository merchantProfileCardRepository;
	
	@Autowired
	private PaymentCardRepository paymentCardRepository;
	
	public String validateCardName(String cardName, Boolean checkBlank) throws ValidationException {
		if (checkBlank) {
			if (StringUtils.isBlank(cardName)) {
				throw new ValidationException(
						Status.RS_2001, 
						"Validation failed because the card name is not supplied", 
						null);
			}			
		}
		return cardName;
	}
	
	public String validateCardEmail(String cardEmail) throws ValidationException {
		if (StringUtils.isNotBlank(cardEmail)) {
			if (!EmailValidator.getInstance().isValid(cardEmail)) {
				throw new ValidationException(
						Status.RS_2002, 
						"Validation failed because the card email is invalid", 
						null);
			}
		}
		return cardEmail;
	}
	
	@Transactional(readOnly = true)
	public MerchantProfileCard validateCardPan(String cardPan, Boolean checkBlank, MerchantProfile merchantProfile) throws ValidationException {
		if (checkBlank) {
			if (StringUtils.isBlank(cardPan)) {
				throw new ValidationException(
						Status.RS_2003, 
						"Validation failed because the card PAN is not supplied", 
						null);
			}			
		}
		
		MerchantProfileCard merchantProfileCard = null;
		
		if (StringUtils.isNotBlank(cardPan)) {
			if (StringUtils.length(cardPan) > 20) {
				throw new ValidationException(
						Status.RS_2004, 
						"Validation failed because the card PAN has exceeded the maximum number of characters", 
						null);
			}
			
			if (!cardPan.matches("[0-9]+")) {
				throw new ValidationException(
						Status.RS_2005, 
						"Validation failed because the card PAN does not contain digits", 
						null);
			}
			
			LinkedList<MerchantProfileCard> merchantProfileCards = merchantProfileCardRepository.findByMerchantProfileAndEnabled(
					merchantProfile, 
					true); // enabled
			
			merchantProfileCard = cardSupport.find(cardPan, merchantProfileCards);
			if (merchantProfileCard == null) {
				throw new ValidationException(
						Status.RS_2005, 
						"Validation failed because the card PAN did not pass a luhn check", 
						null);
			}
		}
		return merchantProfileCard;
	}
	
	public void validateCardExpiry(String expiryMonth, String expiryYear, Boolean checkBlank) throws ValidationException {
		if (checkBlank) {
			if (StringUtils.isBlank(expiryMonth)) {
				throw new ValidationException(
						Status.RS_2006, 
						"Validation failed because the card expiry month is not supplied", 
						null);
			}

			if (StringUtils.isBlank(expiryYear)) {
				throw new ValidationException(
						Status.RS_2007, 
						"Validation failed because the card expiry year is not supplied", 
						null);
			}
		}
		
		if (StringUtils.isNotBlank(expiryMonth) && StringUtils.isNotBlank(expiryYear)) {
			try {
				if (cardSupport.expired(expiryMonth, expiryYear)) {
					throw new ValidationException(
							Status.RS_2008, 
							"Validation failed because the card has expired", 
							null);
				}
			}
			catch (ParseException exception) {
				throw new ValidationException(
						Status.RS_2009, 
						"Validation failed because the card expiry format is invalid", 
						exception);
			}
		}
	}
	
	public String validateCardCvv(String cardCvv, MerchantProfileCard merchantProfileCard) throws ValidationException {
		if (StringUtils.isNotBlank(cardCvv)) {
			if (!cardCvv.matches("[0-9]+")) {
				throw new ValidationException(
						Status.RS_2010, 
						"Validation failed because the card cvv does not contain digits", 
						null);
			}
			
			if (StringUtils.equals(merchantProfileCard.getCard().getBrand(), "American Express") && 
					StringUtils.length(cardCvv) != 4) {
				throw new ValidationException(
						Status.RS_2010, 
						"Validation failed because the card cvv does not contain a 4 digit cvv compatible with the American Express brand", 
						null);
			}
			else if (StringUtils.length(cardCvv) != 3) {
				throw new ValidationException(
						Status.RS_2010, 
						"Validation failed because the card cvv does not contain a 3 digit cvv compatible with the " + merchantProfileCard.getCard().getBrand() + "brand", 
						null);
			}
		}
		return cardCvv;
	}
	
	@Transactional(readOnly = true)
	public PaymentCard validatePaymentCard(String code, MerchantProfile merchantProfile, Boolean enforceEnabledCheck) throws ValidationException {
		if (StringUtils.isBlank(code)) {
			throw new ValidationException(
					Status.RS_2011, 
					"Validation failed because the payment card code was not supplied", 
					null);
		}
		
		PaymentCard paymentCard = null;
		
		if (enforceEnabledCheck) {
			paymentCard = paymentCardRepository.findByCodeAndMerchantProfileAndEnabled(
					code, 
					merchantProfile, 
					true); // enabled
		}
		else {
			paymentCard = paymentCardRepository.findByCodeAndMerchantProfile(code, merchantProfile);
		}
		
		if (paymentCard == null) {
			throw new ValidationException(
					Status.RS_2012, 
					"Validation failed because the payment card is invalid", 
					null);
		}
		return paymentCard;
	}
}