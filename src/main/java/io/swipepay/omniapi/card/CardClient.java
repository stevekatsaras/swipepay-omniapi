package io.swipepay.omniapi.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.swipepay.omniapi.card.add.CardAddAction;
import io.swipepay.omniapi.card.add.CardAddRequest;
import io.swipepay.omniapi.card.add.CardAddResponse;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.paymentcard.PaymentCard;
import io.swipepay.omniapi.common.exception.ValidationException;

@Component
public class CardClient {
	
	@Autowired
	private CardAddAction cardAddAction;
	
	public CardAddResponse addCard(
			MerchantProfile merchantProfile, 
			String name, 
			String email, 
			String pan, 
			String expiryMonth, 
			String expiryYear, 
			String cvv) 
					throws ValidationException, CardException {
		
		CardAddRequest cardAddRequest = new CardAddRequest();
		cardAddRequest.setMerchantProfile(merchantProfile.getCode());
		cardAddRequest.setName(name);
		cardAddRequest.setEmail(email);
		cardAddRequest.setPan(pan);
		cardAddRequest.setExpiryMonth(expiryMonth);
		cardAddRequest.setExpiryYear(expiryYear);
		cardAddRequest.setCvv(cvv);
		return cardAddAction.add(cardAddRequest, new PaymentCard(merchantProfile), "127.0.0.1");
	}
}