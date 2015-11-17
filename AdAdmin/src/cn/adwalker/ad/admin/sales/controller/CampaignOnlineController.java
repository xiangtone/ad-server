package cn.adwalker.ad.admin.sales.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.ad.bean.CampaignOnlineSerach;
import cn.adwalker.ad.admin.ad.service.ICampaignService;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.sales.vo.CampaignOnlineVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;

/**
 * 
 * <p>
 * Title: CampaignOnlineController
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2014-3-27
 */
@Controller
public class CampaignOnlineController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(CampaignOnlineController.class);

	/** 广告活动相关业务 */
	@Resource
	private ICampaignService campaignService;

	/**
	 * Description:在线活动查询 </p>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * @date 2014-3-2
	 * @version 1.0
	 * @author mandy
	 */
	@RequestMapping("/campaignRunningList.do")
	public String list(HttpServletRequest request,
			HttpServletResponse response, CampaignOnlineSerach bean) {
		List<CampaignOnlineVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = this.campaignService
					.findCampaignOnlineByPage(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.debug("获取在线活动列表异常！");
			e.printStackTrace();
		}
		return "sales/campaignonline/list";
	}
}
