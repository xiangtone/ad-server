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
public class AdByPlacementBean {
	
	private Long placement_id;

	// 媒体id
	private Long appID;
	// 媒体名称
	private String media;
	// 广告形式
	private Long type_id;

	// 媒体类型
	private String blanceMode;
	// 投放状态
	private Integer status;
	// 结算方式
	private Integer ad_type;

	public Long getAppID() {
		return appID;
	}

	public void setAppID(Long appID) {
		this.appID = appID;
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

	public String getBlanceMode() {
		return blanceMode;
	}

	public void setBlanceMode(String blanceMode) {
		this.blanceMode = blanceMode;
	}

	public String getMedia_name() {
		String media_name = null;
		if (!StringUtils.isEmpty(media)) {
			media_name = media.trim();
		}
		return media_name;

	}

	public Long getPlacement_id() {
		return placement_id;
	}

	public void setPlacement_id(Long placement_id) {
		this.placement_id = placement_id;
	}

	public long getMedia_id() {
		Long media_id = 0l;
		if (!StringUtils.isEmpty(media) && NumberUtils.isNumber(media)) {
			media_id = Long.valueOf(media);
		}
		return media_id;
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
}
