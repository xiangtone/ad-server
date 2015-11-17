package cn.adwalker.ad.admin.finance.vo;

import java.util.Date;

public class DevListAwaedEnterBeforeVo {
	private Long dev_id; // 网站主id
	private String dev_email; // 网站主名称
	private Double award_money; // 奖励金额
	private Date create_time; // 日期

	public Long getDev_id() {
		return dev_id;
	}

	public void setDev_id(Long dev_id) {
		this.dev_id = dev_id;
	}

	public String getDev_email() {
		return dev_email;
	}

	public void setDev_email(String dev_email) {
		this.dev_email = dev_email;
	}

	public Double getAward_money() {
		return award_money;
	}

	public void setAward_money(Double award_money) {
		this.award_money = award_money;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}
