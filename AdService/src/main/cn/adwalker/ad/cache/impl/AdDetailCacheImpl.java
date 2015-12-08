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

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.adwalker.ad.bean.AdDetailPicture;
import cn.adwalker.ad.bean.SysCategorye;
import cn.adwalker.ad.cache.IAdCache;
import cn.adwalker.ad.cache.IAdDetailCache;
import cn.adwalker.ad.cache.ISystemConfigCache;
import cn.adwalker.ad.cache.element.AdDetailElement;
import cn.adwalker.ad.cache.element.Advertise;
import cn.adwalker.ad.cache.element.ServiceConfig;
import cn.adwalker.ad.cache.element.UserInfo;
import cn.adwalker.ad.dao.IAdvertiseDao;
import cn.adwalker.ad.dao.IEscoreCategoryeDao;
import cn.adwalker.ad.dao.IMaterielScoreDao;
import cn.adwalker.ad.dao.IPlacementDao;
import cn.adwalker.ad.dao.IPlacementPackageDao;
import cn.adwalker.ad.dao.IPlacementScreenshotDao;
import cn.adwalker.ad.dao.domain.Ad;
import cn.adwalker.ad.dao.domain.MaterielScore;
import cn.adwalker.ad.dao.domain.Placement;
import cn.adwalker.ad.dao.domain.PlacementPackage;
import cn.adwalker.ad.dao.domain.PlacementScreenshot;
import cn.adwalker.ad.pool.MemCached;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.DateUtil;
import cn.adwalker.ad.util.PublicUtil;
import cn.adwalker.ad.util.WallUtils;

/**
 * <p>Title: AdDetailCacheImpl</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-19
 */
@Service("adDetailCache")
public class AdDetailCacheImpl implements IAdDetailCache {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory
			.getLogger(AdDetailCacheImpl.class);
	
	
	@Resource
	private MemCached memCached;
	@Resource
	private IPlacementDao placementDao;	
	@Resource
	private IPlacementPackageDao placementPackageDao;
	@Resource
	private IAdvertiseDao advertiseDao;
	@Resource
	private IAdCache advCache;	
	@Resource
	private IPlacementScreenshotDao campaignScreenshotDao;
	@Resource
	private IEscoreCategoryeDao escoreCategoryeDao;
	@Resource
	private ISystemConfigCache systemConfigCache;
	@Resource
	private IMaterielScoreDao materielScoreDao; //add by jief 2013-09-06
	/**  
	* <p>Title: getAdDetail</p>
	* <p>Description:TODO</p>
	* @param id
	* @param userInfo
	* @return
	* @see cn.adwalker.ad.cache.IAdDetailCache#getAdDetail(long, cn.adwalker.ad.cache.element.UserInfo)
	*/
	@Override
	public AdDetailElement getAdDetail(long id, UserInfo userInfo,String version,String os,String type) {
		AdDetailElement adDetail = (AdDetailElement) memCached.get(AppConstant.MEMCACHE_AD_DETAIL+id);	 
		if(adDetail == null){
			adDetail =new AdDetailElement();
			Ad adv = advertiseDao.getAdvertise(id);
			Placement placement = placementDao.getPlacement(adv.getPlacement_id());
			PlacementPackage placementPackage = placementPackageDao.getPlacementPackage(adv.getPackage_id());
		    //add by jief2013-09-06
			MaterielScore materielScore = materielScoreDao.getMaterielScore(placement.getId());
			adDetail.setDetail_icon_Url(AppConstant.imageF + placement.getIcon_url());
			adDetail.setDetail_seventh(placement.getCampaign_desc());	
			adDetail.setDetail_first(adv.getAd_name());
			ServiceConfig systemConfig = systemConfigCache.getSystemConfig();
			//add by jief 2013-09-06
			if(null==materielScore || StringUtils.isBlank(materielScore.getScore_long_desc())){
				adDetail.setScore_msg(systemConfig.getScore_explain());
			}else{
				adDetail.setScore_msg(materielScore.getScore_long_desc());
			}
			if(placementPackage!=null){
				adDetail.setDetail_second("更新："+DateUtil.getDateStringByPattern(placementPackage.getUpdate_time(),"yyyy-MM-dd"));			
				adDetail.setDetail_third("版本："+placementPackage.getVersion_code());
				adDetail.setDetail_fourth("大小："+PublicUtil.getSize(placementPackage.getFile_size().longValue()));
				adDetail.setResourceUrl(placementPackage.getRes_url());
			    if (placement.getOs().equals(AppConstant.OS_IOS)) {
			    	adDetail.setResourceUrl(placementPackage.getRes_url());
				}else{
					adDetail.setResourceUrl(AppConstant.resF
							+ placementPackage.getRes_url());
				}
				
			    adDetail.setResourceSize(placementPackage.getFile_size()
						.longValue());
			    
			    adDetail.setPackageName(placementPackage
						.getPackage_name());
			   
			}else{
				adDetail.setDetail_second("更新："+DateUtil.getDateStringByPattern(new Date(),"yyyy-MM-dd"));			
				adDetail.setDetail_third("版本："+"1.1.1");
				adDetail.setDetail_fourth("大小：2M");
			}
			adDetail.setDetail_sixth("软件介绍");
			if(placement.getCategory_id()!=null){
				SysCategorye escoreCategorye = escoreCategoryeDao.getEscoreCategorye(placement.getCategory_id());
				String category_name = "其他";
				if(escoreCategorye!=null)
					category_name = escoreCategorye.getName();
				adDetail.setCategory_name("类型："+category_name);
				adDetail.setCategory_id(placement.getCategory_id().longValue());
			}else{
				adDetail.setCategory_name("类型：其他");
				adDetail.setCategory_id(0l);
			}
			//图片
			List<PlacementScreenshot> campaignScreenshotList = campaignScreenshotDao.findCampaignScreenshot(placement.getId());
			List<AdDetailPicture> piclist =new ArrayList<AdDetailPicture>();
			for(PlacementScreenshot campaignScreenshot:campaignScreenshotList){
				AdDetailPicture ap =new AdDetailPicture();
				ap.setDetail_picture_Url(AppConstant.imageF+campaignScreenshot.getImg_url());
				piclist.add(ap);
			}
			adDetail.setAdDetailPicture(piclist);
			memCached.add(AppConstant.MEMCACHE_AD_DETAIL+id, adDetail, new Date(AppConstant.CACHE_TIME));
		}
		//change by jief 为了贵宝的h5 2013-09-14
		//获取广告
		Advertise ad=advCache.getAdv(id);
		if (ad!=null && AppConstant.PAGE_WALLTYPE_LIST_SMALL.equals(ad.getType_id())) {
			if (userInfo != null && userInfo.getSignAdRels()!=null){
				adDetail.setIsDownload(WallUtils.getIsDownload(id, ad.getIsDownload(),userInfo.getSignAdRels()));
			}else{
				adDetail.setIsDownload(ad.getIsDownload());
			}
		}
		return adDetail;
	}

	/**  (non-Javadoc)
	* <p>Title: replaceAdDetail</p>
	* <p>Description:TODO</p>
	* @param id
	* @return
	* @see cn.adwalker.ad.cache.IAdDetailCache#replaceAdDetail(long)
	*/
	@Override
	public void replaceAdDetail(long id) {
		memCached.clear(AppConstant.MEMCACHE_AD_DETAIL+id);
	}

	
	
}
