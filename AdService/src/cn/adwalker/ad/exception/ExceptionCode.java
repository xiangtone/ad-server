package cn.adwalker.ad.exception;

public class ExceptionCode {
	
	// 101=参数无效
	public static final String REQUEST_PARAM_INVALID = "101";

	// 103=密钥不正确
	public static final String REQUEST_KEY_NOT_MATCH = "103";
	// 200=成功
	public static final String SUCCESS = "200";
	// 301=用户不存在
	public static final String BUSINESS_USER_NOT_EXIST = "301";

	// 302=开发者或应用不存在
	public static final String ERROR_APP_NOT_EXIST = "302";
	// 303=广告主或广告不存在
	public static final String BUSINESS_ADV_AD_NOT_EXIST = "303";

	// 304=广告状态不正常
	public static final String BUSINESS_AD_STATUS_ERROR = "304";

	// 401=用户积分不足
	public static final String BUSINESS_USER_SCORE_NOT_ENOUGH = "401";

	

	// 500=请求处理失败，请联系接口服务维护人员
	public static final String SERVER_ERROR = "500";

	
	// 801=参数缺失
	public static final String REQUEST_PARAM_LACK = "801";

	
	//加载成功, 但暂无数据!
	public static final String WALL_LOAD_EMPTY = "1000";
	
	
	public static final String USER_ACTION_REPEAT = "4002";
	

	public static final String USER_ACTION_LIMIT = "4004";
		
	public static final String SIGN_LIMIT = "1200";
		
    public static final String ADV_Fail = "3000";

}
