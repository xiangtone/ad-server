/**
 * <p>Title: PlacementEditVo.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-5-15
 * @version 1.0
 */
package cn.adwalker.ad.admin.ad.vo;

/**
 * <p>
 * Title: PlacementEditVo
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-5-15
 */
public class PlacementEditVo {
	private Long id;

	// 投放名称
	private String name;

	// 星级
	private Double star_level;

	// 状态
	private Integer status;

	// 平台类型
	private String os;

	/**
	 * 分类id
	 */
	private Long category_id;

	/**
	 * 广告语
	 */
	private String slogan;

	/**
	 * 关键字
	 */
	private String keyword;

	/**
	 * 活动介绍
	 */
	private String campaign_desc;

	/**
	 * 是否热门推荐
	 */
	private Integer popular_app;
	/**
	 * 是否优先推荐
	 */
	private Integer priority;
	/**
	 * 是否是新应用
	 */
	private Integer new_app;
	/**
	 * 是否是链接
	 */
	private Integer is_url_params;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getStar_level() {
		return star_level;
	}

	public void setStar_level(Double star_level) {
		this.star_level = star_level;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getCampaign_desc() {
		return campaign_desc;
	}

	public void setCampaign_desc(String campaign_desc) {
		this.campaign_desc = campaign_desc;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getNew_app() {
		return new_app;
	}

	public void setNew_app(Integer new_app) {
		this.new_app = new_app;
	}

	public Integer getPopular_app() {
		return popular_app;
	}

	public void setPopular_app(Integer popular_app) {
		this.popular_app = popular_app;
	}

	public Integer getIs_url_params() {
		return is_url_params;
	}

	public void setIs_url_params(Integer is_url_params) {
		this.is_url_params = is_url_params;
	}
}
