package io.swipepay.omniapi.bankaccount.lookup;

import org.springframework.stereotype.Component;

import io.swipepay.omniapi.bankaccount.BankAccountValidator;
import io.swipepay.omniapi.bankaccount.lookup.payload.BankAccountLookupRequest;
import io.swipepay.omniapi.bankaccount.lookup.payload.dto.BankAccountLookupDto;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.ValidationException;

@Component
public class BankAccountLookupValidator extends BankAccountValidator {
	
	public void validate(BankAccountLookupRequest bankAccountLookupRequest, BankAccountLookupData bankAccountLookupData) throws ValidationException {
		BankAccountLookupDto bankAccountLookupDto = bankAccountLookupRequest.getBankAccountLookupDto();
		try {
			validateBankAccount(bankAccountLookupDto);
			validateBsb(bankAccountLookupDto.getBsb(), true);
			validateAccountNumber(bankAccountLookupDto.getAccountNumber(), true);
			validateCountry(bankAccountLookupDto.getCountryCode(), true);
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
	
	private void validateBankAccount(BankAccountLookupDto bankAccountLookupDto) throws ValidationException {
		if (bankAccountLookupDto == null) {
			throw new ValidationException(
					Status.RS_5000, 
					"Validation failed because the bank account is missing", 
					null);
		}
	}
}