/**
 * <p>Title: DevAppCacheImpl.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2013-4-14
 * @version 1.0
 */
package cn.adwalker.ad.cache.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.adwalker.ad.cache.IDevAppCache;
import cn.adwalker.ad.cache.element.DevApp;
import cn.adwalker.ad.dao.IClusterAdConfigDao;
import cn.adwalker.ad.dao.ICurrencyDao;
import cn.adwalker.ad.dao.IDevelopedAppDao;
import cn.adwalker.ad.dao.IPageDao;
import cn.adwalker.ad.dao.ISysConfigFinanceDao;
import cn.adwalker.ad.dao.domain.ConfigFinance;
import cn.adwalker.ad.dao.domain.DevCurrency;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.pool.MemCached;
import cn.adwalker.ad.util.AppConstant;

/**
 * <p>
 * Title: DevAppCacheImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author www.adwalker.cn
 * @date 2013-4-14
 */
@Service("devAppCache")
public class DevAppCacheImpl implements IDevAppCache {

	private static final Logger log = LoggerFactory
			.getLogger(DevAppCacheImpl.class);

	@Resource
	private MemCached memCached;
	@Resource
	private ICurrencyDao currencyDao;
	
	
	@Resource
	private IPageDao pageDao;
	@Resource
	private IDevelopedAppDao developedAppDao;

	@Resource
	private ISysConfigFinanceDao sysConfigFinanceDao;
	
	@Resource
	private IClusterAdConfigDao clusterAdConfigDao;


	/**
	 * <p>
	 * Title: getDevApp
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param appId
	 * @return DevApp
	 * @see cn.adwalker.ad.cache.IDevAppCache#getDevApp(java.lang.Long)
	 */
	@Override
	public DevApp getDevApp(Long appId) {
		// 获得应用
		DevApp app = (DevApp) memCached.get(AppConstant.MEMCACHE_APPLICATION+ appId);
		if (app == null || app.getScale() == null ) {
			app = developedAppDao.getApplication(appId);
			// 虚拟货币
			DevCurrency currency = currencyDao.findCurrency(appId);
			if (currency == null) {
				log.error("db.DevCurrency is null appId=" + appId);
				currency = new DevCurrency();
				ConfigFinance cf = sysConfigFinanceDao.getConfigFinance();
				currency.setVirtual_currency_name("积分");
				currency.setExchange_rate_rmb(StringUtil.dealNull(cf.getMoneyScoreRate(),"0"));
			}
			app.setCurrency(currency);
			app.setTypeIds(pageDao.getTypeIdList(appId));
			app.setAd_res(clusterAdConfigDao.getTypeIdList(appId));

			;
			memCached.clear(AppConstant.MEMCACHE_APPLICATION+ appId);
			memCached.add(AppConstant.MEMCACHE_APPLICATION + appId, app,new Date(AppConstant.CACHE_TIME));
		}
		return app;
	}

	/**
	 * <p>
	 * Title: updateApp
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param appId
	 * @see cn.adwalker.ad.cache.IDevAppCache#updateApp(java.lang.Long)
	 */
	@Override
	public void updateApp(Long appId) {
		memCached.clear(AppConstant.MEMCACHE_APPLICATION + appId);
		DevApp app = developedAppDao.getApplication(appId);
		memCached.clear(AppConstant.MEMCACHE_KEY_APP +app.getAppkey());
	}



	
	
	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getAppVOCache
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param appId
	 * @return
	 * @see cn.adwalker.ad.cache.IAppVOCache#getAppVOCache(long)
	 */
	@Override
	public DevApp getCache(String appKey) {
		DevApp vo = null;
		vo = (DevApp) memCached.get(AppConstant.MEMCACHE_KEY_APP + appKey);
		if (vo == null) {
			log.info("mem.AppKey is null appId=" + appKey);
			vo = developedAppDao.findByAppKey(appKey);
			if (vo != null) {
				memCached.add(AppConstant.MEMCACHE_KEY_APP + appKey,getDevApp(vo.getId()),
						new Date(AppConstant.CACHE_TIME));
			}
		}
		return vo;
	}
}
