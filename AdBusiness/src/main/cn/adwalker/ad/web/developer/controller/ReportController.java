package cn.adwalker.ad.web.developer.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.core.page.IPageInfo;
import cn.adwalker.ad.core.page.SetPage;
import cn.adwalker.ad.model.application.domain.Application;
import cn.adwalker.ad.upload.util.JacksonMapper;
import cn.adwalker.ad.util.DateUtil;
import cn.adwalker.ad.util.page.PageUtil;
import cn.adwalker.ad.web.common.controller.AbstractControllerParent;
import cn.adwalker.ad.web.developer.bean.DevReportbean;
import cn.adwalker.ad.web.developer.service.ApplicationService;
import cn.adwalker.ad.web.developer.service.DeveloperReportService;
import cn.adwalker.ad.web.developer.vo.DevActualTimeReportVo;

/**
 * 功能概述：<br>
 * 开发者用户账户控制层
 */
@Controller
public class ReportController extends AbstractControllerParent {

	@Resource
	private DeveloperReportService developerReportService;

	@Resource
	private ApplicationService applicationService;

	/**
	 * <p>
	 * Title: reportActualTime
	 * </p>
	 * <p>
	 * Description:开发者实时数据
	 * </p>
	 * 
	 * @param request
	 * @param bean
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/reportActualTime.action")
	public String reportActualTime(HttpServletRequest request, DevReportbean bean, Map<String, Object> model) throws Exception {
		if (bean == null) {
			bean = new DevReportbean();
		}
		if (StringUtils.isEmpty(bean.getEnd_date())) {
			bean.setEnd_date(DateUtil.format(DateUtil.getNow()));
		}
		Long developerId = getUserDeveloper().getId();
		bean.setDev_id(developerId);
		IPageInfo pageInfo = new SetPage(request);
		List<Application> appList = applicationService.findByDeveloper(developerId);
		List<DevActualTimeReportVo> list = developerReportService.findDevActualTime(bean, pageInfo);
		List<DevActualTimeReportVo> all = developerReportService.findDevActualAll(bean);

		Integer activate[] = new Integer[all.size()];
		Integer click[] = new Integer[all.size()];
		String static_date[] = new String[all.size()];
		for (int i = 0; i < all.size(); i++) {
			DevActualTimeReportVo reportVo = all.get(i);
			activate[i] = reportVo.getActivate();
			click[i] = reportVo.getClick();
			static_date[i] = reportVo.getStatic_hour();
		}
		String jsonactivate = JacksonMapper.objectToJsonString(activate);
		String jsonclick = JacksonMapper.objectToJsonString(click);
		String jsonstatic_date = JacksonMapper.objectToJsonString(static_date);
		model.put("pageInfo", PageUtil.fenyeNew(pageInfo));
		model.put("appList", appList);
		model.put("list", list);
		model.put("all", all);
		model.put("bean", bean);
		model.put("activate", jsonactivate);
		model.put("click", jsonclick);
		model.put("static_date", jsonstatic_date);
		return "developer/realtime_statistics";
	}

	/**
	 * <p>
	 * Title: reportHistorical
	 * </p>
	 * <p>
	 * Description:开发者历史数据统计
	 * </p>
	 * 
	 * @param request
	 * @param bean
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/reportHistorical.action")
	public String reportHistorical(HttpServletRequest request, DevReportbean bean, Map<String, Object> model) throws Exception {
		Date date = new Date();
		if(bean.getTime_q() == null) {
			bean.setTime_q(new Long(7));
		}
		if(bean.getTime_q() == 7) {
			bean.setStart_date(DateUtil.formatDate(DateUtil.addDays(date, -7), "yyyy-MM-dd"));
			bean.setEnd_date(DateUtil.formatDate(DateUtil.addDays(date, -1), "yyyy-MM-dd"));
		} else if (bean.getTime_q() == 30) {
			bean.setStart_date(DateUtil.formatDate(DateUtil.addDays(date, -30), "yyyy-MM-dd"));
			bean.setEnd_date(DateUtil.formatDate(DateUtil.addDays(date, -1), "yyyy-MM-dd"));
		}
		Long developerId = getUserDeveloper().getId();
		bean.setDev_id(developerId);
		IPageInfo pageInfo = new SetPage(request);
		List<Application> appList = applicationService.findByDeveloper(developerId);
		List<DevActualTimeReportVo> list = developerReportService.findDevHistorical(bean, pageInfo);
		List<DevActualTimeReportVo> all = developerReportService.findDevHistoricalAll(bean);
		Integer activate[] = new Integer[all.size()];
		Integer click[] = new Integer[all.size()];
		String static_date[] = new String[all.size()];
		for (int i = 0; i < all.size(); i++) {
			DevActualTimeReportVo reportVo = all.get(i);
			activate[i] = reportVo.getActivate();
			click[i] = reportVo.getClick();
			static_date[i] = reportVo.getStatic_hour();
		}
		String jsonactivate = JacksonMapper.objectToJsonString(activate);
		String jsonclick = JacksonMapper.objectToJsonString(click);
		String jsonstatic_date = JacksonMapper.objectToJsonString(static_date);
		model.put("pageInfo", PageUtil.fenyeNew(pageInfo));
		model.put("appList", appList);
		model.put("list", list);
		model.put("all", all);
		model.put("bean", bean);
		model.put("activate", jsonactivate);
		model.put("click", jsonclick);
		model.put("static_date", jsonstatic_date);
		return "developer/history_statistics";
	}
}
