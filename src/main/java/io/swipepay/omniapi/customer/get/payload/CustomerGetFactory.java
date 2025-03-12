package io.swipepay.omniapi.customer.get.payload;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swipepay.omniapi.bankaccount.get.payload.dto.BankAccountGetDto;
import io.swipepay.omniapi.card.get.payload.dto.CardGetDto;
import io.swipepay.omniapi.common.entity.paymentbankaccount.PaymentBankAccount;
import io.swipepay.omniapi.common.entity.paymentcard.PaymentCard;
import io.swipepay.omniapi.common.entity.paymentcustomer.PaymentCustomer;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.RequestException;
import io.swipepay.omniapi.customer.get.CustomerGetData;
import io.swipepay.omniapi.customer.get.payload.dto.CustomerGetDto;

public class CustomerGetFactory {
	
	public static CustomerGetRequest build(String jsonPayload) throws RequestException {
		CustomerGetRequest customerGetRequest = null;
		try {
			customerGetRequest = new ObjectMapper().readValue(jsonPayload, CustomerGetRequest.class);
			if (customerGetRequest == null) {
				throw new RequestException(
						Status.RS_0004, 
						"Deserialization of the JSON payload was successful, yet the CustomerGetRequest object is NULL", 
						null);
			}
		}
		catch (Exception exception) {
			throw new RequestException(
					Status.RS_0004, 
					"Deserialization failed because the JSON payload does not conform to the CustomerGetRequest class", 
					exception);
		}
		return customerGetRequest;
	}
	
	public static CustomerGetResponse build(CustomerGetRequest customerGetRequest, CustomerGetData customerGetData) {
//		PaymentCustomer paymentCustomer = customerGetData.getPaymentCustomer();
//		
//		LinkedList<CardGetDto> cardGetDtos = buildCards(paymentCustomer.getPaymentCards());
//		LinkedList<BankAccountGetDto> bankAccountGetDtos = buildBankAccounts(paymentCustomer.getPaymentBankAccounts());
//				
//		CustomerGetDto customerGetDto = new ModelMapper().map(paymentCustomer, CustomerGetDto.class);
//		customerGetDto.setCardGetDtos(cardGetDtos);
//		customerGetDto.setBankAccountGetDtos(bankAccountGetDtos);
//		
//		CustomerGetResponse customerGetResponse = new CustomerGetResponse(Status.RS_0000);
//		customerGetResponse.setMerchantProfileDto(customerGetRequest.getMerchantProfileDto());
//		customerGetResponse.setCustomerGetDto(customerGetDto);
//		return customerGetResponse;
		return null;
	}
	
	private static LinkedList<CardGetDto> buildCards(List<PaymentCard> paymentCards) {
		LinkedList<CardGetDto> cardGetDtos = null;
		if (CollectionUtils.isNotEmpty(paymentCards)) {
			cardGetDtos = new LinkedList<CardGetDto>();
			for (PaymentCard paymentCard : paymentCards) {
				CardGetDto cardGetDto = new ModelMapper().map(paymentCard, CardGetDto.class);
				cardGetDto.setCustomerCode(paymentCard.getPaymentCustomer().getCode());
				cardGetDto.setCustomerDefault(paymentCard.getIsDefault());
				cardGetDtos.add(cardGetDto);
			}
		}
		return cardGetDtos;
	}
	
	private static LinkedList<BankAccountGetDto> buildBankAccounts(List<PaymentBankAccount> paymentBankAccounts) {
		LinkedList<BankAccountGetDto> bankAccountGetDtos = null;
		if (CollectionUtils.isNotEmpty(paymentBankAccounts)) {
			bankAccountGetDtos = new LinkedList<BankAccountGetDto>();
			for (PaymentBankAccount paymentBankAccount : paymentBankAccounts) {
				BankAccountGetDto bankAccountGetDto = new ModelMapper().map(paymentBankAccount, BankAccountGetDto.class);
				bankAccountGetDto.setCustomerCode(paymentBankAccount.getPaymentCustomer().getCode());
				bankAccountGetDto.setCustomerDefault(paymentBankAccount.getIsDefault());
				bankAccountGetDtos.add(bankAccountGetDto);
			}
		}
		return bankAccountGetDtos;
	}
	
}