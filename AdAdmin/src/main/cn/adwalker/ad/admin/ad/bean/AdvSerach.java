package cn.adwalker.ad.admin.ad.bean;


/**
* <p>Title: AdSerach</p>
* <p>Description:广告主搜索bean</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-4-15
 */
public class AdvSerach {
	
	private Long adv_id;
	
	private String real_name;
	
	private String adv_name;
	
	/** 账户状态*/
	private Integer status;

	
	public Long getAdv_id() {
		return adv_id;
	}
	public void setAdv_id(Long adv_id) {
		this.adv_id = adv_id;
	}
	public String getAdv_name() {
		return adv_name;
	}
	public void setAdv_name(String adv_name) {
		this.adv_name = adv_name;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
