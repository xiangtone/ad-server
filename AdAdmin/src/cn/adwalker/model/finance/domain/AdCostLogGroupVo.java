/**
 * 
 */
package cn.adwalker.model.finance.domain;

/**
 * @author wjp
 * 
 */
public class AdCostLogGroupVo {

	/** 广告id */
	private Long adId;
	
	/** 广告主id */
	private Long advertiserId;
	
	/** 价格 */
	private double price;
	
	/** 统计时间 */
	private String statDate;
	
	/** 统计数量 */
	private int syscount;

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public Long getAdvertiserId() {
		return advertiserId;
	}

	public void setAdvertiserId(Long advertiserId) {
		this.advertiserId = advertiserId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getStatDate() {
		return statDate;
	}

	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}

	public int getSyscount() {
		return syscount;
	}

	public void setSyscount(int syscount) {
		this.syscount = syscount;
	}

	
}
