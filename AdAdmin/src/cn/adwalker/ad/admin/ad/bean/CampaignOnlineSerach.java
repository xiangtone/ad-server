package cn.adwalker.ad.admin.ad.bean;


/**
* <p>Title: CampaignOnlineSerach</p>
* <p>Description:在线活动搜索bean</p>
* <p>Company: emar</p> 
* @author   mandy
* @date       2014-3-4
 */
public class CampaignOnlineSerach {
	/** 广告主名称 */
	private String adName;
	/** 活动名称 */
	private String campaignName;
	//操作系统
	private String os;
	//计费方式
	private String blance_mode;
	//上线时间
	private String online_time;
	
	public String getAdName() {
		return adName;
	}
	public void setAdName(String adName) {
		this.adName = adName;
	}
	public String getCampaignName() {
		return campaignName;
	}
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getBlance_mode() {
		return blance_mode;
	}
	public void setBlance_mode(String blance_mode) {
		this.blance_mode = blance_mode;
	}
	public String getOnline_time() {
		return online_time;
	}
	public void setOnline_time(String online_time) {
		this.online_time = online_time;
	}
	
	
}
