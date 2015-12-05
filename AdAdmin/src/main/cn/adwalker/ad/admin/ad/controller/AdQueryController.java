package cn.adwalker.ad.admin.ad.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.ad.bean.AdOffLineLogQueryBean;
import cn.adwalker.ad.admin.ad.bean.AdQueryBean;
import cn.adwalker.ad.admin.ad.service.IAdQueryService;
import cn.adwalker.ad.admin.ad.vo.AdOffLineLogVo;
import cn.adwalker.ad.admin.ad.vo.AdQueryVo;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.DateUtil;

/**
 * <p>
 * Title: MediaAdController
 * </p>
 * <p>
 * Description:方案调整
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author zouguibao
 * @date 2013-5-15
 */
@Controller
public class AdQueryController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(AdQueryController.class);

	/** 广告方案调整相关业务 */
	@Resource
	private IAdQueryService adQueryService;

	/**
	 * <p>
	 * Title: listAdAjustment
	 * </p>
	 * <p>
	 * Description:方案调整
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * @date 2013-5-15
	 * @version 1.0
	 * @author zouguibao
	 */
	@RequestMapping("/manage!adQuery.do")
	public String listAdAjustment(HttpServletRequest request,
			HttpServletResponse response, AdQueryBean bean) {
		List<AdQueryVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = this.adQueryService.findByPage(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.debug("获取方案调整列表异常！");
			e.printStackTrace();
		}
		return "ad/query/ad_query_list";
	}

	/**
	 * 
	 * <p>
	 * Title: listAdOffLine
	 * </p>
	 * <p>
	 * Description:广告下线日志
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @author cuidd
	 * @date 2013-10-30
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!adOffLineLog.do")
	public String listAdOffLine(HttpServletRequest request,
			HttpServletResponse response, AdOffLineLogQueryBean bean) {
		List<AdOffLineLogVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			if (bean == null) {
				bean = new AdOffLineLogQueryBean();
			}
			if (StringUtils.isEmpty(bean.getStatic_date())) {
				bean.setStatic_date(DateUtil.formatDate(new Date()));
			}
			list = this.adQueryService.findOffLineLogByPage(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.debug("获取方案调整列表异常！");
			e.printStackTrace();
		}
		return "ad/query/ad_off_line_list";
	}
}
