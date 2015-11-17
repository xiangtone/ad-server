/**
* <p>Title: AdvertiseDaoImpl.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-4-14
* @version 1.0
*/
package cn.adwalker.ad.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.bean.SysSort;
import cn.adwalker.ad.dao.CommonDao;
import cn.adwalker.ad.picker.statement.Statement;
/**
 * <p>Title: AdvertiseDaoImpl</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-14
 */
@Repository("portalDao")
public class PortalDao extends CommonDao{
	public SysSort findEscoreSortByCateId(Integer cateId){
		Statement stms = stmsFactory.createNativeStatement("SELECT t.parent_id FROM t_escore_sort t WHERE t.id=?");
		          stms.addParam(cateId);
		return simpleJdbcTemplate.findObject(stms, SysSort.class);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<String> queryBlackIpList(){
		Statement stms = stmsFactory.createNativeStatement("SELECT t.ipduan FROM t_black_ipduan t WHERE t.state='1'");
		
		RowMapper<String> rm = new RowMapper<String>() {
				@Override
				public String mapRow(ResultSet rs, int arg1) throws SQLException {
				   return rs.getString(1); 
				}
			};
		return (List<String>)simpleJdbcTemplate.queryListForObject(stms, rm);
	}
	
	
	
}
