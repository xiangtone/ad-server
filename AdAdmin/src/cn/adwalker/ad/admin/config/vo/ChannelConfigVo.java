package cn.adwalker.ad.admin.config.vo;

import java.io.Serializable;

/**
 * <p></p>
 * @author jief
 *
 */
public class ChannelConfigVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3817445225663555032L;
	private String channel;
	private Long channel_id;
	private String email;
	private String url;
	private String channel_name;
	private String adid_para;
	private String deviceid_para;
	private String time_para;
	private String udid;
	private String client_ip;
	private String openudid;
	private String idfa;
	private String idfv;
	private String service_name;
	private Float pass_rate;			//激活回调比例0.0-1.0
	
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getChannel_name() {
		return channel_name;
	}
	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}
	public String getAdid_para() {
		return adid_para;
	}
	public void setAdid_para(String adid_para) {
		this.adid_para = adid_para;
	}
	public String getDeviceid_para() {
		return deviceid_para;
	}
	public void setDeviceid_para(String deviceid_para) {
		this.deviceid_para = deviceid_para;
	}
	public String getTime_para() {
		return time_para;
	}
	public void setTime_para(String time_para) {
		this.time_para = time_para;
	}
	public String getUdid() {
		return udid;
	}
	public void setUdid(String udid) {
		this.udid = udid;
	}
	public String getClient_ip() {
		return client_ip;
	}
	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}
	public String getOpenudid() {
		return openudid;
	}
	public void setOpenudid(String openudid) {
		this.openudid = openudid;
	}
	public String getIdfa() {
		return idfa;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public String getIdfv() {
		return idfv;
	}
	public void setIdfv(String idfv) {
		this.idfv = idfv;
	}
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	public Float getPass_rate() {
		return pass_rate;
	}
	public void setPass_rate(Float pass_rate) {
		this.pass_rate = pass_rate;
	}
	public Long getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(Long channel_id) {
		this.channel_id = channel_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
