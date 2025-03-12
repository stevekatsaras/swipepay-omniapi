package io.swipepay.omniapi.customer.edit.payload;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swipepay.omniapi.bankaccount.edit.payload.dto.BankAccountEditDto;
import io.swipepay.omniapi.common.entity.paymentbankaccount.PaymentBankAccount;
import io.swipepay.omniapi.common.entity.paymentcard.PaymentCard;
import io.swipepay.omniapi.common.entity.paymentcustomer.PaymentCustomer;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.RequestException;
import io.swipepay.omniapi.customer.edit.CustomerEditData;
import io.swipepay.omniapi.customer.edit.payload.dto.CustomerEditDto;

public class CustomerEditFactory {
	
	public static CustomerEditRequest build(String jsonPayload) throws RequestException {
		CustomerEditRequest customerEditRequest = null;
		try {
			customerEditRequest = new ObjectMapper().readValue(jsonPayload, CustomerEditRequest.class);
			if (customerEditRequest == null) {
				throw new RequestException(
						Status.RS_0004, 
						"Deserialization of the JSON payload was successful, yet the CustomerEditRequest object is NULL", 
						null);
			}
		}
		catch (Exception exception) {
			throw new RequestException(
					Status.RS_0004, 
					"Deserialization failed because the JSON payload does not conform to the CustomerEditRequest class", 
					exception);
		}
		return customerEditRequest;
	}
	
	public static CustomerEditResponse build(CustomerEditRequest customerEditRequest, CustomerEditData customerEditData) {
//		PaymentCustomer paymentCustomer = customerEditData.getPaymentCustomer();
//		
//		CardEditDto cardEditDto = buildCard(customerEditData.getPaymentCard());
//		BankAccountEditDto bankAccountEditDto = buildBankAccount(customerEditData.getPaymentBankAccount());
//		
//		CustomerEditDto customerEditDto = new ModelMapper().map(paymentCustomer, CustomerEditDto.class);
//		customerEditDto.setCardEditDto(cardEditDto);
//		customerEditDto.setBankAccountEditDto(bankAccountEditDto);
//		
//		CustomerEditResponse customerEditResponse = new CustomerEditResponse(Status.RS_0000);
//		customerEditResponse.setMerchantProfileDto(customerEditRequest.getMerchantProfileDto());
//		customerEditResponse.setCustomerEditDto(customerEditDto);
//		return customerEditResponse;
		return null;
	}
	
//	private static CardEditDto buildCard(PaymentCard paymentCard) {
//		CardEditDto cardEditDto = null;
//		if (paymentCard != null) {
//			cardEditDto = new ModelMapper().map(paymentCard, CardEditDto.class);
//			cardEditDto.setCustomerCode(paymentCard.getPaymentCustomer().getCode());
//			cardEditDto.setCustomerDefault(paymentCard.getIsDefault());
//		}
//		return cardEditDto;
//	}
	
	private static BankAccountEditDto buildBankAccount(PaymentBankAccount paymentBankAccount) {
		BankAccountEditDto bankAccountEditDto = null;
		if (paymentBankAccount != null) {
			bankAccountEditDto = new ModelMapper().map(paymentBankAccount, BankAccountEditDto.class);
			bankAccountEditDto.setCustomerCode(paymentBankAccount.getPaymentCustomer().getCode());
			bankAccountEditDto.setCustomerDefault(paymentBankAccount.getIsDefault());
		}
		return bankAccountEditDto;
	}
	
}