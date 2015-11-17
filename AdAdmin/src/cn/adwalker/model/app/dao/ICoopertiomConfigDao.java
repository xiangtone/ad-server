package cn.adwalker.model.app.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import cn.adwalker.model.config.domain.CooperationConfig;

public interface ICoopertiomConfigDao {

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
	public CooperationConfig getConfigByAppId(Long id);
	
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
	
	public boolean saveConfig(CooperationConfig config);
	
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
	public boolean updateConfig(CooperationConfig config);

}
