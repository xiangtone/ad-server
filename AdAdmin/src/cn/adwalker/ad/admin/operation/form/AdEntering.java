/**
 * 
 */
package cn.adwalker.ad.admin.operation.form;

import java.util.Date;


/**
* <p>Title: AdEntering</p>
* <p>Description:广告效果基础数据</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-5-10
 */
public class AdEntering {

	/** 主键id */
	private Long id;
	/** 活动id */
	private Long campaign_id;
	/** 渠道包 */
	private String page_id;	
	/** 有效激活数 */
	private Integer income_amount;
	/** 效果发生时间 */
	private String stat_date;	
	/** 创建时间 */
	private Date createTime;
	/** 是否覆盖的状态 */
	private  Integer flag_confirm;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPage_id() {
		return page_id;
	}
	public void setPage_id(String page_id) {
		this.page_id = page_id;
	}
	public Integer getIncome_amount() {
		return income_amount;
	}
	public void setIncome_amount(Integer income_amount) {
		this.income_amount = income_amount;
	}
	
	public String getStat_date() {
		return stat_date;
	}
	public void setStat_date(String stat_date) {
		this.stat_date = stat_date;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getCampaign_id() {
		return campaign_id;
	}
	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}
	public Integer getFlag_confirm() {
		return flag_confirm;
	}
	public void setFlag_confirm(Integer flag_confirm) {
		this.flag_confirm = flag_confirm;
	}	
	
}
