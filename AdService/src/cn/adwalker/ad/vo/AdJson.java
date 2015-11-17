/**
* <p>Title: AdJson.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-1-8
* @version 1.0
*/
package cn.adwalker.ad.vo;

import cn.adwalker.ad.bean.Data;


/**
 * <p>Title: AdJson</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-1-8
 */
public class AdJson extends Data{
	private static final long serialVersionUID = -6693462587997126891L;
	private Long id;
	private Long adv_id=1l;//广告主ID
	private String title;// 标题
	private String resourceUrl;// 资源URL
	private Long resourceSize;// 资源大小
	private String fileName;// 文件名词
	private String packageName;// 包名称
	private int isDownload;//0:广告未被下载，1:被下载，2:一次签到，3:二次签到,-1未到签到时间
	private int page_type;//
	private int interval; 
	private GeneralJson general;
	private String adimage_url;
	private int adimage_width;
	private int adimage_height;
	private int ad_type;//0-跳至详情页 1-跳至注册网页jump_url
	private String ad_url;//注册网页URL
	private String app_url;
	private Double star_level;//星级
	private int priority;
	private String category;
	private String score_msg;
	private String ad_score_msg;
	private String create_time;
	private Integer delay_time;
	//是否是新应用1：是，0：否 add bu jief 2013-09-05
	private Integer new_app;
	private Integer apple_id;
	private Integer is_url_params;
	private String url_params;
	
	/*
	 * 搜索关键字
	 */
	private String keyword;
	
	
	/*
	 * 搜索任务描述
	 */
	private String search_desc;
	

	@Deprecated
	private String appid;
	

	public String getAppid() {
		return appid;
	}


	public void setAppid(String appid) {
		this.appid = appid;
	}

	public Integer getIs_url_params() {
		return is_url_params;
	}


	public void setIs_url_params(Integer isUrlParams) {
		is_url_params = isUrlParams;
	}


	public String getUrl_params() {
		return url_params;
	}


	public void setUrl_params(String urlParams) {
		url_params = urlParams;
	}


	public Integer getApple_id() {
		return apple_id;
	}
	
	
	public String getCreate_time() {
		return create_time;
	}


	public void setCreate_time(String createTime) {
		create_time = createTime;
	}


	public void setApple_id(Integer appleId) {
		apple_id = appleId;
	}
	public int getIsDownload() {
		return isDownload;
	}
	public void setIsDownload(int isDownload) {
		this.isDownload = isDownload;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getAd_url() {
		return ad_url;
	}
	public void setAd_url(String ad_url) {
		this.ad_url = ad_url;
	}

	public int getAd_type() {
		return ad_type;
	}
	public void setAd_type(int ad_type) {
		this.ad_type = ad_type;
	}
	public Long getId() {
		return id;
	}
	public String getAdimage_url() {
		return adimage_url;
	}

	public void setAdimage_url(String adimage_url) {
		this.adimage_url = adimage_url;
	}

	public int getAdimage_width() {
		return adimage_width;
	}

	public void setAdimage_width(int adimage_width) {
		this.adimage_width = adimage_width;
	}



	public int getAdimage_height() {
		return adimage_height;
	}

	public void setAdimage_height(int adimage_height) {
		this.adimage_height = adimage_height;
	}



	public void setId(Long id) {
		this.id = id;
	}

	public Long getAdv_id() {
		return adv_id;
	}

	public void setAdv_id(Long adv_id) {
		this.adv_id = adv_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	public Long getResourceSize() {
		return resourceSize;
	}

	public void setResourceSize(Long resourceSize) {
		this.resourceSize = resourceSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public int getPage_type() {
		return page_type;
	}

	public void setPage_type(int page_type) {
		this.page_type = page_type;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public GeneralJson getGeneral() {
		return general;
	}

	public void setGeneral(GeneralJson general) {
		this.general = general;
	}
	public String getApp_url() {
		return app_url;
	}
	public void setApp_url(String app_url) {
		this.app_url = app_url;
	}
	
	
	public Double getStar_level() {
		return star_level;
	}
	public void setStar_level(Double star_level) {
		this.star_level = star_level;
	}
	
	public String getScore_msg() {
		return score_msg;
	}
	public void setScore_msg(String score_msg) {
		this.score_msg = score_msg;
	}
	public Integer getDelay_time() {
		return delay_time;
	}
	public void setDelay_time(Integer delay_time) {
		this.delay_time = delay_time;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getAd_score_msg() {
		return ad_score_msg;
	}
	public void setAd_score_msg(String ad_score_msg) {
		this.ad_score_msg = ad_score_msg;
	}
	public Integer getNew_app() {
		return new_app;
	}
	public void setNew_app(Integer new_app) {
		this.new_app = new_app;
	}


	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public String getSearch_desc() {
		return search_desc;
	}


	public void setSearch_desc(String search_desc) {
		this.search_desc = search_desc;
	}
}
