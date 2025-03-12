package io.swipepay.omniapi.card.list;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.paymentcard.PaymentCard;
import io.swipepay.omniapi.common.support.AuthoriseSupport;
import io.swipepay.omniapi.common.support.NetSupport;

@RestController
public class CardListController {
	
	@Autowired
	private AuthoriseSupport authoriseSupport;
	
	@Autowired
	private CardListAction cardListAction;
	
	@Autowired
	private NetSupport netSupport;
	
	@PostMapping(value = "/card/list", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CardListResponse list(
			@RequestHeader(name = "X-Access-Key", defaultValue = "") String xAccessKey,
			@RequestHeader(name = "X-Timestamp", defaultValue = "") String xTimestamp,
			@RequestHeader(name = "X-Fingerprint", defaultValue = "") String xFingerprint,
			HttpEntity<String> httpEntity, 
			HttpServletRequest httpServletRequest) {
		
		String jsonPayload = httpEntity.getBody();
		
		MerchantProfile merchantProfile = authoriseSupport.authorise(xAccessKey, xTimestamp, xFingerprint, jsonPayload);
		CardListRequest cardListRequest = CardListFactory.build(jsonPayload);
		return cardListAction.list(
				cardListRequest, 
				new PaymentCard(merchantProfile), 
				netSupport.getClientIpAddress(httpServletRequest));
	}
}