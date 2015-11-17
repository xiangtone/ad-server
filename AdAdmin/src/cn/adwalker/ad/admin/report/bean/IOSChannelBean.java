package cn.adwalker.ad.admin.report.bean;

public class IOSChannelBean {

	/*** 广告id **/
	private String ad_id;

	/*** 渠道 **/
	private String channel;

	/*** 广告名称 **/
	private String ad_name;

	/*** mac地址 **/
	private String mac;

	/*** 状态 **/
	private String searchStatus;

	/*** 效果生生日期 **/
	private String statDate_start;

	/*** 效果生生日期 **/
	private String statDate_end;

	/*** 确认时间 **/
	private String activeDate_start;

	/*** 确认时间 **/
	private String activeDate_end;

	private Integer pageType;
	/*** idfa ***/
	private String idfa;
	/*** openudid **/
	private String openUdid;
	/*** idfv */
	private String idfv;
	/***广告名称***/
	private String q;
	
	private String timeType="0";	// 时间类型 0：点击时间，1：确认时间
	
	private String dataType="0";	//数据类型 根据不同的数据类型查不同的表
	
	private String send;			//0:刷量嫌疑未发送，1:已发送，2:被扣量
	public String getAd_id() {
		return ad_id;
	}

	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getAd_name() {
		return ad_name;
	}

	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getSearchStatus() {
		return searchStatus;
	}

	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}

	public String getStatDate_start() {
		return statDate_start;
	}

	public void setStatDate_start(String statDate_start) {
		this.statDate_start = statDate_start;
	}

	public String getStatDate_end() {
		return statDate_end;
	}

	public void setStatDate_end(String statDate_end) {
		this.statDate_end = statDate_end;
	}

	public String getActiveDate_start() {
		return activeDate_start;
	}

	public void setActiveDate_start(String activeDate_start) {
		this.activeDate_start = activeDate_start;
	}

	public String getActiveDate_end() {
		return activeDate_end;
	}

	public void setActiveDate_end(String activeDate_end) {
		this.activeDate_end = activeDate_end;
	}

	public IOSChannelBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getPageType() {
		return pageType;
	}

	public void setPageType(Integer pageType) {
		this.pageType = pageType;
	}

	public String getIdfa() {
		return idfa;
	}

	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}

	public String getOpenUdid() {
		return openUdid;
	}

	public void setOpenUdid(String openUdid) {
		this.openUdid = openUdid;
	}

	public String getIdfv() {
		return idfv;
	}

	public void setIdfv(String idfv) {
		this.idfv = idfv;
	}

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}
	
	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getSend() {
		return send;
	}

	public void setSend(String send) {
		this.send = send;
	}

	
}
