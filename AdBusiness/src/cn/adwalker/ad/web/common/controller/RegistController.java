package cn.adwalker.ad.web.common.controller;

import java.io.PrintStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.model.developer.domain.Developer;
import cn.adwalker.ad.servlet.CaptchaServiceSingleton;
import cn.adwalker.ad.util.EmailUtil;
import cn.adwalker.ad.web.common.form.RegForm;
import cn.adwalker.ad.web.common.service.RegistService;
import cn.adwalker.ad.web.developer.service.DeveloperService;

/**
 * 注册控制层
 */
@Controller
public class RegistController extends AbstractControllerParent {

	@Resource
	private RegistService registService;
	
	@Resource
	private DeveloperService developerService;
	
	@RequestMapping("/register.action")
	public String register(HttpServletRequest request, Model model, HttpSession session, String code, RegForm regForm, HttpServletResponse response) throws Exception {
		Boolean isResponseCorrect = Boolean.FALSE;
		String captchaId = session.getId();
		try {
			isResponseCorrect = CaptchaServiceSingleton.getInstance().validateResponseForID(captchaId, code);
		} catch (Exception e) {
			model.addAttribute("message", "验证码已过期，请刷新验证码!");
			return"../../register";
		}
		if (isResponseCorrect) {
			if (registService.exists(regForm.getEmail())) {
				model.addAttribute("message", "注册失败!可能帐号已存在!");
				return"../../register";
			}
			try {
				registService.registService(regForm);
				return "redirect:regeisterSuccess.action?email=" + regForm.getEmail();
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("message", "你输入的邮件服务器不存在!");
				return"../../register";
			}
		}
		//OutputHelper.outPut(res, JacksonMapper.objectToJsonString(resultVo));
		//return null;
		model.addAttribute("message", "验证码错误!");
		return"../../register";
	}

	@RequestMapping("/regeisterSuccess.action")
	public String regSuccess(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model, HttpSession session, String code, String email) throws Exception {
		String url = EmailUtil.getUrl(email);
		request.setAttribute("url", url);
		request.setAttribute("email", email);
		return "../../registersuccess";
	}

	@RequestMapping("/sendMail.action")
	public String sendMail(HttpServletRequest request, HttpServletResponse response, String email) throws Exception {
		Developer developer = developerService.exists(email);
		PrintStream out = new PrintStream(response.getOutputStream());
		if(developer != null) {
			registService.sendConfrimEmail(developer.getId(), developer.getCreateTime(), email);
			out.print(1);
		} else {
			out.print(2);
		}
		out.close();
		return null;
	}

	@RequestMapping("/confrimAccount.action")
	public String confrimAccount(Map<String, Object> model, String key) throws Exception {
		String msg = null;
		Integer flag = registService.confrimAccount(key);
		if (flag == 1) {
			msg = "激活成功!";
		} else if (flag == 2) {
			msg = "激活失败!";
		} else {
			msg = "已激活成功,不用再激活了!";
		}
		model.put("msg", msg);
		return "../../confirmsuccess";
	}
	
}
