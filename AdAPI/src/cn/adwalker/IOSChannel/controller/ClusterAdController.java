package cn.adwalker.IOSChannel.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.IOSChannel.logger.AdClickLogger;
import cn.adwalker.IOSChannel.param.ClickParam;
import cn.adwalker.IOSChannel.service.IAdClusterService;
import cn.adwalker.ad.cache.element.AdCluster;
import cn.adwalker.ad.util.JacksonMapper;
/**
 * 
* <p>Title: ClusterAdController</p>
* <p>Description:接入广数据处理服务</p>
* <p>Company: adwalker</p> 
* @author    cuidd
* @date       2014年10月17日
 */
@Controller
public class ClusterAdController {
	
	
	@Resource
	private IAdClusterService adClusterService;
	
	@RequestMapping("/common/ios/click.do")
	public void channelCheckControllerPost(HttpServletRequest request,
		 HttpServletResponse response,ClickParam param){
		try {
			System.out.println(JacksonMapper.objectToJsonString(param));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		AdCluster ad=adClusterService.cc(param);
		//1、参数及签名校验
		//2、写日志
		//3、存储数据--参考jierfei批量写数据的方法
		//4、跳转到指定地址
		AdClickLogger logger=new AdClickLogger();
		logger.logInfo(param.getRes_code(), param.getUuid(),param.getMac(),param.getUdid(),param.getIdfa(),param.getAd_id(),param.getApp_id());
		try {
			response.sendRedirect(ad.getStore_url());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
