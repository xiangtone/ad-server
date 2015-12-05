package cn.adwalker.ad.admin.finance.controller;

import java.io.IOException;
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
import cn.adwalker.ad.admin.finance.bean.AchievementReportbean;
import cn.adwalker.ad.admin.finance.form.CampaignConfirmFrom;
import cn.adwalker.ad.admin.finance.service.IAchievementReportService;
import cn.adwalker.ad.admin.finance.vo.AchievementReportSumVo;
import cn.adwalker.ad.admin.finance.vo.CampaignConfirmVo;
import cn.adwalker.ad.admin.finance.vo.IncomeDetailVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.ExportUtils;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultErrorVo;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;

/**
 * <p>
 * Title: IncomeAuditController
 * </p>
 * <p>
 * Description:财务管理》收入明细表
 * </p>
 * <p>
 * Company: emar
 * </p>
 * 
 * @author 
 * @date 2014-03-12
 */
@Controller
public class IncomeAuditController extends AbstractControllerParent {

	@Resource
	private IAchievementReportService achievementReportService;
	/** 日志记录器 */
	Logger logger = Logger.getLogger(IncomeAuditController.class);
	
	/** 管理员实体 */
	private SysUserVo manageUser;

	/**
	* <p>Title: CampaignConfirmList</p>
	* <p>Description: 收入明细表</p>
	* @param request
	* @param response
	* @param bean
	* @return
	* @author cuidd
	* @date 2014年8月13日
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/finance/incomeAuditList.do")
	public String CampaignConfirmList(HttpServletRequest request,
			HttpServletResponse response,HttpSession session, AchievementReportbean bean) {
		List<IncomeDetailVo> list = null;
		AchievementReportSumVo sum = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			manageUser = (SysUserVo) session.getAttribute("manageUser");
			list = this.achievementReportService.findByPage(bean,manageUser, pageInfo);
			// 求和
			sum = achievementReportService.findSum(bean);
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
		return "finance/income_audit_list";
	}

	/***
	* <p>Title: achievementReportListDown</p>
	* <p>Description:导出广告主收入</p>
	* @param request
	* @param response
	* @param bean
	* @return
	* @author cuidd
	* @date 2014年8月13日
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/finance/incomeAuditListDown.do")
	public String achievementReportListDown(HttpServletRequest request,
			HttpServletResponse response, AchievementReportbean bean) {
		List<IncomeDetailVo> list = null;
		AchievementReportSumVo sum = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = this.achievementReportService.findAll(bean);
			// 求和
			//sum = achievementReportService.findSum(bean);
			boolean[] isMoney = new boolean[] { false, false, false, false,
					false, false, false, false, true, false, false, false, false, false, false,
					false, false, false, false, true, false, false,false,  
					true, false };
			ExportUtils.exportExcel(
					list,
					"收入明细表",
					"收入明细表",
					new String[] {"明细单号","广告主名称","活动ID","活动名称","日期", "系统", "结算方式","结算单价", "预确认数", "预确认金额", "销售人员",
							"结算状态", "结算单号"},
					new String[] {"id","company_name","campaign_id","campaign_name","static_date",
							"os", "charge_type","price", "forecast_amount",
							"income_money", "name","status_name",
							"balance_id"},
					new String[] { "汇总", "--", "--", "--","--", "--", "--","--","--",
							"--", "--", "--", "--" }, null, null, isMoney, null, response);
			
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("list", list);
			request.setAttribute("bean", bean);
			//request.setAttribute("sum", sum);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());

		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "finance/achievement_report_list";
	}
	/**
	* <p>Title: submitIncomeNum</p>
	* <p>Description:插入汇总数据</p>
	* @param request
	* @param Response
	* @param ids
	* @return
	* @throws IOException
	* @author cuidd
	* @date 2014年11月14日
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!submitIncomeNum.do")
	public String submitIncomeNum(HttpServletRequest request,
			HttpServletResponse Response, String ids,HttpSession session) throws IOException {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		ResultVo vo = null;
		try {
			Long income_id=this.achievementReportService.submitIncomeNum(ids, manageUser);
			vo = new ResultSuccessVo(income_id);
		} catch (Exception e) {
			e.printStackTrace();
			vo = new ResultErrorVo("系统错误");
		}
		OutputHelper.outPut(Response, JacksonMapper.objectToJsonString(vo));
		return null;
	}
	
	/**
	* <p>Title: entering</p>
	* <p>Description:申请显示页</p>
	* @param session
	* @param model
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014年11月14日
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!enteringDayIncome.do")
	public String entering(HttpServletRequest request,
			HttpServletResponse Response, HttpSession session, Map<String, Object> model,Long income_id)
			throws Exception {
		CampaignConfirmVo plist=new CampaignConfirmVo();
		try {
			plist=achievementReportService.getById(income_id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("vo", plist);
		return "finance/entering_day_income";
	}
	
		/**
		* <p>Title: saveCampaignIncomeDay</p>
		* <p>Description:TODO</p>
		* @param request
		* @param response
		* @param model
		* @param session
		* @param from
		* @return
		* @throws Exception
		* @author cuidd
		* @date 2014年12月16日
		* @return String
		* @version 1.0
		 */
		@RequestMapping("/manage!saveCampaignIncomeDay.do")
		public String saveCampaignIncomeDay(HttpServletRequest request,
				HttpServletResponse response, Map<String, Object> model,
				HttpSession session, CampaignConfirmFrom from) throws Exception {
			manageUser = (SysUserVo) session.getAttribute("manageUser");
			ResultVo vo = null;
			try {
				achievementReportService.updateStatus(from);
			} catch (Exception e) {
				e.printStackTrace();
			}
			vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
			return null;
	
		}
	
	/**
	* <p>Title: deleteCampaignIncomeDay</p>
	* <p>Description:取消申请</p>
	* @param request
	* @param response
	* @param model
	* @param session
	* @param id
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014年12月10日
	* @return String
	* @version 1.0
	 */
	
	@RequestMapping("/manage!deleteCampaignIncomeDay.do")
	public String deleteCampaignIncomeDay(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,
			HttpSession session, Long id) throws Exception {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		ResultVo vo = null;
		try {
			achievementReportService.deleteStatus(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		vo = new ResultSuccessVo();
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;

	}
}
