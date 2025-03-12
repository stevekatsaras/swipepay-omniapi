package io.swipepay.omniapi.tx.card.preauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swipepay.omniapi.common.aspect.durationtime.DurationTimeTxCc;
import io.swipepay.omniapi.common.entity.paymenttxcc.PaymentTxCc;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.ValidationException;
import io.swipepay.omniapi.tx.TxException;

@Service
public class CardPreauthService {
	
	@Autowired
	private CardPreauthAction cardPreauthAction;
	
	@Autowired
	private CardPreauthFactory cardPreauthFactory;
	
	@Autowired
	private CardPreauthValidator cardPreauthValidator;
	
	@DurationTimeTxCc
	public CardPreauthResponse preauth(
			CardPreauthRequest cardPreauthRequest, 
			PaymentTxCc paymentTxCc, 
			String clientIpAddress) 
					throws ValidationException, TxException {
		
		cardPreauthValidator.validate(cardPreauthRequest, paymentTxCc, clientIpAddress);
		try {
			cardPreauthAction.saveNewTx(paymentTxCc);
			cardPreauthAction.savePendingTx(paymentTxCc);
			if (cardPreauthAction.routeTx(paymentTxCc)) {
				cardPreauthAction.saveProcessedTx(paymentTxCc);
			}
			else {
				cardPreauthAction.saveFailedTx(paymentTxCc);
			}
		}
		catch (TxException exception) {
			throw exception;
		}
		catch (Exception exception) {
			throw new TxException(
					Status.RS_0024, 
					"Payment failed because of an internal system error", 
					exception);
		}
		return cardPreauthFactory.build(paymentTxCc);
	}
}