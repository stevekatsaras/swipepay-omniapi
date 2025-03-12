package io.swipepay.omniapi.common.support;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class NetSupport {
	
	public String getClientIpAddress(HttpServletRequest httpServletRequest) {
		String clientIpAddress = httpServletRequest.getHeader("X-Forwarded-For");
		if (StringUtils.isBlank(clientIpAddress)) {
			clientIpAddress = httpServletRequest.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(clientIpAddress)) {
			clientIpAddress = httpServletRequest.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(clientIpAddress)) {
			clientIpAddress = httpServletRequest.getHeader("HTTP_CLIENT_IP");
		}
		if (StringUtils.isBlank(clientIpAddress)) {
			clientIpAddress = httpServletRequest.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StringUtils.isBlank(clientIpAddress)) {
			clientIpAddress = httpServletRequest.getRemoteAddr();
		}
		return clientIpAddress;
	}
}