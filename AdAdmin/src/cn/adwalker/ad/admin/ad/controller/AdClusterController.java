package cn.adwalker.ad.admin.ad.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.ad.bean.AdClusterBean;
import cn.adwalker.ad.admin.ad.form.AdClusterEditForm;
import cn.adwalker.ad.admin.ad.service.IAdClusterService;
import cn.adwalker.ad.admin.ad.service.IPlacementService;
import cn.adwalker.ad.admin.ad.vo.AdClusterEditVo;
import cn.adwalker.ad.admin.ad.vo.AdClusterVo;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;

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
public class AdClusterController extends AbstractControllerParent {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(AdClusterController.class);

	/** 广告方案调整相关业务 */
	@Resource
	private IAdClusterService adClusterService;

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
	@RequestMapping("/adClusterList.do")
	public String list(HttpServletRequest request,
			HttpServletResponse response, AdClusterBean bean) {
		List<AdClusterVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = this.adClusterService.findByPage(bean, pageInfo);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bean", bean);
			request.setAttribute("list", list);
			request.setAttribute("pageRecord", pageInfo.getTotalRow());
			request.setAttribute("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.debug("获取方案调整列表异常！");
			e.printStackTrace();
		}
		return "ad/ad_cluster_list";
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
	@RequestMapping("/adClusterEdit.do")
	public String findAdContent(HttpServletRequest request,
			HttpServletResponse response, Long adId, Map<String, Object> model)
			throws Exception {
		AdClusterEditVo vo = adClusterService.findById(adId);
		request.setAttribute("vo", vo);
		return "ad/ad_cluster_edit";
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
	@RequestMapping("/updateadCluster.do")
	public String updateAdAjustment(HttpServletRequest request,
			HttpServletResponse response,HttpSession session, AdClusterEditForm form,Double price_update ) {
		
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		try {
			adClusterService.updateAdAjustment(form,price_update,manageUser.getId());
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
	//@RequestMapping("/manage!adOnline.do")
	public String updateAdOnline(Long adId, HttpServletResponse response) {
		try {
			adClusterService.adOnline(adId);
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
	//@RequestMapping("/manage!adOffline.do")
	public String adOffline(Long adId, HttpServletResponse response) {
		try {
			adClusterService.adOffline(adId);// 点击下线，取消定时操作
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}

		return null;

	}
}
