package cn.adwalker.ad.dao.domain;

import java.io.Serializable;
import java.util.Date;

public class AndroidDownloadLog implements Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 5630721032135759480L;
	private Long id;
	private String mac_address;
	private String android_id;
	private String adid;
	private String bundleid;
	private String isroot;
	private String imei;
	private String userid;
	private String imsi;
	private String phonenum;
	private Date create_time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMac_address() {
		return mac_address;
	}

	public void setMac_address(String mac_address) {
		this.mac_address = mac_address;
	}

	public String getAndroid_id() {
		return android_id;
	}

	public void setAndroid_id(String android_id) {
		this.android_id = android_id;
	}

	public String getAdid() {
		return adid;
	}

	public void setAdid(String adid) {
		this.adid = adid;
	}

	public String getBundleid() {
		return bundleid;
	}

	public void setBundleid(String bundleid) {
		this.bundleid = bundleid;
	}

	public String getIsroot() {
		return isroot;
	}

	public void setIsroot(String isroot) {
		this.isroot = isroot;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

}
