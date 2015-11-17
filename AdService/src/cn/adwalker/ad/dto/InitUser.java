package cn.adwalker.ad.dto;

import java.io.Serializable;

import cn.adwalker.ad.OS;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.util.WallUtils;

public class InitUser implements Serializable {

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -2338920913316507229L;
	private String os;
	private Long app_catalog_id;
	private String page_type;
	private String mac;
	private String openudid;
	private Long appId;
	
	public String os_version;//操作系统
	
	private String channel;//渠道
	
	private String devUserId;
	
	
	public String idfa;
	
	public String ip;
	
	public String telModel; //机型
	
	public String operator;//运营商
	
	public String terminalType;
	
	public String version; //版本
	
	
	
	
	

	public InitUser(String os, Long app_catalog_id, String page_type,
			String mac, String openudid, Long appId, String os_version,
			String channel, String devUserId, String idfa, String ip,
			String telModel, String operator, String terminalType,
			String version) {
		super();
		this.os = os;
		this.app_catalog_id = app_catalog_id;
		this.page_type = page_type;
		this.mac = mac;
		this.openudid = openudid;
		this.appId = appId;
		this.os_version = os_version;
		this.channel = channel;
		this.devUserId = devUserId;
		this.idfa = idfa;
		this.ip = ip;
		this.telModel = telModel;
		this.operator = operator;
		this.terminalType = terminalType;
		this.version = version;
	}


	public String getUuid() {
		String uuid="";
		//ios7  版本系统在1.2.2以后采用udid做为uuid.
		if (OS.ios.equals(os)) {
			if(WallUtils.isIos7(mac,os)){
				uuid=StringUtil.dealNull(openudid,mac);
			}else{
				uuid=StringUtil.dealNull(uuid,mac);
			}
		}
		return uuid;
	}
	
	
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public Long getApp_catalog_id() {
		return app_catalog_id;
	}
	public void setApp_catalog_id(Long app_catalog_id) {
		this.app_catalog_id = app_catalog_id;
	}
	public String getPage_type() {
		return page_type;
	}
	public void setPage_type(String page_type) {
		this.page_type = page_type;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}


	public Long getAppId() {
		return appId;
	}


	public void setAppId(Long appId) {
		this.appId = appId;
	}


	public String getOs_version() {
		return os_version;
	}


	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}


	public String getOpenudid() {
		return openudid;
	}

	public void setOpenudid(String openudid) {
		this.openudid = openudid;
	}


	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}


	public String getDevUserId() {
		return devUserId;
	}


	public void setDevUserId(String devUserId) {
		this.devUserId = devUserId;
	}


	public String getIdfa() {
		return idfa;
	}


	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public String getTelModel() {
		return telModel;
	}


	public void setTelModel(String telModel) {
		this.telModel = telModel;
	}


	public String getOperator() {
		return operator;
	}


	public void setOperator(String operator) {
		this.operator = operator;
	}


	public String getTerminalType() {
		return terminalType;
	}


	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}


	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}
}
