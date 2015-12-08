/**
* <p>Title: AppScoreServiceImpl.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-5-15
* @version 1.0
*/
package cn.adwalker.ad.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.adwalker.ad.OS;
import cn.adwalker.ad.bean.PickerConfig;
import cn.adwalker.ad.bean.UserAddScore;
import cn.adwalker.ad.cache.IAdCache;
import cn.adwalker.ad.cache.IDevAppCache;
import cn.adwalker.ad.cache.IMediaUserCache;
import cn.adwalker.ad.cache.ISystemConfigCache;
import cn.adwalker.ad.cache.IUserInfoCache;
import cn.adwalker.ad.cache.IUserScoreCache;
import cn.adwalker.ad.cache.element.Advertise;
import cn.adwalker.ad.cache.element.DevApp;
import cn.adwalker.ad.cache.element.ServiceConfig;
import cn.adwalker.ad.cache.element.UserAdRel;
import cn.adwalker.ad.cache.element.UserInfo;
import cn.adwalker.ad.cache.element.UserScore;
import cn.adwalker.ad.dao.IUserAdRelDao;
import cn.adwalker.ad.dao.IUserScoreDao;
import cn.adwalker.ad.dao.domain.MediaUser;
import cn.adwalker.ad.exception.AdwalkerException;
import cn.adwalker.ad.exception.ExceptionCode;
import cn.adwalker.ad.logger.LoggerManager;
import cn.adwalker.ad.logger.ScoreConsume;
import cn.adwalker.ad.picker.param.ActivateIosParam;
import cn.adwalker.ad.picker.thread.CallApi;
import cn.adwalker.ad.picker.thread.CallApiForScore;
import cn.adwalker.ad.picker.util.DateUtil;
import cn.adwalker.ad.picker.util.NumberUtil;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.service.IAppScoreService;
import cn.adwalker.ad.service.IScoreService;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.ConfigUtil;
import cn.adwalker.ad.util.PublicUtil;
import cn.adwalker.ad.vo.Score;
import cn.adwalker.core.utils.ScoreUtil;
/**
 * <p>Title: AppScoreServiceImpl</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-5-15
 */
@Service("appScoreService")
public class AppScoreServiceImpl implements IAppScoreService {	
	public final static Logger log = Logger.getLogger(AppScoreServiceImpl.class);
	public final static int CACHE_TIME = Integer.parseInt(ConfigUtil.getString("cache_time"));
	
	@Resource
	private IUserScoreDao userScoreDao;
	@Resource
	private IUserAdRelDao userAdRelDao;
	@Resource
	private IAdCache advCache;
	@Resource
	private IDevAppCache devAppCache;	
	@Resource
	private IUserInfoCache userInfoCache;	
	@Resource
	private IScoreService scoreService;	

	@Resource
	private IUserScoreCache userScoreCache;
	@Resource
	private ISystemConfigCache systemConfigCache;
	
	@Resource
	private IMediaUserCache mediaUserCache;
	

	
	/** 
	 * 激活信息  保存T_USER_AD_REL表
	 * @throws AdwalkerException 
	 */
	@Override
	public UserAdRel saveOrUpdateUserAdRel(Long appId,Long adId,String uuid,String imsi,String ip) throws AdwalkerException {
	   	String ipduan = ipdo(ip);
        if(checkActivate(adId, imsi, ipduan)){
        	Date d = new Date();
        	// uuid当日激活条数
        	Integer num = userAdRelDao.findUserAdRelCount(uuid, DateUtil.get00HourDate(d),DateUtil.get24HourDate(d));
     		UserAdRel userAd=userAdRelDao.findUserAdRelByUuidAndAdId(uuid, adId);
     		ServiceConfig conf = systemConfigCache.getSystemConfig();
     		Integer limit = conf.getScore_ac_limit();
     		//激活数小于每日最大激活数userAdRelDao
     		if(NumberUtil.getInt(limit, 0)>NumberUtil.getDouble(num, 0)){
     			if(null==userAd){
     				userAd=new UserAdRel(uuid,appId, adId, 1);
     				userAdRelDao.saveUserAdRel(userAd, ip, ipduan, imsi);
     			}else{
     				//今日已经激活过的数据不在激活.
     				if(userAd.getCreate_time().after(DateUtil.get00HourDate(new Date()))){
     					return null;
     				}
     				userAd.setSign_num(userAd.getSign_num()+1);
     				userAdRelDao.updateUserAdRel(userAd);
     			}
     		}else{
     			throw new AdwalkerException(PublicUtil.installErrorVO(ExceptionCode.SIGN_LIMIT));
     		}
     		return userAd;
        }else{
        	Logger.getLogger("refuse").info("ip: "+ip+"   adId:"+adId+"  imsi:"+imsi);        
        }
        return null;
	}
	
	
	
