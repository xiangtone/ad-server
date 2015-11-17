/**
* <p>Title: UserAdRel.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-4-8
* @version 1.0
*/
package cn.adwalker.ad.cache.element;

import java.util.Date;

import cn.adwalker.ad.bean.Data;

/**
 * <p>Title: UserAdRel</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-8
 */
public class UserAdRel extends Data{

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 6135140517670054730L;
	private Long id;// 主键
	private String uuid;// 用户ID
	private Long ad_id;// 广告ID
	private Long app_id;
	private Date create_time;	//sysdate
	private Integer sign_num;
	
	public UserAdRel(){
		
	}
	
	
	
	public Long getApp_id() {
		return app_id;
	}



	public void setApp_id(Long appId) {
		app_id = appId;
	}



	public UserAdRel(String uuid,Long appId, Long adId, Integer signNum) {
		super();
		this.uuid = uuid;
		this.app_id=appId;
		ad_id = adId;
		sign_num = signNum;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public Long getAd_id() {
		return ad_id;
	}
	public void setAd_id(Long ad_id) {
		this.ad_id = ad_id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getSign_num() {
		return sign_num;
	}
	public void setSign_num(Integer sign_num) {
		this.sign_num = sign_num;
	}
	
	
}
