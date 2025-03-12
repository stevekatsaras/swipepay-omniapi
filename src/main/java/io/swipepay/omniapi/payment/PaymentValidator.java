package io.swipepay.omniapi.payment;

import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import io.swipepay.omniapi.common.config.properties.OmniApiPaymentProperties;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.merchantprofile.enums.MerchantProfileEnvironment;
import io.swipepay.omniapi.common.entity.merchantprofilecard.MerchantProfileCard;
import io.swipepay.omniapi.common.entity.merchantprofilecurrency.MerchantProfileCurrency;
import io.swipepay.omniapi.common.entity.merchantprofilepaymentcfg.MerchantProfilePaymentCfg;
import io.swipepay.omniapi.common.entity.merchantprofilepaymentcfg.MerchantProfilePaymentCfgRepository;
import io.swipepay.omniapi.common.entity.merchantprofilepaymentgateway.MerchantProfilePaymentGateway;
import io.swipepay.omniapi.common.entity.merchantprofilepaymentgateway.MerchantProfilePaymentGatewayRepository;
import io.swipepay.omniapi.common.entity.paymentcard.PaymentCard;
import io.swipepay.omniapi.common.entity.paymenttxcc.PaymentTxCc;
import io.swipepay.omniapi.common.enums.RelatedPayment;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.ValidationException;
import io.swipepay.omniapi.common.validator.CreditCardValidator;
import io.swipepay.omniapi.payment.payload.PaymentRequest;
import io.swipepay.omniapi.payment.payload.dto.PaymentDto;

@Component
public class PaymentValidator {
	
	@Autowired
	private CreditCardValidator creditCardValidator;
	
	@Autowired
	private MerchantProfilePaymentGatewayRepository merchantProfilePaymentGatewayRepository;
	
	@Autowired
	private OmniApiPaymentProperties omniApiPaymentProperties;
	
	public void validate(PaymentRequest paymentRequest, PaymentData paymentData) throws ValidationException {
//		try {
//			PaymentMethod paymentMethod = paymentData.getPaymentMethod();
//			PaymentMethodCode paymentMethodCode = Enum.valueOf(PaymentMethodCode.class, paymentMethod.getCode());
//		}
//		catch (ValidationException exception) {
//			throw exception;
//		}
//		catch (Exception exception) {
//			throw new ValidationException(
//					Status.RS_0005, 
//					"Validation failed due to an internal server error", 
//					exception);
//		}
	}
	
