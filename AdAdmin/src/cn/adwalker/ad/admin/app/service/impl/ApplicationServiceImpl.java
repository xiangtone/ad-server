package cn.adwalker.ad.admin.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import cn.adwalker.ad.admin.app.bean.AppQuery;
import cn.adwalker.ad.admin.app.form.AppAddForm;
import cn.adwalker.ad.admin.app.form.AppEditForm;
import cn.adwalker.ad.admin.app.service.IApplicationService;
import cn.adwalker.ad.admin.app.vo.AppEditVo;
import cn.adwalker.ad.admin.app.vo.AppListVo;
import cn.adwalker.ad.admin.app.vo.ApplicationVo;
import cn.adwalker.ad.admin.app.vo.VApplication;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.config.AppConstant;
import cn.adwalker.ad.config.StatusConstant;
import cn.adwalker.ad.task.SendMailTask;
import cn.adwalker.core.log.Log;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.pool.ThreadPoolUtil;
import cn.adwalker.core.spring.AppContext;
import cn.adwalker.core.util.CacheUtils;
import cn.adwalker.core.util.DateUtil;
import cn.adwalker.core.util.lang.MathUtil;
import cn.adwalker.core.util.lang.StringUtil;
import cn.adwalker.model.app.dao.IAppCurrencyDao;
import cn.adwalker.model.app.dao.IAppMediaDao;
import cn.adwalker.model.app.dao.IApplicationDao;
import cn.adwalker.model.app.dao.IDeveloperDao;
import cn.adwalker.model.app.dao.impl.CoopertiomConfigDao;
import cn.adwalker.model.app.domain.AppCurrency;
import cn.adwalker.model.app.domain.AppMedia;
import cn.adwalker.model.app.domain.Application;
import cn.adwalker.model.app.domain.Developer;
import cn.adwalker.model.app.domain.Page;
import cn.adwalker.model.common.dao.IClusterAdConfigDao;
import cn.adwalker.model.common.dao.IPageDao;
import cn.adwalker.model.config.domain.CooperationConfig;
import cn.adwalker.model.news.domain.Mail;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 * <p>
 * Title: ApplicationServiceImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2014-8-26
 */
@Service(value = "applicationService")
public class ApplicationServiceImpl implements IApplicationService {

	@Resource
	private IApplicationDao applicationDao;

	@Resource
	private IDeveloperDao developerDao;

	@Resource
	private IPageDao pageDao;

	@Resource
	private IAppCurrencyDao appCurrencyDao;

	@Resource
	private IAppMediaDao appMediaDao;
	
	@Resource
	private IClusterAdConfigDao clusterAdConfigDao;

	@Resource
	private CoopertiomConfigDao cooperConfigDao;

	/**
	 * 
	 * @see cn.cn.adwalker.ad.admin.report.service.IReportAdByHourService.dev.service.IApplicationService#findByUser(int)
	 */
	@Override
	public List<Application> findByUser(Long id) throws Exception {
		List<Application> developedApp = new ArrayList<Application>();
		developedApp = this.applicationDao.findByUser(id);
		return developedApp;
	}

	/**
	 * 
	 * @see cn.cn.adwalker.ad.admin.report.service.IReportAdByHourService.dev.service.IApplicationService#findById(java.lang.Long)
	 */
	@Override
	public Application get(Long id) throws Exception {
		return this.getApplication(id);

	}

	/**
	 * 
	 * <p>
	 * Title: getApplication
	 * </p>
	 * <p>
	 * Description:获取应用信息
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-7-2
	 * @return Application
	 * @version 1.0
	 */
	private Application getApplication(Long id) throws Exception {
		Application entity = null;
		if (id != null) {
			entity = this.findById(id);
		}

		return entity;

	}

	/**
	 * @throws Exception
	 * @see cn.adwalker.model.app.dao.IApplicationDao#findById(int)
	 */

	@SuppressWarnings("unchecked")
	private Application findById(Long id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from T_APPLICATION where id = ? ");
		List<Application> objects = (List<Application>) applicationDao.findAll(
				sql.toString(), new Object[] { id }, Application.class);
		Application developedApp = null;
		if (objects != null && objects.size() > 0) {
			developedApp = objects.get(0);
			return developedApp;
		}
		return null;
	}

