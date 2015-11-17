/**
 * <p>Title: AdWallServiceImpl.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2014-03-14
 * @version 1.0
 */
package cn.adwalker.ad.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.adwalker.ad.OS;
import cn.adwalker.ad.cache.IMediaUserCache;
import cn.adwalker.ad.cache.element.UserAdRel;
import cn.adwalker.ad.dao.IAndroidActivateLogDao;
import cn.adwalker.ad.dao.IAndroidDownloadLogDao;
import cn.adwalker.ad.dao.domain.AndroidActivateLog;
import cn.adwalker.ad.dao.domain.AndroidDownloadLog;
import cn.adwalker.ad.dao.domain.MediaUser;
import cn.adwalker.ad.exception.AdwalkerException;
import cn.adwalker.ad.service.IAdAgentService;
import cn.adwalker.ad.service.IAppScoreService;
import cn.adwalker.ad.service.vo.AInfoAndroid;
import cn.adwalker.ad.service.vo.DInfoAndroid;

/**
 * <p>
 * Title: AdWallServiceImpl
 * </p>
 * <p>
 * Description:广告墙服务实现
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author www.adwalker.cn
 * @date 2013-5-27
 */
@Service
public class AdAgentServiceImpl implements IAdAgentService {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory
			.getLogger(AdAgentServiceImpl.class);

	@Resource
	private IAndroidDownloadLogDao adAndroidDownloadLogDao;
	
	
	@Resource
	private IAndroidActivateLogDao androidActivateLogDao;
	
	
	
	@Resource
	private IMediaUserCache mediaUserCache;
	
	@Resource
	private IAppScoreService appScoreService;

	@Override
	public void logD(DInfoAndroid log) {
		if (log!=null) {
			AndroidDownloadLog entity=new AndroidDownloadLog();
			entity.setMac_address(log.getMac_address());
			entity.setAndroid_id(log.getAndroid_id());
			entity.setAdid(log.getAdid());
			entity.setBundleid(log.getBundleid());
			entity.setIsroot(log.getRoot());
			entity.setImei(log.getImei());
			entity.setUserid(log.getUserid());
			entity.setImsi(log.getImsi());
			entity.setPhonenum(log.getPhonenum());
			entity.setCreate_time(new Date());
			adAndroidDownloadLogDao.insert(entity);
		}
	}

	@Override
	public void logA(AInfoAndroid log) throws AdwalkerException {
		if (log!=null) {
			//存储日志，发送积分确认
			AndroidActivateLog entity=new AndroidActivateLog();
			entity.setMac_address(log.getMac_address());
			entity.setAndroid_id(log.getAndroid_id());
			entity.setAdid(log.getAdid());
			entity.setBundleid(log.getBundleid());
			entity.setIsroot(log.getRoot());
			entity.setImei(log.getImei());
			entity.setUserid(log.getUserid());
			entity.setImsi(log.getImsi());
			entity.setPhonenum(log.getPhonenum());
			entity.setCreate_time(new Date());
			androidActivateLogDao.insert(entity);
			
			MediaUser mediaUser=mediaUserCache.getMediaUser(log.getUserid(),OS.android);
				String uuid=mediaUser.getUuid();
				String channel="weixin";
				String ip="";
				String version="";
			Long appId=Long.valueOf(mediaUser.getApp_id());
			Long ad_id=Long.valueOf(log.getAdid());
			UserAdRel uar = appScoreService.saveOrUpdateUserAdRel(appId,ad_id,uuid,log.getImsi(),ip);
			appScoreService.appendScore(appId,ad_id,uuid,channel,"0",log.getUserid(),version,ip, uar);
			
			
		}
	}
}
