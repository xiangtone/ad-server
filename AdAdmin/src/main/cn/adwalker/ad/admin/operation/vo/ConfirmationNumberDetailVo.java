package cn.adwalker.ad.admin.operation.vo;


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
public class ConfirmationNumberDetailVo {

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
	
	// 活动名称
	private Long campaign_id;

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
	// 效果发生时间
	private String static_date;
	// 广告主录入效果数
	private Integer confirm_num;
	// 确认数=（confirm_num*渠道指数）
	private Integer confirm_amount;
	// 广告接入单价
	private Double price;
	// 投放单价
	private Double out_price;
	
	private Long balance_id;

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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getOut_price() {
		return out_price;
	}

	public void setOut_price(Double out_price) {
		this.out_price = out_price;
	}

	public Long getBalance_id() {
		return balance_id;
	}

	public void setBalance_id(Long balance_id) {
		this.balance_id = balance_id;
	}

	public Long getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}
	
	
}