	/**
	 * 
	 * @see cn.cn.adwalker.ad.admin.report.service.IReportAdByHourService.dev.service.IApplicationService#insert(cn.adwalker.model.app.domain.Application)
	 */
	@Override
	public Long insert(AppAddForm form, String types[], SysUserVo user) throws Exception {
		// ====上传应用并解包获取生成路径start====
		Application entity = new Application();
		entity.setLastUpdateMan(user.getId());
		entity.setDev_id(form.getDev_id());
		entity.setType_id(form.getType_id());
		entity.setName(form.getName());
		entity.setOs(form.getOs());
		entity.setAppkey("AW" + MathUtil.getRandomCode(AppConstant.CODE_COUNT));// appkey
		entity.setStatus(StatusConstant.APP_STATUS_FOR_AUDTI);// 待审核
		entity.setCreateTime(new Date());
		entity.setUpdateTime(new Date());
		entity.setLongDesc(form.getLongDesc());
		entity.setCategoryId(form.getCategoryId());
		entity.setKeyword(form.getKeyword());
		entity.setPlacement("0");
		entity.setStatus(StatusConstant.APP_STATUS_FOR_AUDTI);
		entity.setApp_res(0);
		entity.setApp_manager("平台");
		entity.setScale(1d);
		Long app_id = this.applicationDao.insert(entity);
		pageDao.saveOrUpdate(app_id, types);
		
		AppCurrency currency = new AppCurrency();
		currency.setApp_id(app_id);
		if (!StringUtil.isEmpty(form.getExchange_rate_rmb()) && NumberUtils.isNumber(form.getExchange_rate_rmb())) {
			currency.setExchange_rate_rmb(form.getExchange_rate_rmb());
		} else {
			currency.setExchange_rate_rmb(String.valueOf(100));
		}
		if (!StringUtil.isEmpty(form.getVirtual_currency_name())) {
			currency.setVirtual_currency_name("积分");
		}
		currency.setStatus(1);
		appCurrencyDao.saveOrUpdate(currency);
		//积分回调接口
		if(!StringUtil.isEmpty(form.getResponse_url())){
			CooperationConfig cc= new CooperationConfig();
			cc.setApp_id(app_id);
			cc.setResponse_url(form.getResponse_url());
			cc.setCreate_time(DateUtil.format(new Date()));
			cooperConfigDao.saveConfig(cc);
		}
		return app_id;
	}

	/**
	 * 
	 * @see cn.cn.adwalker.ad.admin.report.service.IReportAdByHourService.dev.service.IApplicationService#update(cn.adwalker.model.app.domain.Application)
	 */
	@Override
	public void update(AppEditForm form, String[] pageType,String[] ad_res, SysUserVo user)
			throws Exception {
		if (form != null) {
			Application entity = this.findById(form.getId());
			entity.setUpdateTime(new Date());
			entity.setName(form.getName());
			entity.setUpdateTime(new Date());
			entity.setKeyword(form.getKeyword());
			entity.setLongDesc(form.getLongDesc());
			entity.setCategoryId(form.getCategoryId());
			entity.setOs(form.getOs());
			entity.setPlacement(form.getPlacement());
			entity.setIs_coordinate(form.getIs_coordinate());
			entity.setApp_manager_id(form.getApp_manager_id());
			if (form.getScale() != null) {
				entity.setScale(Double.valueOf(form.getScale()) / 100);
			} else {
				entity.setScale(null);
			}

			AppMedia appMedia = this.getAppMedia(form.getApp_manager_id());
			if (appMedia != null) {
				entity.setApp_res(appMedia.getArea_type());
			}

			// ====上传应用并解包获取生成路径start====
			// entity.setStatus(StatusConstant.APP_STATUS_FOR_AUDTI);//
			// 状态置为待审核---应用修改后不会再成为待审核状态
			pageDao.saveOrUpdate(entity.getId(), pageType);
			clusterAdConfigDao.saveOrUpdate(entity.getId(), ad_res);
			this.applicationDao.update(entity);
			CacheUtils.updateAppCache(entity.getId());

		}

	}

	private AppMedia getAppMedia(Long id) throws Exception {
		AppMedia entity = null;
		if (id != null) {
			entity = appMediaDao.get(id, AppMedia.class);
		}
		return entity;

	}

