/**
* <p>Title: Constant.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2012-12-21
* @version 1.0
*/
package cn.adwalker.IOSChannel.vo;

/**
 * <p>Title: Constant</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2012-12-21
 */
public class Constant {
	 public final static String APP_ID = "appid";//
	 public final static String APP_ID2d = "appId";//
	 public final static String DEVICE_ID = "deviceid";//
	 public final static String SOURSE = "source";//广告主提供的appKEY
	 public final static String STAT_DATE = "eventtime";//效果发生时间
	 public final static String UDID = "udid";
	 public final static String CLIENT_IP = "client_ip";
	 
	 
	 public final static Integer IOS_ACTION_LOG_STATUS_UNCONFIRMED = 0;//待确认
	 public final static Integer IOS_ACTION_LOG_STATUS_CONFIRMED = 1;//已发送
	 public final static Integer IOS_ACTION_LOG_ACTIVITE_STATUS_UNCONFIRMED = 0;//未确认
	 public final static Integer IOS_ACTION_LOG_ACTIVITE_STATUS_CONFIRMED = 1;//确认


	 //add by jief 2013-09-03 IDFA OpenUDID IDFV
	 public final static String IOS_OpenUDID = "OPENUDID";		// OpenUDID
	 public final static String IOS_IDFA = "IDFA";				// IDFA
	 public final static String IOS_IDFV = "IDFV";				// IDFV
}
