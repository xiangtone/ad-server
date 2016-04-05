package cn.adwalker.ad.web.developer.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.config.AppConstant;
import cn.adwalker.ad.core.page.IPageInfo;
import cn.adwalker.ad.model.application.dao.ApplicationCurrencyDao;
import cn.adwalker.ad.model.application.dao.ApplicationDao;
import cn.adwalker.ad.model.application.dao.CoopertiomConfigDao;
import cn.adwalker.ad.model.application.dao.PageDao;
import cn.adwalker.ad.model.application.domain.AppCurrency;
import cn.adwalker.ad.model.application.domain.Application;
import cn.adwalker.ad.model.application.domain.CooperationConfig;
import cn.adwalker.ad.model.common.dao.CategoryDao;
import cn.adwalker.ad.model.common.domain.ApplicationCategory;
import cn.adwalker.ad.util.BeanUtils;
import cn.adwalker.ad.util.CacheUtils;
import cn.adwalker.ad.util.DateUtil;
import cn.adwalker.ad.util.Function;
import cn.adwalker.ad.util.StatusConstant;
import cn.adwalker.ad.util.StringUtil;
import cn.adwalker.ad.web.developer.form.AppBaseInfo;
import cn.adwalker.ad.web.developer.form.AppEditForm;
import cn.adwalker.ad.web.developer.vo.ApplicationVo;
import cn.adwalker.ad.web.developer.vo.DevAppVo;
/**
 * 功能概述：<br>
 * 开发者SDK应用服务
 */
@Service("applicationService")
public class ApplicationService {

	@Resource
	private ApplicationDao applicationDao;

	@Resource
	private CategoryDao categoryDao;
	
	@Resource
	private CoopertiomConfigDao cooperConfigDao;

	@Resource
	private PageDao pageDao;

	@Resource
	private ApplicationCurrencyDao applicationCurrencyDao;

	public List<Application> findByDeveloper(Long id) throws Exception {
		List<Application> developedApp = new ArrayList<Application>();
		developedApp = this.applicationDao.findByDeveloper(id);
		return developedApp;
	}

	public DevAppVo getById(Long id) throws Exception {
		DevAppVo vo = null;
		if (id != null) {
			Application entity = this.applicationDao.findById(id);
			if (entity != null) {
				vo = (DevAppVo) BeanUtils.copyProperties(new DevAppVo(), entity);
				vo.setAppKey(entity.getAppkey());
				vo.setCategoryName(categoryDao.findECLById(vo.getCategoryId()));
				AppCurrency currency = applicationCurrencyDao.findCurrencyByAppId(id);
				if (currency != null) {
					vo.setVirtual_currency_name(currency.getVirtual_currency_name());
					vo.setExchange_rate_rmb(currency.getExchange_rate_rmb());
				}
				CooperationConfig cc= cooperConfigDao.getConfigByAppId(id);
				if (cc!= null) {
					vo.setResponse_url(cc.getResponse_url());
					
				}
			}
		}
		return vo;
	}

	public DevAppVo getByDeVId(Long id, Long dev_id) throws Exception {
		DevAppVo vo = null;
		if (id != null) {
			Application entity = this.applicationDao.findByDeveloper(id, dev_id);
			if (entity != null) {
				vo = (DevAppVo) BeanUtils.copyProperties(new DevAppVo(), entity);
				vo.setAppKey(entity.getAppkey());
				vo.setCategoryName(categoryDao.findECLById(vo.getCategoryId()));
				AppCurrency currency = applicationCurrencyDao.findCurrencyByAppId(id);
				if (currency != null) {
					vo.setVirtual_currency_name(currency.getVirtual_currency_name());
					vo.setExchange_rate_rmb(currency.getExchange_rate_rmb());
				}
				CooperationConfig cc= cooperConfigDao.getConfigByAppId(id);
				if (cc!= null) {
					vo.setResponse_url(cc.getResponse_url());
					
				}
			}
		}
		return vo;
	}

