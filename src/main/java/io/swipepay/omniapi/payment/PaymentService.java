package io.swipepay.omniapi.payment;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swipepay.omniapi.card.CardException;
import io.swipepay.omniapi.common.entity.paymenttxccmetadata.PaymentTxCcMetadata;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.crypto.CryptoException;
import io.swipepay.omniapi.payment.action.creditcard.CcCaptureService;
import io.swipepay.omniapi.payment.action.creditcard.CcQueryService;
import io.swipepay.omniapi.payment.action.creditcard.CcRefundService;
import io.swipepay.omniapi.payment.payload.PaymentRequest;
import io.swipepay.omniapi.payment.payload.PaymentResponse;

@Service
public class PaymentService {
	
	@Autowired
	private CcCaptureService ccCaptureService;
	
	@Autowired
	private CcQueryService ccQueryService;
	
	@Autowired
	private CcRefundService ccRefundService;
	
	public PaymentResponse go(PaymentRequest paymentRequest, PaymentData paymentData) throws PaymentException {
//		PaymentResponse paymentResponse = null;
//		
//		PaymentMethod paymentMethod = paymentData.getPaymentMethod();
//		PaymentMethodCode paymentMethodCode = Enum.valueOf(PaymentMethodCode.class, paymentMethod.getCode());
//		
//		return paymentResponse;
		return null;
	}
	
	private PaymentResponse doCcCapture(PaymentRequest paymentRequest, PaymentData paymentData) throws PaymentException {
		try {
			ccCaptureService.saveNewTx(paymentRequest, paymentData);
			ccCaptureService.savePendingTx(paymentRequest, paymentData);
			
			if (ccCaptureService.routeTx(paymentRequest, paymentData)) {
				ccCaptureService.saveProcessedTx(paymentRequest, paymentData);
			}
			else {
				ccCaptureService.saveFailedTx(paymentRequest, paymentData);
			}
		}
		catch (CryptoException exception) {
			throw new PaymentException(
					Status.RS_0023, 
					"Payment failed because of an internal crypto error", 
					exception);
		}
		catch (Exception exception) {
			throw new PaymentException(
					Status.RS_0024, 
					"Payment failed because of an internal system error", 
					exception);
		}
		
		return ccCaptureService.buildPaymentResponse(paymentData);
	}
	
	private PaymentResponse doCcRefund(PaymentRequest paymentRequest, PaymentData paymentData) throws PaymentException {
		try {
			ccRefundService.saveNewTx(paymentRequest, paymentData);
			ccRefundService.savePendingTx(paymentRequest, paymentData);
			
			if (ccRefundService.routeTx(paymentRequest, paymentData)) {
				ccRefundService.saveProcessedTx(paymentRequest, paymentData);
			}
			else {
				ccRefundService.saveFailedTx(paymentRequest, paymentData);
			}			
		}
		catch (CryptoException exception) {
			throw new PaymentException(
					Status.RS_0023, 
					"Payment failed because of an internal crypto error", 
					exception);
		}
		catch (Exception exception) {
			throw new PaymentException(
					Status.RS_0024, 
					"Payment failed because of an internal system error", 
					exception);
		}
		
		return ccRefundService.buildPaymentResponse(paymentData);
	}
	
	private PaymentResponse doCcQuery(PaymentRequest paymentRequest, PaymentData paymentData) throws PaymentException {
//		try {
//			LinkedList<PaymentTxCcMetadata> paymentCcMetadata = ccQueryService.buildPaymentCcMetadata(
//					paymentRequest, 
//					paymentData);
//			
//			paymentData.setPaymentCcMetadata(paymentCcMetadata);
//		}
//		catch (Exception exception) {
//			throw new PaymentException(
//					Status.RS_0024, 
//					"Payment failed because of an internal system error", 
//					exception);
//		}
//		
//		return ccQueryService.buildPaymentResponse(paymentData);
		return null;
	}
	
}