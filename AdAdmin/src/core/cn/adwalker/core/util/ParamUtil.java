/**
 * <p>Title: ParamUtil.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-8-23
 * @version 1.0
 */
package cn.adwalker.core.util;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * Title: ParamUtil
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-8-23
 */
public abstract class ParamUtil {
	public ParamUtil() {
	}

	public static String addParam(String url, String paramName,
			String paramValue) {
		String s = null;
		if (StringUtils.isNotEmpty(url)) {
			if (url.indexOf("?") != -1) {
				url = url + "&" + paramName + "=" + paramValue;
			} else {
				url = url + "?" + paramName + "=" + paramValue;
			}
		}
		return s;

	}
}
