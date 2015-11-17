package cn.adwalker.model.ad.dao;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.ad.domain.PlacementResWallScore;

/**
 * 功能概述：<br>
 * 
 * @author jiaozhichao
 */
public interface IPlacementResWallScoreDao extends IBaseDao<PlacementResWallScore> {

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
	
	PlacementResWallScore getByPlacement(Long id) throws Exception;

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
	
	void saveOrUpdate(PlacementResWallScore resWall) throws Exception;

	/**
	* <p>Title: getMaxWeight</p>
	* <p>Description:TODO</p>
	* @author cuidd
	* @date 2013-5-31
	* @return void
	* @version 1.0
	*/
	int getMaxWeight();
	
	
	
}
