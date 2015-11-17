package cn.adwalker.ad.admin.operation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.finance.vo.SelectMounthVo;
import cn.adwalker.ad.admin.operation.bean.AchievementReportbean;
import cn.adwalker.ad.admin.operation.form.AchievementPublishForm;
import cn.adwalker.ad.admin.operation.service.IOperationAchievementReportService;
import cn.adwalker.ad.admin.operation.vo.AchievementReportSumVo;
import cn.adwalker.ad.admin.operation.vo.AchievementReportVo;
import cn.adwalker.ad.admin.operation.vo.ChannelDetailVo;
import cn.adwalker.ad.admin.operation.vo.ChannelPlatformSumVo;
import cn.adwalker.ad.admin.operation.vo.PlatformDetailVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.ExportUtils;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.core.vo.ResultErrorVo;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.model.ad.domain.Area;

/**
 * <p>
 * Title: OperationAchievementReportController
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
public class OperationAchievementReportController extends
		AbstractControllerParent {

	@Resource
	private IOperationAchievementReportService operationAchievementReportService;
	/** 日志记录器 */
	Logger logger = Logger
			.getLogger(OperationAchievementReportController.class);

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
	@RequestMapping("/manage!achievementReportList.do")
	public String CampaignConfirmList(HttpServletRequest request,
			HttpServletResponse response, AchievementReportbean bean) {
		List<AchievementReportVo> list = null;
		AchievementReportSumVo sum = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = this.operationAchievementReportService.findByPage(bean,
					pageInfo);
			// 求和
			sum = operationAchievementReportService.findSum(bean);
			List<Area> areaList =operationAchievementReportService.findByArea();
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
		return "operation/ope_achievement_report_list";
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
	@RequestMapping("manage!achievementReportListDown.do")
	public String achievementReportListDown(HttpServletRequest request,
			HttpServletResponse response, AchievementReportbean bean) {
		List<AchievementReportVo> list = null;
		AchievementReportSumVo sum = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = this.operationAchievementReportService.findAll(bean);
			// 求和
			sum = operationAchievementReportService.findSum(bean);
			boolean[] isMoney = new boolean[] { false, false, false, false,
					false, false, false, false, true, false, false, false,
					false, false, false, false, false, false, false, true,
					false, false, false, true, false };

			ExportUtils.exportExcel(
					list,
					"业绩报表",
					"业绩报表",
					new String[] { "发生开始时间", "发生结束时间", "广告主id", "广告主名称",
							"活动id", "活动名称", "预确认数", "确认数", "损耗", "单价", "结算方式",
							"总收入", "总成本", "总毛利", "平台收入", "平台成本", "平台毛利",
							"渠道收入", "渠道成本", "	渠道毛利", "平台类型", "状态", "销售人员",
							"大区", "录数人" },
					new String[] { "month_stat_date", "month_end_date",
							"adv_id", "company_name", "campaign_id",
							"campaign_name", "forecast_amount",
							"income_amount", "ullage", "price", "charge_type",
							"income_money", "cost_money", "gross_profit_rate",
							"platform_income_money", "platform_cost_money",
							"pla_gross_profit_rate", "channel_income_money",
							"channel_cost_money", "cha_gross_profit_rate",
							"os", "status_name", "name", "area_type_name",
							"create_user_name" },
					new String[] { "汇总", "--", "--", "--", "--", "--",
							"" + sum.getSum_forecast_amount(),
							"" + sum.getSum_income_amount(), "--", "--", "--",
							"" + sum.getSum_income_money(),
							"" + sum.getSum_cost_money(),
							"" + sum.getSum_gross_profit(),
							"" + sum.getSum_income_plm_money(),
							"" + sum.getSum_cos_plm_money(),
							"" + sum.getSum_plm_gross_profit(),
							"" + sum.getSum_income_cha_money(),
							"" + sum.getSum_cost_cha_money(),
							"" + sum.getSum_cha_gross_profit(), "--", "--",
							"--", "--", "--" }, null, null, isMoney, null,
					response);
			
			List<SelectMounthVo> monthList = new ArrayList<SelectMounthVo>();
			monthList.add(new SelectMounthVo("2013-8", "2013-08-01", "2013-08-31"));
			monthList.add(new SelectMounthVo("2013-9", "2013-09-01", "2013-09-30"));
			monthList.add(new SelectMounthVo("2013-10", "2013-10-01", "2013-10-31"));
			monthList.add(new SelectMounthVo("2013-11", "2013-11-01", "2013-11-30"));
			request.setAttribute("selMonth", monthList);
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
		return "operation/ope_achievement_report_list";
	}

	/**
	 * 
	 * <p>
	 * Title: prePublish
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @author cuidd
	 * @date 2013-12-10
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("prePublishachievement.do")
	public String prePublish(HttpServletRequest request,
			HttpServletResponse response, Long id) {
		request.setAttribute("id", id);
		return "operation/pre_publish";
	}

	/**
	 * <p>
	 * Title: achievementReport
	 * </p>
	 * <p>
	 * Description:发布业绩
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws IOException
	 * @author lichuang
	 * @date 2013-10-26
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!achievementReportPublish.do")
	public String achievementReport(HttpServletRequest request,
			HttpServletResponse response, AchievementPublishForm form)
			throws IOException {
		ResultVo vo = null;
		try {
			vo = new ResultSuccessVo();
			this.operationAchievementReportService.publish(form);
		} catch (Exception e) {
			e.printStackTrace();
			vo = new ResultErrorVo("系统错误");

		}
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
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
	@RequestMapping("/manage!getPlaDetailInfo.do")
	public String getPlaInfo(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, String confirm_id,
			String month_stat_date, String month_end_date, String cost_date)
			throws Exception {
		AchievementReportbean bean = new AchievementReportbean();
		List<PlatformDetailVo> list = null;
		ChannelPlatformSumVo sum = null;
		IPageInfo pageInfo = new SetPage(request);
		if (ObjectUtils.isNotEmpty(confirm_id)) {
			bean.setConfirm_id(confirm_id);

		}
		list = this.operationAchievementReportService
				.getPlaInfo(bean, pageInfo);
		sum = operationAchievementReportService.findPlaSum(bean);
		request.setAttribute("list", list);
		request.setAttribute("sum", sum);
		request.setAttribute("str_stat", cost_date);
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
	@RequestMapping("/manage!getChaDetailInfo.do")
	public String getChaInfo(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, Long confirm_id,
			String month_stat_date, String month_end_date, String cost_date)
			throws Exception {
		List<ChannelDetailVo> list = null;
		ChannelPlatformSumVo sum = null;
		IPageInfo pageInfo = new SetPage(request);
		list = this.operationAchievementReportService.getChaInfo(confirm_id,
				pageInfo);
		sum = operationAchievementReportService.findChaSum(confirm_id);
		request.setAttribute("list", list);
		request.setAttribute("str_stat", cost_date);
		request.setAttribute("sum", sum);
		request.setAttribute("month_end_date", month_end_date);
		request.setAttribute("month_stat_date", month_stat_date);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("pageRecord", pageInfo.getTotalRow());
		request.setAttribute("page", pageInfo.getPageSize());

		return "operation/ope_ach_cha__detail";

	}
	
	/**
	* <p>Title: reduceAchievement</p>
	* <p>Description:业绩报表还原</p>
	* @param id
	* @param response
	* @return
	* @author lichuang
	* @date 2014-2-17
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!reduceAchievementCampaign.do")
		public String reduceAchievement(Long id,
				HttpServletResponse response) {
			try {
				this.operationAchievementReportService.updatereduceAchievement(id);
				ResultVo vo = new ResultSuccessVo();
				OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
			} catch (Exception e) {
				logger.error(e.getLocalizedMessage());
			}

			return null;
		}

}
