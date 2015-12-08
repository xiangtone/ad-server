package cn.adwalker.ad.param;

import cn.adwalker.ad.OS;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.picker.vo.ParamVo;
import cn.adwalker.ad.util.WallUtils;
public class WallParam extends ParamVo{
	
	private String os;
	
	private String uuid;
	
	private String page_type;//广告的分类(区分 墙 插屏  banner)
	
	private String image_type;
	
	private String mac;
	
	private String openudid;
	
	private String terminalType;
	
	private Integer isSign;
	
	private Long appId;
	
	private String telModel;
	
	
	private String channel;//渠道
	
	
	private String devUserId;
	
	private String idfa;
	
	
	private String operator;
	
	private String quickly_task;
	
	private Integer pageNo;//页码
	private Integer pageSize;
	private String ssid;
	private String bssid;
	private String phoneName;
	private String latitude;
	private String longitude;

	public String getUuid() {
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

	public String getImage_type() {
		return image_type;
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

	public Integer getPageNo() {
		return pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public String getSsid() {
		return ssid;
	}

	public String getBssid() {
		return bssid;
	}

	public String getPhoneName() {
		return phoneName;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}
	
	public String getTerminalType() {
		return terminalType;
	}
	
	public Integer getIsSign() {
		return isSign;
	}
	
	public Long getAppId() {
		return appId;
	}
	
	public String getTelModel() {
		return telModel;
	}
	
	
	public String getChannel() {
		return channel;
	}
	
	
	public String getDevUserId() {
		return devUserId;
	}
	
	public String getIdfa() {
		return idfa;
	}
	
	public String getQuickly_task() {
		return quickly_task;
	}
	
	public String getOperator() {
		return operator;
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

	public void setIsSign(Integer isSign) {
		this.isSign = isSign;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public void setTelModel(String telModel) {
		this.telModel = telModel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public void setDevUserId(String devUserId) {
		this.devUserId = devUserId;
	}

	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public void setQuickly_task(String quickly_task) {
		this.quickly_task = quickly_task;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public void setBssid(String bssid) {
		this.bssid = bssid;
	}

	public void setPhoneName(String phoneName) {
		this.phoneName = phoneName;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setPage_type(String page_type) {
		this.page_type = page_type;
	}

	public void setImage_type(String image_type) {
		this.image_type = image_type;
	}

	public String getOs_version() {
		return os_version;
	}

	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}
}
