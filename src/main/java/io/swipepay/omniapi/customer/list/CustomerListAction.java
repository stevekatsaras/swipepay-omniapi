package io.swipepay.omniapi.customer.list;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.swipepay.omniapi.card.CardException;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.paymentcustomer.PaymentCustomer;
import io.swipepay.omniapi.common.entity.paymentcustomer.PaymentCustomerRepository;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.customer.CustomerException;
import io.swipepay.omniapi.customer.list.payload.CustomerListFactory;
import io.swipepay.omniapi.customer.list.payload.CustomerListRequest;
import io.swipepay.omniapi.customer.list.payload.CustomerListResponse;

@Service
public class CustomerListAction {
	
	@Autowired
	private PaymentCustomerRepository paymentCustomerRepository;
	
	public CustomerListResponse list(CustomerListRequest customerListRequest, CustomerListData customerListData) throws CustomerException {
//		MerchantProfile merchantProfile = customerListData.getMerchantProfile();
//		try {
//			LinkedList<PaymentCustomer> paymentCustomers = listPaymentCustomers(merchantProfile);
//			
//			customerListData.setPaymentCustomers(paymentCustomers);
//		}
//		catch (Exception exception) {
//			throw new CardException(
//					Status.RS_0024, 
//					"Customer request failed because of an internal system error", 
//					exception);
//		}
//		return CustomerListFactory.build(customerListRequest, customerListData);
		return null;
	}
	
	@Transactional(readOnly = true)
	private LinkedList<PaymentCustomer> listPaymentCustomers(MerchantProfile merchantProfile) {
		return paymentCustomerRepository.findByMerchantProfileAndEnabledOrderByModifiedDesc(
				merchantProfile, 
				true); // enabled
	}
}