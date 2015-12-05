/**
 * <p>Title: DevIncomeAuditLog.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author hadoop
 * @date 2013-10-26
 * @version 1.0
 */
package cn.adwalker.model.log.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * <p>
 * Title: DevIncomeAuditLog
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-10-26
 */
public class DevIncomeAuditLog implements Entity {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = -2327707641971259664L;

	private Long id;
	private Long user_id;
	private Date create_time;
	private Long income_id;
	private Integer status;
	private String note;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Long getIncome_id() {
		return income_id;
	}

	public void setIncome_id(Long income_id) {
		this.income_id = income_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
