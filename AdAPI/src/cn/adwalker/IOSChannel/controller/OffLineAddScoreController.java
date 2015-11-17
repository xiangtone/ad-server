package cn.adwalker.IOSChannel.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.IOSChannel.service.IOffLineAddScoreService;
import cn.adwalker.IOSChannel.thread.OffLineAddScoreThread;
import cn.adwalker.core.util.JsonUtils;
import cn.adwalker.core.vo.ResponseResult;

@Controller
public class OffLineAddScoreController {
	
	@Resource
	private IOffLineAddScoreService offLineAddScoreService;
	
	@RequestMapping("/common/offLineAddScore.do")
	public void offLineAddScore(HttpServletRequest request,
		 HttpServletResponse response)throws IOException{
		response.setContentType("text/x-json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("content-type", "application/json");
		response.setCharacterEncoding("UTF-8");
		String status=request.getParameter("status");
		String responseJson="";
		Integer sta=null;
		//封装返回				 	
	 	ResponseResult resposeResult = null;
		if(StringUtils.isNotBlank(status)){
			sta=Integer.parseInt(status);
			resposeResult = new ResponseResult("接收成功","true");
		}else{
			resposeResult = new ResponseResult("参数异常","false");					
		}
		responseJson = JsonUtils.toJson(resposeResult);
		new OffLineAddScoreThread(offLineAddScoreService,sta).start();
		OutputStream out = response.getOutputStream();
		out.write(responseJson.getBytes("UTF-8"));
		
	}
}
