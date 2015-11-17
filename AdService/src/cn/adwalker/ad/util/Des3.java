package cn.adwalker.ad.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 3DES加密工具类
 */
public class Des3 {
	private final static String secretKey = "adwalkerwifi@lx100$#365#";
	// 向量
	private final static String iv = "76540123";

	public final static String iv_androidd = "12345678";
	
	// 加解密统一使用的编码方式
	private final static String encoding = "utf-8";

	public static void main(String[] args) throws Exception {
		//String str = "uuid=038bcd079067797996c78200a6060524&id=6699&pageType=0&bannerTag=0&devUserId=null&version=androidV2.1.0&appkey=AWPXESYAI8OM6XPN91RMC6KU0462GLXL2O&channel=adwalker&imsi=460022734751797";
		//String s = Des3.encode(str);
		
		System.out.println(Des3.decode("klfZrLKUJJEAdacBkyavjEYaNcdHMZxsB4UmZr1iFezx6BfZ0ee7lEVEDpdN lUd5pz8rFVFKXArlwYcpp+aDeA=="));
		//System.out.println(encode("appId=A820663F693D&pageNo=0"));
		//System.out.println(decode("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 RdVwP9Avf+/+sA=="));
		//System.out.println(URLEncoder.encode("iCbYK/Z9GuTjLx6LIHdvliEjyidwWrql06GhL9b1mR8=", "utf-8"));
		//System.out.println(URLDecoder.decode("%", "utf-8"));
	}

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
		return Base64.encode(encryptData);
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
		return Base64.encode(encryptData);
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
