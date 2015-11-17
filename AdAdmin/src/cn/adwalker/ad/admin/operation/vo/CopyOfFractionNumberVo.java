package cn.adwalker.ad.admin.operation.vo;

/**
 * 
* <p>Title: FractionNumberVo</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author    cuidd
* @date       2013-7-30
 */
public class CopyOfFractionNumberVo {
	// 主键
	private Long id;
	// 渠道ID
	private Long media_id;
	// 渠道名称
	private String media_name;
	// 渠道包号
	private String package_id;

	// 效果发生时间
	private String static_date;
	// 广告形式
	private Integer type_id;
	
	// 广告形式
	private Integer confirm_num;
	// 平台数
	private Integer sys_activate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMedia_id() {
		return media_id;
	}

	public void setMedia_id(Long media_id) {
		this.media_id = media_id;
	}

	public String getMedia_name() {
		return media_name;
	}

	public void setMedia_name(String media_name) {
		this.media_name = media_name;
	}

	public String getPackage_id() {
		return package_id;
	}

	public void setPackage_id(String package_id) {
		this.package_id = package_id;
	}

	public String getStatic_date() {
		return static_date;
	}

	public void setStatic_date(String static_date) {
		this.static_date = static_date;
	}

	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}

	public Integer getConfirm_num() {
		return confirm_num;
	}

	public void setConfirm_num(Integer confirm_num) {
		this.confirm_num = confirm_num;
	}

	public Integer getSys_activate() {
		return sys_activate;
	}

	public void setSys_activate(Integer sys_activate) {
		this.sys_activate = sys_activate;
	}

}
