package io.swipepay.omniapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OmniApiApplication {
	
	/**
	 * TODO - the following items from the omnihub DB schema will need editing:
	 * 1. SwipePay data-key (that was generated from the Ohio CMK) - in prod this needs to change
	 * 2. Omnihubv1 endpoint (points to FZ sandbox) - in prod this needs to change
	 */
	
	public static void main(String[] args) {
		SpringApplication.run(OmniApiApplication.class, args);
	}
}