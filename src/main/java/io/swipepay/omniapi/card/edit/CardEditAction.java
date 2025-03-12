package io.swipepay.omniapi.card.edit;

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
public class CardEditAction {
	
	@Autowired
	private CardEditValidator cardEditValidator;
	
	@Autowired
	private PaymentCardRepository paymentCardRepository;
	
	public CardEditResponse edit(
			CardEditRequest cardEditRequest, 
			PaymentCard paymentCard, 
			String clientIpAddress) 
					throws ValidationException, CardException {

		paymentCard = cardEditValidator.validate(cardEditRequest, paymentCard, clientIpAddress);
		try {
			save(paymentCard);
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
		return CardEditFactory.build(paymentCard);
	}
	
	@Transactional(readOnly = true)
	private PaymentCard getPaymentCard(String paymentCardCode, MerchantProfile merchantProfile) {
		return paymentCardRepository.findByCodeAndMerchantProfile(
				paymentCardCode, 
				merchantProfile);
	}
	
	@Transactional(readOnly = false)
	private void save(PaymentCard paymentCard) {
		paymentCardRepository.saveAndFlush(paymentCard);
	}
	
}