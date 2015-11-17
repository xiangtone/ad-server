package cn.adwalker.IOSChannel.picker.bean;

import cn.adwalker.IOSChannel.picker.util.StringUtil;


/**
 * @author admin
 * 渠道请求封装类
 */
public class ChannelBean extends YjfBean{

	//应用id
	private String appid;
	//渠道标识 如 有米  等
	private String source;
	//设备id  mac地址 处理时替换冒号
	private String deviceid;
	//udid
	private String udid;
	//ip地址
	private String client_ip;
	//时间戳
	private String eventtime;
	//openudid
	private String OPENUDID;
	//idfa
	private String IDFA;
	//idfv
	private String IDFV;
	//ios 系统版本
	private String os;
	
	public String getEventtime() {
		return eventtime;
	}
	public void setEventtime(String eventtime) {
		this.eventtime = eventtime;
	}
	public String getOPENUDID() {
		return OPENUDID;
	}
	public void setOPENUDID(String oPENUDID) {
		OPENUDID = oPENUDID;
	}
	public String getIDFA() {
		return IDFA;
	}
	public void setIDFA(String iDFA) {
		IDFA = iDFA;
	}
	public String getIDFV() {
		return IDFV;
	}
	public void setIDFV(String iDFV) {
		IDFV = iDFV;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getUdid() {
		return udid;
	}
	public void setUdid(String udid) {
		this.udid = udid;
	}
	public String getClient_ip() {
		return client_ip;
	}
	public void setClient_ip(String clientIp) {
		client_ip = clientIp;
	}
	
	public boolean isIos7(){
	   return StringUtil.parseVersion(os)>=7;
	}
	public boolean isIos6(){
		return StringUtil.parseVersion(os)<7;
	}
	
}
