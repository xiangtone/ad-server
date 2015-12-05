/**
 * <p>Title: InterfaceChannelVo.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author wanghl
 * @date 2013-8-6
 * @version 1.0
 */
package cn.adwalker.ad.admin.operation.vo;

/**
 * <p>
 * Title: InterfaceChannelVo
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author wanghl
 * @date 2013-8-6
 */
public class InterfaceChannelVo {
	private String channel;
	private String url;
	private String channel_name;
	private String adid_para;
	private String deviceid_para;
	private String time_para;
	private String udid;
	private String client_ip;

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

}
