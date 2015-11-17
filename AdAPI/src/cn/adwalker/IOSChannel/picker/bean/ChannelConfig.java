package cn.adwalker.IOSChannel.picker.bean;

import java.util.HashMap;
import java.util.Map;

public class ChannelConfig implements  java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String channel;
	private String url;
	private String channel_name;
	public String alis;
	private Float pass_rate; 			//渠道激活通过率0-1.0
	
	private String adid_para;			//广告标示参数
	private String deviceid_para;		//mac 参数名称
	private String time_para;			//激活时间参数名称
	private String client_ip;			//客户端ip参数名称
	private String idfa;				//idfa的参数名
	private String openudid;			//openudid的参数名称、
	private String service_name;		//服务名
	
	public String getAlis() {
		return alis;
	}
	public void setAlis(String alis) {
		this.alis = alis;
	}
	public Map<String, ParamConfig> map = new HashMap<String, ParamConfig>();
	
	public Map<String, ParamConfig> getMap() {
		return map;
	}
	public void setMap(Map<String, ParamConfig> map) {
		this.map = map;
	}
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
	public void setChannel_name(String channelName) {
		channel_name = channelName;
	}
	public Float getPass_rate() {
		return pass_rate;
	}
	public void setPass_rate(Float pass_rate) {
		this.pass_rate = pass_rate;
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
	public String getClient_ip() {
		return client_ip;
	}
	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}
	public String getIdfa() {
		return idfa;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public String getOpenudid() {
		return openudid;
	}
	public void setOpenudid(String openudid) {
		this.openudid = openudid;
	}
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	
	
}
