package cn.adwalker.ad.admin.finance.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.finance.bean.DevPunishDetailbean;
import cn.adwalker.ad.admin.finance.form.FinancePunishDetail;
import cn.adwalker.ad.admin.finance.vo.DevListAwardDetailVo;
import cn.adwalker.ad.admin.finance.vo.FinanceIncomeSumVo;

public interface IFinancePunishDetailService {
	/**
	 * 
	 * <p>
	 * Title: findByList
	 * </p>
	 * <p>
	 * Description:扣费list
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-7-3
	 * @return List<DevListAwardDetailVo>
	 * @version 1.0
	 */
	public List<DevListAwardDetailVo> findByList(DevPunishDetailbean bean,
			IPageInfo pageInfo) throws Exception;

	/**
	 * <p>
	 * Title: findBySum
	 * </p>
	 * <p>
	 * Description:扣费总和
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-7-3
	 * @return FinanceIncomeSumVo
	 * @version 1.0
	 */
	public FinanceIncomeSumVo findBySum(DevPunishDetailbean bean) throws Exception;
	/**
	* <p>Title: updateDevPunish</p>
	* <p>Description:扣款开发者</p>
	* @param from
	* @throws Exception
	* @author lichuang
	* @date 2013-7-4
	* @return void
	* @version 1.0
	 */
	public void updateDevPunish(FinancePunishDetail from,SysUserVo currenUser) throws Exception;
	/**
	* <p>Title: findByall</p>
	* <p>Description:扣费list无分页</p>
	* @param bean
	* @param pageInfo
	* @return
	* @author lichuang
	* @date 2013-7-4
	* @return List<DevListAwardDetailVo>
	* @version 1.0
	 */
public List<DevListAwardDetailVo> findByall(DevPunishDetailbean bean)throws Exception;

}
