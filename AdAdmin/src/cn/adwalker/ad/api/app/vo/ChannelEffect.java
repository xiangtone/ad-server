/**
 * <p>Title: ChannelEffect.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-9-10
 * @version 1.0
 */
package cn.adwalker.ad.api.app.vo;

import java.io.Serializable;

/**
 * <p>
 * Title: ChannelEffect
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-9-10
 */
public class ChannelEffect implements Serializable {
	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 5665661446810617585L;
	private Long id;
	private String channel_id;
	private String channel_name;
	private String ad_name;
	private String package_file_name;
	private Integer effect;
	private Double cost;
	private Double price;
	private String effect_date;
	private String send_date;

	public String getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}

	public Integer getEffect() {
		return effect;
	}

	public void setEffect(Integer effect) {
		this.effect = effect;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getEffect_date() {
		return effect_date;
	}

	public void setEffect_date(String effect_date) {
		this.effect_date = effect_date;
	}

	public String getSend_date() {
		return send_date;
	}

	public void setSend_date(String send_date) {
		this.send_date = send_date;
	}

	public String getAd_name() {
		return ad_name;
	}

	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}

	public String getPackage_file_name() {
		return package_file_name;
	}

	public void setPackage_file_name(String package_file_name) {
		this.package_file_name = package_file_name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
