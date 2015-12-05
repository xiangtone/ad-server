package cn.adwalker.ad.admin.channel.bean;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * 
 * <p>
 * Title: ChannelScaleBean
 * </p>
 * <p>
 * Description:渠道指数查询bean
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-7-22
 */
public class ChannelScaleBean {

	/**
	 * 渠道
	 */
	private String channel;

	/**
	 * 活动
	 */
	private String campaign;
	
	private String os;

	/**
	 * 
	 * <p>
	 * Title: getChannel_id
	 * </p>
	 * <p>
	 * Description:渠道id
	 * </p>
	 * 
	 * @return
	 * @author cuidd
	 * @date 2013-7-22
	 * @return Long
	 * @version 1.0
	 */
	public Long getChannel_id() {
		Long l = null;
		if (!StringUtils.isEmpty(channel) && NumberUtils.isNumber(channel)) {
			l = Long.valueOf(channel.trim());
		}
		return l;
	}

	/**
	 * 
	 * <p>
	 * Title: getChannel_name
	 * </p>
	 * <p>
	 * Description:渠道名称
	 * </p>
	 * 
	 * @return
	 * @author cuidd
	 * @date 2013-7-22
	 * @return String
	 * @version 1.0
	 */
	public String getChannel_name() {
		String s = null;
		if (!StringUtils.isEmpty(channel)) {
			s = channel.trim();
		}
		return s;
	}

	/**
	 * 
	 * <p>
	 * Title: getCampaign_id
	 * </p>
	 * <p>
	 * Description:活动ID
	 * </p>
	 * 
	 * @return
	 * @author cuidd
	 * @date 2013-7-22
	 * @return Long
	 * @version 1.0
	 */
	public Long getCampaign_id() {
		Long l = 0l;
		if (!StringUtils.isEmpty(campaign) && NumberUtils.isNumber(campaign)) {
			l = Long.valueOf(campaign.trim());
		}
		return l;
	}

	/**
	 * 
	 * <p>
	 * Title: getCampaign_name
	 * </p>
	 * <p>
	 * Description:活动名称
	 * </p>
	 * 
	 * @return
	 * @author cuidd
	 * @date 2013-7-22
	 * @return String
	 * @version 1.0
	 */
	public String getCampaign_name() {
		String s = null;
		if (!StringUtils.isEmpty(campaign)) {
			s = campaign.trim();
		}
		return s;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getCampaign() {
		return campaign;
	}

	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}
}
