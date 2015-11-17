package cn.adwalker.ad.admin.finance.vo;

import java.util.Date;

/**
 * WAP合作信息
 * @author Administrator
 * 
 */
/**
 * @author Administrator
 *
 */
public class ChannelAccountmannageVo {
	
	private Long id;
	//状态
	private Integer status;
	//创建时间
	private Date create_time;
	//渠道账号
	private String email;
	//渠道账号密码
	private String password;
	//渠道账号确认密码
	private String password1;
	//渠道名
	private String channel_name;
	//渠道id
	private String channel_id;
	//联系人
	private String real_name;
	//手机号
	private String phone;	
	//分成比例
	private Double into_Rate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getChannel_name() {
		return channel_name;
	}
	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Double getInto_Rate() {
		return into_Rate;
	}
	public void setInto_Rate(Double into_Rate) {
		this.into_Rate = into_Rate;
	}

	
}
