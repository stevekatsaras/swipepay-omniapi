package io.swipepay.omniapi.payment;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.support.AuthoriseSupport;
import io.swipepay.omniapi.common.validator.RequestValidator;
import io.swipepay.omniapi.payment.payload.PaymentRequest;
import io.swipepay.omniapi.payment.payload.PaymentFactory;
import io.swipepay.omniapi.payment.payload.PaymentResponse;

@RestController
public class PaymentController {
	
	@Autowired
	private AuthoriseSupport authoriseSupport;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private PaymentValidator paymentValidator;
	
	@Autowired
	private RequestValidator requestValidator;
	
	@RequestMapping(
			value = "/payment/go", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public PaymentResponse go(
			@RequestHeader(name = "X-Access-Key", defaultValue = "") String xAccessKey,
			@RequestHeader(name = "X-Timestamp", defaultValue = "") String xTimestamp,
			@RequestHeader(name = "X-Fingerprint", defaultValue = "") String xFingerprint,
			HttpEntity<String> httpEntity, 
			HttpServletRequest httpServletRequest) {
		
		String jsonPayload = httpEntity.getBody();
		
		System.out.println("accessKey:" + xAccessKey);
		System.out.println("timestamp:" + xTimestamp);
		System.out.println("fingerprint:" + xFingerprint);
		System.out.println("jsonpayload:" + jsonPayload);
		
//		MerchantProfile merchantProfile = authoriseSupport.authorise(xAccessKey, xTimestamp, xFingerprint, jsonPayload);
//		PaymentRequest paymentRequest = PaymentFactory.buildRequest(jsonPayload);
//		
//		PaymentData paymentData = new PaymentData();
//		paymentData.setMerchantProfile(merchantProfile);
//		paymentData.setMerchant(merchantProfile.getMerchant());
//		
//		requestValidator.validate(paymentRequest, paymentData, httpServletRequest);
//		
//		paymentValidator.validate(paymentRequest, paymentData);
//		PaymentResponse paymentResponse = paymentService.go(paymentRequest, paymentData);
//		
//		return paymentResponse;
		return null;
	}
}