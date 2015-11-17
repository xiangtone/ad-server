/**
 * 
 */
package cn.adwalker.ad.control.domain;


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
public class ConfigEScore  {
	/** 主键编号 */
	private Long id;

	/** 开发者申请体现的最小值 */
	private Double devApplyMinMoney;

	/** 开发者每次申请提现的最小提款值 */
	private Double dev_min_draw_money;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getDevApplyMinMoney() {
		return devApplyMinMoney;
	}

	public void setDevApplyMinMoney(Double devApplyMinMoney) {
		this.devApplyMinMoney = devApplyMinMoney;
	}

	public Double getDev_min_draw_money() {
		return dev_min_draw_money;
	}

	public void setDev_min_draw_money(Double dev_min_draw_money) {
		this.dev_min_draw_money = dev_min_draw_money;
	}

	
	
}
