package cn.adwalker.ad.admin.app.vo;



public class PreventCheatSchemeVo {
	private Long id;
	private String name;//方案名称
	private Integer isDefault;//是否为默认方案1:是,0:否
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
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	public Integer getAdormedia() {
		return adormedia;
	}
	public void setAdormedia(Integer adormedia) {
		this.adormedia = adormedia;
	}
	
	
}
