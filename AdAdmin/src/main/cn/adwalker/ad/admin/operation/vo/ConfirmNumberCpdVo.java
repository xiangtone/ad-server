package cn.adwalker.ad.admin.operation.vo;

import cn.adwalker.core.util.lang.ObjectUtils;

/**
 * <p>
 * Title: ConfirmNumberCpdVo
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-8-26
 */
public class ConfirmNumberCpdVo {
	// 主键
	private Long id;
	// 状态
	private Integer status;
	// 活动名称
	private String campaign_name;
	// 活动ID
	private Long campaign_id;
	// 媒体ID
	private Long media_id;
	// 媒体名称
	private String media_name;
	// 类型名称
	private String type_name;
	// 效果发生时间
	private String static_date;
	// 媒体录入钱
	private double confirm_money;

	/**
	 * 
	 */
	private Integer channel_effect;

	// 单价
	private Double price;
	// 广告主录入效果数
	private Integer confirm_num;
	// 预计确认收入
	private double conf_money;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCampaign_name() {
		return campaign_name;
	}

	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}

	public String getMedia_name() {
		return media_name;
	}

	public void setMedia_name(String media_name) {
		this.media_name = media_name;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getStatic_date() {
		return static_date;
	}

	public void setStatic_date(String static_date) {
		this.static_date = static_date;
	}

	public double getConfirm_money() {
		return confirm_money;
	}

	public void setConfirm_money(double confirm_money) {
		this.confirm_money = confirm_money;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}

	public Long getMedia_id() {
		return media_id;
	}

	public void setMedia_id(Long media_id) {
		this.media_id = media_id;
	}

	/**
	 * @return price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	
	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getConfirm_num() {
		return confirm_num;
	}

	public void setConfirm_num(Integer confirm_num) {
		this.confirm_num = confirm_num;
	}

	public double getConf_money() {
		if (ObjectUtils.isNotEmpty(price)
				&& ObjectUtils.isNotEmpty(confirm_num)) {
			conf_money = price * confirm_num;
		}
		return conf_money;
	}

	public void setConf_money(double conf_money) {
		this.conf_money = conf_money;
	}

	public Integer getChannel_effect() {
		return channel_effect;
	}

	public void setChannel_effect(Integer channel_effect) {
		this.channel_effect = channel_effect;
	}
}
