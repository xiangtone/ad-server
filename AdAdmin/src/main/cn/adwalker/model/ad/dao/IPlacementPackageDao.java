package cn.adwalker.model.ad.dao;

import java.util.List;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.ad.domain.PlacementPackage;

/**
 * 功能概述：<br>
 * 
 * @author jiaozhichao
 */
public interface IPlacementPackageDao extends IBaseDao<PlacementPackage> {
	
	/**
	* <p>Title: packageremarks</p>
	* <p>Description:修改备注</p>
	* @param id
	* @param number
	* @throws Exception
	* @author lichuang
	* @date 2013-7-16
	* @return void
	* @version 1.0
	 */
	public void packageremarks(Long id, String packageremarks) throws Exception;

	/**
	 * <p>
	 * Title: deletePackage
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @author cuidd
	 * @date 2013-5-9
	 * @return void
	 * @version 1.0
	 */

	void deletePackage(Long id);

	/**
	 * <p>
	 * Title: getByPlacement
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param placement_id
	 * @return
	 * @author cuidd
	 * @date 2013-5-21
	 * @return PlacementPackageEditVo
	 * @version 1.0
	 * @throws Exception
	 */

	PlacementPackage getByPlacement(Long placement_id) throws Exception;

	/**
	 * 
	 * <p>
	 * Title: findPackage
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param placement_id
	 * @return
	 * @author cuidd
	 * @date 2013-9-7
	 * @return List<PlacementPackage>
	 * @version 1.0
	 * @throws Exception 
	 */
	public List<PlacementPackage> findPackage(Long placement_id) throws Exception;

}
