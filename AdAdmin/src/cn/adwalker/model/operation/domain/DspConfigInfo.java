package cn.adwalker.model.operation.domain;

import cn.adwalker.core.repository.Entity;

/**
* <p>Title: DspInfo</p>
* <p>Description:DSP信息表 </p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-11-28
 */
public class DspConfigInfo implements Entity {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 3736041807444850338L;
	//主键
	private Long id;
	//dsp_id
	private Long dsp_id;
	//参数
	private String param_type;
	//参数名
	private String param_name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDsp_id() {
		return dsp_id;
	}
	public void setDsp_id(Long dsp_id) {
		this.dsp_id = dsp_id;
	}
	public String getParam_type() {
		return param_type;
	}
	public void setParam_type(String param_type) {
		this.param_type = param_type;
	}
	public String getParam_name() {
		return param_name;
	}
	public void setParam_name(String param_name) {
		this.param_name = param_name;
	}
	
	
	
}
