package cn.adwalker.ad.admin.sales.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.sales.bean.CampaignSalesmanBean;
import cn.adwalker.ad.admin.sales.form.SalesmanAddForm;
import cn.adwalker.ad.admin.sales.form.SalesmanEditForm;
import cn.adwalker.ad.admin.sales.service.ICampaignSalesmanService;
import cn.adwalker.ad.admin.sales.vo.CampaignSalesmanVo;
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
public class CampaignSalesmanController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(CampaignSalesmanController.class);

	/** 广告方案调整相关业务 */
	@Resource
	private ICampaignSalesmanService campaignSalesmanService;

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
	@RequestMapping("/salesmanList.do")
	public String list(HttpServletRequest request,
			HttpServletResponse response, CampaignSalesmanBean bean) {
		List<CampaignSalesmanVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = this.campaignSalesmanService.findByPage(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.debug("获取方案调整列表异常！");
			e.printStackTrace();
		}
		return "sales/salesman/list";
	}

	@RequestMapping("/salesmanAdd.do")
	public String add(HttpServletRequest request, HttpServletResponse response,
			Long adId, Map<String, Object> model) throws Exception {
		List<SysUserVo> list = campaignSalesmanService.querySysUser(null);
		request.setAttribute("sysUserList", list);
		return "sales/salesman/add";
	}

	@RequestMapping("/saveSalesman.do")
	public String save(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			SalesmanAddForm form) {
		try {
			manageUser = (SysUserVo) session.getAttribute("manageUser");
			campaignSalesmanService.saveSalesman(form, manageUser);
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
	@RequestMapping("/salesmanEdit.do")
	public String findAdContent(HttpServletRequest request,
			HttpServletResponse response, Long id, Map<String, Object> model)
			throws Exception {
		SalesmanEditVo vo = campaignSalesmanService.findById(id);
		List<SysUserVo> list = campaignSalesmanService.querySysUser(id);
		request.setAttribute("sysUserList", list);
		request.setAttribute("vo", vo);
		request.setAttribute("id", id);
		return "sales/salesman/salesman_edit";
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
	@RequestMapping("/updateSalesman.do")
	public String updateAdAjustment(HttpServletRequest request,
			HttpServletResponse response, SalesmanEditForm form) {
		try {
			campaignSalesmanService.updateSalesman(form);
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/deleteSalesman.do")
	public String delete(HttpServletRequest request,
			HttpServletResponse response, Long id) {
		try {
			campaignSalesmanService.delete(id);
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
