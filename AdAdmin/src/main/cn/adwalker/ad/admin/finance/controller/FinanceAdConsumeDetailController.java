package cn.adwalker.ad.admin.finance.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.ExportUtils;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.finance.bean.FinanceConsumebean;
import cn.adwalker.ad.admin.finance.service.IFinanceAdConsumeDetailService;
import cn.adwalker.ad.admin.finance.vo.AdvConsumeDetailVo;
import cn.adwalker.ad.admin.finance.vo.FinanceConsumeVo;

/**
 * 查询广告消费明细
 * <p>
 * Title: FinanceAdConsumeDetailController
 * </p>
 * <p>
 * Description:TODO  
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-1-15
 */
@Controller
public class FinanceAdConsumeDetailController extends AbstractControllerParent {
	@Resource
	private IFinanceAdConsumeDetailService financeAdConsumeDetailService;

	/**
	 * <p>
	 * Title: consumeDetailLsit
	 * </p>
	 * <p>
	 * Description:财务：收入明细列表
	 * </p>
	 * 
	 * @param request
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-5-8
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!AdvConsumeDetailList.do")
	public String consumeDetailLsit(HttpServletRequest request,
			FinanceConsumebean bean) {
		List<FinanceConsumeVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			// list数据
			list = this.financeAdConsumeDetailService.findConsumeList(bean,
					pageInfo);
			// 求和
			AdvConsumeDetailVo sumresult = this.financeAdConsumeDetailService
					.getAdvConsumeDetailListForReport(bean);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("entiy", list);
			request.setAttribute("sum", sumresult);
			request.setAttribute("bean", bean);
			request.setAttribute("page", pageInfo.getPageSize());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manage/finance/finance_consume_detail_list";
	}

	/**
	 * <p>
	 * Title: DevConsumeDetailFindAllForReport
	 * </p>
	 * <p>
	 * Description:导出广告消费明细
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-6-8
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!AdvConsumeDetailDownloadList.do")
	public String DevConsumeDetailFindAllForReport(HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			FinanceConsumebean bean) {
		List<FinanceConsumeVo> consumeLsit = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			// list数据
			consumeLsit = this.financeAdConsumeDetailService
					.findConsumeReportList(bean);
			// 求和
			AdvConsumeDetailVo sumresult = this.financeAdConsumeDetailService
					.getAdvConsumeDetailListForReport(bean);
			// 导出报表
			boolean[] isMoney = new boolean[] { false, false, false, false,
					false, false, false, false, false, true, false, true,
					false, false,false, false,};
			ExportUtils.exportExcel(consumeLsit, "收入明细统计", "收入消费明细统计",
					new String[] { "效果发生日期", "广告主ID", "广告主", "活动ID", "活动名称",
							"平台类型","媒体","媒体类型", "加价率", "单价(元)", "广告主确认数", "预计收入",
							"预确认收入(元)", "收入差额" }, new String[] {
							"static_date", "adv_id", "adv_email", "campaign_id",
							"campaign_name", "os","meia_name","type",
							"profit_rate", "in_price", "confirm_num",
							"forecast_income", "income_money", "balance",
							},
					new String[] { "统计", "-", "-", "-", "-", "-", "-","-", "-",
							"-", "" + sumresult.getSum_platform_amount(), "" + sumresult.getSum_forecast_money(),
							"" + sumresult.getSum_income_money(), "-"},
					null, null, isMoney, null, response);

			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("entiy", consumeLsit);
			request.setAttribute("bean", bean);
			request.setAttribute("sumresult", sumresult);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "manage/finance/finance_consume_detail_list";
	}
}
