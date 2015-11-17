package cn.adwalker.ad.util;

/**
 * 功能概述：<br>
 *
 *APK应用程序解析结果
 *
 * @author jiaozhichao
 */
public class ApkParserResult {
	/** 应用支持的屏幕-宽 */
	private String width;

	/** 应用支持的屏幕-高 */
	private String height;

	/** 应用版本名称 */
	private String versionName;

	/** 应用版本编码 */
	private String versionCode;

	/** 应用包名 */
	private String packageName;
	
	/** 系统版本 */
	private String sdkVer;
	
	/** 资源大小  */
	private Long size;
	
	/** 文件名称  */
	private String fileName;
	
	/**  文件存放路径 */
	private String path;

	/**
	 * @return Returns the width.
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            The width to set.
	 */
	public void setWidth(String width) {
		this.width = width;
	}

	/**
	 * @return Returns the height.
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            The height to set.
	 */
	public void setHeight(String height) {
		this.height = height;
	}

	/**
	 * @return Returns the versionName.
	 */
	public String getVersionName() {
		return versionName;
	}

	/**
	 * @param versionName
	 *            The versionName to set.
	 */
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	/**
	 * @return Returns the packageName.
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @param packageName
	 *            The packageName to set.
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * @return Returns the versionCode.
	 */
	public String getVersionCode() {
		return versionCode;
	}

	/**
	 * @param versionCode
	 *            The versionCode to set.
	 */
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	/**
	 * @return the sdkVer
	 */
	public String getSdkVer() {
		return sdkVer;
	}

	/**
	 * @param sdkVer the sdkVer to set
	 */
	public void setSdkVer(String sdkVer) {
		this.sdkVer = sdkVer;
	}

	/**
	 * @return Returns the fileName.
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName The fileName to set.
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return Returns the path.
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path The path to set.
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return Returns the size.
	 */
	public Long getSize() {
		return size;
	}

	/**
	 * @param size The size to set.
	 */
	public void setSize(Long size) {
		this.size = size;
	}
	
}
