/**
 * 
 */
package cn.adwalker.model.finance.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * @author wjp
 * 
 */
public class AdvRechargeLog implements Entity {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = -5398587222330180185L;

	/** 主键id */
	private Long id;

	/** 广告主id */
	private Long adv_Id;

	/** 银行id */
	private Long bankId;

	/** 充值金额 */
	private Double rechargeMoney;

	/** 类型 */
	private Integer type;

	/** 描述 */
	private String description;
	/** Email */
	private String advEmail;

	/** 操作人id */
	private Long managerId;

	/** 充值时间 */
	private String rechargeDate;

	/** 创建时间 */
	private Date createTime;

	/** 联系人 */
	private String realName;

	public String getAdvEmail() {
		return advEmail;
	}

	public void setAdvEmail(String advEmail) {
		this.advEmail = advEmail;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public Long getAdv_Id() {
		return adv_Id;
	}

	public void setAdv_Id(Long adv_Id) {
		this.adv_Id = adv_Id;
	}

	/**
	 * @return the bankId
	 */
	public Long getBankId() {
		return bankId;
	}

	/**
	 * @param bankId
	 *            the bankId to set
	 */
	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	/**
	 * @return the rechargeMoney
	 */
	public Double getRechargeMoney() {
		return rechargeMoney;
	}

	/**
	 * @param rechargeMoney
	 *            the rechargeMoney to set
	 */
	public void setRechargeMoney(Double rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the managerId
	 */
	public Long getManagerId() {
		return managerId;
	}

	/**
	 * @param managerId
	 *            the managerId to set
	 */
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the rechargeDate
	 */
	public String getRechargeDate() {
		return rechargeDate;
	}

	/**
	 * @param rechargeDate
	 *            the rechargeDate to set
	 */
	public void setRechargeDate(String rechargeDate) {
		this.rechargeDate = rechargeDate;
	}

	/**
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * @param realName
	 *            the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

}
