package cn.adwalker.ad.admin.finance.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.ExportUtils;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.finance.bean.PlatformCostByMonthQueryBean;
import cn.adwalker.ad.admin.finance.service.IPlatformCostQueryByMonthService;
import cn.adwalker.ad.admin.finance.vo.DevIncomeAuditSumVo;
import cn.adwalker.ad.admin.finance.vo.PlatformCostByMonthVo;

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
public class DevIncomeQueryByMonthController extends AbstractControllerParent {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory
			.getLogger(DevIncomeQueryByMonthController.class);

	@Resource
	private IPlatformCostQueryByMonthService service;

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
	@RequestMapping("/paltformCostByMonth.do")
	public String list(HttpSession session, HttpServletRequest request,
			PlatformCostByMonthQueryBean bean) throws Exception {
		List<PlatformCostByMonthVo> devList = null;
		DevIncomeAuditSumVo sum = null;
		IPageInfo pageInfo = new SetPage(request);
		if (bean==null) {
			bean=new PlatformCostByMonthQueryBean();
		}
//		if (StringUtils.isEmpty(bean.getStatStartTime())) {
//			bean.setStatStartTime("2013-12-01");
//		}
//		if (StringUtils.isEmpty(bean.getStatEndTime())) {
//			bean.setStatEndTime("2013-12-31");
//			
//		}
		try {
			devList = this.service.findList(bean, pageInfo);
			sum = service.findSummaryByCondition(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("devList", devList);
		request.setAttribute("summary", sum);
		request.setAttribute("bean", bean);
		return "finance/platform_cost_by_month";
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
	@RequestMapping("/paltformCostByMonthDownload.do")
	public String devAdStatListDownloadManage(HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			PlatformCostByMonthQueryBean bean) throws Exception {
		// 组装vo
		List<PlatformCostByMonthVo> devList = new ArrayList<PlatformCostByMonthVo>();
		DevIncomeAuditSumVo sum = null;
		IPageInfo pageInfo = new SetPage(request);
		if (bean==null) {
			bean=new PlatformCostByMonthQueryBean();
		}
	
		try {
			devList = this.service.findAll(bean);
			sum = service.findSummaryByCondition(bean);
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
				false };
					
		ExportUtils.exportExcel(devList, "网站主收入审核明细", "网站主收入审核明细",
				new String[] { "发生时间", "网站主ID", "网站主名称", "账户状态", "应用ID",
						"应用名称", "活动ID", "活动名称", "结算状态", "网站主确认佣金","业绩提交人", "业绩提交时间",
						"审核人","审核时间" }, new String[] { "effect_time", "dev_id",
						"dev_email", "dev_statusString", "app_id", "app_name",
						"campaign_id", "campaign_name", "statusString",
						"confirmMoney", "manager_name", "manager_time","finance_name","finance_time" },
				new String[] { "汇总", "--", "--", "--", "--", "--", "--", "--", "--",
						"" + sum.getSum_sumMoney(), "--", "--", "--", "--" },
				null, null, isMoney, null, response);
		return "finance/dev_income_audit_list";
	}
}
