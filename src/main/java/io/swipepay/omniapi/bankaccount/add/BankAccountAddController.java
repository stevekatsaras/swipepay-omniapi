package io.swipepay.omniapi.bankaccount.add;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.swipepay.omniapi.bankaccount.add.payload.BankAccountAddFactory;
import io.swipepay.omniapi.bankaccount.add.payload.BankAccountAddRequest;
import io.swipepay.omniapi.bankaccount.add.payload.BankAccountAddResponse;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.support.AuthoriseSupport;
import io.swipepay.omniapi.common.validator.RequestValidator;

@RestController
public class BankAccountAddController {
	
	@Autowired
	private AuthoriseSupport authoriseSupport;
	
	@Autowired
	private BankAccountAddAction bankAccountAddAction;
	
	@Autowired
	private BankAccountAddValidator bankAccountAddValidator;
	
	@Autowired
	private RequestValidator requestValidator;
	
	@PostMapping(value = "/bankaccount/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public BankAccountAddResponse add(
			@RequestHeader(name = "X-Access-Key", defaultValue = "") String xAccessKey,
			@RequestHeader(name = "X-Timestamp", defaultValue = "") String xTimestamp,
			@RequestHeader(name = "X-Fingerprint", defaultValue = "") String xFingerprint,
			HttpEntity<String> httpEntity, 
			HttpServletRequest httpServletRequest) {
		
		String jsonPayload = httpEntity.getBody();
		
		MerchantProfile merchantProfile = authoriseSupport.authorise(xAccessKey, xTimestamp, xFingerprint, jsonPayload);
		BankAccountAddRequest bankAccountAddRequest = BankAccountAddFactory.build(jsonPayload);
		BankAccountAddData bankAccountAddData = new BankAccountAddData(merchantProfile);
		
		//requestValidator.validate(bankAccountAddRequest, bankAccountAddData, httpServletRequest);
		bankAccountAddValidator.validate(bankAccountAddRequest, bankAccountAddData);
		
		return bankAccountAddAction.add(bankAccountAddRequest, bankAccountAddData);
	}
}