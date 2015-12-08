/**
 * <p>Title: PublicUtil.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2013-1-4
 * @version 1.0
 */
package cn.adwalker.ad.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.adwalker.ad.bean.Data;
import cn.adwalker.ad.bean.Result;
import cn.adwalker.ad.exception.ErrorInfo;

/**
 * <p>
 * Title: PublicUtil
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author www.adwalker.cn
 * @date 2013-1-4
 */
public class PublicUtil {

	private static final Logger log = LoggerFactory.getLogger(PublicUtil.class);

	/**
	 * 解析参数
	 * 
	 * @param parameters
	 *            imei=863291001183571&netEnv=wifi&areaCode=100023&telNum=
	 *            13804456375 &operator=chinamobile&sw=180&sh=360
	 * @return
	 */
	public static Map<String, String> parseParameterMap(String parameters) {
		Map<String, String> paramsMap = null;
		if (!StringUtils.isEmpty(parameters)) {
			paramsMap = new HashMap<String, String>();
			String paramsStr = new String(parameters);
			String[] params = paramsStr.split("&");
			for (int i = 0; i < params.length; i++) {
				String param = params[i];
				String[] _param = param.split("=");
				if (_param != null && _param.length == 2) {
					paramsMap.put(_param[0], _param[1]);
				}
			}
		}
		return paramsMap;
	}

	/**
	 * 解析参数
	 * 
	 * @param parameters
	 *            imei=863291001183571&netEnv=wifi&areaCode=100023&telNum=
	 *            13804456375 &operator=chinamobile&sw=180&sh=360
	 * @return
	 */
	public static Map<String, String> parseParameter(String parameters) {
		Map<String, String> map = null;
		if (!StringUtils.isEmpty(parameters)) {
			map = new HashMap<String, String>();
			String key = null;
			String value = null;
			String str = parameters;
			String arr[] = str.split("[=]");
			for (int i = 0; i < arr.length; i++) {
				if (key == null) {
					key = arr[i];
				} else {
					if (arr[i].indexOf("&") != -1) {
						value = arr[i].substring(0, arr[i].lastIndexOf("&"));
						map.put(key, value);
						key = arr[i].substring(arr[i].lastIndexOf("&") + 1,
								arr[i].length());
					} else {
						value = arr[i];
						map.put(key, value);
					}
				}
			}
		}
		return map;
	}

	/**
	 * 装载errorVO
	 * 
	 * @param code
	 * @return
	 */
	public static ErrorInfo installErrorJVO(String code) {
		String msg = MessageUtil.getString(code);
		if (msg != null && !msg.equals("")) {
			ErrorInfo errorJsonVO = new ErrorInfo();
			errorJsonVO.setCode(code);
			errorJsonVO.setMessage(MessageUtil.getString(code));
			return errorJsonVO;
		}
		return null;
	}

	/**
	 * 封装返回值
	 * 
	 * @param response
	 *            响应
	 * @param status
	 *            状态
	 * @param data
	 *            数据
	 * @throws IOException
	 */
	public static void returnValue(HttpServletResponse response, String status,Data data) {
		Result vo = new Result(status, data);
		String json = vo.toString();
		if (status.equals(AppConstant.STATUS_ERROR)) {
			log.debug(json);
		}
		log.info(json);
		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.write(json);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	/**
	 * 判断参数是否为null
	 * 
	 * @param params
	 * @return
	 */
	public static boolean paramsIsNull(String... params) {
		if (params != null && params.length != 0) {
			for (int i = 0; i < params.length; i++) {
				String str = params[i];
				if (StringUtils.isEmpty(str)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * 装载errorVO
	 * 
	 * @param code
	 * @return
	 */
	public static ErrorInfo installErrorVO(String code) {
		String msg = MessageUtil.getString(code);
		if (msg != null && !msg.equals("")) {
			return new ErrorInfo(code, MessageUtil.getString(code));
		}
		return null;
	}

	/**
	 * 计算共多少页
	 * 
	 * @param resultSize
	 *            返回值数量
	 * @param pageSize
	 *            每页显示条数
	 * @return
	 */
	public static int calculatePageCount(int resultSize, int pageSize) {
		int pageCount = 0;
		if (resultSize <= pageSize) {
			pageCount = 1;
		} else {
			if (resultSize % pageSize != 0) {
				pageCount = (resultSize / pageSize) + 1;
			} else {
				pageCount = resultSize / pageSize;
			}
		}
		return pageCount;
	}

	/**
	 * 根据用户基本信息拼接uuid
	 * 
	 * @param imei
	 * @param telModel
	 * @param screenWidth
	 * @param screenHeigh
	 * @param brand
	 * @return
	 */
	public static String installUuid(String imei, String telModel,
			String screenWidth, String screenHeigh, String brand) {
		//String uuidStr = new StringBuffer().append(imei).append(telModel).append(screenWidth).append(screenHeigh).append(brand).toString();
		String uid = imei+telModel+screenWidth+screenHeigh+brand;
		String uuid= new cn.adwalker.ad.picker.util.MD5().getMD5ofStr(uid);
		//System.out.println(uid+"==========="+uid.length()+"============="+uuid);
		return uuid;
	}
	
	public static String installUuid(String media_user,String app_id) {
		String uuidStr = new StringBuffer().append(media_user).append(app_id).toString();
		String uuid= new cn.adwalker.ad.picker.util.MD5().getMD5ofStr(uuidStr);
		//System.out.println(uid+"==========="+uid.length()+"============="+uuid);
		return uuid;
	}

	/**
	 * 拼接sessionId
	 * 
	 * @param uuid
	 *            终端用户ID
	 * @param developerId
	 *            开发者ID
	 * @param appId
	 *            应用ID
	 * @param channel
	 *            渠道
	 * @param flag
	 *            开始结束标识 0:开始 1:结束
	 * @return
	 */
	public static String installSessionId(String uuid, Long developerId,
			Long appId, String channel) {
		StringBuffer sbStr = new StringBuffer();
		sbStr.append(uuid).append("_").append(developerId).append("_")
				.append(appId).append("_").append(channel);
		return sbStr.toString();
	}

	/**
	 * 格式转换应用大小 单位"M"
	 */
	public static String getSize(long size) {
		return new DecimalFormat("0.##").format(size * 1.0 / (1024 * 1024))
				+ "M";
	}
}
