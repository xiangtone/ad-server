package cn.adwalker.IOSChannel.picker.bean;

public class AdBean extends YjfBean {
	//APP标识
	private String appid;
    //设备id mac openudid 定位终端用户
    private String deviceid;
    //mac 为激活标识 ios6为 mac ios7则为openudid idfa idfv中的一种有广告方提供
    private String mac;
	//openudid
	private String OPENUDID;
	//idfa
	private String IDFA;
	//idfv
	private String IDFV;
	//ios 系统版本
	private String os;
	//data json数据
	private String data;
	//签名
	private String sign;
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
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
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
    
	
	
}
