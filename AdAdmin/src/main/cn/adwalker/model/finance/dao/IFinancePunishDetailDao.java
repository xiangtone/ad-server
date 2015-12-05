package cn.adwalker.model.finance.dao;

import java.util.List;

import cn.adwalker.ad.admin.finance.bean.DevPunishDetailbean;
import cn.adwalker.ad.admin.finance.vo.FinanceIncomeSumVo;
import cn.adwalker.core.repository.IBaseDao;

@SuppressWarnings("rawtypes")
public interface IFinancePunishDetailDao extends IBaseDao {
	/**
	* <p>Title: findBySummary</p>
	* <p>Description:扣款汇总</p>
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-7-4
	* @return List<FinanceIncomeSumVo>
	* @version 1.0
	 */
	public List<FinanceIncomeSumVo> findBySummary(DevPunishDetailbean bean)throws Exception;
}
