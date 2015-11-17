package cn.adwalker.ad.admin.finance.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.finance.bean.AchievementReportbean;
import cn.adwalker.ad.admin.finance.service.IAchievementReportService;
import cn.adwalker.ad.admin.finance.vo.AchievementReportSumVo;
import cn.adwalker.ad.admin.finance.vo.AchievementReportVo;
import cn.adwalker.ad.admin.finance.vo.ChannelDetailVo;
import cn.adwalker.ad.admin.finance.vo.ChannelPlatformSumVo;
import cn.adwalker.ad.admin.finance.vo.PlatformDetailVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.ExportUtils;
import cn.adwalker.model.ad.domain.Area;

/**
 * <p>
 * Title: AchievementReportController
 * </p>
 * <p>
 * Description:业绩报表
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-10-24
 */
@Controller
public class AchievementReportController extends AbstractControllerParent {

	@Resource
	private IAchievementReportService achievementReportService;
	/** 日志记录器 */
	Logger logger = Logger.getLogger(AchievementReportController.class);

	/**
	 * <p>
	 * Title: CampaignConfirmList
	 * </p>
	 * <p>
	 * Description:确认数录入List
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-6-8
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/finance/achievementReportList.do")
	public String CampaignConfirmList(HttpServletRequest request,
			HttpServletResponse response, AchievementReportbean bean) {
		List<AchievementReportVo> list = null;
		AchievementReportSumVo sum = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			bean.setStatus(8l);
			//list = this.achievementReportService.findByPage(bean, pageInfo);
			// 求和
			sum = achievementReportService.findSum(bean);
			List<Area> areaList =achievementReportService.findByArea();
			request.setAttribute("areaList", areaList);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("list", list);
			request.setAttribute("bean", bean);
			request.setAttribute("sum", sum);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());

		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "finance/achievement_report_list";
	}

	/**
	 * <p>
	 * Title: achievementReportListDown
	 * </p>
	 * <p>
	 * Description:确认数录入List导出
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-10-26
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/finance/achievementReportListDown.do")
	public String achievementReportListDown(HttpServletRequest request,
			HttpServletResponse response, AchievementReportbean bean) {
		List<AchievementReportVo> list = null;
		AchievementReportSumVo sum = null;
		IPageInfo pageInfo = new SetPage(request);
		bean.setStatus(8l);
		try {
			//list = this.achievementReportService.findAll(bean);
			// 求和
			sum = achievementReportService.findSum(bean);
			boolean[] isMoney = new boolean[] { false, false, false, false,
					false, false, false, false, false, false, false, false, false, false, false,
					false, false, false, false, true, false, false,false,  
					true, false };
		
			ExportUtils.exportExcel(
					list,
					"财务收入成本总表",
					"财务收入成本总表",
					new String[] { "发生开始时间","发生结束时间", "广告主id", "广告主名称", "活动id", "活动名称", "预确认数",
							"确认数","损耗", "单价", "结算方式", "总收入","总成本","总毛利","平台收入","平台成本",
							"平台毛利","渠道收入","渠道成本","	渠道毛利", "平台类型", "状态", "销售人员",
							"大区", "录数人" },
					new String[] { "month_stat_date","month_end_date",
							"adv_id", "company_name","campaign_id", "campaign_name",
							"forecast_amount", "income_amount", "str_ullage","price",
							"charge_type", "income_money","cost_money","str_gross_profit_rate",
							"platform_income_money","platform_cost_money","str_pla_gross_profit_rate",
							"channel_income_money","channel_cost_money","str_cha_gross_profit_rate",
							"os", "status_name",
							"name", "area_type_name", "create_user_name" },
					new String[] { "汇总", "--", "--", "--","--","--",
							"" + sum.getSum_forecast_amount(),
							"" + sum.getSum_income_amount(), "--", "--","--",
							"" + sum.getSum_income_money(), "" + sum.getSum_cost_money(), "" + sum.getSum_gross_profit()+"%", "" + sum.getSum_income_plm_money(),
							"" + sum.getSum_cos_plm_money(),"" + sum.getSum_plm_gross_profit()+"%","" + sum.getSum_income_cha_money(), "" + sum.getSum_cost_cha_money(), "" + sum.getSum_cha_gross_profit()+"%","--", "--", "--",
							"--", "--" }, null, null, isMoney, null, response);
			
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("list", list);
			request.setAttribute("bean", bean);
			request.setAttribute("sum", sum);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());

		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "finance/achievement_report_list";
	}

	/**
	 * <p>
	 * Title: getPlaInfo
	 * </p>
	 * <p>
	 * Description:平台成本明细
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-10-26
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/finance/getPlaDetailInfo.do")
	public String getPlaInfo(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, Long confirm_id,
			String month_stat_date, String month_end_date,String cost_date) throws Exception {
		List<PlatformDetailVo> list = null;
		ChannelPlatformSumVo sum = null;
		IPageInfo pageInfo = new SetPage(request);
	
		list = this.achievementReportService.getPlaInfo(confirm_id, pageInfo);
		sum = achievementReportService.findPlaSum(confirm_id);
		request.setAttribute("list", list);
		request.setAttribute("sum", sum);
		request.setAttribute("cost_date", cost_date);
		request.setAttribute("month_stat_date", month_stat_date);
		request.setAttribute("month_end_date", month_end_date);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("pageRecord", pageInfo.getTotalRow());
		request.setAttribute("page", pageInfo.getPageSize());

		return "operation/ope_ach_pla_detail";

	}

	/**
	 * <p>
	 * Title: getChaInfo
	 * </p>
	 * <p>
	 * Description:渠道成本明细
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-10-26
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/finance/getChaDetailInfo.do")
	public String getChaInfo(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, Long confirm_id, String month_stat_date,
			String month_end_date,String cost_date) throws Exception {
		List<ChannelDetailVo> list = null;
		ChannelPlatformSumVo sum = null;
		IPageInfo pageInfo = new SetPage(request);
		list = this.achievementReportService.getChaInfo(confirm_id, pageInfo);
		sum = achievementReportService.findChaSum(confirm_id);
		request.setAttribute("list", list);
		request.setAttribute("sum", sum);
		request.setAttribute("cost_date", cost_date);
		request.setAttribute("month_end_date", month_end_date);
		request.setAttribute("month_stat_date", month_stat_date);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("pageRecord", pageInfo.getTotalRow());
		request.setAttribute("page", pageInfo.getPageSize());

		return "operation/ope_ach_cha__detail";

	}
}
