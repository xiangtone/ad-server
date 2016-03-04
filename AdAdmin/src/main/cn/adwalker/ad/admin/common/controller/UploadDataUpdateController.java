/*
 * UploadDataUpdateController.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 2012-2-27
 */
package cn.adwalker.ad.admin.common.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.common.util.ad.ApkParser;
import org.common.util.ad.ApkParserResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.core.util.ConfigUtil;
import cn.adwalker.core.util.OutputHelper;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.core.vo.ResultErrorVo;
import cn.adwalker.core.vo.ResultSuccessVo;
import cn.adwalker.core.vo.ResultVo;
import cn.adwalker.model.app.domain.Application;
import cn.adwalker.ad.admin.ad.form.PackageForm;
import cn.adwalker.ad.admin.ad.service.IPlacementPackageService;
import cn.adwalker.ad.admin.ad.vo.PlacementPackageVo;
import cn.adwalker.ad.admin.app.service.IApplicationService;
import cn.adwalker.ad.admin.common.vo.UploadResultVo;

/**
 * 功能描述<br>
 * 更新上传信息 控制层
 * 
 * @author guoyongxiang
 */
@Controller(value = "uploadDataUpdateController")
public class UploadDataUpdateController {

	private static Log logger = LogFactory
			.getLog(UploadDataUpdateController.class);

	@Resource
	private IApplicationService applicationService;


	@Resource
	private IPlacementPackageService placementPackageService;

	/**
	 * 更新应用信息
	 * 
	 * @param id
	 * @param path
	 * @param response
	 */
	@RequestMapping("/updateAppDate.do")
	public void updateAppDate(long id, String path, HttpServletResponse response) {
		try {
			path = java.net.URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e2) {
			logger.error(e2.toString());
			logger.error("编码转换失败！");
		}
		String tar = ConfigUtil.getString("file.path");// 读取文件地址前缀
		Application developedApp = new Application();
		developedApp.setId(id);
		developedApp.setProjectUrl(path);
		UploadResultVo uploadResultVo = new UploadResultVo();
		try {
			// ========此处解包处理流程start==========
			ApkParserResult pr = new ApkParserResult();
			try {
				if (logger.isDebugEnabled()) {
					logger.debug("上传完成路径为:" + tar + path);
					logger.debug("上传相对路径为:" + path);
				}
				if (path != null) {
					if (path.toLowerCase().endsWith(".apk")) {
						pr = ApkParser.getApkInfo(tar + path);
						logger.debug("android系统解包结束");
					}
				} else {
					logger.error("解包失败,未能获取到path！");
					uploadResultVo.setStatus("err");
					uploadResultVo.setErrMag("解包失败,未能获取到path！");
				}
				// 装配解包信息
				developedApp.setResourceSize(pr.getSize());
				developedApp.setPackageName(pr.getPackageName());
				developedApp.setVersionCode(pr.getVersionCode());
				developedApp.setVersionName(pr.getVersionName());

			} catch (Exception e1) {
				e1.printStackTrace();
				logger.error("解包失败未能更新解包相关信息,path:" + path);
				uploadResultVo.setStatus("err");
				uploadResultVo.setErrMag("解包失败未能更新解包相关信息,path:" + path);
			}
			// ========此处解包处理流程end==========
			uploadResultVo.setPath(path);// 相对路径
			uploadResultVo
					.setFileName(path.substring(path.lastIndexOf("/") + 1));// 截取文件名
			uploadResultVo.setUrlPrefix(ConfigUtil
					.getString("resources.url.prefix"));// 息
			try {
				applicationService.update(developedApp);// 更新文件信息
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("有错误了" + developedApp.getId());
			}

		} catch (Exception e) {
			e.printStackTrace();
			uploadResultVo.setStatus("err");
			uploadResultVo.setErrMag("文件储存地址更新失败！");
		}
		OutputHelper.writerJSON(uploadResultVo, response);
	}

