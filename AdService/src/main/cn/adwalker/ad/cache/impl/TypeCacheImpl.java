/**
 * <p>Title: TypeCacheImpl.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2013-4-14
 * @version 1.0
 */
package cn.adwalker.ad.cache.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.adwalker.ad.bean.SysCategorye;
import cn.adwalker.ad.cache.IAdClusterCache;
import cn.adwalker.ad.cache.ITypeCache;
import cn.adwalker.ad.cache.element.Advertise;
import cn.adwalker.ad.cache.element.DevApp;
import cn.adwalker.ad.cache.element.Type1;
import cn.adwalker.ad.dao.IAdvertiseDao;
import cn.adwalker.ad.dao.IDevelopedAppDao;
import cn.adwalker.ad.dao.IEscoreCategoryeDao;
import cn.adwalker.ad.dao.IMaterielBannerDao;
import cn.adwalker.ad.dao.IMaterielChartboostDao;
import cn.adwalker.ad.dao.IMaterielListDao;
import cn.adwalker.ad.dao.IMaterielScoreDao;
import cn.adwalker.ad.dao.IPlacementDao;
import cn.adwalker.ad.dao.IPlacementPackageDao;
import cn.adwalker.ad.dao.domain.AdList;
import cn.adwalker.ad.dao.domain.MaterielBanner;
import cn.adwalker.ad.dao.domain.MaterielChartboost;
import cn.adwalker.ad.dao.domain.MaterielList;
import cn.adwalker.ad.dao.domain.MaterielScore;
import cn.adwalker.ad.dao.domain.Placement;
import cn.adwalker.ad.dao.domain.PlacementPackage;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.pool.MemCached;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.BeanUtils;
import cn.adwalker.ad.util.PublicUtil;

