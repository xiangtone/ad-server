package cn.adwalker.ad.admin.operation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.operation.bean.DevIncomeAuditbean;
import cn.adwalker.ad.admin.operation.service.IOperationDevIncomeAuditService;
import cn.adwalker.ad.admin.operation.vo.DevIncomeAuditSumVo;
import cn.adwalker.ad.admin.operation.vo.IncomeAuditVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.DateUtil;
import cn.adwalker.core.util.ExportUtils;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultErrorVo;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;

/**
 * <p>
 * Title: DevIncomeAuditController
 * </p>
 * <p>
 * Description:运营开发者收入审核
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-17
 */

@Controller
public class OperationDevIncomeAuditController extends AbstractControllerParent {

	private static final Logger logger = LoggerFactory
			.getLogger(OperationDevIncomeAuditController.class);

	@Resource
	private IOperationDevIncomeAuditService devIncomeAuditService;

	/**
	 * <p>
	 * Title: devIncomeAuditList
	 * </p>
	 * <p>
	 * Description:开发者收入审核List
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @param bean
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-6-17
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/operation/devIncomeAuditList.do")
	public String list(HttpSession session, HttpServletRequest request,
			DevIncomeAuditbean bean) throws Exception {
		List<IncomeAuditVo> devList = null;
		DevIncomeAuditSumVo sum = null;
		IPageInfo pageInfo = new SetPage(request);
		if (bean == null) {
			bean = new DevIncomeAuditbean();
		}
		if (StringUtils.isEmpty(bean.getStatStartTime())
				&& StringUtils.isEmpty(bean.getStatEndTime())) {
			Date date = new Date();
			date = DateUtil.addDays(date, -4);
			bean.setStatEndTime(DateUtil.formatDate(date));
			date = DateUtil.addDays(date, -10);
			bean.setStatStartTime(DateUtil.formatDate(date));
		}
		try {
			devList = this.devIncomeAuditService.findList(bean, pageInfo);
			sum = devIncomeAuditService.findSummaryByCondition(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("devList", devList);
		request.setAttribute("summary", sum);
		request.setAttribute("bean", bean);
		return "operation/ope_dev_income_audit_list";
	}

	/**
	 * <p>
	 * Title: devAdStatListDownloadManage
	 * </p>
	 * <p>
	 * Description:开发者收入审核List导出报表
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-6-17
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!operationDevIncomeAuditListDownload.do")
	public String devAdStatListDownloadManage(HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			DevIncomeAuditbean bean) throws Exception {
		// 组装vo
		List<IncomeAuditVo> devList = new ArrayList<IncomeAuditVo>();
		DevIncomeAuditSumVo sum = null;
		IPageInfo pageInfo = new SetPage(request);
		if (bean == null) {
			bean = new DevIncomeAuditbean();
		}
		if (StringUtils.isEmpty(bean.getStatStartTime())
				&& StringUtils.isEmpty(bean.getStatEndTime())) {
			Date date = new Date();
			date = DateUtil.addDays(date, -4);
			bean.setStatEndTime(DateUtil.formatDate(date));
			date = DateUtil.addDays(date, -10);
			bean.setStatStartTime(DateUtil.formatDate(date));
		}
		try {
			devList = this.devIncomeAuditService.findAll(bean);
			sum = devIncomeAuditService.findSummaryByCondition(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("devList", devList);
		request.setAttribute("summary", sum);
		request.setAttribute("bean", bean);
		// 此处调用excel导出工具类方法
		// 导出报表
		boolean[] isMoney = new boolean[] { false, false, true, false, false,
				false, false, false, false, true, false, false, false, false,
				false, false };
		ExportUtils.exportExcel(devList, "网站主收入审核明细", "网站主收入审核明细",
				new String[] { "发生时间", "网站主ID", "网站主名称", "账户状态", "应用ID",
						"应用名称", "活动ID", "活动名称", "结算状态", "网站主确认佣金",
						"提交时间", "操作人" }, new String[] { "effect_time",
						"dev_id", "dev_email", "dev_statusString", "app_id",
						"app_name", "campaign_id", "campaign_name",
						"statusString","confirmMoney",
						"manager_time", "manager_name" }, new String[] { "汇总",
						"--", "--", "--", "--", "--", "--", "--", "--",
						"" + sum.getSum_sumMoney(), "--", "--"},
				null, null, isMoney, null, response);
		return null;
	}

	/**
	 * <p>
	 * Title: devIncomeStatus
	 * </p>
	 * <p>
	 * Description:单个结算
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @param type
	 * @return
	 * @author lichuang
	 * @date 2013-6-17
	 * @return String
	 * @version 1.0
	 * @throws IOException
	 */
	@RequestMapping("/operation/devIncomeStatus.do")
	public String devIncomeStatus(HttpServletRequest request, HttpServletResponse response, Long id) throws IOException {
		ResultVo vo = null;
		SysUserVo manageUser = (SysUserVo) request.getSession().getAttribute("manageUser");
		try {
			Long[] ids = { id };
			this.devIncomeAuditService.tranAudit(ids, manageUser);// 处理审核
			vo = new ResultSuccessVo();
		} catch (Exception e) {
			logger.debug("开发者广告效果数据单个审核处理异常！" + e.getLocalizedMessage());
			vo = new ResultErrorVo("开发者广告效果数据单个审核处理异常！");
		}
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
	}
	
	
	
