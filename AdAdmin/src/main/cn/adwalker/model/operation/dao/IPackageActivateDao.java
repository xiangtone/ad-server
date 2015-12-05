package cn.adwalker.model.operation.dao;

import java.util.List;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.operation.domain.PackageActivate;
import cn.adwalker.model.operation.domain.PackageActivateAndroid;

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
public interface IPackageActivateDao extends IBaseDao<PackageActivate>{
	/**
	 * 
	 * <p>
	 * Title: confirmationNumber
	 * </p>
	 * <p>
	 * Description:广告主确认数录入
	 * </p>
	 * 
	 * @param id
	 * @param number
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-23
	 * @return void
	 * @version 1.0
	 */
	public void confirmationNumber(Long id, Integer number,Integer status) throws Exception;


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
	 * <p>
	 * Title: submitConfirmNumber
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param ids
	 * @author cuidd
	 * @date 2013-7-16
	 * @return void
	 * @version 1.0
	 */
	public void submitConfirmNumber(String ids);


	/**
	* <p>Title: confirmationCpd</p>
	* <p>Description:cpd确认的钱</p>
	* @param id
	* @param amount
	* @throws Exception
	* @author lichuang
	* @date 2013-8-8
	* @return void
	* @version 1.0
	 */
	public void confirmationCpd(Long id, Double amount)throws Exception;

	/**
	* <p>Title: getById</p>
	* <p>Description:读取提交android数据</p>
	* @param ids
	* @return
	* @author cuidd
	* @date 2014年11月14日
	* @return List<PackageActivateAndroid>
	* @version 1.0
	 */
	public PackageActivateAndroid getById(Long id)throws Exception;

}
