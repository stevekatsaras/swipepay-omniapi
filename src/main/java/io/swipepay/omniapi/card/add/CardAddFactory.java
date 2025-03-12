package io.swipepay.omniapi.card.add;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swipepay.omniapi.common.entity.paymentcard.PaymentCard;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.RequestException;

public class CardAddFactory {
	
	public static CardAddRequest build(String jsonPayload) throws RequestException {
		CardAddRequest cardAddRequest = null;
		try {
			cardAddRequest = new ObjectMapper().readValue(jsonPayload, CardAddRequest.class);
			if (cardAddRequest == null) {
				throw new RequestException(
						Status.RS_0004, 
						"Deserialization of the JSON payload was successful, yet the CardAddRequest object is NULL", 
						null);
			}
		}
		catch (Exception exception) {
			throw new RequestException(
					Status.RS_0004, 
					"Deserialization failed because the JSON payload does not conform to the CardAddRequest class", 
					exception);
		}
		return cardAddRequest;
	}
	
	public static CardAddResponse build(PaymentCard paymentCard) {
		CardAddResponse cardAddResponse = new CardAddResponse(Status.RS_0000);
		cardAddResponse.setMerchantProfile(paymentCard.getMerchantProfile().getCode());
		cardAddResponse.setCode(paymentCard.getCode());
		cardAddResponse.setName(paymentCard.getCardName());
		cardAddResponse.setEmail(paymentCard.getCardEmail());
		cardAddResponse.setBin(paymentCard.getCardBin());
		cardAddResponse.setPan(paymentCard.getCardPan());
		cardAddResponse.setExpiryMonth(paymentCard.getCardExpiryMonth());
		cardAddResponse.setExpiryYear(paymentCard.getCardExpiryYear());
		cardAddResponse.setEnabled(paymentCard.getEnabled());
		cardAddResponse.setModified(paymentCard.getModified());
		return cardAddResponse;
	}
}