/**
 * <p>
 * Title: TypeCacheImpl
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
@Service("typeCache")
public class TypeCacheImpl implements ITypeCache {

	private static final Logger log = LoggerFactory
			.getLogger(TypeCacheImpl.class);

	@Resource
	private MemCached memCached;
	@Resource
	private IMaterielScoreDao materielScoreDao;
	@Resource
	private IMaterielListDao materielListDao;
	@Resource
	private IMaterielChartboostDao materielChartboostDao;
	@Resource
	private IMaterielBannerDao materielBannerDao;
	@Resource
	private IAdvertiseDao advertiseDao;
	@Resource
	private IPlacementDao placementDao;
	@Resource
	private IPlacementPackageDao placementPackageDao;
	@Resource
	private IDevelopedAppDao developedAppDao;
	@Resource
	private IEscoreCategoryeDao escoreCategoryeDao;
	
	@Resource
	private IAdClusterCache adClusterCache;

	/**
	 * <p>
	 * Title: getType
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param os
	 * @param typeId
	 * @return
	 * @see cn.adwalker.ad.cache.ITypeCache#getType(java.lang.String, int)
	 */
	@Override
	public Type1 getType(String os, int typeId, DevApp app) {
		Type1 type = (Type1) memCached.get(AppConstant.MEMCACHE_TYPE + os+ typeId + app.getPlacement());
		if (type == null) {
			log.info("begin cache typeId["+typeId+"]");
			long a = System.currentTimeMillis();
			type = new Type1();
			type.setId(typeId);
			List<Advertise> adList=this.getAdList(os, typeId, app.getPlacement());
			if (os.equals(AppConstant.OS_IOS)&&typeId==Integer.valueOf(AppConstant.PAGE_WALLTYPE_LIST_SMALL)) {
				List<Advertise> tmpList=adClusterCache.getClusterAdList();
				adList.addAll(tmpList);
				//adList=tmpList;//暂时只显示外接广告
			}
			type.setAdvertiselist(adList);
			memCached.add(AppConstant.MEMCACHE_TYPE + os + typeId+ app.getPlacement(), type, new Date(AppConstant.CACHE_TIME));
			log.info("cache typeId["+typeId+"] done :"+(System.currentTimeMillis()-a)+"ms");
		}	
		return type;
	}
	
	
	
	private List<Advertise> getAdList(String os,long typeid,long appPlacement){
		List<Advertise> list=new ArrayList<Advertise>();
		List<AdList> adList = advertiseDao.getAdvertiseList(os, typeid,appPlacement);
		Advertise ad=null;
		for (AdList tmpAd : adList) {
			ad=BeanUtils.getAdvertise(tmpAd);
			Long placement_id=tmpAd.getPlacement_id();
			Placement entity = placementDao.getPlacement(tmpAd.getPlacement_id());
			ad.setSlogan(entity.getSlogan());
			ad.setPriority(entity.getPriority());
			ad.setIcon_url(AppConstant.imageF+ entity.getIcon_url());
			ad.setStar_level(entity.getStar_level());
			ad.setConfig_id(entity.getConfig_id());
			ad.setOs(entity.getOs());
			ad.setPlacement_id(entity.getId());
			ad.setAd_url("");
			ad.setKeyword(entity.getKeyword());
			PlacementPackage placementPackage = placementPackageDao.getPlacementPackage(tmpAd.getPackage_id());
			if (placementPackage != null) {
				if (os.equals(AppConstant.OS_IOS)) {
					ad.setApp_url(placementPackage.getRes_url());
				}
				if (placementPackage.getRes_url() != null) {
					placementPackage.setRes_url(AppConstant.resF+ placementPackage.getRes_url());
				} else {
					placementPackage.setRes_url("");
				}
				ad.setPackageInfo(BeanUtils.packageInfo(placementPackage));
				ad.setAppstore_id(placementPackage.getAppstore_id());
			}
	
			// 应用类别
			if (ad.getCategory_id() != null) {
				SysCategorye escoreCategorye = escoreCategoryeDao.getEscoreCategorye(ad.getCategory_id());
				if (escoreCategorye != null){
					ad.setCategory_name(escoreCategorye.getName());
				}
			} else {
				ad.setCategory_id(0L);
				ad.setCategory_name("其他");
			}
			String size = "0";
			if (placementPackage != null) {
				size = PublicUtil.getSize(placementPackage.getFile_size()
						.longValue());
			}
			ad.setAd_package_size("大小：" + size);
	
			// 积分墙
			if (String.valueOf(typeid).equals(AppConstant.PAGE_WALLTYPE_LIST_SMALL)) {
				MaterielScore materielScore = materielScoreDao.getMaterielScore(placement_id);
				ad.setWall(materielScore);
				if(materielScore!=null){
					if (materielScore.getResponse_category() == 1) {
						ad.setResponse_type(1);
					} else {
						ad.setResponse_type(materielScore.getResponse_type());
					}
					if (materielScore.getWeight() != null)
						ad.setWeight(materielScore.getWeight());
					// add by jief 2013-09-05 是否是新应用
				}
				if(os.equals(AppConstant.OS_IOS)){
					ad .setNew_app(entity.getNew_app()==null?1:entity.getNew_app());
				}
				// 推荐墙
			} else if (String.valueOf(typeid).equals(AppConstant.PAGE_WALLTYPE_LIST_BIG)) {
				MaterielList materielList = materielListDao.getMaterielList(placement_id);
				ad.setWall(materielList);
				if(materielList!=null){
					if (materielList.getResponse_category() == 1) {
						ad.setResponse_type(1);
						ad.setAd_url(materielList.getRedirect_url());
					} else {
						ad.setResponse_type(materielList.getResponse_type());
					}
					if (materielList.getWeight() != null)
						ad.setWeight(materielList.getWeight());
				}else{
					//System.out.println(placement.getId());
				}
	
				// banner
			} else if (String.valueOf(typeid).equals(AppConstant.PAGE_WALLTYPE_BANNER)) {
				MaterielBanner materielBanner = materielBannerDao
						.getMaterielBanner(placement_id);
				ad.setWall(materielBanner);
				if (materielBanner.getResponse_category() == 1) {
					ad.setResponse_type(1);
					ad.setAd_url(materielBanner.getRedirect_url());
				} else {
					ad.setResponse_type(materielBanner.getResponse_type());
				}
				if (materielBanner.getWeight() != null)
					ad.setWeight(materielBanner.getWeight());
	
				// 插屏
			} else if (String.valueOf(typeid).equals(AppConstant.PAGE_WALLTYPE_PLAQUE)) {
				MaterielChartboost materielChartboost = materielChartboostDao
						.getMaterielChartboost(placement_id);
				ad.setWall(materielChartboost);
				if(materielChartboost!=null){
					if (materielChartboost.getResponse_category() == 1) {
						ad.setResponse_type(1);
						ad.setAd_url(materielChartboost.getRedirect_url());
					} else {
						ad.setResponse_type(materielChartboost
								.getResponse_type());
					}
					if (materielChartboost.getWeight() != null){
						ad.setWeight(materielChartboost.getWeight());
					} 
				}
			}
				
	
			
			// 积分描述
			if (ad.getResponse_type() == null) {
				ad.setResponse_type(0);
			}
			
			if(!StringUtil.isEmpty(ad.getIs_url_params()) && ad.getIs_url_params()==1){
				String urlParam = placementDao.getUrlParamByConfigId(ad.getConfig_id());					
				ad.setUrl_params(StringUtil.dealNull(urlParam));
			}
			ad.setRes_code("adwalker");
			list.add(ad);
		}
		return list;
	}
	
	
	
	
	
	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: replaceType
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param adId
	 * @return
	 * @see cn.adwalker.ad.cache.ITypeCache#replaceType(long)
	 */
	@Override
	public void replaceType(long adId, String os) {
		List<Map<String, Object>> apps = developedAppDao.appPlacementList();
		Iterator<Map<String, Object>> appMap = apps.iterator();
		while (appMap.hasNext()) {
			Map<String, Object> map = appMap.next();
			if (map.get("PLACEMENT") != null) {
				String placement = map.get("PLACEMENT").toString();
				for (int i = 0; i < 6; i++) {
					memCached.clear(AppConstant.MEMCACHE_TYPE + os + i+ placement);
				}
			} else {
				log.error("replaceType is err");
			}
		}
	}

	@Override
	public Type1 getFeedBackType(String os, int typeId, DevApp app) {
		Type1 type = (Type1) memCached.get(AppConstant.MEMCACHE_TYPE_FEEDBACK + os+ typeId + app.getPlacement());
		if (type == null) {
			type = new Type1();
			type.setId(typeId);
			List<AdList> adList = advertiseDao.getAdvertiseList(os, typeId,app.getPlacement());
			List<Advertise> list=new ArrayList<Advertise>();
			Advertise ad=null;
			for (AdList	tmpAd : adList) {
				ad=BeanUtils.getAdvertise(tmpAd);
				Placement entity = placementDao.getPlacement(tmpAd.getPlacement_id());
				ad.setSlogan(entity.getSlogan());
				ad.setIcon_url(entity.getIcon_url());
				ad.setPriority(entity.getPriority());
				ad.setStar_level(entity.getStar_level());
				ad.setConfig_id(entity.getConfig_id());
				ad.setOs(entity.getOs());
				ad.setPlacement_id(entity.getId());
				if (ad.getResponse_type() == null) {
					ad.setResponse_type(0);
				}
				list.add(ad);
			}
			type.setAdvertiselist(list);
			memCached.add(AppConstant.MEMCACHE_TYPE_FEEDBACK + os + typeId+ app.getPlacement(), type, new Date(AppConstant.CACHE_TIME));			
		}	
		return type;
	}

}
