/**
 * 
 */
package cn.adwalker.ad.admin.ad.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.ad.bean.PlacementQueryBean;
import cn.adwalker.ad.admin.ad.form.PlacementEditForm;
import cn.adwalker.ad.admin.ad.service.IAdService;
import cn.adwalker.ad.admin.ad.service.ICampaignService;
import cn.adwalker.ad.admin.ad.service.IPlacementService;
import cn.adwalker.ad.admin.ad.vo.AdVo;
import cn.adwalker.ad.admin.ad.vo.CampaignInfoVo;
import cn.adwalker.ad.admin.ad.vo.ErrorInfo;
import cn.adwalker.ad.admin.ad.vo.PlacementEditVo;
import cn.adwalker.ad.admin.ad.vo.PlacementInfoVo;
import cn.adwalker.ad.admin.ad.vo.PlacementVo;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.config.StatusConstant;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.pool.SpringDatePool;
import cn.adwalker.core.util.ConfigUtil;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.model.ad.domain.Campaign;
import cn.adwalker.model.ad.domain.PlacementTypeRel;
import cn.adwalker.model.common.domain.Sort;
import cn.adwalker.model.common.domain.SysCategory;

/**
 * 
 * <p>
 * Title: CampaignController
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-2
 */
@Controller
public class PlacementController extends AbstractControllerParent {

	@Resource
	private IPlacementService placementService;

	@Resource
	private SpringDatePool springDatePool;

	@Resource
	private ICampaignService campaignService;

	@Resource
	private IAdService adService;

	/** 日志记录器 */
	Logger logger = Logger.getLogger(PlacementController.class);

