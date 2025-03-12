package io.swipepay.omniapi.tx.card.preauth;

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
public class CardPreauthFactory {
	
	public CardPreauthRequest build(String jsonPayload) throws RequestException {
		CardPreauthRequest cardPreauthRequest = null;
		try {
			cardPreauthRequest = new ObjectMapper().readValue(jsonPayload, CardPreauthRequest.class);
			if (cardPreauthRequest == null) {
				throw new RequestException(
						Status.RS_0004, 
						"Deserialization of the JSON payload was successful, yet the CardPreauthRequest object is NULL", 
						null);
			}
		}
		catch (Exception exception) {
			throw new RequestException(
					Status.RS_0004, 
					"Deserialization failed because the JSON payload does not conform to the CardPreauthRequest class", 
					exception);
		}
		return cardPreauthRequest;
	}
	
	public CardPreauthResponse build(PaymentTxCc paymentTxCc) {
		CardPreauthResponse cardPreauthResponse = new CardPreauthResponse(Status.RS_0000);
		cardPreauthResponse.setMerchantProfile(paymentTxCc.getMerchantProfile().getCode());
		cardPreauthResponse.setCode(paymentTxCc.getCode());
		cardPreauthResponse.setReference(paymentTxCc.getReference());
		cardPreauthResponse.setAmount(Long.toString(paymentTxCc.getAmount()));
		cardPreauthResponse.setCurrency(paymentTxCc.getCurrencyCode());
		cardPreauthResponse.setTxType(paymentTxCc.getTxType());
		cardPreauthResponse.setEnvironment(paymentTxCc.getEnvironment());
		cardPreauthResponse.setEvent(paymentTxCc.getEvent());
		cardPreauthResponse.setName(paymentTxCc.getCardName());
		cardPreauthResponse.setEmail(paymentTxCc.getCardEmail());
		cardPreauthResponse.setBin(paymentTxCc.getCardBin());
		cardPreauthResponse.setPan(paymentTxCc.getCardPan());
		cardPreauthResponse.setExpiryMonth(paymentTxCc.getCardExpiryMonth());
		cardPreauthResponse.setExpiryYear(paymentTxCc.getCardExpiryYear());
		cardPreauthResponse.setCaptured(paymentTxCc.getCaptured());
		cardPreauthResponse.setCapturedAmount(Long.toString(paymentTxCc.getCapturedAmountTotal()));
		cardPreauthResponse.setRefunded(paymentTxCc.getRefunded());
		cardPreauthResponse.setRefundedAmount(Long.toString(paymentTxCc.getRefundedAmountTotal()));
		cardPreauthResponse.setReceipt(paymentTxCc.getReceiptNo());
		cardPreauthResponse.setResult(paymentTxCc.getTxResult());
		cardPreauthResponse.setResponseCode(paymentTxCc.getTxResponseCode());
		cardPreauthResponse.setResponseText(paymentTxCc.getTxResponseText());
		cardPreauthResponse.setSettlementDate(paymentTxCc.getTxSettlementDate());
		cardPreauthResponse.setLogDate(paymentTxCc.getTxLogDate());
		cardPreauthResponse.setMetadata(build(paymentTxCc.getPaymentTxCcMetadata()));
		cardPreauthResponse.setErrors(paymentTxCc.getTxErrors());
		return cardPreauthResponse;
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