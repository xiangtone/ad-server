
package cn.adwalker.ad.bean;

import java.io.Serializable;
import java.util.Date;

import cn.adwalker.ad.logger.DevAddScoreLogger;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.DateUtil;


/**
 * 
* <p>Title: DevaddScoreBean</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-1-8
 */

public class DevaddScoreBean implements Serializable {
	
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 289025492197729797L;
	private Long Id;	
	private String uuid;
	private Double score;
	private Double price;
	private Long ad_id;
	private String page_Type;// 广告墙形式
	private String channel;// 渠道
	private String dateString;
	private String sdkversion;
	private String categoryid;
	private String  pay_type;
	private String area;
	private String ip;
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getAd_id() {
		return ad_id;
	}

	public void setAd_id(Long ad_id) {
		this.ad_id = ad_id;
	}

	public String getPage_Type() {
		return page_Type;
	}

	public void setPage_Type(String page_Type) {
		this.page_Type = page_Type;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getSdkversion() {
		return sdkversion;
	}

	public void setSdkversion(String sdkversion) {
		this.sdkversion = sdkversion;
	}

	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	public String getCreate_time() {
		 dateString = DateUtil.getDateStringByPattern(new Date(),DateUtil.PARTTERN_DATE_TIME);
		return dateString;
	}
	
	
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void devAddScorelogInfo(int signNum) {
		
		//签到不给钱
		if(signNum>0){
			price = 0.00;
		}
		
		String s[] = {this.getCreate_time(),String.valueOf(Id),page_Type,channel,String.valueOf(ad_id),categoryid,pay_type,pay_type.equals(AppConstant.AD_CPA)?String.valueOf(price):"0",uuid, area,sdkversion,String.valueOf(score),ip,String.valueOf(signNum)};
		DevAddScoreLogger logger = new DevAddScoreLogger();
			logger.log(s);
	}


	
}
