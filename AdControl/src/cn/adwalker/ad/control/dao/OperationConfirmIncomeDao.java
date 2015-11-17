package cn.adwalker.ad.control.dao;

import javax.annotation.Resource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("confirmIncomeDao")
public class OperationConfirmIncomeDao {
	
	@Resource(name="namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	/**
	* <p>Title: countQuery</p>
	* <p>Description:TODO</p>
	* @param objects
	* @return
	* @author cuidd
	* @date 2014年6月18日
	* @return int
	* @version 1.0
	 */
	@SuppressWarnings("deprecation")
	public int countQuery( Object[] objects) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) from T_IOS_INCOME_NUMBER t where t.status=?");		
		Integer totalRow = namedParameterJdbcTemplate.getJdbcOperations().queryForInt(sql.toString(), objects);
		return totalRow;
	
		}
	
	
	/**
	* <p>Title: updateIos</p>
	* <p>Description:TODO</p>
	* @param objects
	* @author cuidd
	* @date 2014年6月18日
	* @return void
	* @version 1.0
	 */
	public void updateIos( Object[] objects) {		
		namedParameterJdbcTemplate.getJdbcOperations().update(" update T_IOS_INCOME_NUMBER set STATUS = 2  where STATUS = ? ",
				new Object[] { 0 });
		
	}
	
	
}
