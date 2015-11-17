package cn.adwalker.model.finance.dao;

import java.util.List;

import cn.adwalker.model.finance.domain.DevListAwardDetail;
import cn.adwalker.ad.admin.finance.bean.DevListAwardDetailbean;
import cn.adwalker.ad.admin.finance.vo.FinanceIncomeSumVo;
import cn.adwalker.core.repository.IBaseDao;

@SuppressWarnings("rawtypes")
public interface IDevListAwardDetailDao extends IBaseDao {

	/**
	 * 根据条件查询列表汇总
	 * 
	 * @param vo
	 * @param pageIndex
	 * @param pageRecord
	 * @return
	 * @throws Exception
	 */
	public List<FinanceIncomeSumVo> findSummaryByCondition(
			DevListAwardDetailbean bean) throws Exception;

	/**
	 * 插入数据
	 * 
	 * @param devListAwardDetail
	 * @return
	 * @throws Exception
	 */
	public int insert(DevListAwardDetail devListAwardDetail) throws Exception;
}
