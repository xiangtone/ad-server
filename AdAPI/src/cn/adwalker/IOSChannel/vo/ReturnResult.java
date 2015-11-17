package cn.adwalker.IOSChannel.vo;

public class ReturnResult {

	/***返回内容**/
	private String message;
	
	/***是否成功**/
	private String success;

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public ReturnResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
