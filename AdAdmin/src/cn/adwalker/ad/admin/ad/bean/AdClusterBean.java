package cn.adwalker.ad.admin.ad.bean;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * 
* <p>Title: AdAjustmentBean</p>
* <p>Description:广告管理bean</p>
* <p>Company: adwalker</p> 
* @author    cuidd
* @date       2014-3-27
 */
public class AdClusterBean {

	// 活动id
	private String ad;

	// 广告形式
	private Long type_id;
	// 平台类型
	private String os;
	
	// 结算方式
	private String blanceMode;
	// 投放状态
	private Integer status;
	
	private String res_code;
	
	private Integer res_status;
	

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

	public String getBlanceMode() {
		return blanceMode;
	}

	public void setBlanceMode(String blanceMode) {
		this.blanceMode = blanceMode;
	}

	public Long getAd_id() {
		Long l = 0l;
		if (!StringUtils.isEmpty(ad) && NumberUtils.isNumber(ad)) {
			l = Long.valueOf(ad);
		}
		return l;
	}
	
	public String getAd_name() {
		String s = null;
		if (!StringUtils.isEmpty(ad)) {
			s = ad;
		}
		return s;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getRes_code() {
		return res_code;
	}

	public void setRes_code(String res_code) {
		this.res_code = res_code;
	}

	public Integer getRes_status() {
		return res_status;
	}

	public void setRes_status(Integer res_status) {
		this.res_status = res_status;
	}
}
