/**
* <p>Title: IAppScoreService.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-5-15
* @version 1.0
*/
package cn.adwalker.ad.service;

import cn.adwalker.ad.cache.element.UserAdRel;
import cn.adwalker.ad.exception.AdwalkerException;
import cn.adwalker.ad.picker.param.ActivateIosParam;
import cn.adwalker.ad.vo.Score;

/**
 * <p>Title: IAppScoreService</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-5-15
 */
public interface IAppScoreService {
	/**
	* <p>Title: getScore</p>
	* <p>Description:获取积分</p>
	* @param uuid
	* @param appId
	* @param sdkversion
	* @return
	* @return Score
	* @throws
	*/
	public Score getScore(String uuid, long appId, String sdkversion)throws AdwalkerException ;

	/**
	* <p>Title: consumeScore</p>
	* <p>Description:消耗积分</p>
	* @param developerId
	* @param appId
	* @param uuid
	* @param score
	* @param sessionId
	* @param channel
	* @param version
	* @param ip
	* @return
	* @return Score
	* @throws
	*/
	public Score consumeScore(Long developerId, Long appId, String uuid,
			int score, String sessionId, String channel, String version,
			String ip)throws AdwalkerException;
	
	

	
	/**
	* <p>Title: iosAddScore</p>
	* <p>Description:ios--增加积分</p>
	* @param parseLong
	* @param mac
	* @param parseLong2
	* @param channel
	* @param string
	* @param sdkversion
	* @return void
	* @throws
	*/
	public Score iosAddScore(ActivateIosParam vo)throws Exception;


	
	/**
	 * activate
	 * @param vo
	 * @return
	 */
	public UserAdRel saveOrUpdateUserAdRel(Long appId,Long adId,String uuid,String imsi,String ip) throws AdwalkerException;
	
	/**
	 * 增加积分
	 * @param vo
	 * @param userAd
	 * @return
	 */
	public Score appendScore( Long appId,
	 Long adId,
	 String uuid,
	 String channel,//渠道
	 String page_type,//广告的分类(区分 墙 插屏  banner)
	 String devUserId,String version,String ip,UserAdRel userAd) throws AdwalkerException;
	
	
	public Score weixinAddScore(String devUserId,Long adId,String idfa,String ip,String udid,
			String openudid,
			String idfv,
			String phoneName);

}
