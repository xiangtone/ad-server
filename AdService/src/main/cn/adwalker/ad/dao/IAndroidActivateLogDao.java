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



import cn.adwalker.ad.dao.domain.AndroidActivateLog;

/**
 * <p>Title: ICampaginDao</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-11
 */
public interface IAndroidActivateLogDao {
	
	
	/**
	 * 对象插入数据库
	 * 
	 * @param userInfo
	 * @return
	 */
	public int insert(AndroidActivateLog entity);
}
