package io.swipepay.omniapi.tx.card.payment;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swipepay.omniapi.common.entity.paymenttxcc.PaymentTxCc;
import io.swipepay.omniapi.common.entity.paymenttxccmetadata.PaymentTxCcMetadata;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.RequestException;

@Component
public class CardPaymentFactory {
	
	public CardPaymentRequest build(String jsonPayload) throws RequestException {
		CardPaymentRequest cardPaymentRequest = null;
		try {
			cardPaymentRequest = new ObjectMapper().readValue(jsonPayload, CardPaymentRequest.class);
			if (cardPaymentRequest == null) {
				throw new RequestException(
						Status.RS_0004, 
						"Deserialization of the JSON payload was successful, yet the CardPaymentRequest object is NULL", 
						null);
			}
		}
		catch (Exception exception) {
			throw new RequestException(
					Status.RS_0004, 
					"Deserialization failed because the JSON payload does not conform to the CardPaymentRequest class", 
					exception);
		}
		return cardPaymentRequest;
	}
	
	public CardPaymentResponse build(PaymentTxCc paymentTxCc) {
		CardPaymentResponse cardPaymentResponse = new CardPaymentResponse(Status.RS_0000);
		cardPaymentResponse.setMerchantProfile(paymentTxCc.getMerchantProfile().getCode());
		cardPaymentResponse.setCode(paymentTxCc.getCode());
		cardPaymentResponse.setReference(paymentTxCc.getReference());
		cardPaymentResponse.setAmount(Long.toString(paymentTxCc.getAmount()));
		cardPaymentResponse.setCurrency(paymentTxCc.getCurrencyCode());
		cardPaymentResponse.setTxType(paymentTxCc.getTxType());
		cardPaymentResponse.setEnvironment(paymentTxCc.getEnvironment());
		cardPaymentResponse.setEvent(paymentTxCc.getEvent());
		cardPaymentResponse.setName(paymentTxCc.getCardName());
		cardPaymentResponse.setEmail(paymentTxCc.getCardEmail());
		cardPaymentResponse.setBin(paymentTxCc.getCardBin());
		cardPaymentResponse.setPan(paymentTxCc.getCardPan());
		cardPaymentResponse.setExpiryMonth(paymentTxCc.getCardExpiryMonth());
		cardPaymentResponse.setExpiryYear(paymentTxCc.getCardExpiryYear());
		cardPaymentResponse.setCaptured(paymentTxCc.getCaptured());
		cardPaymentResponse.setCapturedAmount(Long.toString(paymentTxCc.getCapturedAmountTotal()));
		cardPaymentResponse.setRefunded(paymentTxCc.getRefunded());
		cardPaymentResponse.setRefundedAmount(Long.toString(paymentTxCc.getRefundedAmountTotal()));
		cardPaymentResponse.setReceipt(paymentTxCc.getReceiptNo());
		cardPaymentResponse.setResult(paymentTxCc.getTxResult());
		cardPaymentResponse.setResponseCode(paymentTxCc.getTxResponseCode());
		cardPaymentResponse.setResponseText(paymentTxCc.getTxResponseText());
		cardPaymentResponse.setSettlementDate(paymentTxCc.getTxSettlementDate());
		cardPaymentResponse.setLogDate(paymentTxCc.getTxLogDate());
		cardPaymentResponse.setMetadata(build(paymentTxCc.getPaymentTxCcMetadata()));
		cardPaymentResponse.setErrors(paymentTxCc.getTxErrors());
		return cardPaymentResponse;
	}
	
	private LinkedHashMap<String, String> build(List<PaymentTxCcMetadata> paymentTxCcMetadata) {
		LinkedHashMap<String, String> metadata = null;
		
		if (CollectionUtils.isNotEmpty(paymentTxCcMetadata)) {
			metadata = new LinkedHashMap<String, String>();
			
			for(PaymentTxCcMetadata nvp : paymentTxCcMetadata) {
				metadata.put(nvp.getMdKey(), nvp.getMdValue());
			}
		}
		return metadata;
	}
}