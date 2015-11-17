package cn.adwalker.ad.dao.domain;

import java.io.Serializable;
import java.util.Date;

public class AdList implements Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 8480266317766594613L;
	private Long placement_id;
	private long id;	
	private Double blance_price;//
	private String blance_mode;
	private Integer type_id;// 投放形式
	private Date create_time;
	private Long package_id;//
	private int status;// (0：草稿、1：待审核、2：已审核、3：下线、4：余额不足5：推广结束)
	private Long category_id;// 广告类别
	private Integer popular_app;
	public Integer is_dsp;
	public Integer dsp_id;
	private Integer fast_task;//快速任务
	public String ad_name;
	private Date end_time;
	private Integer budget_day;
	
	private String ad_mark;
	
	//广告限制
	private String terminal_type_str;// 终端类型（0:限制,1:不限制）
	private String operat_agencies_str;//运营商
	private String phone_brand_str;//手机品牌
	private String app_type_str;//应用类型
	private String time_directional_str;//时间定向
	private String area_directional_str;//区域定向
	private String sdk_version_str;	//sdk版本 定向投放  add by jief at 2013-10-21

	public Long getPlacement_id() {
		return placement_id;
	}

	public void setPlacement_id(Long placement_id) {
		this.placement_id = placement_id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer type_id) {
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAd_name() {
		return ad_name;
	}

	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public Integer getPopular_app() {
		return popular_app;
	}

	public void setPopular_app(Integer popular_app) {
		this.popular_app = popular_app;
	}

	public Integer getIs_dsp() {
		return is_dsp;
	}

	public void setIs_dsp(Integer is_dsp) {
		this.is_dsp = is_dsp;
	}

	public Integer getDsp_id() {
		return dsp_id;
	}

	public void setDsp_id(Integer dsp_id) {
		this.dsp_id = dsp_id;
	}

	public String getApp_type_str() {
		return app_type_str;
	}

	public void setApp_type_str(String app_type_str) {
		this.app_type_str = app_type_str;
	}

	public Integer getFast_task() {
		return fast_task;
	}

	public void setFast_task(Integer fast_task) {
		this.fast_task = fast_task;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public Integer getBudget_day() {
		return budget_day;
	}

	public void setBudget_day(Integer budget_day) {
		this.budget_day = budget_day;
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

	public String getAd_mark() {
		return ad_mark;
	}

	public void setAd_mark(String ad_mark) {
		this.ad_mark = ad_mark;
	}
}
