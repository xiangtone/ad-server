/**
* <p>Title: Advertisement_IOS.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2012-12-21
* @version 1.0
*/
package cn.adwalker.IOSChannel.vo;

import java.util.Date;

import cn.adwalker.ad.bean.Data;

/**
 * <p>Title: Advertisement_IOS</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2012-12-21
 */
public class Advertisement_IOS  extends Data{
	
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -7064943546345074924L;

	/***主键id**/
	private String id;
	
	/***广告key  yek提供**/
	private String ad_key;
	
	/***广告名**/
	private String ad_name;

	/***广告主单价**/
	private double adv_price;
	
	/***渠道单价**/
	private double channel_price;
	
	/***创建时间**/
	private Date create_time;
	
	private String url;//广告主URL
	
	private String send_type;//发送方式
	
	private String udid;//广告主URL
	
	private String client_ip;//发送方式
	
	//广告主参数名称
	private String adid_str;
	private String deviceid_para;
	private String sourse_str;
	private String eventtime_para;

	//add by jief 2013-09-03
	private String openudid;  	//ios7以后新添加的设备标示用OpenUDID
	private String idfa;	  	//ios7以后新添加的设备标示用IDFA
	private String idfv;	  	//ios7以后新添加的设备标示用IDFV
	private String callback;  	//给广告主提供的回调接口 2013-11-20
	private String forward_url;	//app下载跳转地址	
	
	private String service_name;//服务名
	
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
	public double getChannel_price() {
		return channel_price;
	}
	public void setChannel_price(double channel_price) {
		this.channel_price = channel_price;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getDeviceid_para() {
		return deviceid_para;
	}
	public void setDeviceid_para(String deviceid_para) {
		this.deviceid_para = deviceid_para;
	}
	
	
	public String getSourse_str() {
		return sourse_str;
	}
	public void setSourse_str(String sourse_str) {
		this.sourse_str = sourse_str;
	}
	public String getEventtime_para() {
		return eventtime_para;
	}
	public void setEventtime_para(String eventtime_para) {
		this.eventtime_para = eventtime_para;
	}
	
	public String getAd_key() {
		return ad_key;
	}
	public void setAd_key(String ad_key) {
		this.ad_key = ad_key;
	}
	public String getAd_name() {
		return ad_name;
	}
	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}
	public String getAdid_str() {
		return adid_str;
	}
	public void setAdid_str(String adid_str) {
		this.adid_str = adid_str;
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
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	public String getForward_url() {
		return forward_url;
	}
	public void setForward_url(String forward_url) {
		this.forward_url = forward_url;
	}
	
}
