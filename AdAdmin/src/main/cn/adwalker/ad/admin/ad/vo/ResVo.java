/**
 * <p>Title: ResVo.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-5-9
 * @version 1.0
 */
package cn.adwalker.ad.admin.ad.vo;

/**
 * <p>
 * Title: ResVo
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-5-9
 */
public class ResVo {
	
	/**
	 * 平台类型
	 */
	private String os;

	/**
	 * 应用ico
	 */
	private String iconimg_url;
	/**
	 * 应用图片1
	 */
	private String appimg_url_01;

	/**
	 * 应用图片2
	 */
	private String appimg_url_02;

	/**
	 * 应用图片3
	 */
	private String appimg_url_03;

	/**
	 * 
	 */
	private String wall_score_banner_url;

	/**
	 * 
	 */
	private String wall_score_desc;

	/**
	 * 积分长描述
	 */
	private String wall_score_long_desc;

	/**
	 * banner响应分类
	 */
	private Integer wall_score_response_category;

	/**
	 * 响应方式
	 */
	private Integer wall_score_response_type;

	// 跳转地址
	private String wall_score_redirect_url;
	
	private String wall_score_weixin_desc;

	/**
	 * 权重
	 */
	private Integer wall_score_weight;
	/**
	 * 积分延时
	 */
	private Integer score_delay;
	
	private Integer	wall_score_limit_time;

	/**
	 * 权重
	 */
	private Integer wall_score_max_weight;

	private String wall_list_banner_url;
	/**
	 * banner响应分类
	 */
	private Integer wall_list_response_category;

	/**
	 * 响应方式
	 */
	private Integer wall_list_response_type;

	// 跳转地址
	private String wall_list_redirect_url;

	/**
	 * 权重
	 */
	private Integer wall_list_weight;
	
	private Integer wall_list_max_weight;

	private String banner_redirect_url;

	// 0-跳至详情页 1-跳至注册网页jump_url
	private Integer banner_response_type;

	private Integer banner_response_category;

	// banner图片1
	private String beanner_img_url_first;

	// banner图片2
	private String beanner_img_url_second;

	// banner图片3
	private String beanner_img_url_third;

	private Integer banner_weight;
	
	private Integer banner_max_weight;

	private String chartboost_redirect_url;

	// 0-跳至详情页 1-跳至注册网页jump_url
	private Integer chartboost_response_type;

	private Integer chartboost_response_category;

	private Integer chartboost_weight;
	
	private Integer chartboost_max_weight;

	// 插屏横向
	private String img_horizontal;

	// 插屏纵向
	private String img_vertical;

	public String getIconimg_url() {
		return iconimg_url;
	}

	public void setIconimg_url(String iconimg_url) {
		this.iconimg_url = iconimg_url;
	}

	public String getAppimg_url_01() {
		return appimg_url_01;
	}

	public void setAppimg_url_01(String appimg_url_01) {
		this.appimg_url_01 = appimg_url_01;
	}

	public String getAppimg_url_02() {
		return appimg_url_02;
	}

	public void setAppimg_url_02(String appimg_url_02) {
		this.appimg_url_02 = appimg_url_02;
	}

	public String getAppimg_url_03() {
		return appimg_url_03;
	}

	public void setAppimg_url_03(String appimg_url_03) {
		this.appimg_url_03 = appimg_url_03;
	}

	public String getBanner_redirect_url() {
		return banner_redirect_url;
	}

	public void setBanner_redirect_url(String banner_redirect_url) {
		this.banner_redirect_url = banner_redirect_url;
	}

	public Integer getBanner_response_type() {
		return banner_response_type;
	}

	public void setBanner_response_type(Integer banner_response_type) {
		this.banner_response_type = banner_response_type;
	}

	public Integer getBanner_response_category() {
		return banner_response_category;
	}

	public void setBanner_response_category(Integer banner_response_category) {
		this.banner_response_category = banner_response_category;
	}

	public String getChartboost_redirect_url() {
		return chartboost_redirect_url;
	}

	public void setChartboost_redirect_url(String chartboost_redirect_url) {
		this.chartboost_redirect_url = chartboost_redirect_url;
	}

	public Integer getChartboost_response_type() {
		return chartboost_response_type;
	}

	public void setChartboost_response_type(Integer chartboost_response_type) {
		this.chartboost_response_type = chartboost_response_type;
	}

	public Integer getChartboost_response_category() {
		return chartboost_response_category;
	}

	public void setChartboost_response_category(
			Integer chartboost_response_category) {
		this.chartboost_response_category = chartboost_response_category;
	}

	public String getImg_horizontal() {
		return img_horizontal;
	}

	public void setImg_horizontal(String img_horizontal) {
		this.img_horizontal = img_horizontal;
	}

	public String getImg_vertical() {
		return img_vertical;
	}

	public void setImg_vertical(String img_vertical) {
		this.img_vertical = img_vertical;
	}

	public String getBeanner_img_url_first() {
		return beanner_img_url_first;
	}

	public void setBeanner_img_url_first(String beanner_img_url_first) {
		this.beanner_img_url_first = beanner_img_url_first;
	}

