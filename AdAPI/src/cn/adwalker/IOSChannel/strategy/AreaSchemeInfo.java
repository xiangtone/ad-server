package cn.adwalker.IOSChannel.strategy;

import java.io.Serializable;

public class AreaSchemeInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 759859445182857131L;
	private long id;			//主键
	private long scheme_id;		//方案外健
	private String area;		//区域名称
	private Integer config;		//限制配置
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getScheme_id() {
		return scheme_id;
	}
	public void setScheme_id(long scheme_id) {
		this.scheme_id = scheme_id;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Integer getConfig() {
		return config;
	}
	public void setConfig(Integer config) {
		this.config = config;
	}
	
}
