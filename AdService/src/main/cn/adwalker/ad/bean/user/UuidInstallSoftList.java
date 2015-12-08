
package cn.adwalker.ad.bean.user;

import java.io.Serializable;
import java.util.Date;

import cn.adwalker.ad.logger.InstallSoftListLogger;
import cn.adwalker.ad.util.DateUtil;
/**
 * 
* <p>Title: UuidInstallSoftList</p>
* <p>Description:用户安装软件列表</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-1-4
 */
public class UuidInstallSoftList implements Serializable {

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -2211649620198497005L;
	/*终端手机用户的唯一标示*/
	private String uuid;
	/*终端手机用户安装的应用的名称*/
	private String appName;
	/*终端手机用户安装的应用的包名*/
	private String packageName;
	private String appkey;//appkey
	private String sdk_version;//sdk版本
	
	private String dateString;
	public void softListlogInfo() {
		String s[] = {uuid,packageName,appName,this.getCreate_time(),appkey,sdk_version};
		if(uuid != null && !"".equals(uuid)){
			InstallSoftListLogger logger = new InstallSoftListLogger();
			logger.log(s);
			
		}
	}
	
	public String getCreate_time() {
		 dateString = DateUtil.getDateStringByPattern(new Date(),DateUtil.PARTTERN_DATE_TIME);
		return dateString;
	}
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getSdk_version() {
		return sdk_version;
	}

	public void setSdk_version(String sdk_version) {
		this.sdk_version = sdk_version;
	}
}

