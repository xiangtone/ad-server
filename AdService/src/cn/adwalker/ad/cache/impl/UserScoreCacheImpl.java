/**
* <p>Title: UserScoreCacheImpl.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-5-17
* @version 1.0
*/
package cn.adwalker.ad.cache.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.adwalker.ad.cache.IUserScoreCache;
import cn.adwalker.ad.cache.element.UserScore;
import cn.adwalker.ad.dao.IUserScoreDao;
import cn.adwalker.ad.pool.MemCached;
import cn.adwalker.ad.util.AppConstant;

/**
 * <p>Title: UserScoreCacheImpl</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-5-17
 */
@Service("userScoreCache")
public class UserScoreCacheImpl implements IUserScoreCache {
	
	@Resource
	private IUserScoreDao userScoreDao;
	@Resource
	private MemCached memCached;
	/** 
	* <p>Title: getUserScore</p>
	* <p>Description:TODO</p>
	* @param uuid
	* @return
	* @see cn.adwalker.ad.cache.IUserScoreCache#getUserScore(java.lang.String)
	*/
	@Override
	public UserScore getUserScore(String uuid,long appId) {
		UserScore userScore = (UserScore) memCached.get(AppConstant.MEM_USER_SCORE+uuid+appId);
		if(userScore==null){
			userScore = userScoreDao.getUuidAppScore(uuid, appId);
			memCached.add(AppConstant.MEM_USER_SCORE+uuid+appId, userScore, new Date(AppConstant.CACHE_TIME));
		}
		return userScore;
	}

	/** 
	* <p>Title: replaceUserScore</p>
	* <p>Description:TODO</p>
	* @param uuid
	* @return
	* @see cn.adwalker.ad.cache.IUserScoreCache#replaceUserScore(java.lang.String)
	*/
	@Override
	public UserScore replaceUserScore(String uuid,long appId) {
		UserScore userScore = userScoreDao.getUuidAppScore(uuid, appId);
		if(userScore!=null)
		memCached.set(AppConstant.MEM_USER_SCORE+uuid+appId, userScore,new Date(AppConstant.CACHE_TIME));
		return userScore;
	}

	/**  (non-Javadoc)
	* <p>Title: replaceUserScore</p>
	* <p>Description:TODO</p>
	* @param userScore
	* @see cn.adwalker.ad.cache.IUserScoreCache#replaceUserScore(cn.adwalker.ad.cache.element.UserScore)
	*/
	@Override
	public void replaceUserScore(UserScore userScore) {
		memCached.set(AppConstant.MEM_USER_SCORE+userScore.getUuid()+userScore.getApp_id(), userScore,new Date(AppConstant.CACHE_TIME));		
	}

	
	
}
