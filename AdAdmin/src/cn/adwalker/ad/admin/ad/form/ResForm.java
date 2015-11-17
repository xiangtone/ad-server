/**
 * <p>Title: ResForm.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-4-17
 * @version 1.0
 */
package cn.adwalker.ad.admin.ad.form;

/**
 * <p>
 * Title: ResForm
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-4-17
 */
public class ResForm {

	/**
	 * 
	 */
	private String iconimg_url;

	private String appimg_url[];

	private String wall_score_banner_url;

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
	
	private Integer wall_score_limit_time;
	
	

	/**
	 * 权重
	 */
	private Integer wall_score_weight;
	/**
	 * 积分延时
	 */
	private Integer score_delay;

	private String wall_score_desc;
	private String wall_score_long_desc;

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

	/**
	 * banner响应分类
	 */
	private Integer banner_response_category;

	/**
	 * 响应方式
	 */
	private Integer banner_response_type;

	// 跳转地址
	private String banner_redirect_url;

	private String banner01_url;
	private String banner02_url;
	private String banner03_url;

	private Integer banner_weight;

	/**
	 * banner响应分类
	 */
	private Integer chartboost_response_catelog;

	/**
	 * 响应方式
	 */
	private Integer chartboost_response_type;

	// 跳转地址
	private String chartboost_redirect_url;
	private String chartboost_x_url;
	private String chartboost_y_url;

	private Integer chartboost_weight;

	public String[] getAppimg_url() {
		return appimg_url;
	}

	public void setAppimg_url(String[] appimg_url) {
		this.appimg_url = appimg_url;
	}

	public String getIconimg_url() {
		return iconimg_url;
	}

	public void setIconimg_url(String iconimg_url) {
		this.iconimg_url = iconimg_url;
	}

	public String getBanner01_url() {
		return banner01_url;
	}

	public void setBanner01_url(String banner01_url) {
		this.banner01_url = banner01_url;
	}

	public String getBanner02_url() {
		return banner02_url;
	}

	public void setBanner02_url(String banner02_url) {
		this.banner02_url = banner02_url;
	}

	public String getBanner03_url() {
		return banner03_url;
	}

	public void setBanner03_url(String banner03_url) {
		this.banner03_url = banner03_url;
	}

	public String getChartboost_x_url() {
		return chartboost_x_url;
	}

	public void setChartboost_x_url(String chartboost_x_url) {
		this.chartboost_x_url = chartboost_x_url;
	}

	public String getChartboost_y_url() {
		return chartboost_y_url;
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

	public void setWall_score_response_category(Integer wall_score_response_category) {
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

	public void setChartboost_y_url(String chartboost_y_url) {
		this.chartboost_y_url = chartboost_y_url;
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

	public Integer getBanner_response_category() {
		return banner_response_category;
	}

	public void setBanner_response_category(Integer banner_response_category) {
		this.banner_response_category = banner_response_category;
	}

	public Integer getBanner_response_type() {
		return banner_response_type;
	}

	public void setBanner_response_type(Integer banner_response_type) {
		this.banner_response_type = banner_response_type;
	}

	public String getBanner_redirect_url() {
		return banner_redirect_url;
	}

	public void setBanner_redirect_url(String banner_redirect_url) {
		this.banner_redirect_url = banner_redirect_url;
	}

	public Integer getChartboost_response_catelog() {
		return chartboost_response_catelog;
	}

	public void setChartboost_response_catelog(
			Integer chartboost_response_catelog) {
		this.chartboost_response_catelog = chartboost_response_catelog;
	}

	public Integer getChartboost_response_type() {
		return chartboost_response_type;
	}

	public void setChartboost_response_type(Integer chartboost_response_type) {
		this.chartboost_response_type = chartboost_response_type;
	}

	public String getChartboost_redirect_url() {
		return chartboost_redirect_url;
	}

	public void setChartboost_redirect_url(String chartboost_redirect_url) {
		this.chartboost_redirect_url = chartboost_redirect_url;
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

	public Integer getWall_score_weight() {
		return wall_score_weight;
	}

	public void setWall_score_weight(Integer wall_score_weight) {
		this.wall_score_weight = wall_score_weight;
	}

	public Integer getScore_delay() {
		return score_delay;
	}

	public void setScore_delay(Integer score_delay) {
		this.score_delay = score_delay;
	}

	public String getWall_score_weixin_desc() {
		return wall_score_weixin_desc;
	}

	public void setWall_score_weixin_desc(String wall_score_weixin_desc) {
		this.wall_score_weixin_desc = wall_score_weixin_desc;
	}

	public Integer getWall_score_limit_time() {
		return wall_score_limit_time;
	}

	public void setWall_score_limit_time(Integer wall_score_limit_time) {
		this.wall_score_limit_time = wall_score_limit_time;
	}
}
