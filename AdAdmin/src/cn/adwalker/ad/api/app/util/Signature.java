/**
 * <p>Title: Signature.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-8-8
 * @version 1.0
 */
package cn.adwalker.ad.api.app.util;

import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import cn.adwalker.core.util.ConfigUtil;
import cn.adwalker.core.util.DateUtil;
import cn.adwalker.core.util.MD5;

/**
 * <p>
 * Title: Signature
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-8-8
 */
public abstract class Signature {

	/**
	 * 
	 * <p>
	 * Title: signature
	 * </p>
	 * <p>
	 * Description:签名
	 * </p>
	 * 
	 * @param key
	 * @return
	 * @author cuidd
	 * @date 2013-8-8
	 * @return String
	 * @version 1.0
	 */
	public static String signature() {
		String key = ConfigUtil.getString("api.kuaiyou.signature.key");
		String s = null;
		if (!StringUtils.isEmpty(key)) {
			MD5 m = new MD5();
			String timestamp = DateUtil.formatDate(new Date(),
					DateUtil.PARTTERN_DATE_TIME);
			s = new String(Base64.encodeBase64((timestamp + "_" + m
					.getMD5ofStr(key + timestamp).toLowerCase()).getBytes()));

		}
		return s;
	}

	/**
	 * 
	 * <p>
	 * Title: vaildateSignature
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param s
	 * @param key
	 * @return
	 * @author cuidd
	 * @date 2013-8-8
	 * @return boolean
	 * @version 1.0
	 */
	public static boolean vaildateSignature(String s) {
		String key = ConfigUtil.getString("api.kuaiyou.signature.key");
		boolean b = false;
		if (!StringUtils.isEmpty(key)) {
			MD5 m = new MD5();
			String timestamp = new String(Base64.decodeBase64(s.getBytes()));
			if (timestamp.indexOf("_") != -1) {
				timestamp = timestamp.split("_")[0];
				String temp = new String(
						Base64.encodeBase64((timestamp + "_" + m.getMD5ofStr(
								key + timestamp).toLowerCase()).getBytes()));
				b = temp.equals(s);
			}
		}
		return b;
	}
}
