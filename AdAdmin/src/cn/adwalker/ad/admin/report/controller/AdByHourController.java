package cn.adwalker.ad.admin.report.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.report.bean.AdByHourQuery;
import cn.adwalker.ad.admin.report.service.IReportAdByHourService;
import cn.adwalker.ad.admin.report.vo.ReportAdByHour;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.DateUtil;
import cn.adwalker.core.util.ExportUtils;

/**
 * 
 * <p>
 * Title: AdByHourController
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-8-19
 */
@Controller
public class AdByHourController extends AbstractControllerParent {
	private static final Logger logger = LoggerFactory
			.getLogger(AdByHourController.class);

	@Resource
	private IReportAdByHourService service;

	/**
	 * 应用列表
	 * 
	 * @return
	 */
	@RequestMapping("manage!ReportAdByHour.do")
	public String list(HttpSession session, HttpServletRequest request,
			AdByHourQuery bean, Map<String, Object> model) {
		try {
			if (bean == null) {
				bean = new AdByHourQuery();
			}

			if (StringUtils.isEmpty(bean.getBeginTime())) {
				bean.setBeginTime(DateUtil.formatDate(new Date()));
			}
			if (StringUtils.isEmpty(bean.getEndTime())) {
				bean.setEndTime(DateUtil.formatDate(new Date()));
			}
//			if (Constant.isSeal(user.getRoleCode())) {
//				Long l = campaignSalesmanService.getSalesmanIdBySysUser(user
//						.getId());
//				bean.setSalesman_id(l);
//			}
			IPageInfo pageInfo = new SetPage(request, 20);
			List<ReportAdByHour> list = this.service.findByPage(bean, pageInfo);
			ReportAdByHour total = this.service.findTotal(bean);
			model.put("pageInfo", pageInfo);
			model.put("bean", bean);
			request.setAttribute("list", list);
			request.setAttribute("total", total);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return "report/reportAdByHour";

	}

	/**
	 * <p>
	 * Title: exportCSV
	 * </p>
	 * <p>
	 * Description:导出CSV
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @author lichuang
	 * @date 2013-5-30
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */
	@RequestMapping("/manage!exportAdByHourCSV.do")
	public void exportCSV(HttpSession session, HttpServletResponse response,
			AdByHourQuery bean) throws Exception {
		// SysUserVo user = (SysUserVo) session.getAttribute("manageUser");
		if (StringUtils.isEmpty(bean.getBeginTime())) {
			bean.setBeginTime(DateUtil.formatDate(new Date()));
		}
		if (StringUtils.isEmpty(bean.getEndTime())) {
			bean.setEndTime(DateUtil.formatDate(new Date()));
		}
		// bean.setArea_type(user.getArea_type());
		String fileName = "实时统计----广告统计报表.xls";
		String workbookName = "广告按小时统计";
		String title[] = { "日期", "	小时", "广告主ID", "广告ID", "广告名称", "广告样式",
				"平台类型", "广告展示", "广告点击", "广告下载", "广告激活", "	费用支出(元)", "点击转化率",
				"下载转化率", "激活转化率" };
		List<Object> list = service.findAll(bean);
		String clumes[] = { "static_date", "static_hour", "adv_id", "id",
				"placement_name", "fname", "os", "adpv", "click", "download",
				"activate", "cost", "ctrc", "ctrd", "ctra" };
		ExportUtils.exportXlsFile(response, title, null, list, clumes,
				fileName, workbookName);
	}
}
