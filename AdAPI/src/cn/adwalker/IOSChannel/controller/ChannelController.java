/**
* <p>Title: ChannelController.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2012-12-21
* @version 1.0
*/
package cn.adwalker.IOSChannel.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.IOSChannel.logger.ChannelReceiveLogger;
import cn.adwalker.IOSChannel.service.IChannelService;
import cn.adwalker.IOSChannel.thread.ChannelGetThread;
import cn.adwalker.IOSChannel.thread.ChannelThread;
import cn.adwalker.IOSChannel.util.IOSUtil;
import cn.adwalker.IOSChannel.vo.ChannelRequestResult;
import cn.adwalker.IOSChannel.vo.Constant;
import cn.adwalker.ad.pool.ThreadPool;
import cn.adwalker.ad.util.ConfigUtil;
import cn.adwalker.ad.util.StringUtil;
import cn.adwalker.core.util.JsonUtils;
import cn.adwalker.core.vo.ResponseResult;


/**
 * 
 * <p>Title: ChannelController</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2012-12-21
 */
@Controller
public class ChannelController {
	private static final Logger log = LoggerFactory.getLogger(ChannelController.class);
	private static final String channelJson="channelJson";
	
	@Resource
	private IChannelService channelService;
	/*****
	 * 渠道请求接口处理
	 * @param request
	 * @param response
	 */
	@RequestMapping("/common/channelCheckPost.do")
	public void channelCheckControllerPost(HttpServletRequest request,
		 HttpServletResponse response){
		 long startTime = System.currentTimeMillis();
		 response.setContentType("text/xml;charset=UTF-8");
         response.setHeader("Cache-Control", "no-cache");
		 response.setCharacterEncoding("UTF-8");
		 try {
			 if("application/json".equals(request.getHeader("content-type")) && "application/json".equals(request.getHeader("accept"))){
				 	InputStream is =request.getInputStream();
					//得到body体中的内容
					String requestJson = IOSUtil.inputStream2String(is);
					long Time1 = System.currentTimeMillis();
					long finalTime1 = Time1-startTime;
					log.info("解析完成网络请求时间为："+finalTime1);
					//解请求json
//					String urlPath = "f:/adSageRequest"+System.currentTimeMillis()+".json";
					String urlPath = ConfigUtil.getString("channelJson")+"/"+channelJson+System.currentTimeMillis()+".json";					
					IOSUtil.writeFile(urlPath,requestJson);					
					//封装返回
					ResponseResult resposeResult = new ResponseResult("接收成功","true");
					String responseJson = StringUtil.toJson(resposeResult);
					OutputStream out = response.getOutputStream();
					out.write(responseJson.getBytes("UTF-8"));
					out.close();
					//启动线程执行service
					ChannelThread r = new ChannelThread(requestJson,channelService);
					r.start();
										
					try {
						r.join();
					} catch (InterruptedException e) {
						
					}
					
					
			 }else{
				 	//封装返回				 	
				 	ResponseResult resposeResult = new ResponseResult("参数异常","false");					
					String responseJson =StringUtil.toJson(resposeResult);
					OutputStream out = response.getOutputStream();
					out.write(responseJson.getBytes("UTF-8"));
					
			 }
			 long endTime = System.currentTimeMillis();
			 long finalTime = endTime-startTime;
				
				log.info("方法activateCheckController执行时间为："+finalTime);
		} catch (IOException e) {
			e.printStackTrace();
			//封装返回
			ResponseResult resposeResult = new ResponseResult("参数异常","false");
			String responseJson = StringUtil.toJson(resposeResult);
			OutputStream out;
			try {
				out = response.getOutputStream();
				out.write(responseJson.getBytes("UTF-8"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		} 
	}
	
	@RequestMapping("/common/channelCheckGet.do")
	public void channelCheckControllerGet(HttpServletRequest request,
		 HttpServletResponse response){
//		 long startTime = System.currentTimeMillis(); 
	try{	 
		 String appid = String.valueOf(request.getParameter(Constant.APP_ID));			//广告接口配置表中的id值
		 String channel = String.valueOf(request.getParameter(Constant.SOURSE));		//渠道名称
		 String deviceid = request.getParameter(Constant.DEVICE_ID);					//设备信息mac change by jief del String.vlaueOf()
		 String udid = String.valueOf(request.getParameter(Constant.UDID));				//udid
		 String client_ip = String.valueOf(request.getParameter(Constant.CLIENT_IP));	//客户端ip
		 String callback_url=request.getParameter("callback_url");		//渠道的回调地址
		 String app_key=request.getParameter("app_key");				//自有的应用id add By jief
		 String ad_key=request.getParameter("ad_key");					//自有的广告id add by jief
		 String page_type=request.getParameter("page_type");			//自有平台的广告类型	add by jief
		 String sdkVersion=request.getParameter("sdkVersion");			//sdk版本
		 //add by jief 2013-09-03
		 String OpenUDID = request.getParameter(Constant.IOS_OpenUDID);					//openudid
		 if(StringUtils.isBlank(OpenUDID) || "null".equals(OpenUDID)){
			 OpenUDID="";
		 }
		 String IDFA = request.getParameter(Constant.IOS_IDFA);							//idfa
		 if(StringUtils.isBlank(IDFA) || "null".equals(IDFA)){ 
			 IDFA="";
		 }else{
			 IDFA=IDFA.toUpperCase();
		 }
		 String IDFV = request.getParameter(Constant.IOS_IDFV);							//idfv
		 if(StringUtils.isBlank(IDFV) || "null".equals(IDFV)) IDFV="";
		 String os=request.getParameter("os");											//ios操作系统版本
		//add by jief ios7 无法获取mac 所以渠道或自己平台不要发送mac值了
		 if(StringUtils.isBlank(deviceid) || 
				 deviceid.replaceAll(":", "").equals("020000000000") || 
				 (StringUtils.isNotBlank(os) && os.compareTo("7")>=0)) {
			 deviceid="";
			 if(StringUtil.isBlank(os))
			    os="7.*";
		 }else{
			deviceid = deviceid.replaceAll(":", ""); 
			 if(StringUtil.isBlank(os))
				os="6.*";
		 }
		 //该参数非必须，如果不填，默认：数据接收时间。
		 Long statDate =null;
		 if(request.getParameter(Constant.STAT_DATE)!=null){
			 statDate = Long.valueOf(String.valueOf(request.getParameter(Constant.STAT_DATE)));
		 }else{
			 statDate =  System.currentTimeMillis()/1000;  
		 }
		 ChannelReceiveLogger logger =new ChannelReceiveLogger();
		 if("".equals(deviceid)){
		     logger.logInfo(appid, channel, OpenUDID, client_ip, statDate.toString());
		 }else{
			 logger.logInfo(appid, channel, deviceid, client_ip, statDate.toString());
		 }
		 ChannelRequestResult result = new ChannelRequestResult();			 
		 result.setAdId(appid);
		 result.setChannel(channel);
		 result.setSource(channel);
		 result.setDeviceId(deviceid.toUpperCase());
		 result.setStatDate(statDate);
		 result.setUdid(udid);
		 result.setClient_ip(client_ip);
		 //jief 2013-09-03
		 result.setOpenUDID(OpenUDID);
		 result.setIdfa(IDFA);
		 result.setIdfv(IDFV);
		 result.setOs_version(os);
		 //add by jief 2013-11-27
		 result.setAd_key(ad_key);
		 result.setApplication_key(app_key);
		 if(StringUtil.isNotBlank(page_type) && page_type.matches("[0-9]+")){
			 result.setPage_type(Integer.parseInt(page_type));
		 }
		 if(StringUtils.isNotBlank(callback_url)){
			 callback_url=StringUtil.decode(callback_url);
		     result.setCallback_url(callback_url);
		 }
		 result.setSdkversion(sdkVersion);
		//启动线程执行service 
		ChannelGetThread r = new ChannelGetThread(result,channelService);
		//采用线程池
		ThreadPool.getInstance().getThreadPoolExecutor().execute(r);
		//封装返回
		ResponseResult resposeResult = new ResponseResult("发送成功！", "true");
		JsonUtils.sendResponse(resposeResult, response);
	}catch(Exception e){
		e.printStackTrace();
		//封装返回
		ResponseResult resposeResult = new ResponseResult("参数异常","false");
		JsonUtils.sendResponse(resposeResult, response);
	}		
		
   }
}
