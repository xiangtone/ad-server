package cn.adwalker.model.operation.domain;


/**
* <p>Title: CollocationIosForm</p>
* <p>Description:Ios配置</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-6-6
 */
public class CollocationIos {
	/** 活动id   */
	private Long placement_id;	
	/** 主键   */
	private String id;	
	/**广告主广告主键  */
//	private String ad_key;
	/** url地址 */
//	private String url;	
	/**广告主键变量名 */
//	private String adid_str;
	/** MAC变量名   */
//	private String deviceid_para;
	/** 附带参数   */
//	private String sourse_str;
	/**  激活时间变量名   */
//	private String eventtime_para;	
	/**发送方式（GET/POST） */
//	private String send_type;
	/**  平台标示   */
//	private String source;	
	/**客户端IP*/
//	private String client_ip;
	/**客户端IP*/
//	private String ad_name;
	
//	private Date create_time;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Long getPlacement_id() {
		return placement_id;
	}
	public void setPlacement_id(Long placement_id) {
		this.placement_id = placement_id;
	}
}
