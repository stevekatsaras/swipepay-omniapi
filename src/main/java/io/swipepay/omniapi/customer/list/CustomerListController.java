package io.swipepay.omniapi.customer.list;

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
import io.swipepay.omniapi.customer.list.payload.CustomerListFactory;
import io.swipepay.omniapi.customer.list.payload.CustomerListRequest;
import io.swipepay.omniapi.customer.list.payload.CustomerListResponse;

@RestController
public class CustomerListController {
	
	@Autowired
	private AuthoriseSupport authoriseSupport;
	
	@Autowired
	private CustomerListAction customerListAction;
	
	@Autowired
	private RequestValidator requestValidator;
	
	@PostMapping(value = "/customer/list", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomerListResponse list(
			@RequestHeader(name = "X-Access-Key", defaultValue = "") String xAccessKey,
			@RequestHeader(name = "X-Timestamp", defaultValue = "") String xTimestamp,
			@RequestHeader(name = "X-Fingerprint", defaultValue = "") String xFingerprint,
			HttpEntity<String> httpEntity, 
			HttpServletRequest httpServletRequest) {
		
//		String jsonPayload = httpEntity.getBody();
//		
//		MerchantProfile merchantProfile = authoriseSupport.authorise(xAccessKey, xTimestamp, xFingerprint, jsonPayload);
//		CustomerListRequest customerListRequest = CustomerListFactory.build(jsonPayload);
//		CustomerListData customerListData = new CustomerListData(merchantProfile);
//		
//		requestValidator.validate(customerListRequest, customerListData, httpServletRequest);
//		
//		return customerListAction.list(customerListRequest, customerListData);
		return null;
	}
}