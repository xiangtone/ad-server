package cn.adwalker.ad.dao;

import cn.adwalker.ad.dao.domain.DevCurrency;


public interface ICurrencyDao {

	
	/**
	 *查询虚拟货币
	 * 
	 * @param channel
	 * @return
	 * @throws Exception
	 */
	public DevCurrency findCurrency(Long id);
}
