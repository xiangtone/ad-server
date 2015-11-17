package cn.adwalker.ad.admin.report.bean;

public class AdRequestResult {
	
	/***提交内容 mac地址**/
	private String content;
	
	/***苹果商店 ID**/
	private String appid;
	
	private String channel;
	
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	/***参数类型   0:udid 1:mac地址**/
	private Integer type;
	
	/***开始日期  精确到小时  yyyy-MM-dd HH**/
	private String startDate;
	
	/***结束日期  精确到小时  yyyy-MM-dd HH**/
	private String endDate;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public AdRequestResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
