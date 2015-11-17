/**
 * <p>Title: BalancePlatformRel.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author hadoop
 * @date 2013-12-6
 * @version 1.0
 */
package cn.adwalker.model.finance.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * <p>
 * Title: BalancePlatformRel
 * </p>
 * <p>
 * Description:收入结算与平台成本之间的对应关系
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-12-6
 */
public class BalancePlatformRel implements Entity {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = -2816391803797126627L;

	private Long id;
	private Long balance_id;

	private Long detail_id;

	private Date create_time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBalance_id() {
		return balance_id;
	}

	public void setBalance_id(Long balance_id) {
		this.balance_id = balance_id;
	}

	public Long getDetail_id() {
		return detail_id;
	}

	public void setDetail_id(Long detail_id) {
		this.detail_id = detail_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}
