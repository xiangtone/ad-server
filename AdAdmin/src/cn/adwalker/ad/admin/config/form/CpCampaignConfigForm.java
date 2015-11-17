package cn.adwalker.ad.admin.config.form;

import java.util.Date;
/**
 * <p>广告对接接口配置</p>
 * @author jief
 *
 */
public class CpCampaignConfigForm {
	  private Long id;					//id
	  private String ad_key;			//广告主提供的adid
	  private String ad_name;			//广告名称
	  private String url;				//广告主url地址
	  private String adid_str;			//广告主广告id的参数名
	  private String deviceid_para;		//mac参数名称
	  private double adv_price;			//广告单价
	  private String sourse_str;		//额外的参数如source=adwalker
	  private String eventtime_para;	//时间戳秒值
	  private String send_type;			//发送方式get/post
	  private String source;			//渠道名称值
	  private String client_ip;			//终端ip参数名称
	  private String openudid;			//OPENUDID参数名称
	  private String idfa;				//IDFA参数名称
	  private String idfv;				//IDFV参数名称
	  private String service_name;		//服务名称服务工厂根据名称找到相应的接口服务
	  private Date  create_time;		//创建时间
	  private String callback;			//回调接口url
	  private Integer placement_id;		//投放id
	  private Double pass_rate;			//激活通过率0-1.0
	  private String udid;				//udid
	  
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAdid_str() {
		return adid_str;
	}
	public void setAdid_str(String adid_str) {
		this.adid_str = adid_str;
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
	public String getSend_type() {
		return send_type;
	}
	public void setSend_type(String send_type) {
		this.send_type = send_type;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
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
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public double getAdv_price() {
		return adv_price;
	}
	public void setAdv_price(double adv_price) {
		this.adv_price = adv_price;
	}
	public Integer getPlacement_id() {
		return placement_id;
	}
	public void setPlacement_id(Integer placement_id) {
		this.placement_id = placement_id;
	}
	public Double getPass_rate() {
		return pass_rate;
	}
	public void setPass_rate(Double pass_rate) {
		this.pass_rate = pass_rate;
	}
	public String getUdid() {
		return udid;
	}
	public void setUdid(String udid) {
		this.udid = udid;
	}
	  
}
