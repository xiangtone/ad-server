/**
 * <p>Title: UserInfoCacheImpl.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2013-4-8
 * @version 1.0
 */
package cn.adwalker.ad.cache.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.adwalker.ad.bean.UserAddScore;
import cn.adwalker.ad.cache.IUserInfoCache;
import cn.adwalker.ad.cache.element.UserAdRel;
import cn.adwalker.ad.cache.element.UserInfo;
import cn.adwalker.ad.cache.element.UserScore;
import cn.adwalker.ad.dao.IUserAdRelDao;
import cn.adwalker.ad.dao.IUserInfoDao;
import cn.adwalker.ad.picker.util.DateUtil;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.pool.MemCached;
import cn.adwalker.ad.util.AppConstant;

/**
 * <p>
 * Title: UserInfoCacheImpl
 * </p>
 * <p>
 * Description:用户缓存
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author www.adwalker.cn
 * @date 2013-4-8
 */
@Service("userInfoCache")
public class UserInfoCacheImpl implements IUserInfoCache {

	@Resource
	private IUserInfoDao userInfoDao;
	@Resource
	private IUserAdRelDao userAdRelDao;

	@Resource
	private MemCached memCached;
	
	
	private static final Logger log = LoggerFactory.getLogger(UserInfoCacheImpl.class);

	/**
	 * <p>
	 * Title: getUserInfo
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param uuid
	 * @return
	 * @see cn.adwalker.ad.cache.IUserInfoCache#getUserInfo(java.lang.String)
	 */
	@Override
	public UserInfo getUserInfo(String uuid, String imei, String telNum,
			String telModel, String netEnv, String areaCode, String operator,
			String screenWidth, String screenHeigh, String os_version, String brand,
			String imsi, Long appId, String terminalType,String openUDID,String idfa,String jailbroken,String idfv,String osName) {
		int teType = 0;
		if ("ipad".equals(terminalType)) {
			teType = 1;
		}

		UserInfo info = (UserInfo) memCached.get(uuid);
		if (info == null) {
			info = userInfoDao.getUserInfo(uuid);
			if (info == null) {// 数据库不存在，则入库操作,初始化时用户的areaCode为空
				info = new UserInfo(uuid, imei, telNum, telModel, netEnv, null,
						operator, screenWidth, screenHeigh, os_version, brand, imsi,
						appId, teType,openUDID,idfa,jailbroken,idfv);
				userInfoDao.insert(info);//
			}else if(AppConstant.OS_IOS.equals(osName)){	//更新os
				if(StringUtils.isNotBlank(os_version) && !os_version.equals(info.getOs())){
					userInfoDao.updateUserOs(info.getUuid(),os_version,openUDID,idfa,idfv);
				}
			}
			memCached.add(uuid, info, new Date(AppConstant.USER_CACHE_TIME));
			log.info(uuid+" add memcached-----");
		 //如果不为空的话验证操作系统版本	  
		}else if(AppConstant.OS_IOS.equals(osName)){	//更新os
			if(StringUtils.isNotBlank(os_version) && !os_version.equals(info.getOs())){
				info.setOs(os_version);
				info.setOpenUDID(openUDID);
				info.setIdfa(idfa);
				info.setIdfv(idfv);
				userInfoDao.updateUserOs(info.getUuid(),os_version,openUDID,idfa,idfv);
				memCached.add(uuid, info, new Date(AppConstant.USER_CACHE_TIME));
			}
		}
		return info;
	}
	
	
	@Override
	public UserInfo getUserInfo(String uuid, Long appId, String idfa,
			String idfv) {
		return this.getUserInfo(uuid, null, null, null, null, null, null, null, null, null, null, null, appId, null, null, idfa, null, idfv, null);
	}

	/**
	 * <p>
	 * Title: getUserInfo
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param uuid
	 * @return
	 * @see cn.adwalker.ad.cache.IUserInfoCache#getUserInfo(java.lang.String)
	 */
	@Override
	public UserInfo getUserInfo(String uuid) {
		UserInfo userInfo = (UserInfo) memCached.get(uuid);
		if (userInfo == null) {
			userInfo = getBean(uuid);
			memCached.add(uuid, userInfo, new Date(AppConstant.USER_CACHE_TIME));
		}
		return userInfo;
	}
	
