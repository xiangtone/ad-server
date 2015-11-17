/*
 * UserDeveloperDAOImpl.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 30-Nov-2011
 */
package cn.adwalker.model.sys.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.model.sys.dao.IDictionaryDao;
import cn.adwalker.ad.admin.sys.bean.SysDictionaryBean;
import cn.adwalker.ad.admin.sys.vo.SysDictionaryVo;
import cn.adwalker.core.repository.impl.BaseDaoImpl;

/**
 * 
 * <p>
 * Title: ResourceDaoImpl
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-3-15
 */
@SuppressWarnings("rawtypes")
@Repository("dictionaryDao")
public class DictionaryDaoImpl extends BaseDaoImpl implements IDictionaryDao {

	public DictionaryDaoImpl() {
		setTableName("T_SYS_RESOURCE");
	}

	@Override
	public SysDictionaryVo findByDictionaryId(Long id) {
		String sql = "select a.ID id,a.FIELD_NAME columnName,a.FIELD_TYPE columnType,a.FROM_TABLE tableName,a.FROM_VIEW viewName,b.FIELD_DESC columnDesc from T_FIELD_INFO a left join T_FIELD_DESC b on a.id=b.FIELD_ID where a.id=?";
		SysDictionaryVo vo = null;
		List<SysDictionaryVo> sysDictionaryVoList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<SysDictionaryVo>(SysDictionaryVo.class), id);
		if (sysDictionaryVoList != null && sysDictionaryVoList.size() > 0) {
			vo = sysDictionaryVoList.get(0);
		}
		return vo;
	}

	@Override
	public void updateDictionary(SysDictionaryBean form) {
		String updateSql = "update T_FIELD_INFO set FIELD_NAME=?,FIELD_TYPE=?,FROM_TABLE=?,FROM_VIEW=? where ID=" + form.getId();
		jdbcTemplate.update(updateSql, new Object[] { form.getColumnName(), form.getColumnType(), form.getTableName(), form.getViewName() });
		String sql = "select FIELD_DESC as columnDesc from T_FIELD_DESC where FIELD_ID=" + form.getId();
		List<SysDictionaryVo> sysDictionaryVoList = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<SysDictionaryVo>(SysDictionaryVo.class));
		if (sysDictionaryVoList != null && sysDictionaryVoList.size() > 0) {
			String sql1 = "update T_FIELD_DESC set FIELD_DESC='" + form.getColumnDesc() + "' where FIELD_ID=?";
			jdbcTemplate.update(sql1, new Object[] { form.getId() });
		} else {
			String insertSql = "insert into T_FIELD_DESC(FIELD_DESC,FIELD_ID) values (?,?)";
			jdbcTemplate.update(insertSql, new Object[] { form.getColumnDesc(), form.getId() });
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: addDictionary
	 * </p>
	 * 
	 * @param form
	 * @see cn.adwalker.model.app.dao.IApplicationDao#addDictionary(cn.adwalker.ad.admin.sys.bean.SysDictionaryBean)
	 */
	@Override
	public void addDictionary(SysDictionaryBean form) {
		String sql = "insert into T_FIELD_INFO(FIELD_NAME,FIELD_TYPE,FROM_TABLE,FROM_VIEW) values (?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] { form.getColumnName(), form.getColumnType(), form.getTableName(), form.getViewName() });
	}

}