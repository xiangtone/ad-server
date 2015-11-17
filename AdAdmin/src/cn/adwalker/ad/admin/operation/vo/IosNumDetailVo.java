package cn.adwalker.ad.admin.operation.vo;


/**
* <p>Title: IosNumDetailVo</p>
* <p>Description:ios效果数明细Vo</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-11-14
 */
public class IosNumDetailVo {

	// 主键
	private Long id;
	// 状态
	private Integer status;
	// 活动名称
	private String campaign_name;
	 //媒体名称
	private String media_name;
	// 类型名称
	private String type_name;

	private Integer media_type;
	
	private String media_ch_ph;
	// 备注
	private String remarks;
	// 效果发生时间
	private String static_date;
	// 广告主录入效果数
	private Integer confirm_num;
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
		if (confirm_amount == null) {
			confirm_amount = 0;
		}
		return confirm_amount;
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

	public String getremarks() {
		return remarks;
	}
	
	public void setremarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
