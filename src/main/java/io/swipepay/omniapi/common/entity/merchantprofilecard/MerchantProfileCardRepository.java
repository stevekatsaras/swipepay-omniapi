package io.swipepay.omniapi.common.entity.merchantprofilecard;

import java.util.LinkedList;
import org.springframework.data.jpa.repository.JpaRepository;

import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;

public interface MerchantProfileCardRepository extends JpaRepository<MerchantProfileCard, Long> {
	
	LinkedList<MerchantProfileCard> findByMerchantProfileAndEnabled(MerchantProfile merchantProfile, Boolean enabled);
}