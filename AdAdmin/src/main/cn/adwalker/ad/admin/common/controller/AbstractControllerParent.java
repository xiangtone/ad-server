/*
 * ControllerParent.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 08-Dec-2011
 */
package cn.adwalker.ad.admin.common.controller;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;

import cn.adwalker.ad.admin.common.vo.LoginVo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.core.context.Context;
import cn.adwalker.core.pool.SpringDatePool;
import cn.adwalker.core.spring.AppContext;
import cn.adwalker.model.sys.domain.SysResource;
import cn.adwalker.model.sys.domain.SysUser;

import com.sun.org.apache.commons.beanutils.BeanUtils;

/**
 * 功能概述：<br>
 * 控制层父类
 * 
 * @author zhaozengbin
 */
public abstract class AbstractControllerParent {
	@Resource
	private LoginVo currentUser;

	protected SysUserVo manageUser;

	/**
	 * 获取当前登录管理员
	 * 
	 * @return
	 * @throws Exception
	 */
	public SysUser getUserAdmin() {
		SysUser manageUser = new SysUser();
		try {
			BeanUtils.copyProperties(manageUser, currentUser);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return manageUser;
	}

	/**
	 * @param currentUser
	 *            The currentUser to set.
	 */
	public void setCurrentUser(LoginVo currentUser) {
		this.currentUser = currentUser;
	}

	protected SysResource getResource() {
		SysResource resource = null;
		ApplicationContext ctx = AppContext.getApplicationContext();
		SpringDatePool springDatePool = (SpringDatePool) ctx
				.getBean("springDatePool");
		String uri = Context.getInstance().getRequestURI();
		if (!StringUtils.isEmpty(uri)) {
			if (uri.indexOf("/") != -1) {
				uri = uri.substring(uri.lastIndexOf("/"), uri.length());// 截取
																		// ("\\/")[1];
			}
		}
		resource = springDatePool.getResource(uri);
		return resource;
	}

}
