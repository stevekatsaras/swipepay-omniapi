package io.swipepay.omniapi.common.validator;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
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
import io.swipepay.omniapi.common.entity.paymenttxcc.PaymentTxCc;
import io.swipepay.omniapi.common.entity.paymenttxcc.PaymentTxCcRepository;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.ValidationException;
import io.swipepay.omniapi.common.support.CardSupport;

@Component
public class CreditCardValidator {
	
	@Autowired
	private CardSupport cardSupport;
	
	@Autowired
	private MerchantProfileCardRepository merchantProfileCardRepository;
	
	@Autowired
	private PaymentTxCcRepository paymentCcRepository;
	
	@Autowired
	private PaymentCardRepository paymentCcTokenRepository;
	
	/*
	public void validateCreditCards(LinkedList<CreditCardDto> creditCardDtos) throws ValidationException {
		if (creditCardDtos == null) {
			throw new ValidationException(
					Status.RS_0065, 
					"Validation failed because the credit-cards were missing", 
					null);
		}
		
		if (creditCardDtos.isEmpty()) {
			throw new ValidationException(
					Status.RS_0065, 
					"Validation failed because the credit-cards were missing", 
					null);
		}
	}
	*/

	

	

	

	
	
	
	
	/*
	@Transactional(readOnly = true)
	public PaymentTxCc validatePaymentCc(
			String code, 
			MerchantProfile merchantProfile, 
			Boolean enforceTypeCheck, 
			PaymentTypeCode paymentTypeCode, 
			Boolean enforceTxSuccessCheck) 
					throws ValidationException {
		
		// is the code supplied?
		
		if (StringUtils.isBlank(code)) {
			throw new ValidationException(
					Status.RS_3001,
					"Validation failed because the payment code is not supplied", 
					null);
		}
		
		PaymentTxCc paymentCc = null;
		
		// if true, will enforce the 'type' check on this query
		
		if (enforceTypeCheck) {
			paymentCc = paymentCcRepository
					.findByCodeAndPaymentTypeCodeAndMerchantProfile(
							code, 
							paymentTypeCode.name(), 
							merchantProfile);					
		}
		
		else {
			paymentCc = paymentCcRepository.findByCodeAndMerchantProfile(code, merchantProfile);
		}
		
		// can the payment transaction be found?
		
		if (paymentCc == null) {
			throw new ValidationException(
					Status.RS_3002, 
					"Validation failed because the payment transaction could not be found", 
					null);
		}
		
		// if true, we will force validation of a successful transaction...
		
		if (enforceTxSuccessCheck) {
			
			// was the payment transaction processed successfully?
			
			if (BooleanUtils.isFalse(paymentCc.getTxSuccess())) {
				throw new ValidationException(
						Status.RS_3003, 
						"Validation failed because the payment transaction was not processed successfully", 
						null);
			}			
		}
		
		return paymentCc;
	}*/
}