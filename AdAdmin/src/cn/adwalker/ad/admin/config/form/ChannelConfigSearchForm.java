package cn.adwalker.ad.admin.config.form;
/**
 * 渠道配置查询表单bean
 * @author jief
 *
 */
public class ChannelConfigSearchForm {

	private String channel;
	private String url;
	private String channel_name;
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getChannel_name() {
		return channel_name;
	}
	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}
	
}
