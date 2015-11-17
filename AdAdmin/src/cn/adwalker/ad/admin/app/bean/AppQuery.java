/**
 * <p>Title: AppQuery.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-1-25
 * @version 1.0
 */
package cn.adwalker.ad.admin.app.bean;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * <p>
 * Title: AppQuery
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-1-25
 */
public class AppQuery implements Serializable{

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 4989966504593903902L;

	private String app;
	
	private Long app_manager_id;

	private String dev;

	private Integer app_status;
	
	private Integer app_res;

	private Integer status;

	private String startTime;

	private String endTime;

	/** 平台 */
	private String os;

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getApp_id() {
		long l = 0L;
		if (!StringUtils.isEmpty(app) && NumberUtils.isNumber(app)) {
			l = Long.valueOf(app);
		}
		return l;
	}

	public String getApp_name() {
		String s = "";
		if (!StringUtils.isEmpty(app)) {
			s = app;
		}
		return s;
	}

	public String getDev_email() {
		String s = "";
		if (!StringUtils.isEmpty(dev)) {
			s = dev;
		}
		return s;
	}

	public Long getDev_id() {
		long l = 0L;
		if (!StringUtils.isEmpty(dev) && NumberUtils.isNumber(dev)) {
			l = Long.valueOf(dev);
		}
		return l;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getDev() {
		return dev;
	}

	public void setDev(String dev) {
		this.dev = dev;
	}

	public Integer getApp_status() {
		return app_status;
	}

	public void setApp_status(Integer app_status) {
		this.app_status = app_status;
	}

	public Integer getApp_res() {
		return app_res;
	}

	public void setApp_res(Integer app_res) {
		this.app_res = app_res;
	}

	public Long getApp_manager_id() {
		return app_manager_id;
	}

	public void setApp_manager_id(Long app_manager_id) {
		this.app_manager_id = app_manager_id;
	}
}
