package cn.adwalker.ad.web.common.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.adwalker.ad.servlet.CaptchaServiceSingleton;
import cn.adwalker.ad.web.common.service.RegistService;

/**
 * 功能概述：<br>
 * 用户ajax
 */
@Controller
public class UserAjax {

	@Resource
	private RegistService registService;

	@RequestMapping("/ajax/checkEmail.action")
	@ResponseBody
	public boolean checkEmail(String email) throws Exception {
		if (registService.exists(email)) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping("/ajax/checkCode.action")
	@ResponseBody
	public boolean checkCode(HttpSession session, String code) throws Exception {
		Boolean isResponseCorrect = Boolean.FALSE;
		String captchaId = session.getId();
		isResponseCorrect = CaptchaServiceSingleton.getInstance().validateResponseForID(captchaId, code);
		if (isResponseCorrect) {
			return true;
		} else {
			return false;
		}
	}
	
}
