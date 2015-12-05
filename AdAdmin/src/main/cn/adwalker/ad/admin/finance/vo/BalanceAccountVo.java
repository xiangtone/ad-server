package cn.adwalker.ad.admin.finance.vo;

import java.math.BigDecimal;

/**
 * 
 * <p>
 * Title: ChannelEffectVo
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-7-30
 */
public class BalanceAccountVo {

	// 主键
	private Long id;
	
	// 效果发生时间
	private String static_date;
	
	private Long campaign_id;
	
	// 活动名称
	private String campaign_name;
	
	
	private BigDecimal sys_cost;
	
	private Integer confirm_amount;

	
	// 状态
	private Integer status;

	// 广告主录入效果数
	private Integer sys_activate;

	/**
	 * 媒体名称
	 */
	private String media_name;

	/**
	 * 类型名称
	 */
	private String type_name;

	private Integer media_type;


	private String media_ch_ph;
	// 渠道包号
	private String package_id;
	// 编号
	private String code;

	// 备注
	private String remarks;

	// 广告接入单价
	private double price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCampaign_name() {
		return campaign_name;
	}

	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}

	public String getPackage_id() {
		return package_id;
	}

	public void setPackage_id(String package_id) {
		this.package_id = package_id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatic_date() {
		return static_date;
	}

	public void setStatic_date(String static_date) {
		this.static_date = static_date;
	}

 

	public Integer getSys_activate() {
		return sys_activate;
	}

	public void setSys_activate(Integer sys_activate) {
		this.sys_activate = sys_activate;
	}

	 

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getMedia_type() {
		return media_type;
	}

	public void setMedia_type(Integer media_type) {
		this.media_type = media_type;
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

	public Integer getConfirm_amount() {
		return confirm_amount;
	}

	/**
	 * @return remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setConfirm_amount(Integer confirm_amount) {
		this.confirm_amount = confirm_amount;
	}

	public String getMedia_ch_ph() {
		if (media_type == 0) {
			media_ch_ph = "平台";
		} else {
			media_ch_ph = "渠道";
		}
		return media_ch_ph;
	}

	public void setMedia_ch_ph(String media_ch_ph) {
		this.media_ch_ph = media_ch_ph;
	}

	
	public Long getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}

	public BigDecimal getSys_cost() {
		return sys_cost;
	}

	public void setSys_cost(BigDecimal sys_cost) {
		this.sys_cost = sys_cost;
	}

}
