package cn.adwalker.ad.dao;

import cn.adwalker.ad.cache.element.UserInfo;


public interface IUserInfoDao {


	/**
	 * 对象插入数据库
	 * 
	 * @param userInfo
	 * @return
	 */
	public int insert(UserInfo userInfo);

	
	/**
	 * 
	* <p>Title: getUserInfo</p>
	* <p>Description:TODO</p>
	* @param uuid
	* @return
	* @return UserInfo
	* @throws
	 */
	public UserInfo getUserInfo(String uuid);

	public void updateUserArea_Code(String uuidl, String city,String province);

	/**
	 * <p>Title: updateUserOs</p>
	 * <p>Description:更新用户的操作系统版本号for ios7 add by jief2013-09-04</p>
	 * @param uuid
	 * @param os
	 */
	public void updateUserOs(String uuid,String os,String openUDID,String idfa,String idfv);
}
