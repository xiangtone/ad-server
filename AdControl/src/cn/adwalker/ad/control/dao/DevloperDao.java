package cn.adwalker.ad.control.dao;

import javax.annotation.Resource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("userDeveloperDao")
public class DevloperDao {
	
	@Resource(name="namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	/**
	* <p>Title: updateMonthFinance</p>
	* <p>Description:每月5号月结算数据定时</p>
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014年7月15日
	* @return int
	* @version 1.0
	 */
	public int updateMonthFinance() throws Exception {
		
		StringBuffer sql = new StringBuffer();
			sql.append("update T_DEVELOPER set FINANCE_INCOME=IFNULL(FINANCE_INCOME,0.0)+IFNULL(MONTH_INCOME,0.0)");
			return namedParameterJdbcTemplate.getJdbcOperations().update(sql.toString());
	}
	/**
	* <p>Title: updateMonth</p>
	* <p>Description:每月5号月结算数据定时</p>
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014年7月15日
	* @return int
	* @version 1.0
	 */
	
	public int updateMonth() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update T_DEVELOPER set MONTH_INCOME=0");
		return namedParameterJdbcTemplate.getJdbcOperations().update(sql.toString());
	}
}
