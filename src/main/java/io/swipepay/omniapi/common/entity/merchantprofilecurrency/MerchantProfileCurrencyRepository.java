package io.swipepay.omniapi.common.entity.merchantprofilecurrency;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;

public interface MerchantProfileCurrencyRepository extends JpaRepository<MerchantProfileCurrency, Long> {
	
	@Query("SELECT mpc FROM MerchantProfileCurrency mpc WHERE mpc.merchantProfile = :merchantProfile "
			+ "AND mpc.enabled = true AND mpc.currency.iso3 = :currencyIso3")
	MerchantProfileCurrency findByMerchantProfileAndEnabledIsTrueAndCurrencyIso3(
			@Param("merchantProfile") MerchantProfile merchantProfile, 
			@Param("currencyIso3") String currencyIso3);
}