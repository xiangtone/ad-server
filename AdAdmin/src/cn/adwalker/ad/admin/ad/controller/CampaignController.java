/**
 * 
 */
package cn.adwalker.ad.admin.ad.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.ad.bean.CampaignQueryBean;
import cn.adwalker.ad.admin.ad.form.CampaignAddForm;
import cn.adwalker.ad.admin.ad.form.CampaignForm;
import cn.adwalker.ad.admin.ad.form.CampaignPriceForm;
import cn.adwalker.ad.admin.ad.service.ICampaignService;
import cn.adwalker.ad.admin.ad.vo.CampaignEditVo;
import cn.adwalker.ad.admin.ad.vo.CampaignInfoVo;
import cn.adwalker.ad.admin.ad.vo.CampaignVo;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.mail.service.ICampaignMailService;
import cn.adwalker.ad.admin.operation.service.IDspInfoImpService;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.pool.SpringDatePool;
import cn.adwalker.core.util.DateUtil;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultErrorVo;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.model.ad.domain.CampaignSalesman;
import cn.adwalker.model.common.domain.Sort;
import cn.adwalker.model.operation.domain.DspInfo;

/**
 * 
 * <p>
 * Title: CampaignController
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-2
 */
@Controller
public class CampaignController extends AbstractControllerParent {

	@Resource
	private ICampaignService campaignService;

	@Resource
	private ICampaignMailService campaignMailService;
	@Resource
	private IDspInfoImpService dspInfoImpService;

	@Resource
	private SpringDatePool springDatePool;

	/** 日志记录器 */
	Logger logger = Logger.getLogger(CampaignController.class);

	/**
	 * 
	 * <p>
	 * Title: publishActivityManage
	 * </p>
	 * <p>
	 * Description:活动发布
	 * </p>
	 * 
	 * @param request
	 * @param session
	 * @param model
	 * @param advEmail
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-3-21
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!addCampaign.do")
	public String addCampaign(HttpServletRequest request, HttpSession session,
			Map<String, Object> model, String advEmail, Long adv_id)
			throws Exception {
		logger.debug("into method publishActivity: 管理员添加活动");
		List<Sort> sList = springDatePool.getBigSort();
		List<DspInfo> dspList = dspInfoImpService.getDspInfo();
		List<CampaignSalesman> salesmanList = campaignService
				.getSalesmanList(adv_id);
		model.put("sList", sList);
		model.put("salesmanList", salesmanList);
		model.put("advEmail", advEmail);
		model.put("dspList", dspList);
		model.put("adv_id", adv_id);
		model.put("today", DateUtil.formatDate(new Date()));
		return "ad/campaign/campaign_add";
	}

	/**
	 * 添加活动
	 * <p>
	 * Title: AddPublishActivity
	 * </p>
	 * <p>
	 * Description:保存活动
	 * </p>
	 * 
	 * @param request
	 * @param model
	 * @param session
	 * @param code
	 * @param activityPublishVo
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-3-21
	 * @return String
	 * @version 1.0
	 */

	@RequestMapping("/manage!saveCampaign.do")
	public void saveCampaign(HttpServletResponse response, HttpSession session, String code,
			CampaignAddForm form, Long adv_id,int isSubmit) throws Exception {
		logger.debug("into method publishActivity: 操作人保存活动");
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		if (manageUser != null) {
			form.setCreate_user(manageUser.getId());
		}
		ResultVo vo =null;
		try {
			campaignService.saveCampaign(form, manageUser,(isSubmit == 1) ? true : false);
			 vo = new ResultSuccessVo();
		} catch (Exception e) {
			 vo = new ResultErrorVo();
			e.printStackTrace();
		}
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		

	}

	/**
	 * 
	 * <p>
	 * Title: addCampaignCategory
	 * </p>
	 * <p>
	 * Description:添加广告定向投放时间
	 * </p>
	 * 
	 * @param request
	 * @param model
	 * @param session
	 * @param id
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-4-18
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!addCampaignCategory.do")
	public String addCampaignCategory(HttpServletRequest request,
			Map<String, Object> model, HttpSession session, Long id)
			throws Exception {
		try {
			request.setAttribute("id", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ad/campaign/campaign_category_add";

	}

	/**
	 * <p>
	 * Title: updateCampaign
	 * </p>
	 * <p>
	 * Description:更新活动
	 * </p>
	 * 
	 * @param request
	 * @param session
	 * @param res
	 * @param code
	 * @param form
	 * @param category
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-30
	 * @return String
	 * @version 1.0
	 */

