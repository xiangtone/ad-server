package cn.adwalker.ad.admin.app.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.app.bean.AppMediaQueryBean;
import cn.adwalker.ad.admin.app.form.AppMediaAddForm;
import cn.adwalker.ad.admin.app.form.AppMediaEditForm;
import cn.adwalker.ad.admin.app.service.IAppMediaService;
import cn.adwalker.ad.admin.app.vo.AppMediaVo;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.sales.vo.SalesmanEditVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;

/**
 * 
 * <p>
 * Title: CampaignSalesmanController
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2014-2-19
 */
@Controller
public class AppMediaController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(AppMediaController.class);

	/** 广告方案调整相关业务 */
	@Resource
	private IAppMediaService appMediaService;

	/**
	 * <p>
	 * Title: listAdAjustment
	 * </p>
	 * <p>
	 * Description:方案调整
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * @date 2013-5-15
	 * @version 1.0
	 * @author zouguibao
	 */
	@RequestMapping("/appMediaList.do")
	public String list(HttpServletRequest request,
			HttpServletResponse response, AppMediaQueryBean bean) {
		List<AppMediaVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = this.appMediaService.findByPage(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.debug("获取方案调整列表异常！");
			e.printStackTrace();
		}
		return "media/appmedia/list";
	}

	@RequestMapping("/appMediaAdd.do")
	public String add(HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> model) throws Exception {
		List<SysUserVo> list = appMediaService.querySysUser(null);
		request.setAttribute("sysUserList", list);
		return "media/appmedia/add";
	}

	@RequestMapping("/saveAppMedia.do")
	public String save(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			AppMediaAddForm form) {
		try {
			manageUser = (SysUserVo) session.getAttribute("manageUser");
			appMediaService.saveSalesman(form, manageUser);
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <p>
	 * Title: findAdContent
	 * </p>
	 * <p>
	 * Description:方案详细内容查询
	 * </p>
	 * 
	 * @param adId
	 * @param price
	 * @param request
	 * @return String
	 * @date 2013-5-15
	 * @version 1.0
	 * @author zouguibao
	 * @throws Exception
	 */
	@RequestMapping("/appMediaEdit.do")
	public String findAdContent(HttpServletRequest request,
			HttpServletResponse response, Long id, Map<String, Object> model)
			throws Exception {
		SalesmanEditVo vo = appMediaService.findById(id);
		List<SysUserVo> list = appMediaService.querySysUser(id);
		request.setAttribute("sysUserList", list);
		request.setAttribute("vo", vo);
		request.setAttribute("id", id);
		return "media/appmedia/edit";
	}

	/**
	 * <p>
	 * Title: updateAdContent
	 * </p>
	 * <p>
	 * Description:修改广告投放方案
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param form
	 * @return String
	 * @date 2013-5-15
	 * @version 1.0
	 * @author zouguibao
	 */
	@RequestMapping("/updateAppMedia.do")
	public String updateAdAjustment(HttpServletRequest request,
			HttpServletResponse response, AppMediaEditForm form) {
		try {
			appMediaService.updateSalesman(form);
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/deleteAppMedia.do")
	public String delete(HttpServletRequest request,
			HttpServletResponse response, Long id) {
		try {
			appMediaService.delete(id);
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
