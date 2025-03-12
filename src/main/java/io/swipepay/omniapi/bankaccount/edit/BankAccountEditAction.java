package io.swipepay.omniapi.bankaccount.edit;

import java.time.LocalDateTime;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.swipepay.omniapi.bankaccount.BankAccountException;
import io.swipepay.omniapi.bankaccount.edit.payload.BankAccountEditFactory;
import io.swipepay.omniapi.bankaccount.edit.payload.BankAccountEditRequest;
import io.swipepay.omniapi.bankaccount.edit.payload.BankAccountEditResponse;
import io.swipepay.omniapi.bankaccount.edit.payload.dto.BankAccountEditDto;
import io.swipepay.omniapi.common.entity.country.CountryRepository;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.entity.paymentbankaccount.PaymentBankAccount;
import io.swipepay.omniapi.common.entity.paymentbankaccount.PaymentBankAccountRepository;
import io.swipepay.omniapi.common.enums.Status;

@Service
public class BankAccountEditAction {
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private PaymentBankAccountRepository paymentBankAccountRepository;
	
	public BankAccountEditResponse edit(BankAccountEditRequest bankAccountEditRequest, BankAccountEditData bankAccountEditData) throws BankAccountException {
//		BankAccountEditDto bankAccountEditDto = bankAccountEditRequest.getBankAccountEditDto();
//		MerchantProfile merchantProfile = bankAccountEditData.getMerchantProfile();
//		try {
//			PaymentBankAccount paymentBankAccount = getPaymentBankAccount(bankAccountEditDto.getCode(), merchantProfile);
//			save(paymentBankAccount, bankAccountEditDto);
//			
//			bankAccountEditData.setPaymentBankAccount(paymentBankAccount);
//		}
//		catch (Exception exception) {
//			throw new BankAccountException(
//				Status.RS_0024, 
//				"Bank account request failed because of an internal system error", 
//				exception);
//		}
//		return BankAccountEditFactory.build(bankAccountEditRequest, bankAccountEditData);
		return null;
	}
	
	@Transactional(readOnly = true)
	private PaymentBankAccount getPaymentBankAccount(String paymentBankAccountCode, MerchantProfile merchantProfile) {
		return paymentBankAccountRepository.findByCodeAndMerchantProfile(
				paymentBankAccountCode, 
				merchantProfile);
	}
	
	@Transactional(readOnly = false)
	private void save(PaymentBankAccount paymentBankAccount, BankAccountEditDto bankAccountEditDto) {
		if (StringUtils.isNotBlank(bankAccountEditDto.getBsb()))
			paymentBankAccount.setBsb(bankAccountEditDto.getBsb());
		
		if (StringUtils.isNotBlank(bankAccountEditDto.getAccountNumber()))
			paymentBankAccount.setAccountNumber(bankAccountEditDto.getAccountNumber());
		
		if (StringUtils.isNotBlank(bankAccountEditDto.getAccountName()))
			paymentBankAccount.setAccountName(bankAccountEditDto.getAccountName());
		
		if (StringUtils.isNotBlank(bankAccountEditDto.getAccountEmail()))
			paymentBankAccount.setAccountEmail(bankAccountEditDto.getAccountEmail());
		
		if (StringUtils.isNotBlank(bankAccountEditDto.getCountryCode())) {
			paymentBankAccount.setCountryCode(bankAccountEditDto.getCountryCode());
			paymentBankAccount.setCountry(countryRepository.findByIso2(bankAccountEditDto.getCountryCode()));
		}
		
		if (bankAccountEditDto.getEnabled() != null)
			paymentBankAccount.setEnabled(bankAccountEditDto.getEnabled());
		
		paymentBankAccount.setModified(LocalDateTime.now());
		paymentBankAccountRepository.saveAndFlush(paymentBankAccount);
	}
}