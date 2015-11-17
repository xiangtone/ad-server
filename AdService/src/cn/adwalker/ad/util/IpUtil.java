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
	public final static String IP1 = "x-forwarded-for";
	public final static String IP2 = "Proxy-Client-IP";
	public final static String IP3 = "WL-Proxy-Client-IP";
	public final static String UNKNOWN = "unknown";
	public final static String COMMA = ",";

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

		String address = request.getHeader("X-Real-IP");
		if (address != null && address.length() > 0
				&& !"unknown".equalsIgnoreCase(address)) {
			return address;
		}
		return request.getRemoteAddr();
	}
	
	/**
	 * @author luoyouhua  2014-03-18  
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader(IP1);
		if (ip == null || ip.length() == 0|| UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader(IP2);
		}
		if (ip == null || ip.length() == 0|| UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader(IP3);
		}
		if (ip == null || ip.length() == 0|| UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.contains(COMMA)) {
			int i = ip.indexOf(COMMA);
			ip = ip.substring(0, i);
		}
		return ip;
	}
	
}