package io.swipepay.omniapi.bankaccount.list;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.swipepay.omniapi.bankaccount.BankAccountException;
import io.swipepay.omniapi.bankaccount.list.payload.BankAccountListFactory;
import io.swipepay.omniapi.bankaccount.list.payload.BankAccountListRequest;
import io.swipepay.omniapi.bankaccount.list.payload.BankAccountListResponse;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.paymentbankaccount.PaymentBankAccount;
import io.swipepay.omniapi.common.entity.paymentbankaccount.PaymentBankAccountRepository;
import io.swipepay.omniapi.common.enums.Status;

@Service
public class BankAccountListAction {
	
	@Autowired
	private PaymentBankAccountRepository paymentBankAccountRepository;
	
	public BankAccountListResponse list(BankAccountListRequest bankAccountListRequest, BankAccountListData bankAccountListData) throws BankAccountException {
//		MerchantProfile merchantProfile = bankAccountListData.getMerchantProfile();
//		try {
//			LinkedList<PaymentBankAccount> paymentBankAccounts = getPaymentBankAccounts(merchantProfile);
//			
//			bankAccountListData.setPaymentBankAccounts(paymentBankAccounts);
//		}
//		catch (Exception exception) {
//			throw new BankAccountException(
//				Status.RS_0024, 
//				"Bank account request failed because of an internal system error", 
//				exception);
//		}
//		return BankAccountListFactory.build(bankAccountListRequest, bankAccountListData);
		return null;
	}
	
	@Transactional(readOnly = true)
	private LinkedList<PaymentBankAccount> getPaymentBankAccounts(MerchantProfile merchantProfile) {
		return paymentBankAccountRepository.findByMerchantProfileOrderByModifiedDesc(merchantProfile);
	}
	
}