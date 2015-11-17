package cn.adwalker.model.operation.dao;

import cn.adwalker.model.operation.domain.CollocationIos;
import cn.adwalker.ad.admin.operation.vo.InterfaceChannelVo;
import cn.adwalker.core.repository.IBaseDao;

/**
* <p>Title: IOperationInterfaceIosDao</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-6-5
 */
@SuppressWarnings("rawtypes")
public interface IOperationInterfaceIosDao extends IBaseDao {
	/**
	* <p>Title: getIos</p>
	* <p>Description:获取ios配置数据</p>
	* @param campaign_id
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-6-6
	* @return CollocationIos
	* @version 1.0
	 */
	public CollocationIos getIos(Long placement_id)throws Exception ;
	/**
	* <p>Title: insertIos</p>
	* <p>Description:数据插入ios</p>
	* @param collocation
	* @throws Exception
	* @author lichuang
	* @date 2013-6-6
	* @return void
	* @version 1.0
	 */
	public int insertIos(CollocationIos collocation)throws Exception ;
	/**
	* <p>Title: updatePlacementIos</p>
	* <p>Description:把配置表里面的id插入T_PLACEMENT</p>
	* @param string
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-6-7
	* @return int
	* @version 1.0
	 */
	public int updatePlacementIos(CollocationIos collocation)throws Exception ;
	/**
	* <p>Title: getChannel</p>
	* <p>Description:TODO</p>
	* @param channel
	* @return
	* @return InterfaceChannelVo
	* @throws
	*/
	public InterfaceChannelVo getChannel(String channel);
	
}
