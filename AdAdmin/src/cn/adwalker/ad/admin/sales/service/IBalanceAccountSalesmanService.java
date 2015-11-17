package cn.adwalker.ad.admin.sales.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.adwalker.ad.admin.ad.form.CampaignPaymentsForm;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.sales.bean.BalanceAccountSalesmanBean;
import cn.adwalker.ad.admin.sales.vo.BalanceAccountInfoVo;
import cn.adwalker.ad.admin.sales.vo.BalanceAccountVo;
import cn.adwalker.core.page.IPageInfo;

/**
 * 
 * @author zouguibao
 * @version 1.0
 * @since 2013-5-14
 */
public interface IBalanceAccountSalesmanService {
	/**
	 * <p>
	 * Title: findByPage
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfor
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-5-15
	 * @return List<MediaRatingVo>
	 * @version 1.0
	 * @throws
	 */

	public List<BalanceAccountVo> findByPage(BalanceAccountSalesmanBean bean,
			IPageInfo pageInfor) throws Exception;

	/**
	* <p>Title: savePayments</p>
	* <p>Description:TODO</p>
	* @param form
	* @author cuidd
	* @date 2014-2-24
	* @return void
	* @version 1.0
	 * @param user 
	 * @throws Exception 
	*/
	public void savePayments(CampaignPaymentsForm form, SysUserVo user) throws Exception;

	/**
	* <p>Title: bad_debt</p>
	* <p>Description:TODO</p>
	* @param id
	* @param user
	* @author cuidd
	* @date 2014-2-24
	* @return void
	* @version 1.0
	 * @throws Exception 
	*/
	public void bad_debt(Long id, SysUserVo user) throws Exception;

	/**
	* <p>Title: updateConfirmMoney</p>
	* <p>Description:TODO</p>
	* @param id
	* @param confirm_money
	* @param confirm_date
	* @author cuidd
	* @date 2014-2-24
	* @return void
	* @version 1.0
	 * @throws Exception 
	*/
	
	public void updateConfirmMoney(Long id, BigDecimal confirm_money,
			Date confirm_date) throws Exception;

	/**
	* <p>Title: info</p>
	* <p>Description:TODO</p>
	* @param id
	* @author cuidd
	* @date 2014-2-24
	* @return void
	* @version 1.0
	 * @throws Exception 
	*/
	
	public BalanceAccountInfoVo info(Long id) throws Exception;

	/**
	* <p>Title: sendBalanceMail</p>
	* <p>Description:TODO</p>
	* @param id
	* @author cuidd
	* @date 2014-2-25
	* @return void
	* @version 1.0
	 * @throws Exception 
	*/
	public void sendBalanceMail(Long id) throws Exception;

}
