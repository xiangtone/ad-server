/**
 * <p>Title: SystemConfigImpl.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2013-6-9
 * @version 1.0
 */
package cn.adwalker.ad.cache.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.adwalker.ad.bean.CacheElement;
import cn.adwalker.ad.cache.ISystemConfigCache;
import cn.adwalker.ad.cache.element.ServiceConfig;
import cn.adwalker.ad.dao.ISysConfigDao;
import cn.adwalker.ad.dao.ISysConfigFinanceDao;
import cn.adwalker.ad.dao.domain.ConfigFinance;
import cn.adwalker.ad.dao.domain.SysConfig;
import cn.adwalker.ad.pool.MemCached;
import cn.adwalker.ad.util.AppConstant;

/**
 * <p>
 * Title: SystemConfigImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author www.adwalker.cn
 * @date 2013-6-9
 */

@Service("systemConfigCache")
public class SystemConfigCacheImpl implements ISystemConfigCache {

	@Resource
	private MemCached memCached;
	
	@Resource
	private ISysConfigDao sysConfigDao;

	@Resource
	private ISysConfigFinanceDao sysConfigFinanceDao;
	

	/**
	 * 内存缓存
	 */
	private static final CacheElement<ServiceConfig> cache = new CacheElement<ServiceConfig>(
			AppConstant.DEFAULT_MEM_CACHE_TIME);

	/**
	 * <p>
	 * Title: getSystemConfig
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @see cn.adwalker.ad.cache.ISystemConfigCache#getSystemConfig()
	 */
	@Override
	public ServiceConfig getSystemConfig() {
		ServiceConfig config = cache.getElement();
		if (config == null) {
			config = (ServiceConfig) memCached
					.get(AppConstant.MEMCACHE_SYSTEM_CONFIG);
			if (config == null) {
				config = new ServiceConfig();
				List<SysConfig> configList = sysConfigDao.getSysConfig();
				for (SysConfig sysConfig : configList) {
					if (sysConfig.getConfig_type().equals(
							AppConstant.SYS_AC_LIMIT)) {
						config.setScore_ac_limit(Integer.parseInt(sysConfig
								.getConfig_value()));
					} else if (sysConfig.getConfig_type().equals(
							AppConstant.SYS_DELAY_TIME)) {
						config.setScore_delay_time(Integer.parseInt(sysConfig
								.getConfig_value()));
					} else if (sysConfig.getConfig_type().equals(
							AppConstant.SYS_EXPLAIN)) {
						config.setScore_explain(sysConfig.getConfig_value());
					} else if (sysConfig.getConfig_type().equals(
							AppConstant.SYS_BANNER_INTERVAL)) {
						config.setBanner_interval(Integer.parseInt(sysConfig
								.getConfig_value()));
					}else if (sysConfig.getConfig_type().equals(
							AppConstant.SYS_ANDROID_BANNER)) {
						config.setAndroid_banner_probability(Integer.parseInt(sysConfig
								.getConfig_value()));
					}else if (sysConfig.getConfig_type().equals(								
							AppConstant.SYS_ANDROID_PLAQUE)) {
						config.setAndroid_plaque_probability(Integer.parseInt(sysConfig
								.getConfig_value()));
					}else if (sysConfig.getConfig_type().equals(
							AppConstant.SYS_IOS_BANNER)) {						
						config.setIos_banner_probability(Integer.parseInt(sysConfig
								.getConfig_value()));
					}else if (sysConfig.getConfig_type().equals(
							AppConstant.SYS_IOS_PLAQUE)) {
						config.setIos_plaque_probability(Integer.parseInt(sysConfig
								.getConfig_value()));
					}else if (sysConfig.getConfig_type().equals(
							AppConstant.SYS_LOMARK_PRICE)) {
						config.setLomark_price(Double.parseDouble(sysConfig
								.getConfig_value()));					
					}else if (sysConfig.getConfig_type().equals(
							AppConstant.SYS_LOMARK_PAY_TYPE)) {
						config.setLomark_pay_type(sysConfig
								.getConfig_value());
					}else if (sysConfig.getConfig_type().equals(
							AppConstant.SYS_LOMARK_URL)) {
						config.setLomark_url(sysConfig.getConfig_value());
					}else if (sysConfig.getConfig_type().equals(
							AppConstant.SYS_LOMARK_KEY)) {
						config.setLomark_key(sysConfig
								.getConfig_value());					
					}else if (sysConfig.getConfig_type().equals(
							AppConstant.SYS_LOMARK_SAFE_KEY)) {
						config.setLomark_safe_key(sysConfig
								.getConfig_value());
					}else if (sysConfig.getConfig_type().equals(AppConstant.AD_RES_SWITCH_CHUKONG)) {
						config.setAd_res_switch_chukong(sysConfig.getConfig_value());
					}else if (sysConfig.getConfig_type().equals(AppConstant.AD_RES_SWITCH_KUAIYOU)) {
						config.setAd_res_switch_kuaiyou(sysConfig.getConfig_value());
					}else if (sysConfig.getConfig_type().equals(AppConstant.AD_RES_SWITCH_PLATFORM)) {
						config.setAd_res_switch_platform(sysConfig.getConfig_value());
					}else if (sysConfig.getConfig_type().equals(AppConstant.AD_RES_SWITCH_YOUMI)) {
						config.setAd_res_switch_youmi(sysConfig.getConfig_value());
					}
				}
				ConfigFinance	entity = sysConfigFinanceDao.getConfigFinance();
				config.setQuickly_task(entity.getQuickly_task());
				cache.setElement(config);
			}
		}
		return config;
	}


	/**
	 * <p>
	 * Title: replaceSystemConfig
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @see cn.adwalker.ad.cache.ISystemConfigCache#replaceSystemConfig()
	 */
	@Override
	public void replaceSystemConfig() {
		memCached.clear(AppConstant.MEMCACHE_SYSTEM_CONFIG);
		cache.clearElement();
	}

}
