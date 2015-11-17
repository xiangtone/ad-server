package cn.adwalker.ad.api.app.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 3DES加密工具类
 */
public class Des3 {
	// 密钥
	private final static String secretKey = "lx100$#365#$";
	// 向量
	private final static String iv = "76540123";

	public final static String iv_android = "12345678";
	// 加解密统一使用的编码方式
	private final static String encoding = "utf-8";
	/**
	 * 3DES加密
	 * 
	 * @param plainText
	 *            普通文本
	 * @return
	 * @throws Exception
	 */
	public static String encode(String plainText) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);

		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
		return new String(Base64.encodeBase64(encryptData));
	}

	/**
	 * 3DES加密
	 * 
	 * @param plainText
	 *            普通文本
	 * @return
	 * @throws Exception
	 */
	public static String encode(String plainText, byte[] b) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);

		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(b);
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
		return new String(Base64.encodeBase64(encryptData));
	}

	/**
	 * 
	 * <p>
	 * Title: decode
	 * </p>
	 * <p>
	 * Description:解密
	 * </p>
	 * 
	 * @param encryptText
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-7-4
	 * @return String
	 * @version 1.0
	 */
	public static String decode(String encryptText) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

		byte[] decryptData = cipher
				.doFinal(org.apache.commons.codec.binary.Base64
						.decodeBase64(encryptText.getBytes()));

		return new String(decryptData, encoding);
	}

	/**
	 * 
	 * <p>
	 * Title: decode
	 * </p>
	 * <p>
	 * Description:解密文本
	 * </p>
	 * 
	 * @param encryptText
	 * @param b
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-7-4
	 * @return String
	 * @version 1.0
	 */
	public static String decode(String encryptText, byte[] b) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(b);
		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
		byte[] decryptData = cipher
				.doFinal(org.apache.commons.codec.binary.Base64
						.decodeBase64(encryptText.getBytes()));
		return new String(decryptData, encoding);
	}
}
