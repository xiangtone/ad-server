package cn.adwalker.ad.admin.config.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.config.form.ChannelConfigSearchForm;
import cn.adwalker.ad.admin.config.service.IChannelConfigService;
import cn.adwalker.ad.admin.config.vo.ChannelConfigVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.model.channel.domain.Channel;
import cn.adwalker.model.config.domain.ChannelConfig;

@Controller
public class ChannelConfigController extends AbstractControllerParent {

	@Resource
	IChannelConfigService channelConfigService;

	@RequestMapping("/config!channelConfigList.do")
	public String reportList(HttpServletRequest request,
			ChannelConfigSearchForm bean, HttpSession session) throws Exception {
		IPageInfo pageInfo = new SetPage(request);
		List<ChannelConfigVo> configList = this.channelConfigService.getList(
				bean, pageInfo);
		request.setAttribute("list", configList);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("vo", bean);
		request.setAttribute("pageRecord", pageInfo.getTotalRow());
		request.setAttribute("page", pageInfo.getPageSize());
		return "operation/channelConfig/interface_Channel_list";
	}

	@RequestMapping("/config!eidteChannelConfig.do")
	public String editCampaign(HttpServletRequest request, String channel,
			HttpSession session) throws Exception {
		if (StringUtils.isNotBlank(channel)) {
			ChannelConfig config = channelConfigService
					.getConfigByChannel(channel);
			List<Channel> cist = channelConfigService
					.queryChannelForSel(channel);
			request.setAttribute("clist", cist);
			request.setAttribute("vo", config);
		}
		return "operation/channelConfig/interface_Channel_edit";
	}

	@RequestMapping("/config!addChannelConfig.do")
	public String addCampaign(HttpServletRequest request, HttpSession session)
			throws Exception {
		List<Channel> cist = channelConfigService.queryChannelForSel(null);
		request.setAttribute("clist", cist);
		return "operation/channelConfig/interface_Channel_add";
	}

	@RequestMapping("/config!saveChannelConfig.do")
	public String saveCampaign(HttpServletRequest request, ChannelConfig form,
			HttpSession session, HttpServletResponse response) throws Exception {
		String result = "{status:1}";
		try {
			result = this.channelConfigService.saveOrUpdate(form);
		} catch (Exception e) {
			result = "{status:-1,error:'保存失败！'}";
		}
		OutputHelper.outPut(response, result);
		return null;
	}

}
