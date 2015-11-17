package cn.adwalker.ad.upload.util;

public class AppConstant {

	public static int MAX_QUENE_SIZE = 1;// 最大任务队列数
	public static int MIN_QUENE_SIZE = 1;// 默认任务队列数

	public static int MAX_SIM_UPLOAD = 1;// 同时上传最大任务数
	public static int MIN_SIM_UPLOAD = 1;// 默认同时上传任务数

	// 正则
	public static String DEFAULT_FILE_EXT = "*.*";// 默认的类型
	public static String FILE_EXT_MATCH = "^\\*\\.\\*$|^\\*\\.[a-zA-Z]+([;]\\*\\.[a-zA-Z]+)*$";// 文件格式的正则表达式
	public static String FILE_NAME_MATCH = "^([\\u4e00-\\u9fa5\\w\\(\\).-])+\\.[a-zA-Z]+$";
//			"^([\\u4e00-\\u9fa5\\w.-])+\\.[a-zA-Z]+$";// 上传文件的名称正则表达式
	public static String IMAGE_NAME_MATCH = "^([\\u4e00-\\u9fa5\\w.-])+\\.[a-zA-Z]+$";// 上传图片的名称正则表达式
	public static String IMAGE_ZOOM_SIZE_MATCH = "^\\d+_\\d+$";// 切图尺寸格式正则表达式
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

}
