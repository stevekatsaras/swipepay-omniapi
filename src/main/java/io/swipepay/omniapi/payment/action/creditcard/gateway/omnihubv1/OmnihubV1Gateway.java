package io.swipepay.omniapi.payment.action.creditcard.gateway.omnihubv1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import io.swipepay.omniapi.common.entity.merchantprofilepaymentgateway.MerchantProfilePaymentGateway;
import io.swipepay.omniapi.common.entity.paymentgateway.PaymentGateway;
import io.swipepay.omniapi.common.entity.paymentresponsecc.PaymentResponseCc;
import io.swipepay.omniapi.common.entity.paymentresponsecc.PaymentResponseCcRepository;
import io.swipepay.omniapi.common.entity.paymenttxcc.PaymentTxCc;
import io.swipepay.omniapi.common.enums.RelatedPayment;
import io.swipepay.omniapi.payment.PaymentData;
import io.swipepay.omniapi.payment.action.creditcard.gateway.omnihubv1.payload.OmnihubV1Response;
import io.swipepay.omniapi.payment.action.creditcard.gateway.omnihubv1.payload.dto.ResponseDto;
import io.swipepay.omniapi.payment.payload.PaymentRequest;
import io.swipepay.omniapi.tx.GatewayException;

public abstract class OmnihubV1Gateway {
	
	@Autowired
	private PaymentResponseCcRepository paymentResponseRepository;
	
	public abstract void send(
			MerchantProfilePaymentGateway merchantProfilePaymentGateway,
			PaymentRequest paymentRequest, 
			PaymentData paymentData) 
					throws GatewayException;
	
	public HttpHeaders getHttpHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return httpHeaders;
	}
	
	public RestTemplate buildRestTemplate(MerchantProfilePaymentGateway merchantProfilePaymentGateway) throws Exception {
		PaymentGateway paymentGateway = merchantProfilePaymentGateway.getPaymentGateway();
		
		String rootUri = paymentGateway.getEndpoint();
		String connectTimeout = paymentGateway.getConnectTimeoutMs();
		String readTimeout = paymentGateway.getReadTimeoutMs();
		String username = merchantProfilePaymentGateway.getUsername();
		String password = merchantProfilePaymentGateway.getPassword();

		RestTemplate restTemplate = new RestTemplateBuilder()
			.rootUri(rootUri)
			.setConnectTimeout(Integer.parseInt(connectTimeout))
			.setReadTimeout(Integer.parseInt(readTimeout))
			.basicAuthorization(username, password)
			.build();
	
		return restTemplate;
	}
	
	@Transactional(readOnly = true)
	public void unmarshalResponse(OmnihubV1Response omnihubV1Response, PaymentRequest paymentRequest, PaymentData paymentData) {
//		ResponseDto responseDto = omnihubV1Response.getResponse();
//		
//		PaymentResponseCc paymentResponse = paymentResponseRepository.findByCodeAndPaymentMethod(
//				responseDto.getResponseCode(), 
//				paymentData.getPaymentMethod());
//		
//		PaymentTxCc paymentCc = paymentData.getPaymentCc();
//		paymentCc.setTxSuccess(responseDto.getSuccessful());
//		paymentCc.setTxResult((paymentCc.getTxSuccess() ? "Approved" : "Declined"));
//		paymentCc.setTxId(responseDto.getTransactionId());
//		paymentCc.setTxResponseCode(paymentResponse.getCode());
//		paymentCc.setTxResponseText(paymentResponse.getText());
//		paymentCc.setPaymentResponse(paymentResponse);
//		
//		// if successful, get the settlement/log dates
//		
//		if (paymentCc.getTxSuccess()) {
//			paymentCc.setTxSettlementDate(responseDto.getSettlementDate());
//			paymentCc.setTxLogDate(LocalDateTime.parse(responseDto.getTransactionDate(), DateTimeFormatter.ISO_OFFSET_DATE_TIME));
//			
//			if (StringUtils.equals(paymentCc.getPaymentTypeCode(), PaymentTypeCode.capture.name()) || 
//					StringUtils.equals(paymentCc.getPaymentTypeCode(), PaymentTypeCode.refund.name())) {
//				
//				PaymentTxCc relatedPaymentCc = paymentData
//						.getRelatedPaymentData()
//						.get(RelatedPayment.purchase.name())
//						.getPaymentCc();
//				
//				relatedPaymentCc.setTxSettlementDate(responseDto.getSettlementDate());
//			}
//		}
//		
//		// if not successful, get the errors
//		
//		else {
//			paymentCc.setTxErrors(omnihubV1Response.getErrors().toString());
//		}
	}
	
}