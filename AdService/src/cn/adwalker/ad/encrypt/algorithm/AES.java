package cn.adwalker.ad.encrypt.algorithm;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES 算法
 * 
 * @author gary
 * 
 */
public class AES extends Encrypt {

	private final static String ALGORITHM = "AES";
	// java.security.InvalidParameterException: Wrong keysize: must be equal to
	// 128, 192 or 256
	// 128、192 和 256 位密钥
	private final static int ALGORITHM_KEYSIZE = 128;

	private final static String ENCRYPT_RANDOM = "SHA1PRNG";

	/**
	 * AES加密算法
	 */
	public AES() {
	}

	/**
	 * 加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param keyWord
	 *            加密密钥
	 * @return byte[] 加密后的字节数组
	 */
	public static byte[] encrypt(String content, String keyWord)
			throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM);
		SecureRandom secureRandom = SecureRandom.getInstance(ENCRYPT_RANDOM);
		secureRandom.setSeed(keyWord.getBytes(STRING_CHARSET));
		kgen.init(ALGORITHM_KEYSIZE, secureRandom);
		SecretKey secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		SecretKeySpec key = new SecretKeySpec(enCodeFormat, ALGORITHM);
		Cipher cipher = Cipher.getInstance(ALGORITHM);// 创建密码器
		byte[] byteContent = content.getBytes(STRING_CHARSET);
		cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
		byte[] result = cipher.doFinal(byteContent);
		return result; // 加密
	}

	/**
	 * 解密
	 * 
	 * @param content
	 *            待解密内容
	 * @param keyWord
	 *            解密密钥
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] content, String keyWord)
			throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM);
		SecureRandom secureRandom = SecureRandom.getInstance(ENCRYPT_RANDOM);
		secureRandom.setSeed(keyWord.getBytes(STRING_CHARSET));
		kgen.init(ALGORITHM_KEYSIZE, secureRandom);
		SecretKey secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		SecretKeySpec key = new SecretKeySpec(enCodeFormat, ALGORITHM);
		Cipher cipher = Cipher.getInstance(ALGORITHM);// 创建密码器
		cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
		byte[] result = cipher.doFinal(content);
		return result; // 加密

	}

	/**
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密钥
	 * @return String 加密后的字符串
	 * @throws Exception
	 */
	public static String encrypttoStr(String content, String password)
			throws Exception {
		return byte2Hex(encrypt(content, password));
	}

	/**
	 * @param content
	 *            待解密内容(字符串)
	 * @param keyWord
	 *            解密密钥
	 * @return byte[]
	 */
	public static byte[] decrypt(String content, String keyWord)
			throws Exception {
		return decrypt(hex2Byte(content), keyWord);
	}

	public static void main(String[] args) throws Exception {
		String content = "imei=865061000259321&telModel=w700&netEnv=WIFIAndCMNET&areaCode=460014502787528&telNum=&operator=China Unicom&os=Android2.2&brand=tianyu&sw=320&sh=533";
		content = "12345";
		String Key = "qianchi_escore_other_encrypt";

		// 加密
		System.out.println("加密前：" + content);
		String encryptResult = encrypttoStr(content, Key);
		System.out.println("加密后：" + encryptResult);
		// 解密
		byte[] decryptResult = decrypt(encryptResult, Key);
		System.out.println("解密后：" + new String(decryptResult));
	}
}