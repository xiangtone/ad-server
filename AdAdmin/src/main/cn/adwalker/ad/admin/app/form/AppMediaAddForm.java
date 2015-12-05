package cn.adwalker.ad.admin.app.form;


/**
 * <p>
 * Title: AdEditForm
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-4-17
 */
public class AppMediaAddForm {

	/**
	 * 日投放量单位
	 */
	private String name;
	
	private String qq;

	private String mobile;
	
	private Integer area_type;
	
	private Long sys_user_id;

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return qq
	 */
	public String getQq() {
		return qq;
	}

	/**
	 * @param qq the qq to set
	 */
	
	public void setQq(String qq) {
		this.qq = qq;
	}

	/**
	 * @return mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return area_type
	 */
	public Integer getArea_type() {
		return area_type;
	}

	/**
	 * @param area_type the area_type to set
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
