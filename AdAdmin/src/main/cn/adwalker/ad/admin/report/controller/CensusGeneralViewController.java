package cn.adwalker.ad.admin.report.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.report.bean.CensusGeneralViewBean;
import cn.adwalker.ad.admin.report.service.ICensusGeneralViewService;
import cn.adwalker.ad.admin.report.vo.AllCensusGeneralViewVo;
import cn.adwalker.ad.admin.report.vo.CensusGeneralViewAdvVo;
import cn.adwalker.ad.admin.report.vo.CensusGeneralViewAppVo;
import cn.adwalker.ad.admin.report.vo.ChannelCensusGeneralViewVo;
import cn.adwalker.ad.admin.report.vo.PlatformCensusGeneralViewVo;
import cn.adwalker.ad.admin.report.vo.ReportAndroidIosAllOutincomeVo;
import cn.adwalker.ad.admin.report.vo.ReportAndroidIosAllVo;
import cn.adwalker.ad.admin.report.vo.ReportIncomeExpensesVo;
import cn.adwalker.core.util.DateUtil;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.lang.ObjectUtils;

/**
* <p>Title: CensusGeneralViewController</p>
* <p>Description:统计概览</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-5-18
 */
@Controller
public class CensusGeneralViewController extends AbstractControllerParent {

	@Resource
	private ICensusGeneralViewService censusGeneralView;
	/** 日志记录器 */
	Logger logger = Logger.getLogger(CensusGeneralViewController.class);
	
	
	@RequestMapping("/manage!staticView.do")
	public String index(HttpServletRequest request,
			HttpServletResponse response, CensusGeneralViewBean bean) {
		return "report/general_view";
	}
	
	
	
	
	
	/**
	* <p>Title: list</p>
	* <p>Description:统计概览list</p>
	* @param request
	* @param response
	* @param bean
	* @return
	* @author lichuang
	* @date 2013-5-18
	* @return String
	* @version 1.0
	 */

