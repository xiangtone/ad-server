package cn.adwalker.ad.admin.operation.vo;

import cn.adwalker.core.util.lang.ObjectUtils;

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
public class ConfirmNumberChannelVo {

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
	 * 活动id
	 */
	private Long campaign_id;

	// 广告主录入效果数
	private Integer sys_activate;

	/**
	 * 媒体名称
	 */
	private String media_name;

	/**
	 * 媒体id
	 */
	private Long media_id;

	/**
	 * 类型名称
	 */
	private String type_name;

	private Integer media_type;

	// 渠道包号
	private String package_id;
	// 编号
	private String code;
	// 备注
	private String remarks;
	// 效果发生时间
	private String static_date;
	// 广告主录入效果数
	private Integer confirm_num;
	// 确认数=（confirm_num*渠道指数）
	private Integer confirm_amount;
	// 确认数=（confirm_num*渠道指数）
	private Integer confirm_amount_percent;
	// 广告接入单价
	private double price;
	// 渠道指数
	private Double scale;

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

	public Integer getConfirm_num() {
		return confirm_num;
	}

	public void setConfirm_num(Integer confirm_num) {
		this.confirm_num = confirm_num;
	}

	public Integer getUse_count() {
		return use_count;
	}

	public Integer getSys_activate() {
		return sys_activate;
	}

	public void setSys_activate(Integer sys_activate) {
		this.sys_activate = sys_activate;
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
		if (confirm_amount == null) {
			confirm_amount = 0;
		}
		return confirm_amount;
	}

	public void setConfirm_amount(Integer confirm_amount) {
		this.confirm_amount = confirm_amount;
	}

	public Integer getConfirm_amount_percent() {
		if (ObjectUtils.isNotEmpty(scale)
				&& ObjectUtils.isNotEmpty(confirm_num)) {
			confirm_amount_percent = (int) (this.scale * this.confirm_num);
		}
		return confirm_amount_percent;
	}

	public void setConfirm_amount_percent(Integer confirm_amount_percent) {
		this.confirm_amount_percent = confirm_amount_percent;
	}

	public Double getScale() {
		return scale;
	}

	public void setScale(Double scale) {
		this.scale = scale;
	}

	public String getMedia_ch_ph() {
		String s = null;
		if (media_type == 0) {
			s = "平台";
		} else {
			s = "渠道";
		}
		return s;
	}

	public String getremarks() {
		return remarks;
	}

	public void setremarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getMedia_id() {
		return media_id;
	}

	public void setMedia_id(Long media_id) {
		this.media_id = media_id;
	}

	public Long getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}
}
