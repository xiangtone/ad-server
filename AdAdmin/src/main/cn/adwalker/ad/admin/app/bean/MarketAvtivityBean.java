package cn.adwalker.ad.admin.app.bean;



public class MarketAvtivityBean {
	private Long id;
	private String name;
	private Long budget;
	private String marketContent;
	private Integer status;
	private String acitvity_time;
	private String award;
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
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
