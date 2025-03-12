package io.swipepay.omniapi.tx.card.payment;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.merchantprofilecard.MerchantProfileCard;
import io.swipepay.omniapi.common.entity.merchantprofilecurrency.MerchantProfileCurrency;
import io.swipepay.omniapi.common.entity.paymentcard.PaymentCard;
import io.swipepay.omniapi.common.entity.paymenttxcc.PaymentTxCc;
import io.swipepay.omniapi.common.entity.paymenttxcc.enums.PaymentTxCcEvent;
import io.swipepay.omniapi.common.entity.paymenttxccmetadata.PaymentTxCcMetadata;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.ValidationException;
import io.swipepay.omniapi.common.support.CardSupport;
import io.swipepay.omniapi.common.validator.RequestValidator;
import io.swipepay.omniapi.crypto.CryptoClient;
import io.swipepay.omniapi.crypto.CryptoException;
import io.swipepay.omniapi.tx.TxValidator;

@Component
public class CardPaymentValidator extends TxValidator {
	
	@Autowired
	private CardSupport cardSupport;
	
	@Autowired
	private CryptoClient cryptoClient;
	
	@Autowired
	private RequestValidator requestValidator;
	
	public PaymentTxCc validate(
			CardPaymentRequest cardPaymentRequest, 
			PaymentTxCc paymentTxCc, 
			String clientIpAddress) 
					throws ValidationException {
		
		MerchantProfile merchantProfile = paymentTxCc.getMerchantProfile();
		try {
			requestValidator.validateClientIpAddress(clientIpAddress);
			requestValidator.validateMerchantProfile(cardPaymentRequest.getMerchantProfile(), merchantProfile);
			String reference = validateReference(cardPaymentRequest.getReference(), merchantProfile);
			Long amount = validateAmount(cardPaymentRequest.getAmount());
			MerchantProfileCurrency merchantProfileCurrency = validateCurrency(cardPaymentRequest.getCurrency(), merchantProfile);
			String cardName = null;
			String cardEmail = null;
			String cardBin = null;
			String cardPan = null;
			String cardNumber = null;
			String cardNumberPlainText = null;
			String cardExpiryMonth = null;
			String cardExpiryYear = null;
			String cardCvv = null;
			String cardBrand = null;
			String paymentCardCode = null;
			MerchantProfileCard merchantProfileCard = null;
			PaymentCard paymentCard = null;
			
			if (StringUtils.isNotBlank(cardPaymentRequest.getCardToken())) {
				paymentCard = validateTokenisedPaymentCard(cardPaymentRequest.getCardToken(), merchantProfile);
				paymentCardCode = paymentCard.getCode();
				merchantProfileCard = paymentCard.getMerchantProfileCard();
				cardName = paymentCard.getCardName();
				cardEmail = paymentCard.getCardEmail();
				cardBin = paymentCard.getCardBin();
				cardPan = paymentCard.getCardPan();
				cardNumber = paymentCard.getCardNumber();
				cardNumberPlainText = cryptoClient.decrypt(merchantProfile.getDataKey(), cardNumber);
				cardExpiryMonth = paymentCard.getCardExpiryMonth();
				cardExpiryYear = paymentCard.getCardExpiryYear();
				cardBrand = merchantProfileCard.getCard().getBrand();
			}
			else {
				cardName = validateCardName(cardPaymentRequest.getName());
				cardEmail = validateCardEmail(cardPaymentRequest.getEmail());
				merchantProfileCard = validateCardPan(cardPaymentRequest.getPan(), merchantProfile);
				cardBin = cardSupport.bin(cardPaymentRequest.getPan());
				cardPan = cardSupport.mask(cardPaymentRequest.getPan(), "X");
				cardNumber = cryptoClient.encrypt(merchantProfile.getDataKey(), cardPaymentRequest.getPan());
				cardNumberPlainText = cardPaymentRequest.getPan();
				validateCardExpiry(cardPaymentRequest.getExpiryMonth(), cardPaymentRequest.getExpiryYear());
				cardExpiryMonth	= cardPaymentRequest.getExpiryMonth();
				cardExpiryYear = cardPaymentRequest.getExpiryYear();
				cardCvv = validateCardCvv(cardPaymentRequest.getCvv(), merchantProfileCard);
				cardBrand = merchantProfileCard.getCard().getBrand();
			}
			validateMetadata(cardPaymentRequest.getMetadata());
			validatePaymentGateways(merchantProfile, "cc");
			
			LinkedList<PaymentTxCcMetadata> paymentTxCcMetadata = new LinkedList<PaymentTxCcMetadata>();
			LinkedHashMap<String, String> metadata = cardPaymentRequest.getMetadata();
			for (Map.Entry<String, String> entry : metadata.entrySet()) {
				PaymentTxCcMetadata mdnvp = new PaymentTxCcMetadata();
				mdnvp.setMdKey(entry.getKey());
				mdnvp.setMdValue(entry.getValue());
				mdnvp.setPaymentTxCc(paymentTxCc);
				paymentTxCcMetadata.add(mdnvp);
			}
			
			paymentTxCc.setCode("txcc_" + UUID.randomUUID().toString().replaceAll("-", ""));
			paymentTxCc.setReference(reference);
			paymentTxCc.setAmount(amount);
			paymentTxCc.setCurrencyCode(cardPaymentRequest.getCurrency());
			paymentTxCc.setTxType("payment");
			paymentTxCc.setEnvironment(merchantProfile.getEnvironment());
			paymentTxCc.setEvent(PaymentTxCcEvent.New.name());
			paymentTxCc.setCardName(cardName);
			paymentTxCc.setCardEmail(cardEmail);
			paymentTxCc.setCardBin(cardBin);
			paymentTxCc.setCardPan(cardPan);
			paymentTxCc.setCardNumber(cardNumber);
			paymentTxCc.setCardNumberPlainText(cardNumberPlainText);
			paymentTxCc.setCardExpiryMonth(cardExpiryMonth);
			paymentTxCc.setCardExpiryYear(cardExpiryYear);
			paymentTxCc.setCardCvv(cardCvv);
			paymentTxCc.setCardBrand(cardBrand);
			paymentTxCc.setClientIp(clientIpAddress);
			paymentTxCc.setPaymentCardCode(paymentCardCode);
			paymentTxCc.setCaptured(false);
			paymentTxCc.setCapturedAmountTotal(new Long(0));
			paymentTxCc.setCapturedAmountRemaining(new Long(0));
			paymentTxCc.setRefunded(false);
			paymentTxCc.setRefundedAmountTotal(new Long(0));
			paymentTxCc.setRefundedAmountRemaining(new Long(0));
			paymentTxCc.setMerchantProfileCurrency(merchantProfileCurrency);
			paymentTxCc.setMerchantProfileCard(merchantProfileCard);
			paymentTxCc.setPaymentCard(paymentCard);
			paymentTxCc.setPaymentTxCcMetadata(paymentTxCcMetadata);
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
		return paymentTxCc;
	}
	
}