	/**
	 * 
	 * @see cn.cn.adwalker.ad.admin.report.service.IReportAdByHourService.dev.service.IApplicationService#update(cn.adwalker.model.app.domain.Application,
	 *      java.util.List)
	 */
	@Override
	public void update(Application app) throws Exception {
		app.setUpdateTime(new Date());
		CacheUtils.updateAppCache(app.getId());
		this.applicationDao.update(app);

	}

	/**
	 * 
	 * @see cn.cn.adwalker.ad.admin.report.service.IReportAdByHourService.dev.service.IApplicationService#update(cn.adwalker.model.app.domain.Application,
	 *      java.util.List)
	 */
	@Override
	public void updateAppApkInf(Application developedApp) throws Exception {
		developedApp.setUpdateTime(new Date());
		this.applicationDao.update(developedApp);

	}

	@Override
	public Long updateByDevId(Application developedApp) throws Exception {
		return this.applicationDao.updateByDevId(developedApp);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findByPage
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.cn.adwalker.ad.admin.report.service.IReportAdByHourService.dev.service.IApplicationService#findByPage(cn.adwalker.base.dao.bean.PageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	@Log
	public List<AppListVo> findByPage(IPageInfo pageInfo, AppQuery bean)
			throws Exception {
		List<AppListVo> resultList = null;
		StringBuilder sb = new StringBuilder(" where  1=1");
		List<Object> param = new ArrayList<Object>();
		if (bean != null) {
			if (!StringUtils.isEmpty(bean.getApp())) {
				sb.append(" and (upper(v.name) like ? or v.id=?)");
				param.add("%" + bean.getApp_name().trim().toUpperCase() + "%");
				param.add(bean.getApp_id());
			}
			if (!StringUtils.isEmpty(bean.getDev())) {
				sb.append(" and (upper(v.dev_email) like ? or v.dev_id=? )");
				param.add("%" + bean.getDev_email().trim().toUpperCase() + "%");
				param.add(bean.getDev_id());
			}

			if (!StringUtils.isEmpty(bean.getOs())) {
				sb.append(" and v.os = ?");
				param.add(bean.getOs().trim());
			}
			if (bean.getApp_manager_id()!=null) {
				sb.append(" and v.app_manager_id=?");
				param.add(bean.getApp_manager_id());
			}
			if (!StringUtils.isEmpty(bean.getStartTime())) {
				sb.append(" and date_format(v.release_time,'%Y-%m-%d')>=?");
				param.add(bean.getStartTime());
			}
			if (!StringUtils.isEmpty(bean.getEndTime())) {
				sb.append(" and date_format(v.release_time,'%Y-%m-%d')<=?");
				param.add(bean.getEndTime());
			}
			if (bean.getStatus() != null) {
				sb.append(" and v.status=?");
				param.add(bean.getStatus());
			}

			if (bean.getApp_res() != null) {
				sb.append(" and v.app_res=?");
				param.add(bean.getApp_res());
			}
		}
		resultList = (List<AppListVo>) applicationDao.findByPage(
				"v.*,m.name app_manager_name",
				"V_APP_DEV v left join  t_app_media m on v.app_manager_id=m.id"
						+ sb.toString(), param.toArray(),
				"order by UPDATE_TIME desc", AppListVo.class, pageInfo);

		if (resultList != null && resultList.size() > 0) {
			for (AppListVo developedApp : resultList) {
				List<String> wallList = pageDao.getPageByAppId(developedApp
						.getId());
				developedApp.setPageTypeVos(wallList);
			}
		}
		return resultList;
	}

	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<String> getTypeList(Long app_id) {
		List<String> wallList = pageDao.getPageByAppId(app_id);
		return wallList;

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getById
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.cn.adwalker.ad.admin.report.service.IReportAdByHourService.dev.service.IApplicationService#getById(java.lang.Long)
	 */
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public ApplicationVo getById(Long id) throws Exception {
		ApplicationVo vo = null;
		Application entity = this.findById(id);
		if (entity != null) {
			vo = new ApplicationVo();
			vo.setReleaseTime(entity.getReleaseTime());
			vo.setId(entity.getId());
			vo.setDev_id(entity.getDev_id());
			vo.setAppkey(entity.getAppkey());
			vo.setName(entity.getName());
			vo.setKeyword(entity.getKeyword());
			vo.setLongDesc(entity.getLongDesc());
			vo.setCategoryId(entity.getCategoryId());
			vo.setPlacement(entity.getPlacement());
			vo.setApp_manager(entity.getApp_manager());
			vo.setIs_coordinate(entity.getIs_coordinate());
			Developer developer = developerDao.getById(vo.getDev_id());
			if (developer != null) {
				vo.setDevEmail(developer.getEmail());
				vo.setDevName(developer.getRealName());
			}
			vo.setUpdateTime(entity.getUpdateTime());
			vo.setCreateTime(entity.getCreateTime());
			vo.setProjectUrl(entity.getProjectUrl());
			vo.setOs(entity.getOs());
			if (entity.getScale() != null) {
				vo.setScale(((Double) (entity.getScale() * 100)).intValue());
			}
			vo.setStatus(entity.getStatus());

		}

		return vo;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findByPage
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param developerId
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.cn.adwalker.ad.admin.report.service.IReportAdByHourService.dev.service.IApplicationService#findByPage(java.lang.Long,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@Transactional(propagation = Propagation.NEVER)
	@SuppressWarnings("unchecked")
	@Override
	public List<VApplication> findByPage(Long developerId, IPageInfo pageInfo)
			throws Exception {
		return (List<VApplication>) applicationDao.findByPage("*",
				"V_APP_DEV  where dev_id=?", new Object[] { developerId },
				"order by CREATE_TIME desc", VApplication.class, pageInfo);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updatePrice
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param rate
	 * @see cn.cn.adwalker.ad.admin.report.service.IReportAdByHourService.dev.service.IApplicationService#updatePrice(java.lang.Long,
	 *      java.lang.Double)
	 */
	@Override
	public void updateAppRate(Long id, Double rate) {
		applicationDao.updateAppRate(id, rate);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: audit
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param ispass
	 * @param reason
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.app.service.IApplicationService#audit(boolean,
	 *      java.lang.String)
	 */
	@Override
	public Application audit(Long appId, boolean ispass, String reason)
			throws Exception {
		Application entity = this.getApplication(appId);
		if (entity != null) {
			CacheUtils.updateAppCache(entity.getId());
			if (ispass) {// 通过
				entity.setStatus(StatusConstant.APP_STATUS_ONLINE);// 通过直接上线
				entity.setReleaseTime(new Date());// 记录审核时间
				if (entity.getScale() == null) {
					entity.setScale(1.0);
				}
			} else {
				if (!StringUtils.isEmpty(reason)) {
					entity.setCheckMsg(reason);
					entity.setStatus(StatusConstant.APP_STATUS_NOTPASS);
				}

			}
			if (ispass) {// 通过
				sendSuccess(appId, entity.getName());
			} else {
				if (!StringUtils.isEmpty(reason)) {
					sendError(appId, entity.getName(), reason);
				}

			}

			applicationDao.update(entity);
		}
		return entity;

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateAppStatus
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param appId
	 * @param status
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.app.service.IApplicationService#updateAppStatus(java.lang.Long,
	 *      java.lang.String)
	 */
	@Override
	public void updateAppStatus(Long appId, Integer status) throws Exception {
		Application entity = this.getApplication(appId);
		if (entity != null) {
			if (status == StatusConstant.APP_STATUS_ONLINE) {// 上线
				entity.setReleaseTime(new Date());
			}
			if (status == StatusConstant.APP_STATUS_OFFLINE) {// 下线
				entity.setDown_time(new Date());// 下线时间
			}
			entity.setStatus(status);
			applicationDao.update(entity);
			CacheUtils.updateAppCache(entity.getId());
		}
	}

	@SuppressWarnings("unchecked")
	private Developer getDevByAppId(Long app_id) throws Exception {
		Developer dev = null;
		if (app_id != null) {
			List<Developer> list = (List<Developer>) developerDao
					.findAll(
							"select dev.* from T_APPLICATION app left join T_DEVELOPER dev on app.dev_id=dev.id where app.id="
									+ app_id, Developer.class);
			if (list != null && list.size() > 0) {
				dev = list.get(0);

			}
		}
		return dev;

	}

	private String devEmail(Long app_id) throws Exception {
		String s = null;
		if (app_id != null) {
			Developer dev = getDevByAppId(app_id);
			if (dev != null) {
				s = dev.getEmail();
			}
		}
		return s;

	}

	private void sendSuccess(Long app_id, String app_name) throws Exception {
		ApplicationContext ctx = AppContext.getApplicationContext();
		Configuration freadwalkerkerConfig = (Configuration) ctx
				.getBean("freemarkerConfigurer");
		Template temp = freadwalkerkerConfig
				.getTemplate("/template/email/app_audit_success.ftl");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("date", DateUtil.formatDate(new Date(), "yyyy年MM月dd日"));
		map.put("app_name", app_name);
		String s = FreeMarkerTemplateUtils.processTemplateIntoString(temp, map);
		String email = devEmail(app_id);
		ThreadPoolExecutor threadPool = ThreadPoolUtil.getThreadPool();
		Mail vo = new Mail();
		vo.setTitle("SDK植入审核结果");
		vo.setEmail(email);
		vo.setContent(s);
		threadPool.execute(new SendMailTask(vo, email));
	}

	private void sendError(Long app_id, String app_name, String refuse_reason)
			throws Exception {
		ApplicationContext ctx = AppContext.getApplicationContext();
		Configuration freadwalkerkerConfig = (Configuration) ctx
				.getBean("freemarkerConfigurer");
		Template temp = freadwalkerkerConfig
				.getTemplate("/template/email/app_audit_error.ftl");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("date", DateUtil.formatDate(new Date(), "yyyy年MM月dd日"));
		map.put("app_name", app_name);
		map.put("refuse_reason", refuse_reason);
		String s = FreeMarkerTemplateUtils.processTemplateIntoString(temp, map);
		String email = devEmail(app_id);
		ThreadPoolExecutor threadPool = ThreadPoolUtil.getThreadPool();
		Mail vo = new Mail();
		vo.setTitle("SDK植入审核结果");
		vo.setEmail(email);
		vo.setContent(s);
		threadPool.execute(new SendMailTask(vo, email));
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findCurrencyByAppId
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param app_id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.app.service.IApplicationService#findCurrencyByAppId(java.lang.Long)
	 */
	@Override
	public AppCurrency findCurrencyByAppId(Long app_id) throws Exception {
		return appCurrencyDao.findCurrencyByAppId(app_id);
	}

	@Override
	public AppEditVo getByAppForEdit(Long id) throws Exception {
		AppEditVo vo = null;
		Application entity = this.findById(id);
		if (entity != null) {
			vo = new AppEditVo();
			vo.setReleaseTime(entity.getReleaseTime());
			vo.setId(entity.getId());
			vo.setDev_id(entity.getDev_id());
			vo.setAppkey(entity.getAppkey());
			vo.setName(entity.getName());
			vo.setKeyword(entity.getKeyword());
			vo.setLongDesc(entity.getLongDesc());
			vo.setCategoryId(entity.getCategoryId());
			vo.setPlacement(entity.getPlacement());
			vo.setApp_manager(entity.getApp_manager());
			vo.setIs_coordinate(entity.getIs_coordinate());
			vo.setApp_manager_id(entity.getApp_manager_id());
			Developer developer = developerDao.getById(vo.getDev_id());
			if (developer != null) {
				vo.setDev_email(developer.getEmail());
				vo.setDev_name(developer.getRealName());
			}
			vo.setCreateTime(entity.getCreateTime());
			vo.setProjectUrl(entity.getProjectUrl());
			vo.setOs(entity.getOs());
			if (entity.getScale() != null) {
				vo.setScale(((Double) (entity.getScale() * 100)).intValue());
			}
			vo.setStatus(entity.getStatus());
			List<Page> pageTypeVos = pageDao.findByApp(entity.getId());
			StringBuilder sb = new StringBuilder();
			if (pageTypeVos != null && pageTypeVos.size() > 0) {
				for (Page pageType : pageTypeVos) {
					sb.append(pageType.getType_id() + ",");
				}
				sb = sb.deleteCharAt(sb.length() - 1);
			}
			vo.setPage_type(sb.toString());
			String adres =clusterAdConfigDao.findByApp(entity.getId());
			vo.setAd_res(adres);
		}
		return vo;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getSalesmanList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<AppMedia> getAppMediaList() throws Exception {
		List<AppMedia> list = null;
		list = appMediaDao.all();

		return list;
	}

}