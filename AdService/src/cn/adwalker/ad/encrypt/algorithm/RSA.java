package cn.adwalker.ad.encrypt.algorithm;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.crypto.Cipher;

/**
 * RSA 算法
 * @author gary
 * 
 */
public class RSA extends Encrypt{
	
	private final static String ALGORITHM = "RSA";
	private final static int ALGORITHM_KEYSIZE = 1024;

	public static PublicKey publicKey;
	public static PrivateKey privateKey;

	public static void generateKey() throws Exception {
		KeyPairGenerator gen = KeyPairGenerator.getInstance(ALGORITHM);
		gen.initialize(ALGORITHM_KEYSIZE, new SecureRandom());
		KeyPair keyPair = gen.generateKeyPair();
		publicKey = keyPair.getPublic();
		privateKey = keyPair.getPrivate();
		
		//System.out.println("uk: " + publicKey);
		//System.out.println("rk: " + privateKey);
	}
	
	

	private static byte[] encrypt(String text, PublicKey pubALGORITHM)
			throws Exception {
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, pubALGORITHM);
		return cipher.doFinal(text.getBytes(STRING_CHARSET));
	}

	public final static String encrypt(String text) throws Exception {

		return byte2Hex(encrypt(text, publicKey));
	}

	public final static String decrypt(String data) throws Exception {

		return new String(decrypt(hex2Byte(data)),STRING_CHARSET);

	}

	private static byte[] decrypt(byte[] src) throws Exception {
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(src);
	}

	// just for test
	public static void main(String args[]) throws Exception {
		String content = "12222221111111111111111111111sssssssssssssssssasd阿达速度撒打算打算打算打算的sss1";

		RSA.generateKey();
		// 加密
		System.out.println("加密前：" + content);
		String encryptResult = RSA.encrypt(content);
		System.out.println("加密后：" + encryptResult);
		// 解密
		//String encryptResult = "3B4D23651733C2D02A77E1166329E8CC0EC4CAE564B6F6ABDDA51BC4216F594B52C6562A040FAB7D50AA82DA2ED45865AB2656704DE6CBED17374EED93ED3B41023426320181E91C61F6CAD3341315BA081049000690FE557CADCEF3E8D99C1E1684C05019D4C5EF25165E043CDF77B685AFB38D625E32410F5C915A0A4ED05D";
		String decryptResult = RSA.decrypt(encryptResult);
		System.out.println("解密后：" + new String(decryptResult));

	}

}