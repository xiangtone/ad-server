package cn.adwalker.ad.admin.app.form;

import java.util.Date;
/**
* <p>Title: MarketAvtivityForm</p>
* <p>Description:市场活动form</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-7-8
 */

public class MarketAvtivityForm {
	//主键
	private Long id;
	//市场活动名称
	private String name;
	//市场活动预算
	private Long budget;
	//市场活动内容
	private String marketContent;
	
	private Date create_time;
	//媒体活动时间
	private String acitvity_time;
	//市场活动奖励
	private String award;
	//市场活动总结
	private String summary;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getBudget() {
		return budget;
	}
	public void setBudget(Long budget) {
		this.budget = budget;
	}
	
	
	public String getMarketContent() {
		return marketContent;
	}
	public void setMarketContent(String marketContent) {
		this.marketContent = marketContent;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public String getAcitvity_time() {
		return acitvity_time;
	}
	public void setAcitvity_time(String acitvity_time) {
		this.acitvity_time = acitvity_time;
	}
	public String getAward() {
		return award;
	}
	public void setAward(String award) {
		this.award = award;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
}
