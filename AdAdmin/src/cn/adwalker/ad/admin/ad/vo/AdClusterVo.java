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
public class AdClusterVo {
	// 广告id
	private Long adId;
	
	private String res_code;
	
	private Double blance_price;
	
	private Integer media_status;
	
	
	private Double income_price;
	
	// 广告名称
	private String ad_name;

	// 平台类型
	private String os;
	// 广告形式
	private Long type_id;
	//
	private String blance_mode;

	// 接入时间
	private Date create_time;

	// 媒体类型
	private Integer ad_type;

	// t投放状态（0:上线1:人工下线,-10:推广结束,-20:超量下线）
	private String status;

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Long getType_id() {
		return type_id;
	}

	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}

	public String getBlance_mode() {
		return blance_mode;
	}

	public void setBlance_mode(String blance_mode) {
		this.blance_mode = blance_mode;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAd_name() {
		return ad_name;
	}

	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}

	public Integer getAd_type() {
		return ad_type;
	}

	public void setAd_type(Integer ad_type) {
		this.ad_type = ad_type;
	}

	public String getRes_code() {
		return res_code;
	}

	public void setRes_code(String res_code) {
		this.res_code = res_code;
	}

	public Double getBlance_price() {
		return blance_price;
	}

	public void setBlance_price(Double blance_price) {
		this.blance_price = blance_price;
	}

	public Double getIncome_price() {
		return income_price;
	}

	public void setIncome_price(Double income_price) {
		this.income_price = income_price;
	}

	public Integer getMedia_status() {
		return media_status;
	}

	public void setMedia_status(Integer media_status) {
		this.media_status = media_status;
	}
}
