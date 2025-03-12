package io.swipepay.omniapi.tx;

import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import io.swipepay.omniapi.card.CardValidator;
import io.swipepay.omniapi.common.config.properties.OmniApiPaymentProperties;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.merchantprofile.enums.MerchantProfileEnvironment;
import io.swipepay.omniapi.common.entity.merchantprofilecard.MerchantProfileCard;
import io.swipepay.omniapi.common.entity.merchantprofilecard.MerchantProfileCardRepository;
import io.swipepay.omniapi.common.entity.merchantprofilecurrency.MerchantProfileCurrency;
import io.swipepay.omniapi.common.entity.merchantprofilecurrency.MerchantProfileCurrencyRepository;
import io.swipepay.omniapi.common.entity.merchantprofilepaymentgateway.MerchantProfilePaymentGatewayRepository;
import io.swipepay.omniapi.common.entity.paymentcard.PaymentCard;
import io.swipepay.omniapi.common.entity.paymenttxcc.PaymentTxCc;
import io.swipepay.omniapi.common.entity.paymenttxcc.PaymentTxCcRepository;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.ValidationException;
import io.swipepay.omniapi.common.support.CardSupport;

public class TxValidator {
	
	@Autowired
	private CardSupport cardSupport;
	
	@Autowired
	private CardValidator cardValidator;
	
	@Autowired
	private MerchantProfileCardRepository merchantProfileCardRepository;
	
	@Autowired
	private MerchantProfileCurrencyRepository merchantProfileCurrencyRepository;
	
	@Autowired
	private MerchantProfilePaymentGatewayRepository merchantProfilePaymentGatewayRepository;
	
	@Autowired
	private OmniApiPaymentProperties omniApiPaymentProperties;
	
	@Autowired
	private PaymentTxCcRepository paymentTxCcRepository;
	
	@Transactional(readOnly = true)
	public String validateReference(String reference, MerchantProfile merchantProfile) throws ValidationException {
		if (StringUtils.isBlank(reference)) {
			throw new ValidationException(
					Status.RS_3001, 
					"Validation failed because the reference is not supplied", 
					null);
		}
		
		Long count = paymentTxCcRepository.countByReferenceAndMerchantProfile(reference, merchantProfile);
		if (count > 0) {
			throw new ValidationException(
					Status.RS_3002, 
					"Validation failed because the reference has already been used", 
					null);
		}
		return reference;
	}
	
	public Long validateAmount(String amount) throws ValidationException {
		if (StringUtils.isBlank(amount)) {
			throw new ValidationException(
					Status.RS_3003, 
					"Validation failed because the amount is not supplied", 
					null);
		}
		if (!amount.matches("[0-9]+")) {
			throw new ValidationException(
					Status.RS_3004, 
					"Validation failed because the amount does not contain digits only", 
					null);
		}
		return new Long(amount);
	}
	
	@Transactional(readOnly = true)
	public MerchantProfileCurrency validateCurrency(String currencyCode, MerchantProfile merchantProfile) throws ValidationException {
		if (StringUtils.isBlank(currencyCode)) {
			throw new ValidationException(
					Status.RS_3005, 
					"Validation failed because the currency is not supplied", 
					null);
		}
		
		MerchantProfileCurrency merchantProfileCurrency = merchantProfileCurrencyRepository.findByMerchantProfileAndEnabledIsTrueAndCurrencyIso3(
				merchantProfile, 
				currencyCode);
		
		if (merchantProfileCurrency == null) {
			throw new ValidationException(
					Status.RS_3006, 
					"Validation failed because the currency is invalid for this merchant profile", 
					null);
		}
		return merchantProfileCurrency;
	}
	
	public String validateCardName(String cardName) throws ValidationException {
		if (StringUtils.isBlank(cardName)) {
			throw new ValidationException(
					Status.RS_2001, 
					"Validation failed because the card name is not supplied", 
					null);
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
	public MerchantProfileCard validateCardPan(String cardPan, MerchantProfile merchantProfile) throws ValidationException {
		if (StringUtils.isBlank(cardPan)) {
			throw new ValidationException(
					Status.RS_2003, 
					"Validation failed because the card PAN is not supplied", 
					null);			
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
	
	public void validateCardExpiry(String expiryMonth, String expiryYear) throws ValidationException {
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
	
	public void validateMetadata(LinkedHashMap<String, String> metadata) throws ValidationException {
		for (Map.Entry<String, String> entry : metadata.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			
			// if a metadata key/value exists, make sure they are not blank
			
			if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
				throw new ValidationException(
						Status.RS_3008, 
						"Validation failed because the metadata key/value pair is not supplied", 
						null);
			}
		}
	}
	
	@Transactional(readOnly = true)
	public void validatePaymentGateways(MerchantProfile merchantProfile, String paymentChoice) throws ValidationException {
		if (BooleanUtils.isFalse(omniApiPaymentProperties.getGatewayForceSimulation())) {
			if (StringUtils.equals(merchantProfile.getEnvironment(), MerchantProfileEnvironment.Live.name())) {
				Long count = new Long(0);
				switch (paymentChoice) {
				case "cc":
					count = merchantProfilePaymentGatewayRepository.countByMerchantProfileAndEnabledIsTrueAndPaymentGatewayDoCc(
							merchantProfile, 
							true); // does cc
					break;
				case "de":
					//TODO
					break;
				default:
					break;
				}
				if (count == 0) {
					throw new ValidationException(
							Status.RS_3011, 
							"Validation failed because no payment gateways are configured/enabled for this merchant profile", 
							null);
				}
			}
		}
	}
	
	@Transactional(readOnly = true)
	public PaymentTxCc validatePaymentTxCc(String code, MerchantProfile merchantProfile) throws ValidationException {
		if (StringUtils.isBlank(code)) {
			throw new ValidationException(
					Status.RS_3007,
					"Validation failed because the tx code is not supplied", 
					null);
		}
		
		PaymentTxCc paymentTxCc = paymentTxCcRepository.findByCodeAndMerchantProfile(code, merchantProfile);
		if (paymentTxCc == null) {
			throw new ValidationException(
					Status.RS_3014, 
					"Validation failed because the transaction could not be found", 
					null);
		}
		return paymentTxCc;
	}
	
	public PaymentCard validateTokenisedPaymentCard(String cardCode, MerchantProfile merchantProfile) throws ValidationException {
		PaymentCard paymentCard = cardValidator.validatePaymentCard(cardCode, merchantProfile, true);
		cardValidator.validateCardExpiry(paymentCard.getCardExpiryMonth(), paymentCard.getCardExpiryYear(), false);
		return paymentCard;
	}
	
}