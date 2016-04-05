/**
 * 
 */
package cn.adwalker.ad.model.common.domain;

/**
 * @author wjp 行云广告设置表实体
 */
public class ConfigEScore {

	/** 主键编号 */
	private Long id;

	/** 广告墙列表广告数量 */
	private Integer adWallSize;

	/**  开发者账号提款最低额度*/
	private Double devApplyMinMoney;
	
	
	
	/**  开发者每次申请提现的最小提款值 */
	private Double dev_min_draw_money;

	/** 活动默认激活有效期 */
	private Integer defaultActivatePeriod;

	/** 默认广告活动指数 */
	private Integer defaultAdIndex;

	/** 广告墙活动最低单价 */
	private Double adwallCamLowestPrice;

	/** 广告墙活动最低每日预算 */
	private Double adwallCamLowestDayBudget;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAdWallSize() {
		return adWallSize;
	}

	public void setAdWallSize(Integer adWallSize) {
		this.adWallSize = adWallSize;
	}

	public Double getDevApplyMinMoney() {
		return devApplyMinMoney;
	}

	public void setDevApplyMinMoney(Double devApplyMinMoney) {
		this.devApplyMinMoney = devApplyMinMoney;
	}

	public Integer getDefaultActivatePeriod() {
		return defaultActivatePeriod;
	}

	public void setDefaultActivatePeriod(Integer defaultActivatePeriod) {
		this.defaultActivatePeriod = defaultActivatePeriod;
	}

	public Integer getDefaultAdIndex() {
		return defaultAdIndex;
	}

	public void setDefaultAdIndex(Integer defaultAdIndex) {
		this.defaultAdIndex = defaultAdIndex;
	}

	public Double getAdwallCamLowestPrice() {
		return adwallCamLowestPrice;
	}

	public void setAdwallCamLowestPrice(Double adwallCamLowestPrice) {
		this.adwallCamLowestPrice = adwallCamLowestPrice;
	}

	public Double getAdwallCamLowestDayBudget() {
		return adwallCamLowestDayBudget;
	}

	public void setAdwallCamLowestDayBudget(Double adwallCamLowestDayBudget) {
		this.adwallCamLowestDayBudget = adwallCamLowestDayBudget;
	}

	public Double getDev_min_draw_money() {
		return dev_min_draw_money;
	}

	public void setDev_min_draw_money(Double dev_min_draw_money) {
		this.dev_min_draw_money = dev_min_draw_money;
	}

}