	@Override
	public UserInfo getUserInfo(String uuid,Long appId) {
		UserInfo userInfo = getUserInfo(uuid);
		String date = DateUtil.getFormatDate(new Date(), "yyyyMMdd");
		//加载appId 的积分情况到缓存总
		if (userInfo != null && (userInfo.getUserScore(appId)==null || !StringUtil.equals(userInfo.dateTag, date))) {
		   	UserScore us = userAdRelDao.findUserScore(uuid, appId);
		   	if(us==null){
		   		us = new UserScore(uuid, appId, 0, 0, 0);
		   	}else{
		   		List<UserAddScore> list = userAdRelDao.queryCurrentUserAddScore(uuid, appId);
		   		Integer totalInteger=0;
		   		for(UserAddScore uas:list){
		   			totalInteger+=uas.getScore();
		   		}
		   		us.setTodayInteger(totalInteger);
		   		us.setDownLoadTime(list.size());
		   	}
		   	
		   	List<UserAdRel> userAdRels = userAdRelDao.getUserAdRel(uuid);
			StringBuilder appIds = new StringBuilder("(-1");
			if (userAdRels != null & userAdRels.size() > 0) {
				for (UserAdRel userAdRel : userAdRels) {
					appIds.append("|"+userAdRel.getAd_id() );
				}
			}
			appIds.append(")");
			
			userInfo.setSignAdRels(userAdRels);
			userInfo.setAppIds(appIds.toString());
		   	
		   	
		   	userInfo.dateTag=date;
		   	userInfo.putUserScore(us);
	   		replaceUserInfo(userInfo);
			
		}
		return userInfo;
	}
	
	@Override
	public String getAreaCode(String uuid){
		UserInfo user = getUserInfo(uuid);
		return user==null?"全国":StringUtil.dealNull(user.getAreaCode(),"全国");
	}

	/**
	 * <p>
	 * Title: replaceUserInfo
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param uuid
	 * @return
	 * @see cn.adwalker.ad.cache.IUserInfoCache#replaceUserInfo(java.lang.String)
	 */
	@Override
	public UserInfo replaceUserInfo(String uuid) {
		UserInfo userInfo = getBean(uuid);
		memCached.set(uuid, userInfo, new Date(AppConstant.USER_CACHE_TIME));
		return userInfo;
	}

	/**
	 * 
	 * <p>
	 * Title: getBean
	 * </p>
	 * <p>
	 * Description:获取用户信息
	 * </p>
	 * 
	 * @param uuid
	 * @return
	 * @author cuidd
	 * @date 2013-7-5
	 * @return UserInfo
	 * @version 1.0
	 */
	private UserInfo getBean(String uuid) {
		UserInfo userInfo = null;
		if (!StringUtils.isEmpty(uuid)) {
			userInfo = userInfoDao.getUserInfo(uuid);
			if (userInfo == null) {
				log.error("db.userInfo is null uuid=" + uuid);
			} else {
				userInfo.setScoreMap(new HashMap<String, UserScore>());
			}
		}

		return userInfo;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: replaceUserInfo
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param userInfo
	 * @see cn.adwalker.ad.cache.IUserInfoCache#replaceUserInfo(cn.adwalker.ad.cache.element.UserInfo)
	 */
	@Override
	public void replaceUserInfo(UserInfo userInfo) {
		memCached.set(userInfo.getUuid(), userInfo, new Date(AppConstant.USER_CACHE_TIME));
	}


	@Override
	public UserInfo getUserInfo(String uuid, String imei, String telNum,
			String telModel, String netEnv, String areaCode, String operator,
			String screenWidth, String screenHeigh, String os_version, String brand,
			String imsi, Long appId, String terminalType, String osName) {
		
		return this.getUserInfo(uuid,imei, telNum,
				telModel,netEnv, 
				areaCode, operator,
				screenWidth,screenHeigh,
				os_version,brand,imsi,appId,
				terminalType, null, null,null, null,osName);
	}
}
