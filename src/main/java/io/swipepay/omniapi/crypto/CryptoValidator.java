package io.swipepay.omniapi.crypto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.common.exception.ValidationException;
import io.swipepay.omniapi.crypto.payload.CryptoRequest;
import io.swipepay.omniapi.crypto.payload.dto.CryptoDto;

@Component
public class CryptoValidator {

	public void validateEncryption(CryptoRequest cryptoRequest) throws ValidationException {
		CryptoDto cryptoDto = cryptoRequest.getCryptoDto();
		validateCrypto(cryptoDto);
		validateCryptoCipherDataKey(cryptoDto.getCipherDataKey());
		validateCryptoPlainTextData(cryptoDto.getPlainTextData());
	}
	
	public void validateDecryption(CryptoRequest cryptoRequest) throws ValidationException {
		CryptoDto cryptoDto = cryptoRequest.getCryptoDto();
		validateCrypto(cryptoDto);
		validateCryptoCipherDataKey(cryptoDto.getCipherDataKey());
		validateCryptoCipherTextData(cryptoDto.getCipherTextData());
	}
	
	public void validateCrypto(CryptoDto cryptoDto) throws ValidationException {
		if (cryptoDto == null) {
			throw new ValidationException(
					Status.RS_1000, 
					"Validation failed because crypto is missing", 
					null);
		}
	}
	
	public void validateCryptoCipherDataKey(String cipherDataKey) throws ValidationException {
		if (StringUtils.isBlank(cipherDataKey)) {
			throw new ValidationException(
					Status.RS_1001, 
					"Validation failed because the crypto cipher data key is not supplied", 
					null);
		}
	}
	
	public void validateCryptoPlainTextData(String plainTextData) throws ValidationException {
		if (StringUtils.isBlank(plainTextData)) {
			throw new ValidationException(
					Status.RS_1002, 
					"Validation failed because the crypto plain text data is not supplied", 
					null);
		}
	}
	
	public void validateCryptoCipherTextData(String cipherTextData) throws ValidationException {
		if (StringUtils.isBlank(cipherTextData)) {
			throw new ValidationException(
					Status.RS_1003, 
					"Validation failed because the crypto cipher text data is not supplied", 
					null);
		}
	}
	
}