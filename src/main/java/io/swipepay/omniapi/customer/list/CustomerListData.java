package io.swipepay.omniapi.customer.list;

import java.util.LinkedList;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.paymentcustomer.PaymentCustomer;

public class CustomerListData {
	private LinkedList<PaymentCustomer> paymentCustomers;
	
	public CustomerListData(MerchantProfile merchantProfile) {
		//super(merchantProfile.getMerchant(), merchantProfile);
	}
	
	public LinkedList<PaymentCustomer> getPaymentCustomers() {
		return paymentCustomers;
	}

	public void setPaymentCustomers(LinkedList<PaymentCustomer> paymentCustomers) {
		this.paymentCustomers = paymentCustomers;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}