package cn.adwalker.ad.admin.finance.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultErrorVo;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.finance.bean.CampaignConfirmbean;
import cn.adwalker.ad.admin.finance.service.ICampaignConfirmService;
import cn.adwalker.ad.admin.finance.vo.CampaignConfirmSumVo;
import cn.adwalker.ad.admin.finance.vo.CampaignConfirmVo;

/**
 * <p>
 * Title: CampaignConfirmController
 * </p>
 * <p>
 * Description:确认数录入
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-8
 */
@Controller
public class CampaignConfirmController extends AbstractControllerParent {

	@Resource(name = "finance.campaignConfirmService")
	private ICampaignConfirmService campaignConfirmService;

	/** 日志记录器 */
	Logger logger = Logger.getLogger(CampaignConfirmController.class);

	
	/**
	 * <p>
	 * Title: CampaignConfirmList
	 * </p>
	 * <p>
	 * Description:确认数录入List
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-6-8
	 * @return String
	 * @version 1.0
	 */

	@RequestMapping("finance/campaignConfirmList.do")
	public String CampaignConfirmList(HttpServletRequest request,
			HttpServletResponse response, CampaignConfirmbean bean) {
		List<CampaignConfirmVo> list = null;
		CampaignConfirmSumVo sum = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = this.campaignConfirmService.findByPage(bean, pageInfo);
			// 求和
			sum = campaignConfirmService.findSum(bean);

			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("list", list);
			request.setAttribute("bean", bean);
			request.setAttribute("sum", sum);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());

		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "finance/campaign_confirm_list";
	}

	/**
	 * <p>
	 * Title: campaignConfirmpublish
	 * </p>
	 * <p>
	 * Description:发布
	 * </p>
	 * 
	 * @param request
	 * @param Response
	 * @param Id
	 * @return
	 * @throws IOException
	 * @author lichuang
	 * @date 2013-6-8
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/finance/campaignConfirmpublish.do")
	public String campaignConfirmpublish(HttpServletRequest request,
			HttpServletResponse response, Long Id) throws IOException {
		ResultVo vo = null;
		try {
			vo = new ResultSuccessVo();
			this.campaignConfirmService.updateStatus(Id);
		} catch (Exception e) {
			e.printStackTrace();
			vo = new ResultErrorVo("系统错误");

		}
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * <p>
	 * Title: editEntering
	 * </p>
	 * <p>
	 * Description::查看录入信息
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @author lichuang
	 * @date 2013-10-11
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/finance/campaignBlanceAcount.do")
	public String editEntering(HttpServletRequest request,
			HttpServletResponse response, Long id) {
		CampaignConfirmVo cVo = null;
		try {
			cVo = this.campaignConfirmService.getEntering(id);
			request.setAttribute("vo", cVo);
		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "finance/campaign_blance_acount";
	}
}
