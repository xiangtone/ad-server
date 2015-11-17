package cn.adwalker.model.operation.dao;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.operation.domain.ActivateNumIos;

/**
 * <p>
 * Title: IPackageActivateDetailDao 
 * </p>
 * <p>
 * Description:TODO 
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-5-23
 */
public interface IActivateIosDao extends IBaseDao<ActivateNumIos> {
	/**
	* <p>Title: restConfIosNum</p>
	* <p>Description:IOS日确认数重置</p>
	* @param id
	* @param number
	* @param status
	* @throws Exception
	* @author lichuang
	* @date 2013-11-8
	* @return void
	* @version 1.0
	 */
	public void restConfIosNum(Long id, Integer number,Integer status) throws Exception;


	/**
	* <p>Title: updateStatus</p>
	* <p>Description:IOS日确认数删除</p>
	* @param id
	* @param status
	* @throws Exception
	* @author lichuang
	* @date 2013-11-8
	* @return void
	* @version 1.0
	 */
	public void updateStatus(Long id,Integer status) throws Exception;

	/**
	 * <p>
	 * Title: statusConfirm
	 * </p>
	 * <p>
	 * Description:数据分完
	 * </p>
	 * 
	 * @param fraction_id
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-27
	 * @return void
	 * @version 1.0
	 */
	public void updateStatu(String ids, Integer status) throws Exception;

	/**
	* <p>Title: submitConfNumIos</p>
	* <p>Description:TODO</p>
	* @param ids
	* @author lichuang
	* @date 2013-11-8
	* @return void
	* @version 1.0
	 */
	public void submitConfNumIos(String ids);

	/**
	* <p>Title: getById</p>
	* <p>Description:查询ios日数据</p>
	* @param id
	* @return
	* @author cuidd
	* @date 2014年11月14日
	* @return ActivateNumIos
	* @version 1.0
	 */
	public ActivateNumIos getById(Long id)throws Exception;;

}
