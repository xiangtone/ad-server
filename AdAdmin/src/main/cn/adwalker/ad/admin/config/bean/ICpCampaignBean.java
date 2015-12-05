package cn.adwalker.ad.admin.config.bean;
/**
 * 广告主接口配置查询bean
 * @author jief
 *
 */
public class ICpCampaignBean {
	
	 private String ad_key;			//广告主提供的adid
	  private String ad_name;		//广告名称
	public String getAd_key() {
		return ad_key;
	}
	public void setAd_key(String ad_key) {
		this.ad_key = ad_key;
	}
	public String getAd_name() {
		return ad_name;
	}
	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}

}
