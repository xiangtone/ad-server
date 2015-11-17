package cn.adwalker.ad.param;

import cn.adwalker.ad.OS;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.picker.vo.ParamVo;
import cn.adwalker.ad.util.WallUtils;
public class WallParam2 extends ParamVo{
	
	private String os;
	
	private String page_type;//广告的分类(区分 墙 插屏  banner)
	
	private String mac;
	
	private String openudid;
	
	private String terminalType;
	
	private Long appId;
	
	private String quickly_task;
	public String getUuid() {
		String uuid=null;
		//ios7  版本系统在1.2.2以后采用udid做为uuid.
		if (OS.ios.equals(os)) {
			if(WallUtils.isIos7(mac,os_version)){
				uuid=StringUtil.dealNull(openudid,mac);
			}else{
				uuid=StringUtil.dealNull(uuid,mac);
			}
		}
		return uuid;
	}

	public String getPage_type() {
		return page_type;
	}
	
	public String getOs() {
		return os;
	}

	public String getMac() {
		return mac;
	}

	public String getOpenudid() {
		return openudid;
	}
	
	public String getTerminalType() {
		return terminalType;
	}
	
	public Long getAppId() {
		return appId;
	}

	public String getQuickly_task() {
		return quickly_task;
	}
	
	
	//###################################################3

	public void setOs(String os) {
		this.os = os;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public void setOpenudid(String openudid) {
		this.openudid = openudid;
	}

	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}
	
	public void setQuickly_task(String quickly_task) {
		this.quickly_task = quickly_task;
	}

	public void setPage_type(String page_type) {
		this.page_type = page_type;
	}

	public String getOs_version() {
		return os_version;
	}

	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}

}
