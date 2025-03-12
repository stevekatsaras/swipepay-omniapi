package io.swipepay.omniapi.customer;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.swipepay.omniapi.common.entity.country.Country;
import io.swipepay.omniapi.common.entity.country.CountryRepository;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.paymentcustomer.PaymentCustomer;
import io.swipepay.omniapi.common.entity.paymentcustomer.PaymentCustomerRepository;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.ValidationException;

@Component
public class CustomerValidator {
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private PaymentCustomerRepository paymentCustomerRepository;
	
	public void validateFirstName(String firstName) throws ValidationException {
		if (StringUtils.isBlank(firstName)) {
			throw new ValidationException(
					Status.RS_4001, 
					"Validation failed because the first name is not supplied", 
					null);
		}
	}
	
	public void validateLastName(String lastName) throws ValidationException {
		if (StringUtils.isBlank(lastName)) {
			throw new ValidationException(
					Status.RS_4002, 
					"Validation failed because the last name is not supplied", 
					null);
		}
	}

	public void validateAddress(String address) throws ValidationException {
		if (StringUtils.isBlank(address)) {
			throw new ValidationException(
					Status.RS_4003, 
					"Validation failed because the address is not supplied", 
					null);
		}
	}
	
	public void validateCity(String city) throws ValidationException {
		if (StringUtils.isBlank(city)) {
			throw new ValidationException(
					Status.RS_4004, 
					"Validation failed because the city is not supplied", 
					null);
		}
	}
	
	public void validateState(String state) throws ValidationException {
		if (StringUtils.isBlank(state)) {
			throw new ValidationException(
					Status.RS_4005, 
					"Validation failed because the state is not supplied", 
					null);
		}
	}
	
	public void validatePostcode(String postcode) throws ValidationException {
		if (StringUtils.isBlank(postcode)) {
			throw new ValidationException(
					Status.RS_4006, 
					"Validation failed because the postcode is not supplied", 
					null);
		}
	}
	
	@Transactional(readOnly = true)
	public void validateCountry(String countryCode, Boolean checkBlank) throws ValidationException {
		if (checkBlank) {
			if (StringUtils.isBlank(countryCode)) {
				throw new ValidationException(
						Status.RS_4007, 
						"Validation failed because the country is not supplied", 
						null);
			}
		}
		
		if (StringUtils.isNotBlank(countryCode)) {
			Country country = countryRepository.findByIso2(countryCode);
			if (country == null) {
				throw new ValidationException(
						Status.RS_4008, 
						"Validation failed because the country is invalid", 
						null);
			}
		}
	}
	
	@Transactional(readOnly = true)
	public void validatePaymentCustomer(String customerCode, MerchantProfile merchantProfile, Boolean enforceEnabledCheck) throws ValidationException {
		if (StringUtils.isBlank(customerCode)) {
			throw new ValidationException(
					Status.RS_4009, 
					"Validation failed because the customer code is not supplied", 
					null);
		}			
		
		PaymentCustomer paymentCustomer = null;
		
		if (enforceEnabledCheck) {
			paymentCustomer = paymentCustomerRepository.findByCodeAndMerchantProfileAndEnabled(
					customerCode, 
					merchantProfile, 
					true); // enabled;			
		}
		else {
			paymentCustomer = paymentCustomerRepository.findByCodeAndMerchantProfile(customerCode, merchantProfile);
		}
		
		if (paymentCustomer == null) {
			throw new ValidationException(
					Status.RS_4010, 
					"Validation failed because the customer is invalid", 
					null);
		}
	}
}