	@Transactional
	public Long insert(AppBaseInfo vo, Long devId) throws Exception {
		Application entity = new Application();
		entity.setDev_id(devId);
		entity.setCategoryId(vo.getCategoryId());
		entity.setName(vo.getName());
		entity.setOs(vo.getOs());
		entity.setAppkey("AW" + Function.getRandomCode(AppConstant.CODE_COUNT));// appkey
		entity.setStatus(StatusConstant.STATUS_UNAPPLY);// 待审核
		entity.setCreateTime(new Date());
		entity.setUpdateTime(new Date());
		entity.setKeyword(vo.getKeyword());
		entity.setLongDesc(vo.getLongDesc());
		entity.setApp_res(AppConstant.APP_RES);
		entity.setApp_manager("平台");
		entity.setScale(1d);
		Long developedAppId = this.applicationDao.insert(entity);
		pageDao.saveAndUpdate(developedAppId, vo.getPageType());
		
		AppCurrency currency = new AppCurrency();
		currency.setApp_id(developedAppId);
		if (!StringUtil.isEmpty(vo.getExchange_rate_rmb()) && NumberUtils.isNumber(vo.getExchange_rate_rmb())) {
			currency.setExchange_rate_rmb(vo.getExchange_rate_rmb());
		} else {
			currency.setExchange_rate_rmb(String.valueOf(100));
		}
		if (!StringUtil.isEmpty(vo.getVirtual_currency_name())) {
			currency.setVirtual_currency_name("积分");
		}
		currency.setStatus(1);
		applicationCurrencyDao.saveOrUpdate(currency);
		//积分回调接口
		if(!StringUtil.isEmpty(vo.getResponse_url())){
			CooperationConfig cc= new CooperationConfig();
			cc.setApp_id(developedAppId);
			cc.setResponse_url(vo.getResponse_url());
			cc.setCreate_time(DateUtil.format(new Date()));
			cooperConfigDao.saveConfig(cc);
			
		}
		
		return developedAppId;
	}

	@Transactional
	public Long update(AppBaseInfo vo, Long devId) throws Exception {
		Application entity = new Application();
		Long appId = vo.getId();
		entity.setId(appId);
		entity.setDev_id(devId);
		entity.setCategoryId(vo.getCategoryId());
		entity.setName(vo.getName());
		entity.setOs(vo.getOs());
		//entity.setAppkey("EM" + Function.getRandomCode(AppConstant.CODE_COUNT));// appkey
		entity.setStatus(StatusConstant.STATUS_UNAPPLY);// 待审核
		//entity.setCreateTime(new Date());
		entity.setUpdateTime(new Date());
		entity.setKeyword(vo.getKeyword());
		entity.setLongDesc(vo.getLongDesc());
		entity.setApp_res(AppConstant.APP_RES);
		entity.setApp_manager("平台");
		entity.setScale(1d);
		applicationDao.update(entity);
		pageDao.saveAndUpdate(appId, vo.getPageType());
		
		AppCurrency currency = new AppCurrency();
		currency.setApp_id(appId);
		if (!StringUtil.isEmpty(vo.getExchange_rate_rmb()) && NumberUtils.isNumber(vo.getExchange_rate_rmb())) {
			currency.setExchange_rate_rmb(vo.getExchange_rate_rmb());
		} else {
			currency.setExchange_rate_rmb(String.valueOf(100));
		}
		if (!StringUtil.isEmpty(vo.getVirtual_currency_name())) {
			currency.setVirtual_currency_name("积分");
		}
		currency.setStatus(1);
		applicationCurrencyDao.saveOrUpdate(currency);
		//积分回调接口
		if(!StringUtil.isEmpty(vo.getResponse_url())){
			CooperationConfig cc= new CooperationConfig();
			cc.setApp_id(appId);
			cc.setResponse_url(vo.getResponse_url());
			cc.setCreate_time(DateUtil.format(new Date()));
			cooperConfigDao.updateConfig(cc);
			CacheUtils.updateAppApiCache(appId);
		}
		return appId;
	}

	@Transactional
	public Long update(AppBaseInfo vo) throws Exception {
		Application entity = new Application();
		entity.setId(vo.getId());
		entity.setMarketUrl(vo.getMarketUrl());
		entity.setStatus(vo.getStatus());
		entity.setUpdateTime(new Date());
		Long developedAppId = applicationDao.update(entity);
		pageDao.saveAndUpdate(developedAppId, vo.getPageType());
		return developedAppId;
	}

