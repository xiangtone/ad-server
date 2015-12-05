/**
 * <p>Title: PackageActivate.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author hadoop
 * @date 2013-12-11
 * @version 1.0
 */
package cn.adwalker.model.operation.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * <p>
 * Title: PackageActivate
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-12-11
 */
public class PackageActivate implements Entity{

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -51439472768166945L;
	private Long id;
	private Date create_time;
	private Long package_id;
	private String static_date;
	private Integer sys_activate;
	private Integer confirm_amount;

	private Integer status;

	/**
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return create_time
	 */
	public Date getCreate_time() {
		return create_time;
	}

	/**
	 * @param create_time the create_time to set
	 */
	
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	/**
	 * @return package_id
	 */
	public Long getPackage_id() {
		return package_id;
	}

	/**
	 * @param package_id the package_id to set
	 */
	
	public void setPackage_id(Long package_id) {
		this.package_id = package_id;
	}

	/**
	 * @return static_date
	 */
	public String getStatic_date() {
		return static_date;
	}

	/**
	 * @param static_date the static_date to set
	 */
	
	public void setStatic_date(String static_date) {
		this.static_date = static_date;
	}

	/**
	 * @return sys_activate
	 */
	public Integer getSys_activate() {
		return sys_activate;
	}

	/**
	 * @param sys_activate the sys_activate to set
	 */
	
	public void setSys_activate(Integer sys_activate) {
		this.sys_activate = sys_activate;
	}

	/**
	 * @return confirm_amount
	 */
	public Integer getConfirm_amount() {
		return confirm_amount;
	}

	/**
	 * @param confirm_amount the confirm_amount to set
	 */
	
	public void setConfirm_amount(Integer confirm_amount) {
		this.confirm_amount = confirm_amount;
	}

	/**
	 * @return status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

}
