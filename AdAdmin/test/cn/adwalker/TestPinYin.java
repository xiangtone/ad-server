/**
 * <p>Title: TestPinYin.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-5-29
 * @version 1.0
 */
package cn.adwalker;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * Title: TestPinYin
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-5-29
 */
public class TestPinYin extends TestBase {

	public void testPinyin() {
		System.out.println(getPinYin("波哥是大嗓门"));

	}

	private String getPinYin(String ch) {
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

	private HanyuPinyinOutputFormat getDefaultOutputFormat() {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 小写
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 没有音调数字
		format.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);// u显示
		return format;
	}

}
