package cn.adwalker.ad.admin.sales.bean;

import java.io.Serializable;


/**
 * <p>
 * Title: AdByPlacementBean
 * </p>
 * <p>
 * Description:广告管理bean
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-4
 */
public class CampaignSalesmanBean implements Serializable {

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 8069583883442397569L;


	/**
	 * 广告id
	 */
	private Long id;
	
	
	// 媒体名称
	private String name;
	
	// 广告形式
	private Integer area_type;
	
	// 投放状态
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return area_type
	 */
	public Integer getArea_type() {
		return area_type;
	}

	/**
	 * @param area_type the area_type to set
	 */
	
	public void setArea_type(Integer area_type) {
		this.area_type = area_type;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	
	public void setName(String name) {
		this.name = name;
	}
}
