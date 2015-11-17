/**
 * <p>Title: RefreshLog4jConstant.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2013-1-4
 * @version 1.0
 */
package cn.adwalker.ad.util;

/**
 * <p>
 * Title: RefreshLog4jConstant
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author www.adwalker.cn
 * @date 2013-1-4
 */
public class AppConstant {
	/**
	 * 图片资源前缀
	 */
	public final static String imageF = ConfigUtil.getString("images.url.prefix");
	
	/**
	 * 文件资源前缀
	 */
	public final static String resF = ConfigUtil.getString("resources.url.prefix");
	

	/**
	 * 内存缓存时间
	 */
	public static final Integer DEFAULT_MEM_CACHE_TIME = 30;

	/** 货币的保留小数 */
	public final static int MONEY_POINT = 4;


	// 请求参数加密key
	public static final String REQUENST_PARAM_KEY = "requenst_param_key";

	// 访问状态
	public final static String STATUS_OK = "ok";// 访问成功
	public final static String STATUS_ERROR = "error";// 访问失败
	public final static String MEMCACHE_TYPE = "memTypes_12_15_1";// 广告位
	public final static String MEMCACHE_TYPE_FEEDBACK = "memTypeFeedback";// 反馈内容广告名称
	public final static String MEMCACHE_DEV_CURRENCY = "memDevCurrency";// 应用货币表
	public final static String MEMCACHE_APPLICATION = "memApps_n";// 应用
	public final static String MEMCACHE_KEY_APP = "memAppKey_n";// 应用
	public static final String MEM_AD_SSP = "memAdSsp";
	public static final String MEM_AD_SSP_ID = "memAdSspId";
	public static final String MEM_SIGN = "memSign";
	public static final String MEM_USER_SCORE = "memUserScore";
	public final static String MEMCACHE_AD = "memAd_new_2_114";
	public final static String MEMCACHE_CAMPAIGN_CATEGORY = "memCamCate";
	public static final String AD_CHANNEL = "adChannel";
	public static final String AD_IOS_KEY = "adKey";
	public static final String AD_IOS_ID = "adId";
	public final static String MEMCACHE_SYSTEM_CONFIG = "memSysCon_new";
	public final static String MEMCACHE_APP_BLACK = "memAppBlack_";
	public final static String MEMCACHE_MEDIA_USER = "memMediaUser_33333";
	
	public final static String MEMCACHE_DOWNLOAD_TOKEN = "memdownload_";
	
	// 请求参数

	public final static String P_NAME_APP_KEY = "appkey";
	public final static String P_NAME_MD5 = "m";

	public final static String P_PARAM_MAP_KEY = "paramsMap";

	//
	// 请求参数
	public final static String P_NAME_MAP = "map";

	public final static String MEMCACHE_AD_DETAIL = "memAdDetail_new";//原来的缓存结构修改了下，重新定义的key.
	
	
	public final static String MEMCACHE_AD_CLUSTER = "memAdcluster";
	// 默认的imei
	public static final String DEFAULT_IMEI = "imei_is_null";

	// 请求有效期；单位秒，默认2小时
	public static final long REQ_EXPIRED_TIME = 60 * 60 * 2;

	// 默认编码
	public static final String DEFAULT_CODE = "UTF-8";

	// 自己人
	public static final Long SELF_DEV_ID = 88L;

	// 自己人应用ID
	public static final Long SELF_DEV_APP_ID = 777L;

	// 自己人应用key
	public static final String SELF_DEV_APP_KEY = "QIANCHIANDadwalkerDEZIJIRENZHANGHAO";

	/** 待审核 */
	public final static int STATUS_UNAPPLY = 0;

	/** 审核中 */
	public final static int STATUS_UNCHECK = 1;

	/** 通过 */
	public final static int STATUS_PASS = 2;

	/** 未通过 */
	public final static int STATUS_NOTPASS = 3;

	/** 上线 */
	public final static int STATUS_ONLINE = 4;

	/** 下线 */
	public final static int STATUS_OFFLINE = 5;

	/** 终止 */
	public final static int STATUS_STOP = 6;


	// 未删除
	public final static int DELETE_FALSE = 0;

	// 删除状态
	public final static int DELETE_TRUE = 1;

	// 信用额度来源
	public final static int MONEY_SRC_CREDIT = 1;// 信用额度
	public final static int MONEY_SRC_ACTUAL = 2;// 实际额度
	public final static int MONEY_SRC_MIX = 3;// 混合额度

