package io.swipepay.omniapi.customer.add;

import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.swipepay.omniapi.bankaccount.BankAccountClient;
import io.swipepay.omniapi.bankaccount.BankAccountException;
import io.swipepay.omniapi.bankaccount.add.payload.BankAccountAddResponse;
import io.swipepay.omniapi.bankaccount.add.payload.dto.BankAccountAddDto;
import io.swipepay.omniapi.card.CardClient;
import io.swipepay.omniapi.card.CardException;
import io.swipepay.omniapi.card.add.CardAddResponse;
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
import io.swipepay.omniapi.customer.add.payload.CustomerAddFactory;
import io.swipepay.omniapi.customer.add.payload.CustomerAddRequest;
import io.swipepay.omniapi.customer.add.payload.CustomerAddResponse;
import io.swipepay.omniapi.customer.add.payload.dto.CustomerAddDto;

@Service
public class CustomerAddAction {
	
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
	
	public CustomerAddResponse add(CustomerAddRequest customerAddRequest, CustomerAddData customerAddData) throws CustomerException {
//		CustomerAddDto customerAddDto = customerAddRequest.getCustomerAddDto();
//		MerchantProfile merchantProfile = customerAddData.getMerchantProfile();
//		try {
//			PaymentCard paymentCard = tokeniseCard(customerAddDto.getCardAddDto(), merchantProfile);
//			PaymentBankAccount paymentBankAccount = tokeniseBankAccount(customerAddDto.getBankAccountAddDto(), merchantProfile);
//			PaymentCustomer paymentCustomer = getPaymentCustomer(customerAddDto.getCode(), merchantProfile);
//			
//			Boolean isDefault = false;
//			
//			if (paymentCustomer == null) {
//				paymentCustomer = buildPaymentCustomer(customerAddDto, merchantProfile);
//				isDefault = true;
//			}
//			
//			save(paymentCustomer, paymentCard, paymentBankAccount, isDefault);
//			
//			customerAddData.setPaymentCustomer(paymentCustomer);
//			customerAddData.setPaymentCard(paymentCard);
//			customerAddData.setPaymentBankAccount(paymentBankAccount);
//		}
//		catch (CustomerException exception) {
//			throw exception;
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
//		return CustomerAddFactory.build(customerAddRequest, customerAddData);
		return null;
	}
	
	@Transactional(readOnly = true)
	private PaymentCustomer getPaymentCustomer(String customerCode, MerchantProfile merchantProfile) {
		PaymentCustomer paymentCustomer = null;
		if (StringUtils.isNotBlank(customerCode)) {
			paymentCustomer = paymentCustomerRepository.findByCodeAndMerchantProfileAndEnabled(
				customerCode, 
				merchantProfile, 
				true); // enabled
		}
		return paymentCustomer;
	}
	
	@Transactional(readOnly =  true)
	private PaymentCustomer buildPaymentCustomer(CustomerAddDto customerAddDto, MerchantProfile merchantProfile) {
		PaymentCustomer paymentCustomer = new ModelMapper().map(customerAddDto, PaymentCustomer.class);
		paymentCustomer.setCode("cus_" + UUID.randomUUID().toString().replaceAll("-", ""));
		paymentCustomer.setEnabled(true);
		paymentCustomer.setModified(LocalDateTime.now());
		paymentCustomer.setMerchantProfile(merchantProfile);
		paymentCustomer.setCountry(countryRepository.findByIso2(customerAddDto.getCountryCode()));
		return paymentCustomer;
	}
	
	@Transactional(readOnly = false)
	private void save(PaymentCustomer paymentCustomer, PaymentCard paymentCard, PaymentBankAccount paymentBankAccount, Boolean isDefault) {
		paymentCustomerRepository.saveAndFlush(paymentCustomer);
		
		if (paymentCard != null) {
			paymentCard.setIsDefault(isDefault);
			paymentCard.setPaymentCustomer(paymentCustomer);
			paymentCard.setModified(LocalDateTime.now());
			paymentCardRepository.saveAndFlush(paymentCard);
		}
		
		if (paymentBankAccount != null) {
			paymentBankAccount.setIsDefault(isDefault);
			paymentBankAccount.setPaymentCustomer(paymentCustomer);
			paymentBankAccount.setModified(LocalDateTime.now());
			paymentBankAccountRepository.saveAndFlush(paymentBankAccount);
		}
	}
	
/*	private PaymentCard tokeniseCard(CardAddDto cardAddDto, MerchantProfile merchantProfile) throws CustomerException, CardException {
		PaymentCard paymentCard = null;
		if (cardAddDto != null) {
			String paymentCardCode = cardAddDto.getCode();
			
			if (StringUtils.isBlank(paymentCardCode)) {
				CardAddResponse cardAddResponse = cardClient.addCard(cardAddDto, merchantProfile);
				paymentCardCode = cardAddResponse.getCardAddDto().getCode();
			}
			
			paymentCard = paymentCardRepository.findByCodeAndMerchantProfileAndEnabled(
					paymentCardCode, 
					merchantProfile, 
					true); // enabled
			
			if (paymentCard.getPaymentCustomer() != null) {
				throw new CustomerException(
						Status.RS_2014, 
						"Customer request failed because payment card is associated with another customer", 
						null);
			}
		}
		return paymentCard;
	}
	*/
	private PaymentBankAccount tokeniseBankAccount(BankAccountAddDto bankAccountAddDto, MerchantProfile merchantProfile) {
		PaymentBankAccount paymentBankAccount = null;
		if (bankAccountAddDto != null) {
			String paymentBankAccountCode = bankAccountAddDto.getCode();
			
			if (StringUtils.isBlank(paymentBankAccountCode)) {
				BankAccountAddResponse bankAccountAddResponse = bankAccountClient.addBankAccount(bankAccountAddDto, merchantProfile);
				paymentBankAccountCode = bankAccountAddResponse.getBankAccountAddDto().getCode();
			}
			
			paymentBankAccount = paymentBankAccountRepository.findByCodeAndMerchantProfileAndEnabled(
					paymentBankAccountCode, 
					merchantProfile, 
					true); // enabled
			
			if (paymentBankAccount.getPaymentCustomer() != null) {
				throw new CustomerException(
						Status.RS_5014, 
						"Customer request failed because the payment bank account is associated with another customer", 
						null);
			}
		}
		return paymentBankAccount;
	}
}