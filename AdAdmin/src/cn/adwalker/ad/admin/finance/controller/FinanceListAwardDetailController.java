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
import cn.adwalker.core.util.ExportUtils;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultErrorVo;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.finance.bean.DevListAwardDetailbean;
import cn.adwalker.ad.admin.finance.form.FinanceAwardDetail;
import cn.adwalker.ad.admin.finance.service.IDevListAwardDetailService;
import cn.adwalker.ad.admin.finance.vo.DevListAwardDetailVo;
import cn.adwalker.ad.admin.finance.vo.FinanceIncomeSumVo;

@Controller
public class FinanceListAwardDetailController {
	@Resource
	private IDevListAwardDetailService devListAwardDetailService;
	/** 日志记录器 */
	Logger logger = Logger.getLogger(FinanceListAwardDetailController.class);

	/**
	 * <p>
	 * Title: listAwaedDetailUpdate添加奖励
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param request
	 * @param form
	 * @return
	 * @author lichuang
	 * @date 2013-7-2
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!updateDevListAwardDetail.do")
	public String listAwaedDetailUpdate(HttpServletRequest request,
			HttpServletResponse response, FinanceAwardDetail form)
			throws Exception {
		ResultVo resultVo = null;
		try {
			// 当前登录用户
			SysUserVo currenUser = (SysUserVo) request.getSession()
					.getAttribute("manageUser");
			this.devListAwardDetailService.updateDev(form, currenUser);
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
	 * <p>
	 * Title: listAwaedDetailSearch
	 * </p>
	 * <p>
	 * Description:网站主奖励明细查找
	 * </p>
	 * 
	 * @param request
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-7-2
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!searchDevListAwardDetail.do")
	public String listAwaedDetailSearch(HttpServletRequest request,
			DevListAwardDetailbean bean) {
		List<DevListAwardDetailVo> devList = null;
		FinanceIncomeSumVo summary = null;
		try {
			IPageInfo pageInfo = new SetPage(request);
			devList = devListAwardDetailService.findByCondition(bean, pageInfo);
			summary = devListAwardDetailService.findByConditionSum(bean);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("vo", bean);
			request.setAttribute("voList", devList);
			request.setAttribute("summary", summary);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manage/finance/finance_award_detail_list";
	}

	/**
	 * <p>
	 * Title: addAward
	 * </p>
	 * <p>
	 * Description:跳转添加页
	 * </p>
	 * 
	 * @param request
	 * @return
	 * @author lichuang
	 * @date 2013-7-2
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!addAward.do")
	public String addAward(HttpServletRequest request) {

		return "manage/finance/finance_award_add";
	}

	/**
	 * <p>
	 * Title: reportListDownloadExcel
	 * </p>
	 * <p>
	 * Description:广告主奖励明细导出Excel
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-7-9
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!awardDetailDownloadExcel.do")
	public String reportListDownloadExcel(HttpServletRequest request,
			HttpServletResponse response, DevListAwardDetailbean bean)
			throws Exception {
		List<DevListAwardDetailVo> devList = null;
		FinanceIncomeSumVo summary = null;
		try {
			devList = devListAwardDetailService.findByAll(bean);
			summary = devListAwardDetailService.findByConditionSum(bean);
		} catch (Exception e) {
		}
		boolean[] isMoney = new boolean[] { false, false, false, true, false,
				false, false, false };
		ExportUtils.exportExcel(devList, "网站主奖励明细", "网站主奖励明细", new String[] {
				"网站主ID", "网站主名称", "活动名称", "金额(元)", "活动周期", "结算日期", "结算人",
				"结算类型" }, new String[] { "dev_id", "dev_account",
				"activity_name", "award_money", "activityInterval",
				"stat_date", "finance_name", "finance_type" }, new String[] {
				"汇总", "--", "--", "" + summary.getSum_sumMoney(), "--", "--",
				"--", "--" }, null, null, isMoney, null, response);

		request.setAttribute("vo", bean);
		request.setAttribute("voList", devList);
		request.setAttribute("summary", summary);
		return "manage/finance/finance_award_detail_list";
	}
}
