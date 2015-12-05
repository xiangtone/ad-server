package cn.adwalker.ad.admin.sys.vo;

import java.io.Serializable;
import java.util.Date;

import cn.adwalker.core.util.lang.ObjectUtils;

/**
 * <p>
 * Title: DataMonitorVo
 * </p>
 * <p>
 * Description:定时数据监控Vo
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-8-22
 */
public class DataMonitorVo implements Serializable {
	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 1L;
	/** 主键 */
	private Long id;
	/** 创建时间 */
	private Date create_time;
	/** 状态（状态(0：失败，1：成功)） */
	private Integer status;
	/**
	 * 定时任务类型（0：按天统计----广告，1：按天统计----应用，2：实时统计----应用，3：实时统计----广告，4：开发者收入审核，5：
	 * 渠道数据统计）
	 */
	private Integer task_type;
	/** 发送状态（0：未发送，1：发送） */
	private Integer dispatch_status;

	private String task_name;

	private String dispatch_name;

	private String status_name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTask_type() {
		return task_type;
	}

	public void setTask_type(Integer task_type) {
		this.task_type = task_type;
	}

	public Integer getDispatch_status() {
		return dispatch_status;
	}

	public void setDispatch_status(Integer dispatch_status) {
		this.dispatch_status = dispatch_status;
	}

	public String getTask_name() {
		if (ObjectUtils.isNotEmpty(task_type)) {
			if (task_type == 0) {
				task_name = "按天统计--广告";
			} else if (task_type == 1) {
				task_name = "按天统计--应用";
			} else if (task_type == 2) {
				task_name = "实时统计--应用";
			} else if (task_type == 3) {
				task_name = "实时统计--广告";
			} else if (task_type == 4) {
				task_name = "开发者收入审核";
			} else {
				task_name = "渠道数据统计";
			}
		}
		return task_name;
	}

	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}

	public String getDispatch_name() {
		if (ObjectUtils.isNotEmpty(dispatch_status)) {
			if (dispatch_status == 0) {
				dispatch_name = "未发送";
			} else {
				dispatch_name = "发送";
			}
		}
		return dispatch_name;
	}

	public void setDispatch_name(String dispatch_name) {
		this.dispatch_name = dispatch_name;
	}

	public String getStatus_name() {
		if (ObjectUtils.isNotEmpty(status)) {
			if (status == 0) {
				status_name = "失败";
			} else {
				status_name = "成功";
			}
		}
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}

}