	@Transactional(readOnly = true)
	public void validateCcCaptureType(PaymentRequest paymentRequest, PaymentData paymentData) throws ValidationException {
//		PaymentDto paymentDto = paymentRequest.getPaymentDto();
//		OrderDto orderDto = paymentRequest.getPaymentDto().getOrderDto();
//		
//		MerchantProfile merchantProfile = paymentData.getMerchantProfile();
//		
//		PaymentTxCc preauthPaymentCc = creditCardValidator.validatePaymentCc(
//				paymentDto.getCode(), 
//				merchantProfile, 
//				true, // check payment type
//				PaymentTypeCode.preauth, 
//				true); // check if payment is succcessful
//		
//		orderValidator.validateOrder(orderDto);
//		orderValidator.validateOrderReference(orderDto.getReference(), merchantProfile);
//		
//		// does the preauth transaction have the same status as the merchant profile?
//		// both must equal 'test' or 'live'
//		
//		// NOTE: but if the "gateway-force-simulation" key is true, then all transactions 
//		// will be forced to route through the simulator
//		// by default, it should be 'false' - but maybe in non-prod it could be true
//		
//		if (BooleanUtils.isFalse(omniApiPaymentProperties.getGatewayForceSimulation())) {
//			if (!StringUtils.equals(preauthPaymentCc.getStatus(), merchantProfile.getStatus())) {
//				throw new ValidationException(
//						Status.RS_3013, 
//						"Validation failed because the payment transaction status does not match the merchant profile status", 
//						null);
//			}			
//		}
//		
//		// has preauth transaction been fully captured?
//		
//		if (preauthPaymentCc.getCapturedAmountRemaining() == 0) {
//			throw new ValidationException(
//					Status.RS_3004, 
//					"Validation failed because the payment transaction amount has been fully captured", 
//					null);
//		}
//		
//		// if amount is supplied, validate the capture limits on this preauth transaction
//		
//		if (StringUtils.isNotBlank(orderDto.getAmount())) {
//			orderValidator.validateOrderAmount(orderDto.getAmount());
//			
//			// is amount greater than the preauth amount?
//			
//			if (Long.parseLong(orderDto.getAmount()) > preauthPaymentCc.getAmount()) {
//				throw new ValidationException(
//						Status.RS_3005, 
//						"Validation failed because the capture amount exceeds the payment amount", 
//						null);
//			}
//			
//			// is amount greater than the remaining amount to be captured on this preauth transaction?
//			
//			else if (Long.parseLong(orderDto.getAmount()) > preauthPaymentCc.getCapturedAmountRemaining()) {
//				throw new ValidationException(
//						Status.RS_3005, 
//						"Validation failed because the capture amount exceeds the remaining payment amount", 
//						null);
//			}
//		}
//		
//		// TODO day limit for a capture on a preauth (within 5 days of preauth)
//		
//		// only validate payment gateways if the merchant profile status is set to 'Live'
//		// merchant profiles with a status of 'Test' will redirect to an internal simulator
//		
//		// NOTE: but if the "gateway-force-simulation" key is true, then all transactions 
//		// will be forced to route through the simulator
//		// by default, it should be 'false' - but maybe in non-prod it could be true
//		
//		if (BooleanUtils.isFalse(omniApiPaymentProperties.getGatewayForceSimulation())) {
//			if (StringUtils.equals(merchantProfile.getStatus(), MerchantProfileStatus.Live.name())) {
//				
//				// get the payment gateway that the preauth transaction used
//				// it's required to use the same payment gateway to capture the transaction
//
//				MerchantProfilePaymentGateway merchantProfilePaymentGateway = merchantProfilePaymentGatewayRepository
//						.findByMerchantProfileAndPaymentGatewayAndEnabled(
//								merchantProfile, 
//								preauthPaymentCc.getPaymentGateway(), 
//								true); // enabled
//				
//				if (merchantProfilePaymentGateway == null) {
//					throw new ValidationException(
//							Status.RS_3010, 
//							"Validation failed because no payment gateway is configured/enabled for this merchant profile", 
//							null);
//				}
//				
//				paymentData.setMerchantProfilePaymentGateway(merchantProfilePaymentGateway);
//			}			
//		}
//		
//		PaymentData preauthPaymentData = new PaymentData();
//		preauthPaymentData.setPaymentCc(preauthPaymentCc);
//		
//		paymentData.getRelatedPaymentData().put(RelatedPayment.purchase.name(), preauthPaymentData);
	}
	
