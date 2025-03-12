package io.swipepay.omniapi.card.list;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.swipepay.omniapi.card.CardException;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.paymentcard.PaymentCard;
import io.swipepay.omniapi.common.entity.paymentcard.PaymentCardRepository;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.ValidationException;

@Service
public class CardListAction {
	
	@Autowired
	private CardListValidator cardListValidator;
	
	@Autowired
	private PaymentCardRepository paymentCardRepository;

	public CardListResponse list(
			CardListRequest cardListRequest, 
			PaymentCard paymentCard, 
			String clientIpAddress) 
					throws ValidationException, CardException {
		
		cardListValidator.validate(cardListRequest, paymentCard, clientIpAddress);
		
		LinkedList<PaymentCard> paymentCards = null;
		try {
			paymentCards = getPaymentCards(paymentCard.getMerchantProfile());
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
		return CardListFactory.build(paymentCard.getMerchantProfile(), paymentCards);
	}
	
	@Transactional(readOnly = true)
	private LinkedList<PaymentCard> getPaymentCards(MerchantProfile merchantProfile) {
		return paymentCardRepository.findByMerchantProfileOrderByModifiedDesc(merchantProfile);
	}
}