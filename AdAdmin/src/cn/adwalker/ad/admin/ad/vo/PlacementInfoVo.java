/**
 * <p>Title: PlacementEditVo.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-5-15
 * @version 1.0
 */
package cn.adwalker.ad.admin.ad.vo;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.adwalker.model.ad.domain.Type;

/**
 * <p>
 * Title: PlacementEditVo
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-5-15
 */
public class PlacementInfoVo {
	private Long id;

	// 投放名称
	private String name;

	// 星级
	private Double star_level;

	// 平台类型
	private String os;

	/**
	 * 分类id
	 */
	private Long category_id;

	/**
	 * 广告语
	 */
	private String slogan;

	/**
	 * 关键字
	 */
	private String keyword;
	
	/**
	 * 是否优先推荐
	 */
	private Integer priority;

	/**
	 * 活动介绍
	 */
	private String campaign_desc;

	/**
	 * 广告形式
	 */
	private List<Type> typeList;

	/**
	 * 排期开始时间
	 */
	private Date plan_start;

	/**
	 * 排期结束时间
	 */
	private Date plan_end;

	// 是否热门推荐
	private Integer popular_app;
	
	// 是否是新应用
	private Integer new_app;
	
	/**
	 * 是否是链接
	 */
	private Integer is_url_params;
	
	// 确认方式（0线下、1线上）
	private Integer confirm_mode;
	
	public String getTypeStr() {
		String str = null;
		if (typeList != null && typeList.size() > 0) {
			for (Type type : typeList) {
				if (!StringUtils.isEmpty(str)) {
					str = str + "," + type.getName();
				} else {
					str = type.getName();
				}
			}

		}
		return str;
	}

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

	/**
	 * 权重
	 */
	private Integer wall_score_weight;
	/**
	 * 积分延时
	 */
	private Integer score_delay;

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
	// iOS接口配置（平台ios上线要配置）
	private String config_id;
	
	public void setTypeList(List<Type> typeList) {
		this.typeList = typeList;
	}

	public Date getPlan_start() {
		return plan_start;
	}

	public void setPlan_start(Date plan_start) {
		this.plan_start = plan_start;
	}

	public Date getPlan_end() {
		return plan_end;
	}

	public void setPlan_end(Date plan_end) {
		this.plan_end = plan_end;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getStar_level() {
		return star_level;
	}

	public void setStar_level(Double star_level) {
		this.star_level = star_level;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getCampaign_desc() {
		return campaign_desc;
	}

	public void setCampaign_desc(String campaign_desc) {
		this.campaign_desc = campaign_desc;
	}

	public List<Type> getTypeList() {
		return typeList;
	}

	public String getWall_score_banner_url() {
		return wall_score_banner_url;
	}

	public void setWall_score_banner_url(String wall_score_banner_url) {
		this.wall_score_banner_url = wall_score_banner_url;
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
		return wall_score_weight;
	}

	public void setWall_score_weight(Integer wall_score_weight) {
		this.wall_score_weight = wall_score_weight;
	}

	public Integer getWall_score_max_weight() {
		return wall_score_max_weight;
	}

	public void setWall_score_max_weight(Integer wall_score_max_weight) {
		this.wall_score_max_weight = wall_score_max_weight;
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

	public Integer getBanner_weight() {
		return banner_weight;
	}

	public void setBanner_weight(Integer banner_weight) {
		this.banner_weight = banner_weight;
	}

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

	public void setChartboost_response_category(Integer chartboost_response_category) {
		this.chartboost_response_category = chartboost_response_category;
	}

	public Integer getChartboost_weight() {
		return chartboost_weight;
	}

	public void setChartboost_weight(Integer chartboost_weight) {
		this.chartboost_weight = chartboost_weight;
	}

	public Integer getChartboost_max_weight() {
		return chartboost_max_weight;
	}

	public void setChartboost_max_weight(Integer chartboost_max_weight) {
		this.chartboost_max_weight = chartboost_max_weight;
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

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getConfig_id() {
		return config_id;
	}

	public void setConfig_id(String config_id) {
		this.config_id = config_id;
	}

	public Integer getNew_app() {
		return new_app;
	}

	public void setNew_app(Integer new_app) {
		this.new_app = new_app;
	}

	public Integer getScore_delay() {
		return score_delay;
	}

	public void setScore_delay(Integer score_delay) {
		this.score_delay = score_delay;
	}

	public Integer getPopular_app() {
		return popular_app;
	}

	public void setPopular_app(Integer popular_app) {
		this.popular_app = popular_app;
	}

	public Integer getIs_url_params() {
		return is_url_params;
	}

	public void setIs_url_params(Integer is_url_params) {
		this.is_url_params = is_url_params;
	}

	public Integer getConfirm_mode() {
		return confirm_mode;
	}

	public void setConfirm_mode(Integer confirm_mode) {
		this.confirm_mode = confirm_mode;
	}
	
	
	
}
