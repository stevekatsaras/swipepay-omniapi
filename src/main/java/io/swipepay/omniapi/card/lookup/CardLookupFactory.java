package io.swipepay.omniapi.card.lookup;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swipepay.omniapi.common.entity.paymentcard.PaymentCard;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.RequestException;

public class CardLookupFactory {
	
	public static CardLookupRequest build(String jsonPayload) throws RequestException {
		CardLookupRequest cardLookupRequest = null;
		try {
			cardLookupRequest = new ObjectMapper().readValue(jsonPayload, CardLookupRequest.class);
			if (cardLookupRequest == null) {
				throw new RequestException(
						Status.RS_0004, 
						"Deserialization of the JSON payload was successful, yet the CardLookupRequest object is NULL", 
						null);
			}
		}
		catch (Exception exception) {
			throw new RequestException(
					Status.RS_0004, 
					"Deserialization failed because the JSON payload does not conform to the CardLookupRequest class", 
					exception);
		}
		return cardLookupRequest;
	}
	
	public static CardLookupResponse build(PaymentCard paymentCard) {
		CardLookupResponse cardLookupResponse = new CardLookupResponse(Status.RS_0000);
		cardLookupResponse.setMerchantProfile(paymentCard.getMerchantProfile().getCode());
		cardLookupResponse.setCode(paymentCard.getCode());
		cardLookupResponse.setName(paymentCard.getCardName());
		cardLookupResponse.setEmail(paymentCard.getCardEmail());
		cardLookupResponse.setBin(paymentCard.getCardBin());
		cardLookupResponse.setPan(paymentCard.getCardPan());
		cardLookupResponse.setExpiryMonth(paymentCard.getCardExpiryMonth());
		cardLookupResponse.setExpiryYear(paymentCard.getCardExpiryYear());
		cardLookupResponse.setEnabled(paymentCard.getEnabled());
		cardLookupResponse.setModified(paymentCard.getModified());
		return cardLookupResponse;
	}
}