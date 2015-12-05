package cn.adwalker.model.operation.dao;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.operation.domain.PackageActivateDetail;

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
public interface IPackageActivateDetailDao extends IBaseDao<PackageActivateDetail>{

	/**
	 * <p>
	 * Title: saveConfirm
	 * </p>
	 * <p>
	 * Description:保存渠道的效果数
	 * </p>
	 * 
	 * @param id
	 * @param confirm_num
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-27
	 * @return void
	 * @version 1.0
	 */
	public int saveConfirm(Long[] id, Integer[] confirm_num) throws Exception;

	/**
	 * <p>
	 * Title: saveConfirm
	 * </p>
	 * <p>
	 * Description:保存渠道的效果数
	 * </p>
	 * 
	 * @param id
	 * @param confirm_num
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-27
	 * @return void
	 * @version 1.0
	 */
	public int saveConfirm(Long id, Integer confirm_num) throws Exception;

	/**
	 * <p>
	 * Title: confirmationAmount
	 * </p>
	 * <p>
	 * Description:根据渠道指数给渠道分数
	 * </p>
	 * 
	 * @param id
	 * @param amount
	 * @author lichuang
	 * @date 2013-7-18
	 * @return void
	 * @version 1.0
	 */
	public void confirmationAmount(Long id, Integer amount, Double out_price)
			throws Exception;

	/**
	 * <p>
	 * Title: getPrice
	 * </p>
	 * <p>
	 * Description:查看和媒体结算单价
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-7-29
	 * @return Double
	 * @version 1.0
	 */
	public Double getPrice(Long id) throws Exception;

	/**
	 * <p>
	 * Title: confirmationAmount
	 * </p>
	 * <p>
	 * Description:保存分数
	 * </p>
	 * 
	 * @param id
	 * @param amount
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-7-29
	 * @return void
	 * @version 1.0
	 */
	public void confirmationAmount(Long id, Integer amount) throws Exception;

	/**
	 * 
	 * <p>
	 * Title: updateChannelCost
	 * </p>
	 * <p>
	 * Description:更新渠道成本
	 * </p>
	 * 
	 * @param id
	 * @param sys_cost
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-9-22
	 * @return void
	 * @version 1.0
	 * @param num 
	 */
	public void updateChannelCost(Long id, Double sys_cost, Integer num) throws Exception;

	/**
	 * 
	 * <p>
	 * Title: updateStatus
	 * </p>
	 * <p>
	 * Description:更新渠道数据状态
	 * </p>
	 * 
	 * @param id
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-10-17
	 * @return void
	 * @version 1.0
	 */
	public void submitData(Long id,Integer status) throws Exception;

}
