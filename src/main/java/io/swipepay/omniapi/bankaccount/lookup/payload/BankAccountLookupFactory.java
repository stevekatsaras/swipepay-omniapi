package io.swipepay.omniapi.bankaccount.lookup.payload;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swipepay.omniapi.bankaccount.lookup.BankAccountLookupData;
import io.swipepay.omniapi.bankaccount.lookup.payload.dto.BankAccountLookupDto;
import io.swipepay.omniapi.common.entity.paymentbankaccount.PaymentBankAccount;
import io.swipepay.omniapi.common.entity.paymentcustomer.PaymentCustomer;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.RequestException;

public class BankAccountLookupFactory {
	
	public static BankAccountLookupRequest build(String jsonPayload) throws RequestException {
		BankAccountLookupRequest bankAccountLookupRequest = null;
		try {
			bankAccountLookupRequest = new ObjectMapper().readValue(jsonPayload, BankAccountLookupRequest.class);
			if (bankAccountLookupRequest == null) {
				throw new RequestException(
						Status.RS_0004, 
						"Deserialization of the JSON payload was successful, yet the BankAccountLookupRequest object is NULL", 
						null);
			}
		}
		catch (Exception exception) {
			throw new RequestException(
					Status.RS_0004, 
					"Deserialization failed because the JSON payload does not conform to the BankAccountLookupRequest class", 
					exception);
		}
		return bankAccountLookupRequest;
	}
	
	public static BankAccountLookupResponse build(BankAccountLookupRequest bankAccountLookupRequest, BankAccountLookupData bankAccountLookupData) {
//		PaymentBankAccount paymentBankAccount = bankAccountLookupData.getPaymentBankAccount();
//		PaymentCustomer paymentCustomer = paymentBankAccount.getPaymentCustomer();
//		
//		BankAccountLookupDto bankAccountLookupDto = new ModelMapper().map(paymentBankAccount, BankAccountLookupDto.class);
//		
//		if (paymentCustomer != null) {
//			bankAccountLookupDto.setCustomerCode(paymentCustomer.getCode());
//			bankAccountLookupDto.setCustomerDefault(paymentBankAccount.getIsDefault());
//		}
//		
//		BankAccountLookupResponse bankAccountLookupResponse = new BankAccountLookupResponse(Status.RS_0000);
//		bankAccountLookupResponse.setMerchantProfileDto(bankAccountLookupRequest.getMerchantProfileDto());
//		bankAccountLookupResponse.setBankAccountLookupDto(bankAccountLookupDto);
//		return bankAccountLookupResponse;
		return null;
	}
}