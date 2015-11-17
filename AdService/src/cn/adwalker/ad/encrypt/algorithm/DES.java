package cn.adwalker.ad.encrypt.algorithm;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * 
 * @author gary
 *
 */
public class DES {
	public static int _DES = 1;
	public static int _DESede = 2;
	public static int _Blowfish = 3;
	private static Cipher cipher;
	private static SecretKey secretKey;
	private static String algorithm;

	private static int keySize = 56;

	private void selectAlgorithm(int al) {
		switch (al) {
		default:
		case 1:
			algorithm = "DES";
			keySize = 56;
			break;
		case 2:
			algorithm = "DESede";
			//keysize: must be equal to 112 or 168
			keySize = 168;
			break;
		case 3:
			algorithm = "Blowfish";
			// Keysize must be multiple of 8, and can only range from 32 to 448
			// (inclusive)
			keySize = 128;
			break;
		}
	}

	public DES(int algorithm) throws Exception {
		selectAlgorithm(algorithm);
		cipher = Cipher.getInstance(DES.algorithm);
	}

	private Key getKey(byte[] key) throws Exception {
		KeyGenerator keygen = KeyGenerator.getInstance(algorithm);
		SecureRandom sr = new SecureRandom(key);
		keygen.init(keySize, sr);
		secretKey = keygen.generateKey();
		return secretKey;

	}

	public byte[] encrypt(byte[] data, byte[] key) throws Exception {
		Key sk = getKey(key);
		cipher.init(Cipher.ENCRYPT_MODE, sk);
		return cipher.doFinal(data);
	}

	public byte[] decrypt(byte[] encdata, byte[] enckey) throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		return cipher.doFinal(encdata);
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return String
	 */
	public static String byte2Hex(byte[] buf) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return byte[]
	 */
	public static byte[] hex2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		
		String msg  = "123";
		String key  = "1";
		String key2 = "12";
		
		System.out.println("加密数据:" + msg);
		System.out.println("-------------------------------------------" );
		
		
		DES des = new DES(3); // 声明DES
		
		byte[] a = des.encrypt(msg.getBytes(), key.getBytes());
		System.out.println("加密后的数据为: " + byte2Hex(a));
		byte[] b = des.decrypt(a, key.getBytes());
		System.out.println("解密后的数据: " + new String(b));
		
		
		System.out.println("-------------------------------------------" );
		
		
		byte[] a2 = des.encrypt(msg.getBytes(), key2.getBytes());
		System.out.println("加密后的数据为: " + byte2Hex(a2));
		byte[] b2 = des.decrypt(a2, key.getBytes());
		System.out.println("解密后的数据: " + new String(b2));
		

	}
}