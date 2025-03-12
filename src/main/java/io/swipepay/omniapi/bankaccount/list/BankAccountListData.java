package io.swipepay.omniapi.bankaccount.list;

import java.util.LinkedList;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.paymentbankaccount.PaymentBankAccount;

public class BankAccountListData{
	private LinkedList<PaymentBankAccount> paymentBankAccounts;
	
	public BankAccountListData(MerchantProfile merchantProfile) {
		//super(merchantProfile.getMerchant(), merchantProfile);
	}
	
	public LinkedList<PaymentBankAccount> getPaymentBankAccounts() {
		return paymentBankAccounts;
	}

	public void setPaymentBankAccounts(LinkedList<PaymentBankAccount> paymentBankAccounts) {
		this.paymentBankAccounts = paymentBankAccounts;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}