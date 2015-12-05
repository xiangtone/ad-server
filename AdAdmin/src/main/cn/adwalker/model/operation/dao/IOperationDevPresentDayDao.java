package cn.adwalker.model.operation.dao;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.ad.admin.operation.bean.OperDevPresentbean;

/**
 * 
 * <p>
 * Title: IOperationDevPresentDayDao
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-3-11
 */
@SuppressWarnings("rawtypes")
public interface IOperationDevPresentDayDao extends IBaseDao {

	/**
	 * <p>
	 * Title: findSumReward
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-19
	 * @return double
	 * @version 1.0
	 */
	public double findSumReward(OperDevPresentbean bean, IPageInfo pageInfo)
			throws Exception;
}
