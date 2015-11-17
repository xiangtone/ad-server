package cn.adwalker.ad.web.developer.form;

public class AppEditForm {

	private Long id;

	private String pageType[];

	private String os;

	private String name;

	private Long categoryId;

	/** 关键字 */
	private String keyword;

	/** 应用描述 */
	private String longDesc;

	/** 状态（0：待审核，1：审核中，2：通过，3：未通过，4：上线，5：下线，6：终止） */
	private Integer status;

	/**
	 * 应用市场地址
	 */
	private String marketUrl;

	/** 货币显示名称 */
	private String virtual_currency_name;

	/** 货币与人民币的比例 */
	private String exchange_rate_rmb;
	
	/** 积分回调接口*/
	private String response_url;

	public String[] getPageType() {
		return pageType;
	}

	public void setPageType(String[] pageType) {
		this.pageType = pageType;
	}

	public String getVirtual_currency_name() {
		return virtual_currency_name;
	}

	public void setVirtual_currency_name(String virtual_currency_name) {
		this.virtual_currency_name = virtual_currency_name;
	}

	public String getExchange_rate_rmb() {
		return exchange_rate_rmb;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setExchange_rate_rmb(String exchange_rate_rmb) {
		this.exchange_rate_rmb = exchange_rate_rmb;
	}

	public String getMarketUrl() {
		return marketUrl;
	}

	public void setMarketUrl(String marketUrl) {
		this.marketUrl = marketUrl;
	}

	public String getResponse_url() {
		return response_url;
	}

	public void setResponse_url(String response_url) {
		this.response_url = response_url;
	}
	
	
}
