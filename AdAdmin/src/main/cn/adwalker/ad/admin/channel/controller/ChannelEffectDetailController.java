package cn.adwalker.ad.admin.channel.controller;

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
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.ad.admin.channel.bean.ChannelEffectQueryBean;
import cn.adwalker.ad.admin.channel.service.IChannelEffectQueryService;
import cn.adwalker.ad.admin.channel.vo.ChannelEffectSumVo;
import cn.adwalker.ad.admin.channel.vo.ChannelEffectVo;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;

/**
 * 
 * <p>
 * Title: ChannelEffectDetailController
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-9-3
 */
@Controller
public class ChannelEffectDetailController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(ChannelEffectDetailController.class);

	/** 广告业务 */
	@Resource
	private IChannelEffectQueryService channelEffectQueryService;

	/**
	 * 
	 * <p>
	 * Title: channelEffectList
	 * </p>
	 * <p>
	 * Description:渠道效果数查询
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param bean
	 * @return
	 * @author cuidd
	 * @date 2014-2-11
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!channelEffectList.do")
	public String channelEffectList(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			ChannelEffectQueryBean bean) {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		List<ChannelEffectVo> list = null;
		try {
			IPageInfo pageInfo = new SetPage(request);
			// 权限控制
			if (ObjectUtils.isNotEmpty(manageUser.getRealName())
					&& ObjectUtils.isNotEmpty(manageUser.getRoleId())) {
				bean.setReal_name(manageUser.getRealName());
				bean.setRole_id(manageUser.getRoleId());
			}
			list = this.channelEffectQueryService.findByPage(bean,
					pageInfo);

			// 总计
			ChannelEffectSumVo sumresult = this.channelEffectQueryService
					.getNumberSum(bean);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("sum", sumresult);
			request.setAttribute("list", list);
		} catch (Exception e) {
			logger.debug("获取广告单价列表异常！");
			e.printStackTrace();
		}
		return "channel/channel_effect";
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
	@RequestMapping("/manage!exportChannelEffect.do")
	public String DevConsumeDetailFindAllForReport(HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			ChannelEffectQueryBean bean) {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		List<ChannelEffectVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			// 权限控制
			if (ObjectUtils.isNotEmpty(manageUser.getRealName())
					&& ObjectUtils.isNotEmpty(manageUser.getRoleId())) {
				bean.setReal_name(manageUser.getRealName());
				bean.setRole_id(manageUser.getRoleId());
			}
			list = this.channelEffectQueryService.findAll(bean);
			// 权限控制
			if (ObjectUtils.isNotEmpty(manageUser.getRealName())
					&& ObjectUtils.isNotEmpty(manageUser.getRoleId())) {
				bean.setReal_name(manageUser.getRealName());
				bean.setRole_id(manageUser.getRoleId());
			}
			// 求和
			ChannelEffectSumVo sumresult = this.channelEffectQueryService
					.getNumberSum(bean);

			// 导出报表
			boolean[] isMoney = new boolean[] { false, false, false, false,
					false, false, false, false, false, false, false, false,
					false, };
			ExportUtils.exportExcel(list, "收入明细统计", "收入消费明细统计", new String[] {
					"ID", "编号", "渠道包号", "文件名", "效果发生时间", "活动名称", "媒体名称",
					"渠道备注", "广告形式", "广告主确认数" }, new String[] { "id", "code",
					"package_id", "file_name", "static_date", "campaign_name",
					"media_name", "remarks", "type_name", "confirm_amount" },
					new String[] { "统计", "-", "-", "-", "-", "-", "-", "-",
							"-", "" + sumresult.getSum_platform_amount() },
					null, null, isMoney, null, response);

			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
			request.setAttribute("sum", sumresult);
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "channel/channel_effect";
	}
}
