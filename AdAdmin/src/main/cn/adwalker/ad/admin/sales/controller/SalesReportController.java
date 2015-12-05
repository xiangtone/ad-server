package cn.adwalker.ad.admin.sales.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.sales.bean.DetailAndroidQueryBean;
import cn.adwalker.ad.admin.sales.bean.DetailIosQueryBean;
import cn.adwalker.ad.admin.sales.bean.SalesReportBean;
import cn.adwalker.ad.admin.sales.service.ISalesReportService;
import cn.adwalker.ad.admin.sales.vo.SalesReportDetailAndroidVo;
import cn.adwalker.ad.admin.sales.vo.SalesReportDetailIosVo;
import cn.adwalker.ad.admin.sales.vo.SalesReportVo;
import cn.adwalker.ad.config.Constant;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.model.ad.domain.CampaignSalesman;

/**
 * 
 * <p>
 * Title: SalesmanReportController
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2014-8-12
 */
@Controller
public class SalesReportController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(CampaignOnlineController.class);

	/** 广告活动相关业务 */
	@Resource
	private ISalesReportService salesReportService;

	@RequestMapping("/salesReport.do")
	public String list(HttpServletRequest request,
			HttpServletResponse response, SalesReportBean bean,
			HttpSession session) {
		List<SalesReportVo> list = null;
		SysUserVo user = (SysUserVo) session.getAttribute("manageUser");
		IPageInfo pageInfo = new SetPage(request);
		if (bean == null) {
			bean = new SalesReportBean();
		}
		try {
			if (Constant.isSeal(user.getRoleCode())) {
				Long l = salesReportService
						.getSalesmanIdBySysUser(user.getId());
				bean.setSales_id(l);
			}
			list = this.salesReportService.findByPage(bean, pageInfo);
			List<CampaignSalesman> salesmans = salesReportService
					.getSalesmanList();
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
			request.setAttribute("salesmans", salesmans);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.debug("获取在线活动列表异常！");
			e.printStackTrace();
		}
		return "sales/report/list";
	}

	@RequestMapping("/salesReportDetail_android.do")
	public String detail_android(HttpServletRequest request,
			HttpServletResponse response, DetailAndroidQueryBean bean) {
		try {
			IPageInfo pageInfo = new SetPage(request);
			List<SalesReportDetailAndroidVo> list = this.salesReportService
					.detail_android(bean, pageInfo);
			request.setAttribute("list", list);
			request.setAttribute("bean", bean);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.debug("获取在线活动列表异常！");
			e.printStackTrace();
		}
		return "sales/report/detail_android";
	}

	@RequestMapping("/salesReportDetail_ios.do")
	public String detail_ios(HttpServletRequest request,
			HttpServletResponse response, DetailIosQueryBean bean) {
		try {
			IPageInfo pageInfo = new SetPage(request);
			List<SalesReportDetailIosVo> list = this.salesReportService
					.detail_ios(bean, pageInfo);
			request.setAttribute("list", list);
			request.setAttribute("bean", bean);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.debug("获取在线活动列表异常！");
			e.printStackTrace();
		}
		return "sales/report/detail_ios";
	}

}
