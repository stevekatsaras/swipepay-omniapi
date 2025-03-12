package io.swipepay.omniapi.common.entity.paymenttxcc;

import org.springframework.data.jpa.repository.JpaRepository;

import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;

public interface PaymentTxCcRepository extends JpaRepository<PaymentTxCc, Long> {
	
	Long countByReferenceAndMerchantProfile(String reference, MerchantProfile merchantProfile);
	
	PaymentTxCc findByCodeAndMerchantProfile(String code, MerchantProfile merchantProfile);
	
}