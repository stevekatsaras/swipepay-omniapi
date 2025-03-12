package io.swipepay.omniapi.card.get;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swipepay.omniapi.common.entity.paymentcard.PaymentCard;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.RequestException;

public class CardGetFactory {
	
	public static CardGetRequest build(String jsonPayload) throws RequestException {
		CardGetRequest cardGetRequest = null;
		try {
			cardGetRequest = new ObjectMapper().readValue(jsonPayload, CardGetRequest.class);
			if (cardGetRequest == null) {
				throw new RequestException(
						Status.RS_0004, 
						"Deserialization of the JSON payload was successful, yet the CardGetRequest object is NULL", 
						null);
			}
		}
		catch (Exception exception) {
			throw new RequestException(
					Status.RS_0004, 
					"Deserialization failed because the JSON payload does not conform to the CardGetRequest class", 
					exception);
		}
		return cardGetRequest;
	}
	
	public static CardGetResponse build(PaymentCard paymentCard) {
		CardGetResponse cardGetResponse = new CardGetResponse(Status.RS_0000);
		cardGetResponse.setMerchantProfile(paymentCard.getMerchantProfile().getCode());
		cardGetResponse.setCode(paymentCard.getCode());
		cardGetResponse.setName(paymentCard.getCardName());
		cardGetResponse.setEmail(paymentCard.getCardEmail());
		cardGetResponse.setBin(paymentCard.getCardBin());
		cardGetResponse.setPan(paymentCard.getCardPan());
		cardGetResponse.setExpiryMonth(paymentCard.getCardExpiryMonth());
		cardGetResponse.setExpiryYear(paymentCard.getCardExpiryYear());
		cardGetResponse.setEnabled(paymentCard.getEnabled());
		cardGetResponse.setModified(paymentCard.getModified());
		return cardGetResponse;
	}
}