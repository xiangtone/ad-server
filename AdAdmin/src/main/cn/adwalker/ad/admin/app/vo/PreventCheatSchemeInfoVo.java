package cn.adwalker.ad.admin.app.vo;



public class PreventCheatSchemeInfoVo {
	private Long id;
	private Long schemeId;//方案表外健
	private String area;//区域名称
	private Integer config;//0标示限制，正整数标示可配置数，-1标示不限
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(Long schemeId) {
		this.schemeId = schemeId;
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
