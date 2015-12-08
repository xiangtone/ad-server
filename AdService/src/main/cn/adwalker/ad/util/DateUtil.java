package cn.adwalker.ad.util;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
/**
 * 
* <p>Title: DateUtils</p>
* <p>Description:日期工具类</p>
* <p>Company: adwalker</p> 
* @author    adwalker
* @date       2012-12-14
 */
public class DateUtil extends org.apache.commons.lang.time.DateUtils {

	/**
	 * 默认时期样式
	 */
	public static final String DEFAULT_PARTTERN = "yyyy-MM-dd";

	/**
	 * 时间样式 yyyy-MM-dd HH:mm:ss
	 */
	public static final String PARTTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 时间带毫秒
	 */
	public static final String PARTTERN_DATE_TIME_SS = "yyyy-MM-dd HH:mm:ss.SSS";

	private static SimpleDateFormat datetimeFormat = new SimpleDateFormat(
			DEFAULT_PARTTERN);
	
	private static Calendar cal = Calendar.getInstance();
	
	
	/**
	 * 时间比较
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean compare(Date d1, Date d2) {
		if (d1 == null || d2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		return compare(c1, c2);
	}
	
	/**
	 * 日期比较
	 * 
	 * @param c1
	 * @param c2
	 * @return
	 */
	public static boolean compare(Calendar c1, Calendar c2) {
		if (c1 == null || c2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		return c1.getTimeInMillis() > c2.getTimeInMillis();

	}

	/**
	 * 将指定日期转换为指定格式的字符串
	 * 
	 * @param date
	 *            ( java.util.Date )
	 * @param pattern
	 *            日期格式，如："yyyy-MM-dd" ，"yyyy-MM-dd HH:mm:ss"，"HH:mm:ss"
	 * @return String
	 */
	public static String getDateStringByPattern(Date date, String pattern) {
		if (date == null) {// 默认今天
			date = new Date();
		}
		SimpleDateFormat sf = new SimpleDateFormat(pattern);
		String str = sf.format(date);

		return str;
	}
	
	/**
	 * 将指定日期转换为指定格式的字符串
	 * 
	 * @param date
	 *            ( java.util.Date )
	 * @param pattern
	 *            日期格式，如："yyyy-MM-dd" ，"yyyy-MM-dd HH:mm:ss"，"HH:mm:ss"
	 * @return String
	 */
	public static String getDateStringByPattern(Date date) {
		if (date == null) {// 默认今天
			date = new Date();
		}
		datetimeFormat.applyLocalizedPattern(DEFAULT_PARTTERN);
		String str = datetimeFormat.format(date);
		return str;
	}
	
	
	/**
	 * 将指定日期转换为指定格式的字符串
	 * 
	 * @param date
	 *            ( java.util.Date )
	 * @param pattern
	 *            日期格式，如："yyyy-MM-dd" ，"yyyy-MM-dd HH:mm:ss"，"HH:mm:ss"
	 * @return String
	 * @throws Exception
	 */
	public static String format(Date date) throws IllegalArgumentException {
		if (date == null) {// 默认今天
			throw new IllegalArgumentException("The date must not be null");
		}
		datetimeFormat.applyPattern(DEFAULT_PARTTERN);
		String str = datetimeFormat.format(date);
		return str;
	}
	
	/**
	 * 
	 * <p>
	 * Title: getDayEnd
	 * </p>
	 * <p>
	 * Description:转换为一天的结束时间
	 * </p>
	 * 
	 * @param d
	 * @return
	 * @author cuidd
	 * @date 2013-6-13
	 * @return Timestamp
	 * @version 1.0
	 */
	public static Date getDayEnd(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();

	}
	
	
	public static int distenceHour(Date start,Date end){
		int i=0;
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(start);
		c2.setTime(end);
		i=c1.get(Calendar.HOUR_OF_DAY)-c2.get(Calendar.HOUR_OF_DAY);
		return i;
		
	}

	

	/**
	 * 将指定日期转换为指定格式的字符串
	 * 
	 * @param date
	 *            ( java.util.Date )
	 * @param pattern
	 *            日期格式，如："yyyy-MM-dd" ，"yyyy-MM-dd HH:mm:ss"，"HH:mm:ss"
	 * @return String
	 * @throws Exception
	 */
	public static String format(Date date, String pattern) {
		String s="";
		if (date!=null) {
			datetimeFormat.applyPattern(pattern);
			s = datetimeFormat.format(date);
		}
		return s;
	}

	/**
	 * 
	* <p>Title: format</p>
	* <p>Description:格式化毫秒转成字符串</p>
	* @param millis
	* @param pattern
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2012-12-14
	* @return String
	* @version 1.0
	* @throws
	 */
	public static String format(long millis, String pattern) throws Exception {
		cal.setTimeInMillis(millis);
		datetimeFormat.applyPattern(pattern);
		String str = datetimeFormat.format(cal.getTime());
		return str;
	}
	

	/**
	 * 日期累加
	 * 
	 * @param add
	 *            可以是负数表示日期减少
	 * @return
	 */
	public static Date getDateAddDay(int add) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, add);
		return cal.getTime();
	}

	/**
	 * 增加小时
	 * 
	 * @param add
	 * @return
	 */
	public static Date getDateAddHour(int add) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, add);
		return cal.getTime();
	}

	/**
	 * string to date
	 * 
	 * @param strDate
	 * @param parrent
	 * @return
	 */
	public static Date parseDate(String strDate, String parrent) {

		ParsePosition pos = new ParsePosition(0);
		SimpleDateFormat fmtDate = new SimpleDateFormat(parrent);

		Date dtRet = null;
		try {
			return dtRet = fmtDate.parse(strDate, pos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtRet;
	}

	/**
	 * string to date
	 * 
	 * @param strDate
	 * @param parrent
	 * @return
	 * @throws Exception
	 */
	public static Date parse(String strDate, String pattern) throws Exception {
		if (StringUtils.isEmpty(strDate) || StringUtils.isEmpty(pattern)) {
			throw new IllegalArgumentException("The date must not be null");
		}
		datetimeFormat.applyPattern(pattern);
		Date dtRet = datetimeFormat.parse(strDate);
		return dtRet;
	}

	/**
	 * string to date
	 * 
	 * @param strDate
	 * @param parrent
	 * @return
	 * @throws Exception
	 */
	public static Date parse(String strDate) throws Exception {
		if (StringUtils.isEmpty(strDate)) {
			throw new IllegalArgumentException("The date must not be null");
		}
		datetimeFormat.applyPattern(DEFAULT_PARTTERN);
		Date dtRet = datetimeFormat.parse(strDate);
		return dtRet;
	}

	/**
	 * 得到当前日期
	 * 
	 * @return
	 */
	public static String now() {
		datetimeFormat.applyPattern(DEFAULT_PARTTERN);
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		return datetimeFormat.format(date);

	}

	/***
	 * 比较时间所差天数
	 * 
	 * @param s1
	 * @param s2
	 * @return long 类型
	 */
	public static long checkTime(String s1, String s2) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date now = df.parse(s1);
			java.util.Date date = df.parse(s2);
			long l = now.getTime() - date.getTime();
			long day = l / (24 * 60 * 60 * 1000);

			return day;
		} catch (Exception e) {
			return -1;
		}

	}

	/**
	 * <p>
	 * Title: getDateFormat
	 * </p>
	 * <p>
	 * Description:获取DateFormat
	 * </p>
	 * @param string
	 * @return
	 * @author cuidd
	 * @date 2012-12-13
	 * @return DateFormat
	 * @version 1.0
	 * @throws
	 */
	public static DateFormat getDateFormat(String string) {
		datetimeFormat.applyPattern(string);
		return datetimeFormat;
	}

	public static int compareDate(String date1, String date2) {
		int n = 0;
		String formatStyle = "yyyy-MM-dd";

		date2 = date2 == null ? now() : date2;

		DateFormat df = new SimpleDateFormat(formatStyle);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(df.parse(date1));
			c2.setTime(df.parse(date2));
		} catch (Exception e3) {
			System.out.println("wrong occured");
		}
		while (!c1.after(c2)) { // 循环对比，直到相等，n 就是所要的结果
			n++;
			c1.add(Calendar.DATE, 1); // 比较天数，日期+1
		}
		n = n - 1;
		return n;
	}

}
