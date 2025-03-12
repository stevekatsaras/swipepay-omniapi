package io.swipepay.omniapi.bankaccount.get.payload;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swipepay.omniapi.bankaccount.get.BankAccountGetData;
import io.swipepay.omniapi.bankaccount.get.payload.dto.BankAccountGetDto;
import io.swipepay.omniapi.common.entity.paymentbankaccount.PaymentBankAccount;
import io.swipepay.omniapi.common.entity.paymentcustomer.PaymentCustomer;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.RequestException;

public class BankAccountGetFactory {
	
	public static BankAccountGetRequest build(String jsonPayload) throws RequestException {
		BankAccountGetRequest bankAccountGetRequest = null;
		try {
			bankAccountGetRequest = new ObjectMapper().readValue(jsonPayload, BankAccountGetRequest.class);
			if (bankAccountGetRequest == null) {
				throw new RequestException(
						Status.RS_0004, 
						"Deserialization of the JSON payload was successful, yet the BankAccountGetRequest object is NULL", 
						null);
			}
		}
		catch (Exception exception) {
			throw new RequestException(
					Status.RS_0004, 
					"Deserialization failed because the JSON payload does not conform to the BankAccountGetRequest class", 
					exception);
		}
		return bankAccountGetRequest;
	}
	
	public static BankAccountGetResponse build(BankAccountGetRequest bankAccountGetRequest, BankAccountGetData bankAccountGetData) {
//		PaymentBankAccount paymentBankAccount = bankAccountGetData.getPaymentBankAccount();
//		PaymentCustomer paymentCustomer = paymentBankAccount.getPaymentCustomer();
//		
//		BankAccountGetDto bankAccountGetDto = new ModelMapper().map(paymentBankAccount, BankAccountGetDto.class);
//		
//		if (paymentCustomer != null) {
//			bankAccountGetDto.setCustomerCode(paymentCustomer.getCode());
//			bankAccountGetDto.setCustomerDefault(paymentBankAccount.getIsDefault());
//		}
//		
//		BankAccountGetResponse bankAccountGetResponse = new BankAccountGetResponse(Status.RS_0000);
//		bankAccountGetResponse.setMerchantProfileDto(bankAccountGetRequest.getMerchantProfileDto());
//		bankAccountGetResponse.setBankAccountGetDto(bankAccountGetDto);
//		return bankAccountGetResponse;
		return null;
	}
}