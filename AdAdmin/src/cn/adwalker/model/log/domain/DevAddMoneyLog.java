/**
 * <p>Title: DevAddMoneyLog.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author hadoop
 * @date 2013-12-2
 * @version 1.0
 */
package cn.adwalker.model.log.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * <p>
 * Title: DevAddMoneyLog
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-12-2
 */
public class DevAddMoneyLog implements Entity {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 2425565716024256695L;

	private Long id;
	private Long dev_id;
	private Date create_time;
	private Long income_id;
	private Double money;
	private Double old_money;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDev_id() {
		return dev_id;
	}

	public void setDev_id(Long dev_id) {
		this.dev_id = dev_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Long getIncome_id() {
		return income_id;
	}

	public void setIncome_id(Long income_id) {
		this.income_id = income_id;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getOld_money() {
		return old_money;
	}

	public void setOld_money(Double old_money) {
		this.old_money = old_money;
	}
	
	

}
