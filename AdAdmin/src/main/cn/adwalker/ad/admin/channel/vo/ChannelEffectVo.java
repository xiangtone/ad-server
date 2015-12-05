package cn.adwalker.ad.admin.channel.vo;

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
public class ChannelEffectVo {

	/**
	 * 文件名
	 */
	private String file_name;
	// 主键
	private Long id;
	// 渠道数（一个包下有几个渠道）
	private Integer use_count;
	// 状态
	private Integer status;
	// 活动名称
	private String campaign_name;

	/**
	 * 媒体名称
	 */
	private String media_name;

	/**
	 * 类型名称
	 */
	private String type_name;

	// 渠道包号
	private String package_id;
	// 编号
	private String code;
	// 备注
	private String remarks;
	// 效果发生时间
	private String static_date;
	// 确认数=（confirm_num*渠道指数）
	private Integer confirm_amount;
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

	public Integer getUse_count() {
		return use_count;
	}

	public void setUse_count(Integer use_count) {
		this.use_count = use_count;
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

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
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
		if (confirm_amount == null) {
			confirm_amount = 0;
		}
		return confirm_amount;
	}

	public void setConfirm_amount(Integer confirm_amount) {
		this.confirm_amount = confirm_amount;
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
}
