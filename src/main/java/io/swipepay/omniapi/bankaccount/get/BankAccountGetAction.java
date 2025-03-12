package io.swipepay.omniapi.bankaccount.get;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.swipepay.omniapi.bankaccount.BankAccountException;
import io.swipepay.omniapi.bankaccount.get.payload.BankAccountGetFactory;
import io.swipepay.omniapi.bankaccount.get.payload.BankAccountGetRequest;
import io.swipepay.omniapi.bankaccount.get.payload.BankAccountGetResponse;
import io.swipepay.omniapi.bankaccount.get.payload.dto.BankAccountGetDto;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.paymentbankaccount.PaymentBankAccount;
import io.swipepay.omniapi.common.entity.paymentbankaccount.PaymentBankAccountRepository;
import io.swipepay.omniapi.common.enums.Status;

@Service
public class BankAccountGetAction {
	
	@Autowired
	private PaymentBankAccountRepository paymentBankAccountRepository;
	
	public BankAccountGetResponse get(BankAccountGetRequest bankAccountGetRequest, BankAccountGetData bankAccountGetData) throws BankAccountException {
//		BankAccountGetDto bankAccountGetDto = bankAccountGetRequest.getBankAccountGetDto();
//		MerchantProfile merchantProfile = bankAccountGetData.getMerchantProfile();
//		try {
//			PaymentBankAccount paymentBankAccount = getPaymentBankAccount(bankAccountGetDto.getCode(), merchantProfile);
//			
//			bankAccountGetData.setPaymentBankAccount(paymentBankAccount);
//		}
//		catch (Exception exception) {
//			throw new BankAccountException(
//				Status.RS_0024, 
//				"Bank account request failed because of an internal system error", 
//				exception);
//		}
//		return BankAccountGetFactory.build(bankAccountGetRequest, bankAccountGetData);
		return null;
	}
	
	@Transactional(readOnly = true)
	private PaymentBankAccount getPaymentBankAccount(String paymentBankAccountCode, MerchantProfile merchantProfile) {
		return paymentBankAccountRepository.findByCodeAndMerchantProfile(
				paymentBankAccountCode, 
				merchantProfile);
	}
	
}