/**
* <p>Title: IUserAdRelDao.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-5-16
* @version 1.0
*/
package cn.adwalker.ad.dao;

import java.util.Date;
import java.util.List;

import cn.adwalker.ad.bean.UserAddScore;
import cn.adwalker.ad.cache.element.UserAdRel;
import cn.adwalker.ad.cache.element.UserScore;

/**
 * <p>Title: IUserAdRelDao</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-5-16
 */
public interface IUserAdRelDao {
	
	/**
	* <p>Title: getUserAdRel</p>
	* <p>Description:TODO</p>
	* @param uuid
	* @return
	* @return List<UserAdRel>
	* @throws
	*/
	public List<UserAdRel> getUserAdRel(String uuid);
	
	/**
	 * 查询当日激活数量
	 * @param uuid
	 * @return
	 */
	public Integer findUserAdRelCount(String uuid,Date beginDate,Date endDate);
	
	public UserAdRel findUserAdRelByUuidAndAdId(String uuid,Long appId);

	public void updateUserAdRel(UserAdRel uar);
	public void saveUserAdRel(UserAdRel uar,String ip,String ipduan,String imsi);
	public UserScore findUserScore(String uuid,Long appId);
	public List<UserAddScore> queryCurrentUserAddScore(String uuid,Long appId);
	public Integer findUserAdRelCountByImsiAdId(String imsi,Long adId);
	public Integer findUserAdRelCountByIpduanAdId(String ipduan,Long adId);

}
