/**
* <p>Title: IScoreAPIService.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-7-18
* @version 1.0
*/
package cn.adwalker.ad.api.dev.service;

/**
 * <p>Title: IScoreAPIService</p>
 * <p>Description:积分接口</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-7-18
 */
public interface IScoreAPIService {

	/**
	 * 
	* <p>Title: sendData</p>
	* <p>Description:开发者积分回调</p>
	* @param uuid
	* @param userID
	* @param score
	* @param exchangetime
	* @param plat
	* @param appName
	* @param packageName
	* @param adId
	* @param appId
	* @param idfa
	* @author cuidd
	* @date 2014年10月17日
	* @return void
	* @version 1.0
	 * @param fastTask 
	 * @param icon_url 
	 */
	//add idfa by jief 
	public void sendData(String uuid,String userID,String score,String exchangetime,String plat,String appName,String packageName,String adId,String appId,String idfa, String fastTask, String icon_url);
}
