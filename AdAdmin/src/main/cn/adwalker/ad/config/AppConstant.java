package cn.adwalker.ad.config;

public class AppConstant {
	
	/** 渠道重置默认密码 */
	public final static String RESET_CHANNEL = "123456";
	/** 渠道重置默认密码 */
	public final static String RESET_ROLE = "123456";

	/** 货币的保留小数 */
	public final static int MONEY_POINT = 4;
	
	/** 生成随机密钥位数 */
	public final static int CODE_COUNT = 32;

	public final static int DEL_YES = 1;// 删除标识

	public final static int DEL_NO = 0;// 未删除标识
	
	
	
	
	/** 广告主 */
	public final static int USER_ADVERTISER = 1;

	/** 开发者 */
	public final static int USER_DEVELOPER = 2;
	
	
	/**渠道 */
	public final static int USER_CHANNELS = 3;

	/** 提现审批状态 */
	public final static int DEV_APPLY_MONEY = 0;// 申请提现——未审批

	public final static int DEV_APPLY_MONEY_PASS = 1;// 申请提现——审批通过

	public final static int DEV_APPLY_MONEY_NOTPASS = -1;// 申请提现——审批未通过


	public final static int IS_LOGIN_FAIL = 0;// 用户未登录

	public final static int IS_ERROR = -1;// 运行时异常

	public final static int STAT_TYPE_COMPREHENSIVE = 1;// 统计类型 综合;

	public final static int STAT_TYPE_DAY = 2;// 统计类型 日运算;

	public final static int STAT_RANGE_COMPREHENSIVE = 1;// 统计类型 综合;

	public final static int STAT_RANGE_SINGLE = 2;// 统计类型 单个;

	public final static int CHANNEL_RANGE_COMPREHENSIVE = 1;// 统计渠道 综合;

	public final static int CHANNEL_RANGE_SINGLE = 2;// 统计渠道 单个;

	public static final Long SELF_DEV_ID = 888L;// 自己人

	public static final Long SELF_DEV_APP_ID = 777L;// 自己人应用
	
	/** 新闻公告类型-新闻 */
	public static final int NEWS_NOTICE_TYPE_NEWS = 1;
	/** 新闻公告类型-公告 */
	public static final int NEWS_NOTICE_TYPE_NOTICE = 2;
	
	// 财务线上数据
	public final static int FINANCE_ONLINE_STATUS = 1;
	// 财务线下数据
	public final static int FINANCE_OFFLINE_STATUS = 2;
	
	
	// 信用额度来源
	public final static int MONEY_SRC_CREDIT = 1;// 信用额度
	
	
	public final static int MONEY_SRC_ACTUAL = 2;// 实际额度
	
	
	public final static int MONEY_SRC_MIX = 3;// 混合额度

	public final static int STATUS_PASS = 2;
	//销售状态
	public final static int STATUS= 1;
	
	
	/** 资源状态-删除 */
	public final static int RESOURCE_CLOSED = -1;
	
	/** 资源管理添加 */
	public final static int RESOURCE_ADD = 0;
	
	public static int MAX_QUENE_SIZE = 1;// 最大任务队列数
	public static int MIN_QUENE_SIZE = 1;// 默认任务队列数

	public static int MAX_SIM_UPLOAD = 1;// 同时上传最大任务数
	public static int MIN_SIM_UPLOAD = 1;// 默认同时上传任务数

	// 正则
//	public static String DEFAULT_FILE_EXT = "*.*";// 默认的类型
//	public static String FILE_EXT_MATCH = "^\\*\\.\\*$|^\\*\\.[a-zA-Z]+([;]\\*\\.[a-zA-Z]+)*$";// 文件格式的正则表达式
	public static String FILE_NAME_MATCH = "^([\\u4e00-\\u9fa5\\w\\(\\).-])+\\.[a-zA-Z]+$";
	public static String DEFAULT_FILE_EXT = "*.*";// 默认的类型
	public static String FILE_EXT_MATCH = "^/*/./*$|^/*/.[a-zA-Z]+([;]/*/.[a-zA-Z]+)*$";// 文件格式的正则表达式
//	public static String FILE_NAME_MATCH = "^([/u4e00-/u9fa5/w/(/).-])+/.[a-zA-Z]+$";
////			"^([\\u4e00-\\u9fa5\\w.-])+\\.[a-zA-Z]+$";// 上传文件的名称正则表达式
//	public static String IMAGE_NAME_MATCH = "^([\\u4e00-\\u9fa5\\w.-])+\\.[a-zA-Z]+$";// 上传图片的名称正则表达式
//	public static String IMAGE_ZOOM_SIZE_MATCH = "^\\d+_\\d+$";// 切图尺寸格式正则表达式
	public static String IMAGE_NAME_MATCH = "^([/u4e00-/u9fa5/w.-])+/.[a-zA-Z]+$";// 上传图片的名称正则表达式
	public static String IMAGE_ZOOM_SIZE_MATCH = "^/d+_/d+$";// 切图尺寸格式正则表达式
	public static String IS_NUM_MATCH = "^[1-9][0-9]*$";// 验证正整数

	// 文件名词策略
	public static String FILE_NAME_POLOCY_NOCHANGE_RULE = "0";// 不变并且符合正则,如果不符合规则，则报异常
	public static String FILE_NAME_POLOCY_RANDOM = "1";// 随机生成
	public static String FILE_NAME_POLOCY_NOCHANGE_ALL = "2";// 不变

	// 是否覆盖
	public static String IS_COVER_YES = "1";// 1:覆盖
	public static String IS_COVER_NO = "0";// 0:不覆盖

	// 是否允许裁剪图片
	public static String IS_CUT_YES = "1";// 1:裁剪
	public static String IS_CUT_NO = "0";// 0:等比

	// 方法
	public static String METHOD_FILE = "/upload/uf";// 上传文件方法
	public static String METHOD_IMAGE = "/upload/ui";// 上传图片方法

	// 编码
	public static String UTF8 = "utf-8";

	// 返回值的key
	public static String KEY_ERROR = "error";
	public static String KEY_SUCCEED = "succeed";
	public static String KEY_FALL = "fall";
	// 我方公司名称
	public static String COMPANY_NAME = "天津行云在线科技有限公司北京分公司";
	
	
	
	public static final int CAMPAIGN_CATEGORY_TYPE_TERMINAL=0;
	public static final int CAMPAIGN_CATEGORY_TYPE_SDK=1;
	
	/**
	 * 运营商
	 */
	public static final int CAMPAIGN_CATEGORY_TYPE_OPERAT=2;
	
	public static final int CAMPAIGN_CATEGORY_TYPE_BRAND=3;
	
	public static final int CAMPAIGN_CATEGORY_TYPE_CATEGORY=4;
	
	public static final int CAMPAIGN_CATEGORY_TYPE_TIME=5;
	
	public static final int CAMPAIGN_CATEGORY_TYPE_DIRECTIONAL=6;
	
	public static final int CAMPAIGN_CATEGORY_TYPE[]={CAMPAIGN_CATEGORY_TYPE_TERMINAL,CAMPAIGN_CATEGORY_TYPE_SDK,CAMPAIGN_CATEGORY_TYPE_OPERAT,CAMPAIGN_CATEGORY_TYPE_BRAND,CAMPAIGN_CATEGORY_TYPE_CATEGORY,CAMPAIGN_CATEGORY_TYPE_TIME,CAMPAIGN_CATEGORY_TYPE_DIRECTIONAL};
	
	
	public static String IOS_BELOW = "ios.below.date";
}
