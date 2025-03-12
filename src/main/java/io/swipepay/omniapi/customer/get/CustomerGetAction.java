package io.swipepay.omniapi.customer.get;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.paymentcustomer.PaymentCustomer;
import io.swipepay.omniapi.common.entity.paymentcustomer.PaymentCustomerRepository;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.customer.CustomerException;
import io.swipepay.omniapi.customer.get.payload.CustomerGetFactory;
import io.swipepay.omniapi.customer.get.payload.CustomerGetRequest;
import io.swipepay.omniapi.customer.get.payload.CustomerGetResponse;
import io.swipepay.omniapi.customer.get.payload.dto.CustomerGetDto;

@Service
public class CustomerGetAction {
	
	@Autowired
	private PaymentCustomerRepository paymentCustomerRepository;
	
	public CustomerGetResponse get(CustomerGetRequest customerGetRequest, CustomerGetData customerGetData) throws CustomerException {
//		CustomerGetDto customerGetDto = customerGetRequest.getCustomerGetDto();
//		MerchantProfile merchantProfile = customerGetData.getMerchantProfile();
//		try {
//			PaymentCustomer paymentCustomer = getPaymentCustomer(customerGetDto.getCode(), merchantProfile);
//			
//			customerGetData.setPaymentCustomer(paymentCustomer);
//		}
//		catch (Exception exception) {
//			throw new CustomerException(
//				Status.RS_0024, 
//				"Customer request failed because of an internal system error", 
//				exception);
//		}
//		return CustomerGetFactory.build(customerGetRequest, customerGetData);
		return null;
	}
	
	@Transactional(readOnly = true)
	private PaymentCustomer getPaymentCustomer(String customerCode, MerchantProfile merchantProfile) {
		return paymentCustomerRepository.findByCodeAndMerchantProfileAndEnabled(
				customerCode, 
				merchantProfile, 
				true); // enabled
	}
}