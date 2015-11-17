package cn.adwalker.ad.admin.finance.vo;

import java.io.Serializable;

/**
 * <p>
 * Title: CostChannelTempVo
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-10-31
 */
public class CostChannelTempVo implements Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 4475803794461940939L;
	private String static_date;
	private Long type_id;
	private Double cost;
	private Integer amount;
	/**
	 * 广告主确认数，开发者收入
	 */
	private Integer confrim_num;

	public String getStatic_date() {
		return static_date;
	}

	public void setStatic_date(String static_date) {
		this.static_date = static_date;
	}

	public Long getType_id() {
		return type_id;
	}

	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getConfrim_num() {
		return confrim_num;
	}

	public void setConfrim_num(Integer confrim_num) {
		this.confrim_num = confrim_num;
	}
}
