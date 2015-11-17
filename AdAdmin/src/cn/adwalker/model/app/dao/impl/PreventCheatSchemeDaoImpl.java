package cn.adwalker.model.app.dao.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.admin.app.bean.PreventBean;
import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.app.dao.IPreventCheatSchemeDao;
import cn.adwalker.model.app.domain.PreventCheatScheme;


@Repository("preventCheatSchemeDao")
public class PreventCheatSchemeDaoImpl extends BaseDaoImpl<PreventCheatScheme> implements IPreventCheatSchemeDao {
	public PreventCheatSchemeDaoImpl() {
		super.setTableName("t_prevent_cheat_scheme");
	}
	
	@Override
	public int saveOrUpdate(PreventBean app) throws Exception {
		PreventCheatScheme entity = (PreventCheatScheme) this.get(app.getId(), PreventCheatScheme.class);
		boolean flag = false;
		if (entity != null) {
			flag = true;
		} else {
			entity = new PreventCheatScheme();
			entity.setId(app.getId());
		}
		entity.setName(app.getName());
		entity.setIs_default(app.getIsDefault());
		entity.setAdormedia(app.getAdormedia());
	
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
	
	private int insert(PreventCheatScheme entity) {
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
	public List<PreventCheatScheme> getPreventCheatSchemeList(Integer type) throws Exception {
		StringBuffer sb = new StringBuffer("select * from t_prevent_cheat_scheme where 1=1 ");
		if (type > -1) {
			sb.append(" and adormedia="+type);
		}
		return super.namedParameterJdbcTemplate.getJdbcOperations().query(sb.toString(), new BeanPropertyRowMapper<PreventCheatScheme>(PreventCheatScheme.class));
	}
	
	@Override
	public PreventCheatScheme getIsDefaultPreventCheatScheme(Integer isDefault,Integer adormedia) {
		String sql = "SELECT * FROM  t_prevent_cheat_scheme WHERE is_default=? AND adormedia=?";
		try {
			return super.namedParameterJdbcTemplate.getJdbcOperations().queryForObject(sql, new BeanPropertyRowMapper<PreventCheatScheme>(PreventCheatScheme.class), isDefault,adormedia);
		} catch (EmptyResultDataAccessException e) {			
			return null;	
	   }
	}

	@Override
	public PreventCheatScheme getPreventCheatSchemeById(Long id) {
		String sql = "SELECT * FROM  t_prevent_cheat_scheme WHERE id=?";
		try {
			return super.namedParameterJdbcTemplate.getJdbcOperations().queryForObject(sql, new BeanPropertyRowMapper<PreventCheatScheme>(PreventCheatScheme.class),id);
		} catch (EmptyResultDataAccessException e) {			
			return null;	
	   }
	}
}
	
