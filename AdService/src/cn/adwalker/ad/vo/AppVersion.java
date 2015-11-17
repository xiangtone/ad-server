package cn.adwalker.ad.vo;

import java.io.IOException;
import java.io.Serializable;

import cn.adwalker.core.utils.JacksonMapper;

public class AppVersion implements Serializable{

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 1329692238445365814L;
	
	private String version;
	
	private String appurl;
	

	public AppVersion(String version, String url) {
		super();
		this.version = version;
		this.appurl = url;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAppurl() {
		return appurl;
	}

	public void setAppurl(String appurl) {
		this.appurl = appurl;
	}
	
	
	public static void main(String args[]) throws IOException{
		V<AppVersion> v=new V<AppVersion>();
		v.setMessage("欢迎下载!!");
		v.setSuccess(true);
		v.setData(new AppVersion("1.0.1","http://res.adwalker.cn/adres/AppXianFeng.apk"));
		System.out.println(JacksonMapper.objectToJsonString(v));
		
	}
}
