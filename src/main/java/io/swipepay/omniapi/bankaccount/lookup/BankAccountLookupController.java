package io.swipepay.omniapi.bankaccount.lookup;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.swipepay.omniapi.bankaccount.lookup.payload.BankAccountLookupFactory;
import io.swipepay.omniapi.bankaccount.lookup.payload.BankAccountLookupRequest;
import io.swipepay.omniapi.bankaccount.lookup.payload.BankAccountLookupResponse;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.support.AuthoriseSupport;
import io.swipepay.omniapi.common.validator.RequestValidator;

@RestController
public class BankAccountLookupController {
	
	@Autowired
	private AuthoriseSupport authoriseSupport;
	
	@Autowired
	private BankAccountLookupAction bankAccountLookupAction;
	
	@Autowired
	private BankAccountLookupValidator bankAccountLookupValidator;
	
	@Autowired
	private RequestValidator requestValidator;
	
	@PostMapping(value = "/bankaccount/lookup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public BankAccountLookupResponse lookup(
			@RequestHeader(name = "X-Access-Key", defaultValue = "") String xAccessKey,
			@RequestHeader(name = "X-Timestamp", defaultValue = "") String xTimestamp,
			@RequestHeader(name = "X-Fingerprint", defaultValue = "") String xFingerprint,
			HttpEntity<String> httpEntity, 
			HttpServletRequest httpServletRequest) {
		
//		String jsonPayload = httpEntity.getBody();
//		
//		MerchantProfile merchantProfile = authoriseSupport.authorise(xAccessKey, xTimestamp, xFingerprint, jsonPayload);
//		BankAccountLookupRequest bankAccountLookupRequest = BankAccountLookupFactory.build(jsonPayload);
//		BankAccountLookupData bankAccountLookupData = new BankAccountLookupData(merchantProfile);
//		
//		requestValidator.validate(bankAccountLookupRequest, bankAccountLookupData, httpServletRequest);
//		bankAccountLookupValidator.validate(bankAccountLookupRequest, bankAccountLookupData);
//		
//		return bankAccountLookupAction.lookup(bankAccountLookupRequest, bankAccountLookupData);
		return null;
	}
}