package cn.adwalker.IOSChannel.strategy;

import java.io.Serializable;
import java.util.List;

public class AreaScheme implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7496243588110123397L;
	private long id;				//id
	private String name;			//方案名称
	private Integer is_default;		//是否是默认方案1:是，0:否
	private Integer adormedia;		//方案类型，广告方案，还是媒体方案
	private List<AreaSchemeInfo> schemeInfo; //方案配置详情
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getIs_default() {
		return is_default;
	}
	public void setIs_default(Integer is_default) {
		this.is_default = is_default;
	}
	public Integer getAdormedia() {
		return adormedia;
	}
	public void setAdormedia(Integer adormedia) {
		this.adormedia = adormedia;
	}
	public List<AreaSchemeInfo> getSchemeInfo() {
		return schemeInfo;
	}
	public void setSchemeInfo(List<AreaSchemeInfo> schemeInfo) {
		this.schemeInfo = schemeInfo;
	}
	
	

}
