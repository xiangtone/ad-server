/**
 * 
 */
package cn.adwalker.model.config.domain;

/**
 * 
 * <p>
 * Title: ConfigEScore
 * </p>
 * <p>
 * Description:平台设置表实体
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-8-26
 */
public class ConfigEScoreAD {

	/** 主键编号 */
	private Long id;

	/** 开发者申请体现的最小值 */
	private Double devApplyMinMoney;

	/** 开发者每次申请提现的最小提款值 */
	private Double dev_min_draw_money;

	/** 广告墙列表广告数量 */
	private Integer adWallSize;

	/** 活动默认激活有效期 */
	private Integer defaultActivatePeriod;

	/** 默认广告活动指数 */
	private Integer defaultAdIndex;
	/** 积分墙广告统一积分延时 */
	private Integer adwallScoreDelay;

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

	public Integer getAdwallScoreDelay() {
		return adwallScoreDelay;
	}

	public void setAdwallScoreDelay(Integer adwallScoreDelay) {
		this.adwallScoreDelay = adwallScoreDelay;
	}

}
