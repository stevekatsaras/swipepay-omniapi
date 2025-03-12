package io.swipepay.omniapi.card.lookup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.swipepay.omniapi.card.CardException;
import io.swipepay.omniapi.common.entity.paymentcard.PaymentCard;
import io.swipepay.omniapi.common.entity.paymentcard.PaymentCardRepository;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.ValidationException;

@Service
public class CardLookupAction {
	
	@Autowired
	private CardLookupValidator cardLookupValidator;
	
	@Autowired
	private PaymentCardRepository paymentCardRepository;
	
	public CardLookupResponse lookup(
			CardLookupRequest cardLookupRequest, 
			PaymentCard paymentCard, 
			String clientIpAddress) 
					throws ValidationException, CardException {
		
		cardLookupValidator.validate(cardLookupRequest, paymentCard, clientIpAddress);
		try {
			PaymentCard tokenisedPaymentCard = lookupCard(paymentCard);
			if (tokenisedPaymentCard != null) {
				paymentCard = tokenisedPaymentCard;
			}
			else {
				throw new CardException(
						Status.RS_2013, 
						"Card request failed because no payment card could be found", 
						null);				
			}
		}
		catch (CardException exception) {
			throw exception;
		}
		catch (Exception exception) {
			throw new CardException(
				Status.RS_0024, 
				"Card request failed because of an internal system error", 
				exception);
		}
		return CardLookupFactory.build(paymentCard);
	}
	
	@Transactional(readOnly = true)
	private PaymentCard lookupCard(PaymentCard paymentCard) {
		return paymentCardRepository.findByCardPanAndCardNumberAndCardExpiryMonthAndCardExpiryYearAndMerchantProfile(
				paymentCard.getCardPan(), 
				paymentCard.getCardNumber(), 
				paymentCard.getCardExpiryMonth(), 
				paymentCard.getCardExpiryYear(), 
				paymentCard.getMerchantProfile());
	}
}