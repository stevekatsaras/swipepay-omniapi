package io.swipepay.omniapi.bankaccount;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.swipepay.omniapi.common.entity.country.Country;
import io.swipepay.omniapi.common.entity.country.CountryRepository;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.paymentbankaccount.PaymentBankAccount;
import io.swipepay.omniapi.common.entity.paymentbankaccount.PaymentBankAccountRepository;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.ValidationException;

@Component
public class BankAccountValidator {
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private PaymentBankAccountRepository paymentBankAccountRepository;
	
	public void validateBsb(String bsb, Boolean checkBlank) throws ValidationException {
		if (checkBlank) {
			if (StringUtils.isBlank(bsb)) {
				throw new ValidationException(
						Status.RS_5001, 
						"Validation failed because the BSB is not supplied", 
						null);
			}
		}
		
		if (StringUtils.isNotBlank(bsb)) {
			if (!bsb.matches("[0-9]{3}-?[0-9]{3}")) {
				throw new ValidationException(
						Status.RS_5002, 
						"Validation failed because the BSB is invalid", 
						null);
			}			
		}
	}
	
	public void validateAccountNumber(String accountNumber, Boolean checkBlank) throws ValidationException {
		if (checkBlank) {
			if (StringUtils.isBlank(accountNumber)) {
				throw new ValidationException(
						Status.RS_5003, 
						"Validation failed because the account number is not supplied", 
						null);
			}
		}
		
		if (StringUtils.isNotBlank(accountNumber)) {
			if (StringUtils.length(accountNumber) < 6) {
				throw new ValidationException(
						Status.RS_5004, 
						"Validation failed because the account number is less than the minimum required length", 
						null);
			}
			if (StringUtils.length(accountNumber) > 10) {
				throw new ValidationException(
						Status.RS_5005, 
						"Validation failed because the account number is greater than the maximum required length", 
						null);
			}
			if (!accountNumber.matches("[0-9]+")) {
				throw new ValidationException(
						Status.RS_5006, 
						"Validation failed because the account number does not contain digits only", 
						null);
			}
		}
	}
	
	public void validateAccountName(String accountName, Boolean checkBlank) throws ValidationException {
		if (checkBlank) {
			if (StringUtils.isBlank(accountName)) {
				throw new ValidationException(
						Status.RS_5007, 
						"Validation failed because the account name is not supplied", 
						null);
			}
		}
	}
	
	public void validateAccountEmail(String accountEmail) throws ValidationException {
 		if (StringUtils.isNotBlank(accountEmail)) {
			if (!EmailValidator.getInstance().isValid(accountEmail)) {
				throw new ValidationException(
						Status.RS_5008, 
						"Validation failed because the account email is invalid", 
						null);
			}
		}
	}
	
	@Transactional(readOnly = true)
	public void validateCountry(String countryCode, Boolean checkBlank) throws ValidationException {
		if (checkBlank) {
			if (StringUtils.isBlank(countryCode)) {
				throw new ValidationException(
						Status.RS_5009, 
						"Validation failed because the country is not supplied", 
						null);
			}
		}
		
		if (StringUtils.isNotBlank(countryCode)) {
			Country country = countryRepository.findByIso2(countryCode);
			if (country == null) {
				throw new ValidationException(
						Status.RS_5010, 
						"Validation failed because the country is invalid", 
						null);
			}
		}
	}
	
	@Transactional(readOnly = true)
	public PaymentBankAccount validatePaymentBankAccount(String paymentBankAccountCode, MerchantProfile merchantProfile, Boolean enforceEnabledCheck) throws ValidationException {
		if (StringUtils.isBlank(paymentBankAccountCode)) {
			throw new ValidationException(
					Status.RS_5011, 
					"Validation failed because the payment bank account code was not supplied", 
					null);	
		}
		
		PaymentBankAccount paymentBankAccount = null;
		
		if (enforceEnabledCheck) {
			paymentBankAccount = paymentBankAccountRepository.findByCodeAndMerchantProfileAndEnabled(
					paymentBankAccountCode, 
					merchantProfile, 
					true); // enabled
		}
		else {
			paymentBankAccount = paymentBankAccountRepository.findByCodeAndMerchantProfile(paymentBankAccountCode, merchantProfile);
		}
		
		if (paymentBankAccount == null) {
			throw new ValidationException(
					Status.RS_5012, 
					"Validation failed because the payment bank account is invalid", 
					null);
		}
		return paymentBankAccount;
	}
	
}