package cn.adwalker.ad.model.application.domain;



/**
* <p>Title: CooperationConfig</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author    lichuang
* @date       2014年7月25日
 */
public class CooperationConfig {
	
	private String response_url;
	private Long app_id;
	private String create_time;
	public String getResponse_url() {
		return response_url;
	}
	public void setResponse_url(String response_url) {
		this.response_url = response_url;
	}
	
	public Long getApp_id() {
		return app_id;
	}
	public void setApp_id(Long app_id) {
		this.app_id = app_id;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
}
