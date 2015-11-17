package cn.adwalker.ad.picker.memcached;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.adwalker.ad.bean.SysCategorye;
import cn.adwalker.ad.cache.element.Advertise;
import cn.adwalker.ad.cache.element.Type1;
import cn.adwalker.ad.cache.impl.TypeCacheImpl;
import cn.adwalker.ad.dao.domain.AdList;
import cn.adwalker.ad.dao.domain.MaterielBanner;
import cn.adwalker.ad.dao.domain.MaterielChartboost;
import cn.adwalker.ad.dao.domain.MaterielList;
import cn.adwalker.ad.dao.domain.MaterielScore;
import cn.adwalker.ad.dao.domain.Placement;
import cn.adwalker.ad.dao.domain.PlacementPackage;
import cn.adwalker.ad.dao.impl.AdvertiseDaoImpl;
import cn.adwalker.ad.dao.impl.EscoreCategoryeDaoImpl;
import cn.adwalker.ad.dao.impl.MaterielBannerDaoImpl;
import cn.adwalker.ad.dao.impl.MaterielChartboostDaoImpl;
import cn.adwalker.ad.dao.impl.MaterielListDaoImpl;
import cn.adwalker.ad.dao.impl.MaterielScoreDaoImpl;
import cn.adwalker.ad.dao.impl.PlacementDaoImpl;
import cn.adwalker.ad.dao.impl.PlacementPackageDaoImpl;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.BeanUtils;
import cn.adwalker.ad.util.PublicUtil;
import cn.adwalker.core.spring.AppContext;

public class AdCache {
	private static final Logger logger = LoggerFactory.getLogger(TypeCacheImpl.class);
	
	public static AdCache newInstance(){
		return new AdCache();
	}
	
	public Type1 getType(String os, int typeId,Long app_placement) {
		Type1 type = MemcachedUtil.getInstance().get(AppConstant.MEMCACHE_TYPE + os+ typeId + app_placement, Type1.class);
		if (type == null) {
			logger.info("begin cache typeId["+typeId+"]");
			long a = System.currentTimeMillis();
			type = new Type1();
			type.setId(typeId);
			AdvertiseDaoImpl advertiseDao = AppContext.getApplicationContext().getBean("advertiseDao", AdvertiseDaoImpl.class);
			List<AdList> adList = advertiseDao.getAdvertiseList(os, typeId,app_placement);
			List<Advertise> list=new ArrayList<Advertise>();
			Advertise ad=null;
			for (AdList tmpAd : adList) {
				ad=BeanUtils.getAdvertise(tmpAd);
				PlacementDaoImpl placementDao = AppContext.getApplicationContext().getBean("placementDao", PlacementDaoImpl.class);
				Placement entity = placementDao.getPlacement(ad.getPlacement_id());
				ad.setSlogan(entity.getSlogan());
				ad.setPriority(entity.getPriority());
				ad.setIcon_url(AppConstant.imageF+ entity.getIcon_url());
				ad.setStar_level(entity.getStar_level());
				ad.setConfig_id(entity.getConfig_id());
				ad.setOs(entity.getOs());
				ad.setAd_url("");
				PlacementPackageDaoImpl placementPackageDao = AppContext.getApplicationContext().getBean("placementPackageDao", PlacementPackageDaoImpl.class);
				PlacementPackage placementPackage = placementPackageDao.getPlacementPackage(ad.getPackage_id());
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
				}

				// 应用类别
				if (ad.getCategory_id() != null) {
					EscoreCategoryeDaoImpl escoreCategoryeDao = AppContext.getApplicationContext().getBean("escoreCategoryeDao", EscoreCategoryeDaoImpl.class);
					SysCategorye escoreCategorye = escoreCategoryeDao.getEscoreCategorye(ad.getCategory_id());
					if (escoreCategorye != null)
						ad.setCategory_name(escoreCategorye.getName());
				} else {
					ad.setCategory_id(0L);
					ad.setCategory_name("其他");
				}

				
				//generalJsonVo.setScoreUnit(scoreUnit);
				String size = "0";
				if (placementPackage != null) {
					size = PublicUtil.getSize(placementPackage.getFile_size()
							.longValue());
				}
				ad.setAd_package_size("大小：" + size);

				// 积分墙
				if (String.valueOf(typeId).equals(AppConstant.PAGE_WALLTYPE_LIST_SMALL)) {
					MaterielScoreDaoImpl materielScoreDao = AppContext.getApplicationContext().getBean("materielScoreDao", MaterielScoreDaoImpl.class);
					MaterielScore materielScore = materielScoreDao.getMaterielScore(entity.getId());
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
				} else if (String.valueOf(typeId).equals(AppConstant.PAGE_WALLTYPE_LIST_BIG)) {
					
					MaterielListDaoImpl materielListDao = AppContext.getApplicationContext().getBean("materielListDao", MaterielListDaoImpl.class);
					MaterielList materielList = materielListDao.getMaterielList(entity.getId());
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
				} else if (String.valueOf(typeId).equals(AppConstant.PAGE_WALLTYPE_BANNER)) {
					
					MaterielBannerDaoImpl materielBannerDao = AppContext.getApplicationContext().getBean("materielBannerDao", MaterielBannerDaoImpl.class);

					MaterielBanner materielBanner = materielBannerDao.getMaterielBanner(entity.getId());
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
				} else if (String.valueOf(typeId).equals(AppConstant.PAGE_WALLTYPE_PLAQUE)) {
					MaterielChartboostDaoImpl materielChartboostDao = AppContext.getApplicationContext().getBean("materielChartboostDao", MaterielChartboostDaoImpl.class);

					MaterielChartboost materielChartboost = materielChartboostDao
							.getMaterielChartboost(entity.getId());
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
					

				if (ad.getResponse_type()== null) {
					ad.setResponse_type(0);
				}
				if(ad.getIs_url_params()==1){
					String urlParam = placementDao.getUrlParamByConfigId(ad.getConfig_id());
					ad.setUrl_params(StringUtil.dealNull(urlParam));
				}
				list.add(ad);
			}
			type.setAdvertiselist(list);
			MemcachedUtil.getInstance().add(AppConstant.MEMCACHE_TYPE + os + typeId+app_placement, type, new Date(AppConstant.CACHE_TIME));
			logger.info("cache typeId["+typeId+"] done :"+(System.currentTimeMillis()-a)+"ms");
		}	
		return type;
	}
	
	
	/**
	 * 
	* <p>Title: getPlacementReg</p>
	* <p>Description:TODO</p>
	* @param os
	* @param typeId
	* @param app_placement 是否单独投放
	* @return
	* @author cuidd
	* @date 2014年10月16日
	* @return String
	* @version 1.0
	 */
	public String getPlacementReg(String os, int typeId,Long app_placement) {
		Type1 t = getType(os, typeId,app_placement);
		List<Advertise> adList = t.getAdvertiselist();
		StringBuffer sbuf = new StringBuffer("(-1");
		for(Advertise ad:adList){
			sbuf.append("|"+ad.getPlacement_id());
		}
		sbuf.append(")");
		return sbuf.toString();
	}
}
