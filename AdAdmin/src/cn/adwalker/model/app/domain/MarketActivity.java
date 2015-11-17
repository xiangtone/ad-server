package cn.adwalker.model.app.domain;

import cn.adwalker.core.repository.Entity;

public class MarketActivity implements Entity {
	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = -2963653975240027531L;
	private Long id;
	private String name;
	private Long budget;
	private String content;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
