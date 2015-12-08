/**
* <p>Title: ICampaginDao.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-4-11
* @version 1.0
*/
package cn.adwalker.ad.dao;



import cn.adwalker.ad.dao.domain.AndroidDownloadLog;
import cn.adwalker.ad.dao.domain.Placement;

/**
 * <p>Title: ICampaginDao</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-11
 */
public interface IAndroidDownloadLogDao {
	
	
	/**
	 * 对象插入数据库
	 * 
	 * @param userInfo
	 * @return
	 */
	public int insert(AndroidDownloadLog entity);
	/**
	* <p>Title: getCampagin</p>
	* <p>Description:TODO</p>
	* @param id
	* @return
	* @return Campagin
	* @throws
	 */
	@Deprecated
	public Placement getPlacement(Long id);
	
	@Deprecated
	public String getUrlParamByConfigId(String confiId);
}