	//激活刷量控制判断
	private boolean checkActivate(Long adId,String imsi,String ipduan){
		/**
		 * imsi规则判断 imsi与adId只能激活一次
		 * */
		if(!StringUtil.isEmpty(imsi)){
		  	Integer c = userAdRelDao.findUserAdRelCountByImsiAdId(imsi, adId);
		    return  c<=1;
		}else{
			/**
			 * ip段规则判断 一个ip段与adId最大激活5次
			 * */
		   	Integer c = userAdRelDao.findUserAdRelCountByIpduanAdId(ipduan, adId);		   	
		   	return c<=5;
		}
	}
	
	//把ip形如 192.168.1.20转换为192p168p1

	private String ipdo(String ip){
		Pattern pattern = Pattern.compile("\\.\\d+$"); 
        Matcher matcher = pattern.matcher(ip); 
        String a=matcher.replaceAll(""); 
        return a.replaceAll("\\.", "p");
	}
	
	
	//增加积分  -------------------------------
	@Override
	public Score appendScore(Long appId,
			 Long adId,
			 String uuid,
			 String channel,//渠道
			 String page_type,//广告的分类(区分 墙 插屏  banner)
			 String devUserId,String version,String ip,UserAdRel userAd) throws AdwalkerException{
		Score currentScore =null;
		Integer addScore=0;
		double blance_price=0d;
		long category_id=0l;
		String blance_mode=null;
		DevApp app = devAppCache.getDevApp(appId);//应用
		Advertise ad=advCache.getAdv(adId);//广告
        if(null!=ad&&null!=app && userAd!=null){
			blance_price=ad.getBlance_price()*app.getScale();
			category_id  = ad.getCategory_id();
			blance_mode = ad.getBlance_mode();
			if(StringUtil.equals(StringUtil.dealNull(ad.getType_id()), AppConstant.PAGE_WALLTYPE_LIST_SMALL)){
				int sdkFlag=ScoreUtil.getFlag(version);
				Integer scale = PickerConfig.getInstance().getScale(userAd.getSign_num(), sdkFlag);
				Double score = ad.getBlance_price()* Integer.parseInt(app.getCurrency().getExchange_rate_rmb()) * app.getScale();
				addScore = (score.intValue()*scale)/10;
				UserScore userScore =userScoreDao.getUuidAppScore(uuid,appId);
                if(null!=userScore){//update
                	userScore.setScore(userScore.getScore()+addScore);
                	userScoreDao.updateUserScore(userScore);
                }else{//save
                	userScore = new UserScore();
                	userScore.setApp_id(appId);
                	userScore.setUuid(uuid);
                	userScore.setScore(addScore);
                	userScoreDao.insertUserScore(userScore);
                }
                
                UserAddScore uas = new UserAddScore();
            	uas.setAd_id(adId);
            	uas.setApp_id(appId);
            	uas.setUuid(uuid);
            	uas.setScore(addScore);
            	userScoreDao.insertUserAddScore(uas);
            	
                log.info("uuid:"+userScore.getUuid()+"  appId:"+userScore.getApp_id()+" score:"+userScore.getScore());
                userScoreCache.replaceUserScore(userScore);
                currentScore=new Score(userScore.getScore(), addScore);
                
                //===通知开发者积分信息
                String userId=null;
                if(!StringUtil.isEmpty(devUserId) && !StringUtil.equals("null", devUserId) ){
                	userId=devUserId;
                }else{
                	userId = scoreService.findMediaUser(uuid, String.valueOf(appId));
                }   
                if(!StringUtil.isEmpty(userId) && !StringUtil.isEmpty(app.getResponse_url())){
                	new Thread(new CallApiForScore(String.valueOf(ad.getId()),ad.getAd_name(),String.valueOf(ad.getFast_task()),
                		AppConstant.imageF+ad.getIcon_url(),userId,addScore,"0",uuid,appId,null)).start();
                }
                LoggerManager.loggerCallApi(appId,adId,score,"0",uuid,userId,app.getResponse_url(),userAd.getSign_num());
			}
			//日志输出
			LoggerManager.loggerDevAddScore(appId,page_type,channel,adId,String.valueOf(category_id), blance_mode, blance_price, uuid, userInfoCache.getAreaCode(uuid), version, addScore, ip, userAd.getSign_num()-1);
			userInfoCache.replaceUserInfo(uuid);
        }
		return currentScore;
		//记录日志
	}

