package cn.adwalker.ad.admin.app.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.admin.app.bean.AppQuery;
import cn.adwalker.ad.admin.app.form.AppAddForm;
import cn.adwalker.ad.admin.app.form.AppEditForm;
import cn.adwalker.ad.admin.app.service.IApplicationService;
import cn.adwalker.ad.admin.app.service.IDeveloperService;
import cn.adwalker.ad.admin.app.vo.AppEditVo;
import cn.adwalker.ad.admin.app.vo.AppListVo;
import cn.adwalker.ad.admin.app.vo.ApplicationVo;
import cn.adwalker.ad.admin.app.vo.DevAppVo;
import cn.adwalker.ad.admin.common.controller.AbstractControllerParent;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.config.Constant;
import cn.adwalker.ad.config.StatusConstant;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.page.SetPage;
import cn.adwalker.core.pool.SpringDatePool;
import cn.adwalker.core.util.BeanUtils;
import cn.adwalker.core.util.ConfigUtil;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.model.app.domain.AppCurrency;
import cn.adwalker.model.app.domain.AppMedia;
import cn.adwalker.model.app.domain.Developer;
import cn.adwalker.model.common.dao.IPageDao;
import cn.adwalker.model.common.domain.SysCategory;

/**
 * <p>
 * Title: AppController
 * </p>
 * <p>
 * Description:开发者应用管理/p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-9-9
 */

@Controller
public class AppController extends AbstractControllerParent {
	private static final Logger loger = LoggerFactory.getLogger(AppController.class);

	@Resource
	private IApplicationService applicationService;

	@Resource
	private IDeveloperService developerService;

	@Resource
	private SpringDatePool springDatePool;

	@Resource
	private IPageDao pageDao;

