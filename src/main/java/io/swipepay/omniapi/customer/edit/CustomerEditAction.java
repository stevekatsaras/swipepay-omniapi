package io.swipepay.omniapi.customer.edit;

import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.swipepay.omniapi.bankaccount.BankAccountClient;
import io.swipepay.omniapi.bankaccount.BankAccountException;
import io.swipepay.omniapi.bankaccount.edit.payload.BankAccountEditResponse;
import io.swipepay.omniapi.bankaccount.edit.payload.dto.BankAccountEditDto;
import io.swipepay.omniapi.card.CardClient;
import io.swipepay.omniapi.card.CardException;
import io.swipepay.omniapi.card.edit.CardEditResponse;
import io.swipepay.omniapi.common.entity.country.CountryRepository;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.paymentbankaccount.PaymentBankAccount;
import io.swipepay.omniapi.common.entity.paymentbankaccount.PaymentBankAccountRepository;
import io.swipepay.omniapi.common.entity.paymentcard.PaymentCard;
import io.swipepay.omniapi.common.entity.paymentcard.PaymentCardRepository;
import io.swipepay.omniapi.common.entity.paymentcustomer.PaymentCustomer;
import io.swipepay.omniapi.common.entity.paymentcustomer.PaymentCustomerRepository;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.customer.CustomerException;
import io.swipepay.omniapi.customer.edit.payload.CustomerEditFactory;
import io.swipepay.omniapi.customer.edit.payload.CustomerEditRequest;
import io.swipepay.omniapi.customer.edit.payload.CustomerEditResponse;
import io.swipepay.omniapi.customer.edit.payload.dto.CustomerEditDto;

@Service
public class CustomerEditAction {
	
	@Autowired
	private BankAccountClient bankAccountClient;
	
	@Autowired
	private CardClient cardClient;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private PaymentBankAccountRepository paymentBankAccountRepository;
	
	@Autowired
	private PaymentCardRepository paymentCardRepository;
	
	@Autowired
	private PaymentCustomerRepository paymentCustomerRepository;
	
	public CustomerEditResponse edit(CustomerEditRequest customerEditRequest, CustomerEditData customerEditData) throws CustomerException {
//		CustomerEditDto customerEditDto = customerEditRequest.getCustomerEditDto();
//		MerchantProfile merchantProfile = customerEditData.getMerchantProfile();
//		try {
//			PaymentCard paymentCard = editTokenisedCard(customerEditDto.getCardEditDto(), merchantProfile);
//			PaymentBankAccount paymentBankAccount = editTokenisedBankAccount(customerEditDto.getBankAccountEditDto(), merchantProfile);
//			PaymentCustomer paymentCustomer = getPaymentCustomer(customerEditDto.getCode(), merchantProfile);
//			
//			save(paymentCustomer, customerEditDto, merchantProfile, paymentCard, paymentBankAccount);
//			
//			customerEditData.setPaymentCustomer(paymentCustomer);
//			customerEditData.setPaymentCard(paymentCard);
//			customerEditData.setPaymentBankAccount(paymentBankAccount);
//		}
//		catch (CardException exception) {
//			throw new CustomerException(
//					Status.RS_2016, 
//					"Customer request failed because of an internal card (API) error", 
//					exception);
//		}
//		catch (BankAccountException exception) {
//			throw new CustomerException(
//					Status.RS_5016, 
//					"Customer request failed because of an internal bank account (API) error", 
//					exception);
//		}
//		catch (Exception exception) {
//			exception.printStackTrace();
//			throw new CustomerException(
//				Status.RS_0024, 
//				"Customer request failed because of an internal system error", 
//				exception);
//		}
//		
//		return CustomerEditFactory.build(customerEditRequest, customerEditData);
		return null;
	}
	
