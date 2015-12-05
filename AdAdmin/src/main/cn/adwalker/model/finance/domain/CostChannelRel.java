/**
 * <p>Title: CostChannelRel.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author hadoop
 * @date 2013-11-27
 * @version 1.0
 */
package cn.adwalker.model.finance.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * <p>
 * Title: CostChannelRel
 * </p>
 * <p>
 * Description:渠道结算数据与每天效果明细之间的关系
 * </p>
 * <p>
 * Company: emar
 * </p>
 * 
 * @author cuidd
 * @date 2013-11-27
 */
public class CostChannelRel implements Entity {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = -5692612697602346895L;

	private Long id;
	private Long balance_id;
	private Integer status;
	private Date create_time;
	private Long detail_id;
	private String os;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Long getDetail_id() {
		return detail_id;
	}
	public void setDetail_id(Long detail_id) {
		this.detail_id = detail_id;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
}
