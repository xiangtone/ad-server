package cn.adwalker.ad.dao.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* <p>Title: AdCluster</p>
* <p>Description:外接广告</p>
* <p>Company: adwalker</p> 
* @author    cuidd
* @date       2014年10月16日
 */
public class AdCluster implements Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = -7997063931582652477L;

	private Long id;// 主键
	private String ad_code;// 广告编号--存储对方广告id
	private String ad_name;// 广告名称
	private String ad_slogan;// 广告语
	private String ad_content;// 广告内容
	private String task_desc;// 积分描述
	private String icon_url;// icon地址
	private String click_url;// 点击跳转地址
	private String store_url;
	private Double blance_price; // 结算单价
	private String store_id;// 应用商店id
	private String res_code;// 来源
	private Integer type_id; // 投放形式
	private Date create_time;// 创建时间
	private String device_mark;// 跳转url之后的参数
	private String allow_device;// 可以投放的设备列表iphoneipadipod空则表示无限制
	private String allow_network;// 投放的网络类型限制取值有wifi3g2g空则表示无限制
	private String media_status;// 状态0正常1删除

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAd_code() {
		return ad_code;
	}

	public void setAd_code(String ad_code) {
		this.ad_code = ad_code;
	}

	public String getAd_name() {
		return ad_name;
	}

	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}

	public String getAd_slogan() {
		return ad_slogan;
	}

	public void setAd_slogan(String ad_slogan) {
		this.ad_slogan = ad_slogan;
	}

	public String getAd_content() {
		return ad_content;
	}

	public void setAd_content(String ad_content) {
		this.ad_content = ad_content;
	}

	public String getTask_desc() {
		return task_desc;
	}

	public void setTask_desc(String task_desc) {
		this.task_desc = task_desc;
	}

	public String getIcon_url() {
		return icon_url;
	}

	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}

	public String getClick_url() {
		return click_url;
	}

	public void setClick_url(String click_url) {
		this.click_url = click_url;
	}

	public Double getBlance_price() {
		return blance_price;
	}

	public void setBlance_price(Double blance_price) {
		this.blance_price = blance_price;
	}

	public String getStore_id() {
		return store_id;
	}

	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	public String getRes_code() {
		return res_code;
	}

	public void setRes_code(String res_code) {
		this.res_code = res_code;
	}

	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getDevice_mark() {
		return device_mark;
	}

	public void setDevice_mark(String device_mark) {
		this.device_mark = device_mark;
	}

	public String getAllow_device() {
		return allow_device;
	}

	public void setAllow_device(String allow_device) {
		this.allow_device = allow_device;
	}

	public String getAllow_network() {
		return allow_network;
	}

	public void setAllow_network(String allow_network) {
		this.allow_network = allow_network;
	}

	public String getMedia_status() {
		return media_status;
	}

	public void setMedia_status(String media_status) {
		this.media_status = media_status;
	}

	public String getStore_url() {
		return store_url;
	}

	public void setStore_url(String store_url) {
		this.store_url = store_url;
	}
}