	@Transactional(readOnly = true)
	private PaymentCustomer getPaymentCustomer(String customerCode, MerchantProfile merchantProfile) {
		return paymentCustomerRepository.findByCodeAndMerchantProfile(
				customerCode, 
				merchantProfile);
	}
	
//	@Transactional(readOnly = false)
//	private void save(
//			PaymentCustomer paymentCustomer, 
//			CustomerEditDto customerEditDto, 
//			MerchantProfile merchantProfile, 
//			PaymentCard paymentCard, 
//			PaymentBankAccount paymentBankAccount) {
//		
//		if (StringUtils.isNotBlank(customerEditDto.getFirstName()))
//			paymentCustomer.setFirstName(customerEditDto.getFirstName());
//		
//		if (StringUtils.isNotBlank(customerEditDto.getLastName()))
//			paymentCustomer.setLastName(customerEditDto.getLastName());
//		
//		if (StringUtils.isNotBlank(customerEditDto.getAddress()))
//			paymentCustomer.setAddress(customerEditDto.getAddress());
//		
//		if (StringUtils.isNotBlank(customerEditDto.getCity()))
//			paymentCustomer.setCity(customerEditDto.getCity());
//		
//		if (StringUtils.isNotBlank(customerEditDto.getState()))
//			paymentCustomer.setState(customerEditDto.getState());
//		
//		if (StringUtils.isNotBlank(customerEditDto.getPostcode()))
//			paymentCustomer.setPostcode(customerEditDto.getPostcode());
//		
//		if (StringUtils.isNotBlank(customerEditDto.getCountryCode())) {
//			paymentCustomer.setCountryCode(customerEditDto.getCountryCode());
//			paymentCustomer.setCountry(countryRepository.findByIso2(customerEditDto.getCountryCode()));
//		}
//		
//		if (customerEditDto.getEnabled() != null) {
//			paymentCustomer.setEnabled(customerEditDto.getEnabled());
//		}
//		
//		paymentCustomer.setModified(LocalDateTime.now());
//		paymentCustomerRepository.saveAndFlush(paymentCustomer);
//		
//		CardEditDto cardEditDto = customerEditDto.getCardEditDto();
//		if (cardEditDto != null) {
//			Boolean isDefault = cardEditDto.getCustomerDefault();
//			if (isDefault != null) {
//				if (isDefault) {
//					paymentCardRepository.setCustomerCardsToDefaultFalse(merchantProfile, paymentCustomer);
//				}
//				paymentCard.setIsDefault(isDefault);
//				paymentCard.setModified(LocalDateTime.now());
//				paymentCardRepository.saveAndFlush(paymentCard);
//			}
//		}
//		
//		BankAccountEditDto bankAccountEditDto = customerEditDto.getBankAccountEditDto();
//		if (bankAccountEditDto != null) {
//			Boolean isDefault = bankAccountEditDto.getCustomerDefault();
//			if (isDefault != null) {
//				if (isDefault) {
//					paymentBankAccountRepository.setCustomerCardsToDefaultFalse(merchantProfile, paymentCustomer);
//				}
//				paymentBankAccount.setIsDefault(isDefault);
//				paymentBankAccount.setModified(LocalDateTime.now());
//				paymentBankAccountRepository.saveAndFlush(paymentBankAccount);
//			}
//		}
//	}
	
//	private PaymentCard editTokenisedCard(CardEditDto cardEditDto, MerchantProfile merchantProfile) throws CardException {
//		PaymentCard paymentCard = null;
//		if (cardEditDto != null) {
//			CardEditResponse cardEditResponse = cardClient.editCard(cardEditDto, merchantProfile);
//			paymentCard = paymentCardRepository.findByCodeAndMerchantProfile(cardEditResponse.getCardEditDto().getCode(), merchantProfile);
//		}
//		return paymentCard;
//	}
	
	private PaymentBankAccount editTokenisedBankAccount(BankAccountEditDto bankAccountEditDto, MerchantProfile merchantProfile) throws BankAccountException {
		PaymentBankAccount paymentBankAccount = null;
		if (bankAccountEditDto != null) {
			BankAccountEditResponse bankAccountEditResponse = bankAccountClient.editBankAccount(bankAccountEditDto, merchantProfile);
			paymentBankAccount = paymentBankAccountRepository.findByCodeAndMerchantProfile(bankAccountEditResponse.getBankAccountEditDto().getCode(), merchantProfile);
		}
		return paymentBankAccount;
	}
	
}