package cn.adwalker.ad.admin.common.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.ConfigUtil;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.util.email.SendMail;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.core.vo.ResultErrorVo;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.model.news.domain.Mail;
import cn.adwalker.ad.admin.common.service.IMailsNoticeService;
import cn.adwalker.ad.admin.common.vo.SysUserVo;

/**
 * 功能描述<br>
 * 邮件发送控制层
 * 
 * @author cai
 */
@Controller(value = "mailsNoticeController")
public class MailsController extends AbstractControllerParent {

	@Resource
	private IMailsNoticeService mailsNoticeService;

	/**
	 * 编辑邮件管理
	 * 
	 * @return
	 */
	@RequestMapping("manage!editMailsNotice.do")
	public String editNewsNotice(HttpServletRequest request, Long id) {
		return "news/ope_mails";
	}

	/**
	 * 群发邮件
	 * 
	 * @param session
	 * @param newsNotice
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping("manage!saveMailsNotice.do")
	public void saveNewsNotice(HttpSession session, Map<String, Object> model,
			Mail mail, HttpServletResponse res) throws IOException {
		ResultVo vo = null;
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		mail.setManager_id(manageUser.getId());
		if (ObjectUtils.isEmpty(mail.getId())) {
			this.mailsNoticeService.sendEmail(mail);
			vo = new ResultSuccessVo();
		} else {
			vo = new ResultErrorVo();
		}
		OutputHelper.outPut(res, JacksonMapper.objectToJsonString(vo));
	}

	/**
	 * 测试发送邮件
	 * 
	 * @param session
	 * @param newsNotice
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping("manage!sendMailsNotice.do")
	public void sendMailsNotice(HttpServletRequest request,
			HttpSession session, Mail newsNotice, HttpServletResponse response)
			throws IOException {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		newsNotice.setManager_id(manageUser.getId());
		String mailtest = request.getParameter("mailtest");
		String reg = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

		if (ObjectUtils.isEmpty(newsNotice.getId())
				&& Pattern.matches(reg, mailtest)) {
			SendMail.send(newsNotice.getTitle(),
					ConfigUtil.getString("sendMail"), mailtest,
					newsNotice.getContent());
			response.getWriter().write("1");
		} else {
			response.getWriter().write("0");
		}

	}

	/**
	 * 获取邮件管理列表
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("manage!mailList.do")
	public String newsNoticeListManage(HttpSession session,
			HttpServletRequest request) {
		IPageInfo pageInfo = new SetPage(request);
		try {
			List<Mail> newsNoticeList = (List<Mail>) this.mailsNoticeService
					.findByPage(pageInfo);
			// 装配分页信息
			request.setAttribute("newsNoticeList", newsNoticeList);
			request.setAttribute("pageInfo", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "news/mail_list";
	}

	/**
	 * 获取邮件详情
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("manage!detailMailsNoticeManage.do")
	public String detailNewsNoticeManage(HttpServletRequest request, Long id) {
		Mail newsNotice = this.mailsNoticeService.findById(id);
		request.setAttribute("newsNotice", newsNotice);
		return "manage/news/ope_mails_content";
	}
}
