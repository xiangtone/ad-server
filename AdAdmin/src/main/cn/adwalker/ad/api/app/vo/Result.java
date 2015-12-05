/**
 * <p>Title: Result.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-7-26
 * @version 1.0
 */
package cn.adwalker.ad.api.app.vo;

import java.io.Serializable;
import java.util.Date;

import cn.adwalker.core.util.DateUtil;

/**
 * <p>
 * Title: Result
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-7-26
 */
public class Result implements Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 7848697957416611747L;
	
	
	public Result() {
		super();
		this.time = DateUtil.formatDateTime(new Date());
	}

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param msg
	 * @param error
	 */
	public Result(boolean msg) {
		super();
		this.msg = msg;
		this.time = DateUtil.formatDateTime(new Date());
	}

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param msg
	 * @param error
	 */
	public Result(boolean msg, String error) {
		super();
		this.msg = msg;
		this.error = error;
		this.time = DateUtil.formatDateTime(new Date());
	}

	private boolean msg;

	private String error;

	private String time;

	public boolean isMsg() {
		return msg;
	}

	public void setMsg(boolean msg) {
		this.msg = msg;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
