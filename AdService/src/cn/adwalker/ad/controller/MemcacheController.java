/**
 * <p>Title: MemcacheController.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2013-1-14
 * @version 1.0
 */
package cn.adwalker.ad.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.bean.ResponseResult;
import cn.adwalker.ad.cache.element.UserInfo;
import cn.adwalker.ad.pool.MemCached;
import cn.adwalker.ad.service.IMemcacheService;

/**
 * <p>
 * Title: MemcacheController
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author www.adwalker.cn
 * @date 2013-1-14
 */
@Controller
public class MemcacheController {
	private static final Logger log = LoggerFactory
			.getLogger(MemcacheController.class);

	/** 依赖注入 **/
	@Resource
	private IMemcacheService memcacheService;
	@Resource
	MemCached memCached;

	@RequestMapping("/cache/updateCache.do")
	public String updateCache(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String adId = request.getParameter("adIds");
			log.info("/common/updateCache.do?adIds" + adId);
			memcacheService.updateCache(adId);

			ResponseResult resposeResult = new ResponseResult();
			resposeResult.setMessage("确认成功");
			resposeResult.setSuccess("true");
			String responseJson = toJson(resposeResult);
			OutputStream out = response.getOutputStream();
			out.write(responseJson.getBytes("UTF-8"));
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			// 封装返回
			ResponseResult resposeResult = new ResponseResult();
			resposeResult.setMessage("参数异常");
			resposeResult.setSuccess("false");
			String responseJson = toJson(resposeResult);
			try {
				OutputStream out = response.getOutputStream();
				out.write(responseJson.getBytes("UTF-8"));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

	@RequestMapping("/cache/updateAppCache.do")
	public String updateAppCache(HttpServletRequest request,
			HttpServletResponse response) {
		String appId = request.getParameter("appId");
		try {

			UpdateAppCacheRunable updateAppCacheRunable = new UpdateAppCacheRunable(
					appId);
			updateAppCacheRunable.start();
			ResponseResult resposeResult = new ResponseResult();
			resposeResult.setMessage("确认成功");
			resposeResult.setSuccess("true");
			String responseJson = toJson(resposeResult);
			OutputStream out = response.getOutputStream();
			out.write(responseJson.getBytes("UTF-8"));
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			// 封装返回
			ResponseResult resposeResult = new ResponseResult();
			resposeResult.setMessage("参数异常");
			resposeResult.setSuccess("false");
			String responseJson = toJson(resposeResult);
			try {
				OutputStream out = response.getOutputStream();
				out.write(responseJson.getBytes("UTF-8"));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

	@RequestMapping("/cache/getAppCache.do")
	public String getAppCache(HttpServletRequest request,
			HttpServletResponse response, Long app_id) {
	
		return null;
	}

	@RequestMapping("/cache/updateConfigFinance.do")
	public String updateMoneyScoreRateCache(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			memcacheService.updateConfigFinance();

			ResponseResult resposeResult = new ResponseResult();
			resposeResult.setMessage("确认成功");
			resposeResult.setSuccess("true");
			String responseJson = toJson(resposeResult);
			OutputStream out = response.getOutputStream();
			out.write(responseJson.getBytes("UTF-8"));
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			// 封装返回
			ResponseResult resposeResult = new ResponseResult();
			resposeResult.setMessage("参数异常");
			resposeResult.setSuccess("false");
			String responseJson = toJson(resposeResult);
			try {
				OutputStream out = response.getOutputStream();
				out.write(responseJson.getBytes("UTF-8"));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	@RequestMapping("/cache/clearUserCahce.do")
	public String clearUserCache(HttpServletRequest request,
			HttpServletResponse response,String uuid) {

		try {
			memcacheService.clearUserCache(uuid);
			ResponseResult resposeResult = new ResponseResult();
			resposeResult.setMessage("清除成功");
			resposeResult.setSuccess("true");
			String responseJson = toJson(resposeResult);
			OutputStream out = response.getOutputStream();
			out.write(responseJson.getBytes("UTF-8"));
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			// 封装返回
			ResponseResult resposeResult = new ResponseResult();
			resposeResult.setMessage("参数异常");
			resposeResult.setSuccess("false");
			String responseJson = toJson(resposeResult);
			try {
				OutputStream out = response.getOutputStream();
				out.write(responseJson.getBytes("UTF-8"));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}


	@RequestMapping("/cache/getCache.do")
	public String getCache(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String uuid = request.getParameter("uuid");
		String s = "";
		UserInfo userInfo = (UserInfo) memCached.get(uuid);
		if (userInfo == null) {
			s = "userInfo is null";
		} else {
			s = "userInfo.area" + userInfo.getAreaCode() + "  id"
					+ userInfo.getId();
		}
		ResponseResult resposeResult = new ResponseResult();
		resposeResult.setMessage(s);
		resposeResult.setSuccess("true");
		String responseJson = toJson(resposeResult);
		OutputStream out = response.getOutputStream();
		out.write(responseJson.getBytes("UTF-8"));
		out.close();
		return null;
	}
	
	
	@RequestMapping("/cache/signCache.do")
	public String updateSignCache(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		memcacheService.updateSignCache();
		ResponseResult resposeResult = new ResponseResult();
		resposeResult.setMessage("ok");
		resposeResult.setSuccess("true");
		String responseJson = toJson(resposeResult);
		OutputStream out = response.getOutputStream();
		out.write(responseJson.getBytes("UTF-8"));
		out.close();
		return null;
	}
	
	
	@RequestMapping("/cache/updateChannelCache.do")
	public String updateChannelCache(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		memcacheService.updateChannelCache();
		ResponseResult resposeResult = new ResponseResult();
		resposeResult.setMessage("ok");
		resposeResult.setSuccess("true");
		String responseJson = toJson(resposeResult);
		OutputStream out = response.getOutputStream();
		out.write(responseJson.getBytes("UTF-8"));
		out.close();
		return null;
	}

	
	@RequestMapping("/cache/updateBlackCache.do")
	public String updateBlackCache(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String appId = request.getParameter("appId");
		memcacheService.updateBlackCache(Long.parseLong(appId));
		ResponseResult resposeResult = new ResponseResult();
		resposeResult.setMessage("ok");
		resposeResult.setSuccess("true");
		String responseJson = toJson(resposeResult);
		OutputStream out = response.getOutputStream();
		out.write(responseJson.getBytes("UTF-8"));
		out.close();
		return null;
	}
	
	
	
	
	@RequestMapping("/cache/updateSspCache.do")
	public String updateSspCache(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String id = request.getParameter("id");
		memcacheService.updateSspCache(Long.parseLong(id));
		ResponseResult resposeResult = new ResponseResult();
		resposeResult.setMessage("ok");
		resposeResult.setSuccess("true");
		String responseJson = toJson(resposeResult);
		OutputStream out = response.getOutputStream();
		out.write(responseJson.getBytes("UTF-8"));
		out.close();
		return null;
	}
	
	
	/*****
	 * 将对象转换成json
	 * 
	 * @param resposeResult
	 * @return
	 */
	public static String toJson(ResponseResult resposeResult) {
		// 将对象转换为json
		JsonGenerator gen = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			StringWriter sw = new StringWriter();
			gen = new JsonFactory().createJsonGenerator(sw);
			mapper.writeValue(gen, resposeResult);
			return sw.toString();
		} catch (Exception e) {
		} finally {
			try {
				if (gen != null) {
					gen.close();
				}
			} catch (IOException e) {
			}
		}
		return null;
	}

	private class UpdateAppCacheRunable extends Thread {
		String appId;
		UpdateAppCacheRunable(String appId) {
			this.appId = appId;
		}

		@Override
		public void run() {
			try {
				sleep(2000);
				memcacheService.updateDevCurrencyCache(appId);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}