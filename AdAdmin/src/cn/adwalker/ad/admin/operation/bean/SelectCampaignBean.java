/**
 * <p>Title: SelectCampaignBean.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author hadoop
 * @date 2013-11-22
 * @version 1.0
 */
package cn.adwalker.ad.admin.operation.bean;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * <p>
 * Title: SelectCampaignBean
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-11-22
 */
public class SelectCampaignBean implements Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 7276630460131404430L;

	private String campaign;

	private String os;

	public Long getCampaign_id() {
		Long l = 0l;
		if (!StringUtils.isEmpty(campaign) && NumberUtils.isNumber(campaign)) {
			l = Long.valueOf(campaign);
		}
		return l;
	}

	public String getCampaign_name() {
		String name = null;
		if (!StringUtils.isEmpty(campaign)) {
			name = campaign.trim();
		}
		return name;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getCampaign() {
		return campaign;
	}

	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}

}
