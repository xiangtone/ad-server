package cn.adwalker.model.ad.dao;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.ad.domain.PlacementResWallList;

/**
 * 功能概述：<br>
 * 
 * @author jiaozhichao
 */
public interface IPlacementResWallListDao extends IBaseDao<PlacementResWallList> {

	/**
	* <p>Title: getByPlacement</p>
	* <p>Description:TODO</p>
	* @param id
	* @return
	* @author cuidd
	* @date 2013-5-10
	* @return PlacementResWallScore
	* @version 1.0
	 * @throws Exception 
	*/
	
	PlacementResWallList getByPlacement(Long id) throws Exception;

	/**
	* <p>Title: saveOrUpdate</p>
	* <p>Description:TODO</p>
	* @param resWall
	* @author cuidd
	* @date 2013-5-10
	* @return void
	* @version 1.0
	 * @throws Exception 
	*/
	
	void saveOrUpdate(PlacementResWallList resWall) throws Exception;

	/**
	* <p>Title: getMaxWeight</p>
	* <p>Description:TODO</p>
	* @return
	* @author cuidd
	* @date 2013-6-1
	* @return int
	* @version 1.0
	*/
	
	int getMaxWeight();
	
	
	
}
