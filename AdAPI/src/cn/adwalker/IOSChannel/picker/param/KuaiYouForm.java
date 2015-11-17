package cn.adwalker.IOSChannel.picker.param;

public class KuaiYouForm {
	
//	private String order;//OrderID
//	private String aid;//AppId
//	private String dan;//广告中文名称，例：酷告积分墙 经过URLEncoder.encode(dan, "utf-8");
//	private String userId;//应用区分用户标示，缺省空字符串
	private String adi;//广告的编号ID，例：EkBEhRWJ
	private String device;//iOS： 设备ID：iOS是MAC地址，iOS 7 IDFA MAC ：小写去冒号 f8a7f8a8f8da IDFA： EBBEB483-FBDF-4DA1-B8F9-A704B4874B37 Android: 设备ID：android是imei 834172312837131
//	private String	chn;//预留字段，可为空
//	private String points;//用户可以赚取的积分，即获得积分
//	private String ti;//创建订单的时间
//	private String to;//签名字段
	public String getAdi() {
		return adi;
	}
	public void setAdi(String adi) {
		this.adi = adi;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
}
