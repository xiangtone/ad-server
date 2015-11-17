package cn.adwalker.ad.control.vo;

import java.io.Serializable;

public class IosStaticNumVo implements Serializable {
	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 4697375895531308612L;
	private Long ad_id;
	private Integer num;

	public Long getAd_id() {
		return ad_id;
	}

	public void setAd_id(Long ad_id) {
		this.ad_id = ad_id;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
}
