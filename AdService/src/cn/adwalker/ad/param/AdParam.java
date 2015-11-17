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
public class AdParam extends ParamVo {
	private String appkey;
	private Integer pageNo;
	private Integer pageSize;
	private String userId;
	
	public Integer getPageNo() {
		return pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public String getVersion() {
		return version;
	}
	
	public String getAppkey() {
		return appkey;
	}
	public void setAppkey(String appkey) {
		this.appkey = appkey;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
}
