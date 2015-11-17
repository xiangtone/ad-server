package cn.adwalker.IOSChannel.picker.vo;

import java.io.Serializable;

import cn.adwalker.IOSChannel.picker.util.StringUtil;
public class ParamVo implements Serializable {
	private static final long serialVersionUID = 1L;
	public String userAgent;
	public String os;//操作系统
	public String version;//sdk版本

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	
	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public boolean isIos7(String mac){
		return StringUtil.equals("020000000000",mac)||StringUtil.parseVersion(os)>=7;
	}
	public boolean isIos6(){
		return StringUtil.parseVersion(os)<7;
	}

	public String getversion() {
		return version;
	}

	public void setversion(String yjfVersion) {
		version = yjfVersion;
	}

	public int verCompareTo(String sdkVersion){
		if(null==getversion()){
			return -1;
		}else{
		    return getversion().compareTo(sdkVersion);
		}
	}
    
	
}
