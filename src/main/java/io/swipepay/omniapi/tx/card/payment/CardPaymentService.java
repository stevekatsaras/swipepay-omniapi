package io.swipepay.omniapi.tx.card.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swipepay.omniapi.common.aspect.durationtime.DurationTimeTxCc;
import io.swipepay.omniapi.common.entity.paymenttxcc.PaymentTxCc;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.ValidationException;
import io.swipepay.omniapi.tx.TxException;

@Service
public class CardPaymentService {
	
	@Autowired
	private CardPaymentAction cardPaymentAction;
	
	@Autowired
	private CardPaymentFactory cardPaymentFactory;
	
	@Autowired
	private CardPaymentValidator cardPaymentValidator;
	
	@DurationTimeTxCc
	public CardPaymentResponse payment(
			CardPaymentRequest cardPaymentRequest, 
			PaymentTxCc paymentTxCc,
			String clientIpAddress) 
					throws ValidationException, TxException {
		
		cardPaymentValidator.validate(cardPaymentRequest, paymentTxCc, clientIpAddress);
		try {
			cardPaymentAction.saveNewTx(paymentTxCc);
			cardPaymentAction.savePendingTx(paymentTxCc);
			if (cardPaymentAction.routeTx(paymentTxCc)) {
				cardPaymentAction.saveProcessedTx(paymentTxCc);
			}
			else {
				cardPaymentAction.saveFailedTx(paymentTxCc);
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
		return cardPaymentFactory.build(paymentTxCc);
	}
}