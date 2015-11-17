package cn.adwalker.ad.admin.app.controller;

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
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.ad.admin.app.service.IAppRatingService;
import cn.adwalker.ad.admin.app.vo.MediaRatingVo;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.operation.bean.MediaRatingBean;

/**
 * <p>
 * Title: AppRatingController
 * </p>
 * <p>
 * Description:媒体评级
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author zouguibao
 * @date 2013-5-14
 */
@Controller
public class AppRatingController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(AppRatingController.class);

	/** 媒体评级相关业务 */
	@Resource
	private IAppRatingService appRatingService;

	/**
	 * <p>
	 * Title: listMediaRating
	 * </p>
	 * <p>
	 * Description:媒体评级
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * @date 2013-5-14
	 * @version 1.0
	 * @author zouguibao
	 */
	@RequestMapping("/manage!appScaleSetting.do")
	public String listMediaRating(HttpSession session,HttpServletRequest request,
			HttpServletResponse response, MediaRatingBean bean) {
		List<MediaRatingVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		SysUserVo user = (SysUserVo) session.getAttribute("manageUser");
		try {
			list = this.appRatingService.findByPage(bean, user,pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.debug("获取媒体评级列表异常！");
			e.printStackTrace();
		}
		return "operation/media/app_rating";
	}

	/**
	 * <p>
	 * Title: modifyMediaRating
	 * </p>
	 * <p>
	 * Description:媒体评级修改
	 * </p>
	 * 
	 * @param session
	 * @param mediaId
	 * @param score
	 * @param model
	 * @param request
	 * @return String
	 * @date 2013-5-14
	 * @version 1.0
	 * @author zouguibao
	 */
	@RequestMapping("/manage!modifyMediaRating.do")
	public String modifyMediaRating(Long mediaId, Double score,
			HttpServletResponse response) {
		try {
			this.appRatingService.updateMediaRating(mediaId, score);
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}

		return null;
	}
}
