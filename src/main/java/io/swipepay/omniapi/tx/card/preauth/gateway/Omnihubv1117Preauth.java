package io.swipepay.omniapi.tx.card.preauth.gateway;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swipepay.omniapi.common.entity.merchantprofilepaymentgateway.MerchantProfilePaymentGateway;
import io.swipepay.omniapi.common.entity.paymentgateway.PaymentGateway;
import io.swipepay.omniapi.common.entity.paymentresponsecc.PaymentResponseCc;
import io.swipepay.omniapi.common.entity.paymentresponsecc.PaymentResponseCcRepository;
import io.swipepay.omniapi.common.entity.paymenttxcc.PaymentTxCc;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.support.RestTemplateSupport;
import io.swipepay.omniapi.tx.GatewayException;

@Service
public class Omnihubv1117Preauth {
	
	@Autowired
	private PaymentResponseCcRepository paymentResponseCcRepository;
	
	@Autowired
	private RestTemplateSupport restTemplateSupport;
	
	public void send(
			PaymentTxCc paymentTxCc, 
			MerchantProfilePaymentGateway merchantProfilePaymentGateway) 
					throws GatewayException {
		
		PaymentGateway paymentGateway = merchantProfilePaymentGateway.getPaymentGateway();
		try {
			HttpEntity<LinkedHashMap<String, String>> httpEntity = new HttpEntity<LinkedHashMap<String, String>>(
					buildRequest(paymentTxCc), 
					restTemplateSupport.getHttpHeaders());
			
			RestTemplate restTemplate = restTemplateSupport.buildRestTemplate(
					paymentGateway.getEndpoint(), 
					paymentGateway.getConnectTimeoutMs(), 
					paymentGateway.getReadTimeoutMs(), 
					merchantProfilePaymentGateway.getUsername(), 
					merchantProfilePaymentGateway.getPassword());
			
			String jsonPayload = restTemplate.postForObject("/v1.0/purchases", httpEntity, String.class);
			LinkedHashMap<String, Object> omnihubv117Response = new ObjectMapper().readValue(jsonPayload, new TypeReference<LinkedHashMap<String, Object>>(){});
			
			unmarshall(omnihubv117Response, paymentTxCc, merchantProfilePaymentGateway);
		}
		catch (RestClientException exception) {
			throw new GatewayException(
					Status.RS_3013, 
					"Omnihubv1117 failed with errors while attempting to communicate with gateway", 
					exception);
		}
		catch (Exception exception) {
			throw new GatewayException(
					Status.RS_3012, 
					"Omnihubv1117 failed with errors", 
					exception);
		}
	}
	
	private LinkedHashMap<String, String> buildRequest(PaymentTxCc paymentTxCc) {
		LinkedHashMap<String, String> request =  new LinkedHashMap<String, String>();
		request.put("amount", Long.toString(paymentTxCc.getAmount()));
		request.put("currency", paymentTxCc.getCurrencyCode());
		request.put("reference", paymentTxCc.getReference());
		request.put("customer_ip", paymentTxCc.getClientIp());
		request.put("card_holder", paymentTxCc.getCardName());
		request.put("card_number", paymentTxCc.getCardNumberPlainText());
		request.put("card_expiry", paymentTxCc.getCardExpiryMonth() + "/" + paymentTxCc.getCardExpiryYear());
		
		if (StringUtils.isNotBlank(paymentTxCc.getCardCvv())) {
			request.put("cvv", paymentTxCc.getCardCvv());			
		}
		
		request.put("capture", "false");
		return request;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	private void unmarshall(
			LinkedHashMap<String, Object> omnihubv117Response, 
			PaymentTxCc paymentTxCc, 
			MerchantProfilePaymentGateway merchantProfilePaymentGateway) {
		
		LinkedHashMap<String, Object> response = (LinkedHashMap<String, Object>) omnihubv117Response.get("response");
		PaymentResponseCc paymentResponseCc = paymentResponseCcRepository.findByCode((String) response.get("response_code"));
		
		paymentTxCc.setPaymentGatewayCode(merchantProfilePaymentGateway.getPaymentGateway().getCode());
		paymentTxCc.setTxSuccess((Boolean) response.get("successful"));
		paymentTxCc.setTxResult((paymentTxCc.getTxSuccess() ? "Approved" : "Declined"));
		paymentTxCc.setTxId((String) response.get("transaction_id"));
		paymentTxCc.setTxResponseCode(paymentResponseCc.getCode());
		paymentTxCc.setTxResponseText(paymentResponseCc.getText());
		paymentTxCc.setMerchantProfilePaymentGateway(merchantProfilePaymentGateway);
		paymentTxCc.setPaymentResponseCc(paymentResponseCc);
		
		if (paymentTxCc.getTxSuccess()) {
			paymentTxCc.setTxSettlementDate((String) response.get("settlement_date"));
			paymentTxCc.setTxLogDate(LocalDateTime.parse((String) response.get("transaction_date"), DateTimeFormatter.ISO_OFFSET_DATE_TIME));
		}
		else {
			ArrayList<String> errors = (ArrayList<String>) omnihubv117Response.get("errors");
			paymentTxCc.setTxErrors(errors.toString());
		}
	}
}