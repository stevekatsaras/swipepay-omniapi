package io.swipepay.omniapi.card.get;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.swipepay.omniapi.card.CardException;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.paymentcard.PaymentCard;
import io.swipepay.omniapi.common.entity.paymentcard.PaymentCardRepository;
import io.swipepay.omniapi.common.exception.ValidationException;

@Service
public class CardGetAction {
	
	@Autowired
	private CardGetValidator cardGetValidator;
	
	@Autowired
	private PaymentCardRepository paymentCardRepository;
	
	public CardGetResponse get(
			CardGetRequest cardGetRequest, 
			PaymentCard paymentCard, 
			String clientIpAddress) 
					throws ValidationException, CardException {
		
		paymentCard = cardGetValidator.validate(cardGetRequest, paymentCard, clientIpAddress);
		return CardGetFactory.build(paymentCard);
	}
	
	@Transactional(readOnly = true)
	private PaymentCard getPaymentCard(String paymentCardCode, MerchantProfile merchantProfile) {
		return paymentCardRepository.findByCodeAndMerchantProfile(
				paymentCardCode, 
				merchantProfile);
	}
	
}