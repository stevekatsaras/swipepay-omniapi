package io.swipepay.omniapi.crypto;

import java.nio.ByteBuffer;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.kms.AWSKMSClient;
import com.amazonaws.services.kms.model.DecryptRequest;
import com.amazonaws.services.kms.model.DecryptResult;
import com.amazonaws.services.kms.model.GenerateDataKeyWithoutPlaintextRequest;
import com.amazonaws.services.kms.model.GenerateDataKeyWithoutPlaintextResult;
import com.amazonaws.util.Base64;

import io.swipepay.omniapi.common.config.properties.OmniApiCryptoProperties;

@Component
public class CryptoSupport {
	
	@Autowired
	private AWSKMSClient awsKmsClient;
	
	@Autowired
	private OmniApiCryptoProperties omniApiCryptoProperties;
	
	public String generateDataKey() throws Exception {
		GenerateDataKeyWithoutPlaintextRequest request = new GenerateDataKeyWithoutPlaintextRequest()
				.withKeyId(omniApiCryptoProperties.getKmsCmkId())
				.withKeySpec(omniApiCryptoProperties.getKmsDataKeySpec());
		
		GenerateDataKeyWithoutPlaintextResult result = awsKmsClient.generateDataKeyWithoutPlaintext(request);
		return Base64.encodeAsString(result.getCiphertextBlob().array());
	}
	
	public SecretKeySpec decryptDataKey(String cipherDataKey)  throws Exception {
		ByteBuffer cipherDataKeyBlob = ByteBuffer.wrap(Base64.decode(cipherDataKey));
		
		DecryptRequest request = new DecryptRequest().withCiphertextBlob(cipherDataKeyBlob);
		DecryptResult result = awsKmsClient.decrypt(request);
		
		return new SecretKeySpec(result.getPlaintext().array(), omniApiCryptoProperties.getKmsDataKeyAlg());
	}
	
	public String encrypt(SecretKeySpec secretKeySpec, String plainTextData) throws Exception {
		Cipher cipher = Cipher.getInstance(secretKeySpec.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
		byte[] cipherTextBytes = cipher.doFinal(plainTextData.getBytes());
		
		return Base64.encodeAsString(cipherTextBytes);
	}
	
	public String decrypt(SecretKeySpec secretKeySpec, String cipherTextData) throws Exception {
		byte[] plainTextBytes = Base64.decode(cipherTextData);
		
		Cipher cipher = Cipher.getInstance(secretKeySpec.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
		return new String(cipher.doFinal(plainTextBytes));
	}
}