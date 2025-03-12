package io.swipepay.omniapi.bankaccount.lookup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.swipepay.omniapi.bankaccount.BankAccountException;
import io.swipepay.omniapi.bankaccount.lookup.payload.BankAccountLookupFactory;
import io.swipepay.omniapi.bankaccount.lookup.payload.BankAccountLookupRequest;
import io.swipepay.omniapi.bankaccount.lookup.payload.BankAccountLookupResponse;
import io.swipepay.omniapi.bankaccount.lookup.payload.dto.BankAccountLookupDto;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.paymentbankaccount.PaymentBankAccount;
import io.swipepay.omniapi.common.entity.paymentbankaccount.PaymentBankAccountRepository;
import io.swipepay.omniapi.common.enums.Status;

@Service
public class BankAccountLookupAction {
	
	@Autowired
	private PaymentBankAccountRepository paymentBankAccountRepository;
	
	public BankAccountLookupResponse lookup(BankAccountLookupRequest bankAccountLookupRequest, BankAccountLookupData bankAccountLookupData) throws BankAccountException {
//		BankAccountLookupDto bankAccountLookupDto = bankAccountLookupRequest.getBankAccountLookupDto();
//		MerchantProfile merchantProfile = bankAccountLookupData.getMerchantProfile();
//		try {
//			PaymentBankAccount paymentBankAccount = lookupPaymentBankAccount(bankAccountLookupDto, merchantProfile);
//			if (paymentBankAccount == null) {
//				throw new BankAccountException(
//						Status.RS_5013, 
//						"Bank account request failed because no bank account could be found", 
//						null);
//			}
//			bankAccountLookupData.setPaymentBankAccount(paymentBankAccount);
//		}
//		catch (BankAccountException exception) {
//			throw exception;
//		}
//		catch (Exception exception) {
//			exception.printStackTrace();
//			throw new BankAccountException(
//				Status.RS_0024, 
//				"Bank account request failed because of an internal system error", 
//				exception);
//		}
//		return BankAccountLookupFactory.build(bankAccountLookupRequest, bankAccountLookupData);
		return null;
	}
	
	@Transactional(readOnly = true)
	private PaymentBankAccount lookupPaymentBankAccount(BankAccountLookupDto bankAccountLookupDto, MerchantProfile merchantProfile) {
		return paymentBankAccountRepository.findByBsbAndAccountNumberAndCountryCodeAndMerchantProfile(
				bankAccountLookupDto.getBsb(), 
				bankAccountLookupDto.getAccountNumber(), 
				bankAccountLookupDto.getCountryCode(), 
				merchantProfile);
	}
	
}