/**
 * <p>Title: Campagin.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2013-4-11
 * @version 1.0
 */
package cn.adwalker.ad.dao.domain;

import cn.adwalker.ad.bean.Data;
import cn.adwalker.ad.cache.CacheAble;

/**
 * <p>
 * Title: Campagin
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author www.adwalker.cn
 * @date 2013-4-11
 */
public class Placement extends Data implements CacheAble {
	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = -9189358294116404842L;
	private long id;
	private String os;
	private String campaign_desc;//活动描述 jief
	private String slogan;//广告语
	private String icon_url;
	private Integer  priority;//是否优先
	private Integer category_id;// 类别
	private Double star_level;
	private String config_id;

	private Integer new_app;	//是否是新应用 1:是,0:否
	
	private String keyword;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getCampaign_desc() {
		return campaign_desc;
	}

	public void setCampaign_desc(String campaign_desc) {
		this.campaign_desc = campaign_desc;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getIcon_url() {
		return icon_url;
	}

	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	public Double getStar_level() {
		return star_level;
	}

	public void setStar_level(Double star_level) {
		this.star_level = star_level;
	}

	public String getConfig_id() {
		return config_id;
	}

	public void setConfig_id(String config_id) {
		this.config_id = config_id;
	}

	public Integer getNew_app() {
		return new_app;
	}

	public void setNew_app(Integer new_app) {
		this.new_app = new_app;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
