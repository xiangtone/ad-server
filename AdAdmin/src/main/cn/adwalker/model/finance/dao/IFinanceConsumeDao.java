package cn.adwalker.model.finance.dao;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.finance.domain.AdConsumeDetailVo;
@SuppressWarnings("rawtypes")
public interface IFinanceConsumeDao extends IBaseDao{
	/*****
	 * 获取广告明细表对象
	 * @param detail
	 * @return
	 */
	public AdConsumeDetailVo checkDevConsumeDetail(AdConsumeDetailVo detail);
	
}
