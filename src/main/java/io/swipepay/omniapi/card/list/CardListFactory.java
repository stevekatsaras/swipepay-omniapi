package io.swipepay.omniapi.card.list;

import java.util.LinkedList;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.paymentcard.PaymentCard;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.RequestException;

public class CardListFactory {
	
	public static CardListRequest build(String jsonPayload) throws RequestException {
		CardListRequest cardListRequest = null;
		try {
			cardListRequest = new ObjectMapper().readValue(jsonPayload, CardListRequest.class);
			if (cardListRequest == null) {
				throw new RequestException(
						Status.RS_0004, 
						"Deserialization of the JSON payload was successful, yet the CardListRequest object is NULL", 
						null);
			}
		}
		catch (Exception exception) {
			throw new RequestException(
					Status.RS_0004, 
					"Deserialization failed because the JSON payload does not conform to the CardListRequest class", 
					exception);
		}
		return cardListRequest;
	}
	
	public static CardListResponse build(MerchantProfile merchantProfile, LinkedList<PaymentCard> paymentCards) {
		LinkedList<CardListResponseItem> cards = new LinkedList<CardListResponseItem>();
		
		for (PaymentCard paymentCard : paymentCards) {
			CardListResponseItem item = new CardListResponseItem();
			item.setCode(paymentCard.getCode());
			item.setName(paymentCard.getCardName());
			item.setEmail(paymentCard.getCardEmail());
			item.setBin(paymentCard.getCardBin());
			item.setPan(paymentCard.getCardPan());
			item.setExpiryMonth(paymentCard.getCardExpiryMonth());
			item.setExpiryYear(paymentCard.getCardExpiryYear());
			item.setEnabled(paymentCard.getEnabled());
			item.setModified(paymentCard.getModified());
			cards.add(item);
		}		
		CardListResponse cardListResponse = new CardListResponse(Status.RS_0000);
		cardListResponse.setMerchantProfile(merchantProfile.getCode());
		cardListResponse.setCards(cards);
		return cardListResponse;
	}
}