	/** 
	* <p>Title: getScore</p>
	* <p>Description:TODO</p>
	* @param uuid
	* @param appId
	* @param sdkversion
	* @return
	 * @throws AdwalkerException 
	* @see cn.adwalker.ad.service.IAppScoreService#getScore(java.lang.String, long, java.lang.String)
	*/
	@Override
	public Score getScore(String uuid, long appId, String sdkversion) throws AdwalkerException {
		DevApp app = devAppCache.getDevApp(appId);
		UserScore userScore =userScoreCache.getUserScore(uuid, appId);
		int score = 0;
		Score vo = new Score();
		if(userScore!=null){
			String currencyName = app.getCurrency().getVirtual_currency_name();
			
			score=userScore.getScore();
			vo.setScore(score);
			vo.setUnit(currencyName);
			vo.setScoreInof("当前" + app.getCurrency().getVirtual_currency_name() + ":" + score + "!");
			return vo;
		} else {			
				vo.setScore(score);
				vo.setUnit("积分");
				vo.setScoreInof("当前积分：" + vo.getScore() + "!");
			//T_User_SCORE新增
			userScore = new UserScore();
			userScore.setUuid(uuid);
			userScore.setApp_id(appId);
			userScore.setScore(score);
			//userScoreDao.insertUserScore(userScore);
			userScoreCache.replaceUserScore(userScore);
			
			return vo;
		}
		
		
	}



	/**  
	* <p>Title: consumeScore</p>
	* <p>Description:TODO</p>
	* @param developerId
	* @param appId
	* @param uuid
	* @param score
	* @param sessionId
	* @param channel
	* @param version
	* @param ip
	* @return
	* @see cn.adwalker.ad.service.IAppScoreService#consumeScore(java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	*/
	@Override
	public Score consumeScore(Long developerId, Long appId, String uuid,
			int score, String sessionId, String channel, String version,
			String ip) throws AdwalkerException{
		
		Score svo = new Score();	
		DevApp app = devAppCache.getDevApp(appId);
		UserScore userScore =userScoreDao.getUuidAppScore(uuid, appId);
		if (userScore == null) {
			log.error("用户不存在！uuid=" + uuid);
			throw new AdwalkerException(PublicUtil.installErrorVO(ExceptionCode.BUSINESS_USER_NOT_EXIST));
			
		//用户积分不足	
		}else if(userScore.getScore()<score){
			throw new AdwalkerException(PublicUtil.installErrorVO(ExceptionCode.BUSINESS_USER_SCORE_NOT_ENOUGH));
		}else{
			//消耗积分
			int newScore = userScore.getScore()-score;
			userScore.setScore(newScore);
			userScoreDao.updateUserScore(userScore);
			
			// 日志记录
			ScoreConsume log = new ScoreConsume();
			log.setAppId(appId);
			log.setUuid(uuid);
			log.setDeveloperId(developerId);
			log.setScore(score);
			log.setChannel(channel);
			log.setIp(ip);
	
			if (!StringUtils.isEmpty(uuid)) {
				log.setAreaCode(this.getUserArea(uuid));			
			}
			
			log.logClick();
			
			String currencyName = app.getCurrency().getVirtual_currency_name();
		
			svo.setSubInfo("消费成功:" + score + currencyName + ", 当前"+ currencyName + "为:" + newScore + "!");
			svo.setScore(newScore);
			svo.setUpdateScore(score);
			svo.setUnit(currencyName);
			
			userScoreCache.replaceUserScore(userScore);
			userInfoCache.replaceUserInfo(uuid);
		}
		
		
		return svo;
	}

	
	private String getUserArea(String uuid) {
		String s="全国";
		// 终端用户信息实体
		UserInfo userInfo = userInfoCache.getUserInfo(uuid);
		if (userInfo!=null&&!StringUtils.isEmpty(userInfo.getAreaCode())&&!userInfo.getAreaCode().equals("null")) {
			s=userInfo.getAreaCode();
		}
		
		log.info("db.userInfo is null uuid=" + uuid);
		return s;
	}






