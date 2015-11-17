package cn.adwalker.ad.admin.operation.vo;

public class ConfirmationNumberVo {

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

	private String campaign_id;

	/**
	 * 结算id,标识是否结算
	 */
	private Long balance_id;

	/**
	 * 渠道
	 */
	private String medias;

	// 广告主录入效果数
	private Integer sys_activate;

	/**
	 * 投放媒体个数
	 */
	private Integer media_num;

	// 渠道包号
	private String package_id;
	// 包备注
	private String remarks;
	// 编号
	private String code;
	// 效果发生时间
	private String static_date;
	// 广告主录入效果数
	private Integer confirm_amount;
	// 广告接入单价
	private double price;

	/**
	 * 
	 * <p>
	 * Title: getFlag
	 * </p>
	 * <p>
	 * Description:是否可以提交
	 * </p>
	 * 
	 * @return
	 * @author cuidd
	 * @date 2013-7-30
	 * @return boolean
	 * @version 1.0
	 */
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

	public Integer getConfirm_amount() {
		return confirm_amount;
	}

	public void setConfirm_amount(Integer confirm_amount) {
		this.confirm_amount = confirm_amount;
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

	public Integer getMedia_num() {
		return media_num;
	}

	public void setMedia_num(Integer media_num) {
		this.media_num = media_num;
	}

	public String getremarks() {
		return remarks;
	}

	public void setremarks(String remarks) {
		this.remarks = remarks;
	}

	public String getMedias() {
		return medias;
	}

	public void setMedias(String medias) {
		this.medias = medias;
	}

	public Long getBalance_id() {
		return balance_id;
	}

	public void setBalance_id(Long balance_id) {
		this.balance_id = balance_id;
	}

	public String getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(String campaign_id) {
		this.campaign_id = campaign_id;
	}

}
