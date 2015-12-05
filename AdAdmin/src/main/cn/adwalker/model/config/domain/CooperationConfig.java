package cn.adwalker.model.config.domain;

import cn.adwalker.core.repository.Entity;

/**
 * <p>
 * 开发者积分对接配置bean
 * </p>
 * 
 * @author jief
 * 
 */
public class CooperationConfig implements Entity {
	
	private static final long serialVersionUID = -4433772964768062289L;
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
