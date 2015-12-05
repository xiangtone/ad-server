/**
 * <p>Title: ChannelQueryBean.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author hadoop
 * @date 2013-10-31
 * @version 1.0
 */
package cn.adwalker.ad.admin.finance.bean;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * <p>
 * Title: ChannelQueryBean
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-10-31
 */
public class ChannelQueryBean implements Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = -7932417613771795980L;

	private String start_date;
	private String end_date;
	private String media;
	private String os;

	private String campaign;

	public Long getMedia_id() {
		Long l = 0l;
		if (!StringUtils.isEmpty(media) && NumberUtils.isNumber(media)) {
			l = Long.valueOf(media);
		}
		return l;
	}

	public Long getCampaign_id() {
		Long l = 0l;
		if (!StringUtils.isEmpty(campaign) && NumberUtils.isNumber(campaign)) {
			l = Long.valueOf(campaign);
		}
		return l;
	}

	public String getMedia_name() {
		String media_name = null;
		if (!StringUtils.isEmpty(media)) {
			media_name = media.trim();
		}
		return media_name;
	}

	public String getCampaign_name() {
		String name = null;
		if (!StringUtils.isEmpty(campaign)) {
			name = campaign.trim();
		}
		return name;
	}

	public String getCampaign() {
		return campaign;
	}

	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}
}
