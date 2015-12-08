package cn.adwalker.ad.util;

import java.util.ArrayList;
import java.util.List;

import cn.adwalker.ad.cache.element.Advertise;
import cn.adwalker.ad.cache.element.PackageInfo;
import cn.adwalker.ad.dao.domain.AdList;
import cn.adwalker.ad.dao.domain.MaterielScore;
import cn.adwalker.ad.dao.domain.PlacementPackage;
import cn.adwalker.ad.picker.util.DateUtil;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.vo.AdJson;
import cn.adwalker.ad.vo.GeneralJson;

public abstract class BeanUtils {

	public static PackageInfo packageInfo(PlacementPackage entity){
		PackageInfo p=null;
		if (entity!=null) {
			p=new PackageInfo();
			p.setRes_url(entity.getRes_url());
			p.setFile_name(entity.getFile_name());
			p.setFile_size(entity.getFile_size());
			p.setPackage_name(entity.getPackage_name());
			p.setVersion_code(entity.getVersion_code());
			p.setUpdate_time(entity.getUpdate_time());
		}
		return p;
	}
	
	
	public static Advertise getAdvertise(AdList tmpAd) {
		Advertise ad = new Advertise();
		ad.setPlacement_id(tmpAd.getPlacement_id());
		ad.setId(tmpAd.getId());
		ad.setBlance_price(tmpAd.getBlance_price());
		ad.setBlance_mode(tmpAd.getBlance_mode());
		ad.setType_id(tmpAd.getType_id());
		ad.setCreate_time(tmpAd.getCreate_time());
		ad.setPackage_id(tmpAd.getPackage_id());
		ad.setCategory_id(tmpAd.getCategory_id());
		ad.setStatus(tmpAd.getStatus());
		ad.setPopular_app(tmpAd.getPopular_app());
		ad.setIs_dsp(tmpAd.getIs_dsp());
		ad.setDsp_id(tmpAd.getDsp_id());
		ad.setApp_type_str(tmpAd.getApp_type_str());
		ad.setFast_task(tmpAd.getFast_task());//快速任务
		ad.setAd_name(tmpAd.getAd_name());
		ad.setEnd_time(tmpAd.getEnd_time());
		ad.setBudget_day(tmpAd.getBudget_day());
		ad.setTerminal_type_str(tmpAd.getTerminal_type_str());
		ad.setOperat_agencies_str(tmpAd.getOperat_agencies_str());
		ad.setPhone_brand_str(tmpAd.getPhone_brand_str());
		ad.setTime_directional_str(tmpAd.getTime_directional_str());
		ad.setArea_directional_str(tmpAd.getArea_directional_str());
		ad.setSdk_version_str(tmpAd.getSdk_version_str());
		ad.setApp_type_str(tmpAd.getApp_type_str());
		ad.setAd_mark(tmpAd.getAd_mark());
		return ad;
	}
	
