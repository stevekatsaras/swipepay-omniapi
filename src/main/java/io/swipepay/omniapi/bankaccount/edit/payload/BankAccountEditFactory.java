package io.swipepay.omniapi.bankaccount.edit.payload;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swipepay.omniapi.bankaccount.edit.BankAccountEditData;
import io.swipepay.omniapi.bankaccount.edit.payload.dto.BankAccountEditDto;
import io.swipepay.omniapi.common.entity.paymentbankaccount.PaymentBankAccount;
import io.swipepay.omniapi.common.entity.paymentcustomer.PaymentCustomer;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.RequestException;

public class BankAccountEditFactory {
	
	public static BankAccountEditRequest build(String jsonPayload) throws RequestException {
		BankAccountEditRequest bankAccountEditRequest = null;
		try {
			bankAccountEditRequest = new ObjectMapper().readValue(jsonPayload, BankAccountEditRequest.class);
			if (bankAccountEditRequest == null) {
				throw new RequestException(
						Status.RS_0004, 
						"Deserialization of the JSON payload was successful, yet the BankAccountEditRequest object is NULL", 
						null);
			}
		}
		catch (Exception exception) {
			throw new RequestException(
					Status.RS_0004, 
					"Deserialization failed because the JSON payload does not conform to the BankAccountEditRequest class", 
					exception);
		}
		return bankAccountEditRequest;
	}
	
	public static BankAccountEditResponse build(BankAccountEditRequest bankAccountEditRequest, BankAccountEditData bankAccountEditData) {
//		PaymentBankAccount paymentBankAccount = bankAccountEditData.getPaymentBankAccount();
//		PaymentCustomer paymentCustomer = paymentBankAccount.getPaymentCustomer();
//		
//		BankAccountEditDto bankAccountEditDto = new ModelMapper().map(paymentBankAccount, BankAccountEditDto.class);
//		
//		if (paymentCustomer != null) {
//			bankAccountEditDto.setCustomerCode(paymentCustomer.getCode());
//			bankAccountEditDto.setCustomerDefault(paymentBankAccount.getIsDefault());
//		}
//		
//		BankAccountEditResponse bankAccountEditResponse = new BankAccountEditResponse(Status.RS_0000);
//		bankAccountEditResponse.setMerchantProfileDto(bankAccountEditRequest.getMerchantProfileDto());
//		bankAccountEditResponse.setBankAccountEditDto(bankAccountEditDto);
//		return bankAccountEditResponse;
		return null;
	}
}