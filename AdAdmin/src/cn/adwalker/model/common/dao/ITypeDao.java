/**
 * 
 */
package cn.adwalker.model.common.dao;

import java.util.List;

import cn.adwalker.model.ad.domain.Type;

/**
 * 功能概述：<br>
 * 分类接口
 * 
 * @author guoyongxiang
 */
public interface ITypeDao {

	
	
	
	/**
	* <p>Title: getAppWallType</p>
	* <p>Description:TODO</p>
	* @param appID
	* @return
	* @author cuidd
	* @date 2013-1-22
	* @return List<Type>
	* @version 1.0
	* @throws
	*/
	
	public List<Type> getAppWallType(Long appID);

}