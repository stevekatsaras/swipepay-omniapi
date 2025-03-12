package io.swipepay.omniapi.customer.get;

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
import io.swipepay.omniapi.customer.get.payload.CustomerGetFactory;
import io.swipepay.omniapi.customer.get.payload.CustomerGetRequest;
import io.swipepay.omniapi.customer.get.payload.CustomerGetResponse;

@RestController
public class CustomerGetController {
	
	@Autowired
	private AuthoriseSupport authoriseSupport;
	
	@Autowired
	private CustomerGetAction customerGetAction;
	
	@Autowired
	private CustomerGetValidator customerGetValidator;
	
	@Autowired
	private RequestValidator requestValidator;
	
	@PostMapping(value = "/customer/get", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomerGetResponse get(
			@RequestHeader(name = "X-Access-Key", defaultValue = "") String xAccessKey,
			@RequestHeader(name = "X-Timestamp", defaultValue = "") String xTimestamp,
			@RequestHeader(name = "X-Fingerprint", defaultValue = "") String xFingerprint,
			HttpEntity<String> httpEntity, 
			HttpServletRequest httpServletRequest) {
		
//		String jsonPayload = httpEntity.getBody();
//		
//		MerchantProfile merchantProfile = authoriseSupport.authorise(xAccessKey, xTimestamp, xFingerprint, jsonPayload);
//		CustomerGetRequest customerGetRequest = CustomerGetFactory.build(jsonPayload);
//		CustomerGetData customerGetData = new CustomerGetData(merchantProfile);
//		
//		requestValidator.validate(customerGetRequest, customerGetData, httpServletRequest);
//		customerGetValidator.validate(customerGetRequest, customerGetData);
//		
//		return customerGetAction.get(customerGetRequest, customerGetData);
		return null;
	}
}