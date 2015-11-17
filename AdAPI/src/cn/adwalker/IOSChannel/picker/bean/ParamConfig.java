/**
* <p>Title: Advertisement_IOS.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2012-12-21
* @version 1.0
*/
package cn.adwalker.IOSChannel.picker.bean;

public class ParamConfig  implements  java.io.Serializable{
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -7064943546345074924L;
	
	private Integer id;
	private String config_type;
	private String config_id;
	private String param_name;
	private String param_alis;
	private String param_value;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getConfig_type() {
		return config_type;
	}
	public void setConfig_type(String configType) {
		config_type = configType;
	}
	public String getConfig_id() {
		return config_id;
	}
	public void setConfig_id(String configId) {
		config_id = configId;
	}
	public String getParam_name() {
		return param_name;
	}
	public void setParam_name(String paramName) {
		param_name = paramName;
	}
	public String getParam_alis() {
		return param_alis;
	}
	public void setParam_alis(String paramAlis) {
		param_alis = paramAlis;
	}
	public String getParam_value() {
		return param_value;
	}
	public void setParam_value(String paramValue) {
		param_value = paramValue;
	}
	
}
