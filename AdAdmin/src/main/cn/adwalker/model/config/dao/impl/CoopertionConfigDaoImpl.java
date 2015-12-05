package cn.adwalker.model.config.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.config.dao.ICooperationConfigDao;
import cn.adwalker.model.config.domain.CooperationConfig;
@Repository("coopertionConfigDao")
public class CoopertionConfigDaoImpl extends BaseDaoImpl<CooperationConfig> implements ICooperationConfigDao{

	public CoopertionConfigDaoImpl(){
		super();
		this.tableName="t_cooperation_app_rel";
	}
	/**
	 * 根据id 查询
	 */
	@Override
	public CooperationConfig getConfigByAppId(Long id) {
		String sql="select app_id,response_url,create_time from "+this.tableName+" where app_id=?";
		try{
			CooperationConfig config=(CooperationConfig)this.jdbcTemplate
					.queryForObject(sql, new BeanPropertyRowMapper<CooperationConfig>(CooperationConfig.class) , new Object[]{id});
			return config;
		}catch(Exception e){
			return null;
		}
	}
	/**
	 * 删除
	 */
	@Override
	public boolean deleteByAppId(int id) {
		String sql="delete from "+this.tableName+" where app_id=?";
		this.jdbcTemplate.update(sql, new Object[]{id},new int[]{java.sql.Types. INTEGER} );
		return true;
	}
	/**
	 * 保存
	 */
	@Override
	public boolean saveConfig(CooperationConfig config) {
		String sql="insert into "+this.tableName+" (app_id,response_url,create_time) values (:app_id,:response_url,:create_time)";
		SqlParameterSource ps=new BeanPropertySqlParameterSource(config);
		this.namedParameterJdbcTemplate.update(sql, ps);
		return true;
	}
	/**
	 * 更新
	 */
	@Override
	public boolean updateConfig(CooperationConfig config) {
		String sql="update  "+this.tableName+" set response_url= :response_url where app_id=:app_id ";
		SqlParameterSource ps=new BeanPropertySqlParameterSource(config);
		this.namedParameterJdbcTemplate.update(sql, ps);
		return true;
	}

}
