/**
 * <p>Title: AdDetailCacheImpl.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2013-4-11
 * @version 1.0
 */
package cn.adwalker.ad.cache.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.adwalker.ad.bean.CacheElement;
import cn.adwalker.ad.bean.SysCategorye;
import cn.adwalker.ad.cache.IAdClusterCache;
import cn.adwalker.ad.cache.IAdCache;
import cn.adwalker.ad.cache.element.Advertise;
import cn.adwalker.ad.dao.IActiviteLogDao;
import cn.adwalker.ad.dao.IAdvertiseDao;
import cn.adwalker.ad.dao.IEscoreCategoryeDao;
import cn.adwalker.ad.dao.IMaterielBannerDao;
import cn.adwalker.ad.dao.IMaterielChartboostDao;
import cn.adwalker.ad.dao.IMaterielListDao;
import cn.adwalker.ad.dao.IMaterielScoreDao;
import cn.adwalker.ad.dao.IPlacementDao;
import cn.adwalker.ad.dao.IPlacementPackageDao;
import cn.adwalker.ad.dao.domain.Ad;
import cn.adwalker.ad.dao.domain.AdActivateNum;
import cn.adwalker.ad.dao.domain.MaterielBanner;
import cn.adwalker.ad.dao.domain.MaterielChartboost;
import cn.adwalker.ad.dao.domain.MaterielList;
import cn.adwalker.ad.dao.domain.MaterielScore;
import cn.adwalker.ad.dao.domain.Placement;
import cn.adwalker.ad.dao.domain.PlacementPackage;
import cn.adwalker.ad.pool.MemCached;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.BeanUtils;

/**
 * <p>
 * Title: AdDetailCacheImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author www.adwalker.cn
 * @date 2013-4-11
 */
@Service("advCache")
public class AdCacheImpl implements IAdCache {
	
	private  Map<String,CacheElement<AdActivateNum>> AD_ACTIVATE_MAP=null;

	@Resource
	private MemCached memCached;
	@Resource
	private IPlacementDao placementDao;
	@Resource
	private IPlacementPackageDao placementPackageDao;
	@Resource
	private IAdvertiseDao advertiseDao;
	@Resource
	private IMaterielChartboostDao materielChartboostDao;
	@Resource
	private IMaterielBannerDao materielBannerDao;
	@Resource
	private IMaterielScoreDao materielScoreDao;
	@Resource
	private IMaterielListDao materielListDao;
	@Resource
	private IEscoreCategoryeDao escoreCategoryeDao;
	
	@Resource
	private	IAdClusterCache adClusterCache;
	
	@Resource
	private	IActiviteLogDao activiteLogDao;
	
	

	/**
	 * <p>
	 * Title: getAdDetail
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @see cn.adwalker.ad.cache.IAdDetailCache#getAdDetail(long)
	 */
	public Advertise getAdv(long id) {
		Advertise adv = (Advertise) memCached.get(AppConstant.MEMCACHE_AD + id);
		if (adv == null) {
			Ad ad = advertiseDao.getAdvertise(id);
			if (ad == null) {
				return null;
			}
			if (ad.getAd_type()==3) {
				adv=adClusterCache.getClusterAd(ad);
			}else {
				adv=getSysAd(ad);
			}
			memCached.add(AppConstant.MEMCACHE_AD + id, adv, new Date(AppConstant.CACHE_TIME));
		}
		return adv;
	}
	
