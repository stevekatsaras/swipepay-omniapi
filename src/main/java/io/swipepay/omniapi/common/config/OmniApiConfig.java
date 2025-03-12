package io.swipepay.omniapi.common.config;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kms.AWSKMSClient;
import com.amazonaws.services.kms.AWSKMSClientBuilder;

import io.swipepay.omniapi.common.config.properties.OmniApiAuthorisationProperties;
import io.swipepay.omniapi.common.config.properties.OmniApiCryptoProperties;
import io.swipepay.omniapi.common.config.properties.OmniApiPaymentProperties;

@Configuration
public class OmniApiConfig {
	
	@Bean
	@ConfigurationProperties("omniapi.authorisation")
	@Validated
	public OmniApiAuthorisationProperties omniApiAuthorisationProperties() {
		return new OmniApiAuthorisationProperties();
	}
	
	@Bean
	@ConfigurationProperties("omniapi.crypto")
	@Validated
	public OmniApiCryptoProperties omniApiCryptoProperties() {
		return new OmniApiCryptoProperties();
	}
	
	@Bean
	@ConfigurationProperties("omniapi.payment")
	@Validated
	public OmniApiPaymentProperties omniApiPaymentProperties() {
		return new OmniApiPaymentProperties();
	}
	
	@Bean
	public AWSKMSClient awsKmsClient() {
		OmniApiCryptoProperties omniApiCryptoProperties = omniApiCryptoProperties();
		
		AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(
				new BasicAWSCredentials(
						omniApiCryptoProperties.getAuthAccessKey(), 
						omniApiCryptoProperties.getAuthSecretKey()));
		
		return (AWSKMSClient) AWSKMSClientBuilder
				.standard()
				.withCredentials(credentialsProvider)
				.withRegion(Regions.fromName(omniApiCryptoProperties.getKmsCmkRegion()))
				.build();
	}
	
	@PostConstruct
	public void initialize() {
		System.out.println("Loading: omniapi.authorisation (properties):" + omniApiAuthorisationProperties());
		System.out.println("Loading: omniapi.crypto (properties):" + omniApiCryptoProperties());
		System.out.println("Loading: omniapi.payment (properties):" + omniApiPaymentProperties());
		
		// build the beans
		
		awsKmsClient();
	}
	
}