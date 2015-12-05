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
import cn.adwalker.ad.admin.operation.bean.IosNumDetailbean;
import cn.adwalker.ad.admin.operation.service.IConfirmationIosNumberService;
import cn.adwalker.ad.admin.operation.vo.AdvNumberSumVo;
import cn.adwalker.ad.admin.operation.vo.IosNumDetailVo;

/**
* <p>Title: AdvIosNumDetailController</p>
* <p>Description:Ios效果明显</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-11-14
 */
@Controller
public class AdvIosNumDetailController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(AdvIosNumDetailController.class);

	/** 广告业务 */
	@Resource
	private IConfirmationIosNumberService confirmationIosNumberService;

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
	@RequestMapping("/manage!advIosNumDetailList.do")
	public String iosNumDetailList(HttpServletRequest request,HttpSession session, 
			HttpServletResponse response, IosNumDetailbean bean) {
	
		List<IosNumDetailVo> list = null;
		try {
			IPageInfo pageInfo = new SetPage(request);
			list = this.confirmationIosNumberService.findDetailByPage(bean,
					pageInfo);
			
			// 求和
			AdvNumberSumVo sumresult = this.confirmationIosNumberService
					.getNumberSum(bean);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("sum", sumresult);
			request.setAttribute("list", list);
		} catch (Exception e) {
			logger.debug("获取广告单价列表异常！");
			e.printStackTrace();
		}
		return "operation/ios_num_detail_list";
	}

	/**
	* <p>Title: IosNumDetailDownList</p>
	* <p>Description:导出详细报表</p>
	* @param session
	* @param request
	* @param response
	* @param bean
	* @return
	* @author lichuang
	* @date 2013-11-14
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/manage!advIosNumDetailDownList.do")
	public String IosNumDetailDownList(HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			IosNumDetailbean bean) {
		List<IosNumDetailVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = this.confirmationIosNumberService.findAll(bean);
			// 求和
		AdvNumberSumVo sumresult = this.confirmationIosNumberService
					.getNumberSum(bean);

			// 导出报表
			boolean[] isMoney = new boolean[] { false, false, false, false,
					false, false, false, false,false,false,};
			ExportUtils.exportExcel(list, "Ios效果明细统计", "Ios效果明细统计", new String[] {
					"ID","效果发生时间", "活动名称","媒体名称",
					"媒体类型", "确认数", "广告形式", "广告主确认数"}, new String[] {
					"id", "static_date","campaign_name", "media_name", "media_ch_ph", "confirm_amount", "type_name",
					"confirm_num" }, new String[] { "统计",
					"-","-", "-", "-", "" + sumresult.getSum_platform_amount(), "-", "" + sumresult.getSum_amount() },
					null, null, isMoney, null, response);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
			request.setAttribute("sum", sumresult);
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "operation/ios_num_detail_list";
	}
}
