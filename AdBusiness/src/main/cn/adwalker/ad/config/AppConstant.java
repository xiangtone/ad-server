package cn.adwalker.ad.config;

public class AppConstant {

	/** 货币的保留小数 */
	public final static int MONEY_POINT = 4;
	
	/** 生成随机密钥位数 */
	public final static int CODE_COUNT = 32;
	
	public final static int DEL_YES = 1;// 删除标识
	
	public final static int DEL_NO = 0;// 未删除标识
	
	public final static int APP_RES = 0;

	/** 广告主 */
	public final static int USER_ADVERTISER = 1;

	/** 开发者 */
	public final static int USER_DEVELOPER = 2;
	/**渠道 */
	public final static int USER_CHANNELS = 3;

	/** 提现审批状态初始化状态 */
	public final static int DEV_APPLY_MONEY_FIRST = -5;// 申请提现——初始化状态
	/** 提现审批状态 */
	public final static int DEV_APPLY_MONEY = 0;// 申请提现——未审批

	public final static int DEV_APPLY_MONEY_PASS = 1;// 申请提现——审批通过

	public final static int DEV_APPLY_MONEY_NOTPASS = -1;// 申请提现——审批未通过

	/** 开发者帐号状态-初始化 */
	public final static int BLOCK_NOTUSED = 0;
	/** 不可进入开发者中心 */
	public final static int NO_INTO_T = -2;

	/** 开发者帐号状态-正常(激活) */
	public final static int BLOCK_USE = 1;

	/** 开发者帐号状态-封停 */
	public final static int BLOCK_CLOSED = 2;

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
	// sdk渠道分成
	public final static double CHANNEL_SDK_SCALE = 0.15;
}
