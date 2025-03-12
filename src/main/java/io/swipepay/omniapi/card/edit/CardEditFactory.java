package io.swipepay.omniapi.card.edit;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swipepay.omniapi.common.entity.paymentcard.PaymentCard;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.RequestException;

public class CardEditFactory {
	
	public static CardEditRequest build(String jsonPayload) throws RequestException {
		CardEditRequest cardEditRequest = null;
		try {
			cardEditRequest = new ObjectMapper().readValue(jsonPayload, CardEditRequest.class);
			if (cardEditRequest == null) {
				throw new RequestException(
						Status.RS_0004, 
						"Deserialization of the JSON payload was successful, yet the CardEditRequest object is NULL", 
						null);
			}
		}
		catch (Exception exception) {
			throw new RequestException(
					Status.RS_0004, 
					"Deserialization failed because the JSON payload does not conform to the CardEditRequest class", 
					exception);
		}
		return cardEditRequest;
	}
	
	public static CardEditResponse build(PaymentCard paymentCard) {
		CardEditResponse cardEditResponse = new CardEditResponse(Status.RS_0000);
		cardEditResponse.setMerchantProfile(paymentCard.getMerchantProfile().getCode());
		cardEditResponse.setCode(paymentCard.getCode());
		cardEditResponse.setName(paymentCard.getCardName());
		cardEditResponse.setEmail(paymentCard.getCardEmail());
		cardEditResponse.setBin(paymentCard.getCardBin());
		cardEditResponse.setPan(paymentCard.getCardPan());
		cardEditResponse.setExpiryMonth(paymentCard.getCardExpiryMonth());
		cardEditResponse.setExpiryYear(paymentCard.getCardExpiryYear());
		cardEditResponse.setEnabled(paymentCard.getEnabled());
		cardEditResponse.setModified(paymentCard.getModified());
		return cardEditResponse;
	}
}