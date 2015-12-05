package cn.adwalker.upload.vo.file;

import java.io.IOException;

import cn.adwalker.core.util.JacksonMapper;

public class SucceedVO {

	private String fileName;// 原文件名称
	private String newFileName;// 新文件名称
	private long size;
	private String contentType;
	private String path;

	public SucceedVO() {

	}

	public SucceedVO(String fileName, String newFileName, long size, String contentType, String path) {
		this.fileName = fileName;
		this.newFileName = newFileName;
		this.size = size;
		this.contentType = contentType;
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
