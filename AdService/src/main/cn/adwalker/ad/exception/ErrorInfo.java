package cn.adwalker.ad.exception;

import cn.adwalker.ad.bean.Data;



public class ErrorInfo extends Data {

	private static final long serialVersionUID = -1554174609139882356L;
	private String code;// 错误编号
	private String message;// 错误信息


	public ErrorInfo(String code, String message) {
		this.code=code;
		this.message = message;
	}
	
	public ErrorInfo() {
		super();
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
