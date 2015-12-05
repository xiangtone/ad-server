package cn.adwalker.ad.api.app.util;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public abstract class Crypts {

	static int ENC_16[] = { 4, 12, 5, 14, 1, 15, 2, 13, 10, 11, 7, 0, 3, 6, 9,
			8 };
	static int DEC_16[] = { 11, 4, 6, 12, 0, 2, 13, 10, 15, 14, 8, 9, 1, 7, 3,
			5 };

	public static BASE64Encoder b64encoder = new BASE64Encoder();
	public static BASE64Decoder b64decoder = new BASE64Decoder();

	public static final int key = 0xc2;

	static char[] byteToChar(byte[] src) {
		char[] dst = new char[src.length];
		for (int i = 0; i < src.length; i++) {
			int nVal = src[i];
			if (nVal < 0)
				nVal += 0x100;
			dst[i] = (char) nVal;
		}
		return dst;
	}

	static byte[] charToByte(char[] src) {
		byte[] dst = new byte[src.length];
		for (int i = 0; i < src.length; i++)
			dst[i] = (byte) src[i];
		return dst;
	}

	public static String xorMapEncrypt(int key, String ss) throws IOException {
		char[] srcBytes = byteToChar(ss.getBytes("utf-8"));
		char[] dstBytes = new char[srcBytes.length + 1];

		int keyBt = key % 0x100;
		dstBytes[srcBytes.length] = (char) (byte) keyBt;
		for (int j = 0; j < srcBytes.length; j++) {
			int b1 = srcBytes[j];
			int b2 = (b1 ^ keyBt) & 0xff;
			int c1 = b2 % 16;
			int c2 = b2 / 16;
			c1 = ENC_16[c1];
			c2 = ENC_16[c2];

			int b3 = c2 * 16 + c1;
			dstBytes[j] = (char) b3;
		}
		String isoString = b64encoder.encode(charToByte(dstBytes));
		return isoString;
	}

	public static String xorMapDecrypt(String ss) throws IOException {
		char[] srcBytes = byteToChar(b64decoder.decodeBuffer(ss));
		if (srcBytes.length < 1) {
			return "";
		}

		char[] dstBytes = new char[srcBytes.length - 1];
		int keyBt = (int) srcBytes[dstBytes.length];
		if (keyBt < 0)
			keyBt += 0x100;
		for (int j = 0; j < dstBytes.length; j++) {
			int b1 = srcBytes[j];
			int c1 = b1 % 16;
			int c2 = b1 / 16;
			c1 = DEC_16[c1];
			c2 = DEC_16[c2];

			int b3 = c2 * 16 + c1;
			int b2 = (b3 ^ keyBt) & 0xff;
			dstBytes[j] = (char) b2;
		}
		return new String(charToByte(dstBytes), "utf-8");
	}
}