package cn.adwalker.ad.admin.common.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.common.form.InteriorMailForm;
import cn.adwalker.ad.admin.common.service.IInteriorMailService;
import cn.adwalker.ad.admin.common.vo.InteriorMailVo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.core.vo.ResultErrorVo;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;

/**
 * <p>
 * Title: InteriorMailController
 * </p>
 * <p>
 * Description:站没内信
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2014-1-24
 */
@Controller
public class InteriorMailController extends AbstractControllerParent {

	@Resource
	private IInteriorMailService interiorMailService;

	private SysUserVo manageUser;

	/**
	 * <p>
	 * Title: interiorMailList
	 * </p>
	 * <p>
	 * Description:站内信列表
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @return
	 * @author lichuang
	 * @date 2014-1-24
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!interiorMailList.do")
	public String interiorMailList(HttpSession session,
			HttpServletRequest request) {
		IPageInfo pageInfo = new SetPage(request);
		try {

			List<InteriorMailVo> interiorMail = this.interiorMailService
					.findTitleList(pageInfo);
			// 装配分页信息
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("interiorMail", interiorMail);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manage/news/interior_mail_list";
	}

	/**
	 * <p>
	 * Title: editInteriorMail
	 * </p>
	 * <p>
	 * Description:编辑站内信
	 * </p>
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @author lichuang
	 * @date 2014-1-24
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("manage!editInteriorMail.do")
	public String editInteriorMail(HttpServletRequest request, Long id) {
		if (ObjectUtils.isEmpty(id)) {
			return "manage/news/interior_mail_add";
		} else {
			InteriorMailVo vo = this.interiorMailService.findById(id);
			request.setAttribute("editInterior", vo);
			return "manage/news/interior_mail_edit";
		}
	}

	/**
	 * <p>
	 * Title: aveInteriorMail
	 * </p>
	 * <p>
	 * Description:保存站内信
	 * </p>
	 * 
	 * @param session
	 * @param model
	 * @param newsNotice
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2014-1-24
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("manage!saveInteriorMail.do")
	public String saveInteriorMail(HttpServletRequest request,HttpSession session,HttpServletResponse res,
			 InteriorMailForm form) throws Exception {

		manageUser = (SysUserVo) session.getAttribute("manageUser");
		ResultVo vo = null;

		try {
			this.interiorMailService.InteriorMailInsert(form, manageUser);
			vo = new ResultSuccessVo();
		} catch (Exception e) {
			e.printStackTrace();
			vo = new ResultErrorVo("修改应用失败！！");
		}
		OutputHelper.outPut(res, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * <p>
	 * Title: updateInteriorMail
	 * </p>
	 * <p>
	 * Description:修改站内信
	 * </p>
	 * 
	 * @param session
	 * @param model
	 * @param newsNotice
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2014-1-24
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("manage!updateInteriorMail.do")
	public String updateInteriorMail(HttpServletRequest request,HttpSession session,HttpServletResponse res,
			Map<String, Object> model, InteriorMailForm form) throws Exception {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		ResultVo vo = null;

		try {
		this.interiorMailService.updateInteriorMail(form, manageUser);
		vo = new ResultSuccessVo();
		
		} catch (Exception e) {
			e.printStackTrace();
			vo = new ResultErrorVo("修改应用失败！！");
		}
		OutputHelper.outPut(res, JacksonMapper.objectToJsonString(vo));
		return null;

	}

	/**
	 * @return the manageUser
	 */
	public SysUserVo getManageUser() {
		return manageUser;
	}

	/**
	 * @param manageUser
	 *            the manageUser to set
	 */
	public void setManageUser(SysUserVo manageUser) {
		this.manageUser = manageUser;
	}

}
