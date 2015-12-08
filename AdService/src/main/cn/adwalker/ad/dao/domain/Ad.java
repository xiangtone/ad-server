package cn.adwalker.ad.dao.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * <p>
 * Title:广告实体
 * </p>
 * <p>
 * Description:广告
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-9
 */
public class Ad implements Serializable {
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -549029193439658179L;
	// 主键
	private Long id;
	// 活动id
	private Long placement_id;
	// 媒体id
	private Long media_id;

	// 日投放量
	private Double budget_day;
	// 日投放量单位
	private String budget_type;
	
	private String ad_name;
	
	private String os;
	
	private Integer fast_task;//快速任务

	/**
	 * 日消耗
	 */
	private String cost_day;
	// 结算单价
	private Double blance_price;
	// 结算方式
	private String blance_mode;
	// 投放形式
	private Long type_id;

	// 操作人
	private Long create_user;
	// 创建时间
	private Date create_time;
	// 包id
	private Long package_id;
	/**
	 * 状态(-40：草稿、-30：超量下线;-20：推广结束;-10、暂停；-1：人工下线;0:初始化；1：上线)
	 */
	private Integer status;
	// 排期开始时间
	private Date start_time;
	// 排期结束时间
	private Date end_time;

	/**
	 * 广告类型（渠道或媒体）
	 */
	private Integer ad_type;
	
	/**
	 * 广告来源0、媒体投放,1、渠道投放；3、自有媒体投放
	 */
	private Integer res_type;

	/**
	 * 上线时间
	 */
	private Date online_time;

	/**
	 * 下线时间
	 */
	private Date offline_time;
	
	private Integer confirm_type;
	
	
	private Long cluster_id;
	
	private String terminal_type_str;// 终端类型（0:限制,1:不限制）
	private String operat_agencies_str;//运营商
	private String phone_brand_str;//手机品牌
	private String app_type_str;//应用类型
	private String time_directional_str;//时间定向
	private String area_directional_str;//区域定向
	private String sdk_version_str;	//sdk版本 定向投放  add by jief at 2013-10-21
	
	
	private String ad_mark;
	
	
	
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPlacement_id() {
		return placement_id;
	}

	public void setPlacement_id(Long placement_id) {
		this.placement_id = placement_id;
	}

	public Double getBlance_price() {
		return blance_price;
	}

	public void setBlance_price(Double blance_price) {
		this.blance_price = blance_price;
	}

	public String getBlance_mode() {
		return blance_mode;
	}

	public void setBlance_mode(String blance_mode) {
		this.blance_mode = blance_mode;
	}

	public Long getType_id() {
		return type_id;
	}

	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Long getPackage_id() {
		return package_id;
	}

	public void setPackage_id(Long package_id) {
		this.package_id = package_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public Long getCreate_user() {
		return create_user;
	}

	public void setCreate_user(Long create_user) {
		this.create_user = create_user;
	}

	public Long getMedia_id() {
		return media_id;
	}

	public void setMedia_id(Long media_id) {
		this.media_id = media_id;
	}

	public Integer getAd_type() {
		return ad_type;
	}

	public void setAd_type(Integer ad_type) {
		this.ad_type = ad_type;
	}

	public Date getOnline_time() {
		return online_time;
	}

	public void setOnline_time(Date online_time) {
		this.online_time = online_time;
	}

	public Date getOffline_time() {
		return offline_time;
	}

	public void setOffline_time(Date offline_time) {
		this.offline_time = offline_time;
	}

	public String getBudget_type() {
		return budget_type;
	}

	public void setBudget_type(String budget_type) {
		this.budget_type = budget_type;
	}

	public String getCost_day() {
		return cost_day;
	}

	public void setCost_day(String cost_day) {
		this.cost_day = cost_day;
	}

	public Double getBudget_day() {
		return budget_day;
	}

	public void setBudget_day(Double budget_day) {
		this.budget_day = budget_day;
	}

	public Integer getRes_type() {
		return res_type;
	}

	public void setRes_type(Integer res_type) {
		this.res_type = res_type;
	}

	public String getAd_name() {
		return ad_name;
	}

	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Long getCluster_id() {
		return cluster_id;
	}

	public void setCluster_id(Long cluster_id) {
		this.cluster_id = cluster_id;
	}

	public Integer getFast_task() {
		return fast_task;
	}

	public void setFast_task(Integer fast_task) {
		this.fast_task = fast_task;
	}

	public String getTerminal_type_str() {
		return terminal_type_str;
	}

	public void setTerminal_type_str(String terminal_type_str) {
		this.terminal_type_str = terminal_type_str;
	}

	public String getOperat_agencies_str() {
		return operat_agencies_str;
	}

	public void setOperat_agencies_str(String operat_agencies_str) {
		this.operat_agencies_str = operat_agencies_str;
	}

	public String getPhone_brand_str() {
		return phone_brand_str;
	}

	public void setPhone_brand_str(String phone_brand_str) {
		this.phone_brand_str = phone_brand_str;
	}

	public String getApp_type_str() {
		return app_type_str;
	}

	public void setApp_type_str(String app_type_str) {
		this.app_type_str = app_type_str;
	}

	public String getTime_directional_str() {
		return time_directional_str;
	}

	public void setTime_directional_str(String time_directional_str) {
		this.time_directional_str = time_directional_str;
	}

	public String getArea_directional_str() {
		return area_directional_str;
	}

	public void setArea_directional_str(String area_directional_str) {
		this.area_directional_str = area_directional_str;
	}

	public String getSdk_version_str() {
		return sdk_version_str;
	}

	public void setSdk_version_str(String sdk_version_str) {
		this.sdk_version_str = sdk_version_str;
	}

	public Integer getConfirm_type() {
		return confirm_type;
	}

	public void setConfirm_type(Integer confirm_type) {
		this.confirm_type = confirm_type;
	}

	public String getAd_mark() {
		return ad_mark;
	}

	public void setAd_mark(String ad_mark) {
		this.ad_mark = ad_mark;
	}
}
