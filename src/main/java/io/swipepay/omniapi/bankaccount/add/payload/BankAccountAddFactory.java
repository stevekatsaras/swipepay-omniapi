package io.swipepay.omniapi.bankaccount.add.payload;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swipepay.omniapi.bankaccount.add.BankAccountAddData;
import io.swipepay.omniapi.bankaccount.add.payload.dto.BankAccountAddDto;
import io.swipepay.omniapi.common.entity.paymentbankaccount.PaymentBankAccount;
import io.swipepay.omniapi.common.entity.paymentcustomer.PaymentCustomer;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.RequestException;

public class BankAccountAddFactory {
	
	public static BankAccountAddRequest build(String jsonPayload) throws RequestException {
		BankAccountAddRequest bankAccountAddRequest = null;
		try {
			bankAccountAddRequest = new ObjectMapper().readValue(jsonPayload, BankAccountAddRequest.class);
			if (bankAccountAddRequest == null) {
				throw new RequestException(
						Status.RS_0004, 
						"Deserialization of the JSON payload was successful, yet the BankAccountAddRequest object is NULL", 
						null);
			}
		}
		catch (Exception exception) {
			throw new RequestException(
					Status.RS_0004, 
					"Deserialization failed because the JSON payload does not conform to the BankAccountAddRequest class", 
					exception);
		}
		return bankAccountAddRequest;
	}
	
	public static BankAccountAddResponse build(BankAccountAddRequest bankAccountAddRequest, BankAccountAddData bankAccountAddData) {
		/*PaymentBankAccount paymentBankAccount = bankAccountAddData.getPaymentBankAccount();
		PaymentCustomer paymentCustomer = paymentBankAccount.getPaymentCustomer();
		
		BankAccountAddDto bankAccountAddDto = new ModelMapper().map(paymentBankAccount, BankAccountAddDto.class);
		
		if (paymentCustomer != null) {
			bankAccountAddDto.setCustomerCode(paymentCustomer.getCode());
			bankAccountAddDto.setCustomerDefault(paymentBankAccount.getIsDefault());
		}
		
		BankAccountAddResponse bankAccountAddResponse = new BankAccountAddResponse(Status.RS_0000);
		bankAccountAddResponse.setMerchantProfileDto(bankAccountAddRequest.getMerchantProfileDto());
		bankAccountAddResponse.setBankAccountAddDto(bankAccountAddDto);
		return bankAccountAddResponse;*/
		return null;
	}
}