	/**
	 * <p>
	 * Title: add
	 * </p>
	 * <p>
	 * Description:管理员为开发者添加应用
	 * </p>
	 * 
	 * @param session
	 * @param model
	 * @param dev_id
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-9-9
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("/manage!addApp.do")
	public String add(HttpSession session, Map<String, Object> model, Long dev_id) throws Exception {
		Developer userDeveloper = developerService.getById(dev_id);
		List<SysCategory> ecList = this.springDatePool.getSysCategory();
		model.put("dev", userDeveloper);
		model.put("ecList", ecList);
		return "media/app/app_add";
	}

	/**
	 * <p>
	 * Title: list
	 * </p>
	 * <p>
	 * Description:应用列表
	 * </p>
	 * 
	 * @param session
	 * @param request
	 * @param bean
	 * @param model
	 * @return
	 * @author lichuang
	 * @date 2013-9-9
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("manage!appList.do")
	public String list(HttpSession session, HttpServletRequest request, AppQuery bean, Map<String, Object> model) {
		try {
			IPageInfo pageInfo = new SetPage(request);
			List<AppListVo> list = this.applicationService.findByPage(pageInfo, bean);
			List<AppMedia> appMediaList = applicationService.getAppMediaList();
			model.put("appMediaList", appMediaList);
			model.put("pageInfo", pageInfo);
			model.put("bean", bean);
			request.setAttribute("devAppList", list);
		} catch (Exception e) {
			loger.error(e.toString());
		}
		return "media/app/app_list";

	}

	/**
	 * <p>
	 * Title: detailDevAppManager
	 * </p>
	 * <p>
	 * Description:应用详情
	 * </p>
	 * 
	 * @param session
	 * @param model
	 * @param appId
	 * @return
	 * @author lichuang
	 * @date 2013-9-9
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("appEidt.do")
	public String eidit(HttpSession session, Map<String, Object> model, Long appId) {
		try {
			List<SysCategory> ecList = this.springDatePool.getSysCategory();
			AppEditVo app = applicationService.getByAppForEdit(appId);
			if (app != null) {

				List<AppMedia> appMediaList = applicationService.getAppMediaList();
				model.put("appMediaList", appMediaList);
				model.put("ecList", ecList);
				model.put("resource_url_default", ConfigUtil.getString("resources.url.prefix"));
				model.put("resource_url_default_other", ConfigUtil.getString("resources.url.prefix_other"));
				model.put("vo", app);
			}

		} catch (Exception e) {
			loger.error(e.toString());
		}
		return "media/app/app_edit";
	}

	/**
	 * <p>
	 * Title: appInfo
	 * </p>
	 * <p>
	 * Description: 应用详情
	 * </p>
	 * 
	 * @param session
	 * @param model
	 * @param app_id
	 * @return
	 * @author lichuang
	 * @date 2013-9-9
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("manage!appInfo.do")
	public String appInfo(HttpSession session, Map<String, Object> model, Long app_id) {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		String fileUrl = null;
		try {
			List<SysCategory> ecList = this.springDatePool.getSysCategory();
			ApplicationVo app = applicationService.getById(app_id);
			if (app != null) {
				model.put("app", app);
				Developer userDeveloper = developerService.getById(app.getDev_id());
				model.put("dev", userDeveloper);
				model.put("devAppVo", BeanUtils.copyProperties(new DevAppVo(), app));
				// 下载应用
				fileUrl = app.getProjectUrl();
				List<String> pageTypeVos = applicationService.getTypeList(app.getId());
				StringBuilder sb = new StringBuilder();
				if (pageTypeVos != null && pageTypeVos.size() > 0) {
					for (String s : pageTypeVos) {
						sb.append(s + ",");
					}
					sb = sb.deleteCharAt(sb.length() - 1);
				}
				model.put("page_type", sb.toString());
			}

			AppCurrency appCurrency = applicationService.findCurrencyByAppId(app_id);
			model.put("appCurrency", appCurrency);
			model.put("ecList", ecList);
			model.put("fileUrl", fileUrl);
			model.put("opeId", manageUser.getId());// 管理员id
			model.put("eis", fileUrl);
			model.put("resource_url_default", ConfigUtil.getString("resources.url.prefix"));
			model.put("resource_url_default_other", ConfigUtil.getString("resources.url.prefix_other"));
		} catch (Exception e) {
			loger.error(e.toString());
		}
		return "media/app/app_info";
	}

	/**
	 * 
	 * <p>
	 * Title: appAudit
	 * </p>
	 * <p>
	 * Description:应用审核
	 * </p>
	 * 
	 * @param session
	 * @param model
	 * @param appId
	 * @return
	 * @author cuidd
	 * @date 2013-7-2
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("manage!appAudit.do")
	public String appAudit(HttpSession session, Map<String, Object> model, Long app_id) {
		String fileUrl = null;
		try {
			List<SysCategory> ecList = this.springDatePool.getSysCategory();
			ApplicationVo app = applicationService.getById(app_id);
			if (app != null) {
				model.put("app", app);
				Developer userDeveloper = developerService.getById(app.getDev_id());
				model.put("dev", userDeveloper);
				model.put("devAppVo", BeanUtils.copyProperties(new DevAppVo(), app));
				model.put("appStatus", StatusConstant.getAppStatusName(app.getStatus()));
				// 下载应用
				fileUrl = app.getProjectUrl();
				List<String> pageTypeVos = applicationService.getTypeList(app.getId());
				StringBuilder sb = new StringBuilder();
				if (pageTypeVos != null && pageTypeVos.size() > 0) {
					for (String s : pageTypeVos) {
						sb.append(s + ",");
					}
					sb = sb.deleteCharAt(sb.length() - 1);
				}
				model.put("page_type", sb.toString());
			}
			model.put("ecList", ecList);
			model.put("fileUrl", fileUrl);
			model.put("eis", fileUrl);
			model.put("resource_url_default", ConfigUtil.getString("resources.url.prefix"));
			model.put("resource_url_default_other", ConfigUtil.getString("resources.url.prefix_other"));
		} catch (Exception e) {
			loger.error(e.toString());
		}
		return "media/app/app_audit";
	}

	/**
	 * <p>
	 * Title: updateApp
	 * </p>
	 * <p>
	 * Description:执行修改应用
	 * </p>
	 * 
	 * @param session
	 * @param form
	 * @param response
	 * @param types
	 * @param model
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-9-9
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("updateApp.do")
	public String updateApp(HttpSession session, AppEditForm form, HttpServletResponse response, String types[], String ad_res[], Map<String, Object> model) throws Exception {
		try {
			manageUser = (SysUserVo) session.getAttribute("manageUser");
			applicationService.update(form, types, ad_res, manageUser);
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (Exception e) {
			loger.error(e.getLocalizedMessage());
		}
		return null;
	}

	/**
	 * <p>
	 * Title: saveApp
	 * </p>
	 * <p>
	 * Description:管理员为开发者添加应用-执行添加
	 * </p>
	 * 
	 * @param session
	 * @param model
	 * @param form
	 * @param developerId
	 * @param types
	 * @param request
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-9-9
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("manage!saveApp.do")
	public String saveApp(HttpSession session, HttpServletResponse response, Map<String, Object> model, AppAddForm form, Long developerId, String types[], HttpServletRequest request) throws Exception {
		loger.debug("into method saveDevAppManager: 管理员执行添加应用");
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		Developer dev = developerService.getById(developerId);
		// ====组装资源存放目录start====
		// ====组装资源存放目录end====
		// 必填信息验证
		if (!validate(model, form)) {
			model.put("app", form);
			model.put("dev", dev);
		}
		try {
			form.setDev_id(dev.getId());
			applicationService.insert(form, types, manageUser);
			OutputHelper.outPut(response, "0");
		} catch (Exception e) {
			e.printStackTrace();
			OutputHelper.outPut(response, "1");
		}
		return null;
	}

	/**
	 * <p>
	 * Title: validate
	 * </p>
	 * <p>
	 * Description:验证应用信息
	 * </p>
	 * 
	 * @param model
	 * @param form
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-9-9
	 * @return boolean
	 * @version 1.0
	 */
	private boolean validate(Map<String, Object> model, AppAddForm form) throws Exception {
		boolean falg = true;
		if (ObjectUtils.isNotEmpty(form)) {
			if (ObjectUtils.isEmpty(form.getName())) {// 应用名称非空验证
				model.put("errMsg_appName", "应用名称不能为空！");
				falg = false;
			}
			if (ObjectUtils.isEmpty(form.getLongDesc())) {// 详细描述非空验证
				model.put("errMsg_appLongDesc", "详细描述不能为空！");
				falg = false;
			}
			return falg;
		}
		return falg;
	}

