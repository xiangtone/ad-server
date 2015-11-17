package cn.adwalker.ad.admin.finance.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.DatabaseExportUtil;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultErrorVo;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.finance.bean.DevPunishDetailbean;
import cn.adwalker.ad.admin.finance.form.FinancePunishDetail;
import cn.adwalker.ad.admin.finance.service.IFinancePunishDetailService;
import cn.adwalker.ad.admin.finance.vo.DevListAwardDetailVo;
import cn.adwalker.ad.admin.finance.vo.FinanceIncomeSumVo;

@Controller
public class FinancePunishDetailController {
	@Resource
	private IFinancePunishDetailService financePunishDetailService;

	/** 日志记录器 */
	Logger logger = Logger.getLogger(FinancePunishDetailController.class);
	
	
	/**
	* <p>Title: addPunish</p>
	* <p>Description:跳转扣费添加页面</p>
	* @param request
	* @return
	* @author lichuang
	* @date 2013-7-9
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!addPunish.do")
	public String addPunish(HttpServletRequest request) {

		return "manage/finance/finance_punish_add";
	}
	/**
	* <p>Title: listAwaedDetail</p>
	* <p>Description:开发者扣费List</p>
	* @param request
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-7-4
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!devPunishDetail.do")
	public String listPunishDetail(HttpServletRequest request,DevPunishDetailbean bean) throws Exception {
		List<DevListAwardDetailVo> devList = null;
		FinanceIncomeSumVo summary = null;
		try {
			IPageInfo pageInfo = new SetPage(request);
			devList = financePunishDetailService.findByList(bean, pageInfo);
			summary = financePunishDetailService.findBySum(bean);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("voList", devList);
			request.setAttribute("summary", summary);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manage/finance/finance_punish_list";
	}

	/**
	* <p>Title: listAwaedDetailUpdate</p>
	* <p>Description:开发者扣费明细录入</p>
	* @param request
	* @return
	* @author lichuang
	* @date 2013-7-4
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!updateDevPunishDetail.do")
	public String listAwaedDetailUpdate(HttpServletRequest request,HttpServletResponse response,FinancePunishDetail from)throws Exception {
		ResultVo resultVo = null;
		try {
			// 当前登录用户
			SysUserVo currenUser = (SysUserVo) request.getSession()
					.getAttribute("manageUser");
			this.financePunishDetailService.updateDevPunish(from,currenUser);
			resultVo = new ResultSuccessVo();
		} catch (Exception e) {
			e.printStackTrace();
			resultVo = new ResultErrorVo("操作失败请重试");
		}
		OutputHelper.outPut(response,
				JacksonMapper.objectToJsonString(resultVo));
		return null;
	}

	/**
	* <p>Title: reportListDownloadExcel</p>
	* <p>Description:导出扣款的报表</p>
	* @param request
	* @param response
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-7-4
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!excelDevPunishDetail.do")
	public String reportListDownloadExcel(HttpServletRequest request,
			HttpServletResponse response,DevPunishDetailbean bean) throws Exception {
		List<DevListAwardDetailVo> devList = null;
		FinanceIncomeSumVo summary = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			devList = financePunishDetailService.findByall(bean);
			summary = financePunishDetailService.findBySum(bean);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 导出报表
		DatabaseExportUtil
				.exportExcel(devList, "开发者扣费明细", "开发者扣费明细", new String[] {
						"开发者ID", "开发者帐号", "扣款原因", "金额(元)", "操作时间", "操作人" },
						new String[] { "dev_id", "dev_account",
								"activity_name", "award_money", "stat_date",
								"finance_name", }, new String[] { "汇总", "--",
								"--", String.valueOf(summary.getSum_sumMoney()), "--", "--" },
						response);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("bean", bean);
		request.setAttribute("voList", devList);
		request.setAttribute("summary", summary);
		return "manage/finance/finance_punish_list";
	}

}
