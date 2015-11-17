/**
 * <p>Title: ResultVo.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-4-30
 * @version 1.0
 */
package cn.adwalker.core.vo;


/**
 * <p>
 * Title: ResultVo
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-4-30
 */
public abstract class ResultVo {
	
	/**
	 * 操作确认
	 */
	public static final Integer SUCCESS_CONFIRM_STATUS=2;

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
