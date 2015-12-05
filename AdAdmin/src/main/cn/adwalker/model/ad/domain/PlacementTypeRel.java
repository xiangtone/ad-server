package cn.adwalker.model.ad.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * 
 * <p>
 * Title: PlacementTypeRel
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-1-22
 */

public class PlacementTypeRel implements Entity {

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -7773976826031552018L;

	/** 主键 */
	private Long id;

	/** 状态（0：初始化，1：正常） */
	private int status;

	/**
	 * 应用id
	 */
	private Long placement_id;

	/**
	 * 类型id
	 */
	private Long type_id;

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

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getType_id() {
		return type_id;
	}

	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}

	public Long getPlacement_id() {
		return placement_id;
	}

	public void setPlacement_id(Long placement_id) {
		this.placement_id = placement_id;
	}
}
