package cn.adwalker.model.operation.domain;

/**
 * <p>
 * Title: IncomeConfirm
 * </p>
 * <p>
 * Description:IOS确认数
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-24
 */
public class IncomeConfirm {
	/** 主键id */
	private Long id;
	/** 广告id */
	private Long ad_id;
	/** 渠道id */
	private Long channel_id;
	/** 广告接入价 */
	private Double price;
	/** 效果发生时间 */
	private String stat_date;
	/** mac */
	private String income_mac;
	/** 激活数 */
	private Integer income_amount;
	/** 类型 */
	private Integer ch_plf_type;
	/** 收入 */
	private Double income_money;
	/**  */
	private String os;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAd_id() {
		return ad_id;
	}

	public void setAd_id(Long ad_id) {
		this.ad_id = ad_id;
	}

	public void setIncome_money(Double income_money) {
		this.income_money = income_money;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getStat_date() {
		return stat_date;
	}

	public void setStat_date(String stat_date) {
		this.stat_date = stat_date;
	}

	public String getIncome_mac() {
		return income_mac;
	}

	public void setIncome_mac(String income_mac) {
		this.income_mac = income_mac;
	}

	public Integer getIncome_amount() {
		return income_amount;
	}

	public void setIncome_amount(Integer income_amount) {
		this.income_amount = income_amount;
	}

	public Integer getCh_plf_type() {
		return ch_plf_type;
	}

	public void setCh_plf_type(Integer ch_plf_type) {
		this.ch_plf_type = ch_plf_type;
	}

	public double getIncome_money() {
		return income_money;
	}

	public void setIncome_money(double income_money) {
		this.income_money = income_money;
	}

	public String getOs() {
		os = "ios";
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Long getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(Long channel_id) {
		this.channel_id = channel_id;
	}

	
}
