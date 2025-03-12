package io.swipepay.omniapi.bankaccount.get;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.swipepay.omniapi.bankaccount.get.payload.BankAccountGetFactory;
import io.swipepay.omniapi.bankaccount.get.payload.BankAccountGetRequest;
import io.swipepay.omniapi.bankaccount.get.payload.BankAccountGetResponse;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.support.AuthoriseSupport;
import io.swipepay.omniapi.common.validator.RequestValidator;

@RestController
public class BankAccountGetController {
	
	@Autowired
	private AuthoriseSupport authoriseSupport;
	
	@Autowired
	private BankAccountGetAction bankAccountGetAction;
	
	@Autowired
	private BankAccountGetValidator bankAccountGetValidator;
	
	@Autowired
	private RequestValidator requestValidator;
	
	@PostMapping(value = "/bankaccount/get", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public BankAccountGetResponse get(
			@RequestHeader(name = "X-Access-Key", defaultValue = "") String xAccessKey,
			@RequestHeader(name = "X-Timestamp", defaultValue = "") String xTimestamp,
			@RequestHeader(name = "X-Fingerprint", defaultValue = "") String xFingerprint,
			HttpEntity<String> httpEntity, 
			HttpServletRequest httpServletRequest) {
		
//		String jsonPayload = httpEntity.getBody();
//		
//		MerchantProfile merchantProfile = authoriseSupport.authorise(xAccessKey, xTimestamp, xFingerprint, jsonPayload);
//		BankAccountGetRequest bankAccountGetRequest = BankAccountGetFactory.build(jsonPayload);
//		BankAccountGetData bankAccountGetData = new BankAccountGetData(merchantProfile);
//		
//		requestValidator.validate(bankAccountGetRequest, bankAccountGetData, httpServletRequest);
//		bankAccountGetValidator.validate(bankAccountGetRequest, bankAccountGetData);
//		
//		return bankAccountGetAction.get(bankAccountGetRequest, bankAccountGetData);
		return null;
	}
}