	/**
	 * <p>
	 * Title: auditApp
	 * </p>
	 * <p>
	 * Description:审核应用
	 * </p>
	 * 
	 * @param session
	 * @param app_id
	 * @param model
	 * @param response
	 * @param ispass
	 * @param reason
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-9-9
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("manage!auditApp.do")
	public String auditApp(HttpSession session, Long app_id, Map<String, Object> model, HttpServletResponse response, boolean ispass, String reason) throws Exception {
		manageUser = (SysUserVo) session.getAttribute("manageUser");
		try {
			applicationService.audit(app_id, ispass, reason);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResultVo vo = new ResultSuccessVo();
		OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		return null;
	}

	/**
	 * <p>
	 * Title: applyDevAppManager
	 * </p>
	 * <p>
	 * Description:审核应用
	 * </p>
	 * 
	 * @param session
	 * @param appId
	 * @param status
	 * @param reason
	 * @param model
	 * @param response
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-9-9
	 * @return String
	 * @version 1.0
	 */
	@RequestMapping("manage!auditDevApp.do")
	public String applyDevAppManager(HttpSession session, Long appId, Integer status, String reason, Map<String, Object> model, HttpServletResponse response) throws Exception {
		try {
			applicationService.updateAppStatus(appId, status);
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reason;
	}

	@RequestMapping("appOline.do")
	public String appOline(HttpSession session, Long appId, Integer status, String reason, Map<String, Object> model, HttpServletResponse response) throws Exception {
		try {
			applicationService.updateAppStatus(appId, Constant.APP_STATUS_ON_LINE);
			ResultVo vo = new ResultSuccessVo();
			OutputHelper.outPut(response, JacksonMapper.objectToJsonString(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reason;
	}
}
