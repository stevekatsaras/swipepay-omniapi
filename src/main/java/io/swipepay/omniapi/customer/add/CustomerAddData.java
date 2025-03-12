package io.swipepay.omniapi.customer.add;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.paymentbankaccount.PaymentBankAccount;
import io.swipepay.omniapi.common.entity.paymentcard.PaymentCard;
import io.swipepay.omniapi.common.entity.paymentcustomer.PaymentCustomer;

public class CustomerAddData {
	private PaymentCustomer paymentCustomer;
	private PaymentCard paymentCard;
	private PaymentBankAccount paymentBankAccount;
	
	public CustomerAddData(MerchantProfile merchantProfile) {
		//super(merchantProfile.getMerchant(), merchantProfile);
	}
	
	public PaymentCustomer getPaymentCustomer() {
		return paymentCustomer;
	}

	public void setPaymentCustomer(PaymentCustomer paymentCustomer) {
		this.paymentCustomer = paymentCustomer;
	}

	public PaymentCard getPaymentCard() {
		return paymentCard;
	}

	public void setPaymentCard(PaymentCard paymentCard) {
		this.paymentCard = paymentCard;
	}

	public PaymentBankAccount getPaymentBankAccount() {
		return paymentBankAccount;
	}

	public void setPaymentBankAccount(PaymentBankAccount paymentBankAccount) {
		this.paymentBankAccount = paymentBankAccount;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}