/**
* <p>Title: IAppBlackCache.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-6-14
* @version 1.0
*/
package cn.adwalker.ad.cache;

import cn.adwalker.ad.dao.domain.MediaUser;

/**
 * <p>Title: IAppBlackCache</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-6-14
 */
public interface IMediaUserCache {
   
	public MediaUser getMediaUser(String media_user_id, Long appid);
	
	public MediaUser getMediaUser(String media_user_id, String os);
	
	public void replaceMediaUser(String media_user_id);
}
