package cn.adwalker.ad.admin.app.controller;

import java.io.IOException;
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
import cn.adwalker.core.vo.ResultErrorVo;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.model.ad.domain.PlacementPackage;
import cn.adwalker.ad.admin.ad.form.AdAddForm;
import cn.adwalker.ad.admin.ad.form.AdEditForm;
import cn.adwalker.ad.admin.app.bean.HistoryAdQuery;
import cn.adwalker.ad.admin.app.bean.MediaBean;
import cn.adwalker.ad.admin.app.service.IMediaService;
import cn.adwalker.ad.admin.app.vo.AdByAppVo;
import cn.adwalker.ad.admin.app.vo.BigAppVo;
import cn.adwalker.ad.admin.app.vo.PlacementVo;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;

/**
 * <p>
 * Title: MediaController
 * </p>
 * <p>
 * Description:大媒体
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-9-6
 */

@Controller
public class MediaController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(MediaController.class);

	/** 广告业务 */
	@Resource
	private IMediaService mediaService;

	/**
	 * <p>
	 * Title: List
	 * </p>
	 * <p>
	 * Description:大媒体List
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-9-6
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!bigAppList.do")
	public String List(HttpServletRequest request,
			HttpServletResponse response, MediaBean bean) {
		List<BigAppVo> list = null;
		try {
			IPageInfo pageInfo = new SetPage(request);
			list = this.mediaService.findList(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.debug("获取广告单价列表异常！");
			e.printStackTrace();
		}
		return "media/placement/big_app_list";
	}

	/**
	 * 
	 * <p>
	 * Title: edit
	 * </p>
	 * <p>
	 * Description:修改投放
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param id
	 * @return
	 * @author cuidd
	 * @date 2013-5-15
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!mediaPlacement.do")
	public String edit(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model, Long app_id) {
		try {
			model.put("app_id", app_id);
		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "media/placement/placement_edit";
	}

	@RequestMapping("/manage!appPlacement.do")
	public String listAdvertiseConfigureTemp(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model, Long app_id) {
		try {
			String page_str = this.mediaService.getPageStrByApp(app_id);
			List<AdByAppVo> adList = mediaService
					.findAndroidByPlacement(app_id);
			model.put("page_str", page_str);
			model.put("page_str", page_str);
			model.put("app_id", app_id);
			model.put("ad_media_list", adList);

		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "media/placement/placement_configure_temp";
	}

	@RequestMapping("/app/loadPlacement.do")
	public void loadPlacement(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,
			Long app_id, Long type_id, String placement_id_name) {
		List<PlacementVo> list = null;
		try {
			list = this.mediaService.findAll(app_id, type_id,placement_id_name);
			OutputHelper.outPut(response,
					JacksonMapper.objectToJsonString(list));
		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
	}

	@RequestMapping("/manage!appPlacementLoadPackage.do")
	public void loadPackage(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,
			Long placement_id) {
		List<PlacementPackage> list = null;
		try {
			list = this.mediaService.findPackage(placement_id);
			OutputHelper.outPut(response,
					JacksonMapper.objectToJsonString(list));
		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * <p>
	 * Title: addAd
	 * </p>
	 * <p>
	 * Description:添加广告
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param placement_id
	 * @param appid
	 * @param page_type
	 * @return
	 * @author cuidd
	 * @date 2013-5-13
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/app/addAd.do")
	public String addAd(HttpServletRequest request,
			HttpServletResponse response, AdAddForm form) {
		Long id = null;
		try {
			if (form != null && form.getAppid() != null
					&& form.getPlacement_id() != null) {
				id = mediaService.insertAd(form, 0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		OutputHelper.outPut(response, id != null ? String.valueOf(id) : "");
		return null;
	}

	/**
	 * 
	 * <p>
	 * Title: delAd
	 * </p>
	 * <p>
	 * Description:删除广告
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @author cuidd
	 * @date 2013-5-13
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/app/delAd.do")
	public String delAd(HttpServletRequest request,
			HttpServletResponse response, Long id) {
		try {
			mediaService.deleteAd(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * <p>
	 * Title: updateAd
	 * </p>
	 * <p>
	 * Description:更新广告
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 * @author cuidd
	 * @date 2013-5-13
	 * @return String
	 * @version 1.0
	 * @throws IOException
	 */
	@RequestMapping("/app/updateAd.do")
	public String updateAd(HttpServletRequest request,
			HttpServletResponse response, AdEditForm form) throws IOException {
		ResultVo result = null;
		try {
			mediaService.updateAd(form);
			result = new ResultSuccessVo();
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResultErrorVo("系统异常！！");
		}
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(result));
		return null;
	}

	@RequestMapping("/app/submitAd.do")
	public String submitAd(HttpServletRequest request,
			HttpServletResponse response, Long ad_id) throws IOException {
		ResultVo result = null;
		try {
			mediaService.submitAd(ad_id);
			result = new ResultSuccessVo();
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResultErrorVo("系统异常！！");
		}
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(result));
		return null;
	}

	@RequestMapping("/app/historyAd.do")
	public String appHistoryAd(HttpServletRequest request,
			HttpServletResponse response, HistoryAdQuery bean) {
		List<AdByAppVo> list = null;
		try {
			IPageInfo pageInfo = new SetPage(request);
			list = this.mediaService.findHistoryAdByPage(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.debug("获取广告单价列表异常！");
			e.printStackTrace();
		}
		return "media/placement/history_ad";
	}

}
