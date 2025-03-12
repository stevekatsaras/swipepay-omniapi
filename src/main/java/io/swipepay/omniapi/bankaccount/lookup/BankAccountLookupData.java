package io.swipepay.omniapi.bankaccount.lookup;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.paymentbankaccount.PaymentBankAccount;

public class BankAccountLookupData {
	private PaymentBankAccount paymentBankAccount;
	
	public BankAccountLookupData(MerchantProfile merchantProfile) {
		//super(merchantProfile.getMerchant(), merchantProfile);
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