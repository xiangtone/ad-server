/*
 * UserDeveloperDAOImpl.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 30-Nov-2011
 */
package cn.adwalker.model.sys.dao.impl;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.sys.dao.IResourceUserRelDao;
import cn.adwalker.model.sys.domain.PermissionResRel;

/**
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
@Repository
public class ResourceUserRelDaoImpl extends BaseDaoImpl<PermissionResRel> implements IResourceUserRelDao {

	public ResourceUserRelDaoImpl() {
		setTableName("T_SYS_RESOURCE_USER_REL");
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: needDataPermisstion
	 * </p>
	 * 
	 * @param resource_id
	 * @return
	 * @see cn.adwalker.model.sys.dao.IResourceUserRelDao#needDataPermisstion(java.lang.Long)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public boolean needDataPermisstion(Long resource_id, Long user_id) {
		boolean b = false;
		if (resource_id != null) {
			Integer countInteger = super.jdbcTemplate.queryForInt("select count(1) from T_SYS_RESOURCE_USER_REL where resource_id=? and user_id=?", new Object[] { resource_id, user_id });
			if (countInteger != null && countInteger >= 1) {
				b = true;
			}
		}
		return b;
	}

}