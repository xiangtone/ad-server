package cn.adwalker.ad.facade;

import cn.adwalker.ad.cache.element.UserInfo;

/**
 * 
* <p>Title: IWeiXinFacade</p>
* <p>Description:业务接口</p>
* <p>Company: adwalker</p> 
* @author    cuidd
* @date       2014年12月22日
 */
public interface IWeiXinFacade {
	
	public String convertAppKey(String appkey,String os);
	
	public UserInfo checkUser(String user_id, String appkey);

	/**
	 * 
	* <p>Title: initMediaUser</p>
	* <p>Description:TODO</p>
	* @param userId
	* @param appkey
	* @param userType 二次用户
	* @author cuidd
	* @date 2014年12月23日
	* @return void
	* @version 1.0
	 */
	public void initMediaUser(String userId, String appkey,Integer userType,String userCode2);

}
