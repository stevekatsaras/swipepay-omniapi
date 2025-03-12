package io.swipepay.omniapi.bankaccount.edit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.swipepay.omniapi.bankaccount.edit.payload.BankAccountEditFactory;
import io.swipepay.omniapi.bankaccount.edit.payload.BankAccountEditRequest;
import io.swipepay.omniapi.bankaccount.edit.payload.BankAccountEditResponse;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.support.AuthoriseSupport;
import io.swipepay.omniapi.common.validator.RequestValidator;

@RestController
public class BankAccountEditController {
	
	@Autowired
	private AuthoriseSupport authoriseSupport;
	
	@Autowired
	private BankAccountEditAction bankAccountEditAction;
	
	@Autowired
	private BankAccountEditValidator bankAccountEditValidator;
	
	@Autowired
	private RequestValidator requestValidator;
	
	@PostMapping(value = "/bankaccount/edit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public BankAccountEditResponse edit(
			@RequestHeader(name = "X-Access-Key", defaultValue = "") String xAccessKey,
			@RequestHeader(name = "X-Timestamp", defaultValue = "") String xTimestamp,
			@RequestHeader(name = "X-Fingerprint", defaultValue = "") String xFingerprint,
			HttpEntity<String> httpEntity, 
			HttpServletRequest httpServletRequest) {
		
//		String jsonPayload = httpEntity.getBody();
//		
//		MerchantProfile merchantProfile = authoriseSupport.authorise(xAccessKey, xTimestamp, xFingerprint, jsonPayload);
//		BankAccountEditRequest bankAccountEditRequest = BankAccountEditFactory.build(jsonPayload);
//		BankAccountEditData bankAccountEditData = new BankAccountEditData(merchantProfile);
//		
//		requestValidator.validate(bankAccountEditRequest, bankAccountEditData, httpServletRequest);
//		bankAccountEditValidator.validate(bankAccountEditRequest, bankAccountEditData);
//		
//		return bankAccountEditAction.edit(bankAccountEditRequest, bankAccountEditData);
		return null;
	}
}