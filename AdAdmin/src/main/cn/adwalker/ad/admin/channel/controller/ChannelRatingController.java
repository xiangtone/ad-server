package cn.adwalker.ad.admin.channel.controller;

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
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.ad.admin.channel.bean.ChannelRatingBean;
import cn.adwalker.ad.admin.channel.service.IChannelRatingService;
import cn.adwalker.ad.admin.channel.vo.ChannelRatingVo;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;

/**
 * <p>
 * Title: AppRatingController
 * </p>
 * <p>
 * Description:渠道评级
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author zouguibao
 * @date 2013-5-15
 */
@Controller
public class ChannelRatingController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(ChannelRatingController.class);

	/** 渠道评级相关业务 */
	@Resource
	private IChannelRatingService channelRatingService;

	/**
	 * <p>
	 * Title: listMediaRating
	 * </p>
	 * <p>
	 * Description:渠道评级
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * @date 2013-5-15
	 * @version 1.0
	 * @author zouguibao
	 */
	@RequestMapping("/manage!channelScaleSetting.do")
	public String listMediaRating(HttpServletRequest request,
			HttpServletResponse response, ChannelRatingBean bean) {
		List<ChannelRatingVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = this.channelRatingService.findByPage(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.debug("获取渠道评级列表异常！");
			e.printStackTrace();
		}
		return "operation/media/channel_rating";
	}

	/**
	 * <p>
	 * Title: modifyMediaRating
	 * </p>
	 * <p>
	 * Description:渠道评级修改
	 * </p>
	 * 
	 * @param channelId
	 * @param score
	 * @param request
	 * @return String
	 * @date 2013-5-15
	 * @version 1.0
	 * @author zouguibao
	 */
	@RequestMapping("/manage!modifyChannelRating.do")
	public String modifyMediaRating(Long channelId, Double score,
			HttpServletResponse response) {
		try {
			this.channelRatingService.updateChannelRating(channelId, score);
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}

		return null;
	}
}
