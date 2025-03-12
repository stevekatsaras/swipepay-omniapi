package io.swipepay.omniapi.tx.card.payment;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.paymenttxcc.PaymentTxCc;
import io.swipepay.omniapi.common.support.AuthoriseSupport;
import io.swipepay.omniapi.common.support.NetSupport;

@RestController
public class CardPaymentController {
	
	@Autowired
	private AuthoriseSupport authoriseSupport;
	
	@Autowired
	private CardPaymentFactory cardPaymentFactory;
	
	@Autowired
	private CardPaymentService cardPaymentService;
	
	@Autowired
	private NetSupport netSupport;
	
	@PostMapping(
			value = "/tx/card/payment", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public CardPaymentResponse payment(
			@RequestHeader(name = "X-Access-Key", defaultValue = "") String xAccessKey,
			@RequestHeader(name = "X-Timestamp", defaultValue = "") String xTimestamp,
			@RequestHeader(name = "X-Fingerprint", defaultValue = "") String xFingerprint,
			HttpEntity<String> httpEntity, 
			HttpServletRequest httpServletRequest) {
		
		String jsonPayload = httpEntity.getBody();
		MerchantProfile merchantProfile = authoriseSupport.authorise(xAccessKey, xTimestamp, xFingerprint, jsonPayload);
		
		CardPaymentRequest cardPaymentRequest = cardPaymentFactory.build(jsonPayload);
		return cardPaymentService.payment(
				cardPaymentRequest, 
				new PaymentTxCc(merchantProfile), 
				netSupport.getClientIpAddress(httpServletRequest));
	}
}