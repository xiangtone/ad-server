package cn.adwalker.ad.picker.form;

public class UserForm {
	
	public Long appId;
	
	public String channel; //渠道
	
	public String devUserId;
	
	public String idfa;
	
	public String ip;
	
	public String mac; 
	
	public String uuid; //uuid
	
	public String telModel; //机型
	
	public String os_version;//操作系统
	
	public String openudid;
	
	
	public String operator;//运营商
	
	public String terminalType;
	
	public String version; //版本

	public UserForm(Long appId, String channel, String devUserId, String idfa,
			String ip, String mac, String uuid, String telModel, String os,
			String openudid, String operator, String terminalType,
			String version) {
		super();
		this.appId = appId;
		this.channel = channel;
		this.devUserId = devUserId;
		this.idfa = idfa;
		this.ip = ip;
		this.mac = mac;
		this.uuid = uuid;
		this.telModel = telModel;
		this.os_version = os;
		this.openudid = openudid;
		this.operator = operator;
		this.terminalType = terminalType;
		this.version = version;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
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

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getTelModel() {
		return telModel;
	}

	public void setTelModel(String telModel) {
		this.telModel = telModel;
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
