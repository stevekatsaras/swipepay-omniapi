package io.swipepay.omniapi.payment.action.creditcard;

import java.util.LinkedList;

import io.swipepay.omniapi.common.entity.merchantprofilepaymentgateway.MerchantProfilePaymentGateway;
import io.swipepay.omniapi.common.entity.paymenttxcc.PaymentTxCc;
import io.swipepay.omniapi.common.entity.paymenttxccmetadata.PaymentTxCcMetadata;
import io.swipepay.omniapi.payment.PaymentData;
import io.swipepay.omniapi.payment.payload.PaymentRequest;
import io.swipepay.omniapi.payment.payload.PaymentResponse;

public abstract class CcTxService {
	
	public abstract void saveNewTx(PaymentRequest paymentRequest, PaymentData paymentData);
	
	public abstract void savePendingTx(PaymentRequest paymentRequest, PaymentData paymentData);

	public abstract void saveProcessedTx(PaymentRequest paymentRequest, PaymentData paymentData);
	
	public abstract void saveFailedTx(PaymentRequest paymentRequest, PaymentData paymentData);
	
	public abstract Boolean routeTx(PaymentRequest paymentRequest, PaymentData paymentData);
	
	public abstract MerchantProfilePaymentGateway routeTxViaGateway(PaymentRequest paymentRequest, PaymentData paymentData);
	
	public abstract void finaliseTxViaGateway(PaymentData paymentData, MerchantProfilePaymentGateway merchantProfilePaymentGateway, LinkedList<String> txExceptions);
	
	public abstract PaymentTxCc buildPaymentCc(PaymentRequest paymentRequest, PaymentData paymentData, String cryptoTextData);
	
	public abstract LinkedList<PaymentTxCcMetadata> buildPaymentCcMetadata(PaymentRequest paymentRequest, PaymentData paymentData);
	
	public abstract PaymentResponse buildPaymentResponse(PaymentData paymentData);
	
}