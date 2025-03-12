package io.swipepay.omniapi.common.entity.paymentbankaccount;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.paymentcustomer.PaymentCustomer;

@Repository
public interface PaymentBankAccountRepository extends JpaRepository<PaymentBankAccount, Long> {
	
	PaymentBankAccount findByCodeAndMerchantProfile(String code, MerchantProfile merchantProfile);
	
	PaymentBankAccount findByCodeAndMerchantProfileAndEnabled(String code, MerchantProfile merchantProfile, Boolean enabled);
	
	PaymentBankAccount findByBsbAndAccountNumberAndCountryCodeAndMerchantProfile(
			String bsb, 
			String accountNumber, 
			String countryCode, 
			MerchantProfile merchantProfile);
	
	PaymentBankAccount findByIsDefaultAndPaymentCustomerAndMerchantProfile(
			Boolean isDefault, 
			PaymentCustomer paymentCustomer, 
			MerchantProfile merchantProfile);
	
	LinkedList<PaymentBankAccount> findByMerchantProfileOrderByModifiedDesc(MerchantProfile merchantProfile);
	
	@Transactional(readOnly = false)
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE PaymentBankAccount SET isDefault = false WHERE merchantProfile = :merchantProfile AND paymentCustomer = :paymentCustomer")
	int setCustomerCardsToDefaultFalse(
			@Param("merchantProfile") MerchantProfile merchantProfile, 
			@Param("paymentCustomer") PaymentCustomer paymentCustomer);
}