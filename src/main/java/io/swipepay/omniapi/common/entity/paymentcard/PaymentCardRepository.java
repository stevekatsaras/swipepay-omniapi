package io.swipepay.omniapi.common.entity.paymentcard;

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
public interface PaymentCardRepository extends JpaRepository<PaymentCard, Long> {
	
	PaymentCard findByCodeAndMerchantProfile(String code, MerchantProfile merchantProfile);
	
	PaymentCard findByCodeAndMerchantProfileAndEnabled(String code, MerchantProfile merchantProfile, Boolean enabled);
	
	PaymentCard findByCardPanAndCardNumberAndCardExpiryMonthAndCardExpiryYearAndMerchantProfile(
			String cardPan, 
			String cardNumber, 
			String cardExpiryMonth, 
			String cardExpiryYear, 
			MerchantProfile merchantProfile);
//	
//	PaymentCard findByIsDefaultAndPaymentCustomerAndMerchantProfile(
//			Boolean isDefault, 
//			PaymentCustomer paymentCustomer, 
//			MerchantProfile merchantProfile);
	
	LinkedList<PaymentCard> findByMerchantProfileOrderByModifiedDesc(MerchantProfile merchantProfile);
	
//	@Transactional(readOnly = false)
//	@Modifying(clearAutomatically = true)
//	@Query("UPDATE PaymentCard SET isDefault = false WHERE merchantProfile = :merchantProfile AND paymentCustomer = :paymentCustomer")
//	int setCustomerCardsToDefaultFalse(
//			@Param("merchantProfile") MerchantProfile merchantProfile, 
//			@Param("paymentCustomer") PaymentCustomer paymentCustomer);
}