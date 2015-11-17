package cn.adwalker.ad.admin.operation.vo;

import cn.adwalker.model.operation.domain.CollocationIos;

/**
* <p>Title: CollocationIosForm</p>
* <p>Description:Ios配置</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-6-6
 */
public class CollocationIosVo {
	/** 主键   */
	private Long placement_id;	
	/** 主键   */
	private String id;	
	/**广告主广告主键  */
	private String ad_key;
	/** url地址 */
	private String url;	
	/**广告主键变量名 */
	private String adid_str;
	/** MAC变量名   */
	private String deviceid_para;
	/** 附带参数   */
	private String sourse_str;
	/**  激活时间变量名   */
	private String eventtime_para;	
	/**发送方式（GET/POST） */
	private String send_type;
	/**  平台标示   */
	private String source;	
	/**客户端IP*/
	private String client_ip;
	/**客户端IP*/
	private String ad_name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAd_key() {
		return ad_key;
	}
	public void setAd_key(String ad_key) {
		this.ad_key = ad_key;
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
	
	public Long getPlacement_id() {
		return placement_id;
	}
	public void setPlacement_id(Long placement_id) {
		this.placement_id = placement_id;
	}
	public String getAd_name() {
		return ad_name;
	}
	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}
	public CollocationIosVo() {
		super();
	}
	public CollocationIosVo(CollocationIos collocation) {
		this.id=collocation.getId();
//		this.ad_key=collocation.getAd_key();
//		this.url=collocation.getUrl();
//		this.adid_str=collocation.getAdid_str();
//		this.deviceid_para=collocation.getDeviceid_para();
//		this.sourse_str=collocation.getSourse_str();
//		this.eventtime_para=collocation.getEventtime_para();
//		this.send_type=collocation.getSend_type();
//		this.source=collocation.getSource();
//		this.client_ip=collocation.getClient_ip();
//		this.ad_name =collocation.getAd_name();
	}
	
}
