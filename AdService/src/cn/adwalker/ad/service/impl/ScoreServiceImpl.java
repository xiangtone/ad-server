package cn.adwalker.ad.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.adwalker.ad.bean.DevaddScoreBean;
import cn.adwalker.ad.cache.IDevAppCache;
import cn.adwalker.ad.cache.IUserInfoCache;
import cn.adwalker.ad.cache.element.UserInfo;
import cn.adwalker.ad.dao.ICurrencyDao;
import cn.adwalker.ad.dao.IMediaUserDao;
import cn.adwalker.ad.dao.IUserInfoDao;
import cn.adwalker.ad.exception.AdwalkerException;
import cn.adwalker.ad.service.IAppScoreService;
import cn.adwalker.ad.service.IScoreService;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.vo.Score;

@Service("scoreService")
public class ScoreServiceImpl implements IScoreService {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory
			.getLogger(ScoreServiceImpl.class);

	@Resource
	private IAppScoreService appScoreService;
	

	@Resource
	private IUserInfoCache userInfoCache;
	
	@Resource
	private IUserInfoDao userInfoDao;
	
	@Resource
	private ICurrencyDao currencyDao;

	@Resource
	private IDevAppCache devAppCache;
	
	
	@Resource
	private IMediaUserDao mediaUserDao;


	/**
	 * (non-Javadoc)
	* <p>Title: doDevScore</p>
	* <p>Description:开发者积分处理</p>
	* @param score
	* @param appId
	* @param channel
	* @param adId
	* @param uuid
	* @param page_Type
	* @param price
	* @param sdkversion
	* @param categoryid
	* @param pay_type
	* @param signNum
	* @param ip
	* @see cn.adwalker.ad.service.IScoreService#doDevScore(java.lang.Double, java.lang.Long, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.Double, java.lang.String, java.lang.Integer, java.lang.String, int, java.lang.String)
	 */
	public void doDevScore(Double score, Long appId, String channel,
			Long adId, String uuid, String page_Type, Double price,
			String sdkversion, Integer categoryid, String pay_type,int signNum,String ip){
		// 计算开发者获得的钱 score/moneyScoreRate
		String area_code = "全国";
		// 终端用户信息实体
		UserInfo userInfo = userInfoCache.getUserInfo(uuid);
		
		if (userInfo != null && userInfo.getAreaCode() != null) {
			area_code = userInfo.getAreaCode();
		}
		DevaddScoreBean devasb = new DevaddScoreBean();
		devasb.setUuid(uuid);
		devasb.setAd_id(adId);
		devasb.setArea(area_code);
		devasb.setId(appId);
		devasb.setChannel(channel);
		if (page_Type.equals(AppConstant.PAGE_WALLTYPE_LIST_SMALL)) {
			devasb.setScore(score);
		} else {
			devasb.setScore(0d);
		}
		devasb.setPage_Type(page_Type);
		if (price == null) {
			devasb.setPrice(0d);
		} else {
			devasb.setPrice(price);
		}
		devasb.setSdkversion(sdkversion);
		devasb.setCategoryid(String.valueOf(categoryid));
		devasb.setPrice(price);
		devasb.setPay_type(pay_type);
		devasb.setIp(ip);
		devasb.devAddScorelogInfo(signNum);
	}

	@Override
	public void updateConfig(String uuid, String devUserId,String appId,String os) {
		mediaUserDao.initMediaUser(uuid, devUserId,appId,os);
	}

	@Override
	public String findMediaUser(String uuid,String appId) {
		return mediaUserDao.findMediaUser(uuid,appId);
	}

	
	/**  (non-Javadoc)
	* <p>Title: getScore</p>
	* <p>Description:TODO</p>
	* @param uuid
	* @return
	 * @throws AdwalkerException 
	* @see cn.adwalker.ad.service.IScoreService#getScore(java.lang.String)
	*/
	@Override
	public Score getScore(String uuid,long appId,String sdkversion) throws AdwalkerException {		
		Score score  = appScoreService.getScore(uuid,appId,sdkversion); 
		return score;
	}
}
