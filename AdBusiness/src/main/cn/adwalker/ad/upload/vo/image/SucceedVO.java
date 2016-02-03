package cn.adwalker.ad.upload.vo.image;

import java.io.IOException;

import cn.adwalker.ad.upload.util.JacksonMapper;

public class SucceedVO {

	private String fileName;// 原文件名称
	private String newFileName;// 新文件名称
	private String path;// 相对路径
	private long size;
	private String contentType;
	private int width;// 原图宽度
	private int height;// 原图高度
	private int zoomWidth;// 缩放宽度
	private int zoomHeight;// 缩放高度

	public SucceedVO() {

	}

	public SucceedVO(String fileName, String newFileName, long size, String contentType, int width, int height, int zoomWidth, int zoomHeight, String path) {
		this.fileName = fileName;
		this.newFileName = newFileName;
		this.size = size;
		this.contentType = contentType;
		this.width = width;
		this.height = height;
		this.zoomWidth = zoomWidth;
		this.zoomHeight = zoomHeight;
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getNewFileName() {
		return newFileName;
	}

	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getZoomWidth() {
		return zoomWidth;
	}

	public void setZoomWidth(int zoomWidth) {
		this.zoomWidth = zoomWidth;
	}

	public int getZoomHeight() {
		return zoomHeight;
	}

	public void setZoomHeight(int zoomHeight) {
		this.zoomHeight = zoomHeight;
	}

	public String toString() {
		String json = null;
		try {
			json = JacksonMapper.objectToJsonString(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

}
