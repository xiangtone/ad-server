package cn.adwalker.ad.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cn.adwalker.ad.cache.element.Advertise;
import cn.adwalker.ad.cache.element.ServiceConfig;
import cn.adwalker.ad.cache.element.UserAdRel;
import cn.adwalker.ad.dao.domain.MaterielList;
import cn.adwalker.ad.dao.domain.MaterielScore;
import cn.adwalker.ad.picker.util.DateUtil;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.rules.RulesUtil;
import cn.adwalker.ad.rules.strategy.BannerStrategy;
import cn.adwalker.ad.rules.strategy.PlaqueStrategy;
import cn.adwalker.ad.rules.strategy.StrategyPic;

public abstract class WallUtils {
	
	public static List<Advertise>  adFilter(List<Advertise> advertiseList,ServiceConfig config, String ad_res,String quickly_task){
		if (advertiseList!=null) {
			List<Advertise> removeList = new ArrayList<Advertise>();
			for (Advertise advertise:advertiseList) {
				if (advertise!=null&&!StringUtil.isEmpty(advertise.getAd_res_code())) {
					if (advertise.getAd_res_code().equals(AppConstant.AD_RES_CODE_KUAIYOU)) {
						if (config.getAd_res_switch_kuaiyou().equals("0")) {
							removeList.add(advertise);
						}
					}else if (advertise.getAd_res_code().equals(AppConstant.AD_RES_CODE_CHUKONG)) {
						if (config.getAd_res_switch_chukong().equals("0")) {
							removeList.add(advertise);
						}
					}else if (advertise.getAd_res_code().equals(AppConstant.AD_RES_CODE_PLATFORM)) {
						if (config.getAd_res_switch_platform().equals("0")) {
							removeList.add(advertise);
						}
					}else if (advertise.getAd_res_code().equals(AppConstant.AD_RES_CODE_YOUMI)) {
						if (config.getAd_res_switch_youmi().equals("0")) {
							removeList.add(advertise);
						}
					}
					if (!"ADWALKER".equals(advertise.getAd_res_code())) {
						if (!advertise.getAd_res_code().matches(ad_res)) {
							removeList.add(advertise);
						}
					}
				}
				//过滤快速任务
				if (!StringUtil.isEmpty(quickly_task)&&quickly_task.equals("1")) {
					if (advertise.getFast_task()==null||advertise.getFast_task()!=1) {
						removeList.add(advertise);
					}
				}else {
					if (advertise.getFast_task()!=null&&advertise.getFast_task()==1) {
						removeList.add(advertise);
					}
					
				}
				
			}
			advertiseList.removeAll(removeList);
		}
		return  advertiseList;
	}
	
	public static List<Advertise>  adFilter(List<Advertise> advertiseList,ServiceConfig config, String ad_res){
		return  adFilter(advertiseList, config, ad_res, null);
	}
	
	
	/**
	 * 
	* <p>Title: getPicture</p>
	* <p>Description:配置图片信息</p>
	* @param wallType
	* @param imageType
	* @param ad
	* @param os
	* @return
	* @author cuidd
	* @date 2014年11月18日
	* @return Advertise
	* @version 1.0
	* @throws
	 */
	public static Advertise getPicture(String wallType, String imageType,
			Advertise ad, String os) {
		StrategyPic pic = null;
		if (AppConstant.PAGE_WALLTYPE_BANNER.equals(wallType)) {
			pic = new BannerStrategy();
			ad = pic.getPicture(wallType, imageType, ad, os);
		} else if (AppConstant.PAGE_WALLTYPE_PLAQUE.equals(wallType)) {
			pic = new PlaqueStrategy();
			ad = pic.getPicture(wallType, imageType, ad, os);
		} else if (AppConstant.PAGE_WALLTYPE_LIST_SMALL.equals(wallType)) {
			MaterielScore materielScore=(MaterielScore) ad.getWall();
			if (materielScore!=null && materielScore.getBanner_url() != null) {
				ad.setAdimage_url(AppConstant.imageF + materielScore.getBanner_url());
			} else {
				ad.setAdimage_url("");
			}
			ad.setAdimage_width(RulesUtil.IMAGGE_TYPE_1_WIDTH);
			ad.setAdimage_height(RulesUtil.IMAGGE_TYPE_1_HEIGHT);
		} else {
			MaterielList materielList=(MaterielList) ad.getWall();
			if (materielList!=null && materielList.getBanner_url() != null) {
				ad.setAdimage_url(AppConstant.imageF+ materielList.getBanner_url());
			} else {
				ad.setAdimage_url("");
			}
			ad.setAdimage_width(RulesUtil.IMAGGE_TYPE_1_WIDTH);
			ad.setAdimage_height(RulesUtil.IMAGGE_TYPE_1_HEIGHT);
		}
		return ad;
	}
	
	
	/**
	 * <p>
	 * Title: getAcNum
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param userAdList
	 * @return
	 * @return int
	 * @throws
	 */
	@Deprecated
	public static int getAcNum(List<UserAdRel> userAdList, int flag,int limit) {

		// 判断每天的重复激活
		int acNum = 0;
		java.util.Date nowdate = new java.util.Date();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd 00:00:00",Locale.CHINA);
		SimpleDateFormat e = new SimpleDateFormat("yyyy-MM-dd 23:59:59",Locale.CHINA);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
		String start = s.format(nowdate);
		String end = e.format(nowdate);
		for (UserAdRel userAdRel : userAdList) {
			String createTime = sdf.format(userAdRel.getCreate_time());
			if (createTime.compareTo(start) > 0&& createTime.compareTo(end) <= 0) {
				acNum++;
			}
		}
		if (acNum >= limit) {
			flag = AppConstant.SCORE_FLAG;
		}

		return flag;
	}
	
	
	public static boolean isIos7(String mac,String os_version){
		return StringUtil.equals("020000000000",mac)||StringUtil.parseVersion(os_version)>=7;
	}
	
	
	public static boolean isIos6(String os_version){
		return StringUtil.parseVersion(os_version)<7;
	}
	
	
	/**
	 * 广告签到下载状态  //0:广告未被下载，1激活,2一次签到，3二次签到,-1当天广告状态激活后状态
	* <p>Title: getAdScore</p>
	* <p>Description:TODO</p>
	* @param price
	* @param exchange_rate_rmb
	* @param sign
	* @return
	* @return int
	* @throws
	 */
	public static int getIsDownload(long adId,int isDownload,List<UserAdRel> userAdRels) {
		int result = isDownload;
		if(userAdRels!=null && userAdRels.size()>0){
			for(UserAdRel userAdRel:userAdRels){
				if(userAdRel.getAd_id().equals(adId)){								
					result= userAdRel.getCreate_time()!=null && DateUtil.get00HourDate(new Date()).before(userAdRel.getCreate_time())?-1:userAdRel.getSign_num();
					//flag=1;
					break;
				}
			}
		}
		/**
		if(result == AppConstant.DOWNLOAD&&flag==0){
			result = -1;
		}
		*/
		//弥补SDK3.1.5bug
		if(result==3){
			result=4;
		}
		return result;
	}
	
	
	
	
	
	

}
