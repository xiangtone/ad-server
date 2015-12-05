package cn.adwalker.ad.admin.sales.controller;

import java.util.List;
import java.util.Map;

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
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.report.bean.SalesAchievementBean;
import cn.adwalker.ad.admin.report.service.IReportSalesAchievementService;
import cn.adwalker.ad.admin.report.vo.ReportSalesAchievementVo;
import cn.adwalker.ad.admin.report.vo.SumSalesAchievementVo;
import cn.adwalker.ad.admin.sales.service.ICampaignSalesmanService;
import cn.adwalker.ad.config.Constant;

/**
 * <p>
 * Title: SalesAchievementController
 * </p>
 * <p>
 * Description:销售业绩模块
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-8-19
 */
@Controller
public class SalesAchievementController extends AbstractControllerParent {
	private static final Logger logger = LoggerFactory
			.getLogger(SalesAchievementController.class);

	@Resource
	private IReportSalesAchievementService salesAchievementService;

	@Resource
	private ICampaignSalesmanService campaignSalesmanService;

	/**
	 * <p>
	 * Title: list
	 * </p>
	 * <p>
	 * Description:销售业绩List
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @param bean
	 * @param model
	 * @return
	 * @author lichuang
	 * @date 2013-8-19
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("manage!salesAchievement.do")
	public String list(HttpSession session, HttpServletRequest request,
			SalesAchievementBean bean, Map<String, Object> model) {
		SysUserVo user = (SysUserVo) session.getAttribute("manageUser");
		try {
			if (bean == null) {
				bean = new SalesAchievementBean();
			}
			if (user.getRoleCode().equals(Constant.ROLE_CODE_SALES_MANAGER)) {
				bean.setArea_type_ad(user.getArea_type());
			} else if (user.getRoleCode().equals(Constant.ROLE_CODE_SALES_MAN)) {
				Long l = campaignSalesmanService.getSalesmanIdBySysUser(user
						.getId());
				bean.setSales_id(l);
			}

			IPageInfo pageInfo = new SetPage(request, 20);
			List<ReportSalesAchievementVo> list = this.salesAchievementService
					.findByPage(pageInfo, bean);
			// 求和
			SumSalesAchievementVo sumresult = this.salesAchievementService
					.getSumSales(bean);
			model.put("pageInfo", pageInfo);
			model.put("sumresult", sumresult);
			model.put("bean", bean);
			request.setAttribute("list", list);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return "report/report_sales_achievement";

	}

	/**
	 * <p>
	 * Title: exportCSV
	 * </p>
	 * <p>
	 * Description:销售业绩List导出
	 * </p>
	 * 
	 * @param session
	 * @param response
	 * @param bean
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-8-19
	 * @return void
	 * @version 1.0
	 */
	@RequestMapping("/manage!salesAchievementDown.do")
	public String AchievementDown(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			SalesAchievementBean bean) throws Exception {
		List<ReportSalesAchievementVo> consumeLsit = null;
		if (bean == null) {
			bean = new SalesAchievementBean();
		}
		try {
			// list数据
			consumeLsit = this.salesAchievementService.findAll(bean);

			// 求和
			SumSalesAchievementVo sumresult = this.salesAchievementService
					.getSumSales(bean);
			// 导出报表
			boolean[] isMoney = new boolean[] { false, false, false, false,
					false, false, false, false, false, false, };
			ExportUtils.exportExcel(consumeLsit, "销售业绩统计报表", "销售业绩统计报表",
					new String[] { "发生时间", "广告主ID", "广告主账号", "活动ID", "活动名称",
							"平台类型", "确认收入", "销售人", "大区" }, new String[] {
							"static_date", "adv_Id", "adv_email",
							"campaign_id", "campaign_name", "os",
							"income_money", "sales_name", "area_type_name", },
					new String[] { "统计", "-", "-", "-", "-", "-",
							"" + sumresult.getSum_income_money(), "-", "-" },
					null, null, isMoney, null, response);

			request.setAttribute("list", consumeLsit);
			request.setAttribute("bean", bean);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "report/report_sales_achievement";
	}
}
