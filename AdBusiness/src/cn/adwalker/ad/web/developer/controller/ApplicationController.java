package cn.adwalker.ad.web.developer.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.config.Role;
import cn.adwalker.ad.core.page.IPageInfo;
import cn.adwalker.ad.core.page.SetPage;
import cn.adwalker.ad.log.service.AdwalkerLogger;
import cn.adwalker.ad.model.application.dao.PageDao;
import cn.adwalker.ad.model.application.domain.Application;
import cn.adwalker.ad.model.application.domain.Page;
import cn.adwalker.ad.model.common.domain.EJumpType;
import cn.adwalker.ad.model.common.domain.EscoreCategory;
import cn.adwalker.ad.util.ConfigUtil;
import cn.adwalker.ad.util.Function;
import cn.adwalker.ad.util.JacksonMapper;
import cn.adwalker.ad.util.ObjectUtils;
import cn.adwalker.ad.util.OutputHelper;
import cn.adwalker.ad.util.StatusConstant;
import cn.adwalker.ad.util.page.PageUtil;
import cn.adwalker.ad.web.common.controller.AbstractControllerParent;
import cn.adwalker.ad.web.common.vo.ResultErrorVo;
import cn.adwalker.ad.web.common.vo.ResultSuccessVo;
import cn.adwalker.ad.web.common.vo.ResultVo;
import cn.adwalker.ad.web.developer.form.AppBaseInfo;
import cn.adwalker.ad.web.developer.form.AppEditForm;
import cn.adwalker.ad.web.developer.service.ApplicationService;
import cn.adwalker.ad.web.developer.service.CategoryService;
import cn.adwalker.ad.web.developer.service.DeveloperService;
import cn.adwalker.ad.web.developer.vo.ApplicationVo;
import cn.adwalker.ad.web.developer.vo.DevAppVo;

/**
 * 功能概述：<br>
 * 开发者SDK应用控制层
 */
@Controller
public class ApplicationController extends AbstractControllerParent {

	private static final Logger log = LoggerFactory.getLogger(ApplicationController.class);

	/**
	 * 应用服务组件
	 */
	@Resource
	private ApplicationService applicationService;

	/**
	 * 开发者服务组件
	 */
	@Resource
	private DeveloperService developerService;

	/**
	 * 系统分类组件
	 */
	@Resource
	private CategoryService categoryService;

	/**
	 * 墙组件
	 */
	@Resource
	private PageDao pageDao;

	/**
	 * 查询应用列表
	 * 
	 * @return
	 */
	@RequestMapping("applicationList.action")
	public String devAppList(Map<String, Object> model, HttpServletRequest request) throws Exception {
		log.debug("into method devAppList: 查询应用列表");
		try {
			Integer status = Function.getInt(request, "status", -100);
			IPageInfo pageInfo = new SetPage(request);
			List<ApplicationVo> list = applicationService.findByPage(super.getUserDeveloper().getId(), pageInfo, status);
			if (list != null && list.size() > 0) {
				for (ApplicationVo app : list) {
					app.setTypeList(pageDao.findByApplicationId(app.getId()));
				}
			}
			model.put("pageInfo", PageUtil.fenyeNew(pageInfo));
			model.put("total", pageInfo.getTotalRow());
			model.put("applist", list);
			model.put("status", status);
			AdwalkerLogger.log(super.getUserDeveloper().getId(), Role.DEVELOPER, "/applicationList.action", null, "获取应用列表");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("applicationList:" + e.getMessage());
			AdwalkerLogger.error(super.getUserDeveloper().getId(), Role.DEVELOPER, "/applicationList.action", null, e.getMessage());
		}
		return "developer/application_list";
	}

