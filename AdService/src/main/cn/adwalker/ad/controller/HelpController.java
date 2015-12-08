/**
 * <p>Title: ScoreDescController.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2013-5-10
 * @version 1.0
 */
package cn.adwalker.ad.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.service.IHelpService;

/**
 * <p>
 * Title: ScoreDescController
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author www.adwalker.cn
 * @date 2013-5-10
 */
@Controller
public class HelpController {
	@Resource
	private IHelpService helpService;
	
	// 获取积分说明
	@RequestMapping("/common/score_desc.do" )
	public void getUuidScore(HttpServletRequest request,HttpServletResponse response) throws IOException {

	}

	// 获取积分说明
	@RequestMapping("/android/help_desc.do" )
	public void androidHelpDesc(HttpServletRequest request,HttpServletResponse response){
		
	}
	
	
	// 获取积分说明
	@RequestMapping("/ios/help_desc.do" )
	public void iosHelpDesc(HttpServletRequest request,HttpServletResponse response){
		
	}
}