	/**
	 * 
	 * <p>
	 * Title: placementList
	 * </p>
	 * <p>
	 * Description:投放列表
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param bean
	 * @return
	 * @author cuidd
	 * @date 2013-5-14
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!placementList.do")
	public String placementList(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,
			PlacementQueryBean bean) {
		List<PlacementVo> list = null;
		IPageInfo pageInfo = new SetPage(request);
		try {
			list = this.placementService.findByPage(bean, manageUser, pageInfo);
			List<SysCategory> ecList = this.springDatePool.getSysCategory();
			model.put("ecList", ecList);
			model.put("pageInfo", pageInfo);
			model.put("list", list);
			model.put("bean", bean);
			model.put("pageRecord", pageInfo.getTotalRow());
			model.put("page", pageInfo.getPageSize());
		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "ad/placement/placement_list";
	}

	/**
	 * 
	 * <p>
	 * Title: placementInfo
	 * </p>
	 * <p>
	 * Description:投放信息
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param id
	 * @return
	 * @author cuidd
	 * @date 2013-5-29
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!placementInfo.do")
	public String placementInfo(HttpServletRequest request,
			Map<String, Object> model, HttpServletResponse response,
			HttpSession session, Long id) {
		model.put("placement_id", id);
		return "ad/placement/placement_info";
	}

	/**
	 * 
	 * <p>
	 * Title: preAuditCampaign
	 * </p>
	 * <p>
	 * Description:审核投放
	 * </p>
	 * 
	 * @param request
	 * @param model
	 * @param session
	 * @param code
	 * @param placement_id
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-5-29
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!preAuditPlacement.do")
	public String preAuditCampaign(HttpServletRequest request,
			Map<String, Object> model, HttpSession session, String code,
			Long placement_id) throws Exception {
		try {

			model.put("placement_id", placement_id);
		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "ad/placement/placement_audit";
	}

	/**
	 * 
	 * <p>
	 * Title: auditCampaign
	 * </p>
	 * <p>
	 * Description:执行投放审核
	 * </p>
	 * 
	 * @param request
	 * @param model
	 * @param session
	 * @param response
	 * @param note
	 * @param ispass
	 * @param placement_id
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-5-29
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!auditPlacement.do")
	public String auditCampaign(HttpServletRequest request,
			Map<String, Object> model, HttpSession session,
			HttpServletResponse response, String note, Integer ispass,
			Long placement_id) throws Exception {
		try {
			this.placementService.audit(placement_id, ispass, note, manageUser);
		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		ResultVo vo = new ResultSuccessVo();
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * 
	 * <p>
	 * Title: add
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param id
	 * @return
	 * @author cuidd
	 * @date 2013-6-20
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!addPlacement.do")
	public String add(HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> model, Long id) {
		try {
			List<SysCategory> ecList = this.springDatePool.getSysCategory();
			PlacementEditVo vo = placementService.getPlacementEditVo(id);
			Campaign campaign = this.campaignService
					.getCampaignBaseInfoByPlacement(id);
			List<PlacementTypeRel> pageTypeVos = placementService
					.findTypesByplacement(id);
			StringBuilder sb = new StringBuilder();
			if (pageTypeVos != null && pageTypeVos.size() > 0) {
				for (PlacementTypeRel pageType : pageTypeVos) {
					sb.append(pageType.getType_id() + ",");
				}
				sb = sb.deleteCharAt(sb.length() - 1);
			}
			model.put("page_type", sb.toString());
			model.put("ecList", ecList);
			model.put("campaign", campaign);
			model.put("vo", vo);
			request.setAttribute("placement_id", id);
		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "ad/placement/placement_add";
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
	@RequestMapping("/manage!editPlacement.do")
	public String edit(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model, Long id) {
		try {
			PlacementEditVo vo = placementService.getPlacementEditVo(id);
			List<SysCategory> ecList = this.springDatePool.getSysCategory();
			List<PlacementTypeRel> pageTypeVos = placementService
					.findTypesByplacement(id);
			StringBuilder sb = new StringBuilder();
			if (pageTypeVos != null && pageTypeVos.size() > 0) {
				for (PlacementTypeRel pageType : pageTypeVos) {
					sb.append(pageType.getType_id() + ",");
				}
				sb = sb.deleteCharAt(sb.length() - 1);
			}
			model.put("page_type", sb.toString());
			model.put("vo", vo);
			model.put("ecList", ecList);
			model.put("placement_id", id);
		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "ad/placement/placement_edit";
	}

	/**
	 * <p>
	 * Title: updateStatus
	 * </p>
	 * <p>
	 * Description:更新投放状态
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param id
	 * @param status
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-30
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!updateStatus.do")
	public String updateStatus(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Long id,
			Integer status) throws Exception {
		ResultVo vo = null;
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		placementService.updateStatus(id, manageUser, status);
		vo = new ResultSuccessVo();
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * 
	 * <p>
	 * Title: update
	 * </p>
	 * <p>
	 * Description:保存投放基本信息/p>
	 * 
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-5-8
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!updatePlacement.do")
	public String update(HttpServletRequest request,
			HttpServletResponse response, PlacementEditForm form)
			throws Exception {
		ResultVo vo = null;
		form.setStatus(StatusConstant.CAMPAIGN_STATUS_PLACEMENT_CAOGAO);
		placementService.updatePlacement(form);
		vo = new ResultSuccessVo();
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * 
	 * <p>
	 * Title: modify
	 * </p>
	 * <p>
	 * Description:修改投放
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-5-29
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!modifyPlacement.do")
	public String modify(HttpServletRequest request,
			HttpServletResponse response, PlacementEditForm form)
			throws Exception {
		ResultVo vo = null;
		placementService.updatePlacement(form);
		vo = new ResultSuccessVo();
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
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
	@RequestMapping("/manage!placementInfoTemp.do")
	public String placementInfoTemp(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model, Long id) {
		try {
			PlacementInfoVo vo = placementService.getPlacementInfo(id);
			CampaignInfoVo campaignInfoVo = this.campaignService
					.getCampaignInfoByPlacement(id);
			List<SysCategory> ecList = this.springDatePool.getSysCategory();
			String typeStr = placementService.getTypeStr(id);
			List<Sort> sList = springDatePool.getBigSort();
			model.put("sList", sList);
			model.put("page_type", typeStr);
			model.put("ecList", ecList);
			model.put("vo", vo);
			model.put("campaignInfoVo", campaignInfoVo);
			model.put("placement_id", id);
			model.put("resources_url_prefix",
					ConfigUtil.getString("resources.url.prefix"));
			model.put("images_url_prefix",
					ConfigUtil.getString("images.url.prefix"));
		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "ad/placement/placement_info_temp";
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
	@RequestMapping("/manage!preSubmitPlacement.do")
	public String preSubmitPlacement(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model, Long id) {
		try {
			model.put("placement_id", id);
			model.put("errorList", validateData(id));
		} catch (Exception e) {
			logger.debug("获取广告效果列表异常！");
			e.printStackTrace();
		}
		return "ad/placement/placement_info_submit";
	}

	private List<ErrorInfo> validateData(Long placement_id) throws Exception {
		List<ErrorInfo> list = new ArrayList<ErrorInfo>();
		if (placement_id != null) {
			PlacementInfoVo vo = placementService
					.getPlacementInfo(placement_id);
			if (vo != null) {
				if (StringUtils.isEmpty(vo.getName())) {
					list.add(new ErrorInfo("请填写投放名称！！"));
				}
				if (StringUtils.isNotEmpty(vo.getOs())) {
					if (vo.getOs().equals("ios")) {
						if (StringUtils.isEmpty(vo.getConfig_id())) {
							if(vo.getConfirm_mode()==1){
								list.add(new ErrorInfo("亲请在运营管理里面配置IOS！！"));
							}
						}
					}
				}
				if (vo.getStar_level() == null) {
					list.add(new ErrorInfo("请填应用级别！！"));
				}
				if (vo.getCategory_id() == null) {
					list.add(new ErrorInfo("请选择投放分类！！"));

				}
				if (vo.getCategory_id() == null) {
					list.add(new ErrorInfo("请填写广告语！！"));

				}
				if (vo.getKeyword() == null) {
					list.add(new ErrorInfo("请填写关键字！！"));

				}
				if (vo.getCampaign_desc() == null) {
					list.add(new ErrorInfo("请填写活动描述！！"));
				}
				if (vo.getTypeList() == null || vo.getTypeList().size() <= 0) {
					list.add(new ErrorInfo("请填写广告形式！！"));
				}
				if (StringUtils.isEmpty(vo.getAppimg_url_01())
						|| StringUtils.isEmpty(vo.getAppimg_url_02())
						|| StringUtils.isEmpty(vo.getAppimg_url_03())) {
					list.add(new ErrorInfo("请上传应用截图！！"));
				}
				if (vo.getTypeList() == null || vo.getTypeList().size() <= 0) {
					list.add(new ErrorInfo("请填写广告形式！！"));

				} else {
					if (vo.getTypeList().contains(0L)) {
						StringBuilder sb = new StringBuilder("积分墙:");
						if (StringUtils.isEmpty(vo.getWall_score_desc())) {
							sb.append("获取积分描述,");
						}

						if (StringUtils.isEmpty(vo.getWall_score_long_desc())) {
							sb.append("积分长描述,");
						}

						if (vo.getWall_score_weight() != null) {
							sb.append("权重,");
						}
						if (vo.getWall_score_response_category() == 0) {
							if (vo.getWall_score_response_type() == null) {
								sb.append("下载方式,");
							}
						} else if (vo.getWall_score_response_category() == 1) {
							if (StringUtils.isEmpty(vo
									.getWall_score_redirect_url())) {
								sb.append("Url跳转地址,");
							}
						}
						sb.deleteCharAt(sb.length() - 1);
						sb.append("不能为空！！");
						list.add(new ErrorInfo(sb.toString()));
					}
					if (vo.getTypeList().contains(1L)) {
						StringBuilder sb = new StringBuilder("推荐墙:");
						if (vo.getWall_list_weight() != null) {
							sb.append("权重,");
						}
						if (vo.getWall_list_response_category() == 0) {
							if (vo.getWall_list_response_type() == null) {
								sb.append("下载方式,");
							}
						} else if (vo.getWall_list_response_category() == 1) {
							if (StringUtils.isEmpty(vo
									.getWall_list_redirect_url())) {
								sb.append("Url跳转地址,");
							}
						}
						sb.deleteCharAt(sb.length() - 1);
						sb.append("不能为空！！");
						list.add(new ErrorInfo(sb.toString()));
					}
					if (vo.getTypeList().contains(4L)) {
						StringBuilder sb = new StringBuilder("BANNER:");
						if (StringUtils.isEmpty(vo.getBeanner_img_url_first())
								&& StringUtils.isEmpty(vo
										.getBeanner_img_url_second())
								&& StringUtils.isEmpty(vo
										.getBeanner_img_url_third())) {
							sb.append("BANNER图片,");
						}
						if (vo.getBanner_weight() != null) {
							sb.append("权重,");
						}
						if (vo.getBanner_response_category() == 0) {
							if (vo.getBanner_response_type() == null) {
								sb.append("下载方式,");
							}
						} else if (vo.getBanner_response_category() == 1) {
							if (StringUtils
									.isEmpty(vo.getBanner_redirect_url())) {
								sb.append("Url跳转地址,");
							}
						}
						sb.deleteCharAt(sb.length() - 1);
						sb.append("不能为空！！");
						list.add(new ErrorInfo(sb.toString()));
					}

					if (vo.getTypeList().contains(5L)) {
						StringBuilder sb = new StringBuilder("插屏:");
						if (StringUtils.isEmpty(vo.getImg_horizontal())) {
							sb.append("横屏图片,");
						}
						if (StringUtils.isEmpty(vo.getImg_vertical())) {
							sb.append("竖屏图片,");
						}
						if (vo.getChartboost_weight() != null) {
							sb.append("权重,");
						}
						if (vo.getChartboost_response_category() == 0) {
							if (vo.getBanner_response_type() == null) {
								sb.append("下载方式,");
							}
						} else if (vo.getChartboost_response_category() == 1) {
							if (StringUtils.isEmpty(vo
									.getChartboost_redirect_url())) {
								sb.append("Url跳转地址,");
							}
						}
						sb.deleteCharAt(sb.length() - 1);
						sb.append("不能为空！！");
						list.add(new ErrorInfo(sb.toString()));
					}
				}
				List<AdVo> adList = adService.findByPlacement(placement_id);
				if (adList == null || adList.size() == 0) {
					list.add(new ErrorInfo("活动没有投放，请在方案配置里添加！！"));
				} else {
					for (AdVo adVo : adList) {
						boolean flag = false;
						StringBuilder sb = new StringBuilder();

						if (adVo.getBlance_mode() == null) {
							flag = true;
							sb.append("结算方式,");
						}

						if (adVo.getBlance_price() == null) {
							flag = true;
							sb.append("结算单价,");
						}

						if (adVo.getBudget_day() == null) {
							flag = true;
							sb.append("日投放量,");
						}

						if (adVo.getBudget_type() == null) {
							flag = true;
							sb.append("日投放限制类型,");
						}

						if (adVo.getPackage_id() == null) {
							flag = true;
							sb.append("广告包,");
						}
						if (flag) {
							sb.deleteCharAt(sb.length() - 1);
							sb.append("不能为空！！");
							list.add(new ErrorInfo("广告id:" + adVo.getId()
									+ sb.toString()));
						}

					}
				}

			}

		} else {
			list.add(new ErrorInfo("系统错误"));
		}

		return list;

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
	 * @throws Exception
	 */
	@RequestMapping("/manage!submitPlacement.do")
	public String submitPlacement(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model, Long id)
			throws Exception {
		ResultVo vo = null;
		placementService.submitPlacement(id);
		vo = new ResultSuccessVo();
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
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
	 * @throws Exception
	 */
	@RequestMapping("/manage!placementOffline.do")
	public String updateAdOffline(String ids, HttpServletResponse response)
			throws Exception {
		ResultVo vo = null;
		placementService.Offline(ids);
		vo = new ResultSuccessVo();
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

}
