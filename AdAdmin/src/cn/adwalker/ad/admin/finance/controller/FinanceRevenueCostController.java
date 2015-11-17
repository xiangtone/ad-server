package cn.adwalker.ad.admin.finance.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.finance.bean.FinanceRevenueCostYJFbean;
import cn.adwalker.ad.admin.finance.bean.FinanceRevenueCostbean;
import cn.adwalker.ad.admin.finance.service.IFinanceRevenueCostService;
import cn.adwalker.ad.admin.finance.vo.FinanceRevenueCostVo;
import cn.adwalker.ad.admin.finance.vo.FinanceRevenueCostYJFVo;
import cn.adwalker.ad.admin.finance.vo.RevenueCostVo;
import cn.adwalker.ad.admin.finance.vo.RevenueCostYJFVo;

/**
* <p>Title: FinanceRevenueCostController</p>
* <p>Description:确认收入成本</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-6-7
 */
@Controller
public class FinanceRevenueCostController extends AbstractControllerParent {

	@Resource
	private IFinanceRevenueCostService financeRevenueCost;
	
	/**
	* <p>Title: financeRevenueCostList</p>
	* <p>Description:确认收入成本List</p>
	* @param session
	* @param request
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-6-7
	* @return String
	* @version 1.0
	 */
	@RequestMapping("manage!financeRevenueCostList.do")
	public String financeRevenueCostList(HttpSession session,
			HttpServletRequest request, FinanceRevenueCostbean bean)
			throws Exception {
		List<FinanceRevenueCostVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		RevenueCostVo sumresult=null;
		try {
			// 导出数据list
			list = this.financeRevenueCost.findByCost(bean,pageInfo);
			// 求和
			sumresult = this.financeRevenueCost
					.getRevenueCostSum(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("advList", list);
		request.setAttribute("sum", sumresult);
		request.setAttribute("bean", bean);
		request.setAttribute("page", pageInfo.getPageSize());
		return "manage/finance/finance_revenue_cost_list";
	}

	/**
	* <p>Title: financeCostList</p>
	* <p>Description:确认收入成本(平台)List</p>
	* @param session
	* @param request
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-7-3
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!financeRevenueCostYJFList.do")
	public String financeCostList(HttpSession session,
			HttpServletRequest request, FinanceRevenueCostYJFbean bean)
			throws Exception {
		List<FinanceRevenueCostYJFVo> advList = null;
		RevenueCostYJFVo sumresult=null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			// 导出数据list
			advList = this.financeRevenueCost.findByList(bean,pageInfo);
			// 求和
			sumresult = this.financeRevenueCost
					.getRevenueCostYJFSum(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("advList", advList);
		request.setAttribute("bean", bean);
		request.setAttribute("sum", sumresult);
		request.setAttribute("pageRecord", pageInfo.getTotalRow());
		request.setAttribute("page", pageInfo.getPageSize());
		return "finance/finance_yjf_cost_list";
	}

	
}
