package io.swipepay.omniapi.customer.get;

import org.springframework.stereotype.Component;

import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.ValidationException;
import io.swipepay.omniapi.customer.CustomerValidator;
import io.swipepay.omniapi.customer.get.payload.CustomerGetRequest;
import io.swipepay.omniapi.customer.get.payload.dto.CustomerGetDto;

@Component
public class CustomerGetValidator extends CustomerValidator {
	
	public void validate(CustomerGetRequest customerGetRequest, CustomerGetData customerGetData) throws ValidationException {
//		CustomerGetDto customerGetDto = customerGetRequest.getCustomerGetDto();
//		MerchantProfile merchantProfile = customerGetData.getMerchantProfile();
//		try {
//			validateCustomer(customerGetDto);
//			validatePaymentCustomer(customerGetDto.getCode(), merchantProfile, false);
//		}
//		catch (ValidationException exception) {
//			throw exception;
//		}
//		catch (Exception exception) {
//			throw new ValidationException(
//					Status.RS_0005, 
//					"Validation failed due to an internal server error", 
//					exception);
//		}
	}
	
	private void validateCustomer(CustomerGetDto customerGetDto) throws ValidationException {
		if (customerGetDto == null) {
			throw new ValidationException(
					Status.RS_4000, 
					"Validation failed because the customer is missing", 
					null);
		}
	}
}