package io.swipepay.omniapi.payment.action.creditcard.gateway.omnihubv1;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import io.swipepay.omniapi.common.entity.merchantprofilepaymentgateway.MerchantProfilePaymentGateway;
import io.swipepay.omniapi.common.entity.paymenttxcc.PaymentTxCc;
import io.swipepay.omniapi.common.enums.RelatedPayment;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.payment.PaymentData;
import io.swipepay.omniapi.payment.action.creditcard.gateway.omnihubv1.payload.OmnihubV1Request;
import io.swipepay.omniapi.payment.action.creditcard.gateway.omnihubv1.payload.OmnihubV1Response;
import io.swipepay.omniapi.payment.payload.PaymentRequest;
import io.swipepay.omniapi.tx.GatewayException;

@Service
public class OmnihubV1CaptureGateway extends OmnihubV1Gateway {

	@Override
	public void send(
			MerchantProfilePaymentGateway merchantProfilePaymentGateway, 
			PaymentRequest paymentRequest,
			PaymentData paymentData) 
					throws GatewayException {
//		try {
//			OmnihubV1Request omnihubV1Request = buildRequest(paymentRequest, paymentData);
//			HttpEntity<OmnihubV1Request> httpEntity = new HttpEntity<OmnihubV1Request>(omnihubV1Request, getHttpHeaders());
//			
//			String gwPreauthTxId = paymentData
//					.getRelatedPaymentData()
//					.get(RelatedPayment.purchase.name())
//					.getPaymentCc()
//					.getTxId();
//			
//			RestTemplate restTemplate = buildRestTemplate(merchantProfilePaymentGateway);
//			OmnihubV1Response omnihubV1Response = restTemplate.postForObject("/v1.0/purchases/" + gwPreauthTxId + "/capture", httpEntity, OmnihubV1Response.class);
//			unmarshalResponse(omnihubV1Response, paymentRequest, paymentData);
//		}
//		catch (RestClientException exception) {
//			throw new GatewayException(
//				Status.RS_0043, 
//				"OmnihubV1Gateway failed with errors while attempting to communicate with gateway", 
//				exception);
//		}
//		catch (Exception exception) {
//			throw new GatewayException(
//				Status.RS_0042, 
//				"OmnihubV1Gateway failed with errors", 
//				exception);
//		}
	}
	
	private OmnihubV1Request buildRequest(PaymentRequest paymentRequest, PaymentData paymentData) {
//		PaymentTxCc paymentCc = paymentData.getPaymentCc();
//		
//		OmnihubV1Request omnihubV1Request = new OmnihubV1Request();
//		omnihubV1Request.setAmount(Long.toString(paymentCc.getAmount()));
//		return omnihubV1Request;
		return null;
	}
}