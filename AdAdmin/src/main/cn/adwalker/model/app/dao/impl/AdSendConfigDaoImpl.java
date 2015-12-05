package cn.adwalker.model.app.dao.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.admin.app.bean.AdSendBean;
import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.app.dao.IAdSendConfigDao;
import cn.adwalker.model.app.domain.AdSendConfigEntity;


@Repository("adSendConfigDao")
public class AdSendConfigDaoImpl  extends BaseDaoImpl<AdSendConfigEntity> implements IAdSendConfigDao {
	public AdSendConfigDaoImpl() {
		super.setTableName("t_ad_send_config");
	}
	@Override
	public int saveOrUpdate(AdSendBean app) throws Exception {
		AdSendConfigEntity entity = getAdSendConfigByid(app.getId());
		boolean flag = false;
		if (entity != null) {
			flag = true;
		} else {
			entity = new AdSendConfigEntity();
			entity.setAd_id(app.getId());
		}
		entity.setScheme_id(app.getSchemeId());
		entity.setAd_ipsegment_num(app.getAdIpsegmentNum());
		entity.setAd_ip_num(app.getAdIpNum());
		entity.setAd_bssid_num(app.getAdBssidNum());
		entity.setAd_latlon_num(app.getAdLatlonNum());
		if (flag) {
			return this.updateObject(entity);
		} else {
			return this.insertAdSend(entity);
		}
		
	}
	private int updateObject(Object obj) throws Exception {
		Field[] fields = obj.getClass().getDeclaredFields();
		List<String> arr = new ArrayList<String>();
		for (int i = 0, len = fields.length; i < len; i++) {
			if (!fields[i].getName().toUpperCase().equals("SERIALVERSIONUID")
					&& (!fields[i].getName().equals(super.ID))) {
				arr.add(fields[i].getName());
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE " + tableName + " SET ");
		for (String s : arr) {
			sb.append(s.toUpperCase() + "= :" + s + ",");
		}
		sb = sb.deleteCharAt(sb.length() - 1);
		sb.append(" WHERE ad_id=:ad_id");
		return namedParameterJdbcTemplate.update(sb.toString(),
				new BeanPropertySqlParameterSource(obj));

	}
	
	private AdSendConfigEntity getAdSendConfigByid(Long id) {
		String sql="select * from  t_ad_send_config where ad_id=?";
		AdSendConfigEntity config=null;
		try{
		    config=super.jdbcTemplate.queryForObject(sql,  new BeanPropertyRowMapper<AdSendConfigEntity>(AdSendConfigEntity.class), new Object[]{id});
		}catch(Exception e){}
		return config;
	}
	
	private int insertAdSend(AdSendConfigEntity entity) {
		Field[] fields = entity.getClass().getDeclaredFields();
		List<String> arr = new ArrayList<String>();
		for (int i = 0, len = fields.length; i < len; i++) {
			if (!fields[i].getName().toUpperCase().equals("SERIALVERSIONUID")) {
				if (!fields[i].getName().equals(super.ID)) {
					arr.add(fields[i].getName());
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(" insert into " + tableName + "(");
		for (String s : arr) {
			sb.append(s + ",");
		}
		sb = sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		sb.append(" values (");
		for (String s : arr) {
			if (!s.equals(super.ID)) {
				sb.append(":" + s + ",");
			}
		}
		sb = sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		return super.update(sb.toString(), entity);

	}
	@Override
	public int delAdSendConfig(Long id) throws Exception {
		String str = "delete from " + tableName + " where ad_id=?";
		return super.namedParameterJdbcTemplate.getJdbcOperations().update(str, id);	
	}
}
