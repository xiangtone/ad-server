package cn.adwalker.ad.upload.vo.image;

import java.io.IOException;

import cn.adwalker.ad.upload.util.JacksonMapper;

public class FallVO {

	private String fileName;// 原文件名称
	private String errorMsg;// 错误信息

	public FallVO() {

	}

	public FallVO(String fileName, String errorMsg) {
		this.fileName = fileName;
		this.errorMsg = errorMsg;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
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
