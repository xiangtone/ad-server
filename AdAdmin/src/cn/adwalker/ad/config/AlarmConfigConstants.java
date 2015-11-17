package cn.adwalker.ad.config;

/**
 * <p>
 * Title: AlarmConfigConstants
 * </p>
 * <p>
 * Description:积分基础数据常量
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2012-9-20
 */
public class AlarmConfigConstants {
	/**
	 * 墙停留时间
	 */
	public static final String COLUMN_SCORE_DELAY_TIME = "score_delay_time";

	/**
	 * 详情页停留时间
	 */
	public static final String COLUMN_DETIAL_STAY_TIME = "DETIAL_STAY_TIME";

	/**
	 * 凌晨下载量
	 */
	public static final String COLUMN_NIGHT_DOWNLOAD_AMOUNT = "NIGHT_DOWNLOAD_AMOUNT";

	/**
	 * 同ip访问设备数
	 */
	public static final String COLUMN_SAME_IP_EQUIPMENT = "SAME_IP_EQUIPMENT";

	/**
	 * 同应用对应的同IP设备数超过预警值的IP数
	 */
	public static final String COLUMN_SAME_IP_EQUIPMENT_IP_NUM = "SAME_IP_EQUIPMENT_IP_NUM";

	/**
	 * 同ip激活数
	 */
	public static final String COLUMN_SAME_IP_ACTIVATION = "SAME_IP_ACTIVATION";

	/**
	 * 同应用对应的同IP激活数超过预警值的激活数
	 */
	public static final String COLUMN_SAME_IP_ACTIVATION_IP_NUM = "SAME_IP_ACTIVATION_IP_NUM";

	/*
	 * 同手机应用访问数
	 */
	public static final String COLUMN_SAME_MOBILE_REQUEST_APP = "SAME_MOBILE_REQUEST_APP";

	/**
	 * 流量辨别类型集合
	 */
	private static final String arr[] = new String[] { COLUMN_SCORE_DELAY_TIME };

	/**
	 * 
	 * <p>
	 * Title: getColumns
	 * </p>
	 * <p>
	 * Description:获取积分辨别所有参数
	 * </p>
	 * @return
	 * @return String[]
	 */
	public static String[] getColumns() {
		return arr;
	}

}
