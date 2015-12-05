package cn.adwalker.ad.admin.channel.bean;
/**
* <p>Title: SearchChannelBean</p>
* <p>Description:渠道bean</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-4-11
 */
public class SearchChannelBean {
	/** 主键ID */
	private Long id;
	/** 用户名（电子邮箱） */
	private String channel_emails;
	/** 渠道名 */
	private String channel_names;
	/** 渠道联系人 */
	private String real_names;	
	/** 平台类型 */
	private String os;	
	/** 渠道负责人 */
	private String channe_managers;
	/** 合作方式 */
	private Integer channe_modes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getChannel_emails() {
		return channel_emails;
	}
	public void setChannel_emails(String channel_emails) {
		this.channel_emails = channel_emails;
	}
	public String getChannel_names() {
		return channel_names;
	}
	public void setChannel_names(String channel_names) {
		this.channel_names = channel_names;
	}
	public String getReal_names() {
		return real_names;
	}
	public void setReal_names(String real_names) {
		this.real_names = real_names;
	}
	public String getChanne_managers() {
		return channe_managers;
	}
	public void setChanne_managers(String channe_managers) {
		this.channe_managers = channe_managers;
	}
	public Integer getChanne_modes() {
		return channe_modes;
	}
	public void setChanne_modes(Integer channe_modes) {
		this.channe_modes = channe_modes;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	

}
