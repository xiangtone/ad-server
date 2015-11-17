package cn.adwalker.ad.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * IP工具类. 根据httpServletRequest对象获取http请求来源地的ip字符串
 */
public class IpUtil {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(IpUtil.class);

	/** 匹配点分割格式的ip地址的模式字符串 */
	private static Pattern pattern = Pattern.compile("([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}");

	/**
	 * 判断是否符合ip格式, 不符合格式则返回null
	 * 
	 * @param ipString
	 *            原始ip字符串
	 * @return 形如'1.2.3.4'格式的ip字符串; 如果原始ip字符串不是该格式, 则返回null
	 */
	@SuppressWarnings("unused")
	private static String isDottedIp(String ipString) {
		if (ipString != null) {
			Matcher matcher = pattern.matcher(ipString);
			if (matcher.matches()) {
				return matcher.group();
			}
		}
		return null;
	}

	/**
	 * 获取发送请求的客户端(或最后一个代理)的 ip字符串, 形如'1.2.3.4'格式
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @return request中的形如'1.2.3.4'格式的ip字符串; 如果原始ip字符串不是该格式, 则返回null,
	 *         并且输出所有的request头信息
	 */
	public static String getIp(HttpServletRequest request) {
//		String ip = request.getHeader("X-Forwarded-For");
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = isDottedIp(request.getHeader("Proxy-Client-IP"));
//			if (ip != null) {
//				return ip;
//			}
//		} else {
//			if (ip.indexOf(",") != -1) {
//				String[] s = ip.split(",");
//				for (int i = 0; i < s.length; i++) {
//					if (s[i] != null && !"".equals(s[i]) && !"unknown".equals(s[i])) {
//						ip = isDottedIp(s[i].trim());
//						if (ip != null) {
//							return ip;
//						}
//					}
//				}
//			} else if (ip.indexOf(";") != -1) {
//				String[] s = ip.split(";");
//				for (int i = 0; i < s.length; i++) {
//					if (s[i] != null && !"".equals(s[i]) && !"unknown".equals(s[i])) {
//						ip = isDottedIp(s[i].trim());
//						if (ip != null) {
//							return ip;
//						}
//					}
//				}
//			} else {
//				if ("unknown".equals(ip)) {
//					ip = isDottedIp(ip.trim());
//					if (ip != null) {
//						return ip;
//					}
//				}
//			}
//		}
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = isDottedIp(request.getHeader("WL-Proxy-Client-IP"));
//			if (ip != null) {
//				return ip;
//			}
//		}
//		if (ip != null && ip.indexOf(' ') != -1) {
//			ip = isDottedIp(ip.substring(0, ip.indexOf(' ')));
//			if (ip != null) {
//				return ip;
//			}
//		}
//		if (ip == null) {
//			if (request.getHeader("x-forwarded-for") != null || request.getHeader("Proxy-Client-IP") != null || request.getHeader("WL-Proxy-Client-IP") != null) {
//				// 执行到这里, ipString要么为null, 要么不是点分割的数字格式, 需要输出所有的request头信息供调试
//				Enumeration headers = request.getHeaderNames();
//				while (headers.hasMoreElements()) {
//					String headerName = headers.nextElement().toString();
//					log.warn(headerName + " --> " + request.getHeader(headerName));
//				}
//				log.warn("--------request.getRemoteAddr:" + request.getRemoteAddr() + "--------");
//			}
//			ip = request.getRemoteAddr();
//		}
//		return ip;
		String address = request.getHeader("X-Real-IP");
		if (address != null && address.length() > 0
				&& !"unknown".equalsIgnoreCase(address)) {
			return address;
		}
		return request.getRemoteAddr();
	}
}