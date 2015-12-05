package cn.adwalker.model.operation.dao;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.finance.domain.CostMonthChannel;
import cn.adwalker.model.operation.domain.PackageActivate;

/**
* <p>Title: IIosAndroidDayNumDao</p>
* <p>Description:ios/android每天的渠道数据</p>
* <p>Company: adwalker</p> 
* @author    lichuang
* @date       2014年11月14日
 */
public interface IIosAndroidDayChannelNumDao extends IBaseDao<PackageActivate>{
	/**
	* <p>Title: getById</p>
	* <p>Description:查询每天数据渠道汇总</p>
	* @param id
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014年11月17日
	* @return CostMonthChannel
	* @version 1.0
	 */
	public CostMonthChannel getById(String id)throws Exception;
	/**
	* <p>Title: updateById</p>
	* <p>Description:TODO</p>
	* @param ars
	* @throws Exception
	* @author cuidd
	* @date 2014年11月14日
	* @return void
	* @version 1.0
	 */
	public void updateById(String ars,Long income_id)throws Exception;
	/**
	* <p>Title: updateInCome</p>
	* <p>Description:结算数据申请驳回</p>
	* @param ars
	* @throws Exception
	* @author cuidd
	* @date 2014年11月15日
	* @return void
	* @version 1.0
	 */
	public void updateInCome(String ars,Integer status)throws Exception;
	/**
	* <p>Title: updateStatus</p>
	* <p>Description:更改取消申请的渠道成本</p>
	* @param id
	* @param status
	* @throws Exception
	* @author cuidd
	* @date 2014年12月10日
	* @return void
	* @version 1.0
	 */
	public void updateStatus(Long id, int status)throws Exception;
	
}
