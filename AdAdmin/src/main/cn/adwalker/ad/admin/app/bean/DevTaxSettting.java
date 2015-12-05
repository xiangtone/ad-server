package cn.adwalker.ad.admin.app.bean;

/**
 * 功能概述：<br>
 * 开发者免税设置
 * 
 * @author nemo
 */
public class DevTaxSettting {

	private Long dev_id;

	private String dev_email;

	private String dev_name;
	
	private Integer tax_status;
	
	private String startTime;

	private String endTime;
    

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public String getDev_email() {
		return dev_email;
	}

	public void setDev_email(String dev_email) {
		this.dev_email = dev_email;
	}


	public Long getDev_id() {
		return dev_id;
	}

	public void setDev_id(Long dev_id) {
		this.dev_id = dev_id;
	}

	public String getDev_name() {
		return dev_name;
	}

	public void setDev_name(String dev_name) {
		this.dev_name = dev_name;
	}

	public Integer getTax_status() {
		return tax_status;
	}

	public void setTax_status(Integer tax_status) {
		this.tax_status = tax_status;
	}
}
