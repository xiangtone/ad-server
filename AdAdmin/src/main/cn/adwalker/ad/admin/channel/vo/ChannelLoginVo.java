package cn.adwalker.ad.admin.channel.vo;

/**
 * COOPERATION_HANDLE_LOG
 * 
 * @author Administrator
 * 
 */
public class ChannelLoginVo {
	private String ip; // ip
	private String url; // url
	private Integer port; // 端口
	private String cp_id; // 渠道
	private Integer status; // 状态 0-成功 1-失败
	private String description; // 描述

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getCp_id() {
		return cp_id;
	}

	public void setCp_id(String cp_id) {
		this.cp_id = cp_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
