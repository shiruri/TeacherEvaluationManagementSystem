package com.shiro;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

public class AES {
	 SecretKey key; // key for decryption
	private int KEY_SIZE = 256;
	Cipher engcryptionCipher; // cipher
	private int T_LEN =112; // tag length
	
	// dosent work broken ahh cyrption

	public void init() throws Exception {
		
		KeyGenerator generator =  KeyGenerator.getInstance("AES"); // the i forgot but the provider
		generator.init(KEY_SIZE); // key size
		key = generator.generateKey(); // key generator
	}
	
	public String engcypt(String password) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		
		byte[] messageBytes = password.getBytes();
		engcryptionCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		engcryptionCipher.init(Cipher.ENCRYPT_MODE,key);
		byte[] engcryptedBytes = engcryptionCipher.doFinal(messageBytes);
		
		return encode(engcryptedBytes);
	}
	
	public String decrypt(String engcryptedMessage) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte[] messageInBytes = decode(engcryptedMessage);
		Cipher decryptedCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		GCMParameterSpec spec = new GCMParameterSpec(T_LEN,engcryptionCipher.getIV());
		decryptedCipher.init(Cipher.DECRYPT_MODE,key,spec);
		byte[] decryptedBytes = decryptedCipher.doFinal(messageInBytes);
		return new String(decryptedBytes);
	}
	
	private String encode(byte[] data) {
		return Base64.getEncoder().encodeToString(data);
	}
	
	private byte[] decode(String data) {
		return Base64.getDecoder().decode(data);
	}
	}