	/**
	 * 更新广告信息
	 * 
	 * @param id
	 * @param path
	 * @param response
	 */
	@RequestMapping("/updateAdDate.do")
	public void updateAdDate(long id, String path, HttpServletResponse response) {
		try {
			path = java.net.URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
			logger.error("编码转换失败！");
		}
		ResultVo vo = null;
		try {
			// ========此处解包处理流程start==========
			ApkParserResult pr = new ApkParserResult();
			try {
				String tar = ConfigUtil.getString("file.path");// 读取文件地址前缀
				if (logger.isDebugEnabled()) {
					logger.debug("上传完成路径为:" + tar + path);
					logger.debug("上传相对路径为:" + path);
				}
				if (path != null) {
					if (path.toLowerCase().endsWith(".apk")) {
						pr = ApkParser.getApkInfo(tar + path);
					}
				} else {
					logger.error("解包失败,未能获取到path！");
					vo = new ResultErrorVo("解包失败,未能获取到path！");
				}
				PackageForm advertisement = new PackageForm();
				advertisement.setPlacement_id(id);
				advertisement.setResourceUrl(path);
				// 装配解包信息
				if (ObjectUtils.isNotEmpty(pr.getSize())) {
					advertisement.setResourceSize(pr.getSize().doubleValue());
				}
				if (ObjectUtils.isNotEmpty(pr.getPackageName())) {
					advertisement.setPackageName(pr.getPackageName());
				}
				if (ObjectUtils.isNotEmpty(pr.getVersionCode())) {
					advertisement.setVersionCode(pr.getVersionCode());
				}
				if (ObjectUtils.isNotEmpty(pr.getVersionName())) {
					advertisement.setVersionName(pr.getVersionName());
				}
				if (ObjectUtils.isNotEmpty(pr.getFileName())) {
					advertisement.setFileName(pr.getFileName());
				}
				PlacementPackageVo entity = placementPackageService
						.uploadPath(advertisement);// 更新文件信息

				// ========此处解包处理流程end==========
				vo = new ResultSuccessVo(entity);
			} catch (Exception e1) {
				logger.error("[updateAdDate]" + e1.getMessage());
				e1.printStackTrace();
				logger.error("解包失败未能更新解包相关信息,path:" + path);
				vo = new ResultErrorVo("解包失败未能更新解包相关信息,path:" + path);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("文件储存地址更新失败！");
			vo = new ResultErrorVo("文件储存地址更新失败！");
		}
		OutputHelper.writerJSON(vo, response);
	}

	/**
	 * 更新应用icon
	 * 
	 * @param id
	 * @param path
	 * @param response
	 */
	@Deprecated
	@RequestMapping("/updateAppIcon.do")
	public void updateAppIcon(long id, String path, HttpServletResponse response) {
		Application developedApp = new Application();
		developedApp.setId(id);
		UploadResultVo uploadResultVo = new UploadResultVo();
		try {
			uploadResultVo.setPath(path);// 相对路径
			uploadResultVo
					.setFileName(path.substring(path.lastIndexOf("/") + 1));// 截取文件名
			uploadResultVo.setUrlPrefix(ConfigUtil
					.getString("images.url.prefix"));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("icon储存地址更新失败！");
			uploadResultVo.setStatus("err");
			uploadResultVo.setErrMag("icon储存地址更新失败！");
		}
		OutputHelper.writerJSON(uploadResultVo, response);
	}

	
	/**
	 * 更新应用预览图
	 * 
	 * @param id
	 * @param path
	 * @param response
	 */
	@RequestMapping("/updateAdScreenshot.do")
	public void updateAdScreenshot(long id, int sort, String[] paths,
			HttpServletResponse response) {
		List<String> adScreenshots = new ArrayList<String>();
		Collections.addAll(adScreenshots, paths);
		UploadResultVo uploadResultVo = new UploadResultVo();
		uploadResultVo.setSort(sort);
		try {
			uploadResultVo.setPaths(paths);
			uploadResultVo.setUrlPrefix(ConfigUtil
					.getString("images.url.prefix"));

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("preview储存地址更新失败！");
			uploadResultVo.setStatus("err");
			uploadResultVo.setErrMag("preview储存地址更新失败！");
		}
		OutputHelper.writerJSON(uploadResultVo, response);
	}
}
