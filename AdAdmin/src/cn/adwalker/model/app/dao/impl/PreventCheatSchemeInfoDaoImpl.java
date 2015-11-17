package cn.adwalker.model.app.dao.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.admin.app.bean.PreventInfoBean;
import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.app.dao.IPreventCheatSchemeInfoDao;
import cn.adwalker.model.app.domain.PreventCheatSchemeInfo;


@Repository("preventCheatSchemeInfoDao")
public class PreventCheatSchemeInfoDaoImpl extends BaseDaoImpl<PreventCheatSchemeInfo> implements IPreventCheatSchemeInfoDao {
	public PreventCheatSchemeInfoDaoImpl() {
		super.setTableName("t_prevent_cheat_scheme_info");
	}
	
	@Override
	public int saveOrUpdate(PreventInfoBean app) throws Exception {
		PreventCheatSchemeInfo entity = (PreventCheatSchemeInfo) this.get(app.getId(), PreventCheatSchemeInfo.class);
		boolean flag = false;
		if (entity != null) {
			flag = true;
		} else {
			entity = new PreventCheatSchemeInfo();
			entity.setId(app.getId());
		}
		entity.setArea(app.getArea());
		entity.setScheme_id(app.getSchemeId());
		entity.setConfig(app.getConfig());
	
		if (flag) {
			return this.updateObject(entity);
		} else {
			return this.insert(entity);
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
		sb.append(" WHERE ID=:id");
		return namedParameterJdbcTemplate.update(sb.toString(),
				new BeanPropertySqlParameterSource(obj));

	}
	
	private int insert(PreventCheatSchemeInfo entity) {
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
}
