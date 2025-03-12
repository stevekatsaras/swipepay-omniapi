package io.swipepay.omniapi.common.entity.paymentcustomer;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;

import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;

public interface PaymentCustomerRepository extends JpaRepository<PaymentCustomer, Long>, PaymentCustomerRepositorySupport {
	
	PaymentCustomer findByCodeAndMerchantProfile(String code, MerchantProfile merchantProfile);
	
	PaymentCustomer findByCodeAndMerchantProfileAndEnabled(String code, MerchantProfile merchantProfile, Boolean enabled);
		
	LinkedList<PaymentCustomer> findByMerchantProfileAndEnabledOrderByModifiedDesc(
			MerchantProfile merchantProfile, 
			Boolean enabled);
}