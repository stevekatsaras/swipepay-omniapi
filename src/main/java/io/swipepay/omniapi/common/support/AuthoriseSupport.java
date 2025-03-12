package io.swipepay.omniapi.common.support;

import java.time.Instant;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.swipepay.omniapi.common.config.properties.OmniApiAuthorisationProperties;
import io.swipepay.omniapi.common.entity.merchant.Merchant;
import io.swipepay.omniapi.common.entity.merchant.enums.MerchantStatus;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfileRepository;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.AuthoriseException;

@Component
public class AuthoriseSupport {
	
	@Autowired
	private FingerprintSupport fingerprintSupport;
	
	@Autowired
	private MerchantProfileRepository merchantProfileRepository;
	
	@Autowired
	private OmniApiAuthorisationProperties omniApiAuthorisationProperties;
	
	@Transactional(readOnly = true)
	public MerchantProfile authorise(
			String xAccessKey, 
			String xTimestamp, 
			String xFingerprint, 
			String requestPayload) throws AuthoriseException {
		
		MerchantProfile merchantProfile;
		try {
			if (StringUtils.isBlank(xAccessKey) || StringUtils.isBlank(xTimestamp) || StringUtils.isBlank(xFingerprint)) {
				throw new AuthoriseException(
						Status.RS_0001, 
						"Authorisation failed because of bad credentials: X-Access-Key, X-Timestamp and X-Fingerprint are required", 
						null);
			}
			
			if (BooleanUtils.isTrue(omniApiAuthorisationProperties.getExpirationEnabled())) {
				if (isRequestExpired(xTimestamp)) {
					throw new AuthoriseException(
							Status.RS_0001, 
							"Authorisation failed because the request is expired", 
							null);
				}
			}
			
			merchantProfile = merchantProfileRepository.findByAccessKey(xAccessKey);
			if (merchantProfile == null) {
				throw new AuthoriseException(
						Status.RS_0001, 
						"Authorisation failed because the X-Access-Key could not be found", 
						null);
			}
			
			if (!BooleanUtils.isTrue(merchantProfile.getEnabled())) {
				throw new AuthoriseException(
						Status.RS_0002, 
						"Authorisation failed because the merchant profile is not enabled", 
						null);
			}
			
			Merchant merchant = merchantProfile.getMerchant();
			if (!StringUtils.equals(merchant.getStatus(), MerchantStatus.Active.name())) {
				throw new AuthoriseException(
						Status.RS_0002, 
						"Authorisation failed because the merchant is not active", 
						null);
			}
			
			String requestToSign = new StringBuilder()
					.append(xTimestamp).append(":").append(requestPayload)
					.toString();
			
			String calculatedFingerprint = fingerprintSupport.calculate(merchantProfile.getSecretKey(), requestToSign);
			
			if (!StringUtils.equals(xFingerprint, calculatedFingerprint)) {
				throw new AuthoriseException(
						Status.RS_0001, 
						"Authorisation failed because the calculated fingerprint does not match the X-Fingerprint", 
						null);
			}
		}
		catch (AuthoriseException exception) {
			throw exception;
		}
		catch (Exception exception) {
			throw new AuthoriseException(
					Status.RS_0003, 
					"Authorisation failed due to an internal server error", 
					exception);
		}
		return merchantProfile;
	}
	
	private boolean isRequestExpired(String xTimestamp) throws Exception {
		Instant now = Instant.now();
		Instant request = Instant.ofEpochMilli(Long.parseLong(xTimestamp));
		request = request.plusMillis(Long.parseLong(omniApiAuthorisationProperties.getExpirationTimeout()));
		return now.isAfter(request);
	}
}