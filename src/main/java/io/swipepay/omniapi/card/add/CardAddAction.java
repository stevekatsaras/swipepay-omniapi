package io.swipepay.omniapi.card.add;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.swipepay.omniapi.card.CardException;
import io.swipepay.omniapi.common.entity.paymentcard.PaymentCard;
import io.swipepay.omniapi.common.entity.paymentcard.PaymentCardRepository;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.ValidationException;

@Service
public class CardAddAction {
	
	@Autowired
	private CardAddValidator cardAddValidator;
	
	@Autowired
	private PaymentCardRepository paymentCardRepository;
	
	public CardAddResponse add(
			CardAddRequest cardAddRequest, 
			PaymentCard paymentCard, 
			String clientIpAddress) 
					throws ValidationException, CardException {
		
		cardAddValidator.validate(cardAddRequest, paymentCard, clientIpAddress);
		try {
			PaymentCard tokenisedPaymentCard = isCardTokenised(paymentCard);
			if (tokenisedPaymentCard != null) {
				paymentCard = tokenisedPaymentCard;
			}
			else {
				save(paymentCard);				
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
		return CardAddFactory.build(paymentCard);
	}
	
	@Transactional(readOnly = true)
	private PaymentCard isCardTokenised(PaymentCard paymentCard) {
		return paymentCardRepository.findByCardPanAndCardNumberAndCardExpiryMonthAndCardExpiryYearAndMerchantProfile(
				paymentCard.getCardPan(), 
				paymentCard.getCardNumber(), 
				paymentCard.getCardExpiryMonth(), 
				paymentCard.getCardExpiryYear(), 
				paymentCard.getMerchantProfile());
	}
	
	@Transactional(readOnly = false)
	private void save(PaymentCard paymentCard) {
		paymentCardRepository.saveAndFlush(paymentCard);
	}
	
}