	/**  (non-Javadoc)
	* <p>Title: iosAddScore</p>
	* <p>Description:TODO</p>
	* @param appKey
	* @param mac
	* @param adKey
	* @param channel
	* @param string
	* @param sdkversion
	* @return
	* @throws Exception
	* @see cn.adwalker.ad.service.IAppScoreService#iosAddScore(long, java.lang.String, long, java.lang.String, java.lang.String, java.lang.String)
	*/
	@Override
	public Score iosAddScore(ActivateIosParam vo) throws Exception {
		return iosAddScore(vo.getUuid(),vo.getAdId(),vo.getAppId(),vo.getDevUserId(),vo.getIdfa(),vo.getPage_type(),vo.getChannel(),vo.getIp(),vo.getVersion());
		/**
		userAdRelDao.insertUuidAdId(uuid, adId);
		// 获取广告
		
		DevApp app = devAppCache.getDevApp(appId);
		Advertise advertise = advCache.getAdv(adId);
		Double scoreD = advertise.getBlance_price()* Integer.parseInt(app.getCurrency().getExchange_rate_rmb());
		Integer score = scoreD.intValue();
		
		//更新积分
		UserScore userScore =userScoreDao.getUuidAppScore(uuid, appId);
		if(userScore == null){
			userScore = new UserScore();
			userScore.setApp_id(appId);
			userScore.setUuid(uuid);
			userScore.setScore(score);
			userScore.setUpdateScore(score);
			userScoreDao.insertUserScore(userScore);
		}else{
			userScore.setScore(userScore.getScore()+score);
			userScore.setUpdateScore(userScore.getScore());
			userScoreDao.updateUserScore(userScore);
		}
		
		userScoreCache.replaceUserScore(userScore);
		
		
		// 2.开发sys者、用户、广告日志输入
		scoreService.doDevScore(scoreD, appId, channel, adId, uuid, page_type, advertise.getBlance_price(), "ios", advertise.getPlacement().getCategory_id(), advertise.getBlance_mode(),0,"127.0.0.1");
			
		
		//积分墙对外接口		
		String userId = scoreService.fingChangDouId(uuid, String.valueOf(appId));
		Advertise vo=null;
		try {
			vo =advCache.getAdv(adId);
		} catch (Exception e) {
			log.error("从缓存中获取广告失败!!广告id为"+adId);// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (userId != null
				&& page_Type.equals(AppConstant.PAGE_WALLTYPE_LIST_SMALL)) {
			
			URLBean bean = escoreAPICache.getURLBean();
			//System.out.println("uuid"+uuid);
			//System.out.println("userId"+userId);
			//System.out.println("data.getUpdateScore()"+userScore.getUpdateScore());
			//System.out.println("appId"+appId);
			//System.out.println("vo"+vo);
			//System.out.println("bean.getApiURL()"+bean.getApiURL());
			new Thread(new MyRunable(uuid, userId, userScore.getUpdateScore(),
					String.valueOf(appId), vo,bean.getApiURL())).start();
		}
		
		
		return null;*/
	}
	
	
	
	
	
