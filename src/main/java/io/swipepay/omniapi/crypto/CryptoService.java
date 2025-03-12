package io.swipepay.omniapi.crypto;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.crypto.payload.CryptoRequest;
import io.swipepay.omniapi.crypto.payload.dto.CryptoDto;

@Service
public class CryptoService {
	
	@Autowired
	private CryptoSupport cryptoSupport;
	
	public String generateDataKey() throws CryptoException {
		String cipherDataKey = null;
		try {
			cipherDataKey = cryptoSupport.generateDataKey();
		}
		catch (Exception exception) {
			throw new CryptoException(
					Status.RS_1004, 
					"Crypto cipher data key failed to generate from the AWS key management service (KMS)", 
					exception);
		}
		return cipherDataKey;
	}
	
	public String decrypt(CryptoRequest cryptoRequest) throws CryptoException {
		String plainTextData = null;
		try {
			CryptoDto cryptoDto = cryptoRequest.getCryptoDto();
			
			SecretKeySpec secretKeySpec = decryptCipherDataKey(cryptoDto.getCipherDataKey());
			plainTextData = cryptoSupport.decrypt(secretKeySpec, cryptoDto.getCipherTextData());
		}
		catch (CryptoException exception) {
			throw exception;
		}
		catch (Exception exception) {
			throw new CryptoException(
					Status.RS_1007, 
					"Crypto cipher text data failed to decrypt from the AWS key management service (KMS)", 
					exception);
		}
		return plainTextData;
	}
	
	public String encrypt(CryptoRequest cryptoRequest) throws CryptoException {
		String cipherTextData = null;
		try {
			CryptoDto cryptoDto = cryptoRequest.getCryptoDto();
			
			SecretKeySpec secretKeySpec = decryptCipherDataKey(cryptoDto.getCipherDataKey());
			cipherTextData = cryptoSupport.encrypt(secretKeySpec, cryptoDto.getPlainTextData());
		}
		catch (CryptoException exception) {
			throw exception;
		}
		catch (Exception exception) {
			throw new CryptoException(
					Status.RS_1006, 
					"Crypto plain text data failed to encrypt from the AWS key management service (KMS)", 
					exception);
		}
		return cipherTextData;
	}
	
	private SecretKeySpec decryptCipherDataKey(String cipherDataKey) throws CryptoException {
		SecretKeySpec secretKeySpec = null;
		try {
			secretKeySpec = cryptoSupport.decryptDataKey(cipherDataKey);
		}
		catch (Exception exception) {
			throw new CryptoException(
					Status.RS_1005, 
					"Crypto cipher data key failed to decrypt from the AWS key management service (KMS). "
					+ "This is required to perform crypto functions", 
					exception);
		}
		return secretKeySpec;
	}
}