/**
* <p>Title: AdDetailCacheImpl.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-4-19
* @version 1.0
*/
package cn.adwalker.ad.cache.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.adwalker.ad.cache.IAdClusterCache;
import cn.adwalker.ad.cache.ISystemConfigCache;
import cn.adwalker.ad.cache.element.Advertise;
import cn.adwalker.ad.cache.element.PackageInfo;
import cn.adwalker.ad.dao.IAdClusterDao;
import cn.adwalker.ad.dao.IAdvertiseDao;
import cn.adwalker.ad.dao.domain.Ad;
import cn.adwalker.ad.dao.domain.AdCluster;
import cn.adwalker.ad.dao.domain.MaterielScore;
import cn.adwalker.ad.pool.MemCached;
import cn.adwalker.ad.util.AppConstant;

/**
 * <p>Title: AdDetailCacheImpl</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-19
 */
@Service
public class AdClusterCacheImpl implements IAdClusterCache {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(AdClusterCacheImpl.class);
	
	@Resource
	private IAdvertiseDao advertiseDao;

	@Resource
	private ISystemConfigCache systemConfigCache;
	
	@Resource
	private MemCached memCached;
	
	@Resource
	private IAdClusterDao adClusterDao;
	/**  
	* <p>Title: getAdDetail</p>
	* <p>Description:TODO</p>
	* @param id
	* @param userInfo
	* @return
	* @see cn.adwalker.ad.cache.IAdDetailCache#getAdDetail(long, cn.adwalker.ad.bean.UserInfo)
	*/
	@Override
	public AdCluster getCacheElement(long id) {
		AdCluster entity = (AdCluster) memCached.get(AppConstant.MEMCACHE_AD_CLUSTER+id);	 
		if(entity == null){
			entity =adClusterDao.getAdClusterListId(id);
			memCached.add(AppConstant.MEMCACHE_AD_CLUSTER+id, entity, new Date(AppConstant.CACHE_TIME));
		}
		
		return entity;
	}

	/**  (non-Javadoc)
	* <p>Title: replaceAdDetail</p>
	* <p>Description:TODO</p>
	* @param id
	* @return
	* @see cn.adwalker.ad.cache.IAdDetailCache#replaceAdDetail(long)
	*/
	@Override
	public void replaceCacheElement(long ad_id) {
		Long id=adClusterDao.getClusterIdByAdId(ad_id);
		if (id!=null) {
			memCached.clear(AppConstant.MEMCACHE_AD_CLUSTER+id);
		}
		
	}

	@Override
	public List<Advertise> getClusterAdList() {
		List<Advertise> list=new ArrayList<Advertise>();
		List<Ad> adList=adClusterDao.getAdClusterList();
		if (adList.isEmpty()) {
			return list;
		}
		for (Ad ad:adList) {
			AdCluster adCluster=this.getCacheElement(ad.getCluster_id());
			Advertise advertise=this.getAdv(adCluster, ad);
			list.add(advertise);
		}
		
		return list;
	}
	
	private Advertise getAdv(AdCluster adCluster,Ad ad) {
		if (adCluster == null) {
			return null;
		}
		Advertise adv =new  Advertise();
		adv=new Advertise();
		adv.setAd_mark(ad.getAd_mark());
		adv.setId(ad.getId());
		adv.setBlance_price(ad.getBlance_price());
		adv.setBlance_mode(ad.getBlance_mode());
		adv.setType_id(ad.getType_id().intValue());
		adv.setCreate_time(ad.getCreate_time());
		adv.setPackage_id(ad.getPackage_id());
		adv.setStatus(ad.getStatus());
		adv.setApp_url(adCluster.getStore_url());
		adv.setFast_task(ad.getFast_task());
		adv.setAd_name(adCluster.getAd_name());//广告名称
		adv.setPlacement_id(0L);
		adv.setOs(AppConstant.OS_IOS);
		adv.setSlogan(adCluster.getAd_slogan());
		adv.setIcon_url(adCluster.getIcon_url());
		adv.setRes_code(adCluster.getRes_code());
		String size = "0";
		adv.setAd_package_size("大小：" + size);
		adv.setAd_res_code(adCluster.getRes_code());//广告来源
		adv.setConfig_id(adCluster.getRes_code()+"_"+adCluster.getAd_code());
		// 应用类别
		adv.setCategory_id(0L);
		adv.setCategory_name("其他");
		int typeId = ad.getType_id().intValue();
		if (String.valueOf(typeId).equals(AppConstant.PAGE_WALLTYPE_LIST_SMALL)) {
			MaterielScore materielScore = new MaterielScore();
			adv.setResponse_type(2);
			materielScore.setScore_desc(adCluster.getTask_desc());
			materielScore.setLimit_time(300);
			adv.setWall(materielScore);
			adv.setWeight(0);
		}
		PackageInfo packageInfo =new PackageInfo();
		packageInfo.setRes_url(adCluster.getClick_url());
		packageInfo.setFile_name("");
		packageInfo.setFile_size(1d);
		packageInfo.setPackage_name("..");
		packageInfo.setVersion_code("1.0");
		packageInfo.setUpdate_time(new Date());
		adv.setPackageInfo(packageInfo);
		adv.setAppstore_id(adCluster.getStore_id());
		return adv;
	}

	@Override
	public Advertise getClusterAd(Ad ad) {
		Advertise adv=null;
		if (ad!=null) {
			AdCluster adCluster=getCacheElement(ad.getCluster_id());
			adv=this.getAdv(adCluster, ad);
		}
		return adv;
	}
}
