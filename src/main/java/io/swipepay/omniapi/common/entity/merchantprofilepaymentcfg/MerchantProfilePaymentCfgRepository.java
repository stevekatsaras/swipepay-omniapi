package io.swipepay.omniapi.common.entity.merchantprofilepaymentcfg;

import org.springframework.data.jpa.repository.JpaRepository;

import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;

public interface MerchantProfilePaymentCfgRepository extends JpaRepository<MerchantProfilePaymentCfg, Long> {
	
	MerchantProfilePaymentCfg findByMerchantProfile(MerchantProfile merchantProfile);

}