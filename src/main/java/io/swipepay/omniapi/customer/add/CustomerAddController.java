package io.swipepay.omniapi.customer.add;

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
import io.swipepay.omniapi.customer.add.payload.CustomerAddFactory;
import io.swipepay.omniapi.customer.add.payload.CustomerAddRequest;
import io.swipepay.omniapi.customer.add.payload.CustomerAddResponse;

@RestController
public class CustomerAddController {
	
	@Autowired
	private AuthoriseSupport authoriseSupport;
	
	@Autowired
	private CustomerAddAction customerAddAction;
	
	@Autowired
	private CustomerAddValidator customerAddValidator;
	
	@Autowired
	private RequestValidator requestValidator;
	
	@PostMapping(value = "/customer/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomerAddResponse add(
			@RequestHeader(name = "X-Access-Key", defaultValue = "") String xAccessKey,
			@RequestHeader(name = "X-Timestamp", defaultValue = "") String xTimestamp,
			@RequestHeader(name = "X-Fingerprint", defaultValue = "") String xFingerprint,
			HttpEntity<String> httpEntity, 
			HttpServletRequest httpServletRequest) {
		
//		String jsonPayload = httpEntity.getBody();
//		
//		MerchantProfile merchantProfile = authoriseSupport.authorise(xAccessKey, xTimestamp, xFingerprint, jsonPayload);
//		CustomerAddRequest customerAddRequest = CustomerAddFactory.build(jsonPayload);
//		CustomerAddData customerAddData = new CustomerAddData(merchantProfile);
//		
//		requestValidator.validate(customerAddRequest, customerAddData, httpServletRequest);
//		customerAddValidator.validate(customerAddRequest, customerAddData);
//		
//		return customerAddAction.add(customerAddRequest, customerAddData);
		return null;
	}
}