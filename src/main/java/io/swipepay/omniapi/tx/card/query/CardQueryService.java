package io.swipepay.omniapi.tx.card.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swipepay.omniapi.common.entity.paymenttxcc.PaymentTxCc;
import io.swipepay.omniapi.common.exception.ValidationException;
import io.swipepay.omniapi.tx.TxException;

@Service
public class CardQueryService {
	
	@Autowired
	private CardQueryFactory cardQueryFactory;
	
	@Autowired
	private CardQueryValidator cardQueryValidator;
	
	public CardQueryResponse query(
			CardQueryRequest cardQueryRequest, 
			PaymentTxCc paymentTxCc, 
			String clientIpAddress) 
					throws ValidationException, TxException {
		
		paymentTxCc = cardQueryValidator.validate(cardQueryRequest, paymentTxCc, clientIpAddress);
		return cardQueryFactory.build(paymentTxCc);
	}
}