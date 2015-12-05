package cn.adwalker.ad.admin.operation.service;

import java.util.List;

import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.operation.bean.ConfirmNumChannelbean;
import cn.adwalker.ad.admin.operation.bean.ConfirmNumCpdbean;
import cn.adwalker.ad.admin.operation.bean.ConfirmNumDetailbean;
import cn.adwalker.ad.admin.operation.vo.ConfirmCpdSumVo;
import cn.adwalker.ad.admin.operation.vo.ConfirmNumberChannelVo;
import cn.adwalker.ad.admin.operation.vo.ConfirmNumberCpdVo;
import cn.adwalker.ad.admin.operation.vo.ConfirmationNumberDetailVo;
import cn.adwalker.ad.admin.operation.vo.FractionNumberVo;
import cn.adwalker.core.page.IPageInfo;

/**
 * <p>
 * Title: IChannelEffectQueryService
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
public interface IConfirmNumberChannelService {


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

	public List<ConfirmNumberChannelVo> findDetailByPage(
			ConfirmNumChannelbean bean, IPageInfo pageInfo) throws Exception;

	/**
	 * <p>
	 * Title: confirmationAmount
	 * </p>
	 * <p>
	 * Description:根据渠道指数给渠道分数
	 * </p>
	 * 
	 * @param id
	 * @param number
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-7-18
	 * @return void
	 * @version 1.0
	 */
	public void confirmationAmount(Long id, Integer amount, Integer media_type,Long campaign_id,SysUserVo managerUser)
			throws Exception;

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
	 * Title: findByCpdList
	 * </p>
	 * <p>
	 * Description:录入渠道cpaList
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-8-6
	 * @return List<ConfirmNumberCpdVo>
	 * @version 1.0
	 */
	public List<ConfirmNumberCpdVo> findByCpdList(ConfirmNumCpdbean bean,
			IPageInfo pageInfo) throws Exception;

	/**
	 * <p>
	 * Title: confirmationCpd
	 * </p>
	 * <p>
	 * Description:录入cpd的渠道返回的数据
	 * </p>
	 * 
	 * @param id
	 * @param amount
	 * @param media
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-8-6
	 * @return void
	 * @version 1.0
	 */
	public void confirmationCpd(Long id) throws Exception;

	/**
	 * 
	 * <p>
	 * Title: updateCpdData
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param amount
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-10-17
	 * @return void
	 * @version 1.0
	 * @param num 
	 */
	public void updateCpdData(Long id, Double amount, Integer num) throws Exception;

	/**
	 * <p>
	 * Title: findSum
	 * </p>
	 * <p>
	 * Description:Cpd数据录入汇总
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-10-12
	 * @return ConfirmCpdSumVo
	 * @version 1.0
	 * @throws Exception
	 */
	public ConfirmCpdSumVo findSum(ConfirmNumCpdbean bean) throws Exception;
}
