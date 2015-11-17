package cn.adwalker.ad.admin.operation.form;

/**
* <p>Title: DspInfoVo</p>
* <p>Description:dspVo</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-12-3
 */
public class DspInfoForm {
	//主键
	private Long dsp_id;
	//dsp名称
	private String dsp_name;
	//dsp请求路径
	private String dsp_url;
	//备注
	private String dsp_desc;
	//加密因子
	private String dsp_key;
	//额外的固定参数值
	private String source_str;
	//启动的服务名称
	private String service;
	//名称
	private String bean;
	
	public Long getDsp_id() {
		return dsp_id;
	}
	public void setDsp_id(Long dsp_id) {
		this.dsp_id = dsp_id;
	}
	public String getDsp_name() {
		return dsp_name;
	}
	public void setDsp_name(String dsp_name) {
		this.dsp_name = dsp_name;
	}
	public String getDsp_url() {
		return dsp_url;
	}
	public void setDsp_url(String dsp_url) {
		this.dsp_url = dsp_url;
	}
	public String getDsp_desc() {
		return dsp_desc;
	}
	public void setDsp_desc(String dsp_desc) {
		this.dsp_desc = dsp_desc;
	}
	public String getDsp_key() {
		return dsp_key;
	}
	public void setDsp_key(String dsp_key) {
		this.dsp_key = dsp_key;
	}
	public String getSource_str() {
		return source_str;
	}
	public void setSource_str(String source_str) {
		this.source_str = source_str;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getBean() {
		return bean;
	}
	public void setBean(String bean) {
		this.bean = bean;
	}


	
}
