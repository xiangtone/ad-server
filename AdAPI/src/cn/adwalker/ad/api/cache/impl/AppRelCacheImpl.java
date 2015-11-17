/**
* <p>Title: AppRelCacheImpl.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-7-22
* @version 1.0
*/
package cn.adwalker.ad.api.cache.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.adwalker.ad.api.cache.IAppRelCache;
import cn.adwalker.ad.dao.IAppRelDao;
import cn.adwalker.ad.pool.MemCached;
import cn.adwalker.ad.util.AppConstant;

/**
 * <p>Title: AppRelCacheImpl</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-7-22
 */
@Service("appRelCache")
public class AppRelCacheImpl implements IAppRelCache {
	@Resource
	private MemCached memCached;
	
	@Resource
	private IAppRelDao appRelDao;
	
	/**  (non-Javadoc)
	* <p>Title: getAppRel</p>
	* <p>Description:TODO</p>
	* @param appId
	* @return
	* @see cn.adwalker.ad.api.cache.IAppRelCache#getAppRel(java.lang.String)
	*/
	@Override
	public String getAppRel(String appId) {
		
		String key =  AppConstant.MEM_API_APP_REL+appId;
		String url = (String) memCached.get(key);	 
		if(url == null){
			url = appRelDao.fingChangDouUrl(appId);
			memCached.add(key, url,  new Date(AppConstant.CACHE_TIME));
	    }
		
		return url;
	} 
}
