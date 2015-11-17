package cn.adwalker.ad.admin.ad.bean;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * <p>
 * Title: AdByPlacementBean
 * </p>
 * <p>
 * Description:广告管理bean  
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-4
 */
public class AdChannelBean {

	/**
	 * 广告id
	 */
	private String id;

	// 活动id
	private String campaign;
	// 媒体名称
	private String media;
	// 广告形式
	private Long type_id;
	// 平台类型
	private String os;
	// 广告主邮箱
	private String adv;
	// 结算方式
	private String blanceMode;
	// 投放状态
	private Integer status;
	// 媒体类型 
	private Integer ad_type;

	public Long getAd_id() {
		Long l = null;
		if (!StringUtils.isEmpty(id)) {
			l = Long.valueOf(id.trim());
		}
		return l;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public Long getType_id() {
		return type_id;
	}

	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getAdv_email() {
		String s = null;
		if (!StringUtils.isEmpty(adv)) {
			s = adv.trim();
		}
		return s;
	}

	public Long getAdv_id() {
		Long l = 0l;
		if (!StringUtils.isEmpty(adv) && NumberUtils.isNumber(adv)) {
			l = Long.valueOf(adv);
		}
		return l;
	}

	public String getBlanceMode() {
		return blanceMode;
	}

	public void setBlanceMode(String blanceMode) {
		this.blanceMode = blanceMode;
	}

	public Long getCampaign_id() {
		Long l = 0l;
		if (!StringUtils.isEmpty(campaign) && NumberUtils.isNumber(campaign)) {
			l = Long.valueOf(campaign);
		}
		return l;
	}

	public String getMedia_name() {
		String media_name = null;
		if (!StringUtils.isEmpty(media)) {
			media_name = media.trim();
		}
		return media_name;

	}

	public long getMedia_id() {
		Long media_id = 0l;
		if (!StringUtils.isEmpty(media) && NumberUtils.isNumber(media)) {
			media_id = Long.valueOf(media);
		}
		return media_id;
	}

	public String getCampaign_name() {
		String s = null;
		if (!StringUtils.isEmpty(campaign)) {
			s = campaign;
		}
		return s;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAd_type() {
		return ad_type;
	}

	public void setAd_type(Integer ad_type) {
		this.ad_type = ad_type;
	}

	public String getCampaign() {
		return campaign;
	}

	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}

	public String getAdv() {
		return adv;
	}

	public void setAdv(String adv) {
		this.adv = adv;
	}
}
