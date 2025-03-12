package io.swipepay.omniapi.tx.card.query;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swipepay.omniapi.common.entity.paymenttxcc.PaymentTxCc;
import io.swipepay.omniapi.common.entity.paymenttxccmetadata.PaymentTxCcMetadata;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.RequestException;

@Component
public class CardQueryFactory {
	
	public CardQueryRequest build(String jsonPayload) throws RequestException {
		CardQueryRequest cardQueryRequest = null;
		try {
			cardQueryRequest = new ObjectMapper().readValue(jsonPayload, CardQueryRequest.class);
			if (cardQueryRequest == null) {
				throw new RequestException(
						Status.RS_0004, 
						"Deserialization of the JSON payload was successful, yet the CardQueryRequest object is NULL", 
						null);
			}
		}
		catch (Exception exception) {
			throw new RequestException(
					Status.RS_0004, 
					"Deserialization failed because the JSON payload does not conform to the CardQueryRequest class", 
					exception);
		}
		return cardQueryRequest;
	}
	
	public CardQueryResponse build(PaymentTxCc paymentTxCc) {
		CardQueryResponse cardQueryResponse = new CardQueryResponse(Status.RS_0000);
		cardQueryResponse.setMerchantProfile(paymentTxCc.getMerchantProfile().getCode());
		cardQueryResponse.setCode(paymentTxCc.getCode());
		cardQueryResponse.setReference(paymentTxCc.getReference());
		cardQueryResponse.setAmount(Long.toString(paymentTxCc.getAmount()));
		cardQueryResponse.setCurrency(paymentTxCc.getCurrencyCode());
		cardQueryResponse.setTxType(paymentTxCc.getTxType());
		cardQueryResponse.setEnvironment(paymentTxCc.getEnvironment());
		cardQueryResponse.setEvent(paymentTxCc.getEvent());
		cardQueryResponse.setName(paymentTxCc.getCardName());
		cardQueryResponse.setEmail(paymentTxCc.getCardEmail());
		cardQueryResponse.setBin(paymentTxCc.getCardBin());
		cardQueryResponse.setPan(paymentTxCc.getCardPan());
		cardQueryResponse.setExpiryMonth(paymentTxCc.getCardExpiryMonth());
		cardQueryResponse.setExpiryYear(paymentTxCc.getCardExpiryYear());
		
		if (StringUtils.equals(paymentTxCc.getTxType(), "payment") || 
				StringUtils.equals(paymentTxCc.getTxType(), "preauth")) {
			
			cardQueryResponse.setCaptured(paymentTxCc.getCaptured());
			cardQueryResponse.setCapturedAmount(Long.toString(paymentTxCc.getCapturedAmountTotal()));		
			cardQueryResponse.setRefunded(paymentTxCc.getRefunded());
			cardQueryResponse.setRefundedAmount(Long.toString(paymentTxCc.getRefundedAmountTotal()));
		}
		
		cardQueryResponse.setReceipt(paymentTxCc.getReceiptNo());
		cardQueryResponse.setResult(paymentTxCc.getTxResult());
		cardQueryResponse.setResponseCode(paymentTxCc.getTxResponseCode());
		cardQueryResponse.setResponseText(paymentTxCc.getTxResponseText());
		cardQueryResponse.setSettlementDate(paymentTxCc.getTxSettlementDate());
		cardQueryResponse.setLogDate(paymentTxCc.getTxLogDate());
		cardQueryResponse.setMetadata(build(paymentTxCc.getPaymentTxCcMetadata()));
		cardQueryResponse.setErrors(paymentTxCc.getTxErrors());
		return cardQueryResponse;
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