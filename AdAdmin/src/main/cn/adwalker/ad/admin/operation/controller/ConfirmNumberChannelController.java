package cn.adwalker.ad.admin.operation.controller;

import java.io.IOException;
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
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultErrorVo;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.operation.bean.ConfirmNumChannelbean;
import cn.adwalker.ad.admin.operation.service.IConfirmNumberChannelService;
import cn.adwalker.ad.admin.operation.vo.ConfirmNumberChannelVo;

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
public class ConfirmNumberChannelController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(ConfirmNumberChannelController.class);

	/** 广告业务 */
	@Resource
	private IConfirmNumberChannelService confirmNumberChannelService;

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
	@RequestMapping("/manage!advConfirmationChannel.do")
	public String listadvConfirmationNumber(HttpServletRequest request,
			HttpServletResponse response, ConfirmNumChannelbean bean) {
		List<ConfirmNumberChannelVo> list = null;
		try {
			IPageInfo pageInfo = new SetPage(request);
			list = this.confirmNumberChannelService.findDetailByPage(bean,
					pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
		} catch (Exception e) {
			logger.debug("获取广告单价列表异常！");
			e.printStackTrace();
		}
		return "operation/confirm_number_channel_list";
	}

	/**
	 * <p>
	 * Title: confirmationAmount
	 * </p>
	 * <p>
	 * Description:根据渠道指数给渠道分数
	 * </p>
	 * 
	 * @param request
	 * @param Response
	 * @param id
	 * @param number
	 * @return
	 * @throws IOException
	 * @author lichuang
	 * @date 2013-7-18
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!confirmationAmount.do")
	public String confirmationAmount(HttpServletRequest request, HttpSession session,
			HttpServletResponse Response, Long id, Integer amount,Integer media,Long campaign_id)
			throws IOException {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		ResultVo vo = null;
		try {
			vo = new ResultSuccessVo();
			this.confirmNumberChannelService.confirmationAmount(id, amount,media,campaign_id,manageUser);
		} catch (Exception e) {
			e.printStackTrace();
			vo = new ResultErrorVo("系统错误");

		}
		OutputHelper.outPut(Response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

}
