package cn.adwalker.ad.control.util;

public class Result {

	private boolean succeed = true; // 是否操作成功
	private Object errorMsg; // 错误信息
	private Integer errorCode; // 错误编码

	public Result() {}

	public boolean isSucceed() {
		return succeed;
	}

	public void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}

	public Object getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(Object errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public void setReturnError(ReturnError returnError) {
		setErrorCode(returnError.getCode());
		setErrorMsg(returnError.getDesc());
	}
}