	public static AdJson getAdJsonList(Advertise ad,
			String typeId, int frequency, Long appId,
			int delay_time, String default_score_explain) {
			AdJson adJson = new AdJson();
			GeneralJson general=new GeneralJson();
			general.setWall_icon_Url(ad.getIcon_url());
			general.setWall_left_first("安装激活");// (0-描述 1-描述 2-空)----写死的值，没用
			general.setWall_left_second(ad.getAd_name());// (0-名称 1-名称 2-名称)
			general.setWall_left_third(ad.getAd_package_size());// (0-版本号 1-版本号+大小 2-空)
			general.setWall_right(String.valueOf(ad.getShow_score())+ ad.getScoreUnit());// (0-大小 1-积分 2-空)
			general.setWall_desc(ad.getSlogan());// (0-大小 1-积分 2-空)
			String version_code="";
			if (ad.getPackageInfo()!=null) {
				version_code=ad.getPackageInfo().getVersion_code();
			}
			
			version_code="版本号：" + version_code;
			general.setWall_version(version_code);// (0-大小 1-积分 2-空)
			general.setScore(ad.getShow_score()!=null?ad.getShow_score():0);// 积分墙分数
			general.setScoreUnit(ad.getScoreUnit());// 积分墙单位
			adJson.setGeneral(general);
			adJson.setId(ad.getId());
			adJson.setAdimage_url(ad.getAdimage_url());
			adJson.setAdimage_height(ad.getAdimage_height());
			adJson.setAdimage_width(ad.getAdimage_width());

			adJson.setTitle(ad.getAd_name());
			adJson.setPage_type(Integer.parseInt(typeId));
			adJson.setInterval(frequency);		
			adJson.setKeyword(ad.getKeyword());
			
			adJson.setAd_type(StringUtil.dealNull(null,"0").equals("1")?3:ad.getResponse_type());
			adJson.setAd_url(ad.getAd_url());
			adJson.setStar_level(ad.getStar_level());
			if (ad.getPriority() != null)
				adJson.setPriority(ad.getPriority());
				adJson.setCategory(ad.getCategory_name());
				adJson.setIsDownload(ad.getIsDownload());
			//changed by jierfei 2013-08-23 积分墙延迟给分  start
			//adJson.setDelay_time(delay_time);
			if(typeId.equals(AppConstant.PAGE_WALLTYPE_LIST_SMALL)&& 
					 null!=ad.getWall()){
				MaterielScore materielScore=(MaterielScore) ad.getWall();
				Integer delayTime=materielScore.getScore_delay();
				adJson.setDelay_time(delayTime==null?delay_time:delayTime);	
				adJson.setSearch_desc(materielScore.getWeixin_desc());
			 }else{
				adJson.setDelay_time(delay_time);	
			 }
			//changed by jierfei 2013-08-23 延迟给分 end
			//add bu jief 2013-10-25 修改积分描述
			if (ad.getType_id() == 0) {
				String score_msg=default_score_explain;
				if (null!=ad.getWall() ) {
					MaterielScore materielScore=(MaterielScore) ad.getWall();
					if (!StringUtil.isEmpty(materielScore.getScore_desc())) {
						 score_msg=materielScore.getScore_desc();
					}
				} 
				adJson.setScore_msg(score_msg);
			}
			
			
			// 包信息
			if (ad.getPackageInfo() != null) {
				adJson.setApp_url(ad.getApp_url());
				adJson.setPackageName(ad.getPackageInfo().getPackage_name());
				adJson.setResourceSize(ad.getPackageInfo().getFile_size()
						.longValue());
				adJson.setResourceUrl(ad.getPackageInfo().getRes_url());
				adJson.setFileName(ad.getPackageInfo().getFile_name());
			} else {
				adJson.setApp_url("");
				adJson.setPackageName("com.adwalker.cn");
				adJson.setResourceSize(10000000l);
				adJson.setResourceUrl("");
				adJson.setFileName("");
			}
			adJson.setCreate_time(DateUtil.getFormatDate(ad.getCreate_time(), "yyyy-MM-dd"));
			adJson.setApple_id(!StringUtil.isEmpty(ad.getAppstore_id())?Integer.valueOf(ad.getAppstore_id()):0);
			adJson.setAppid(ad.getConfig_id());
			adJson.setIs_url_params(ad.getIs_url_params());
			adJson.setUrl_params(ad.getUrl_params());
		return adJson;
	}
	
	
	/**
	 * 
	* <p>Title: adCluster</p>
	* <p>Description:集群广告信息处理</p>
	* @param tmpList
	* @param os
	* @param page_type
	* @return
	* @author cuidd
	* @date 2014年10月20日
	* @return List<Advertise>
	* @version 1.0
	 * @param appId 
	 * @param deveiceId 
	 * @param mac 
	 * @param ip 
	 * @param uuid 
	 */
	public static List<Advertise> adCluster(List<Advertise> tmpList, String os, String page_type, String uuid, String ip, String mac, String deveiceId, Long appId) {
		List<Advertise> list = new ArrayList<Advertise>();
//		if (os.equals(AppConstant.OS_IOS)&&page_type.equals(AppConstant.PAGE_WALLTYPE_LIST_SMALL)) {
//			Map<String,String> map=new HashMap<String, String>();
//			map.put("uuid", uuid);
//			map.put("ip", ip);
//			map.put("mac", mac);
//			map.put("deveiceId", deveiceId);
//			map.put("app_id",String.valueOf(appId));
//			for (Advertise ad:tmpList) {
//				if (!ad.getRes_code().equals(AppConstant.AD_RES_CODE_SELF)) {
//					//如果是外接广告，拼接appstore地址，转到api上
//					String url=AppConstant.AD_CLUSTER_CLICK_URL+"";
//					map.put("ad_id",String.valueOf(ad.getId()));
//					if (map!=null&&!map.isEmpty()) {
//						StringBuilder sb=new StringBuilder();
//						Set<String> keySet=map.keySet();
//						for (String key:keySet) {
//							sb.append("&").append(key).append("=").append(StringUtil.encode(map.get(key)));
//						}
//						if (url.indexOf("?")!=-1) {
//							url=url+sb.toString();
//						}else {
//							url=url+"?t="+System.currentTimeMillis()+sb.toString();
//						}
//					}
//					ad.setApp_url(url);
//				}
//				list.add(ad);
//			}
//		}else {
//			list=tmpList;
//		}
		
		list=tmpList;
		return list;

	}
	
	
	
	
	
	
	

}