	/**
	 * to修改应用
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/toUpdateApplication.action")
	public String toUpdateDevApp(HttpServletRequest req, Map<String, Object> model, Long id) throws Exception {
		log.debug("into method toUpdateDevApp: to修改应用");
		DevAppVo app = null;
		String fileUrl = null;
		try {
			app = applicationService.getByDeVId(id, super.getUserDeveloper().getId());
			List<Page> pageList = pageDao.findByApplication(app.getId());
			List<EscoreCategory> ecList = this.categoryService.findECList(4);
			// 下载应用
			if (ObjectUtils.isEmpty(app.getProjectUrl())) {
				fileUrl = null;
			} else {
				fileUrl = ConfigUtil.getString("resources.url.prefix") + app.getProjectUrl();
			}
			app.getAppKey();
			model.put("app", app);
			model.put("fileUrl", fileUrl);
			model.put("ecList", ecList);
			model.put("pageList", pageList);
			model.put("dev", super.getUserDeveloper());

		} catch (Exception e) {
			log.error("toUpdateDevApp:" + e.getMessage());
			AdwalkerLogger.error(super.getUserDeveloper().getId(), Role.DEVELOPER, "/toUpdateApplication.action", "appid:" + id, e.getMessage());
		}
		AdwalkerLogger.log(super.getUserDeveloper().getId(), Role.DEVELOPER, "/toUpdateApplication.action", "appid:" + id, "to修改应用");
		return "developer/application_edit";
	}
	
	/**
	 * to修改应用
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/app_info.action")
	public String info(HttpServletRequest req, Map<String, Object> model, Long id) throws Exception {
		DevAppVo app = null;
		String fileUrl = null;
		try {
			app = applicationService.getByDeVId(id, super.getUserDeveloper().getId());
			List<Page> pageList = pageDao.findByApplication(app.getId());
			List<EscoreCategory> ecList = this.categoryService.findECList(4);
			// 下载应用
			if (ObjectUtils.isEmpty(app.getProjectUrl())) {
				fileUrl = null;
			} else {
				fileUrl = ConfigUtil.getString("resources.url.prefix") + app.getProjectUrl();
			}
			app.getAppKey();
			model.put("app", app);
			model.put("fileUrl", fileUrl);
			model.put("ecList", ecList);
			model.put("pageList", pageList);
			model.put("dev", super.getUserDeveloper());

		} catch (Exception e) {
			log.error("toUpdateDevApp:" + e.getMessage());
			AdwalkerLogger.error(super.getUserDeveloper().getId(), Role.DEVELOPER, "/toUpdateApplication.action", "appid:" + id, e.getMessage());
		}
		return "developer/application_info";
	}

	/**
	 * 执行修改应用
	 * 
	 * @param developedApp
	 * @return
	 */
	@RequestMapping("/updateApplication.action")
	public String updateDevApp(HttpServletRequest request, HttpServletResponse res, Map<String, Object> model, AppEditForm form) throws Exception {
		log.debug("into method updateDevApp: 执行修改应用");
		ResultVo vo = null;
		try {
			applicationService.updateApp(form);
			AdwalkerLogger.log(super.getUserDeveloper().getId(), Role.DEVELOPER, "/updateApplication.action", JacksonMapper.objectToJsonString(form), "执行修改应用");
			vo = new ResultSuccessVo();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("updateDevApp:" + e.getMessage());
			AdwalkerLogger.error(super.getUserDeveloper().getId(), Role.DEVELOPER, "/updateApplication.action", JacksonMapper.objectToJsonString(form), e.getMessage());
			vo = new ResultErrorVo("修改应用失败！！");
		}
		OutputHelper.outPut(res, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * 进入添加应用
	 * 
	 * @param developedApp
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toAddApplication1.action")
	public String toAddDevApp1(HttpServletRequest request, Map<String, Object> model) throws Exception {
		Long appId = Function.getLong(request, "appId", -1);
		List<EscoreCategory> ecList = this.categoryService.findECList(4);
		if (appId != -1) {
			DevAppVo vo = applicationService.getById(appId);
			model.put("vo", vo);
			List<Page> pageList = pageDao.findByApplication(appId);
			model.put("pageList", pageList);
		}
		model.put("dev", super.getUserDeveloper());
		log.debug("into method addDevApp: 进入添加应用");
		model.put("ecList", ecList);
		return "developer/application_add1";
	}

	/**
	 * 添加应用1
	 * 
	 * @param developedApp
	 * @return
	 */
	@RequestMapping("/addApplication1.action")
	public String addDevApp1(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model, AppBaseInfo vo) throws Exception {
		log.debug("into method addDevApp: 新增应用1");
		Long appId = vo.getId();
		try {
			if (appId == null || appId == 0) {
				appId = applicationService.insert(vo, super.getUserDeveloper().getId());
			} else {
				applicationService.update(vo, super.getUserDeveloper().getId());
			}
			AdwalkerLogger.log(super.getUserDeveloper().getId(), Role.DEVELOPER, "/addApplication1.action", JacksonMapper.objectToJsonString(vo), "添加应用");
			response.sendRedirect("toAddApplication2.action?appId=" + appId);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("addApplication1:" + e.getMessage());
			AdwalkerLogger.error(super.getUserDeveloper().getId(), Role.DEVELOPER, "/addApplication1.action", JacksonMapper.objectToJsonString(vo), e.getMessage());
			return super.jumpPage(model, "添加应用失败！", "/toAddApplication1.action?appId=" + appId, EJumpType.FAIL.getValue());
		}
	}

	@RequestMapping("/toAddApplication2.action")
	public String toAddDevApp2(Long appId, Map<String, Object> model) throws Exception {
		DevAppVo vo = applicationService.getById(appId);
		List<Page> pageList = pageDao.findByApplication(appId);
		String pageIds = "";
		for (Page page : pageList) {
			pageIds += page.getType_id() + ",";
		}
		if (pageIds.lastIndexOf(",") == pageIds.length() - 1) {
			pageIds = pageIds.substring(0, pageIds.length() - 1);
		}
		model.put("dev", super.getUserDeveloper());
		model.put("vo", vo);
		model.put("pageIds", pageIds);
		log.debug("into method addDevApp: 进入添加应用2");
		return "developer/application_add2";
	}

	@RequestMapping("toAddApplication3.action")
	public String toAddDevApp3(Long appId, Map<String, Object> model) throws Exception {
		log.debug("into method toAddDevApp3: to添加应用3");
		DevAppVo app = applicationService.getById(appId);
		try {
			String fileUrl = null;
			// 下载应用
			if (ObjectUtils.isEmpty(app.getProjectUrl())) {
				fileUrl = null;
			} else {
				fileUrl = ConfigUtil.getString("resources.url.prefix") + app.getProjectUrl();
			}
			List<Page> pageList = pageDao.findByApplication(appId);
			String pageNames = "";
			for (Page page : pageList) {
				pageNames += page.getType_name() + " ";
			}
			model.put("fileUrl", fileUrl);
			model.put("pageNames", pageNames);
			model.put("vo", app);
			model.put("dev", super.getUserDeveloper());
		} catch (Exception e) {
			log.error("toAddApplication3:" + e.getMessage());
			AdwalkerLogger.error(super.getUserDeveloper().getId(), Role.DEVELOPER, "/toAddApplication3.action", "appid:" + appId, e.getMessage());
		}
		AdwalkerLogger.log(super.getUserDeveloper().getId(), Role.DEVELOPER, "/toAddApplication3.action?appId=" + appId, "appid:" + appId, "to添加应用3");
		return "developer/application_add3";
	}

	/**
	 * 添加应用
	 * 
	 * @param developedApp
	 * @return
	 */
	@RequestMapping("/addApplication3.action")
	public String addDevApp3(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model, AppBaseInfo vo) throws Exception {
		log.debug("into method addApplication3: 新增应用3");
		try {
			vo.setStatus(StatusConstant.STATUS_UNCHECK);
			applicationService.update(vo);
			AdwalkerLogger.log(super.getUserDeveloper().getId(), Role.DEVELOPER, "/addApplication3.action", JacksonMapper.objectToJsonString(vo), "添加应用");
			response.sendRedirect("applicationList.action");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("addDevApp:" + e.getMessage());
			AdwalkerLogger.error(super.getUserDeveloper().getId(), Role.DEVELOPER, "/addApplication3.action", JacksonMapper.objectToJsonString(vo), e.getMessage());
			return super.jumpPage(model, "添加应用失败！", "/toAddApplication3.action?appId=" + vo.getId(), EJumpType.FAIL.getValue());
		}
	}

	/**
	 * 
	 * <p>
	 * Title: getAndroidSDKUri
	 * </p>
	 * 
	 * @param ids
	 * @param response
	 * @return
	 */
	@RequestMapping("getAndroidSDKUri.action")
	public String getAndroidSDKUri(String ids, HttpServletResponse response) {
		String s = null;
		s = "xingyun-android-sdk.zip";
		OutputHelper.outPut(response, s);
		return null;
	}

	@RequestMapping("toDownloadSDK.action")
	public String toDownloadSDK(HttpServletRequest request, HttpServletResponse response) {
		return "developer/sdk_download";
	}

	@RequestMapping("pauseApplication.action")
	public String pauseApp(HttpServletRequest request, HttpServletResponse response, Long appId) throws Exception {
		AppBaseInfo info = new AppBaseInfo();
		info.setId(appId);
		info.setStatus(StatusConstant.STATUS_PAUSE);
		applicationService.update(info);
		return "redirect:applicationList.action";
	}

	@RequestMapping("onlineApplication.action")
	public String onlineApp(HttpServletRequest request, HttpServletResponse response, Long appId) throws Exception {
		AppBaseInfo info = new AppBaseInfo();
		info.setId(appId);
		info.setStatus(StatusConstant.STATUS_ONLINE);
		applicationService.update(info);
		return "redirect:applicationList.action";
	}

	@RequestMapping("deleteApplication.action")
	public String deleteApp(HttpServletRequest request, HttpServletResponse response, Long appId) throws Exception {
		AppBaseInfo info = new AppBaseInfo();
		info.setId(appId);
		info.setStatus(StatusConstant.STATUS_STOP);
		applicationService.update(info);
		return "redirect:applicationList.action";
	}

	/**
	 * 
	 * <p>
	 * Title: 获取应用屏蔽广告分类
	 * </p>
	 * 
	 * @param ids
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getCategoryIds.action")
	public String getCategoryIds(Long appId, HttpServletResponse response) throws Exception {
		String categoryIds = applicationService.findCategoryIdsByAppId(appId);
		OutputHelper.outPut(response, categoryIds);
		return null;
	}

	/**
	 * <p>
	 * Title: adSeting
	 * </p>
	 * <p>
	 * Description:获取应用屏蔽广告分类
	 * </p>
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/adSeting.action")
	public String adSeting(HttpServletRequest request, Map<String, Object> model) throws Exception {
		List<Application> appList = applicationService.findByDeveloper(getUserDeveloper().getId());
		List<EscoreCategory> ecList = this.categoryService.findECList(7);
		model.put("appList", appList);
		model.put("ecList", ecList);
		return "developer/ad_setting";
	}

	/**
	 * <p>
	 * Title: updateAdSeting
	 * </p>
	 * <p>
	 * Description:保存获取应用屏蔽广告分类
	 * </p>
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateAdSeting.action")
	public String updateAdSeting(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) throws Exception {
		Long appId = Function.getLong(request, "appId", 0);
		String categoryIds = Function.getString(request, "categoryIds", "");
		// 修改
		try {
			if (appId != 0) {
				applicationService.updateApplicationCategory(appId, categoryIds);
				OutputHelper.outPut(response, "true");
			}
		} catch (Exception e) {
			e.printStackTrace();
			OutputHelper.outPut(response, "false");
		}
		return null;
	}

}
