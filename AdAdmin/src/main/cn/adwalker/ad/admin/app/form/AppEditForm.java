/**
* <p>Title: AppAddForm.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author kingdom
* @date 2013-5-26
* @version 1.0
*/
package cn.adwalker.ad.admin.app.form;

/**
 * <p>Title: AppAddForm</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    cuidd
 * @date       2013-5-26
 */
public class AppEditForm {
	

	/** 开发者ID */
	private Long dev_id;
	

	/** 应用名称 */
	private String name;
	
	/** 关键字 */
	private String keyword;
	
	/** 应用描述 */
	private String longDesc;
	
	/** 分类ID */
	private Long categoryId;
	
	/** 操作系统 */
	private String os;
	
	/** 是否单独投放 **/
	private String placement;
	
	/** 主键 */
	private Long id;
	
	/**
	 * 
	 */
	private Long app_manager_id;
	
	/**
	 * 是否获取经纬度信息
	 */
	private Integer is_coordinate;
	/**
	 * 媒体评价
	 */
	private Integer scale;


	public Long getDev_id() {
		return dev_id;
	}

	public void setDev_id(Long dev_id) {
		this.dev_id = dev_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getPlacement() {
		return placement;
	}

	public void setPlacement(String placement) {
		this.placement = placement;
	}

	public Integer getScale() {
		return scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIs_coordinate() {
		return is_coordinate;
	}

	public void setIs_coordinate(Integer is_coordinate) {
		this.is_coordinate = is_coordinate;
	}

	public Long getApp_manager_id() {
		return app_manager_id;
	}

	public void setApp_manager_id(Long app_manager_id) {
		this.app_manager_id = app_manager_id;
	}
}
