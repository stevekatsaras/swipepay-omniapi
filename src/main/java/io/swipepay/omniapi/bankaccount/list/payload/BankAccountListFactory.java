package io.swipepay.omniapi.bankaccount.list.payload;

import java.util.LinkedList;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swipepay.omniapi.bankaccount.list.BankAccountListData;
import io.swipepay.omniapi.bankaccount.list.payload.dto.BankAccountListDto;
import io.swipepay.omniapi.common.entity.paymentbankaccount.PaymentBankAccount;
import io.swipepay.omniapi.common.entity.paymentcustomer.PaymentCustomer;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.RequestException;

public class BankAccountListFactory {
	
	public static BankAccountListRequest build(String jsonPayload) throws RequestException {
		BankAccountListRequest bankAccountListRequest = null;
		try {
			bankAccountListRequest = new ObjectMapper().readValue(jsonPayload, BankAccountListRequest.class);
			if (bankAccountListRequest == null) {
				throw new RequestException(
						Status.RS_0004, 
						"Deserialization of the JSON payload was successful, yet the BankAccountListRequest object is NULL", 
						null);
			}
		}
		catch (Exception exception) {
			throw new RequestException(
					Status.RS_0004, 
					"Deserialization failed because the JSON payload does not conform to the BankAccountListRequest class", 
					exception);
		}
		return bankAccountListRequest;
	}
	
	public static BankAccountListResponse build(BankAccountListRequest bankAccountListRequest, BankAccountListData bankAccountListData) {
//		LinkedList<BankAccountListDto> bankAccountListDtos = new LinkedList<BankAccountListDto>();
//		
//		LinkedList<PaymentBankAccount> paymentBankAccounts = bankAccountListData.getPaymentBankAccounts();
//		for (PaymentBankAccount paymentBankAccount : paymentBankAccounts) {
//			BankAccountListDto bankAccountListDto = new ModelMapper().map(paymentBankAccount, BankAccountListDto.class);
//			
//			PaymentCustomer paymentCustomer = paymentBankAccount.getPaymentCustomer();
//			if (paymentCustomer != null) {
//				bankAccountListDto.setCustomerCode(paymentCustomer.getCode());
//				bankAccountListDto.setCustomerDefault(paymentBankAccount.getIsDefault());
//			}
//			bankAccountListDtos.add(bankAccountListDto);
//		}
//		
//		BankAccountListResponse bankAccountListResponse = new BankAccountListResponse(Status.RS_0000);
//		bankAccountListResponse.setMerchantProfileDto(bankAccountListRequest.getMerchantProfileDto());
//		bankAccountListResponse.setBankAccountListDtos(bankAccountListDtos);
//		return bankAccountListResponse;
		return null;
	}
}