	// 财务线下数据
	public final static int FINANCE_OFFLINE_STATUS = 2;

	/**
	 * 广告分类是否排重,1:排重，0:不排重
	 */
	public final static int AD_RULE_CATEGORY_TYPE_UNIQUE = 1;
	// 墙的类别,1:墙，2：banner
	public static final String PAGE_WALLTYPE_LIST_SMALL = "0";// 列表小按钮,积分强
	public static final String PAGE_WALLTYPE_LIST_BIG = "1";// 列表大按钮，推荐墙
	public static final String PAGE_WALLTYPE_GRID = "2";// 九宫格，推荐墙
	public static final String PAGE_WALLTYPE_PUSH = "3";// 推送
	public static final String PAGE_WALLTYPE_BANNER = "4";// 推广条，BANNER
	public static final String PAGE_WALLTYPE_PLAQUE = "5";// 插屏
	/**
	 * * imageType 0; // 图片-推广条小(320*80) （默认） 1; // 图片-推广条大(320*134) 2; //
	 * 图片-插屏横屏(200*100) 3; // 图片-插屏竖屏(100*200)（默认）
	 */
	public static final String PAGE_IMAGE_TYPE_BANNER_SMILL = "0";
	public static final String PAGE_IMAGE_TYPE_BANNER_BIG = "1";
	public static final String PAGE_IMAGE_TYPE_PLAQUE_WIDTH = "2";
	public static final String PAGE_IMAGE_TYPE_PLAQUE_HEIGHT = "3";

	public static final String OS_ANDROID = "android";
	/**
	 * 系统类型常量--IOS
	 */
	public static final String OS_IOS = "ios";

	public static final String AD_CPA = "CPA";
	public static final String AD_CPM = "CPM";
	public static final String AD_CPD = "CPD";
	public static final String AD_CPC = "CPC";

	/**
	 * ad_type;//0-跳至详情页 1-跳至注册网页jump_url,2app推广下载,3直接下载
	 */
	public static final int AD_TYPE_DETAILS = 0;
	public static final int AD_TYPE_URL = 1;
	public static final int AD_TYPE_DOWNLOAD = 2;
	public static final int AD_TYPE_DRIECT_DOWNLOAD = 3;

	
	
	/**
	 * SDK版本：androidV3.0.0
	 */
	public static final String ANDROIDV300 = "androidV3.0.0";

	/**
	 * SDK版本：androidV3.1.0
	 */
	public static final String ANDROIDV310 = "androidV3.1.0";

	/**
	 * SDK版本：androidV3.1.1
	 */
	public static final String ANDROIDV311 = "androidV3.1.1";

	/**
	 * SDK版本：androidV3.1.2
	 */
	public static final String ANDROIDV312 = "androidV3.1.2";

	public static final String ANDROIDV313 = "androidV3.1.3";

	public static final String ANDROIDV314 = "androidV3.1.4";

	public static final String ANDROIDV315 = "androidV3.1.5";
	
	public static final String ANDROIDV316 = "androidV3.1.6";
	public static final String ANDROIDV317 = "androidV3.1.7";
	
	public static final String ANDROIDV210 = "androidV2.1.0";
	

	public static final String IOSV100 = "IOS1.0.0";

	public static final String IOSV101 = "IOS1.0.1";

	public static final String IOSV102 = "IOS1.0.2";

	public static final String IOSV110 = "IOS1.1.0";

	public static final String IOSV111 = "IOS1.1.1";

	public static final String IOSV120 = "IOS1.2.0";
	//add by jief 2013-09-02
	public static final String IOSV121 = "IOS1.2.1";
	//add by jief 2013-09-09 for ios7
	public static final String IOSV122 = "IOS1.2.2";
	public static final String IOSV123 = "IOS1.2.3";
	
	public static final String IOSV210 = "IOS2.1.0";
	
	
	// 广告主接收数据方式
	public final static String SEND_TYPE_GET = "GET";
	public final static String SEND_TYPE_POST = "POST";

	/**
	 * 广告是否被下载
	 */
	public static final int DOWNLOAD = 1;

	/**
	 * 终端类型 mark by cdd
	 */
	public static final String PAD = "ipad";
	public static final String PHONE="phone";
	public static final String UNKNOWN="unknown";

	/**
	 * 终端类型 mark by cdd
	 */
	public static final String ZIJIREN = "zijiren";

	