	@RequestMapping("/operation/devIncomeConfrim_no.do")
	public String devIncomeConfrim_no(HttpServletRequest request, HttpServletResponse response, Long id) throws IOException {
		ResultVo vo = null;
		SysUserVo manageUser = (SysUserVo) request.getSession().getAttribute("manageUser");
		try {
			Long[] ids = { id };
			this.devIncomeAuditService.tranAudit_no(ids, manageUser);// 处理审核
			vo = new ResultSuccessVo();
		} catch (Exception e) {
			logger.debug("开发者广告效果数据单个审核处理异常！" + e.getLocalizedMessage());
			vo = new ResultErrorVo("开发者广告效果数据单个审核处理异常！");
		}
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * <p>
	 * Title: updateBatchDevIncome
	 * </p>
	 * <p>
	 * Description:批量更新
	 * </p>
	 * 
	 * @param request
	 * @param checkbox
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-6-17
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/operation/updateBatchDevIncome.do")
	public void updateBatchDevIncome(HttpServletRequest request, String[] dev_checkbox) throws Exception {
		SysUserVo manageUser = (SysUserVo) request.getSession().getAttribute("manageUser");
		Long[] ids = new Long[dev_checkbox.length];
		try {
			for (int i = 0; i < dev_checkbox.length; i++) {
				String[] s = dev_checkbox[i].split("\\^");
				ids[i] = Long.parseLong(s[0]);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		try {
			devIncomeAuditService.tranAudit(ids, manageUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * Title: keyBalanceDevIncome
	 * </p>
	 * <p>
	 * Description:一键结算
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @param bean
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-10-15
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!keyBalanceDevIncome.do")
	public void keyBalanceDevIncome(HttpSession session, HttpServletRequest request, DevIncomeAuditbean bean) throws Exception {
		SysUserVo manageUser = (SysUserVo) request.getSession().getAttribute("manageUser");
		List<IncomeAuditVo> devList = null;
		if (bean == null) {
			bean = new DevIncomeAuditbean();
		}
		if (StringUtils.isEmpty(bean.getStatStartTime())
				&& StringUtils.isEmpty(bean.getStatEndTime())) {
			Date date = new Date();
			date = DateUtil.addDays(date, -4);
			bean.setStatEndTime(DateUtil.formatDate(date));
			date = DateUtil.addDays(date, -10);
			bean.setStatStartTime(DateUtil.formatDate(date));
		}
		try {
			if (bean != null && bean.getDev_status() == 1 && bean.getStatus() == 0 && bean.getDev_confirmMoney() != null) {
				devList = this.devIncomeAuditService.findAll(bean);
				Long[] ids = new Long[devList.size()];
				for (int i = 0; i < devList.size(); i++) {
					IncomeAuditVo vo = devList.get(i);
					ids[i] = vo.getId();
				}
				devIncomeAuditService.tranAudit(ids, manageUser);
			}
			request.setAttribute("bean", bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
