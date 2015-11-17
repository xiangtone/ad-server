/**
* <p>Title: IUserScore.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-4-23
* @version 1.0
*/
package cn.adwalker.ad.dao;

import cn.adwalker.ad.bean.UserAddScore;
import cn.adwalker.ad.cache.element.UserScore;

/**
 * <p>Title: IUserScore</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-23
 */
public interface IUserScoreDao {

	
	public UserScore getUuidAppScore(String uuid,long appId);

	/**
	* <p>Title: insertUserScore</p>
	* <p>Description:TODO</p>
	* @param userScore
	* @return void
	* @throws
	*/
	public void insertUserScore(UserScore userScore);

	public void insertUserAddScore(UserAddScore uas);
	
	/**
	* <p>Title: updateUserScore</p>
	* <p>Description:TODO</p>
	* @param userScore
	* @return void
	* @throws
	*/
	public void updateUserScore(UserScore userScore);
}
