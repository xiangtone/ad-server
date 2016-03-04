/*
 * DealApkFile.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 2011-12-27
 */
package cn.adwalker.core.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.common.util.ad.ApkParser;
import org.common.util.ad.ApkParserResult;

import cn.adwalker.core.listener.StartupListener;

/**
 * 功能概述：<br>
 * 
 * 解析apk文件工具
 * 
 * @author jiaozhichao
 */
public class DealApkFile {
	private static Log logger = LogFactory.getLog(StartupListener.class);

	/** 文件路径配置key */
	private static final String FILE_PATH_KEY = "file.path";

	/** 文件固定路径 */
	private static String FILE_PATH = "";

	static {
		FILE_PATH = ConfigUtil.getString(FILE_PATH_KEY);
	}

	/**
	 * 根据request处理apk，包含上传机制
	 * 
	 * @param req
	 *            Request
	 * @param fileName
	 *            网页input file 的名字
	 * @param dirName
	 *            上传后文件夹名字,类似 2011/11/11/dirnmae/
	 * @return
	 * @throws Exception
	 */
	public static ApkParserResult dealApkByRequest(HttpServletRequest req,
			String fileName, String[] dirName) throws Exception {

		// 开始上传文件
		String apkfilePath = FileUploadTool.upload(req, fileName, dirName,
				FileUploadTool.FILE);
		// 开始解apk信息
		ApkParserResult result = new ApkParserResult();

		try {

			result = ApkParser.getApkInfo(FILE_PATH + apkfilePath);
			result.setPath(apkfilePath);
			return result;
		} catch (Exception e) {
			logger.error("解析apk信息发生异常-" + DateUtil.formatDateTime(new Date())
					+ e.getLocalizedMessage());
			return null;
		}

	}

}