	@RequestMapping("/manage!updateCampaign.do")
	public String updateCampaign(HttpServletRequest request,
			HttpSession session, HttpServletResponse res, String code,
			CampaignForm form, Long category[], Double price_update)
			throws Exception {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		if (manageUser != null) {
			form.setCreate_user(manageUser.getId());
		}
		ResultVo vo = null;
		if (form != null && form.getId() != null) {
			campaignService.updateCampaign(form, category, price_update);
		}

		vo = new ResultSuccessVo();
		OutputHelper.outPut(res, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * 
	 * <p>
	 * Title: listactivityFind
	 * </p>
	 * <p>
	 * Description:查询活动list
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-3-21
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!campaignList.do")
	public String campaignList(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			Map<String, Object> model, CampaignQueryBean bean) {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		List<CampaignVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = this.campaignService.findByPage(bean, manageUser, pageInfo);
			List<Sort> sList = springDatePool.getBigSort();
			model.put("sList", sList);
			model.put("pageInfo", pageInfo);
			model.put("list", list);
			model.put("bean", bean);
			model.put("pageRecord", pageInfo.getTotalRow());
			model.put("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "ad/campaign/campaign_list";
	}

	/**
	 * 
	 * <p>
	 * Title: auditingActivity
	 * </p>
	 * <p>
	 * Description:审核活动
	 * </p>
	 * 
	 * @param request
	 * @param model
	 * @param session
	 * @param code
	 * @param actId
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-2
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!preAuditCampaign.do")
	public String preAuditCampaign(HttpServletRequest request,
			Map<String, Object> model, HttpSession session, String code,
			Long campaign_id) throws Exception {
		CampaignInfoVo campaignInfoVo = null;
		try {
			campaignInfoVo = this.campaignService.getCampaignInfo(campaign_id);
			List<Sort> sList = springDatePool.getBigSort();
			model.put("sList", sList);
			model.put("vo", campaignInfoVo);
		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "ad/campaign/campaign_audit";
	}

	/**
	 * <p>
	 * Title: auditCampaign
	 * </p>
	 * <p>
	 * Description:审核活动
	 * </p>
	 * 
	 * @param request
	 * @param model
	 * @param session
	 * @param response
	 * @param note
	 * @param ispass
	 * @param id
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-22
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!auditCampaign.do")
	public String auditCampaign(HttpServletRequest request,
			Map<String, Object> model, HttpSession session,
			HttpServletResponse response, String note, Integer ispass, Long id)
			throws Exception {
		try {
			this.campaignService.auditingStatus(id, ispass, note, manageUser);
			if (ispass == 1) {
				campaignMailService.sendMailAudit(id, manageUser);
			}
		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		ResultVo vo = new ResultSuccessVo();
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	

	/**
	 * 
	 * <p>
	 * Title: findActivity
	 * </p>
	 * <p>
	 * Description:活动信息查看
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param actId
	 * @return
	 * @author lichuang
	 * @date 2013-4-7
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!campaignInfo.do")
	public String campaignInfo(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,
			Long campaign_id) {
		CampaignInfoVo campaignInfoVo = null;
		try {
			campaignInfoVo = this.campaignService.getCampaignInfo(campaign_id);
			List<Sort> sList = springDatePool.getBigSort();
			model.put("sList", sList);
			model.put("vo", campaignInfoVo);
		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "ad/campaign/campaign_info";
	}

	/**
	 * 
	 * <p>
	 * Title: findActivity
	 * </p>
	 * <p>
	 * Description:活动信息查看修改界面
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param actId
	 * @return
	 * @author lichuang
	 * @date 2013-4-7
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!editCampaign.do")
	public String editCampaign(HttpServletRequest request,
			Map<String, Object> model, Long id) {
		CampaignEditVo view = null;
		try {
			view = this.campaignService.getCampaign(id);
			List<DspInfo> dspList = dspInfoImpService.getDspInfo();
			List<CampaignSalesman> salesmanList = campaignService
					.getSalesmanList(view.getAdv_id());
			List<Sort> sList = springDatePool.getBigSort();
			model.put("salesmanList", salesmanList);
			model.put("sList", sList);
			model.put("vo", view);
			model.put("dspList", dspList);
			model.put("today", DateUtil.formatDate(new Date()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ad/campaign/campaign_edit";
	}

	/**
	 * <p>
	 * Title: editCampaign
	 * </p>
	 * <p>
	 * Description:修改活动单价
	 * </p>
	 * 
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 * @author cuidd
	 * @date 2014年8月11日
	 * @return String
	 * @version 1.0
	 */

	@RequestMapping("/manage!editCampaignPrice.do")
	public String editCampaignPrice(HttpServletRequest request,
			Map<String, Object> model, Long id) {
		CampaignEditVo view = null;
		try {
			view = this.campaignService.getCampaign(id);
			model.put("vo", view);
			model.put("today", DateUtil.formatDate(new Date()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ad/campaign/campaign_edit_price";
	}

	/**
	 * <p>
	 * Title: updateCampaignPrice
	 * </p>
	 * <p>
	 * Description:修改活动单价
	 * </p>
	 * 
	 * @param request
	 * @param session
	 * @param res
	 * @param code
	 * @param form
	 * @param category
	 * @param price_update
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2014年8月11日
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!updateCampaignPrice.do")
	public String updateCampaignPrice(HttpServletRequest request,
			HttpSession session, HttpServletResponse res, String code,
			CampaignPriceForm form, Long category[], Double price_update)
			throws Exception {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		ResultVo vo = null;
		if (form != null && form.getId() != null) {
			campaignService.updateCampaignPrice(form, price_update, manageUser);
		}

		vo = new ResultSuccessVo();
		OutputHelper.outPut(res, JacksonMapper.objectToJsonString(vo));
		return null;
	}
}
