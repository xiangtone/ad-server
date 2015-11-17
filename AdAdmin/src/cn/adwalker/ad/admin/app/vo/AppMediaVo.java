package cn.adwalker.ad.admin.app.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Title: AdByPlacementVo
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-4
 */
public class AppMediaVo implements Serializable {
	
	
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 1966356784714949206L;


	private Long id;
	
	
	private String name;
	
	
	
	private String mobile;
	
	
	private String qq;
	
	
	private Date  create_time;
	
	private Integer status;
	
	private Integer area_type;
	
	private Long create_user;
	
	private String create_user_name;

	/**
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	
	public void setId(Long id) {
		this.id = id;
	}

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
	 * @return create_time
	 */
	public Date getCreate_time() {
		return create_time;
	}

	/**
	 * @param create_time the create_time to set
	 */
	
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	/**
	 * @return status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	
	public void setStatus(Integer status) {
		this.status = status;
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
	 * @return create_user
	 */
	public Long getCreate_user() {
		return create_user;
	}

	/**
	 * @param create_user the create_user to set
	 */
	
	public void setCreate_user(Long create_user) {
		this.create_user = create_user;
	}

	/**
	 * @return create_user_name
	 */
	public String getCreate_user_name() {
		return create_user_name;
	}

	/**
	 * @param create_user_name the create_user_name to set
	 */
	
	public void setCreate_user_name(String create_user_name) {
		this.create_user_name = create_user_name;
	}
	
	
}
