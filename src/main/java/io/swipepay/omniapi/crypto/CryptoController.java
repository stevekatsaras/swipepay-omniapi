package io.swipepay.omniapi.crypto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swipepay.omniapi.common.enums.Status;
import io.swipepay.omniapi.crypto.payload.CryptoRequest;
import io.swipepay.omniapi.crypto.payload.CryptoResponse;
import io.swipepay.omniapi.crypto.payload.dto.CryptoDto;

@RestController
@RequestMapping(value = "/crypto")
public class CryptoController {
	
	@Autowired
	private CryptoService cryptoService;
	
	@Autowired
	private CryptoValidator cryptoValidator;
	
	@PostMapping(value = "/key/generate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CryptoResponse generateDataKey() {
		String cipherDataKey = cryptoService.generateDataKey();
		
		CryptoDto cryptoDto = new CryptoDto();
		cryptoDto.setCipherDataKey(cipherDataKey);
		
		CryptoResponse response = new CryptoResponse(Status.RS_0000);
		response.setCryptoDto(cryptoDto);
		return response;
	}
	
	@PostMapping(value = "/decrypt", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CryptoResponse decrypt(@RequestBody CryptoRequest cryptoRequest) {
		cryptoValidator.validateDecryption(cryptoRequest);
		String plainTextData = cryptoService.decrypt(cryptoRequest);
		
		CryptoDto cryptoDto = new CryptoDto();
		cryptoDto.setPlainTextData(plainTextData);
		
		CryptoResponse response = new CryptoResponse(Status.RS_0000);
		response.setCryptoDto(cryptoDto);
		return response;
	}
	
	@PostMapping(value = "/encrypt", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CryptoResponse encrypt(@RequestBody CryptoRequest cryptoRequest) {
		cryptoValidator.validateEncryption(cryptoRequest);
		String cipherTextData = cryptoService.encrypt(cryptoRequest);
		
		CryptoDto cryptoDto = new CryptoDto();
		cryptoDto.setCipherTextData(cipherTextData);

		CryptoResponse response = new CryptoResponse(Status.RS_0000);
		response.setCryptoDto(cryptoDto);
		return response;
	}
}