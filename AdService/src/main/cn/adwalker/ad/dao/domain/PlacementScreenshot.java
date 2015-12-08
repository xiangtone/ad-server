/**
* <p>Title: CampaignScreenshot.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-4-16
* @version 1.0
*/
package cn.adwalker.ad.dao.domain;

import cn.adwalker.ad.bean.Data;

/**
 * <p>Title: CampaignScreenshot</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn 
 * @date       2013-4-16
 */
public class PlacementScreenshot extends Data{

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 4548554307740357085L;

	private long id;
	private long campaign_id;
	private String img_url;
	private int sort;
	private int del;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCampaign_id() {
		return campaign_id;
	}
	public void setCampaign_id(long campaign_id) {
		this.campaign_id = campaign_id;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getDel() {
		return del;
	}
	public void setDel(int del) {
		this.del = del;
	}
}
