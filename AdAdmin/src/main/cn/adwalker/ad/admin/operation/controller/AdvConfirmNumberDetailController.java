package cn.adwalker.ad.admin.operation.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.ExportUtils;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.operation.bean.ConfirmNumDetailbean;
import cn.adwalker.ad.admin.operation.service.IConfirmationNumberService;
import cn.adwalker.ad.admin.operation.vo.AdvNumberSumVo;
import cn.adwalker.ad.admin.operation.vo.ConfirmationNumberDetailVo;

/**
 * <p>
 * Title: AdEffectAndroidController
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-5-23
 */

@Controller
public class AdvConfirmNumberDetailController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(AdvConfirmNumberDetailController.class);

	/** 广告业务 */
	@Resource
	private IConfirmationNumberService confirmationNumberService;

	/**
	 * <p>
	 * Title: listadvConfirmationNumber
	 * </p>
	 * <p>
	 * Description:广告主确认数录入List
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param bean 
	 * @return 
	 * @author lichuang
	 * @date 2013-5-23
	 * @return String 
	 * @version 1.0
	 */
	@RequestMapping("/manage!advConfirmationDetailList.do")
	public String listadvConfirmationNumber(HttpServletRequest request,HttpSession session, 
			HttpServletResponse response, ConfirmNumDetailbean bean) {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		List<ConfirmationNumberDetailVo> list = null;
		try {
			IPageInfo pageInfo = new SetPage(request);
			list = this.confirmationNumberService.findDetailByPage(bean,
					pageInfo);
			
			// 求和
			AdvNumberSumVo sumresult = this.confirmationNumberService
					.getNumberSum(bean);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("sum", sumresult);
			request.setAttribute("list", list);
		} catch (Exception e) {
			logger.debug("获取广告单价列表异常！");
			e.printStackTrace();
		}
		return "operation/confirm_number_detail_list";
	}

	/**
	 * <p>
	 * Title: DevConsumeDetailFindAllForReport
	 * </p>
	 * <p>
	 * Description:导出详细报表
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-7-22
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!advConfirmationDetailDownList.do")
	public String DevConsumeDetailFindAllForReport(HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			ConfirmNumDetailbean bean) {
		List<ConfirmationNumberDetailVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = this.confirmationNumberService.findAll(bean);
			// 求和
			AdvNumberSumVo sumresult = this.confirmationNumberService
					.getNumberSum(bean);

			// 导出报表
			boolean[] isMoney = new boolean[] { false, false, false, false,
					false, false, false, false, false, false, false, false,false,false, false,};
			ExportUtils.exportExcel(list, "收入明细统计", "收入消费明细统计", new String[] {"对账情况",
					"ID","编号", "渠道包ID", "文件名", "效果发生时间", "活动名称","媒体名称","渠道包号",
					"媒体类型", "确认数", "广告形式", "平台激活数", "广告主确认数"}, new String[] {"invoice_status_name",
					"id", "code", "package_id", "file_name","static_date","campaign_name", 
					"media_name", "remarks","media_ch_ph", "confirm_amount", "type_name", "sys_activate",
					"confirm_num" }, new String[] { "统计",
					"-","-", "-", "-", "-", "-", "-", "-","-", "" + sumresult.getSum_platform_amount(), "-", "-", "" + sumresult.getSum_amount() },
					null, null, isMoney, null, response);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
			request.setAttribute("sum", sumresult);
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "operation/confirm_number_detail_list";
	}
}
