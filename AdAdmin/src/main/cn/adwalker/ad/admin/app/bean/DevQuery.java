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
public class DevQuery {

	private String idOrEmail;

	/**
	 * 开发者姓名
	 */
	private String dev_name;

	private Integer status;

	private String logonStartTime;

	private String logonEndTime;
	
	
	private String resource;
	

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getLogonStartTime() {
		return logonStartTime;
	}

	public void setLogonStartTime(String logonStartTime) {
		this.logonStartTime = logonStartTime;
	}

	public String getLogonEndTime() {
		return logonEndTime;
	}

	public void setLogonEndTime(String logonEndTime) {
		this.logonEndTime = logonEndTime;
	}

	public Long getDev_id() {
		Long l = 0L;
		if (!StringUtils.isEmpty(idOrEmail) && NumberUtils.isNumber(idOrEmail)) {
			l = Long.valueOf(idOrEmail);
		}

		return l;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDev_name() {
		return dev_name;
	}

	public String getDev_email() {
		String s = null;
		if (!StringUtils.isEmpty(idOrEmail)) {
			s = idOrEmail;
		}
		return s;
	}

	public void setDev_name(String dev_name) {
		this.dev_name = dev_name;
	}

	public String getIdOrEmail() {
		return idOrEmail;
	}

	public void setIdOrEmail(String idOrEmail) {
		this.idOrEmail = idOrEmail;
	}
}
