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

import cn.adwalker.ad.admin.ad.bean.AdAjustmentBean;
import cn.adwalker.ad.admin.ad.form.AdAjustmentEditForm;
import cn.adwalker.ad.admin.ad.form.AdCategoryForm;
import cn.adwalker.ad.admin.ad.service.IAdAjustmentService;
import cn.adwalker.ad.admin.ad.service.ICampaignService;
import cn.adwalker.ad.admin.ad.service.IPlacementService;
import cn.adwalker.ad.admin.ad.vo.AdAjustmentEditVo;
import cn.adwalker.ad.admin.ad.vo.AdAjustmentVo;
import cn.adwalker.ad.admin.ad.vo.AdCategoryVo;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.operation.service.IDspInfoImpService;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.pool.SpringDatePool;
import cn.adwalker.core.util.DateUtil;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.model.ad.domain.Placement;
import cn.adwalker.model.common.domain.Sort;

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
public class AdAjustmentController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(AdAjustmentController.class);

	/** 广告方案调整相关业务 */
	@Resource
	private IAdAjustmentService adAjustmentService;

	@Resource
	private IPlacementService placementService;
	
	
	@Resource
	private ICampaignService campaignService;
	
	@Resource
	private IDspInfoImpService dspInfoImpService;
	
	
	@Resource
	private SpringDatePool springDatePool;

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
	@RequestMapping("/manage!adAjustmentSetting.do")
	public String listAdAjustment(HttpServletRequest request,
			HttpServletResponse response, AdAjustmentBean bean) {
		List<AdAjustmentVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = this.adAjustmentService.findByPage(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.debug("获取方案调整列表异常！");
			e.printStackTrace();
		}
		return "ad/ad/adjustment_list";
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
	@RequestMapping("/manage!findAdContent.do")
	public String findAdContent(HttpServletRequest request,
			HttpServletResponse response, Long adId, Map<String, Object> model)
			throws Exception {
		AdAjustmentEditVo vo = adAjustmentService.findById(adId);
		Placement p = null;
		try {
			if (vo != null && vo.getPlacementID() != null) {
				p = placementService.getPlacement(vo.getPlacementID());
			}
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
	@RequestMapping("/manage!updateAdContent.do")
	public String updateAdAjustment(HttpServletRequest request,
			HttpServletResponse response,HttpSession session, AdAjustmentEditForm form,Double price_update ) {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		try {
			adAjustmentService.updateAdAjustment(form,price_update,manageUser.getId());
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <p>
	 * Title: adOnline
	 * </p>
	 * <p>
	 * Description:修改广告上线
	 * </p>
	 * @param adId
	 * @param request
	 * @return String
	 * @date 2013-5-17
	 * @version 1.0
	 * @author zouguibao
	 * @throws IOException
	 */
	@RequestMapping("/manage!adOnline.do")
	public String updateAdOnline(Long adId, HttpServletResponse response) {
		try {
			adAjustmentService.adOnline(adId);
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}

		return null;
	}

	/**
	 * <p>
	 * Title: adOffline
	 * </p>
	 * <p>
	 * Description:修改广告下线
	 * </p>
	 * 
	 * @param adId
	 * @param request
	 * @return String
	 * @date 2013-5-17
	 * @version 1.0
	 * @author zouguibao
	 */
	@RequestMapping("/manage!adOffline.do")
	public String adOffline(Long adId, HttpServletResponse response) {
		try {
			adAjustmentService.adOffline(adId);// 点击下线，取消定时操作
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}

		return null;

	}
	
	

	/**
	 * 
	 * <p>
	 * Title: findActivity
	 * </p>
	 * <p>
	 * Description:活动信息查看修改界面
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param actId
	 * @return
	 * @author lichuang
	 * @date 2013-4-7
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!editCampaign111111.do")
	public String editCampaign(HttpServletRequest request,
			Map<String, Object> model, Long id) {
		List<AdCategoryVo> list = null;
		try {
			list = this.adAjustmentService.getCategory(id);
			Map<String, List<Sort>> map = adAjustmentService.queryAllCategory();
			Map<String, List<Sort>> mapc = adAjustmentService.queryAllCity();
			String str = null;
			if (list != null && list.size() > 0) {
				for (AdCategoryVo c : list) {
					if (str != null) {
						str = str + "," + c.getCategory_id();
					} else {
						str = String.valueOf(c.getCategory_id());
					}
				}
			}
			List<Sort> sList = springDatePool.getBigSort();
			model.put("id", id);
			model.put("sList", sList);
			model.put("map", map);
			model.put("mapc", mapc);
			model.put("category_str", str);
			model.put("today", DateUtil.formatDate(new Date()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ad/ad/ad_config";
	}
	
	
	@RequestMapping("/updateCampaign111.do")
	public String updateCampaign(HttpServletRequest request,
			HttpSession session, HttpServletResponse res, String code,
			AdCategoryForm form, Long category[])
			throws Exception {
		ResultVo vo = null;
		if (form != null && form.getId() != null) {
			adAjustmentService.updateAdCategory(form, category);
		}

		vo = new ResultSuccessVo();
		OutputHelper.outPut(res, JacksonMapper.objectToJsonString(vo));
		return null;
	}
}
