package cn.adwalker.ad.admin.operation.service;

import java.util.List;

import cn.adwalker.ad.admin.operation.bean.ConfirmNumDetailbean;
import cn.adwalker.ad.admin.operation.bean.ConfirmNumberbean;
import cn.adwalker.ad.admin.operation.vo.AdvNumberSumVo;
import cn.adwalker.ad.admin.operation.vo.ConfirmationNumberDetailVo;
import cn.adwalker.ad.admin.operation.vo.ConfirmationNumberVo;
import cn.adwalker.ad.admin.operation.vo.FractionNumberVo;
import cn.adwalker.core.page.IPageInfo;

/**
 * <p>
 * Title: IConfirmationNumberService
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
public interface IConfirmationNumberService {

	/**
	 * <p>
	 * Title: findList
	 * </p>
	 * <p>
	 * Description:广告主确认数录入List
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-23
	 * @return List<ConfirmationNumberVo>
	 * @version 1.0
	 */

	public List<ConfirmationNumberVo> findList(ConfirmNumberbean bean,
			IPageInfo pageInfo) throws Exception;

	/**
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
	public void confirmationNumber(Long id, Integer number) throws Exception;

	/**
	 * <p>
	 * Title: findFractionList
	 * </p>
	 * <p>
	 * Description:查询分数渠道
	 * </p>
	 * 
	 * @param package_id
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-5-27
	 * @return List<FractionNumberVo>
	 * @version 1.0
	 */
	public List<FractionNumberVo> findFractionList(Long id) throws Exception;

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
	public void saveConfirm(Long[] id, Integer[] confirm_num, Long fraction_id)
			throws Exception;

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
	 * @date 2013-7-13
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */
	public void submitConfirmNumber(String ids) throws Exception;

	/**
	 * <p>
	 * Title: findDetailByPList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @author cuidd
	 * @date 2013-7-16
	 * @return List<ChannelEffectVo>
	 * @version 1.0
	 * @throws Exception
	 */

	public List<ConfirmationNumberDetailVo> findDetailByPage(
			ConfirmNumDetailbean bean, IPageInfo pageInfo) throws Exception;

	/**
	 * <p>
	 * Title: findAll
	 * </p>
	 * <p>
	 * Description:导出报表
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-7-22
	 * @return List<ChannelEffectVo>
	 * @version 1.0
	 */
	public List<ConfirmationNumberDetailVo> findAll(ConfirmNumDetailbean bean)
			throws Exception;

	/**
	 * <p>
	 * Title: getNumberSum
	 * </p>
	 * <p>
	 * Description:求和
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-8-5
	 * @return ChannelEffectSumVo
	 * @version 1.0
	 */
	public AdvNumberSumVo getNumberSum(ConfirmNumDetailbean bean)
			throws Exception;

	/**
	 * <p>
	 * Title: restConfirmationNumber
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @author cuidd
	 * @date 2013-8-13
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */
	public void restConfirmationNumber(Long id) throws Exception;

	/**
	 * <p>
	 * Title: delConfirmationNumber
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @author cuidd
	 * @date 2013-8-13
	 * @return void
	 * @version 1.0
	 * @throws Exception
	 */
	public void delConfirmationNumber(Long id) throws Exception;

	/**
	 * 
	* <p>Title: findAll</p>
	* <p>Description:查询所有</p>
	* @param bean
	* @return
	* @author cuidd
	* @date 2014-7-7
	* @return List<Object>
	* @version 1.0
	 * @throws Exception 
	 */
	public List<Object> findAll(ConfirmNumberbean bean) throws Exception;
}
