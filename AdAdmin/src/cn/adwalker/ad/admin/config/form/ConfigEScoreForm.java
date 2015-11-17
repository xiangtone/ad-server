/**
 * <p>Title: ConfigEScoreForm.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-8-26
 * @version 1.0
 */
package cn.adwalker.ad.admin.config.form;

/**
 * <p>
 * Title: ConfigEScoreForm
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-8-26
 */
public class ConfigEScoreForm {

	private Long id;
	private Double devApplyMinMoney;
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
