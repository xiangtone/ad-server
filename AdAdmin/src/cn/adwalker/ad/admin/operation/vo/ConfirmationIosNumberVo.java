package cn.adwalker.ad.admin.operation.vo;

/**
 * <p>
 * Title: ConfirmationIosNumberVo
 * </p>
 * <p>
 * Description:IOS日数据录入Vo
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-11-8
 */
public class ConfirmationIosNumberVo {

	// 主键
	private Long id;
	// 状态
	private Integer status;
	// 活动id
	private String campaign_id;
	// 活动名称
	private String campaign_name;
	// 渠道
	private String medias;
	// 广告主录入效果数
	private Integer sys_activate;
	// 效果数
	private Integer activate;
	// 投放媒体个数
	private Integer media_num;
	// 点击数
	private Integer click;
	// 编号
	private String code;
	// 效果发生时间
	private String static_date;
	// 广告主录入效果数
	private Integer confirm_amount;
	// 广告接入单价
	private Double price;

	public boolean getFlag() {
		boolean b = false;
		if (media_num == 1) {
			b = true;
		}
		return b;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(String campaign_id) {
		this.campaign_id = campaign_id;
	}

	public String getCampaign_name() {
		return campaign_name;
	}

	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}

	public String getMedias() {
		return medias;
	}

	public void setMedias(String medias) {
		this.medias = medias;
	}

	public Integer getSys_activate() {
		return sys_activate;
	}

	public void setSys_activate(Integer sys_activate) {
		this.sys_activate = sys_activate;
	}

	public Integer getMedia_num() {
		return media_num;
	}

	public void setMedia_num(Integer media_num) {
		this.media_num = media_num;
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

	public Integer getConfirm_amount() {
		return confirm_amount;
	}

	public void setConfirm_amount(Integer confirm_amount) {
		this.confirm_amount = confirm_amount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getClick() {
		return click;
	}

	public void setClick(Integer click) {
		this.click = click;
	}

	public Integer getActivate() {
		return activate;
	}

	public void setActivate(Integer activate) {
		this.activate = activate;
	}

}
