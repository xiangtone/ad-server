package cn.adwalker.ad.dao;

import cn.adwalker.ad.dao.domain.MediaUser;

public interface IMediaUserDao {
	
	
	public String findMediaUser(String uuid, String appId);
	
	/**
	 * 
	* <p>Title: findMediaUser</p>
	* <p>Description:TODO</p>
	* @param media_user_id
	* @return
	* @author cuidd
	* @date 2014年11月25日
	* @return String
	* @version 1.0
	 */
	public MediaUser findMediaUser(String media_user_id,Long appid);
	
	public void  initMediaUser(String uuid, String devUserId, String appId,String os);
	
	
	public MediaUser findMediaUserByOs(String media_user_id,String os);

}
