package io.swipepay.omniapi.common.entity.merchantprofilepaymentgateway;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;

public interface MerchantProfilePaymentGatewayRepository extends JpaRepository<MerchantProfilePaymentGateway, Long> {
	
	@Query("SELECT count(*) FROM MerchantProfilePaymentGateway mppg WHERE mppg.merchantProfile = :merchantProfile "
			+ "AND mppg.enabled = true AND mppg.paymentGateway.doesCc = :paymentGatewayDoesCc")
	Long countByMerchantProfileAndEnabledIsTrueAndPaymentGatewayDoCc(
			@Param("merchantProfile") MerchantProfile merchantProfile, 
			@Param("paymentGatewayDoesCc") Boolean paymentGatewayDoesCc);
	
	@Query("SELECT mppg FROM MerchantProfilePaymentGateway mppg WHERE mppg.merchantProfile = :merchantProfile "
			+ "AND mppg.enabled = true AND mppg.paymentGateway.doesCc = :paymentGatewayDoesCc ORDER BY mppg.priority ASC")
	LinkedList<MerchantProfilePaymentGateway> findByMerchantProfileAndEnabledIsTrueAndPaymentGatewayDoesCcOrderByPriorityAsc(
			@Param("merchantProfile") MerchantProfile merchantProfile, 
			@Param("paymentGatewayDoesCc") Boolean paymentGatewayDoesCc);

	
	
	
//	MerchantProfilePaymentGateway findByMerchantProfileAndPaymentGatewayAndEnabled(
//			MerchantProfile merchantProfile, 
//			PaymentGateway paymentGateway, 
//			Boolean enabled);
	
}