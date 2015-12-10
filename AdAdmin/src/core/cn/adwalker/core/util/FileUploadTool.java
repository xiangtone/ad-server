/*
 * FileUploadTool.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 06-Dec-2011
 */
package cn.adwalker.core.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import cn.adwalker.core.util.lang.ObjectUtils;

import sun.misc.BASE64Decoder;

/**
 * 功能概述：<br>
 * 上传工具类
 * 
 * @author zhaozengbin
 */
public class FileUploadTool {
	/** 文件路径配置key */
	private static final String FILE_PATH_KEY = "file.path";

	/** 文件路径配置图片 */
	private static final String IMG_PATH_KEY = "img.path";

	/** 文件固定路径 */
	private static String FILE_PATH = "";

	/** 图片固定路径 */
	private static String IMG_PATH = "";

	/** 文件类型 */
	public static String FILE = "file";

	/** 图片固定路径 */
	public static String IMG = "img";

	private static Logger logger = Logger.getLogger(FileUploadTool.class);

	static {
		FILE_PATH = ConfigUtil.getString(FILE_PATH_KEY);
		IMG_PATH = ConfigUtil.getString(IMG_PATH_KEY);
	}

	/**
	 * 上传文件---图片按尺寸切图
	 * 
	 * @param request
	 * @param formName
	 * @return
	 * @throws Exception
	 */
	public static String upload(HttpServletRequest request, String formName,
			String[] dirs, String fileType, String zoomSize, String isCut)
			throws Exception {
		DefaultMultipartHttpServletRequest multipartRequest = (DefaultMultipartHttpServletRequest) request;
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest
				.getFile(formName);
		if (ObjectUtils.isNotEmpty(file) && !file.isEmpty()) {
			String path = null;
			String relativePath = getPathRule(dirs)
					+ getNameRule(file.getOriginalFilename());
			if (fileType.equals(FILE)) {
				path = FILE_PATH + relativePath;
			}
			if (fileType.equals(IMG)) {
				path = IMG_PATH + relativePath;
			}
			File newFile = new File(path);
			if (!newFile.getParentFile().isDirectory()) {
				newFile.getParentFile().mkdirs();
			}
			FileCopyUtils.copy(file.getBytes(), newFile);

			if (ObjectUtils.isNotEmpty(zoomSize) && fileType.equals(IMG)) {
				// ===图片参数组装start===
				int zoomWidth = Integer.parseInt(zoomSize.substring(0,
						zoomSize.indexOf("_")));
				int zoomHeight = Integer.parseInt(zoomSize.substring(zoomSize
						.indexOf("_") + 1));
				String newName = relativePath.substring(0,
						relativePath.lastIndexOf("."))
						+ "_" + zoomWidth + "_" + zoomHeight;// 截取文件名
				newName = newName
						+ relativePath.substring(relativePath.lastIndexOf("."));// 拼接后缀名
				// ===图片参数组装end===
				relativePath = newName;
				// ===调用切图工具生成新图片并返新图片的地址===
				ImageUtil.saveZoomImage(path, IMG_PATH + relativePath,
						zoomWidth, zoomHeight, Integer.parseInt(isCut));// 进行图片按要求裁剪
			}

			if (logger.isDebugEnabled()) {
				logger.debug("上传全路径为:======[" + path + "]======");
				logger.debug("上传相对路径为:======[" + relativePath + "]======");
			}
			return relativePath;
		}
		return null;
	}

	/**
	 * 上传文件---图片按尺寸切图
	 * 
	 * @param request
	 * @param formName
	 * @return
	 * @throws Exception
	 */
	public static String upload(HttpServletRequest request, String formName,
			String[] dirs, String fileType, String zoomSize) throws Exception {
		DefaultMultipartHttpServletRequest multipartRequest = (DefaultMultipartHttpServletRequest) request;
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest
				.getFile(formName);
		if (ObjectUtils.isNotEmpty(file) && !file.isEmpty()) {
			String path = null;
			String relativePath = getPathRule(dirs)
					+ getNameRule(file.getOriginalFilename());
			if (fileType.equals(FILE)) {
				path = FILE_PATH + relativePath;
			}
			if (fileType.equals(IMG)) {
				path = IMG_PATH + relativePath;
			}
			File newFile = new File(path);
			if (!newFile.getParentFile().isDirectory()) {
				newFile.getParentFile().mkdirs();
			}
			FileCopyUtils.copy(file.getBytes(), newFile);

			if (ObjectUtils.isNotEmpty(zoomSize) && fileType.equals(IMG)) {
				// ===图片参数组装start===
				int zoomWidth = Integer.parseInt(zoomSize.substring(0,
						zoomSize.indexOf("_")));
				int zoomHeight = Integer.parseInt(zoomSize.substring(zoomSize
						.indexOf("_") + 1));
				String newName = relativePath.substring(0,
						relativePath.lastIndexOf("."))
						+ "_" + zoomWidth + "_" + zoomHeight;// 截取文件名
				newName = newName
						+ relativePath.substring(relativePath.lastIndexOf("."));// 拼接后缀名
				// ===图片参数组装end===
				relativePath = newName;
				// ===调用切图工具生成新图片并返新图片的地址===
				try {
					ImageUtil.catImg01(path, IMG_PATH + relativePath, zoomWidth,
							zoomHeight);// 进行图片按要求裁剪
				} catch (Exception e) {
					logger.error(e.getLocalizedMessage());
					e.printStackTrace();
				}
			}

			if (logger.isDebugEnabled()) {
				logger.debug("上传全路径为:======[" + path + "]======");
				logger.debug("上传相对路径为:======[" + relativePath + "]======");
			}
			return relativePath;
		}
		return null;
	}