	/**
	 * 电商,游戏,品牌,系统工具,生活工具
	 */
	public static final int CATALOG_1 = 1;
	public static final int CATALOG_2 = 2;
	public static final int CATALOG_3 = 3;
	public static final int CATALOG_4 = 4;
	public static final int CATALOG_5 = 5;
	public static final String CATALOG_1_DESC = "电商";
	public static final String CATALOG_2_DESC = "游戏";
	public static final String CATALOG_3_DESC = "品牌";
	public static final String CATALOG_4_DESC = "系统工具";
	public static final String CATALOG_5_DESC = "生活工具";

	/**
	 * 积分签到
	 */
	public final static String SCORE_SIGN_STATUS = "SIGN_STATUS";
	public final static String SCORE_SIGN_FIRST = "SIGN_FIRST";
	public final static String SCORE_SIGN_SECOND = "SIGN_SECOND";
	public final static String SCORE_SIGN_THIRD = "SIGN_THIRD";
	public final static int SCORE_SIGN_STATUS_CLOSED = 0;
	public final static int SCORE_SIGN_STATUS_CLOSED_SCALE = 10;
	public final static int SCORE_SIGN_STATUS_FIRST = 1;
	public final static int SCORE_SIGN_STATUS_SECOND = 2;

	public final static String SYS_AC_LIMIT = "score_ac_limit";
	public final static String SYS_DELAY_TIME = "score_delay_time";
	public final static String SYS_EXPLAIN = "score_explain";
	public final static String SYS_BANNER_INTERVAL = "banner_interval";
	public final static String SYS_ANDROID_BANNER = "android_banner_probability";
	public final static String SYS_ANDROID_PLAQUE = "android_plaque_probability";
	public final static String SYS_IOS_BANNER = "ios_banner_probability";
	public final static String SYS_IOS_PLAQUE = "ios_plaque_probability";
	public final static String SYS_LOMARK_PRICE = "lomark_price";
	public final static String SYS_LOMARK_PAY_TYPE = "lomark_pay_type";
	
	//add by jief
	public final static String SYS_LOMARK_URL = "lomark_url";
	public final static String SYS_LOMARK_KEY = "lomark_key";
	public final static String SYS_LOMARK_SAFE_KEY = "lomark_safe_key";
	
	
	
	
	
	public static final String	AD_RES_SWITCH_KUAIYOU="AD_RES_SWITCH_KUAIYOU";
	public static final String	AD_RES_SWITCH_CHUKONG="AD_RES_SWITCH_CHUKONG";
	public static final String AD_RES_SWITCH_YOUMI="AD_RES_SWITCH_YOUMI";
	public static final String	AD_RES_SWITCH_PLATFORM="AD_RES_SWITCH_PLATFORM";
	
	
	public static final String	AD_RES_CODE_KUAIYOU="KUAIYOU";
	public static final String	AD_RES_CODE_CHUKONG="CHUKONG";
	public static final String AD_RES_CODE_YOUMI="YOUMI";
	public static final String	AD_RES_CODE_PLATFORM="ADWALKER";
	
	
	
	
	/**
	 * 用户激活签到次数
	 */
	public final static int SIGN_NUM_ZERO = 0;
	public final static int SIGN_NUM_FIRST = 1;
	public final static int SIGN_NUM_SECOND = 2;
	public final static int SIGN_NUM_THIRD = 3;

	public final static int SCORE_FLAG = -1;// 超出激活数
	public final static int SCORE_EXISTING = 1;// 已激活数

	
	public final static int CACHE_TIME = Integer.parseInt(ConfigUtil.getString("cache_time"));
	public final static long USER_CACHE_TIME = Integer.parseInt(ConfigUtil
			.getString("user_cache_time"));
	public final static String extend_sdk_imagesF = ConfigUtil
			.getString("extend_sdk_images.url.prefix");

	public static final String ISODateNum = "yyyyMMddHHmm";
	
	/**
	 * 对外接口URL
	 */
	public static final String ESCORE_API = "ESCORE_API";
	
	
	public static final String YJF="YJF";
	public static final String DSP="DSP";
	
	//逻辑真假
	public static final String LOGIC_TRUE="1";
	public static final String LOGIC_FALSE="0";
	public static final String PARAM_KE_APP_ID="appId";
	
	
	
	public static String PLIST_URL="https://syxf-cdn.b0.upaiyun.com/plist/1417173096648.plist";
}
