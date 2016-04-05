package cn.adwalker.ad.model.application.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.model.application.domain.CooperationConfig;
import cn.adwalker.ad.model.page.dao.BaseDao;

/**
* <p>Title: CoopertiomConfigDao</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author    lichuang
* @date       2014年7月25日
 */
@Repository("cooperConfigDao")
public class CoopertiomConfigDao extends BaseDao {

	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	/**
	* <p>Title: getConfigByAppId</p>
	* <p>Description:TODO</p>
	* @param id
	* @return
	* @author cuidd
	* @date 2014年7月25日
	* @return CooperationConfig
	* @version 1.0
	 */
	public CooperationConfig getConfigByAppId(Long id) {
		StringBuffer sql = new StringBuffer();
		sql.append("select app_id,response_url,create_time from t_cooperation_app_rel where app_id=?");
		List<CooperationConfig> objects = namedParameterJdbcTemplate.getJdbcOperations().query(sql.toString(), new BeanPropertyRowMapper<CooperationConfig>(CooperationConfig.class), new Object[]{id});
		if (objects != null && objects.size() > 0) {
			return objects.get(0);
		}
		return null;
	}
	
	
	/**
	* <p>Title: saveConfig</p>
	* <p>Description:TODO</p>
	* @param config
	* @return
	* @author cuidd
	* @date 2014年7月25日
	* @return boolean
	* @version 1.0
	 */
	
	public boolean saveConfig(CooperationConfig config) {
		String sql="insert into t_cooperation_app_rel (app_id,response_url,create_time) values (:app_id,:response_url,:create_time)";
		SqlParameterSource ps=new BeanPropertySqlParameterSource(config);
		this.namedParameterJdbcTemplate.update(sql, ps);
		return true;
	}
	
	/**
	* <p>Title: updateConfig</p>
	* <p>Description:TODO</p>
	* @param config
	* @return
	* @author cuidd
	* @date 2014年7月25日
	* @return boolean
	* @version 1.0
	 */
	public boolean updateConfig(CooperationConfig config) {
		String sql="update  t_cooperation_app_rel set response_url= :response_url where app_id=:app_id ";
		SqlParameterSource ps=new BeanPropertySqlParameterSource(config);
		this.namedParameterJdbcTemplate.update(sql, ps);
		return true;
	}

}