package cn.adwalker.ad.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.dao.CommonDao;
import cn.adwalker.ad.dao.ICurrencyDao;
import cn.adwalker.ad.dao.domain.DevCurrency;

@Repository("currencyDao")
public class CurrencyDaoImpl extends CommonDao implements ICurrencyDao {
	
	/**
	 * (non-Javadoc)
	* <p>Title: findCurrency</p>
	* <p>Description:查询虚拟货币</p>
	* @param id
	* @return
	* @see cn.adwalker.ad.dao.ICurrencyDao#findCurrency(java.lang.Long)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DevCurrency findCurrency(Long id) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_DEV_CURRENCY t where t.STATUS=1 and t.app_id = ?");
		
//TODO  去掉select *
		List<Object> objects = simpleJdbcTemplate.query(sql.toString(),
				new BeanPropertyRowMapper(DevCurrency.class), id);
		DevCurrency currency = null;
		if (objects != null && objects.size() > 0) {
			currency = (DevCurrency) objects.get(0);
			return currency;
		}
		return null;
	}
}
