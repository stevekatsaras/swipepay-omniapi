package io.swipepay.omniapi.bankaccount.list;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.swipepay.omniapi.bankaccount.list.payload.BankAccountListFactory;
import io.swipepay.omniapi.bankaccount.list.payload.BankAccountListRequest;
import io.swipepay.omniapi.bankaccount.list.payload.BankAccountListResponse;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.support.AuthoriseSupport;
import io.swipepay.omniapi.common.validator.RequestValidator;

@RestController
public class BankAccountListController {
	
	@Autowired
	private AuthoriseSupport authoriseSupport;
	
	@Autowired
	private BankAccountListAction bankAccountListAction;
	
	@Autowired
	private RequestValidator requestValidator;
	
	@PostMapping(value = "/bankaccount/list", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public BankAccountListResponse list(
			@RequestHeader(name = "X-Access-Key", defaultValue = "") String xAccessKey,
			@RequestHeader(name = "X-Timestamp", defaultValue = "") String xTimestamp,
			@RequestHeader(name = "X-Fingerprint", defaultValue = "") String xFingerprint,
			HttpEntity<String> httpEntity, 
			HttpServletRequest httpServletRequest) {
		
//		String jsonPayload = httpEntity.getBody();
//		
//		MerchantProfile merchantProfile = authoriseSupport.authorise(xAccessKey, xTimestamp, xFingerprint, jsonPayload);
//		BankAccountListRequest bankAccountListRequest = BankAccountListFactory.build(jsonPayload);
//		BankAccountListData bankAccountListData = new BankAccountListData(merchantProfile);
//		
//		requestValidator.validate(bankAccountListRequest, bankAccountListData, httpServletRequest);
//		
//		return bankAccountListAction.list(bankAccountListRequest, bankAccountListData);
		return null;
	}
}