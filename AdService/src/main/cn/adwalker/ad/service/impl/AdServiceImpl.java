/**
* <p>Title: AdServiceImpl.java</p>
* <p>Description:</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-1-4
* @version 1.0
*/
package cn.adwalker.ad.service.impl;

import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.adwalker.ad.cache.IAdCache;
import cn.adwalker.ad.cache.IAdDetailCache;
import cn.adwalker.ad.cache.IUserInfoCache;
import cn.adwalker.ad.cache.element.AdDetailElement;
import cn.adwalker.ad.cache.element.AdDetailVo;
import cn.adwalker.ad.cache.element.Advertise;
import cn.adwalker.ad.cache.element.UserInfo;
import cn.adwalker.ad.dao.IMediaUserDao;
import cn.adwalker.ad.dao.domain.MaterielScore;
import cn.adwalker.ad.logger.LoggerManager;
import cn.adwalker.ad.param.AdDetailParam;
import cn.adwalker.ad.param.AdDetailParamIos;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.service.IAdService;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.vo.AdDetailIos;
import cn.adwalker.ad.vo.AdDetailJson;
import cn.adwalker.core.utils.JacksonMapper;



/**
 * <p>Title: AdServiceImpl</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-1-4
 */
@Service("adService")
public class AdServiceImpl implements IAdService {
	private static final Logger log = LoggerFactory.getLogger(AdServiceImpl.class);
	

	@Resource
	private IUserInfoCache userInfoCache;
	@Resource
	private IAdCache advCache;
	@Resource
	private IAdDetailCache adDetailCache;
	
	@Resource
	private IMediaUserDao mediaUserDao;
	
	
	/**
	 * (non-Javadoc)
	* <p>Title: getAdDetail</p>
	* <p>Description:获取广告详情</p>
	* @param adId
	* @return
	 * @throws Exception 
	* @see cn.adwalker.ad.service.IAdService#getAdDetail(int)
	 */
	@Override
	public AdDetailJson getAdDetail(AdDetailParamIos param) throws Exception {
		UserInfo userInfo = userInfoCache.getUserInfo(param.getUuid());	
		Advertise  adv =(Advertise)advCache.getAdv(param.getAdId());	
		AdDetailJson adDetail = this.getAdDetailJson(param.getAdId(), userInfo,param.getVersion(),param.getOs());
		/**
		 * 日志
		 */			
		if(adv!=null){
				//终端用户信息实体
			String area="";
			if(userInfo != null){		
				area = userInfo.getAreaCode();
			}
			if(param.getTerminalType()!=null&&param.getTerminalType().equals(AppConstant.PAD)){
			 
			}else{
				LoggerManager.adDetailsInfo(adv,param.getAppId(), adv.getType_id().toString(), param.getChannel(),param.getUuid(), area,param.getIp(),param.getVersion(),param.getTerminalType(),param.getImsi());
			}
		}else{
			log.info(" db.ad is null id="+param.getAdId());
		}
		return adDetail;
	}
	
	
	/**
	 * 
	* <p>Title: getAdDetailJson</p>
	* <p>Description:类功能拆分---尽量能把缓存给独立出来</p>
	* @param ad_id
	* @param userInfo
	* @param version
	* @param os
	* @return
	* @author cuidd
	* @date 2014年10月15日
	* @return AdDetailJson
	* @version 1.0
	 */
	private AdDetailJson getAdDetailJson(Long ad_id,UserInfo userInfo,String version,String os){
		AdDetailJson json=new AdDetailJson();
		AdDetailElement element=adDetailCache.getAdDetail(ad_id, userInfo,version,os,null);
		if (element!=null) {
			AdDetailVo vo=new AdDetailVo();
			vo.setDetail_icon_Url(element.getDetail_icon_Url());
			vo.setDetail_first(element.getDetail_first());
			vo.setDetail_second(element.getDetail_second());
			vo.setDetail_third(element.getDetail_third());// 版本
			vo.setDetail_fourth(element.getDetail_fourth()); // 大小
			vo.setDetail_fifth(element.getDetail_fifth());// (0-空 1-积分 2-空)
			vo.setDetail_sixth(element.getDetail_sixth());// 文字:软件介绍
			vo.setDetail_seventh(element.getDetail_seventh());// 介绍内容
			vo.setCategory_id(element.getCategory_id());
			vo.setAdDetailPicture(element.getAdDetailPicture());// 界面图片(多)
			vo.setCategory_name(element.getCategory_name());
			vo.setIsDownload(element.getIsDownload());//0:广告未被下载，1被下载
			vo.setResourceUrl(element.getResourceUrl());// 资源URL
			vo.setResourceSize(element.getResourceSize());// 资源大小
			vo.setPackageName(element.getPackageName());// 包名称
			vo.setScore_msg(element.getScore_msg());//积分获取详细说明 jief2013-09-06
			json.setAdDetail(vo);
		}
		 return json;
	}

	
	@Override
	public AdDetailJson getAdDetail(AdDetailParam vo,String os) throws Exception {
		UserInfo userInfo = userInfoCache.getUserInfo(vo.getUuid());	
		Advertise  adv =(Advertise)advCache.getAdv(vo.getAdId());	
		AdDetailJson adDetail =this.getAdDetailJson(vo.getAdId(), userInfo, vo.version, os);

		/**
		 * 日志
		 */			
		if(adv!=null){
				//终端用户信息实体
			String area="";
			if(userInfo != null){		
				area = userInfo.getAreaCode();
			}
			
			//pad终端不记日志 原因待确认 
			if(!StringUtil.equals(vo.getTerminalType(), AppConstant.PAD)){
				LoggerManager.adDetailsInfo(adv,vo.getAppId(), StringUtil.dealNull(vo.getPage_type(),adv.getType_id().toString()), vo.getChannel(), vo.getUuid(), area, vo.ip,vo.version, vo.getTerminalType(), vo.getImsi());
			}
			
			/**
			if(terminalType!=null&&terminalType.equals(AppConstant.PAD)){
			 
			}else{
				 adv.adDetailsInfo(appId, adv.getType_id().toString(), channel, uuid, area, ip, version, terminalType, imsi);
			}*/
		}else{
			log.info(" db.ad is null id="+vo.getAdId());
		}
				
		
		
		return adDetail;
	}


	@Override
	public AdDetailIos getAdDetail(Long adId,String os,String mediaUserId) throws IOException {
		AdDetailIos vo=new AdDetailIos();
		Advertise  ad =(Advertise)advCache.getAdv(adId);
		adDetailCache.getAdDetail(adId, null,null,os,null);
		System.out.println(JacksonMapper.objectToJsonString(ad));
		vo.setAd_name(ad.getAd_name());
		vo.setIcon_url(ad.getIcon_url());
		vo.setSlogan(ad.getSlogan());
		MaterielScore materielScore=(MaterielScore) ad.getWall();
		vo.setScore_desc(materielScore.getScore_desc());
		vo.setClick_url(ad.getApp_url());
		
		return vo;
	}
	
}