	private Advertise getSysAd(Ad ad){
		Advertise advertise=new Advertise();
		if (ad==null) {
			return null;
		}
		advertise=new Advertise();
		advertise.setId(ad.getId());
		advertise.setBlance_price(ad.getBlance_price());
		advertise.setBlance_mode(ad.getBlance_mode());
		advertise.setType_id(ad.getType_id().intValue());
		advertise.setCreate_time(ad.getCreate_time());
		advertise.setPackage_id(ad.getPackage_id());
		advertise.setStatus(ad.getStatus());
		advertise.setTerminal_type_str(ad.getTerminal_type_str());
		advertise.setOperat_agencies_str(ad.getOperat_agencies_str());
		advertise.setPhone_brand_str(ad.getPhone_brand_str());
		advertise.setTime_directional_str(ad.getTime_directional_str());
		advertise.setArea_directional_str(ad.getArea_directional_str());
		advertise.setSdk_version_str(ad.getSdk_version_str());
		advertise.setApp_type_str(ad.getApp_type_str());
		advertise.setAd_name(ad.getAd_name());
		advertise.setConfirm_type(ad.getConfirm_type());
		
		
		
		
		Long placement_id=ad.getPlacement_id();
		Placement entity = placementDao.getPlacement(placement_id);
		advertise.setPlacement_id(entity.getId());
		PlacementPackage placementPackage = placementPackageDao.getPlacementPackage(ad.getPackage_id());
		advertise.setSlogan(entity.getSlogan());
		advertise.setIcon_url(entity.getIcon_url());
		advertise.setPriority(entity.getPriority());
		advertise.setStar_level(entity.getStar_level());
		advertise.setConfig_id(entity.getConfig_id());
		advertise.setOs(entity.getOs());

		// 包信息
		if (placementPackage != null) {
			if (entity.getOs().equals(AppConstant.OS_IOS)) {
				advertise.setApp_url(placementPackage.getRes_url());
			}
			if (placementPackage.getRes_url() != null) {
				placementPackage.setRes_url(AppConstant.resF
						+ placementPackage.getRes_url());
			} else {
				placementPackage.setRes_url("");
			}
			advertise.setPackageInfo(BeanUtils.packageInfo(placementPackage));
		}

		// 应用类别
		if (advertise.getCategory_id() != null) {
			SysCategorye escoreCategorye = escoreCategoryeDao
					.getEscoreCategorye(advertise.getCategory_id());
			if (escoreCategorye != null)
				advertise.setCategory_name(escoreCategorye.getName());
		} else {
			advertise.setCategory_id(0L);
			advertise.setCategory_name("其他");
		}

		advertise.setPackageInfo(BeanUtils.packageInfo(placementPackage));
		int typeId = ad.getType_id().intValue();
		

		// 积分墙
		if (String.valueOf(typeId).equals(AppConstant.PAGE_WALLTYPE_LIST_SMALL)) {
			MaterielScore materielScore = materielScoreDao.getMaterielScore(placement_id);
			if(materielScore!=null){
				if (materielScore.getResponse_category() == 1) {
					advertise.setResponse_type(1);
				} else {
					advertise.setResponse_type(materielScore.getResponse_type());
				}
				advertise.setWall(materielScore);
				if (materielScore.getWeight() != null)
					advertise.setWeight(materielScore.getWeight());
			}

			// 推荐墙
		} else if (String.valueOf(typeId).equals(
				AppConstant.PAGE_WALLTYPE_LIST_BIG)) {
			MaterielList materielList = materielListDao
					.getMaterielList(placement_id);
			
			if (materielList.getResponse_category() == 1) {
				advertise.setResponse_type(1);
				advertise.setAd_url(materielList.getRedirect_url());
			} else {
				advertise.setResponse_type(materielList.getResponse_type());
			}
			advertise.setWall(materielList);
			if (materielList.getWeight() != null)
				advertise.setWeight(materielList.getWeight());

			// banner
		} else if (String.valueOf(typeId).equals(
				AppConstant.PAGE_WALLTYPE_BANNER)) {
			MaterielBanner materielBanner = materielBannerDao
					.getMaterielBanner(placement_id);
			if (materielBanner.getResponse_category() == 1) {
				advertise.setResponse_type(1);
				advertise.setAd_url(materielBanner.getRedirect_url());
			} else {
				advertise.setResponse_type(materielBanner.getResponse_type());
			}
			if (materielBanner.getWeight() != null)
				advertise.setWeight(materielBanner.getWeight());
			advertise.setWall(materielBanner);

			// 插屏
		} else if (String.valueOf(typeId).equals(
				AppConstant.PAGE_WALLTYPE_PLAQUE)) {
			MaterielChartboost materielChartboost = materielChartboostDao
					.getMaterielChartboost(placement_id);
			if (materielChartboost.getResponse_category() == 1) {
				advertise.setResponse_type(1);
				advertise.setAd_url(materielChartboost.getRedirect_url());
			} else {
				advertise.setResponse_type(materielChartboost.getResponse_type());
			}
			if (materielChartboost.getWeight() != null)
				advertise.setWeight(materielChartboost.getWeight());
		}
		return advertise;
		
	}
	

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: replaceAdv
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param uuid
	 * @return
	 * @see cn.adwalker.ad.cache.IAdCache#replaceAdv(long, java.lang.String)
	 */
	@Override
	public Advertise replaceAdv(long id) {
		memCached.clear(AppConstant.MEMCACHE_AD + id);
		Advertise adv = getAdv(id);
		return adv;
	}

	@Override
	public AdActivateNum getAdActivateNum(String ad_id){
		AdActivateNum activateNum=null;
		if (AD_ACTIVATE_MAP==null) {
			AD_ACTIVATE_MAP=new HashMap<String, CacheElement<AdActivateNum>>();
			List<AdActivateNum> list=activiteLogDao.getAdActiviteNum();
			if (list!=null&&list.size()>0) {
				for (AdActivateNum num:list) {
					AD_ACTIVATE_MAP.put(num.getAd_id(),new CacheElement<AdActivateNum>(30));
				}
			}
		}
		if (AD_ACTIVATE_MAP.get(ad_id)!=null&&AD_ACTIVATE_MAP.get(ad_id).getElement()!=null) {
			activateNum=AD_ACTIVATE_MAP.get(ad_id).getElement();
		}
		if (activateNum==null) {
			activateNum=activiteLogDao.getAdActiviteNumById(Long.valueOf(ad_id));
			if (activateNum==null) {
				activateNum=new AdActivateNum(ad_id,0);
			}
			AD_ACTIVATE_MAP.put(ad_id, new CacheElement<AdActivateNum>(30));
		}
	
		return activateNum;
	}

}
