/**
* <p>Title: Advertisement_IOS.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2012-12-21
* @version 1.0
*/
package cn.adwalker.IOSChannel.picker.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: Advertisement_IOS</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2012-12-21
 */
public class CampaignConfig  implements  java.io.Serializable{
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -7064943546345074924L;
	private String id;
	private String ad_key;
	private double adv_price;
	private String url;
	private String send_type;
	
	private String udid;
	private String source;
	private String client_ip;
	private String openudid;  
	private String idfa;	 
	private String idfv;
	private String adid_str;
	private String deviceid_para;
	private String sourse_str;
	private String callback;
	private String service_name;
	private String eventtime_para;		//时间戳
	public Map<String,ParamConfig> map = new HashMap<String, ParamConfig>();
	private Float pass_rate; 			//渠道激活通过率0-1.0
	
	public Float getPass_rate() {
		return pass_rate;
	}
	public void setPass_rate(Float pass_rate) {
		this.pass_rate = pass_rate;
	}
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String serviceName) {
		service_name = serviceName;
	}
	public String getSourse_str() {
		return sourse_str;
	}
	public void setSourse_str(String sourseStr) {
		sourse_str = sourseStr;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public String getAdid_str() {
		return adid_str;
	}
	public void setAdid_str(String adidStr) {
		adid_str = adidStr;
	}
	public String getDeviceid_para() {
		return deviceid_para;
	}
	public void setDeviceid_para(String deviceidPara) {
		deviceid_para = deviceidPara;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
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
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public double getAdv_price() {
		return adv_price;
	}
	public void setAdv_price(double adv_price) {
		this.adv_price = adv_price;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	
	public String getAd_key() {
		return ad_key;
	}
	public void setAd_key(String ad_key) {
		this.ad_key = ad_key;
	}
	
	public String getSend_type() {
		return send_type;
	}
	public void setSend_type(String send_type) {
		this.send_type = send_type;
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
	public String getEventtime_para() {
		return eventtime_para;
	}
	public void setEventtime_para(String eventtime_para) {
		this.eventtime_para = eventtime_para;
	}
	
}
