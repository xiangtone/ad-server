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
import cn.adwalker.model.sys.dao.IPermissionResRelDao;
import cn.adwalker.model.sys.domain.PermissionResRel;

/**
 * 
 * <p>
 * Title: ResourceDaoImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-3-15
 */
@Repository
public class PermissionResRelDaoImpl extends BaseDaoImpl<PermissionResRel> implements IPermissionResRelDao {

	public PermissionResRelDaoImpl() {
		setTableName("T_SYS_PERMISSION_RES_REL");
	}

}