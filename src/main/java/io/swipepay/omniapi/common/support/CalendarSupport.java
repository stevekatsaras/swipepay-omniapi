package io.swipepay.omniapi.common.support;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class CalendarSupport {
	
	public String format(LocalDateTime localDateTime, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return localDateTime.format(formatter);
	}
}