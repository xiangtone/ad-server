/**
* <p>Title: ScoreController.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-7-17
* @version 1.0
*/
package cn.adwalker.ad.api.dev.controller;

import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.api.dev.service.IScoreAPIService;
import cn.adwalker.core.util.JsonUtils;
import cn.adwalker.core.vo.ResponseResult;

/**
 * <p>Title: ScoreController</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-7-17
 */
@Controller
public class ScoreAPIController {

	@Resource
	private IScoreAPIService scoreAPIService;

	
	// 获取积分说明
	@RequestMapping("/common/addScore.do" )
	public String getResult(HttpServletRequest request,
			HttpServletResponse response){
		response.setContentType("application/json;charset=UTF-8");
		String uuid=request.getParameter("uuid");
		String userID=request.getParameter("userID");
		String score=request.getParameter("score");
		String exchangetime=request.getParameter("exchangetime");
		String plat=request.getParameter("plat");
		String appName=request.getParameter("appName");
		String packageName=request.getParameter("packageName");
		String adId=request.getParameter("adId");
		String appId=request.getParameter("appId");
		// add by jief
		String idfa=request.getParameter("idfa");
		String fastTask=request.getParameter("fastTask");
		
		String icon_url=request.getParameter("icon_url");
		try {
			scoreAPIService.sendData(uuid, userID, score, exchangetime, plat, appName, packageName, adId,appId,idfa,fastTask,icon_url);
			
			ResponseResult resposeResult = new ResponseResult("确认成功","true");
			String responseJson = JsonUtils.toJson(resposeResult);
			OutputStream out = response.getOutputStream();
			out.write(responseJson.getBytes("UTF-8"));
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			// 封装返回
			ResponseResult resposeResult = new ResponseResult();
			resposeResult.setMessage("参数异常");
			resposeResult.setSuccess("false");
			String responseJson = JsonUtils.toJson(resposeResult);
			try {
				OutputStream out = response.getOutputStream();
				out.write(responseJson.getBytes("UTF-8"));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return null;		
	}
}
