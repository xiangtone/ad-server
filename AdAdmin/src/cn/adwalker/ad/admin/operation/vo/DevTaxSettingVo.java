package cn.adwalker.ad.admin.operation.vo;


/**
 * 功能描述：<br>
 * 开发者免税审核实体
 * 
 * @author nemo
 */
public class DevTaxSettingVo {

	/** 开发者ID */
	private Long dev_id;

	private String dev_email;
	
	/**
	 * 开发者姓名
	 */
	private String dev_name;

	/**扣税状态(1:正常 2:免税) */
	private Integer tax_status;
	
	

	public Long getDev_id() {
		return dev_id;
	}

	public void setDev_id(Long dev_id) {
		this.dev_id = dev_id;
	}

	public Integer getTax_status() {
		return tax_status;
	}

	public void setTax_status(Integer tax_status) {
		this.tax_status = tax_status;
	}

	public String getDev_email() {
		return dev_email;
	}

	public void setDev_email(String dev_email) {
		this.dev_email = dev_email;
	}

	public String getDev_name() {
		return dev_name;
	}

	public void setDev_name(String dev_name) {
		this.dev_name = dev_name;
	}

}