	@Transactional
	public void update(Application app) throws Exception {
		app.setUpdateTime(new Date());
		this.applicationDao.update(app);
		CacheUtils.updateAppCache(app.getId());
	}

	public List<ApplicationVo> findByPage(Long dev_id, IPageInfo pageInfo, Integer status) throws Exception {
		String where = "V_APP_CATEGORY where status<>? AND dev_id=?";
		Object[] objs = null;
		if(status != -100) {
			where += " and status=?";
			objs = new Object[] {StatusConstant.STATUS_STOP, dev_id, status};
		} else {
			objs = new Object[] {StatusConstant.STATUS_STOP, dev_id};
		}
		return (List<ApplicationVo>) applicationDao.findByPage("*", where, objs, "order by create_time desc", ApplicationVo.class, pageInfo);
	}

	@Transactional
	public void updateApp(AppEditForm form) throws Exception {
		Application entity = applicationDao.findById(form.getId());
		if (form != null && entity != null) {
			//entity.setOs(form.getOs());
			entity.setName(form.getName());
			entity.setCategoryId(form.getCategoryId());
			entity.setKeyword(form.getKeyword());
			entity.setLongDesc(form.getLongDesc());
			if(!"ios".equals(form.getOs()) || entity.getStatus() != StatusConstant.STATUS_ONLINE) {// 上线状态IOS免审核
				entity.setStatus(StatusConstant.STATUS_UNCHECK);
			}
			entity.setMarketUrl(form.getMarketUrl());
			applicationDao.update(entity);
			CacheUtils.updateAppCache(entity.getId());
			pageDao.saveAndUpdate(form.getId(), form.getPageType());
			AppCurrency currency = new AppCurrency();
			currency.setApp_id(entity.getId());
			currency.setStatus(StatusConstant.STATUS_UNCHECK);
			if (!StringUtil.isEmpty(form.getVirtual_currency_name())) {
				currency.setVirtual_currency_name(form.getVirtual_currency_name());
			} else {
				currency.setVirtual_currency_name("积分");
			}
			if (!StringUtil.isEmpty(form.getExchange_rate_rmb()) && NumberUtils.isNumber(form.getExchange_rate_rmb())) {
				currency.setExchange_rate_rmb(form.getExchange_rate_rmb());
			} else {
				currency.setExchange_rate_rmb(String.valueOf(100));
			}
			applicationCurrencyDao.saveOrUpdate(currency);
		
			//积分回调接口
			if(!StringUtil.isEmpty(form.getResponse_url())){
				 CooperationConfig coo= cooperConfigDao.getConfigByAppId(entity.getId());
				CooperationConfig cc= new CooperationConfig();
				cc.setApp_id(entity.getId());
				cc.setResponse_url(form.getResponse_url());
				cc.setCreate_time(DateUtil.format(new Date()));
				if(coo!=null){
					cooperConfigDao.updateConfig(cc);
				}else{
					cooperConfigDao.saveConfig(cc);
					
				}
				CacheUtils.updateAppApiCache(entity.getId());
			}
			if("ios".equals(form.getOs())) {// IOS免审核
				CacheUtils.updateAppCache(entity.getId());
			}
		}
	}
	
	public String findCategoryIdsByAppId(Long appId) throws Exception {
		ApplicationCategory applicationCategory = categoryDao.findApplicationCategoryByAppId(appId);
		if(applicationCategory != null) {
			return applicationCategory.getCategoryIds();
		}
		return null;
	}

	@Transactional
	public Long updateApplicationCategory(Long appId, String categoryIds) throws Exception {
		ApplicationCategory applicationCategory = categoryDao.findApplicationCategoryByAppId(appId);
		if(applicationCategory == null && !"".equals(categoryIds)) {
			applicationCategory = new ApplicationCategory();
			applicationCategory.setAppId(appId);
			applicationCategory.setCategoryIds(categoryIds);
			return categoryDao.saveApplicationCategory(applicationCategory);
		} else if(applicationCategory != null && "".equals(categoryIds)) {
			categoryDao.deleteApplicationCategory(appId);
			return 0L;
		} else if(applicationCategory == null && "".equals(categoryIds)) {
			return 0L;
		}
		applicationCategory.setCategoryIds(categoryIds);
		return categoryDao.updateApplicationCategory(applicationCategory);
	}
	
}