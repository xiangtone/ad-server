package cn.adwalker.ad.admin.app.controller;

import java.util.List;
import java.util.Map;

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
import cn.adwalker.model.ad.domain.Placement;
import cn.adwalker.ad.admin.ad.form.AdAjustmentEditForm;
import cn.adwalker.ad.admin.ad.service.IPlacementService;
import cn.adwalker.ad.admin.ad.vo.AdAjustmentEditVo;
import cn.adwalker.ad.admin.app.bean.MediaAdBean;
import cn.adwalker.ad.admin.app.service.IMediaAdService;
import cn.adwalker.ad.admin.app.vo.MediaAdVo;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;

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
public class MediaAdController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(MediaAdController.class);

	/** 广告方案调整相关业务 */
	@Resource
	private IMediaAdService mediaAdService;

	@Resource
	private IPlacementService placementService;

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
	@RequestMapping("/app/adList.do")
	public String list(HttpServletRequest request,
			HttpServletResponse response, MediaAdBean bean) {
		List<MediaAdVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = this.mediaAdService.findByPage(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.debug("获取方案调整列表异常！");
			e.printStackTrace();
		}
		return "media/ad/list";
	}

	/**
	 * <p>
	 * Title: findAdContent
	 * </p>
	 * <p>
	 * Description:方案详细内容查询
	 * </p>
	 * 
	 * @param adId
	 * @param price
	 * @param request
	 * @return String
	 * @date 2013-5-15
	 * @version 1.0
	 * @author zouguibao
	 * @throws Exception
	 */
	@RequestMapping("/app/adInfo.do")
	public String findAdContent(HttpServletRequest request,
			HttpServletResponse response, Long adId, Map<String, Object> model)
			throws Exception {
		AdAjustmentEditVo vo = mediaAdService.findById(adId);
		Placement p = null;
		try {
			p = placementService.getPlacement(vo.getPlacementID());
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.put("placement", p);
		request.setAttribute("vo", vo);
		return "ad/ad/adjustment_edit";
	}

	/**
	 * <p>
	 * Title: updateAdContent
	 * </p>
	 * <p>
	 * Description:修改广告投放方案
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param form
	 * @return String
	 * @date 2013-5-15
	 * @version 1.0
	 * @author zouguibao
	 */
	@RequestMapping("/app/updateAdInfo.do")
	public String updateAdAjustment(HttpServletRequest request,
			HttpServletResponse response, AdAjustmentEditForm form) {
		try {
			mediaAdService.updateAdAjustment(form);
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * <p>
	 * Title: auditAd
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 * @author cuidd
	 * @date 2013-9-26
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/app/auditAd.do")
	public String auditAd(HttpServletRequest request,
			HttpServletResponse response, Long ad_id) {
		try {
			mediaAdService.auditAd(ad_id);
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