	private Score iosAddScore(String uuid,Long adid,Long appid,String devUserId,String idfa,String page_type,String channel,String ip,String version){
		UserAdRel userAd=userAdRelDao.findUserAdRelByUuidAndAdId(uuid,adid);
		Score currentScore=null;
		if(null==userAd){
			double blance_price=0d;
			long category_id=0l;
			String blance_mode=null;
			Integer score=0;
			userAd=new UserAdRel(uuid,appid, adid, 1);
			userAdRelDao.saveUserAdRel(userAd,null,null,null);
			DevApp app = devAppCache.getDevApp(appid);//应用
			Advertise ad=advCache.getAdv(adid);//广告
			if(null!=ad && null!=app){
				blance_price = ad.getBlance_price()*app.getScale();
				blance_mode = ad.getBlance_mode();
				category_id=ad.getCategory_id();
				if(StringUtil.equals(StringUtil.dealNull(ad.getType_id()), AppConstant.PAGE_WALLTYPE_LIST_SMALL)){//积分墙则添加积分
						score =this.getScore(ad, app);
						UserScore userScore =userScoreDao.getUuidAppScore(uuid, appid);
						if(null==userScore){
							userScore = new UserScore();
							userScore.setApp_id(appid);
		                	userScore.setUuid(uuid);
		                	userScore.setScore(score);
		                	userScoreDao.insertUserScore(userScore);
						}else{
		                	userScore.setScore(userScore.getScore()+score);
							userScoreDao.updateUserScore(userScore);
						}
						UserAddScore uas = new UserAddScore();
	                	uas.setAd_id(adid);
	                	uas.setApp_id(appid);
	                	uas.setUuid(uuid);
	                	uas.setScore(score);
	                	userScoreDao.insertUserAddScore(uas);
						userScoreCache.replaceUserScore(userScore);
						
						//通知开发者
						String userId=null;
		                if(!StringUtil.isEmpty(devUserId) && !StringUtil.equals("null", devUserId) ){
		                	userId=devUserId;
		                }else{
		                	userId = scoreService.findMediaUser(uuid, String.valueOf(appid));
		                }
		                
		                if(!StringUtil.isEmpty(userId) && !StringUtil.isEmpty(app.getResponse_url())){
		                	new Thread(new CallApiForScore(String.valueOf(ad.getId()),ad.getAd_name(),String.valueOf(ad.getFast_task()),ad.getIcon_url(),userId,score,"1",uuid,appid,idfa)).start();
		                }
		                LoggerManager.loggerCallApi(appid,adid,score,"1",uuid,userId,app.getResponse_url(),userAd.getSign_num());
				}
				//记日志
				LoggerManager.loggerDevAddScore(appid,page_type, channel, adid,String.valueOf(category_id), blance_mode, blance_price, uuid, userInfoCache.getAreaCode(uuid), StringUtil.dealNull(version,"ios"), score,ip, userAd.getSign_num()-1);
				//更新缓存
				userInfoCache.replaceUserInfo(uuid);
			}
		}else{
			log.info("userAd exist"+uuid+" "+adid);
		}
		return currentScore;
		
	}
	
	
	/**
	 * 
	* <p>Title: getScore</p>
	* <p>Description:积分换算抽取，快速任务单独汇率</p>
	* @param ad
	* @param app
	* @return
	* @author cuidd
	* @date 2014年11月7日
	* @return int
	* @version 1.0
	 */
	private int getScore(Advertise ad,DevApp app){
		int i=0;
		Double scoreD=null;
		if (ad.getFast_task()!=null&&ad.getFast_task()==1) {
			ServiceConfig sf=systemConfigCache.getSystemConfig();
			scoreD = ad.getBlance_price()*sf.getQuickly_task() * app.getScale();
		}else {
			scoreD = ad.getBlance_price()* Integer.parseInt(app.getCurrency().getExchange_rate_rmb()) * app.getScale();
		}
		if (scoreD!=null) {
			i=scoreD.intValue();
		}
		return i;
		
	}



	@Override
	public Score weixinAddScore(String devUserId,Long adId,String idfa,String ip,String udid,
			String openudid,
			String idfv,
			String phoneName) {
		MediaUser mediaUser=mediaUserCache.getMediaUser(devUserId,OS.ios);
		String uuid=mediaUser.getUuid();
		String appid=mediaUser.getApp_id();
		
		//获取广告信息
		Advertise ad = advCache.getAdv(NumberUtil.getLong(adId,0));
		
		
		//先发送点击后确认激活
		Map<String,String> paramMap=new HashMap<String, String>();
		paramMap.put("ip",ip);
		paramMap.put("app_id", String.valueOf(appid));
		paramMap.put("idfa", idfa);
		paramMap.put("idfv",idfv);//idfv
		paramMap.put("openudid", openudid);//udid
		paramMap.put("os",StringUtil.dealNull("ios"));//os
		paramMap.put("sdkVersion", StringUtil.dealNull("1.0.0"));
		paramMap.put("devUserId",devUserId);
		paramMap.put("page_type", "0");//类型
		paramMap.put("ssid",  null);
		paramMap.put("bssid",  null);
		paramMap.put("phoneName", phoneName);
		paramMap.put("latitude", null);
		paramMap.put("longitude", null);
		paramMap.put("uuid",uuid);
		paramMap.put("version", null);
		paramMap.put("devuserid", devUserId);
		paramMap.put("mac",null);
		new Thread(new CallApi(ad.getId(),ad.getConfig_id(),paramMap) ).start();
		
		//增加广告确认方式判断
		if (ad.getConfirm_type()!=null&&ad.getConfirm_type()==1) {
			iosAddScore(uuid,adId,Long.valueOf(appid), devUserId, idfa, AppConstant.PAGE_IMAGE_TYPE_BANNER_SMILL, "weixin", ip, "1.0.1");
		}
		
		return null;
	}
	
}
