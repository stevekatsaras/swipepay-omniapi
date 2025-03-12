package io.swipepay.omniapi.common.support;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateSupport {
	
	public HttpHeaders getHttpHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return httpHeaders;
	}
	
	public RestTemplate buildRestTemplate(
			String rootUri, 
			String connectTimeout, 
			String readTimeout, 
			String username, 
			String password) throws Exception {
		
		RestTemplate restTemplate = new RestTemplateBuilder()
			.rootUri(rootUri)
			.setConnectTimeout(Integer.parseInt(connectTimeout))
			.setReadTimeout(Integer.parseInt(readTimeout))
			.basicAuthorization(username, password)
			.build();
	
		return restTemplate;
	}
}