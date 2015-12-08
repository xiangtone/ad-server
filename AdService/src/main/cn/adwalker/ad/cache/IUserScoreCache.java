/**
* <p>Title: IUserScoreCache.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-5-17
* @version 1.0
*/
package cn.adwalker.ad.cache;

import cn.adwalker.ad.cache.element.UserScore;

/**
 * <p>Title: IUserScoreCache</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-5-17
 */
public interface IUserScoreCache {

	
	public UserScore getUserScore(String uuid,long appId);
	
	public UserScore replaceUserScore(String uuid,long appId);

	/**
	* <p>Title: replaceUserScore</p>
	* <p>Description:TODO</p>
	* @param userScore
	* @return void
	* @throws
	*/
	public void replaceUserScore(UserScore userScore);
}
