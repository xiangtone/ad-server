package cn.adwalker.model.app.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * 
 * <p>
 * Title: PlacementTypeRel
 * </p>
 * <p>
 * Description:TOD
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-1-22
 */

public class ClusterAdConfig implements Entity {

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -1798887056290766844L;

	/** 主键 */
	private Long id;

	/** 状态（0：初始化，1：正常） */
	private int status;

	/**
	 * 应用id
	 */
	private Long app_id;

	/**
	 * 类型id
	 */
	private String res_code;

	

	/** 创建时间 */
	private Date create_time;


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getApp_id() {
		return app_id;
	}

	public void setApp_id(Long app_id) {
		this.app_id = app_id;
	}

	public String getRes_code() {
		return res_code;
	}

	public void setRes_code(String res_code) {
		this.res_code = res_code;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}
