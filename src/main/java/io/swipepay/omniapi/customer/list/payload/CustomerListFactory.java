package io.swipepay.omniapi.customer.list.payload;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swipepay.omniapi.bankaccount.list.payload.dto.BankAccountListDto;
import io.swipepay.omniapi.card.list.payload.dto.CardListDto;
import io.swipepay.omniapi.common.entity.paymentbankaccount.PaymentBankAccount;
import io.swipepay.omniapi.common.entity.paymentcard.PaymentCard;
import io.swipepay.omniapi.common.entity.paymentcustomer.PaymentCustomer;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.RequestException;
import io.swipepay.omniapi.customer.list.CustomerListData;
import io.swipepay.omniapi.customer.list.payload.dto.CustomerListDto;

public class CustomerListFactory {
	
	public static CustomerListRequest build(String jsonPayload) throws RequestException {
		CustomerListRequest customerListRequest = null;
		try {
			customerListRequest = new ObjectMapper().readValue(jsonPayload, CustomerListRequest.class);
			if (customerListRequest == null) {
				throw new RequestException(
						Status.RS_0004, 
						"Deserialization of the JSON payload was successful, yet the CustomerListRequest object is NULL", 
						null);
			}
		}
		catch (Exception exception) {
			throw new RequestException(
					Status.RS_0004, 
					"Deserialization failed because the JSON payload does not conform to the CustomerListRequest class", 
					exception);
		}
		return customerListRequest;
	}
	
	public static CustomerListResponse build(CustomerListRequest customerListRequest, CustomerListData customerListData) {
//		LinkedList<CustomerListDto> customerListDtos = new LinkedList<CustomerListDto>();
//		
//		LinkedList<PaymentCustomer> paymentCustomers = customerListData.getPaymentCustomers();
//		for (PaymentCustomer paymentCustomer : paymentCustomers) {
//			LinkedList<CardListDto> cardListDtos = buildCards(paymentCustomer.getPaymentCards());
//			LinkedList<BankAccountListDto> bankAccountListDtos = buildBankAccounts(paymentCustomer.getPaymentBankAccounts());
//			
//			CustomerListDto customerListDto = new ModelMapper().map(paymentCustomer, CustomerListDto.class);
//			customerListDto.setCardListDtos(cardListDtos);
//			customerListDto.setBankAccountListDtos(bankAccountListDtos);
//			customerListDtos.add(customerListDto);
//		}
//		
//		CustomerListResponse customerListResponse = new CustomerListResponse(Status.RS_0000);
//		customerListResponse.setMerchantProfileDto(customerListRequest.getMerchantProfileDto());
//		customerListResponse.setCustomerListDtos(customerListDtos);
//		return customerListResponse;
		return null;
	}
	
	private static LinkedList<CardListDto> buildCards(List<PaymentCard> paymentCards) {
		LinkedList<CardListDto> cardListDtos = null;
		if (CollectionUtils.isNotEmpty(paymentCards)) {
			cardListDtos = new LinkedList<CardListDto>();
			for (PaymentCard paymentCard : paymentCards) {
				CardListDto cardListDto = new ModelMapper().map(paymentCard, CardListDto.class);
				cardListDto.setCustomerCode(paymentCard.getPaymentCustomer().getCode());
				cardListDto.setCustomerDefault(paymentCard.getIsDefault());
				cardListDtos.add(cardListDto);
			}
		}
		return cardListDtos;
	}
	
	private static LinkedList<BankAccountListDto> buildBankAccounts(List<PaymentBankAccount> paymentBankAccounts) {
		LinkedList<BankAccountListDto> bankAccountListDtos = null;
		if (CollectionUtils.isNotEmpty(paymentBankAccounts)) {
			bankAccountListDtos = new LinkedList<BankAccountListDto>();
			for (PaymentBankAccount paymentBankAccount : paymentBankAccounts) {
				BankAccountListDto bankAccountListDto = new ModelMapper().map(paymentBankAccount, BankAccountListDto.class);
				bankAccountListDto.setCustomerCode(paymentBankAccount.getPaymentCustomer().getCode());
				bankAccountListDto.setCustomerDefault(paymentBankAccount.getIsDefault());
				bankAccountListDtos.add(bankAccountListDto);
			}
		}
		return bankAccountListDtos;
	}
	
}