	public String getBeanner_img_url_second() {
		return beanner_img_url_second;
	}

	public void setBeanner_img_url_second(String beanner_img_url_second) {
		this.beanner_img_url_second = beanner_img_url_second;
	}

	public String getBeanner_img_url_third() {
		return beanner_img_url_third;
	}

	public void setBeanner_img_url_third(String beanner_img_url_third) {
		this.beanner_img_url_third = beanner_img_url_third;
	}

	public String getWall_score_desc() {
		return wall_score_desc;
	}

	public void setWall_score_desc(String wall_score_desc) {
		this.wall_score_desc = wall_score_desc;
	}

	public String getWall_score_long_desc() {
		return wall_score_long_desc;
	}

	public void setWall_score_long_desc(String wall_score_long_desc) {
		this.wall_score_long_desc = wall_score_long_desc;
	}

	public Integer getBanner_weight() {
		return banner_weight;
	}

	public void setBanner_weight(Integer banner_weight) {
		this.banner_weight = banner_weight;
	}

	public Integer getChartboost_weight() {
		return chartboost_weight;
	}

	public void setChartboost_weight(Integer chartboost_weight) {
		this.chartboost_weight = chartboost_weight;
	}

	public String getWall_score_banner_url() {
		return wall_score_banner_url;
	}

	public void setWall_score_banner_url(String wall_score_banner_url) {
		this.wall_score_banner_url = wall_score_banner_url;
	}

	public Integer getWall_score_response_category() {
		return wall_score_response_category;
	}

	public void setWall_score_response_category(
			Integer wall_score_response_category) {
		this.wall_score_response_category = wall_score_response_category;
	}

	public Integer getWall_score_response_type() {
		return wall_score_response_type;
	}

	public void setWall_score_response_type(Integer wall_score_response_type) {
		this.wall_score_response_type = wall_score_response_type;
	}

	public String getWall_score_redirect_url() {
		return wall_score_redirect_url;
	}

	public void setWall_score_redirect_url(String wall_score_redirect_url) {
		this.wall_score_redirect_url = wall_score_redirect_url;
	}

	public Integer getWall_score_weight() {
		int i = 0;
		if (wall_score_weight != null) {
			i = wall_score_weight;
		} else if (wall_score_max_weight != null) {
			i = wall_score_max_weight;
		}
		return i;
	}

	public void setWall_score_weight(Integer wall_score_weight) {
		this.wall_score_weight = wall_score_weight;
	}

	public String getWall_list_banner_url() {
		return wall_list_banner_url;
	}

	public void setWall_list_banner_url(String wall_list_banner_url) {
		this.wall_list_banner_url = wall_list_banner_url;
	}

	public Integer getWall_list_response_category() {
		return wall_list_response_category;
	}

	public void setWall_list_response_category(
			Integer wall_list_response_category) {
		this.wall_list_response_category = wall_list_response_category;
	}

	public Integer getWall_list_response_type() {
		return wall_list_response_type;
	}

	public void setWall_list_response_type(Integer wall_list_response_type) {
		this.wall_list_response_type = wall_list_response_type;
	}

	public String getWall_list_redirect_url() {
		return wall_list_redirect_url;
	}

	public void setWall_list_redirect_url(String wall_list_redirect_url) {
		this.wall_list_redirect_url = wall_list_redirect_url;
	}

	public Integer getWall_list_weight() {
		return wall_list_weight;
	}

	public void setWall_list_weight(Integer wall_list_weight) {
		this.wall_list_weight = wall_list_weight;
	}

	public Integer getWall_score_max_weight() {
		return wall_score_max_weight;
	}

	public void setWall_score_max_weight(Integer wall_score_max_weight) {
		this.wall_score_max_weight = wall_score_max_weight;
	}

	public Integer getWall_list_max_weight() {
		return wall_list_max_weight;
	}

	public void setWall_list_max_weight(Integer wall_list_max_weight) {
		this.wall_list_max_weight = wall_list_max_weight;
	}

	public Integer getBanner_max_weight() {
		return banner_max_weight;
	}

	public void setBanner_max_weight(Integer banner_max_weight) {
		this.banner_max_weight = banner_max_weight;
	}

	public Integer getChartboost_max_weight() {
		return chartboost_max_weight;
	}

	public void setChartboost_max_weight(Integer chartboost_max_weight) {
		this.chartboost_max_weight = chartboost_max_weight;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Integer getScore_delay() {
		return score_delay;
	}

	public void setScore_delay(Integer score_delay) {
		this.score_delay = score_delay;
	}

	public Integer getWall_score_limit_time() {
		return wall_score_limit_time;
	}

	public void setWall_score_limit_time(Integer wall_score_limit_time) {
		this.wall_score_limit_time = wall_score_limit_time;
	}

	public String getWall_score_weixin_desc() {
		return wall_score_weixin_desc;
	}

	public void setWall_score_weixin_desc(String wall_score_weixin_desc) {
		this.wall_score_weixin_desc = wall_score_weixin_desc;
	}
	
}
