package cn.adwalker.ad.admin.channel.vo;
/**
* <p>Title: ChannelListVo</p>
* <p>Description:渠道list</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-4-11
 */
public class ChannelListVo {
	/** 主键ID */
	private Long id;
	/** 用户名（电子邮箱） */
	private String email;
	/** 帐号状态 (1:激活  2:封号)*/
	private Integer status;
	/** 渠道名 */
	private String name;
	/** 渠道联系人 */
	private String real_name;
	/** 渠道负责人 */
	private String channe_manager;
	/**平台类型*/
	private String os;
	/** 合作方式 */
	private Integer channe_mode;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getChanne_manager() {
		return channe_manager;
	}
	public void setChanne_manager(String channe_manager) {
		this.channe_manager = channe_manager;
	}
	public Integer getChanne_mode() {
		return channe_mode;
	}
	public void setChanne_mode(Integer channe_mode) {
		this.channe_mode = channe_mode;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	
	
}
