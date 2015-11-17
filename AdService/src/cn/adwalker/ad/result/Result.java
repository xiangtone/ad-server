package cn.adwalker.ad.result;

import java.io.Serializable;

public class Result implements Serializable {
	
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -6525344676590192435L;
	public Result(String message, boolean success) {
		super();
		this.message = message;
		this.success = success;
	}
	private String message;
	private boolean success;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	

}
