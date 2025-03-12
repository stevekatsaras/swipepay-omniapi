package io.swipepay.omniapi.bankaccount.get;

import org.springframework.stereotype.Component;

import io.swipepay.omniapi.bankaccount.BankAccountValidator;
import io.swipepay.omniapi.bankaccount.get.payload.BankAccountGetRequest;
import io.swipepay.omniapi.bankaccount.get.payload.dto.BankAccountGetDto;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.ValidationException;

@Component
public class BankAccountGetValidator extends BankAccountValidator {
	
	public void validate(BankAccountGetRequest bankAccountGetRequest, BankAccountGetData bankAccountGetData) throws ValidationException {
//		BankAccountGetDto bankAccountGetDto = bankAccountGetRequest.getBankAccountGetDto();
//		MerchantProfile merchantProfile = bankAccountGetData.getMerchantProfile();
//		try {
//			validateBankAccount(bankAccountGetDto);
//			validatePaymentBankAccount(bankAccountGetDto.getCode(), merchantProfile, false);
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
	
	private void validateBankAccount(BankAccountGetDto bankAccountGetDto) throws ValidationException {
		if (bankAccountGetDto == null) {
			throw new ValidationException(
					Status.RS_5000, 
					"Validation failed because the bank account is missing", 
					null);
		}
	}
}