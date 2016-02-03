package cn.adwalker.ad.web.channel.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.core.page.IPageInfo;
import cn.adwalker.ad.core.page.SetPage;
import cn.adwalker.ad.util.DateUtil;
import cn.adwalker.ad.util.page.PageUtil;
import cn.adwalker.ad.web.channel.bean.ChannelReportbean;
import cn.adwalker.ad.web.channel.service.ChannelReportService;
import cn.adwalker.ad.web.channel.vo.ChannelReportSumVo;
import cn.adwalker.ad.web.channel.vo.ChannelReportVo;
import cn.adwalker.ad.web.channel.vo.ChannelSdkReportVo;
import cn.adwalker.ad.web.common.controller.AbstractControllerParent;

/**
 * 渠道统计
 */

@Controller
public class ChannelReportController extends AbstractControllerParent {

	@Resource
	private ChannelReportService channelReportService;

	/**
	 * 前台渠道代理统计
	 * 
	 * @param request
	 * @param bean
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/reportChannelProxy.action")
	public String reportChannelProxy(HttpServletRequest request, ChannelReportbean bean, Map<String, Object> model) throws Exception {
		try {
			if (bean == null) {
				bean = new ChannelReportbean();
			}
			if (StringUtils.isEmpty(bean.getStart_date())) {
				bean.setStart_date(DateUtil.format(DateUtil.getMonday(new Date())));
			}
			if (StringUtils.isEmpty(bean.getEnd_date())) {
				bean.setEnd_date(DateUtil.format(DateUtil.getNow()));
			}
			IPageInfo pageInfo = new SetPage(request);
			List<ChannelReportVo> list = channelReportService.findProxyList(super.getUserChannels().getId(), bean, pageInfo);
			// 求和
			ChannelReportSumVo sumresult = this.channelReportService.getReportSum(super.getUserChannels().getId(), bean);
			model.put("pageInfo", PageUtil.fenye(pageInfo));
			model.put("list", list);
			model.put("s", sumresult);
			model.put("bean", bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "channel/channel_proxy_census";
	}

	/**
	 * SDK渠道数据统计
	 * 
	 * @param request
	 * @param bean
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/reportChannelSdk.action")
	public String reportChannelSdk(HttpServletRequest request, ChannelReportbean bean, Map<String, Object> model) throws Exception {
		try {
			if (bean == null) {
				bean = new ChannelReportbean();
			}
			if (StringUtils.isEmpty(bean.getStart_date())) {
				bean.setStart_date(DateUtil.format(DateUtil.getMonday(new Date())));
			}
			if (StringUtils.isEmpty(bean.getEnd_date())) {
				bean.setEnd_date(DateUtil.format(DateUtil.getNow()));
			}
			IPageInfo pageInfo = new SetPage(request);
			List<ChannelSdkReportVo> list = channelReportService.findSdkList(super.getUserChannels().getId(), bean, pageInfo);
			// 求和
			ChannelReportSumVo sumresult = this.channelReportService.getReportSumSdk(super.getUserChannels().getId(), bean);
			model.put("pageInfo", PageUtil.fenye(pageInfo));
			model.put("list", list);
			model.put("s", sumresult);
			model.put("bean", bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "channel/channel_sdk_census";
	}
	
}
