package io.swipepay.omniapi.customer.edit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.support.AuthoriseSupport;
import io.swipepay.omniapi.common.validator.RequestValidator;
import io.swipepay.omniapi.customer.edit.payload.CustomerEditFactory;
import io.swipepay.omniapi.customer.edit.payload.CustomerEditRequest;
import io.swipepay.omniapi.customer.edit.payload.CustomerEditResponse;

@RestController
public class CustomerEditController {
	
	@Autowired
	private AuthoriseSupport authoriseSupport;
	
	@Autowired
	private CustomerEditAction customerEditAction;
	
	@Autowired
	private CustomerEditValidator customerEditValidator;
	
	@Autowired
	private RequestValidator requestValidator;
	
	@PostMapping(value = "/customer/edit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomerEditResponse edit(
			@RequestHeader(name = "X-Access-Key", defaultValue = "") String xAccessKey,
			@RequestHeader(name = "X-Timestamp", defaultValue = "") String xTimestamp,
			@RequestHeader(name = "X-Fingerprint", defaultValue = "") String xFingerprint,
			HttpEntity<String> httpEntity, 
			HttpServletRequest httpServletRequest) {
		
//		String jsonPayload = httpEntity.getBody();
//		
//		MerchantProfile merchantProfile = authoriseSupport.authorise(xAccessKey, xTimestamp, xFingerprint, jsonPayload);
//		CustomerEditRequest customerEditRequest = CustomerEditFactory.build(jsonPayload);
//		CustomerEditData customerEditData = new CustomerEditData(merchantProfile);
//		
//		requestValidator.validate(customerEditRequest, customerEditData, httpServletRequest);
//		customerEditValidator.validate(customerEditRequest, customerEditData);
//		
//		return customerEditAction.edit(customerEditRequest, customerEditData);
		return null;
	}
}