	@RequestMapping("/manage!censusGeneralView.do")
	public String list(HttpServletRequest request,
			HttpServletResponse response, CensusGeneralViewBean bean) {
		PlatformCensusGeneralViewVo listPlatform = null;
		List<ChannelCensusGeneralViewVo> listChannel = null;
		AllCensusGeneralViewVo listAll = null;
		try {
			if (bean.getWall_response_category()==0) {
				bean.setStart_stat_date(DateUtil.formatDate(DateUtil.addDays(new Date() , -30), "yyyy-MM-dd"));
				bean.setEnd_stat_date(DateUtil.getYesterday());
			}
//			if (ObjectUtils.isNotEmpty(bean.getWeek_stat_date())) {
//				String sart=DateUtil.formatDate(DateUtil.getMonday(DateUtil.parseDate(bean.getWeek_stat_date(),"yyyy-MM-dd")),"yyyy-MM-dd");
//				String end=DateUtil.formatDate(DateUtil.getSunday(DateUtil.parseDate(bean.getWeek_stat_date(),"yyyy-MM-dd")),"yyyy-MM-dd");
//				bean.setStart_date(sart);
//				bean.setEnd_date(end);
//			}
			//平台总数据表
		//	listPlatform = this.censusGeneralView.findGeneralViewPlatformList(bean);
//			//渠道总数据表
			listChannel = this.censusGeneralView.findGeneralViewChannelList(bean);
//			//总数据表
		//	listAll = this.censusGeneralView.findAllCensusGeneralViewList(bean);
			
//			if (ObjectUtils.isNotEmpty(bean.getMonth_stat_date())) {
//				String sart=DateUtil.formatDate(DateUtil.getMOnStart(DateUtil.parseDate(bean.getMonth_stat_date(),"yyyy-MM")),"yyyy-MM-dd");
//				String end=DateUtil.formatDate(DateUtil.getMOnEnd(DateUtil.parseDate(bean.getMonth_stat_date(),"yyyy-MM")),"yyyy-MM-dd");
//				bean.setStart_date(sart);
//				bean.setEnd_date(end);
//			}
			request.setAttribute("l", listAll);
			request.setAttribute("p", listPlatform);
			request.setAttribute("listChannel", listChannel);
			request.setAttribute("bean", bean);
		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "manage/report/general_view_list";
	}
	
	/**
	* <p>Title: viewTopAppAdvlist</p>
	* <p>Description:广告主/媒体：top10</p>
	* @param request
	* @param response
	* @param bean
	* @return
	* @author lichuang
	* @date 2013-6-5
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!viewTopAppAdv.do")
	public String viewTopAppAdvlist(HttpServletRequest request,
			HttpServletResponse response, CensusGeneralViewBean bean) {
		List<CensusGeneralViewAdvVo> listAdv = null;
		List<CensusGeneralViewAppVo> listApp = null;
		try {
			if (ObjectUtils.isEmpty(bean.getStart_stat_date())&&ObjectUtils.isEmpty(bean.getEnd_stat_date())&&ObjectUtils.isEmpty(bean.getMonth_stat_date())&&ObjectUtils.isEmpty(bean.getWeek_stat_date())) {
				bean.setStart_stat_date(DateUtil.getYesterday());
				bean.setEnd_stat_date(DateUtil.getYesterday());
			}
			if (ObjectUtils.isNotEmpty(bean.getWeek_stat_date())) {
				String sart=DateUtil.formatDate(DateUtil.getMonday(DateUtil.parseDate(bean.getWeek_stat_date(),"yyyy-MM-dd")),"yyyy-MM-dd");
				String end=DateUtil.formatDate(DateUtil.getSunday(DateUtil.parseDate(bean.getWeek_stat_date(),"yyyy-MM-dd")),"yyyy-MM-dd");
				bean.setStart_date(sart);
				bean.setEnd_date(end);
			}
			//top10广告主
			listAdv = this.censusGeneralView.findGeneralViewAdvList(bean);
			//top10媒体
			listApp = this.censusGeneralView.findGeneralViewAppList(bean);
			request.setAttribute("listAdv", listAdv);
			request.setAttribute("listApp", listApp);
			request.setAttribute("bean", bean);
		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "manage/report/view_top_app_adv_list";
	}
	/**
	* <p>Title: flowlist</p>
	* <p>Description:流量趋势图</p>
	* @param request
	* @param response
	* @param bean
	* @return
	* @author lichuang
	* @date 2013-6-19
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!yjfFlow.do")
	public String flowlist(HttpServletRequest request,
			HttpServletResponse response, CensusGeneralViewBean bean) {
		List<ReportAndroidIosAllVo> list = null;
		List<ReportAndroidIosAllOutincomeVo> listOut = null;
		try {
			if (ObjectUtils.isEmpty(bean.getStart_stat_date())&&ObjectUtils.isEmpty(bean.getEnd_stat_date())&&ObjectUtils.isEmpty(bean.getMonth_stat_date())&&ObjectUtils.isEmpty(bean.getWeek_stat_date())) {
				bean.setStart_stat_date(DateUtil.getYesterday());
				bean.setEnd_stat_date(DateUtil.getYesterday());
			}
			if (ObjectUtils.isNotEmpty(bean.getWeek_stat_date())) {
				String sart=DateUtil.formatDate(DateUtil.getMonday(DateUtil.parseDate(bean.getWeek_stat_date(),"yyyy-MM-dd")),"yyyy-MM-dd");
				String end=DateUtil.formatDate(DateUtil.getSunday(DateUtil.parseDate(bean.getWeek_stat_date(),"yyyy-MM-dd")),"yyyy-MM-dd");
				bean.setStart_date(sart);
				bean.setEnd_date(end);
			}
			list = this.censusGeneralView.flowlist(bean);	
			listOut = this.censusGeneralView.flowlistOuticome(bean);	
			BigDecimal pv[] = new BigDecimal[list.size()];
			BigDecimal click[] = new BigDecimal[list.size()];
			BigDecimal income_amount[] = new BigDecimal[listOut.size()];
			String static_date[] = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				ReportAndroidIosAllVo incomeCountVo = list.get(i);
				pv[i] = incomeCountVo.getPv();
				click[i] = incomeCountVo.getClick();
				static_date[i] = incomeCountVo.getStatic_date();
			}
			for (int j = 0; j < listOut.size(); j++) {
				ReportAndroidIosAllOutincomeVo incomeCount = listOut.get(j);		
				income_amount[j] = incomeCount.getSum_income_amount();
			}
			String jsonpv = JacksonMapper.objectToJsonString(pv);
			String jsonclick = JacksonMapper.objectToJsonString(click);
			String jsonincome_amount = JacksonMapper.objectToJsonString(income_amount);
			String jsonstatic_date = JacksonMapper.objectToJsonString(static_date);
			request.setAttribute("jsonpv", jsonpv);
			request.setAttribute("jsonclick", jsonclick);
			request.setAttribute("jsonincome_amount", jsonincome_amount);
			request.setAttribute("jsonstatic_date", jsonstatic_date);
			request.setAttribute("bean", bean);
		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "manage/report/flow_list";
	}
	/**
	* <p>Title: IncomeExpenses</p>
	* <p>Description:收入/支出</p>
	* @param request
	* @param response
	* @param bean
	* @return
	* @author lichuang
	* @date 2013-6-20
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!reportIncomeExpenses.do")
	public String IncomeExpenses(HttpServletRequest request,
			HttpServletResponse response, CensusGeneralViewBean bean) {
		List<ReportIncomeExpensesVo> list = null;
		try {
			if (ObjectUtils.isEmpty(bean.getStart_stat_date())&&ObjectUtils.isEmpty(bean.getEnd_stat_date())&&ObjectUtils.isEmpty(bean.getMonth_stat_date())&&ObjectUtils.isEmpty(bean.getWeek_stat_date())) {
				bean.setStart_stat_date(DateUtil.getYesterday());
				bean.setEnd_stat_date(DateUtil.getYesterday());
			}
			if (ObjectUtils.isNotEmpty(bean.getWeek_stat_date())) {
				String sart=DateUtil.formatDate(DateUtil.getMonday(DateUtil.parseDate(bean.getWeek_stat_date(),"yyyy-MM-dd")),"yyyy-MM-dd");
				String end=DateUtil.formatDate(DateUtil.getSunday(DateUtil.parseDate(bean.getWeek_stat_date(),"yyyy-MM-dd")),"yyyy-MM-dd");
				bean.setStart_date(sart);
				bean.setEnd_date(end);
			}
			list = this.censusGeneralView.IncomeExpenseslist(bean);	
			double cost[] = new double[list.size()];
			double income_money[] = new double[list.size()];
			String static_date[] = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				ReportIncomeExpensesVo incomeCountVo = list.get(i);
				cost[i] = incomeCountVo.getCost();
				income_money[i] = incomeCountVo.getIncome_money();
				static_date[i] = incomeCountVo.getStatic_date();

			}
			String jsoncost = JacksonMapper.objectToJsonString(cost);
			String jsonincome_money = JacksonMapper.objectToJsonString(income_money);
			String jsonstatic_date = JacksonMapper.objectToJsonString(static_date);
			request.setAttribute("jsoncost", jsoncost);
			request.setAttribute("jsonincome_money", jsonincome_money);
			request.setAttribute("jsonstatic_date", jsonstatic_date);
			request.setAttribute("bean", bean);
		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "manage/report/income_expenses";
	}
	
}
