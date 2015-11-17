package cn.adwalker.ad.admin.sales.vo;


/**
 * <p>
 * Title: AdAjustmentEditVo
 * </p>
 * <p>
 * Description:广告管理修改回显Vo
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-4
 */
public class SalesmanEditVo {
	/** 销售负责人 */
	private String name;
	/** 销售负责人手机号 */
	private String mobile;
	/** 销售负责人QQ */
	private String qq;

	/** 区域类型(4:华南、1:华东、2:华北、0:平台) */
	private Integer area_type;
	
	
	private Long sys_user_id;
	
	
	

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return qq
	 */
	public String getQq() {
		return qq;
	}

	/**
	 * @param qq
	 *            the qq to set
	 */

	public void setQq(String qq) {
		this.qq = qq;
	}

	/**
	 * @return area_type
	 */
	public Integer getArea_type() {
		return area_type;
	}

	/**
	 * @param area_type
	 *            the area_type to set
	 */

	public void setArea_type(Integer area_type) {
		this.area_type = area_type;
	}

	/**
	 * @return sys_user_id
	 */
	public Long getSys_user_id() {
		return sys_user_id;
	}

	/**
	 * @param sys_user_id the sys_user_id to set
	 */
	
	public void setSys_user_id(Long sys_user_id) {
		this.sys_user_id = sys_user_id;
	}
	
	
}
