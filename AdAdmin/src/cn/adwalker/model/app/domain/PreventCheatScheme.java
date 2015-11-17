package cn.adwalker.model.app.domain;

import cn.adwalker.core.repository.Entity;

public class PreventCheatScheme implements Entity {

	private static final long serialVersionUID = 1221354956344042409L;
	private Long id;
	private String name;//方案名称
	private Integer is_default;//是否为默认方案1:是,0:否
	private Integer adormedia;//0:广告方案,1媒体方案
	
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
	public Integer getIs_default() {
		return is_default;
	}
	public void setIs_default(Integer is_default) {
		this.is_default = is_default;
	}
	public Integer getAdormedia() {
		return adormedia;
	}
	public void setAdormedia(Integer adormedia) {
		this.adormedia = adormedia;
	}
	
}
