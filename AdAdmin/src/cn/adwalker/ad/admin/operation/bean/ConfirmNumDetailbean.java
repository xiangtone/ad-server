package cn.adwalker.ad.admin.operation.bean;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * 
 * <p>
 * Title: ConfirmNumberbean
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-7-16
 */
public class ConfirmNumDetailbean {
	// 主键
	private Long id;

	// 渠道包号
	private String package_id;
	// 活动名称
	private String campaign;

	// 效果发生时间
	private String static_start_date;
	// 效果结束时间
	private String static_end_date;
	// 效果时间
	private String create_time;
	// 媒体名称
	private String media_name;
	// 平台类型
	private Integer media_type;
	// 对账状态
	private Integer balance_status;

	public String getCampaign_name() {
		String name = null;
		if (!StringUtils.isEmpty(campaign)) {
			name = campaign.trim();
		}
		return name;
	}

	

	/**
	 * @return campaign_id
	 */
	public Long getCampaign_id() {
		Long l = 0l;
		if (!StringUtils.isEmpty(campaign) && NumberUtils.isNumber(campaign)) {
			l = Long.valueOf(campaign);
		}
		return l;
	}

	/**
	 * @return campaign
	 */
	public String getCampaign() {
		return campaign;
	}



	/**
	 * @param campaign the campaign to set
	 */
	
	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPackage_id() {
		return package_id;
	}

	public void setPackage_id(String package_id) {
		this.package_id = package_id;
	}

	public String getStatic_start_date() {
		return static_start_date;
	}

	public void setStatic_start_date(String static_start_date) {
		this.static_start_date = static_start_date;
	}

	public String getStatic_end_date() {
		return static_end_date;
	}

	public void setStatic_end_date(String static_end_date) {
		this.static_end_date = static_end_date;
	}

	public String getMedia_name() {
		return media_name;
	}

	public void setMedia_name(String media_name) {
		this.media_name = media_name;
	}

	public Integer getMedia_type() {
		return media_type;
	}

	public void setMedia_type(Integer media_type) {
		this.media_type = media_type;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public Integer getBalance_status() {
		return balance_status;
	}

	public void setBalance_status(Integer balance_status) {
		this.balance_status = balance_status;
	}
}
