/**
 * 
 */
package cn.adwalker.ad.admin.finance.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.ad.domain.Advertiser;
import cn.adwalker.model.finance.domain.AdvRechargeLog;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.finance.bean.AdvRechargeBean;


/**
 * @author wjp
 * 广告主充值记录
 */
public interface IFinanceAdvRechargeService {

	/**
	 * 插入记录并且更新账户和账户广告信息
	 * @param advRechargeLog
	 * @return
	 * @throws Exception
	 */
	public int tranAddAndUpdateAdv(AdvRechargeLog advRechargeLog,Advertiser userAdver,SysUserVo currManageUser) throws Exception;

	/**
	* <p>Title: findByPage</p>
	* <p>Description:TODO</p>
	* @param pageInfo
	* @return
	* @author cuidd
	* @date 2013-5-26
	* @return List<AdvRechargeLog>
	* @version 1.0
	 * @param bean 
	 * @throws Exception 
	*/
	
	public List<AdvRechargeLog> findByPage(AdvRechargeBean bean, IPageInfo pageInfo) throws Exception;

}
