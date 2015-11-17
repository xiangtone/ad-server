package cn.adwalker.model.app.dao.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.admin.app.bean.AppMediaBean;
import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.app.dao.IMediacallbackConfigDao;
import cn.adwalker.model.app.domain.MediacallbackConfigEntity;


@Repository("mediacallbackConfigDao")
public class MediacallbackConfigDaoImpl  extends BaseDaoImpl<MediacallbackConfigEntity> implements IMediacallbackConfigDao {
	public MediacallbackConfigDaoImpl() {
		super.setTableName("t_mediacallback_config");
	}
	@Override
	public int saveOrUpdate(AppMediaBean app) throws Exception {
		MediacallbackConfigEntity entity = getMediacallbackConfigByid(app.getId());//(MediacallbackConfigEntity) this.get(app.getId(), MediacallbackConfigEntity.class);
		boolean flag = false;
		if (entity != null) {
			flag = true;
		} else {
			entity = new MediacallbackConfigEntity();
			entity.setAppid(app.getId());
		}
		entity.setDetain_rate(app.getDetainRate());
		entity.setScheme_id(app.getSchemeId());
		entity.setIpsegment_times(app.getIpsegmentTimes());
		entity.setIp_times(app.getIpTimes());
		entity.setCa_time_ratio(app.getCaTimeRatio());
		if (flag) {
			return this.updateObject(entity);
		} else {
			return this.insertAPP(entity);
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
		sb.append(" WHERE appid=:appid");
		return namedParameterJdbcTemplate.update(sb.toString(),
				new BeanPropertySqlParameterSource(obj));

	}
	
	private MediacallbackConfigEntity getMediacallbackConfigByid(Long id) {
		String sql="select * from t_mediacallback_config where appid=?";
		MediacallbackConfigEntity config=null;
		try{
		    config=super.jdbcTemplate.queryForObject(sql,  new BeanPropertyRowMapper<MediacallbackConfigEntity>(MediacallbackConfigEntity.class), new Object[]{id});
		}catch(Exception e){}
		return config;
	}
	
	private int insertAPP(MediacallbackConfigEntity entity) {
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
	public int delMediacallbackConfig(Long id) throws Exception {
		String str = "delete from " + tableName + " where appid=?";
		return super.namedParameterJdbcTemplate.getJdbcOperations().update(str, id);		
	}
}
