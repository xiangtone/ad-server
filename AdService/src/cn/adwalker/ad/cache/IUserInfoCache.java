/**
 * <p>Title: IUserInfoCache.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2013-4-8
 * @version 1.0
 */
package cn.adwalker.ad.cache;

import cn.adwalker.ad.cache.element.UserInfo;

/**
 * <p>
 * Title: IUserInfoCache
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author www.adwalker.cn
 * @date 2013-4-8
 */
public interface IUserInfoCache {

	/**
	 * <p>
	 * Title: getUserInfo
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param uuid
	 * @param osName  //操作系统名称如 ios android
	 * @return
	 * @return UserInfo
	 * @throws
	 */
	public UserInfo getUserInfo(String uuid, String imei, String telNum,
			String telModel, String netEnv, String areaCode, String operator,
			String screenWidth, String screenHeigh, String os, String brand,
			String imsi, Long appId,String terminalType,String openUDID,String idfa,String jailbroken,String idfv,String osName);
	
	
	public UserInfo getUserInfo(String uuid, Long appId,String idfa,String idfv);
	
	
	public UserInfo getUserInfo(String uuid, String imei, String telNum,
			String telModel, String netEnv, String areaCode, String operator,
			String screenWidth, String screenHeigh, String os, String brand,
			String imsi, Long appId,String terminalType,String osName);

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
	 * @return UserInfo
	 * @throws
	 */
	public UserInfo getUserInfo(String uuid);
	
	public UserInfo getUserInfo(String uuid,Long appId);
	
	public String getAreaCode(String uuid);

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
	 * @return UserInfo
	 */
	public UserInfo replaceUserInfo(String uuid);

	/**
	 * <p>
	 * Title: replaceUserInfo
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param userInfo
	 * @return void
	 * @throws
	 */
	public void replaceUserInfo(UserInfo userInfo);
}
