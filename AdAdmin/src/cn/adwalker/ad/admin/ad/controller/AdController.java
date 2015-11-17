/**
 * 
 */
package cn.adwalker.ad.admin.ad.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.ad.bean.AdByPlacementBean;
import cn.adwalker.ad.admin.ad.form.AdAddForm;
import cn.adwalker.ad.admin.ad.form.AdEditForm;
import cn.adwalker.ad.admin.ad.service.IAdService;
import cn.adwalker.ad.admin.ad.service.IPlacementPackageService;
import cn.adwalker.ad.admin.ad.service.IPlacementService;
import cn.adwalker.ad.admin.ad.vo.AdByPlacementVo;
import cn.adwalker.ad.admin.ad.vo.AdChannelVo;
import cn.adwalker.ad.admin.ad.vo.AdVo;
import cn.adwalker.ad.admin.ad.vo.AdertiseAppVo;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.mail.service.ICampaignMailService;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.DateUtil;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultErrorVo;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.model.ad.domain.Placement;
import cn.adwalker.model.ad.domain.PlacementPackage;

/**
 * 方案配置
 * <p>
 * Title: AppController
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-9
 */
@Controller
public class AdController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(AdController.class);

	/**
	 * 服务组件
	 */
	@Resource
	private IAdService adService;

	@Resource
	private IPlacementPackageService placementPackageService;

	@Resource
	private IPlacementService placementService;

	@Resource
	private ICampaignMailService campaignMailService;

	@RequestMapping("/updateAdStatus.do")
	public String updateAdStatus(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Long> ids = adService.adBudget();
			campaignMailService.sendMailAdBudget(ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		OutputHelper.outPut(response, "ok");

		return null;
	}

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
	@RequestMapping("/manage!adByPlacementList.do")
	public String listAdAjustment(HttpServletRequest request,
			HttpServletResponse response, AdByPlacementBean bean,
			Map<String, Object> model) {
		List<AdByPlacementVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			if (bean != null && bean.getPlacement_id() != null) {
				list = this.adService.findByPlacement(bean, pageInfo);
				model.put("pageInfo", pageInfo);
				model.put("list", list);
			}
			model.put("bean", bean);
			model.put("pageRecord", pageInfo.getTotalRow());
			model.put("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.debug("获取方案调整列表异常！");
			e.printStackTrace();
		}
		return "ad/ad_list";
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
	@RequestMapping("/manage!addAd.do")
	public String addAd(HttpServletRequest request,
			HttpServletResponse response, AdAddForm form) {
		Long id = null;
		try {
			id = adService.insert(form, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		OutputHelper.outPut(response, id != null ? String.valueOf(id) : "");
		return null;
	}

	/**
	 * <p>
	 * Title: addAdChannel
	 * </p>
	 * <p>
	 * Description:添加渠道广告
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param placement_id
	 * @param appid
	 * @param page_type
	 * @return
	 * @author lichuang
	 * @date 2013-5-30
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!addAdChannel.do")
	public String addAdChannel(HttpServletRequest request,
			HttpServletResponse response, AdAddForm form) {
		Long id = null;
		try {
			id = adService.insertChannelAD(form);
		} catch (Exception e) {
			e.printStackTrace();
		}
		OutputHelper.outPut(response, id != null ? String.valueOf(id) : "");
		return null;
	}

	/**
	 * <p>
	 * Title: listAdvertiseConfigure
	 * </p>
	 * <p>
	 * Description:活动投放方案
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author lichuang
	 * @date 2013-4-9
	 * @return String
	 * @version 1.0
	 * @throws
	 */
	@RequestMapping("/manage!advertiseConfigure.do")
	public String listAdvertiseConfigure(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,
			Long placement_id) {
		try {
			model.put("placement_id", placement_id);
		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "ad/placement/placement_configure";
	}

	/**
	 * 
	 * <p>
	 * Title: listAdConfigureChannel
	 * </p>
	 * <p>
	 * Description:渠道方案配置
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param os
	 * @param placement_id
	 * @return
	 * @author lichuang
	 * @date 2013-5-30
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!advertiseConfigureChannel.do")
	public String listAdConfigureChannel(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,
			Long placement_id) {
		List<AdertiseAppVo> list = null;
		try {
			list = (List<AdertiseAppVo>) this.adService.findAllChannel(
					placement_id, null);
			List<AdChannelVo> adList = adService
					.findChannelByPlacement(placement_id);
			List<PlacementPackage> packages = placementPackageService
					.findByPlacement(placement_id);
			model.put("adList", adList);
			model.put("list", list);
			model.put("packages", packages);
			model.put("placement_id", placement_id);
		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "ad/placement/placement_configure_channel";
	}

	@RequestMapping("/manage!adConfigureChannel_query.do")
	public String listChannel(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,
			Long placement_id, String channel) throws IOException {
		List<AdertiseAppVo> list = null;
		try {
			list = (List<AdertiseAppVo>) this.adService.findAllChannel(
					placement_id, channel);
		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(list));

		return null;
	}

	/**
	 * <p>
	 * Title: listAdvertiseConfigure
	 * </p>
	 * <p>
	 * Description:活动投放方案
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author lichuang
	 * @date 2013-4-9
	 * @return String
	 * @version 1.0
	 * @throws
	 */
	@RequestMapping("/manage!advertiseConfigureTemp.do")
	public String listAdvertiseConfigureTemp(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,
			Long placement_id) {
		List<AdertiseAppVo> list = null;
		try {
			Placement p = placementService.getPlacement(placement_id);
			if (p.getPlan_start() != null) {
				p.setPlan_start(DateUtil.max(p.getPlan_start(), new Date()));// 修改是开始时间不应小于今天之前
			} else {
				p.setPlan_start(new Date());// 修改是开始时间不应小于今天之前
			}
			list = (List<AdertiseAppVo>) this.adService.findAll(p.getOs(), 0L);
			List<AdVo> adList = adService.findAndroidByPlacement(placement_id,
					0);
			List<PlacementPackage> packages = placementPackageService
					.findByPlacement(placement_id);
			String typeStr = placementService.getTypeStr(placement_id);

			Integer placement_status = adService
					.getStatusByPlacementId(placement_id);

			model.put("page_type", typeStr);
			model.put("ad_media_list", adList);
			model.put("media_list", list);
			model.put("packages", packages);
			model.put("placement_id", placement_id);
			model.put("placement_status", placement_status);
			model.put("os", p.getOs());
			model.put("placement", p);
		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "ad/placement/placement_configure_temp";
	}

	/**
	 * <p>
	 * Title: selectApp
	 * </p>
	 * <p>
	 * Description:选择媒体（应用）
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param type_id
	 * @param os
	 * @return
	 * @author lichuang
	 * @date 2013-5-30
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!selectApp.do")
	public String selectApp(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,
			Long type_id, String os) {
		List<AdertiseAppVo> list = null;
		try {
			list = (List<AdertiseAppVo>) this.adService.findAll(os, type_id);
			OutputHelper.outPut(response,
					JacksonMapper.objectToJsonString(list));
		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
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
	@RequestMapping("/manage!updateAd.do")
	public String updateAd(HttpServletRequest request,
			HttpServletResponse response, AdEditForm form) throws IOException {
		ResultVo result = null;
		try {
			adService.updateAd(form);
			result = new ResultSuccessVo();
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResultErrorVo("系统异常！！");
		}
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(result));
		return null;
	}

	/**
	 * 
	 * <p>
	 * Title: updateAdChannel
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 * @throws IOException
	 * @author cuidd
	 * @date 2013-7-10
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!updateAdChannel.do")
	public String updateAdChannel(HttpServletRequest request,
			HttpServletResponse response, AdEditForm form) throws IOException {
		ResultVo result = null;
		try {
			adService.updateAdCha(form);
			
			result = new ResultSuccessVo();
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResultErrorVo("系统异常！！");
		}
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(result));
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
	@RequestMapping("/manage!delAd.do")
	public String delAd(HttpServletRequest request,
			HttpServletResponse response, Long id) {
		try {
			adService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
