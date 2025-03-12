package io.swipepay.omniapi.common.support;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class FingerprintSupport {
	
	private enum Algorithm {
		HmacSHA256;
	}
	
	public String calculate(String privateKey, String requestToSign) throws Exception {
		SecretKeySpec secretKey = new SecretKeySpec(privateKey.getBytes(), Algorithm.HmacSHA256.name());
		Mac mac = Mac.getInstance(Algorithm.HmacSHA256.name());
		mac.init(secretKey);
		return Base64.encodeBase64String(mac.doFinal(requestToSign.getBytes()));
	}
}