	/**
	 * 上传文件
	 * 
	 * @param request
	 * @param formName
	 * @return
	 * @throws Exception
	 */
	public static String upload(HttpServletRequest request, String formName,
			String[] dirs, String fileType) throws Exception {
		DefaultMultipartHttpServletRequest multipartRequest = (DefaultMultipartHttpServletRequest) request;
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest
				.getFile(formName);
		if (ObjectUtils.isNotEmpty(file) && !file.isEmpty()) {
			String path = null;
			String relativePath = getPathRule(dirs)
					+ getNameRule(file.getOriginalFilename());
			if (fileType.equals(FILE)) {
				path = FILE_PATH + relativePath;
			}
			if (fileType.equals(IMG)) {
				path = IMG_PATH + relativePath;
			}
			File newFile = new File(path);
			if (!newFile.getParentFile().isDirectory()) {
				newFile.getParentFile().mkdirs();
			}
			FileCopyUtils.copy(file.getBytes(), newFile);
			if (logger.isDebugEnabled()) {
				logger.debug("上传全路径为:======[" + path + "]======");
				logger.debug("上传相对路径为:======[" + relativePath + "]======");
			}
			return relativePath;
		}
		return null;
	}

	/**
	 * 根据路径上传文件
	 * 
	 * @param request
	 * @param formName
	 * @return
	 * @throws Exception
	 */
	public static String upload(String filePath, String[] dirs, String fileType)
			throws Exception {
		String path = null;
		String relativePath = null;
		if (filePath != null) {
			File file = new File(filePath);
			relativePath = getPathRule(dirs) + file;
			if (fileType.equals(FILE)) {
				path = FILE_PATH + relativePath;
			}
			if (fileType.equals(IMG)) {
				path = IMG_PATH + relativePath;
			}
			File newFile = new File(path);
			if (!newFile.getParentFile().isDirectory()) {
				newFile.getParentFile().mkdirs();
			}
			FileCopyUtils.copy(file, newFile);
			if (logger.isDebugEnabled()) {
				logger.debug("上传全路径为:======[" + path + "]======");
				logger.debug("上传相对路径为:======[" + relativePath + "]======");
			}
		}
		return relativePath;
	}

	/**
	 * 根据64位编码生成图片
	 * 
	 * @param request
	 * @param formName
	 * @return
	 * @throws Exception
	 */
	public static List<String> writerFile(String[] base64decoders, String[] dirs)
			throws Exception {
		List<String> paths = null;
		if (base64decoders != null) {
			paths = new ArrayList<String>();
			for (String base64decoder : base64decoders) {
				paths.add(writerFile(base64decoder, dirs));
			}
		}
		return paths;
	}

	/**
	 * 根据64位编码生成图片
	 * 
	 * @param base64decoder
	 * @param dirs
	 *            目录数组 [email,时间戳];
	 * @return
	 * @throws Exception
	 */
	public static String writerFile(String base64decoder, String[] dirs)
			throws Exception {
		if (base64decoder != null) {
			// 对字节数组字符串进行Base64解码并生成图片
			base64decoder = base64decoder.substring(base64decoder
					.indexOf("base64,") + 7);
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				// Base64解码
				byte[] b = decoder.decodeBuffer(base64decoder);
				for (int i = 0; i < b.length; ++i) {
					if (b[i] < 0) {
						// 调整异常数据
						b[i] += 256;
					}
				}
				// 生成jpeg图片
				String relativePath = getPathRule(dirs)
						+ getNameRule("template.jpg");

				String targetPath = IMG_PATH + relativePath;
				if (!new File(targetPath).getParentFile().isDirectory()) {
					new File(targetPath).getParentFile().mkdirs();
				}
				OutputStream out = new FileOutputStream(targetPath);
				out.write(b);
				out.flush();
				out.close();
				if (logger.isDebugEnabled()) {
					logger.debug("上传全路径为:======[" + targetPath + "]======");
					logger.debug("上传相对路径为:======[" + relativePath + "]======");
				}
				return relativePath;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	/**
	 * 创建路径规则
	 * 
	 * @return
	 */
	private static String getPathRule(String[] dirs) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currentTime = sdf.format(new Date());
		String splitTime[] = currentTime.split("-");
		StringBuffer path = new StringBuffer();
		String separator="/";
		path.append(splitTime[0]);
		path.append(separator);
		path.append(splitTime[1]);
//		path.append(File.separator);
		path.append(separator);
		path.append(splitTime[2]);
		path.append(separator);
		for (String dir : dirs) {
			path.append(dir);
			path.append(separator);
		}
		return path.toString();
	}

	/**
	 * 创建名称规则
	 * 
	 * @return
	 */
	private static String getNameRule(String sourceName) {
		String targetName = System.currentTimeMillis()
				+ sourceName.substring(sourceName.lastIndexOf("."));
		return targetName;
	}

	/**
	 * 获取上传路径
	 * 
	 * @param dirs
	 * @param fileType
	 * @return
	 */
	public static String getUploadPath(String[] dirs, String fileType) {

		String path = null;
		String relativePath = getPathRule(dirs);
		if (fileType.equals(FILE)) {
			path = FILE_PATH + relativePath;
		}
		if (fileType.equals(IMG)) {
			path = IMG_PATH + relativePath;
		}
		File newFile = new File(path);
		if (!newFile.getParentFile().isDirectory()) {
			newFile.getParentFile().mkdirs();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("上传全路径为:======[" + path + "]======");
			logger.debug("上传相对路径为:======[" + relativePath + "]======");
		}
		return relativePath;

	}

}
