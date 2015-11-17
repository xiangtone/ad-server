/**
 * <p>Title: Advertise.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2013-4-11
 * @version 1.0
 */
package cn.adwalker.ad.cache.element;

import java.util.Date;

import cn.adwalker.ad.bean.Data;

/**
 * <p>
 * Title: Advertise
 * </p>
 * <p>
 * Description:广告缓存结构类
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author www.adwalker.cn
 * @date 2013-4-11
 */
public class Advertise extends Data {
	/** @Fields serialVersionUID : 序列化*/
	private static final long serialVersionUID = 615732657232841220L;
	
	private Long placement_id;
	private long id;
	private Double blance_price;//
	private String blance_mode;
	private Integer type_id;// 投放形式
	private Date create_time;
	private Long package_id;//
	private PackageInfo packageInfo;
	private int status;// (0：草稿、1：待审核、2：已审核、3：下线、4：余额不足5：推广结束)
	private String ad_name;// 投放名称
	private Integer fast_task;//快速任务
	private String os;
	private String slogan;//广告语
	private String icon_url;
	private Integer  priority;//是否优先
	private Double star_level;
	private String config_id;
	private Long category_id;//广告类别
	
	private String terminal_type_str;//终端类型（0:限制,1:不限制）
	private String operat_agencies_str;//运营商
	private String phone_brand_str;//手机品牌
	private String time_directional_str;//时间定向
	private String area_directional_str;//区域定向
	private String sdk_version_str;	//sdk版本 定向投放  add by jief at 2013-10-21
	public String app_type_str;
	public Integer budget_day;
	private Date end_time;// 限制时间
	
	private Integer confirm_type;//0激活；2点击
	
	private String keyword;
	
	
	/**
	 * 抽象墙，暂时存储墙物料信息
	 */
	private Wall wall;
	
	//TODO
	private Integer show_score;// 显示的积分	
	private String scoreUnit;// 积分墙单位
	
	
	//自定义
	private Integer response_type;//打开方式
	private String adimage_url;
	private Integer adimage_width;
	private Integer adimage_height;
	private String category_name;//应用类别

	private int isDownload;//是否被下载
	private String res_url;
	private int weight;//
	private String ad_url;
	private String app_url;
	private Integer popular_app;
	private Integer new_app;//是否是新应用1：是，0：否 add bu jief 2013-09-05
	public Integer is_dsp;
	public Integer dsp_id;
	public Integer confirm_mode;//确认方式  线上 线下.
	public Integer is_url_params;
	public String url_params;
	
	
	
	//ios单独
	public String appstore_id;
	
	/**
	 * 广告来源
	 */
	private String ad_res_code;
	
	
	//来源
	public String res_code;
	
	private String ad_package_size;
	
	private String ad_mark;
	
	
	public Wall getWall() {
		return wall;
	}

	public void setWall(Wall wall) {
		this.wall = wall;
	}
	
	public String getUrl_params() {
		return url_params;
	}

	public void setUrl_params(String urlParams) {
		url_params = urlParams;
	}

	public Integer getIs_url_params() {
		return is_url_params;
	}

	public void setIs_url_params(Integer isUrlParams) {
		is_url_params = isUrlParams;
	}

	public String getApp_type_str() {
		return app_type_str;
	}

	public void setApp_type_str(String appTypeStr) {
		app_type_str = appTypeStr;
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

	public void setPopular_app(Integer popularApp) {
		popular_app = popularApp;
	}

	public Integer getConfirm_mode() {
		return confirm_mode;
	}

	public void setConfirm_mode(Integer confirmMode) {
		confirm_mode = confirmMode;
	}

	public Integer getIs_dsp() {
		return is_dsp;
	}

	public void setIs_dsp(Integer isDsp) {
		is_dsp = isDsp;
	}

	public Integer getDsp_id() {
		return dsp_id;
	}

	public void setDsp_id(Integer dspId) {
		dsp_id = dspId;
	}

	//构造器
	public Advertise(){
		isDownload=0;
		weight=0;
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

	public PackageInfo getPackageInfo() {
		return packageInfo;
	}

	public void setPackageInfo(PackageInfo packageInfo) {
		this.packageInfo = packageInfo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Integer getShow_score() {
		return show_score;
	}

	public void setShow_score(Integer show_score) {
		this.show_score = show_score;
	}

	public String getAdimage_url() {
		return adimage_url;
	}

	public void setAdimage_url(String adimage_url) {
		this.adimage_url = adimage_url;
	}

	public Integer getAdimage_width() {
		return adimage_width;
	}

	public void setAdimage_width(Integer adimage_width) {
		this.adimage_width = adimage_width;
	}

	public Integer getAdimage_height() {
		return adimage_height;
	}

	public void setAdimage_height(Integer adimage_height) {
		this.adimage_height = adimage_height;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public int getIsDownload() {
		return isDownload;
	}

	public void setIsDownload(int isDownload) {
		this.isDownload = isDownload;
	}

	public String getRes_url() {
		return res_url;
	}

	public void setRes_url(String res_url) {
		this.res_url = res_url;
	}

	
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getAd_url() {
		return ad_url;
	}

	public void setAd_url(String ad_url) {
		this.ad_url = ad_url;
	}
	


	public String getApp_url() {
		return app_url;
	}

	public void setApp_url(String app_url) {
		this.app_url = app_url;
	}
    
	public Integer getNew_app() {
		return new_app;
	}

	public void setNew_app(Integer new_app) {
		this.new_app = new_app;
	}

	public Long getPlacement_id() {
		return placement_id;
	}

	public void setPlacement_id(Long placement_id) {
		this.placement_id = placement_id;
	}

	public String getAd_package_size() {
		return ad_package_size;
	}

	public void setAd_package_size(String ad_package_size) {
		this.ad_package_size = ad_package_size;
	}

	public String getRes_code() {
		return res_code;
	}

	public void setRes_code(String res_code) {
		this.res_code = res_code;
	}

	public String getAd_res_code() {
		return ad_res_code;
	}

	public void setAd_res_code(String ad_res_code) {
		this.ad_res_code = ad_res_code;
	}

	public String getAppstore_id() {
		return appstore_id;
	}

	public void setAppstore_id(String appstore_id) {
		this.appstore_id = appstore_id;
	}

	public String getScoreUnit() {
		return scoreUnit;
	}

	public void setScoreUnit(String scoreUnit) {
		this.scoreUnit = scoreUnit;
	}

	public Integer getFast_task() {
		return fast_task;
	}

	public void setFast_task(Integer fast_task) {
		this.fast_task = fast_task;
	}

	public String getAd_name() {
		return ad_name;
	}

	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getIcon_url() {
		return icon_url;
	}

	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Double getStar_level() {
		return star_level;
	}

	public void setStar_level(Double star_level) {
		this.star_level = star_level;
	}

	public String getConfig_id() {
		return config_id;
	}

	public void setConfig_id(String config_id) {
		this.config_id = config_id;
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

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Integer getBudget_day() {
		return budget_day;
	}

	public void setBudget_day(Integer budget_day) {
		this.budget_day = budget_day;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public Integer getConfirm_type() {
		return confirm_type;
	}

	public void setConfirm_type(Integer confirm_type) {
		this.confirm_type = confirm_type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getResponse_type() {
		return response_type;
	}

	public void setResponse_type(Integer response_type) {
		this.response_type = response_type;
	}

	public String getAd_mark() {
		return ad_mark;
	}

	public void setAd_mark(String ad_mark) {
		this.ad_mark = ad_mark;
	}
	
	
}