package cn.adwalker.model.app.dao;

import java.util.List;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.app.domain.Application;
import cn.adwalker.model.app.domain.ApplicationEntity;

/**
 * 功能概述： <br>
 * 开发者SDK应用管理接口
 * 
 * @author guoyongxiang
 */
public interface IApplicationDao extends IBaseDao<ApplicationEntity> {

	/**
	 * 添加开发者应用
	 * 
	 * @param developedApp
	 * @throws Exception
	 */
	public Long insert(Application developedApp) throws Exception;

	/**
	 * 根据ID删除开发者应用
	 * 
	 * @param developedApp
	 * @throws Exception
	 */
	public Integer deleteById(Long id) throws Exception;

	/**
	 * 根据用户查询开发者应用列表
	 * 
	 * @param developedApp
	 * @throws Exception
	 */
	public List<Application> findByUser(Long id) throws Exception;

	/**
	 * 
	 * <p>
	 * Title: update
	 * </p>
	 * <p>
	 * Description:修改应用信息
	 * </p>
	 * 
	 * @param developedApp
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-7-2
	 * @return void
	 * @version 1.0
	 */
	public void update(Application developedApp) throws Exception;

	/**
	 * 更新路径
	 * 
	 * @param developedApp
	 * @return
	 * @throws Exception
	 */
	public Long updatePath(Application developedApp) throws Exception;

	/**
	 * 根据开发者ID批量修改
	 * 
	 * @param developedApp
	 * @return
	 * @throws Exception
	 */
	public Long updateByDevId(Application developedApp) throws Exception;

	/**
	 * <p>
	 * Title: findCount
	 * </p>
	 * <p>
	 * Description: 根据开发者ID获得用户app数量
	 * </p>
	 * 
	 * @param developerID
	 * @return
	 */
	public long findCountByDevId(Long devID);

	/**
	 * <p>
	 * Title: updatePrice
	 * </p>
	 * <p>
	 * Description:修改 扣量比例
	 * </p>
	 * 
	 * @param id
	 * @param rate
	 * @author lichuang
	 * @date 2013-3-20
	 * @return void
	 * @version 1.0
	 */
	public void updateAppRate(Long id, Double rate);

	/**
	* <p>Title: updateMediaRating</p>
	* <p>Description:修改媒体评级</p>
	* @param mediaId
	* @param score
	* @author lichuang
	* @date 2013-7-9
	* @return void
	* @version 1.0
	 */
	public void updateMediaRating(Long mediaId, Double score);

	/**
	* <p>Title: saveOrUpdate</p>
	* <p>Description:TODO</p>
	* @param entity
	* @author cuidd
	* @date 2013-8-1
	* @return void
	* @version 1.0
	 * @throws Exception 
	*/
	
	public void saveOrUpdate(ApplicationEntity entity) throws Exception;
}