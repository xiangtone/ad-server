/**
 * <p>Title: CallBack.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-7-26
 * @version 1.0
 */
package cn.adwalker.ad.api.app.vo;

import java.util.Date;

import cn.adwalker.core.util.DateUtil;

/**
 * <p>
 * Title: CallBack
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
public class CallBack {

	public CallBack() {
		this.check_time = DateUtil.formatDateTime(new Date());
	}

	private String check_time;

	private String check_msg;

	private String app_id;

	private String status;
	
	private String scale;

	public String getCheck_time() {
		return check_time;
	}

	public void setCheck_time(String check_time) {
		this.check_time = check_time;
	}

	public String getCheck_msg() {
		return check_msg;
	}

	public void setCheck_msg(String check_msg) {
		this.check_msg = check_msg;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
