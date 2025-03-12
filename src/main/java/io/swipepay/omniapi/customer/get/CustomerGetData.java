package io.swipepay.omniapi.customer.get;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.paymentcustomer.PaymentCustomer;

public class CustomerGetData {
	private PaymentCustomer paymentCustomer;
	
	public CustomerGetData(MerchantProfile merchantProfile) {
		//super(merchantProfile.getMerchant(), merchantProfile);
	}
	
	public PaymentCustomer getPaymentCustomer() {
		return paymentCustomer;
	}

	public void setPaymentCustomer(PaymentCustomer paymentCustomer) {
		this.paymentCustomer = paymentCustomer;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}