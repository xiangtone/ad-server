package cn.adwalker.IOSChannel.picker.param;

public class ChukongForm {
	
	private String os; // 必填项,系统类型。定义enum: ['iOS', 'Android']
	private String os_version; // OS版本号
	private String idfa; // iOS系统的广告标识符,有中划线全部大写，示例：03B610E3-D865-4BCE-AB2E-5A58635A739C
	private String mac; // MAC地址,没有分隔符全部小写，示例：a0edcd359c7d
	private String imei; // Android系统的唯一设备ID
	private String ip; // 手机客户端的ip地址
	private String transactionid; // 必填项,用于识别是否会用重复的积分返还请求，同一个任务的积分返还id不变
	private Integer coins; // 必填项,积分数
	private String adid; // 必填项,广告id
	private String adtitle; // 广告标题（一般为积分墙列表中的app名称）
	private String taskname; // 任务名称（比如：安装、激活）
	private String taskcontent; // 任务内容描述（比如：安装游戏试玩3分钟即获得奖励）
	private String token; // token的值是媒体开发人员在广告sdk中设置的token值，触控广告平台并不关心
	private String sign; // 此参数可选。请求的签名，签名规则： 1.双方协商确定并各自存储用于签名的秘钥 2.所有请求的参数名/值按照字母升序排序,并拼接。

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getOs_version() {
		return os_version;
	}

	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}

	public String getIdfa() {
		return idfa;
	}

	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

	public Integer getCoins() {
		return coins;
	}

	public void setCoins(Integer coins) {
		this.coins = coins;
	}

	public String getAdid() {
		return adid;
	}

	public void setAdid(String adid) {
		this.adid = adid;
	}

	public String getAdtitle() {
		return adtitle;
	}

	public void setAdtitle(String adtitle) {
		this.adtitle = adtitle;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getTaskcontent() {
		return taskcontent;
	}

	public void setTaskcontent(String taskcontent) {
		this.taskcontent = taskcontent;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
