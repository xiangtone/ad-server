package cn.adwalker.model.app.domain;

import cn.adwalker.core.repository.Entity;

public class PreventCheatSchemeInfo implements Entity {

	private static final long serialVersionUID = 1221354956344042409L;
	private Long id;
	private Long scheme_id;//方案表外健
	private String area;//区域名称
	private Integer config;//0标示限制，正整数标示可配置数，-1标示不限
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getScheme_id() {
		return scheme_id;
	}
	public void setScheme_id(Long scheme_id) {
		this.scheme_id = scheme_id;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Integer getConfig() {
		return config;
	}
	public void setConfig(Integer config) {
		this.config = config;
	}
	
	
}
