package cn.adwalker.ad.web.common.vo;

public abstract class ResultVo {

	/**
	 * 操作确认
	 */
	public static final Integer SUCCESS_CONFIRM_STATUS = 2;

	public static final Integer STATUS_ERROR_REDIRECT = -1;

	/**
	 * 状态-1失败；1：成功；2;成功后 跳转
	 */
	protected Integer status;

	/**
	 * 失败提示信息
	 */
	protected String error;

	/**
	 * 成功后返回数据
	 */
	protected Object data;

	/**
	 * 跳转页面地址
	 */
	protected String redirect_url;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getRedirect_url() {
		return redirect_url;
	}

	public void setRedirect_url(String redirect_url) {
		this.redirect_url = redirect_url;
	}

}
