package cn.adwalker.model.api.domain;


import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * 
 * <p>
 * Title: ApiLog
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-8-1
 */
public class ApiLog implements Entity {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 522063683725734194L;

	public ApiLog() {

	}

	public ApiLog(String data, Integer log_type) {
		super();
		this.log_data = data;
		this.log_type = log_type;
		this.create_time = new Date();

	}

	private Long id;

	private String log_data;

	private Integer log_type;

	private Date create_time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLog_data() {
		return log_data;
	}

	public void setLog_data(String log_data) {
		this.log_data = log_data;
	}

	public Integer getLog_type() {
		return log_type;
	}

	public void setLog_type(Integer log_type) {
		this.log_type = log_type;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}
