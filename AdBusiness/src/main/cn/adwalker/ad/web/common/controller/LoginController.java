package cn.adwalker.ad.web.common.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import cn.adwalker.ad.config.AppConstant;
import cn.adwalker.ad.upload.util.JacksonMapper;
import cn.adwalker.ad.util.EmailUtil;
import cn.adwalker.ad.util.OutputHelper;
import cn.adwalker.ad.util.StatusConstant;
import cn.adwalker.ad.web.common.service.LoginService;
import cn.adwalker.ad.web.common.vo.ResultErrorVo;
import cn.adwalker.ad.web.common.vo.ResultSuccessVo;
import cn.adwalker.ad.web.common.vo.ResultVo;
import cn.adwalker.ad.web.common.vo.ViewUserVo;

@Controller
public class LoginController {

	@Resource
	private LoginService loginService;

	/**
	 * 用户登录
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login.action")
	public ModelAndView login(HttpServletRequest req, HttpServletResponse res, HttpSession session, String code) throws Exception {
		return new ModelAndView("../../login", "message", "");
	}

	/**
	 * 用户登录
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/dologin.action")
	public ModelAndView dologin(HttpServletRequest req, HttpServletResponse res, HttpSession session, String code) throws Exception {
		String message = "";
		Boolean isResponseCorrect = Boolean.FALSE;
		String captchaId = session.getId();
		String email = ServletRequestUtils.getStringParameter(req, "email", "");
		String password = ServletRequestUtils.getStringParameter(req, "password", "");
		req.setAttribute("email", email);
//		try {
//			isResponseCorrect = CaptchaServiceSingleton.getInstance().validateResponseForID(captchaId, code);
//		} catch (Exception e) {
//			e.printStackTrace();
//			message = "login验证码已过期，请刷新验证码！！";
//			return new ModelAndView("../../login", "message", message);
//		}
//		if (!isResponseCorrect) {
//			message = "验证码输入错误！！";
//			return new ModelAndView("../../login", "message", message);
//		}
		ViewUserVo vo = loginService.login(email, password);
		if (vo == null) {
			message = "您的用户名或密码错误，请核实后登录，谢谢！！";
			return new ModelAndView("../../login", "message", message);
		} else {
			if (vo.getStatus() == StatusConstant.DEVELOPER_STATUS_INIT) {
				// 用户名，密码不对
				String url = EmailUtil.getUrl(email);
				message = "您的账号尚未激活，请激活或者同客服联系！！";
				req.setAttribute("status", vo.getStatus());
				req.setAttribute("type", vo.getType());
				req.setAttribute("email", email);
				req.setAttribute("password", password);
				req.setAttribute("url", url);
				return new ModelAndView("../../login", "message", message);
			} else if (vo.getStatus() == StatusConstant.DEVELOPER_STATUS_NORMAL || vo.getStatus() == StatusConstant.DEVELOPER_STATUS_FROST) {
				session.setAttribute("user_email", email);
				if (vo.getType() == AppConstant.USER_DEVELOPER) {
					// 开发者
					return new ModelAndView(new RedirectView("developerLogin.action"), "email", email);
				} else if (vo.getType() == AppConstant.USER_CHANNELS) {
					// 渠道
					//return new ModelAndView("../../login", "msg", "您的用户名或密码错误，请核实后登录，谢谢！！");
					// 暂时屏蔽渠道
					 return new ModelAndView(new RedirectView("channelLogin.action"), "email", email);
				}
			} else {
				message = "该账号已封号，请联系客服！！";
				return new ModelAndView("../../login", "message", message);
			}
			message = "您的用户名或密码错误，请核实后登录，谢谢！！";
			return new ModelAndView("../../login", "message", message);
		}
	}

	/**
	 * 用户登录
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doNoCodeLogin.action")
	public ModelAndView doLoginNoCode(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws Exception {
		ResultVo resultVo = null;
		String email = ServletRequestUtils.getStringParameter(req, "email", "");
		String password = ServletRequestUtils.getStringParameter(req, "password", "");
		req.setAttribute("email", email);
		if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
			resultVo = new ResultErrorVo("用户名或者密码不能为空！！");
		} else {
			if (!loginService.userExists(email.trim())) {
				resultVo = new ResultErrorVo("用户名不存在！！");
			} else {
				ViewUserVo user = loginService.login(email.trim(), password.trim());
				if (user == null) {
					// 用户名，密码不对
					resultVo = new ResultErrorVo("您的用户名或密码错误！！");
				} else {
					if (user.getStatus() == -1) {
						resultVo = new ResultErrorVo("该账号已封号，请联系客服！！");
					} else if (user.getStatus() == 0) {
						resultVo = new ResultErrorVo("您的账户未激活，请激活后登录，谢谢！！");
						String url = EmailUtil.getUrl(email);
						String msg = "activation";
						req.setAttribute("url", url);
						req.setAttribute("msg", msg);
						req.setAttribute("status", user.getStatus());
						req.setAttribute("type", user.getType());
						req.setAttribute("email", email);
						req.setAttribute("password", password);
					} else if (user.getStatus() == StatusConstant.DEVELOPER_STATUS_INIT || user.getStatus() == StatusConstant.DEVELOPER_STATUS_NORMAL || user.getStatus() == StatusConstant.DEVELOPER_STATUS_FROST) {
						session.setAttribute("user_email", email);
						if (user.getType() == AppConstant.USER_DEVELOPER) {
							resultVo = new ResultSuccessVo();
							resultVo.setData("developerLogin.action");
						} else if (user.getType() == AppConstant.USER_CHANNELS) {
							resultVo = new ResultSuccessVo();
							//return new ModelAndView("../../login", "msg", "您的用户名或密码错误，请核实后登录，谢谢！！");
							// 暂时屏蔽渠道
							resultVo.setData("channelLogin.action");
						}
					} else {
						resultVo = new ResultErrorVo("您的账户处于非正常状态，请联系管理员解决，谢谢！！");
					}
				}
			}
		}
		OutputHelper.outPut(res, JacksonMapper.objectToJsonString(resultVo));
		return null;
	}

	/**
	 * 用户登录
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/loginNoCode.action")
	public String loginNoCode(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return "../../loginNoCode";

	}

	@RequestMapping("/gotoReg.action")
	public String gotoReg(HttpSession session, Map<String, Object> model) throws Exception {
		return "../../reg";
	}

	/**
	 * 退出登录
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/loginOut.action")
	public String loginOut(HttpSession session, HttpServletResponse response, Map<String, Object> model) throws Exception {
		session.invalidate();
		response.sendRedirect("index.action");
		return null;
	}

	@RequestMapping("/resetPassword.action")
	public String resetPassword(HttpServletRequest req, HttpServletResponse res, String email) throws Exception {
		int pam = 0;
		pam = loginService.resetPassword(email);
		if (pam == 0) {
			OutputHelper.outPut(res, "false");
		} else {
			OutputHelper.outPut(res, "true");
		}
		return null;
	}
	
}
