package cn.adwalker.model.operation.dao;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.operation.domain.DspInfo;

/**
* <p>Title: IDspInfoDao</p>
* <p>Description:DspDAO业务</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-12-2
 */
@SuppressWarnings("rawtypes")
public interface IDspInfoDao extends IBaseDao{
	/**
	* <p>Title: findAdvInfo</p>
	* <p>Description:TODO</p>
	* @param id
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-12-3
	* @return DspInfoVo
	* @version 1.0
	 */
	public  DspInfo findAdvInfo(Long id)throws Exception;
	/**
	* <p>Title: updateDspService</p>
	* <p>Description:修改</p>
	* @param dI
	* @throws Exception
	* @author lichuang
	* @date 2013-12-3
	* @return void
	* @version 1.0
	 */
	public int updateDspService(DspInfo dI)throws Exception;


}
