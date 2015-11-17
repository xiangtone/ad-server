/*
 * UploadResultVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 2012-2-28
 */
package cn.adwalker.ad.web.common.vo;

/**
 * 功能描述<br>
 *
 *
 * @author guoyongxiang
 */
public class UploadResultVo {

	/** 结果状态值，成功ok，失败err */
	private String status;
	/** 文件名称 */
	private String fileName;
	/** 文件存储的相对路径 */
	private String path;
	/** 错误信息 */
	private String errMag;
	/** 下载前缀 */
	private String urlPrefix;
	/** 预览图地址集合 */
	private String[] paths;
	/** 预览图位置编号 */
	private Integer sort;
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return the errMag
	 */
	public String getErrMag() {
		return errMag;
	}
	/**
	 * @param errMag the errMag to set
	 */
	public void setErrMag(String errMag) {
		this.errMag = errMag;
	}
	/**
	 * @return the urlPrefix
	 */
	public String getUrlPrefix() {
		return urlPrefix;
	}
	/**
	 * @param urlPrefix the urlPrefix to set
	 */
	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}
	/**
	 * @return the paths
	 */
	public String[] getPaths() {
		return paths;
	}
	/**
	 * @param paths the paths to set
	 */
	public void setPaths(String[] paths) {
		this.paths = paths;
	}
	/**
	 * @return the sort
	 */
	public Integer getSort() {
		return sort;
	}
	/**
	 * @param sort the sort to set
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
}
