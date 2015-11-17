package cn.adwalker.IOSChannel.picker.param;

public class YoumiForm {
	private String order; // 订单ID：该值是唯一的，如果开发者接收到相同的订单号，那说明该订单已经存在。
	private String app; // 字符串(16字节)开发者应用ID
	private String ad; // 广告名（50字节） 广告名，如果是应用类型的广告则是应用名
	private Long adid; // 广告编号（整形）广告的编号ID
	private String user; // 字符串 用户ID：开发者可以设置自己的用户ID，或者将该字段作为回调预留字段使用
	private String device; // 字符串 设备ID：iOS是MAC地址，iOS 7 没有MAC地址则传Advertising Identifier (IDFA) "https://developer.apple.com/library/ios/documentation/AdSupport/Reference/ASIdentifierManager_Ref/ASIdentifierManager.html#//apple_ref/occ/instp/ASIdentifierManager/advertisingIdentifier“
	private Integer chn; // 整型 渠道号，对于IOS来说该值为0
	private Float price; // 浮点型 开发者获得的收入
	private Integer points; // 整型 用户可以赚取的积分
	private Long time; // 整形 有米创建订单的时间
	private String sig; // 字符串(8字节) 保留字段（无意义）
	private String sign; // 字符串(36字节) 签名

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public Long getAdid() {
		return adid;
	}

	public void setAdid(Long adid) {
		this.adid = adid;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public Integer getChn() {
		return chn;
	}

	public void setChn(Integer chn) {
		this.chn = chn;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getSig() {
		return sig;
	}

	public void setSig(String sig) {
		this.sig = sig;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
}