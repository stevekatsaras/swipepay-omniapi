package io.swipepay.omniapi.batch;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swipepay.omniapi.batch.payload.BatchFactory;
import io.swipepay.omniapi.batch.payload.BatchRequest;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.support.AuthoriseSupport;

@RestController
public class BatchController {
	
	@Autowired
	private AuthoriseSupport authoriseSupport;
	
	@RequestMapping(
			value = "/batch/go/{batchFilename}", 
			method = RequestMethod.POST, 
			consumes = "text/csv",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public void go(
			@RequestHeader(name = "X-Access-Key", defaultValue = "") String xAccessKey,
			@RequestHeader(name = "X-Timestamp", defaultValue = "") String xTimestamp,
			@RequestHeader(name = "X-Fingerprint", defaultValue = "") String xFingerprint,
			@PathVariable(name = "batchFilename") String batchFilename,
			HttpEntity<String> httpEntity, 
			HttpServletRequest httpServletRequest) {
		
		String csvPayload = httpEntity.getBody();
		
		System.out.println("accessKey:" + xAccessKey);
		System.out.println("timestamp:" + xTimestamp);
		System.out.println("fingerprint:" + xFingerprint);
		System.out.println("batchFilename:" + batchFilename);
		System.out.println("csvpayload:" + csvPayload);
		
		MerchantProfile merchantProfile = authoriseSupport.authorise(xAccessKey, xTimestamp, xFingerprint, csvPayload);
		
		BatchRequest batchRequest = BatchFactory.buildRequest(csvPayload);
		
		
		
		
		
		
		
	}
}