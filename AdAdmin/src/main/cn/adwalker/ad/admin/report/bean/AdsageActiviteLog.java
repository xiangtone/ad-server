package cn.adwalker.ad.admin.report.bean;

import java.util.Date;

public class AdsageActiviteLog {

	/*** 主键 **/
	private String id;

	/*** 苹果应用平台id 广告id **/
	private String ad_id;

	/*** 广告名称 **/
	private String ad_name;

	/*** mac地址 **/
	private String mac;

	/*** yek调用咱们接口的时间(激活时间) **/
	private Long activite_date;

	/*** 渠道 **/
	private String channel;
	/**渠道名称**/
	private String channel_name;
	/*** 确认状态（0：待确认 1：已确认 ） **/
	private Integer status;

	/*** 创建时间 **/
	private Date create_time;
	/*** 激活标示 0：未激活 1：已激活 **/
	private Integer activite_status;

	/*** 激活标示 0：未激活 1：已激活 **/
	private Integer page_type;

	/*** 激活标示 0：未激活 1：已激活 **/
	private String application_key;
	/**
	 * add by jief idfa,openudid
	 */
	private String idfa;
	private String openUdid;
	private String idfv;
	
	private Integer send;	//是否发送0:刷量嫌疑未发送，1:已发送，2:被扣量
	
	public String getApplication_key() {
		return application_key;
	}

	public void setApplication_key(String application_key) {
		this.application_key = application_key;
	}

	public Integer getActivite_status() {
		return activite_status;
	}

	public void setActivite_status(Integer activite_status) {
		this.activite_status = activite_status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAd_id() {
		return ad_id;
	}

	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
	}

	public String getAd_name() {
		return ad_name;
	}

	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Long getActivite_date() {
		return activite_date;
	}

	public void setActivite_date(Long activite_date) {
		this.activite_date = activite_date;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Integer getStatus() {
		if (activite_status == 1) {
			status = 1;
		}
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Integer getPage_type() {
		return page_type;
	}

	public void setPage_type(Integer page_type) {
		this.page_type = page_type;
	}

	public String getIdfa() {
		return idfa;
	}

	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}

	public String getOpenUdid() {
		return openUdid;
	}

	public void setOpenUdid(String openUdid) {
		this.openUdid = openUdid;
	}

	public String getIdfv() {
		return idfv;
	}

	public void setIdfv(String idfv) {
		this.idfv = idfv;
	}

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public Integer getSend() {
		return send;
	}

	public void setSend(Integer send) {
		this.send = send;
	}

	
}
