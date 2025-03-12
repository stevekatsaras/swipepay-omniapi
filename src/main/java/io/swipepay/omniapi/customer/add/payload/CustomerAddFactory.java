package io.swipepay.omniapi.customer.add.payload;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swipepay.omniapi.bankaccount.add.payload.dto.BankAccountAddDto;
import io.swipepay.omniapi.common.entity.paymentbankaccount.PaymentBankAccount;
import io.swipepay.omniapi.common.entity.paymentcard.PaymentCard;
import io.swipepay.omniapi.common.entity.paymentcustomer.PaymentCustomer;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.RequestException;
import io.swipepay.omniapi.customer.add.CustomerAddData;
import io.swipepay.omniapi.customer.add.payload.dto.CustomerAddDto;

public class CustomerAddFactory {
	
	public static CustomerAddRequest build(String jsonPayload) throws RequestException {
		CustomerAddRequest customerAddRequest = null;
		try {
			customerAddRequest = new ObjectMapper().readValue(jsonPayload, CustomerAddRequest.class);
			if (customerAddRequest == null) {
				throw new RequestException(
						Status.RS_0004, 
						"Deserialization of the JSON payload was successful, yet the CustomerAddRequest object is NULL", 
						null);
			}
		}
		catch (Exception exception) {
			throw new RequestException(
					Status.RS_0004, 
					"Deserialization failed because the JSON payload does not conform to the CustomerAddRequest class", 
					exception);
		}
		return customerAddRequest;
	}
	
	public static CustomerAddResponse build(CustomerAddRequest customerAddRequest, CustomerAddData customerAddData) {
//		PaymentCustomer paymentCustomer = customerAddData.getPaymentCustomer();
//		
//		CardAddDto cardAddDto = buildCard(customerAddData.getPaymentCard());
//		BankAccountAddDto bankAccountAddDto = buildBankAccount(customerAddData.getPaymentBankAccount());
//		
//		CustomerAddDto customerAddDto = new ModelMapper().map(paymentCustomer, CustomerAddDto.class);
//		customerAddDto.setCardAddDto(cardAddDto);
//		customerAddDto.setBankAccountAddDto(bankAccountAddDto);
//		
//		CustomerAddResponse customerAddResponse = new CustomerAddResponse(Status.RS_0000);
//		customerAddResponse.setMerchantProfileDto(customerAddRequest.getMerchantProfileDto());
//		customerAddResponse.setCustomerAddDto(customerAddDto);
//		return customerAddResponse;
		return null;
	}
	
	/*
	private static CardAddDto buildCard(PaymentCard paymentCard) {
		CardAddDto cardAddDto = null;
		if (paymentCard != null) {
			cardAddDto = new ModelMapper().map(paymentCard, CardAddDto.class);
			cardAddDto.setCustomerCode(paymentCard.getPaymentCustomer().getCode());
			cardAddDto.setCustomerDefault(paymentCard.getIsDefault());
		}
		return cardAddDto;
	}
	*/
	private static BankAccountAddDto buildBankAccount(PaymentBankAccount paymentBankAccount) {
		BankAccountAddDto bankAccountAddDto = null;
		if (paymentBankAccount != null) {
			bankAccountAddDto = new ModelMapper().map(paymentBankAccount, BankAccountAddDto.class);
			bankAccountAddDto.setCustomerCode(paymentBankAccount.getPaymentCustomer().getCode());
			bankAccountAddDto.setCustomerDefault(paymentBankAccount.getIsDefault());
		}
		return bankAccountAddDto;
	}
}