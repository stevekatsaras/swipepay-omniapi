package io.swipepay.omniapi.bankaccount.add;

import org.springframework.stereotype.Component;

import io.swipepay.omniapi.bankaccount.BankAccountValidator;
import io.swipepay.omniapi.bankaccount.add.payload.BankAccountAddRequest;
import io.swipepay.omniapi.bankaccount.add.payload.dto.BankAccountAddDto;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.ValidationException;

@Component
public class BankAccountAddValidator extends BankAccountValidator {
	
	public void validate(BankAccountAddRequest bankAccountAddRequest, BankAccountAddData bankAccountAddData) throws ValidationException {
		BankAccountAddDto bankAccountAddDto = bankAccountAddRequest.getBankAccountAddDto();
		try {
			validateBankAccount(bankAccountAddDto);
			validateBsb(bankAccountAddDto.getBsb(), true);
			validateAccountNumber(bankAccountAddDto.getAccountNumber(), true);
			validateAccountName(bankAccountAddDto.getAccountName(), true);
			validateAccountEmail(bankAccountAddDto.getAccountEmail());
			validateCountry(bankAccountAddDto.getCountryCode(), true);
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
	}
	
	private void validateBankAccount(BankAccountAddDto bankAccountAddDto) throws ValidationException {
		if (bankAccountAddDto == null) {
			throw new ValidationException(
					Status.RS_5000, 
					"Validation failed because the bank account is missing", 
					null);
		}
	}
}