	@Transactional(readOnly = true)
	public void validateCcRefundType(PaymentRequest paymentRequest, PaymentData paymentData) throws ValidationException {
//		PaymentDto paymentDto = paymentRequest.getPaymentDto();
//		OrderDto orderDto = paymentRequest.getPaymentDto().getOrderDto();
//		
//		MerchantProfile merchantProfile = paymentData.getMerchantProfile();
//		
//		PaymentTxCc purchasePaymentCc = creditCardValidator.validatePaymentCc(
//				paymentDto.getCode(), 
//				merchantProfile, 
//				false, // don't check payment type
//				null, 
//				true); // check if payment is successful
//		
//		// is a purchase (payment/preauth) transaction?
//		
//		if (!StringUtils.equals(purchasePaymentCc.getPaymentTypeCode(), PaymentTypeCode.payment.name()) && 
//				!StringUtils.equals(purchasePaymentCc.getPaymentTypeCode(), PaymentTypeCode.preauth.name())) {
//			
//			throw new ValidationException(
//					Status.RS_3006, 
//					"Validation failed because the payment transaction is invalid", 
//					null);
//		}
//		
//		orderValidator.validateOrder(orderDto);
//		orderValidator.validateOrderReference(orderDto.getReference(), merchantProfile);
//		
//		// does the purchase (payment/preauth) transaction have the same status as the merchant profile?
//		// both must equal 'test' or 'live'
//		
//		// NOTE: but if the "gateway-force-simulation" key is true, then all transactions 
//		// will be forced to route through the simulator
//		// by default, it should be 'false' - but maybe in non-prod it could be true
//		
//		if (BooleanUtils.isFalse(omniApiPaymentProperties.getGatewayForceSimulation())) {
//			if (!StringUtils.equals(purchasePaymentCc.getStatus(), merchantProfile.getStatus())) {
//				throw new ValidationException(
//						Status.RS_3013, 
//						"Validation failed because the payment transaction status does not match the merchant profile status", 
//						null);
//			}
//		}
//		
//		// has purchase (payment/preauth) transaction been captured?
//		// if a preauth transaction has not, then there is nothing to refund
//		
//		if (BooleanUtils.isFalse(purchasePaymentCc.getCaptured())) {
//			throw new ValidationException(
//					Status.RS_3007, 
//					"Validation failed because the payment transaction has not been captured", 
//					null);
//		}
//		
//		// has purchase (payment/preauth) transaction been fully refunded?
//		
//		if (purchasePaymentCc.getRefundedAmountRemaining() == 0) {
//			throw new ValidationException(
//					Status.RS_3008, 
//					"Validation failed because the payment transaction amount has been fully refunded", 
//					null);
//		}
//		
//		// if amount is supplied, validate the refund limits on this purchase (payment/preauth) transaction
//		
//		if (StringUtils.isNotBlank(orderDto.getAmount())) {
//			orderValidator.validateOrderAmount(orderDto.getAmount());
//			
//			// is amount greater than the purchase (payment/preauth) amount?
//			
//			if (Long.parseLong(orderDto.getAmount()) > purchasePaymentCc.getAmount()) {
//				throw new ValidationException(
//						Status.RS_3009, 
//						"Validation failed because the refund amount exceeds the payment amount", 
//						null);
//			}
//			
//			else if (Long.parseLong(orderDto.getAmount()) > purchasePaymentCc.getRefundedAmountRemaining()) {
//				throw new ValidationException(
//						Status.RS_3009, 
//						"Validation failed because the refund amount exceeds the remaining payment amount", 
//						null);
//			}	
//		}
//		
//		// only validate payment gateways if the merchant profile status is set to 'Live'
//		// merchant profiles with a status of 'Test' will redirect to an internal simulator
//				
//		// NOTE: but if the "gateway-force-simulation" key is true, then all transactions 
//		// will be forced to route through the simulator
//		// by default, it should be 'false' - but maybe in non-prod it could be true
//		
//		if (BooleanUtils.isFalse(omniApiPaymentProperties.getGatewayForceSimulation())) {
//			if (StringUtils.equals(merchantProfile.getStatus(), MerchantProfileStatus.Live.name())) {
//
//				// get the payment gateway that the purchase (payment/preauth) transaction used
//				// it's required to use the same payment gateway to refund the transaction
//				
//				MerchantProfilePaymentGateway merchantProfilePaymentGateway = merchantProfilePaymentGatewayRepository
//						.findByMerchantProfileAndPaymentGatewayAndEnabled(
//								merchantProfile, 
//								purchasePaymentCc.getPaymentGateway(), 
//								true); // enabled
//				
//				if (merchantProfilePaymentGateway == null) {
//					throw new ValidationException(
//							Status.RS_3010, 
//							"Validation failed because no payment gateway is configured/enabled for this merchant profile", 
//							null);
//				}
//				
//				paymentData.setMerchantProfilePaymentGateway(merchantProfilePaymentGateway);
//			}			
//		}
//		
//		PaymentData purchasePaymentData = new PaymentData();
//		purchasePaymentData.setPaymentCc(purchasePaymentCc);
//		
//		paymentData.getRelatedPaymentData().put(RelatedPayment.purchase.name(), purchasePaymentData);
	}
	
}