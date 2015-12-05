package cn.adwalker.ad.admin.operation.service;

import java.util.List;

import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.operation.bean.ConfirmIosNumberbean;
import cn.adwalker.ad.admin.operation.bean.IosNumDetailbean;
import cn.adwalker.ad.admin.operation.vo.AdvNumberSumVo;
import cn.adwalker.ad.admin.operation.vo.ConfirmationIosNumberVo;
import cn.adwalker.ad.admin.operation.vo.FractionIosNumberVo;
import cn.adwalker.ad.admin.operation.vo.IosNumDetailVo;
import cn.adwalker.core.page.IPageInfo;

/**
* <p>Title: IConfirmationIosNumberService</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-11-8
 */
public interface IConfirmationIosNumberService {

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

	public List<ConfirmationIosNumberVo> findList(ConfirmIosNumberbean bean,
			IPageInfo pageInfo) throws Exception;

	/**
	* <p>Title: confirmationNumber</p>
	* <p>Description:IOS日确认数录入</p>
	* @param id
	* @param number
	* @throws Exception
	* @author lichuang
	* @date 2013-11-8
	* @return void
	* @version 1.0
	 */
	public void confIosNum(Long id, Integer number) throws Exception;

	/**
	* <p>Title: fractionIos</p>
	* <p>Description:IOS日确认数分数</p>
	* @param id
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-11-8
	* @return List<FractionIosNumberVo>
	* @version 1.0
	 */
	public List<FractionIosNumberVo> fractionIos(Long id) throws Exception;

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
	 * @param comfirm_cost 
	 */
	public void saveConfIos(Long[] id, Integer[] confirm_num, Integer[] comfirm_cost, Long fraction_id)
			throws Exception;

	/**
	* <p>Title: submitConfNumIos</p>
	* <p>Description:提交IOS日确认数</p>
	* @param ids
	* @throws Exception
	* @author lichuang
	* @date 2013-11-8
	* @return void
	* @version 1.0
	 */
	public void submitConfNumIos(String ids,SysUserVo manageUser) throws Exception;

	/**
	* <p>Title: findDetailByPage</p>
	* <p>Description:ios效果明细List</p>
	* @param bean
	* @param pageInfo
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-11-14
	* @return List<IosNumDetailVo>
	* @version 1.0
	 */

	public List<IosNumDetailVo> findDetailByPage(
			IosNumDetailbean bean, IPageInfo pageInfo) throws Exception;
	
	
	

	/**
	* <p>Title: findAll</p>
	* <p>Description:导出详细报表</p>
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-11-14
	* @return List<IosNumDetailVo>
	* @version 1.0
	 */
	public List<IosNumDetailVo> findAll(IosNumDetailbean bean)
			throws Exception;

	/**
	 * 
	* <p>Title: getNumberSum</p>
	* <p>Description:TODO</p>
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-11-14
	* @return AdvNumberSumVo
	* @version 1.0
	 */
	public AdvNumberSumVo getNumberSum(IosNumDetailbean bean)
			throws Exception;

	/**
	* <p>Title: restConfIosNum</p>
	* <p>Description:IOS日确认数重置</p>
	* @param id
	* @throws Exception
	* @author lichuang
	* @date 2013-11-8
	* @return void
	* @version 1.0
	 */
	public void restConfIosNum(Long id) throws Exception;

	/**
	* <p>Title: delConfIosNum</p>
	* <p>Description:IOS日确认数删除</p>
	* @param id
	* @throws Exception
	* @author lichuang
	* @date 2013-11-8
	* @return void
	* @version 1.0
	 */
	public void delConfIosNum(Long id) throws Exception;
}
