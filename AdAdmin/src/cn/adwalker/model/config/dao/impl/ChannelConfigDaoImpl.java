package cn.adwalker.model.config.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.config.dao.IChannelConfigDao;
import cn.adwalker.model.config.domain.ChannelConfig;
@Repository("channelConfigDao")
public class ChannelConfigDaoImpl extends  BaseDaoImpl<ChannelConfig> implements IChannelConfigDao{
	
	public ChannelConfigDaoImpl(){
		super();
		this.tableName="t_channel_config";
	}
	@Override
	public void save(ChannelConfig config) {
		String sql="insert into "+this.tableName+" (channel,url,channel_name,adid_para,"
				+" deviceid_para,time_para,udid,client_ip,openudid,idfa,idfv,service_name,pass_rate )" +
				"values(:channel,:url,:channel_name,:adid_para,:deviceid_para,:time_para,:udid,:client_ip,:openudid,:idfa,:idfv,:service_name,:pass_rate)";
		 SqlParameterSource ps=new BeanPropertySqlParameterSource(config);
		 this.namedParameterJdbcTemplate.update(sql, ps);
	}

	@Override
	public void update(ChannelConfig config) {
		String sql="update "+this.tableName+" set url=:url,channel_name=:channel_name,adid_para=:adid_para,"
				+" deviceid_para=:deviceid_para,time_para=:time_para,udid=:udid," +
				"client_ip=:client_ip,openudid=:openudid,idfa=:idfa,idfv=:idfv,service_name=:service_name,pass_rate=:pass_rate,channel_id=:channel_id  where channel=:channel";
		 SqlParameterSource ps=new BeanPropertySqlParameterSource(config);
		this.namedParameterJdbcTemplate.update(sql, ps);
		
	}

	@Override
	public void delById(String channel) {
		String sql="delete from "+this.tableName+" where channel=?";
		this.jdbcTemplate.update(sql, new Object[]{channel});
	}

	@Override
	public ChannelConfig getConfigByid(String channel) {
		String sql="select * from "+tableName+" where channel=?";
		ChannelConfig config=null;
		try{
		    config=this.jdbcTemplate.queryForObject(sql,  new BeanPropertyRowMapper<ChannelConfig>(ChannelConfig.class), new Object[]{channel});
		}catch(Exception e){}
		return config;
	}

}
