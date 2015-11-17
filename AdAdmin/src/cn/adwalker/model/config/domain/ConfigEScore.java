/**
 * 
 */
package cn.adwalker.model.config.domain;

import cn.adwalker.core.repository.Entity;

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
public class ConfigEScore implements Entity {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 4776825020171696913L;

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
