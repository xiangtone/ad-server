package cn.adwalker.ad.picker.vo;




public class ParamVo {
	public String userAgent;
	public String ip;
	public String os_version;//操作系统
	public String version;//sdk版本
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getOs_version() {
		return os_version;
	}

	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}

	public int verCompareTo(String sdkVersion){
		if(null==getVersion()){
			return -1;
		}else{
		    return getVersion().compareTo(sdkVersion);
		}
	}
    
	
}
