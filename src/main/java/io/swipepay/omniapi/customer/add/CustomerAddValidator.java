package io.swipepay.omniapi.customer.add;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.swipepay.omniapi.bankaccount.add.BankAccountAddValidator;
import io.swipepay.omniapi.bankaccount.add.payload.dto.BankAccountAddDto;
import io.swipepay.omniapi.card.add.CardAddValidator;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.merchantprofilecard.MerchantProfileCard;
import io.swipepay.omniapi.common.entity.paymentbankaccount.PaymentBankAccount;
import io.swipepay.omniapi.common.entity.paymentcard.PaymentCard;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.ValidationException;
import io.swipepay.omniapi.customer.CustomerValidator;
import io.swipepay.omniapi.customer.add.payload.CustomerAddRequest;
import io.swipepay.omniapi.customer.add.payload.dto.CustomerAddDto;

@Component
public class CustomerAddValidator extends CustomerValidator {

	@Autowired
	private CardAddValidator cardAddValidator;
	
	@Autowired
	private BankAccountAddValidator bankAccountAddValidator;
	
	public void validate(CustomerAddRequest customerAddRequest, CustomerAddData customerAddData) throws ValidationException {
//		CustomerAddDto customerAddDto = customerAddRequest.getCustomerAddDto();
//		MerchantProfile merchantProfile = customerAddData.getMerchantProfile();
//		try {
//			validateCustomer(customerAddDto);
//			
//			if (StringUtils.isNotBlank(customerAddDto.getCode())) {
//				validatePaymentCustomer(customerAddDto.getCode(), merchantProfile, true);
//			}
//			else {
//				validateFirstName(customerAddDto.getFirstName());
//				validateLastName(customerAddDto.getLastName());
//				validateAddress(customerAddDto.getAddress());
//				validateCity(customerAddDto.getCity());
//				validateState(customerAddDto.getState());
//				validatePostcode(customerAddDto.getPostcode());
//				validateCountry(customerAddDto.getCountryCode(), true);
//			}
//			
//			CardAddDto cardAddDto = customerAddDto.getCardAddDto();
//			BankAccountAddDto bankAccountAddDto = customerAddDto.getBankAccountAddDto();
//			
//			validatePaymentMethods(cardAddDto, bankAccountAddDto);
//			validateCard(cardAddDto, merchantProfile);
//			validateBankAccount(bankAccountAddDto, merchantProfile);
//		}
//		catch (ValidationException exception) {
//			throw exception;
//		}
//		catch (Exception exception) {
//			throw new ValidationException(
//					Status.RS_0005, 
//					"Validation failed due to an internal server error", 
//					exception);
//		}
	}
	
	private void validateCustomer(CustomerAddDto customerAddDto) throws ValidationException {
		if (customerAddDto == null) {
			throw new ValidationException(
					Status.RS_4000, 
					"Validation failed because the customer is missing", 
					null);
		}
	}
/*	
	public void validatePaymentMethods(CardAddDto cardAddDto, BankAccountAddDto bankAccountAddDto) throws ValidationException {
		if (cardAddDto == null && bankAccountAddDto == null) {
			throw new ValidationException(
					Status.RS_4011, 
					"Validation failed because no payment method(s) have been supplied", 
					null);
		}
	}
	
	private void validateCard(CardAddDto cardAddDto, MerchantProfile merchantProfile) {
		if (cardAddDto != null) {
			if (StringUtils.isNotBlank(cardAddDto.getCode())) {
				PaymentCard paymentCard = cardAddValidator.validatePaymentCard(cardAddDto.getCode(), merchantProfile, true);
				if (paymentCard.getPaymentCustomer() != null) {
					throw new ValidationException(
							Status.RS_2014, 
							"Validation failed because the payment card is associated with another customer", 
							null);
				}
			}
			else {
				cardAddValidator.validateCardName(cardAddDto.getCardName(), true);
				cardAddValidator.validateCardEmail(cardAddDto.getCardEmail());
				MerchantProfileCard merchantProfileCard = cardAddValidator.validateCardPan(cardAddDto.getCardPan(), true, merchantProfile);
				cardAddValidator.validateCardExpiry(cardAddDto.getCardExpiryMonth(), cardAddDto.getCardExpiryYear(), true);
				cardAddValidator.validateCardCvv(cardAddDto.getCardCvv(), merchantProfileCard);
			}
		}
	}
	*/
	private void validateBankAccount(BankAccountAddDto bankAccountAddDto, MerchantProfile merchantProfile) {
		if (bankAccountAddDto != null) {
			if (StringUtils.isNotBlank(bankAccountAddDto.getCode())) {
				PaymentBankAccount paymentBankAccount = bankAccountAddValidator.validatePaymentBankAccount(
						bankAccountAddDto.getCode(), 
						merchantProfile, 
						true);
				
				if (paymentBankAccount.getPaymentCustomer() != null) {
					throw new ValidationException(
							Status.RS_5014, 
							"Validation failed because the payment bank account is associated with another customer", 
							null);
				}
			}
			else {
				bankAccountAddValidator.validateBsb(bankAccountAddDto.getBsb(), true);
				bankAccountAddValidator.validateAccountNumber(bankAccountAddDto.getAccountNumber(), true);
				bankAccountAddValidator.validateAccountName(bankAccountAddDto.getAccountName(), true);
				bankAccountAddValidator.validateAccountEmail(bankAccountAddDto.getAccountEmail());
				bankAccountAddValidator.validateCountry(bankAccountAddDto.getCountryCode(), true);
			}
		}
	}
	
}