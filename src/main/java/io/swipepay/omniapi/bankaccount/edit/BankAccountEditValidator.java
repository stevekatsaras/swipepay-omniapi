package io.swipepay.omniapi.bankaccount.edit;

import org.springframework.stereotype.Component;

import io.swipepay.omniapi.bankaccount.BankAccountValidator;
import io.swipepay.omniapi.bankaccount.edit.payload.BankAccountEditRequest;
import io.swipepay.omniapi.bankaccount.edit.payload.dto.BankAccountEditDto;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.ValidationException;

@Component
public class BankAccountEditValidator extends BankAccountValidator {
	
	public void validate(BankAccountEditRequest bankAccountEditRequest, BankAccountEditData bankAccountEditData) throws ValidationException {
//		BankAccountEditDto bankAccountEditDto = bankAccountEditRequest.getBankAccountEditDto();
//		MerchantProfile merchantProfile = bankAccountEditData.getMerchantProfile();
//		try {
//			validateBankAccount(bankAccountEditDto);
//			validateBsb(bankAccountEditDto.getBsb(), false);
//			validateAccountNumber(bankAccountEditDto.getAccountNumber(), false);
//			validateAccountName(bankAccountEditDto.getAccountName(), false);
//			validateAccountEmail(bankAccountEditDto.getAccountEmail());
//			validateCountry(bankAccountEditDto.getCountryCode(), false);
//			validatePaymentBankAccount(bankAccountEditDto.getCode(), merchantProfile, false);
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
	
	private void validateBankAccount(BankAccountEditDto bankAccountDto) throws ValidationException {
		if (bankAccountDto == null) {
			throw new ValidationException(
					Status.RS_5000, 
					"Validation failed because the bank account is missing", 
					null);
		}
	}
}