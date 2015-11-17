/**
* <p>Title: Advertisement.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2012-12-21
* @version 1.0
*/
package cn.adwalker.IOSChannel.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.IOSChannel.logger.AdReceiveLogger;
import cn.adwalker.IOSChannel.service.IAdvertisementService;
import cn.adwalker.IOSChannel.vo.ChannelRequestResult;
import cn.adwalker.IOSChannel.vo.Constant;
import cn.adwalker.IOSChannel.vo.ReturnResult;
import cn.adwalker.core.util.JsonUtils;



/**
 * <p>Title: Advertisement</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2012-12-21
 */
@Controller
public class AdvertisementController  {
	 private static final Logger log=Logger.getLogger(AdvertisementController.class);
	 private final static String APP_ID = "appid";//
	 private final static String DEVICE_ID = "deviceid";//
	 
	 @Resource
	private IAdvertisementService advertisementService;
	/*****
	 *  请求回调接口处理
	 * @param request
	 * @param response
	 */
	@RequestMapping("/common/comfirmActivateCheckGet.do")
	public void comfirmActivateCheck(HttpServletRequest request,
			HttpServletResponse response){
		response.setContentType("application/json;charset=UTF-8");
		ReturnResult result = new ReturnResult();//返回对象
		try {
			String adkey = request.getParameter(APP_ID);//APP标识
			if (StringUtils.isEmpty(adkey)) {
				adkey = request.getParameter("appId");
			}
			String deviceid = request.getParameter(DEVICE_ID);//设备标识   mac地址
			if (StringUtils.isEmpty(deviceid)) {
				deviceid = request.getParameter("udid");
			}
			String jdmac = request.getParameter("mac_udid");//设备标识   mac地址
			if(StringUtils.isNotEmpty(jdmac)){
				deviceid = jdmac.toUpperCase();
			}
			//changed by check 020000000000
			if(StringUtils.isNotBlank(deviceid)&& !deviceid.replaceAll(":", "").equals("020000000000")){
				deviceid=deviceid.toUpperCase();
				deviceid = deviceid.replaceAll(":", "");
			}else{
				deviceid="";
			}
			 //add by jief 2013-09-03
			 String OpenUDID = request.getParameter(Constant.IOS_OpenUDID);
			 if(StringUtils.isBlank(OpenUDID)){
				 OpenUDID="";
			 }
			 String IDFA = request.getParameter(Constant.IOS_IDFA);
			 if(StringUtils.isBlank(IDFA)){
				 IDFA=request.getParameter("idfa");
			 }
			 if(StringUtils.isBlank(IDFA)) {
				 IDFA="";
			 }else{
				 IDFA=IDFA.toUpperCase();
			 }
			 String IDFV = request.getParameter(Constant.IOS_IDFV);
			 if(StringUtils.isBlank(IDFV)){
				 IDFV="";
			 }
			
			//日志 add openudid,idfa,idfv by jief2013-09-16
			AdReceiveLogger logger =new AdReceiveLogger();
			logger.logInfo(adkey, deviceid,OpenUDID,IDFA,IDFV);
			ChannelRequestResult vo =new ChannelRequestResult();
			vo.setAdId(adkey);
			vo.setDeviceId(deviceid);
			vo.setOpenUDID(OpenUDID);
			vo.setIdfa(IDFA);
			vo.setIdfv(IDFV);
			//changed by jief 2013-12-03
			Map<String,String> map =advertisementService.callbackActivateCheck(vo);
			result.setMessage(String.valueOf(map.get("msg")));
			result.setSuccess(String.valueOf(map.get("flag")));
			String returnJson = JsonUtils.toJson(result);			
			JsonUtils.sendJson(response,returnJson);
		} catch (Exception e) {
			log.error("接收广告主确认出错啦！",e);
			result.setMessage("参数异常");
			result.setSuccess("false");
			String returnJson = JsonUtils.toJson(result);			
			JsonUtils.sendJson(response,returnJson);
		}
	}
}
