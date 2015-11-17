package cn.adwalker.ad.param;

import cn.adwalker.ad.picker.vo.ParamVo;

/**
 * 
* <p>Title: AdParam</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author    cuidd
* @date       2014年12月5日
 */
public class AdApiParam extends ParamVo {
	private String app_key;
	private Integer pageNo;
	private Integer pageSize;
	private String udid;//	设备的udid或者Openudid	空	否
	private String	mac;//设备的Mac地址	空	否
	private String idfa;//	设备的ifa	空	否
	private String osv;//系统版本  系统版本号（如7.03等）
	private String nt;//使用网络，取值(wifi，2g，3g)	空	否
	
	
	public Integer getPageNo() {
		return pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public String getVersion() {
		return version;
	}
	public String getApp_key() {
		return app_key;
	}
	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getUdid() {
		return udid;
	}
	public void setUdid(String udid) {
		this.udid = udid;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getIdfa() {
		return idfa;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public String getOsv() {
		return osv;
	}
	public void setOsv(String osv) {
		this.osv = osv;
	}
	public String getNt() {
		return nt;
	}
	public void setNt(String nt) {
		this.nt = nt;
	}
}
