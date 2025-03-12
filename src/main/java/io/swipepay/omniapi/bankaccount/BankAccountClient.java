package io.swipepay.omniapi.bankaccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.swipepay.omniapi.bankaccount.add.BankAccountAddAction;
import io.swipepay.omniapi.bankaccount.add.BankAccountAddData;
import io.swipepay.omniapi.bankaccount.add.payload.BankAccountAddRequest;
import io.swipepay.omniapi.bankaccount.add.payload.BankAccountAddResponse;
import io.swipepay.omniapi.bankaccount.add.payload.dto.BankAccountAddDto;
import io.swipepay.omniapi.bankaccount.edit.BankAccountEditAction;
import io.swipepay.omniapi.bankaccount.edit.BankAccountEditData;
import io.swipepay.omniapi.bankaccount.edit.payload.BankAccountEditRequest;
import io.swipepay.omniapi.bankaccount.edit.payload.BankAccountEditResponse;
import io.swipepay.omniapi.bankaccount.edit.payload.dto.BankAccountEditDto;
import io.swipepay.omniapi.common.entity.merchantprofile.MerchantProfile;
import io.swipepay.omniapi.common.payload.dto.MerchantProfileDto;

@Component
public class BankAccountClient {
	
	@Autowired
	private BankAccountAddAction bankAccountAddAction;
	
	@Autowired
	private BankAccountEditAction bankAccountEditAction;
	
	public BankAccountAddResponse addBankAccount(BankAccountAddDto bankAccountAddDto, MerchantProfile merchantProfile) throws BankAccountException {
//		BankAccountAddRequest bankAccountAddRequest = new BankAccountAddRequest();
//		bankAccountAddRequest.setMerchantProfileDto(new MerchantProfileDto(merchantProfile.getCode()));
//		bankAccountAddRequest.setBankAccountAddDto(bankAccountAddDto);
//		return bankAccountAddAction.add(bankAccountAddRequest, new BankAccountAddData(merchantProfile));
		return null;
	}
	
	public BankAccountEditResponse editBankAccount(BankAccountEditDto bankAccountEditDto, MerchantProfile merchantProfile) throws BankAccountException {
//		BankAccountEditRequest bankAccountEditRequest = new BankAccountEditRequest();
//		bankAccountEditRequest.setMerchantProfileDto(new MerchantProfileDto(merchantProfile.getCode()));
//		bankAccountEditRequest.setBankAccountEditDto(bankAccountEditDto);
//		return bankAccountEditAction.edit(bankAccountEditRequest, new BankAccountEditData(merchantProfile));
		return null;
	}
}