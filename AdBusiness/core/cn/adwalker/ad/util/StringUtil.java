/**
 * 
 */
package cn.adwalker.ad.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;

/**
 * @author fangguanhong
 * 
 */
public class StringUtil extends StringUtils {
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

	public static String encode(String s) {
		String str = null;
		if (!StringUtil.isEmpty(s)) {
			try {
				str = URLEncoder.encode(s, "utf-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}

		}
		return str;
	}

	public static String decode(String s) {
		String str = null;
		if (!StringUtil.isEmpty(s)) {
			try {
				str = URLDecoder.decode(s, "utf-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}

		}
		return str;

	}
}
