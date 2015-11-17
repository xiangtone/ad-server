/**
 * 
 */
package cn.adwalker.core.util.lang;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 
* <p>Title: StringUtil</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author    cuidd
* @date       2013-8-2
 */
public class StringUtil extends org.apache.commons.lang.StringUtils {
	/**
	 * 把一个字符串中连续的几个空格替换成一个@，字符串前后各添一个@
	 * 
	 * @param str
	 * @return
	 */
	public static String getString(String str) {
		String newBlankList = "";
		try {
			String[] black = str.split(" ");
			for (int i = 0; i < black.length; i++) {
				String subString = black[i];
				if (subString.equals(" ") || subString.equals("")) {
					continue;
				}
				newBlankList += "@" + subString.trim();
			}
			newBlankList += "@";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newBlankList;
	}

	/**
	 * 用于List界面的字符串截取显示(字节方式截取)
	 * 
	 * @param str
	 *            字符串
	 * @param length
	 *            截取长度，字节
	 * @param addDot
	 *            截取后字符串是否加点
	 * @return
	 */
	public static String byteSubstring(String str, int length, boolean addDot) {
		if (isEmpty(str))
			return "";

		byte[] b = str.getBytes();

		// 加3个点的长度
		if (addDot)
			length -= 3;

		if (b.length <= length) {
			return str;
		}

		try {
			str = bSubstring(str, length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addDot ? str + "..." : str;

	}

	/**
	 * 
	 * <p>
	 * Title: encode
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param s
	 * @return
	 * @author cuidd
	 * @date 2013-8-2
	 * @return String
	 * @version 1.0
	 */
	public static String encode(String s) {
		String str = null;
		if (!isEmpty(s)) {
			try {
				str=URLEncoder.encode(s, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return str;

	}


	/**
	 * 
	 * <p>
	 * Title: decode
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param s
	 * @return
	 * @author cuidd
	 * @date 2013-8-2
	 * @return String
	 * @version 1.0
	 */
	public static String decode(String s) {
		String str = null;
		if (!isEmpty(s)) {
			try {
				str=URLDecoder.decode(s, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return str;

	}

	/**
	 * 
	 * <p>
	 * Title: bSubstring
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param s
	 * @param length
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-7-10
	 * @return String
	 * @version 1.0
	 */
	public static String bSubstring(String s, int length) throws Exception {
		byte[] bytes = s.getBytes("Unicode");
		int n = 0; // 表示当前的字节数
		int i = 2; // 要截取的字节数，从第3个字节开始
		for (; i < bytes.length && n < length; i++) {
			// 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
			if (i % 2 == 1) {
				n++; // 在UCS2第二个字节时n加1
			} else {
				// 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
				if (bytes[i] != 0) {
					n++;
				}
			}
		}
		// 如果i为奇数时，处理成偶数
		if (i % 2 == 1) {
			// 该UCS2字符是汉字时，去掉这个截一半的汉字
			if (bytes[i - 1] != 0) {
				i = i - 1;
				// 该UCS2字符是字母或数字，则保留该字符
			} else {
				i = i + 1;
			}
		}

		return new String(bytes, 0, i, "Unicode");
	}
}
