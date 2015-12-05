/*
 * FileUploadServiceImpl.java
 *
 * Copyright 2012 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 10-Jan-2012
 */
package cn.adwalker.ad.admin.common.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.adwalker.model.app.dao.IApplicationDao;
import cn.adwalker.model.app.domain.Application;
import cn.adwalker.ad.admin.common.service.IFileUploadService;
import cn.adwalker.core.util.ApkParser;
import cn.adwalker.core.util.ApkParserResult;
import cn.adwalker.core.util.ConfigUtil;
import cn.adwalker.core.util.lang.ObjectUtils;

/**
 * 功能概述：<br>
 * 文件上传
 * 
 * @author zhaozengbin
 */
@Service("fileUploadServiceImpl")
public class FileUploadServiceImpl implements IFileUploadService {
	Logger logger = Logger.getLogger(FileUploadServiceImpl.class);
	/** 开发者应用添加文件地址 */
	@Resource
	private IApplicationDao developedAppDao;

	/**
	 * 
	 * @see cn.adwalker.ad.admin.common.service.manager.common.service.IFileUploadService#updatePath(java.lang.Object,
	 *      java.lang.Class)
	 */
	public boolean updatePath(String path, Long appId, Long opeId,
			Class<? extends Object> objType) {
		String tar = ConfigUtil.getString("file.path");
		ApkParserResult apk = null;
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("上传完成路径为:" + tar + path);
				logger.debug("上传相对路径为:" + tar + path);
			}
			apk = ApkParser.getApkInfo(tar + path);
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (objType.equals(Application.class)) {
			Application developedApp = new Application();
			developedApp.setProjectUrl(path);
			if (ObjectUtils.isNotEmpty(opeId)) {
				developedApp.setLastUpdateMan(opeId);
			} else {
				developedApp.setLastUpdateMan(0l);
			}

			if (ObjectUtils.isNotEmpty(apk)) {
				developedApp.setPackageName(apk.getPackageName());
				developedApp.setVersionCode(apk.getVersionCode());
				developedApp.setVersionName(apk.getVersionName());
				developedApp.setResourceSize(apk.getSize());
			}
			developedApp.setUpdateTime(new Date());
			developedApp.setId(appId);
			try {
				developedAppDao.updatePath(developedApp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		return true;
	}

}
