package cn.adwalker.ad.admin.app.vo;

public class BigAppVo {

	/** 主键 */
	private Long id;
	/** 应用名称 */
	private String name;
	/** 状态（0：平台，渠道） */
	private Integer type;
	
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	

}
