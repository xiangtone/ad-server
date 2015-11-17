package cn.adwalker.ad.admin.app.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.app.bean.Blackbean;
import cn.adwalker.ad.admin.app.bean.SelCampaignQueryBean;
import cn.adwalker.ad.admin.app.service.IApplicationBlackService;
import cn.adwalker.ad.admin.app.vo.AppBlackAdVo;
import cn.adwalker.ad.admin.app.vo.AppSelCampaign;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.CacheUtils;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultErrorVo;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;

/**
 * <p>
 * Title: ConfirmPackageController
 * </p>
 * <p>
 * Description:媒体添加广告黑名单
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-7-16
 */

@Controller
public class AppBlackController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(AppBlackController.class);

	/** 广告业务 */
	@Resource
	private IApplicationBlackService applicationBlackService;

	/**
	 * <p>
	 * Title: BlackList
	 * </p>
	 * <p>
	 * Description:媒体添加应用黑名单List
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-7-16
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!operationBlackList.do")
	public String BlackList(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, Blackbean bean) {
		List<AppBlackAdVo> list = null;
		try {
			SysUserVo user = (SysUserVo) session.getAttribute("manageUser");
			IPageInfo pageInfo = new SetPage(request);
			list = this.applicationBlackService.findList(bean, user, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.debug("获取应用黑名单列表异常！");
			e.printStackTrace();
		}
		return "media/app/black_list";
	}
	
	
	@RequestMapping("/appSelCampaign.do")
	public String roleSelPermission(HttpServletRequest request,
			HttpServletResponse response, SelCampaignQueryBean bean) {
		try {
			IPageInfo pageInfo = new SetPage(request);
			List<AppSelCampaign> list =null;
			if (bean!=null&&bean.getApp_id()!=null) {
				list = applicationBlackService
						.findCampaign(bean, pageInfo);	
			}
			request.setAttribute("list", list);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			return "media/app/app_config";
		} catch (Exception e) {
			logger.debug("获取权限列表异常");
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping("/blackChangeStatus.do")
	public String changeStatus(HttpServletRequest request,
			HttpServletResponse Response, Long id, Integer status,Long app_id)
			throws IOException {
		ResultVo vo = null;
		try {
			vo = new ResultSuccessVo();
			applicationBlackService.changetStatus(id,status);
			CacheUtils.updateConfigBlackCache(app_id);
		} catch (Exception e) {
			e.printStackTrace();
			vo = new ResultErrorVo("系统错误");

		}
		OutputHelper.outPut(Response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * <p>
	 * Title: appBlackAd
	 * </p>
	 * <p>
	 * Description:修改黑名单
	 * </p>
	 * 
	 * @param request
	 * @param Response
	 * @param id
	 * @param ApiLog
	 * @return
	 * @throws IOException
	 * @author lichuang
	 * @date 2013-7-16
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!updateApplicationBlack.do")
	public String updateApplicationBlack(HttpServletRequest request,
			HttpServletResponse Response, Long applicationId, String black)
			throws IOException {
		ResultVo vo = null;
		try {
			vo = new ResultSuccessVo();
			applicationBlackService
					.updateApplicationBlack(applicationId, black);
			CacheUtils.updateConfigBlackCache(applicationId);
		} catch (Exception e) {
			e.printStackTrace();
			vo = new ResultErrorVo("系统错误");

		}
		OutputHelper.outPut(Response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	@RequestMapping("/manage!deleteApplicationBlack.do")
	public String deleteBlackAd(HttpServletRequest request,
			HttpServletResponse Response, Long applicationId)
			throws IOException {
		ResultVo vo = null;
		try {
			vo = new ResultSuccessVo();
			applicationBlackService.deleteApplicationBlack(applicationId);
			CacheUtils.updateConfigBlackCache(applicationId);
		} catch (Exception e) {
			e.printStackTrace();
			vo = new ResultErrorVo("系统错误");
		}
		OutputHelper.outPut(Response, JacksonMapper.objectToJsonString(vo));
		return null;
	}
}
