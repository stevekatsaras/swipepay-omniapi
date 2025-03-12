package io.swipepay.omniapi.customer.edit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.swipepay.omniapi.bankaccount.edit.BankAccountEditValidator;
import io.swipepay.omniapi.bankaccount.edit.payload.dto.BankAccountEditDto;
import io.swipepay.omniapi.card.edit.CardEditValidator;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.paymentbankaccount.PaymentBankAccount;
import io.swipepay.omniapi.common.entity.paymentcard.PaymentCard;
import io.swipepay.omniapi.common.entity.paymentcustomer.PaymentCustomer;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.ValidationException;
import io.swipepay.omniapi.customer.CustomerValidator;
import io.swipepay.omniapi.customer.edit.payload.CustomerEditRequest;
import io.swipepay.omniapi.customer.edit.payload.dto.CustomerEditDto;

@Component
public class CustomerEditValidator extends CustomerValidator {
	
	@Autowired
	private CardEditValidator cardEditValidator;
	
	@Autowired
	private BankAccountEditValidator bankAccountEditValidator;
	
	public void validate(CustomerEditRequest customerEditRequest, CustomerEditData customerEditData) throws ValidationException {
//		CustomerEditDto customerEditDto = customerEditRequest.getCustomerEditDto();
//		MerchantProfile merchantProfile = customerEditData.getMerchantProfile();
//		try {
//			validateCustomer(customerEditDto);
//			validateCountry(customerEditDto.getCountryCode(), false);
//			validatePaymentCustomer(customerEditDto.getCode(), merchantProfile, false);
//			
//			validateCard(customerEditDto.getCardEditDto(), merchantProfile, customerEditDto.getCode());
//			validateBankAccount(customerEditDto.getBankAccountEditDto(), merchantProfile, customerEditDto.getCode());
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
	
	private void validateCustomer(CustomerEditDto customerEditDto) throws ValidationException {
		if (customerEditDto == null) {
			throw new ValidationException(
					Status.RS_4000, 
					"Validation failed because the customer is missing", 
					null);
		}
	}
	
//	private void validateCard(CardEditDto cardEditDto, MerchantProfile merchantProfile, String customerCode) {
//		if (cardEditDto != null) {
//			cardEditValidator.validateCardName(cardEditDto.getCardName(), false);
//			cardEditValidator.validateCardEmail(cardEditDto.getCardEmail());
//			cardEditValidator.validateCardExpiry(cardEditDto.getCardExpiryMonth(), cardEditDto.getCardExpiryYear(), false);
//			
//			PaymentCard paymentCard = cardEditValidator.validatePaymentCard(cardEditDto.getCode(), merchantProfile, false);
//			PaymentCustomer paymentCustomer = paymentCard.getPaymentCustomer();
//			
//			if (paymentCustomer == null) {
//				throw new ValidationException(
//						Status.RS_2015, 
//						"Validation failed because the payment card is not associated with a customer", 
//						null);
//			}
//			else if (!StringUtils.equals(paymentCustomer.getCode(), customerCode)) {
//				throw new ValidationException(
//						Status.RS_2014, 
//						"Validation failed because the payment card is associated with another customer", 
//						null);
//			}
//		}
//	}
	
	private void validateBankAccount(BankAccountEditDto bankAccountEditDto, MerchantProfile merchantProfile, String customerCode) {
		if (bankAccountEditDto != null) {
			bankAccountEditValidator.validateBsb(bankAccountEditDto.getBsb(), false);
			bankAccountEditValidator.validateAccountNumber(bankAccountEditDto.getAccountNumber(), false);
			bankAccountEditValidator.validateAccountName(bankAccountEditDto.getAccountName(), false);
			bankAccountEditValidator.validateAccountEmail(bankAccountEditDto.getAccountEmail());
			bankAccountEditValidator.validateCountry(bankAccountEditDto.getCountryCode(), false);
			
			PaymentBankAccount paymentBankAccount = bankAccountEditValidator.validatePaymentBankAccount(
					bankAccountEditDto.getCode(), 
					merchantProfile, 
					false);
			
			PaymentCustomer paymentCustomer = paymentBankAccount.getPaymentCustomer();
			
			if (paymentCustomer == null) {
				throw new ValidationException(
						Status.RS_5015, 
						"Validation failed because the payment bank account is not associated with a customer", 
						null);
			}
			else if (!StringUtils.equals(paymentCustomer.getCode(), customerCode)) {
				throw new ValidationException(
						Status.RS_5014, 
						"Validation failed because the payment bank account is associated with another customer", 
						null);
			}
		}
	}
	
}