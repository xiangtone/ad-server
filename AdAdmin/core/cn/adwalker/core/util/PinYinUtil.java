package cn.adwalker.core.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * Title: PinYinUtil
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-5-29
 */
public abstract class PinYinUtil {

	private PinYinUtil() {
	}

	/**
	 * 
	* <p>Title: getSpelle</p>
	* <p>Description:转换成拼音</p>
	* @param ch
	* @return
	* @author cuidd
	* @date 2013-5-29
	* @return String
	* @version 1.0
	 */
	public static String toPinYin(String ch) {
		String login_name = "";
		if (!StringUtils.isEmpty(ch)) {
			try {
				char[] chars = ch.toCharArray();
				for (int i = 0; i < chars.length; i++) {
					String[] pinYin;

					pinYin = PinyinHelper.toHanyuPinyinStringArray(chars[i],
							getDefaultOutputFormat());

					// 当转换不是中文字符时,返回null
					if (pinYin != null) {
						login_name += pinYin[0];
					} else {
						login_name += chars[i];
					}
				}
			} catch (BadHanyuPinyinOutputFormatCombination e) {
			}
		}

		return login_name;
	}

	/**
	 * <p>
	 * Title: getDefaultOutputFormat
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @author lichuang
	 * @date 2013-5-29
	 * @return HanyuPinyinOutputFormat
	 * @version 1.0
	 */
	private static HanyuPinyinOutputFormat getDefaultOutputFormat() {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 小写
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 没有音调数字
		format.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);// u显示
		return format;
	}

}
