package cn.adwalker.ad.web.channel.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import cn.adwalker.ad.model.channel.domain.UserChannels;
import cn.adwalker.ad.model.common.domain.EJumpType;
import cn.adwalker.ad.util.MD5;
import cn.adwalker.ad.util.OutputHelper;
import cn.adwalker.ad.web.channel.form.ChannelPassword;
import cn.adwalker.ad.web.channel.service.ChannelReportService;
import cn.adwalker.ad.web.common.controller.AbstractControllerParent;

/**
 * 前台渠道管理
 */
@Controller
public class ChannelController extends AbstractControllerParent {

	private static final Logger log = LoggerFactory.getLogger(ChannelController.class);

	@Resource
	private ChannelReportService channelReportService;

	/**
	 * 前台渠道登录
	 * 
	 * @param email
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/channelLogin.action")
	public ModelAndView channelLogin(HttpServletRequest request, HttpSession session, Map<String, Object> model) throws Exception {
		log.debug("into method channellogin: 前台渠道登录");
		String email = (String) session.getAttribute(("user_email"));
		if (!StringUtils.isEmpty(email)) {
			UserChannels userChannels = channelReportService.exists(email);
			session.setAttribute("userChannels", userChannels);
			super.setUserChannels(userChannels);
			if (userChannels.getChanne_mode().equals(0)) {
				return new ModelAndView(new RedirectView("reportChannelProxy.action?parentId=1"));
			} else {
				return new ModelAndView(new RedirectView("reportChannelSdk.action?parentId=1"));
			}
		}
		return new ModelAndView(new RedirectView("login.action"));

	}

	/**
	 * 修改渠道账号密码
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toUpdateChannelPass.action")
	public String toUpdateUserPass(Map<String, Object> model) throws Exception {
		UserChannels userChannel = null;
		try {
			userChannel = channelReportService.getById(super.getUserChannels().getId());
			model.put("userChannel", userChannel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "channel/channel_update_password";
	}

	/**
	 * 对比输入的就密码是否一致
	 * 
	 * @param request
	 * @param response
	 * @param oldpass
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/passwordChannelRatio.action")
	public String passwordRatio(HttpServletRequest request, HttpServletResponse response, String oldpass) throws Exception {
		if (!new MD5().getMD5ofStr(oldpass.trim()).equals(this.getUserChannels().getPassword())) {
			OutputHelper.outPut(response, "false");
		} else {
			OutputHelper.outPut(response, "true");
		}
		return null;
	}

	/**
	 * 修改用户密码
	 * 
	 * @param model
	 * @param developerVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateChannelPass.action")
	public String updateUserPass(Map<String, Object> model, HttpServletResponse response, ChannelPassword chaPassword) throws Exception {
		if (!new MD5().getMD5ofStr(chaPassword.getOldpass()).equals(this.getUserDeveloper().getPassword())) {
			return super.jumpPage(model, "修改失败，您输入的旧密码错误！", "/toUpdateChannelPass.action?id=" + super.getUserChannels().getId(), EJumpType.FAIL.getValue());
		}
		try {
			UserChannels userChannels = super.getUserChannels();
			channelReportService.updateChannelPass(new MD5().getMD5ofStr(chaPassword.getPassword()), userChannels.getId());
			super.setUserChannels(userChannels);
			OutputHelper.outPut(response, "true");
		} catch (Exception e) {
			e.printStackTrace();
			OutputHelper.outPut(response, "false");
		}
		return null;
	}
	
}
