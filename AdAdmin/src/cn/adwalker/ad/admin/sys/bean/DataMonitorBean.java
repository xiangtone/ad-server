package cn.adwalker.ad.admin.sys.bean;

import java.util.Date;

/**
* <p>Title: DataMonitorBean</p>
* <p>Description:定时数据监控bean</p>
* <p>Company: adwalker</p> 
* @author   lichuang 
* @date       2013-8-22
 */
public class DataMonitorBean {
	
	private Integer task;
	
	private Integer status;
	
	private Date create_time;
	
	private String create_time_str;

	

	public Integer getTask() {
		return task;
	}

	public void setTask(Integer task) {
		this.task = task;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getCreate_time_str() {
		return create_time_str;
	}

	public void setCreate_time_str(String create_time_str) {
		this.create_time_str = create_time_str;
	}
	
}