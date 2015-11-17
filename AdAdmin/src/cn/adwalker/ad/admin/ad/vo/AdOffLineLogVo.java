package cn.adwalker.ad.admin.ad.vo;

import java.util.Date;

/**
 * <p>
 * Title: AdByPlacementVo
 * </p>
 * <p>
 * Description:TODO 
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-4
 */
public class AdOffLineLogVo {
	// 广告id
	private Long ad_id;
	// 广告主id
	private Long adv_id;
	
	
	// 广告主邮箱
	private String adv_name;
	// 活动id
	private Long campaign_id;
	// 活动名称
	private String campaign_name;

	// 平台类型
	private String os;
	// 广告形式
	private Long type_id;
	//
	private String blanceMode;

	// 上线时间
	private Date online_time;
	// 下线时间
	private Date offline_time;
	
	// t投放状态（0:上线1:人工下线,-10:推广结束,-20:超量下线）
	private String status;
	
	private Integer offline_type;
	
	private String budget_type;
	private Integer budget_day;
	
	private Integer pv;
	
	private Integer click;
	private Integer download;
	
	private Integer activate;
	
	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	

	public String getBlanceMode() {
		return blanceMode;
	}

	public void setBlanceMode(String blanceMode) {
		this.blanceMode = blanceMode;
	}

	
	public String getAdv_name() {
		return adv_name;
	}

	public void setAdv_name(String adv_name) {
		this.adv_name = adv_name;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}

	public String getCampaign_name() {
		return campaign_name;
	}

	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}

	public Long getAd_id() {
		return ad_id;
	}

	public void setAd_id(Long ad_id) {
		this.ad_id = ad_id;
	}

	public Long getAdv_id() {
		return adv_id;
	}

	public void setAdv_id(Long adv_id) {
		this.adv_id = adv_id;
	}

	public Long getType_id() {
		return type_id;
	}

	public void setType_id(Long type_id) {
		this.type_id = type_id;
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

	public Integer getPv() {
		return pv;
	}

	public void setPv(Integer pv) {
		this.pv = pv;
	}

	public Integer getClick() {
		return click;
	}

	public void setClick(Integer click) {
		this.click = click;
	}

	public Integer getDownload() {
		return download;
	}

	public void setDownload(Integer download) {
		this.download = download;
	}

	public Integer getActivate() {
		return activate;
	}

	public void setActivate(Integer activate) {
		this.activate = activate;
	}

	public void setOffline_time(Date offline_time) {
		this.offline_time = offline_time;
	}

	public Integer getOffline_type() {
		return offline_type;
	}

	public void setOffline_type(Integer offline_type) {
		this.offline_type = offline_type;
	}

	public String getBudget_type() {
		return budget_type;
	}

	public void setBudget_type(String budget_type) {
		this.budget_type = budget_type;
	}

	public Integer getBudget_day() {
		return budget_day;
	}

	public void setBudget_day(Integer budget_day) {
		this.budget_day = budget_day;
	}
}
