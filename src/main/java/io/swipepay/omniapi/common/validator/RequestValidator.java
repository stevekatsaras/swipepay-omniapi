package io.swipepay.omniapi.common.validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.springframework.stereotype.Component;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.ValidationException;

@Component
public class RequestValidator {
	
	public String validateClientIpAddress(String clientIpAddress) throws ValidationException {
		if (StringUtils.isBlank(clientIpAddress)) {
			throw new ValidationException(
					Status.RS_0025, 
					"Validation failed because the client IP address cannot be determined from the http servlet request", 
					null);
		}		
		if (!InetAddressValidator.getInstance().isValid(clientIpAddress)) {
			throw new ValidationException(
					Status.RS_0026, 
					"Request validation failed because the client IP address is invalid", 
					null);
		}
		return clientIpAddress;
	}
	
	public void validateMerchantProfile(String merchantProfileCode, MerchantProfile merchantProfile) throws ValidationException {
		if (StringUtils.isBlank(merchantProfileCode)) {
			throw new ValidationException(
					Status.RS_0028, 
					"Validation failed because the merchant profile is not supplied", 
					null);
		}
		if (!StringUtils.equals(merchantProfileCode, merchantProfile.getCode())) {
			throw new ValidationException(
					Status.RS_0029, 
					"Validation failed because the merchant profile is invalid", 
					null);			
		}
	}
}