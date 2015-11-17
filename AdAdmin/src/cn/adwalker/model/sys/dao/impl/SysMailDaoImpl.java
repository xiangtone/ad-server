/*
 * UserLoginDaoImpl.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 2011-12-1
 */
package cn.adwalker.model.sys.dao.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.adwalker.model.sys.dao.ISysMailDao;

/**
 * 
* <p>Title: SysMailDaoImpl</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author    cuidd
* @date       2014-8-22
 */
@Repository
public class SysMailDaoImpl implements ISysMailDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	
	
}
