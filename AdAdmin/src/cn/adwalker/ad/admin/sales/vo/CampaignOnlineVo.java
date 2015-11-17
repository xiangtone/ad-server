package cn.adwalker.ad.admin.sales.vo;
import java.io.Serializable;

/**
 * 在线活动VO
 * <p>
 * Title: CampaignOnlineVo
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: emar
 * </p>
 * 
 * @author mandy
 * @date 2014-3-4
 */
public class CampaignOnlineVo implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	
	
	private static final long serialVersionUID = -1202586087319265621L;
	
	// 广告主
	private String adv_name;

	// 活动名称
	private String campaign_name;

	// 计费方式
	private String blance_mode;
	// 上线时间
	private String online_time;
	
	// 平台类型
	private String os;
	
	/**
	 * 状态
	 */
	private String status_name;
	
	public String getAdv_name() {
		return adv_name;
	}
	public void setAdv_name(String adv_name) {
		this.adv_name = adv_name;
	}
	public String getCampaign_name() {
		return campaign_name;
	}
	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}
	public String getBlance_mode() {
		return blance_mode;
	}
	public void setBlance_mode(String blance_mode) {
		this.blance_mode = blance_mode;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	public String getOnline_time() {
		return online_time;
	}
	public void setOnline_time(String online_time) {
		this.online_time = online_time;
	}
	
	
}
