/**
* <p>Title: MemcacheChannelController.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-7-25
* @version 1.0
*/
package cn.adwalker.IOSChannel.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.IOSChannel.picker.util.StringUtil;
import cn.adwalker.IOSChannel.picker.xmemcached.PickerCache;
import cn.adwalker.IOSChannel.service.IMemcacheChannelService;
import cn.adwalker.core.util.JsonUtils;
import cn.adwalker.core.vo.ResponseResult;

/**
 * <p>Title: MemcacheChannelController</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-7-25
 */
@Controller
public class MemcacheChannelController {
	private static final Logger log = LoggerFactory.getLogger(MemcacheChannelController.class);
	@Resource
	private IMemcacheChannelService memcacheChannelService;
	@RequestMapping("/cache/updateCache.do")
	public String updateAppCache(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult resposeResult = new ResponseResult();
		try {
			//add by jief
			memcacheChannelService.updateCache();
			resposeResult.setMessage("缓存清理完毕");
			resposeResult.setSuccess("true");
		} catch (Exception e) {
			log.error("缓存清理出错了。。",e);
			resposeResult.setMessage("参数异常");
			resposeResult.setSuccess("false");
		}
		String responseJson =JsonUtils.toJson(resposeResult);
		JsonUtils.sendJson(response,responseJson);
		return null;
	}
	
	@RequestMapping("/cache/deletecache.do")
	public void deleteCache(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult resposeResult = null;
		try {
			String key = request.getParameter("key");
			if(!StringUtil.isEmpty(key)){
				PickerCache.deleteCache(key);	
			}
			memcacheChannelService.updateCache();
			resposeResult = new ResponseResult("缓存清理"+key,"true");
		} catch (Exception e) {
			log.error("缓存清理出错了。。",e);
			resposeResult = new ResponseResult("参数异常","false");
		}
		String responseJson =JsonUtils.toJson(resposeResult);
		JsonUtils.sendJson(response,responseJson);
		
	}
	@RequestMapping("/cache/deleteSchemecache.do")
	public void deleteSchemecache(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult resposeResult = null;
		try {
			String key = request.getParameter("key");
			
			if(!StringUtil.isEmpty(key)){
				memcacheChannelService.deleteSchemeCache(key);	
			}
			 resposeResult = new ResponseResult("缓存清理"+key,"true");
		} catch (Exception e) {
			log.error("缓存清理出错了。。",e);
			resposeResult = new ResponseResult("参数异常","false");
		}
		String responseJson = JsonUtils.toJson(resposeResult);
		JsonUtils.sendJson(response,responseJson);
	}
	
	/**
	 * <p>是否启动队列</p>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/cache/isQueueAvalid.do")
	public void isavalid(HttpServletRequest request,HttpServletResponse response) {
		
	}
	
}
