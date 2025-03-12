package io.swipepay.omniapi.common.entity.merchantprofile;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantProfileRepository extends JpaRepository<MerchantProfile, Long> {
	
	MerchantProfile findByAccessKey(String accessKey);
}