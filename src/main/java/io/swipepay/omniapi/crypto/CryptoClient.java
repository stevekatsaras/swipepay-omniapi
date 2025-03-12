package io.swipepay.omniapi.crypto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.swipepay.omniapi.crypto.payload.CryptoRequest;
import io.swipepay.omniapi.crypto.payload.dto.CryptoDto;

@Component
public class CryptoClient {
	
	@Autowired
	private CryptoService cryptoService;
	
	@Autowired
	private CryptoValidator cryptoValidator;
	
	public String decrypt(String dataKey, String cipherText) throws CryptoException {
		CryptoRequest cryptoRequest = new CryptoRequest();
		cryptoRequest.setCryptoDto(new CryptoDto(dataKey, cipherText, null));
		
		cryptoValidator.validateDecryption(cryptoRequest);
		return cryptoService.decrypt(cryptoRequest);
	}
	
	public String encrypt(String dataKey, String plainText) throws CryptoException {
		CryptoRequest cryptoRequest = new CryptoRequest();
		cryptoRequest.setCryptoDto(new CryptoDto(dataKey, null, plainText));
		
		cryptoValidator.validateEncryption(cryptoRequest);
		return cryptoService.encrypt(cryptoRequest);
	}
	
}