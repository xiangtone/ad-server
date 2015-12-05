package cn.adwalker.model.config.dao.impl;

import java.util.Date;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.config.dao.ICpIosConfigDao;
import cn.adwalker.model.config.domain.CampaignConfig;
/**
 * <p>广告主对接DAO</p>
 * @author jief
 *
 */
@SuppressWarnings("rawtypes")
@Repository("cpIosConfigDao")
public class CpIosConfigDaoImpl  extends BaseDaoImpl implements ICpIosConfigDao{


	public CpIosConfigDaoImpl(){
		super();
		this.setTableName("t_campaign_config");
	}

	@Override
	public void saveOrUpdate(CampaignConfig config) {
		CampaignConfig cf=this.getConfigById(config.getId());
		if(cf==null){
			config.setCreate_time(new Date());
			String sql="insert into T_CAMPAIGN_CONFIG(ad_key,ad_name,adv_price,create_time,url,adid_str,deviceid_para,sourse_str,eventtime_para,send_type,source,client_ip,placement_id,openudid,idfa,idfv,service_name,callback,pass_rate,udid) " +
					" values(:ad_key,:ad_name,:adv_price,:create_time,:url,:adid_str,:deviceid_para,:sourse_str,:eventtime_para,:send_type,:source,:client_ip,:placement_id,:openudid,:idfa,:idfv,:service_name,:callback,:pass_rate,:udid)";
			 SqlParameterSource ps=new BeanPropertySqlParameterSource(config);
			this.namedParameterJdbcTemplate.update(sql, ps);
		}else{
			config.setId(cf.getId());
			String sql="update T_CAMPAIGN_CONFIG set ad_key=:ad_key,ad_name=:ad_name,adv_price=:adv_price," +
					" url=:url,adid_str=:adid_str," +
					" deviceid_para=:deviceid_para,sourse_str=:sourse_str,eventtime_para=:eventtime_para,send_type=:send_type," +
					" source=:source,client_ip=:client_ip,placement_id=:placement_id,openudid=:openudid,idfa=:idfa,idfv=:idfv," +
					" service_name=:service_name,callback=:callback,pass_rate=:pass_rate,udid=:udid  where id=:id"; 
			 SqlParameterSource ps=new BeanPropertySqlParameterSource(config);
			this.namedParameterJdbcTemplate.update(sql, ps);
		}
	}
	
	
	
	

	@Override
	public void delById(String id) {
		String str = "delete from " + tableName + " where id=?";
		jdbcTemplate.update(str, new Object[] { id });
	}

	@Override
	public CampaignConfig getConfigById(Long id) {
		String sql="select * from "+tableName+" where id=?";
		CampaignConfig config=null;
		try{
		    config=this.jdbcTemplate.queryForObject(sql,  new BeanPropertyRowMapper<CampaignConfig>(CampaignConfig.class), new Object[]{id});
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return config;
	}

	
	@Override
	public void updatePrice(String config_id, Double price) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE T_CAMPAIGN_CONFIG SET adv_price = ?  where id= ? ");
		jdbcTemplate.update(sql.toString(), new Object[] { price, config_id });
		
	}

	@Override
	public void save(CampaignConfig config) {
		CampaignConfig cf=this.getConfigById(config.getId());
		if(cf==null){
			config.setCreate_time(new Date());
			String sql="insert into T_CAMPAIGN_CONFIG(ad_key,ad_name,adv_price,create_time,url,adid_str,deviceid_para,sourse_str,eventtime_para,send_type,source,client_ip,placement_id,openudid,idfa,idfv,service_name,callback,pass_rate,udid) " +
					" values(:ad_key,:ad_name,:adv_price,:create_time,:url,:adid_str,:deviceid_para,:sourse_str,:eventtime_para,:send_type,:source,:client_ip,:placement_id,:openudid,:idfa,:idfv,:service_name,:callback,:pass_rate,:udid)";
			 SqlParameterSource ps=new BeanPropertySqlParameterSource(config);
			this.namedParameterJdbcTemplate.update(sql, ps);
		}
	}
}
