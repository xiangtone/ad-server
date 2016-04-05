package cn.adwalker.ad.web.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 功能描述<br>
 * 首页动态展示的相关信息控制层
 */
@Controller
public class IndexController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	/**
	 * 获取首页展示信息
	 * 
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/index.action")
	public String index(HttpServletRequest request) throws Exception{
		return "../../index";
	}
}
