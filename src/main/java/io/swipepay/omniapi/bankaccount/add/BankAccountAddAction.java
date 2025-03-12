package io.swipepay.omniapi.bankaccount.add;

import java.time.LocalDateTime;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.swipepay.omniapi.bankaccount.BankAccountException;
import io.swipepay.omniapi.bankaccount.add.payload.BankAccountAddFactory;
import io.swipepay.omniapi.bankaccount.add.payload.BankAccountAddRequest;
import io.swipepay.omniapi.bankaccount.add.payload.BankAccountAddResponse;
import io.swipepay.omniapi.bankaccount.add.payload.dto.BankAccountAddDto;
import io.swipepay.omniapi.common.entity.country.CountryRepository;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.paymentbankaccount.PaymentBankAccount;
import io.swipepay.omniapi.common.entity.paymentbankaccount.PaymentBankAccountRepository;
import io.swipepay.omniapi.common.enums.Status;

@Service
public class BankAccountAddAction {
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private PaymentBankAccountRepository paymentBankAccountRepository;
	
	public BankAccountAddResponse add(BankAccountAddRequest bankAccountAddRequest, BankAccountAddData bankAccountAddData) throws BankAccountException {
//		BankAccountAddDto bankAccountAddDto = bankAccountAddRequest.getBankAccountAddDto();
//		MerchantProfile merchantProfile = bankAccountAddData.getMerchantProfile();
//		try {
//			PaymentBankAccount paymentBankAccount = isBankAccountTokenised(bankAccountAddDto, merchantProfile);
//			if (paymentBankAccount == null) {
//				paymentBankAccount = buildPaymentBankAccount(bankAccountAddDto, merchantProfile);
//				save(paymentBankAccount);
//			}
//			bankAccountAddData.setPaymentBankAccount(paymentBankAccount);
//		}
//		catch (Exception exception) {
//			throw new BankAccountException(
//				Status.RS_0024, 
//				"Bank account request failed because of an internal system error", 
//				exception);
//		}
//		return BankAccountAddFactory.build(bankAccountAddRequest, bankAccountAddData);
		return null;
	}
	
	@Transactional(readOnly = true)
	private PaymentBankAccount isBankAccountTokenised(BankAccountAddDto bankAccountAddDto, MerchantProfile merchantProfile) {
		return paymentBankAccountRepository.findByBsbAndAccountNumberAndCountryCodeAndMerchantProfile(
				bankAccountAddDto.getBsb(), 
				bankAccountAddDto.getAccountNumber(),
				bankAccountAddDto.getCountryCode(), 
				merchantProfile);
	}
	
	@Transactional(readOnly = true)
	private PaymentBankAccount buildPaymentBankAccount(BankAccountAddDto bankAccountAddDto, MerchantProfile merchantProfile) {
		PaymentBankAccount paymentBankAccount = new ModelMapper().map(bankAccountAddDto, PaymentBankAccount.class);
		paymentBankAccount.setCode("ba_" + UUID.randomUUID().toString().replaceAll("-", ""));
		paymentBankAccount.setEnabled(true);
		paymentBankAccount.setModified(LocalDateTime.now());
		paymentBankAccount.setMerchantProfile(merchantProfile);
		paymentBankAccount.setCountry(countryRepository.findByIso2(bankAccountAddDto.getCountryCode()));
		return paymentBankAccount;
	}
	
	@Transactional(readOnly = false)
	private void save(PaymentBankAccount paymentBankAccount) {
		paymentBankAccountRepository.saveAndFlush(paymentBankAccount);
	}
	
}