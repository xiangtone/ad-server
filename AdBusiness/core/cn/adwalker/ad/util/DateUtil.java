package cn.adwalker.ad.util;import java.text.ParseException;import java.text.ParsePosition;import java.text.SimpleDateFormat;import java.util.Calendar;import java.util.Date;import java.util.Locale;import org.apache.commons.lang.StringUtils;import org.apache.commons.lang.time.DateUtils;import org.apache.log4j.Logger;/** * 日期处理工具类. */public class DateUtil extends DateUtils {	private static Logger logger = Logger.getLogger(DateUtil.class);	/**	 * 缺省的日期格式	 */	private static final String DAFAULT_DATE_FORMAT = "yyyy-M-d";	public static final String DATE_FORMAT = "yyyy-MM-dd";	/**	 * 默认日期类型格试.	 * 	 * @see DAFAULT_DATE_FORMAT	 */	private static SimpleDateFormat dateFormat = new SimpleDateFormat(DAFAULT_DATE_FORMAT);	/**	 * 缺省的日期时间格式	 */	private static final String DAFAULT_DATETIME_FORMAT = "yyyy-M-d HH:mm:ss";	/**	 * 时间格式	 */	private static String DATETIME_FORMAT = DAFAULT_DATETIME_FORMAT;	private static SimpleDateFormat datetimeFormat = new SimpleDateFormat(DATETIME_FORMAT);	/**	 * 缺省的时间格式	 */	public static final String DAFAULT_TIME_FORMAT = "HH:mm:ss";	private static final String TIME_FORMAT = "yyyy-MM-dd";	private static SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT);	private DateUtil() {		// 私用构造主法.因为此类是工具类.	}	public static Date getMonday(Date date) {		Calendar c = Calendar.getInstance(Locale.CHINA);		c.setTime(date);		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);		c.set(Calendar.HOUR_OF_DAY, 0);		c.set(Calendar.MINUTE, 0);		c.set(Calendar.SECOND, 0);		c.set(Calendar.MILLISECOND, 0);		return new Date(c.getTimeInMillis());	}	/**	 * 获取格式化实例.	 * 	 * @param pattern	 *            如果为空使用DAFAULT_DATE_FORMAT	 * @return	 */	public static SimpleDateFormat getFormatInstance(String pattern) {		if (pattern == null || pattern.length() == 0) {			pattern = DAFAULT_DATE_FORMAT;		}		return new SimpleDateFormat(pattern);	}	/**	 * 格式化Calendar	 * 	 * @param calendar	 * @return	 */	public static String formatCalendar(Calendar calendar) {		if (calendar == null) {			return "";		}		return dateFormat.format(calendar.getTime());	}	public static String formatDateTime(Date d) {		if (d == null) {			return "";		}		return datetimeFormat.format(d);	}	public static String formatDate(Date d) {		if (d == null) {			return "";		}		return dateFormat.format(d);	}	/**	 * 格式化时间	 * 	 * @param calendar	 * @return	 */	public static String format(Date d) {		if (d == null) {			return "";		}		timeFormat.applyPattern(TIME_FORMAT);		return timeFormat.format(d);	}	/**	 * 格式化时间	 * 	 * @param calendar	 * @return	 */	public static String formatTime(Date d) {		if (d == null) {			return "";		}		return timeFormat.format(d);	}	/**	 * 格式化整数型日期	 * 	 * @param intDate	 * @return	 */	public static String formatIntDate(Integer intDate) {		if (intDate == null) {			return "";		}		Calendar c = newCalendar(intDate);		return formatCalendar(c);	}	/**	 * 根据指定格式化来格式日期.	 * 	 * @param date	 *            待格式化的日期.	 * @param pattern	 *            格式化样式或分格,如yyMMddHHmmss	 * @return 字符串型日期.	 */	public static String formatDate(Date date, String pattern) {		if (date == null) {			return "";		}		if (StringUtils.isBlank(pattern)) {			return formatDate(date);		}		SimpleDateFormat simpleDateFormat = null;		try {			simpleDateFormat = new SimpleDateFormat(pattern);		} catch (Exception e) {			e.printStackTrace();			return formatDate(date);		}		return simpleDateFormat.format(date);	}	/**	 * 取得Integer型的当前日期	 * 	 * @return	 */	public static Integer getIntNow() {		return getIntDate(getNow());	}	/**	 * 取得Integer型的当前日期	 * 	 * @return	 */	public static Integer getIntToday() {		return getIntDate(getNow());	}	/**	 * 取得Integer型的当前年份	 * 	 * @return	 */	public static Integer getIntYearNow() {		Calendar c = Calendar.getInstance();		int year = c.get(Calendar.YEAR);		return year;	}	/**	 * 取得Integer型的当前月份	 * 	 * @return	 */	public static Integer getIntMonthNow() {		Calendar c = Calendar.getInstance();		int month = c.get(Calendar.MONTH) + 1;		return month;	}	public static String getStringToday() {		return getIntDate(getNow()) + "";	}	/**	 * 根据年月日获取整型日期	 * 	 * @param year	 * @param month	 * @param day	 * @return	 */	public static Integer getIntDate(int year, int month, int day) {		return getIntDate(newCalendar(year, month, day));	}	/**	 * 某年月的第一天	 * 	 * @param year	 * @param month	 * @return	 */	public static Integer getFirstDayOfMonth(int year, int month) {		return getIntDate(newCalendar(year, month, 1));	}	/**	 * 某年月的第一天	 * 	 * @param year	 * @param month	 * @return	 */	public static Integer getFirstDayOfThisMonth() {		Integer year = DateUtil.getIntYearNow();		Integer month = DateUtil.getIntMonthNow();		return getIntDate(newCalendar(year, month, 1));	}	/**	 * 某年月的第一天	 * 	 * @param date	 * @return	 * @time:2008-7-4 上午09:58:55	 */	public static Integer getFistDayOfMonth(Date date) {		Integer intDate = getIntDate(date);		int year = intDate / 10000;		int month = intDate % 10000 / 100;		return getIntDate(newCalendar(year, month, 1));	}	/**	 * 某年月的最后一天	 * 	 * @param year	 * @param month	 * @return	 */	public static Integer getLastDayOfMonth(int year, int month) {		return intDateSub(getIntDate(newCalendar(year, month + 1, 1)), 1);	}	/**	 * 根据Calendar获取整型年份	 * 	 * @param c	 * @return	 */	public static Integer getIntYear(Calendar c) {		int year = c.get(Calendar.YEAR);		return year;	}	/**	 * 根据Calendar获取整型日期	 * 	 * @param c	 * @return	 */	public static Integer getIntDate(Calendar c) {		int year = c.get(Calendar.YEAR);		int month = c.get(Calendar.MONTH) + 1;		int day = c.get(Calendar.DAY_OF_MONTH);		return year * 10000 + month * 100 + day;	}	/**	 * 根据Date获取整型年份	 * 	 * @param d	 * @return	 */	public static Integer getIntYear(Date d) {		if (d == null) {			return null;		}		Calendar c = Calendar.getInstance();		c.setTime(d);		return getIntYear(c);	}	/**	 * 根据Date获取整型日期	 * 	 * @param d	 * @return	 */	public static Integer getIntDate(Date d) {		if (d == null) {			return null;		}		Calendar c = Calendar.getInstance();		c.setTime(d);		return getIntDate(c);	}	/**	 * 根据Integer获取Date日期	 * 	 * @param n	 * @return	 */	public static Date getDate(Integer n) {		if (n == null) {			return null;		}		Calendar c = Calendar.getInstance();		c.set(n / 10000, n / 100 % 100 - 1, n % 100);		return c.getTime();	}	public static Date getDate(String date) {		if (date == null || date.length() == 0) {			return null;		}		try {			if (date.contains("/")) {				date = date.replaceAll("/", "-");			}			return getFormatInstance(DATE_FORMAT).parse(date);		} catch (ParseException e) {			logger.error("解析[" + date + "]错误！", e);			return null;		}	}	/**	 * 根据年份Integer获取Date日期	 * 	 * @param year	 * @return	 */	public static Date getFirstDayOfYear(Integer year) {		if (year == null) {			return null;		}		Calendar c = Calendar.getInstance();		c.set(year, 1, 1);		return c.getTime();	}	/**	 * 根据年月日生成Calendar	 * 	 * @param year	 * @param month	 * @param day	 * @return	 */	public static Calendar newCalendar(int year, int month, int day) {		Calendar ret = Calendar.getInstance();		if (year < 100) {			year = 2000 + year;		}		ret.set(year, month - 1, day);		return ret;	}	/**	 * 根据整型日期生成Calendar	 * 	 * @param date	 * @return	 */	public static Calendar newCalendar(int date) {		int year = date / 10000;		int month = (date % 10000) / 100;		int day = date % 100;		Calendar ret = Calendar.getInstance();		ret.set(year, month - 1, day);		return ret;	}	/**	 * 取得Date型的当前日期	 * 	 * @return	 */	public static Date getNow() {		return new Date();	}	/**	 * 取得Date型的当前日期	 * 	 * @return	 */	public static Date getToday() {		return DateUtil.getDate(DateUtil.getIntToday());	}	/**	 * 整数型日期的加法	 * 	 * @param date	 * @param days	 * @return	 */	public static Integer intDateAdd(int date, int days) {		int year = date / 10000;		int month = (date % 10000) / 100;		int day = date % 100;		day += days;		return getIntDate(year, month, day);	}	/**	 * 整数型日期的减法	 * 	 * @param date	 * @param days	 * @return	 */	public static Integer intDateSub(int date, int days) {		return intDateAdd(date, -days);	}	/**	 * 计算两个整型日期之间的天数	 * 	 * @param startDate	 * @param endDate	 * @return	 */	public static Integer daysBetweenDate(Integer startDate, Integer endDate) {		if (startDate == null || endDate == null) {			return null;		}		Calendar c1 = newCalendar(startDate);		Calendar c2 = newCalendar(endDate);		Long lg = (c2.getTimeInMillis() - c1.getTimeInMillis()) / 1000 / 60 / 60 / 24;		return lg.intValue();	}	/**	 * 计算两个整型日期之间的天数	 * 	 * @param startDate	 * @param endDate	 * @return	 */	public static Integer daysBetweenDate(Date startDate, Date endDate) {		if (startDate == null || endDate == null) {			return null;		}		Long interval = endDate.getTime() - startDate.getTime();		interval = interval / (24 * 60 * 60 * 1000);		return interval.intValue();	}	/**	 * 取得当前日期.	 * 	 * @return 当前日期,字符串类型.	 */	public static String getStringDate() {		return getStringDate(DateUtil.getNow());	}	/**	 * 根据calendar产生字符串型日期	 * 	 * @param d	 * @return eg:20080707	 */	public static String getStringDate(Date d) {		if (d == null) {			return "";		}		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");		return sdf.format(d);	}	public static String getFormatStringDate(Date d) {		if (d == null) {			return "";		}		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");		return sdf.format(d);	}	/**	 * 将当前日期转换为指定格式的字符串	 * 	 * @param pattern	 *            格式	 * @return String	 */	public static String getDateStringByPattern(String pattern) {		Date date = new Date();		return getDateStringByPattern(date, pattern);	}	/**	 * 将指定日期转换为指定格式的字符串	 * 	 * @param date	 *            ( java.util.Date )	 * @param pattern	 *            日期格式，如："yyyy-MM-dd" ，"yyyy-MM-dd HH:mm:ss"，"HH:mm:ss"	 * @return String	 */	public static String getDateStringByPattern(Date date, String pattern) {		if (date == null) {// 默认今天			date = new Date();		}		SimpleDateFormat sf = new SimpleDateFormat(pattern);		String str = sf.format(date);		return str;	}	/**	 * 日期累加	 * 	 * @param add	 *            可以是负数表示日期减少	 * @return	 */	public static Date getDateAddDay(int add) {		Calendar cal = Calendar.getInstance();		cal.add(Calendar.DATE, add);		return cal.getTime();	}	/**	 * 增加小时	 * 	 * @param add	 * @return	 */	public static Date getDateAddHour(int add) {		Calendar cal = Calendar.getInstance();		cal.add(Calendar.HOUR, add);		return cal.getTime();	}	/**	 * 增加时分秒	 * 	 * @param add	 * @return	 */	public static Date getDateAddHourMS(Date date, int a, int b, int c) {		Calendar cal = Calendar.getInstance();		cal.setTime(date);		cal.add(Calendar.HOUR, a);		cal.add(Calendar.MINUTE, b);		cal.add(Calendar.SECOND, c);		return cal.getTime();	}	/**	 * string to date	 * 	 * @param strDate	 * @param parrent	 * @return	 */	public static Date parseDate(String strDate, String parrent) {		ParsePosition pos = new ParsePosition(0);		SimpleDateFormat fmtDate = new SimpleDateFormat(parrent);		Date dtRet = null;		try {			return dtRet = fmtDate.parse(strDate, pos);		} catch (Exception e) {			e.printStackTrace();		}		return dtRet;	}	public static long getTimeInterval(String startDate, String endDate) {		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");		ParsePosition pos = new ParsePosition(0);		ParsePosition pos1 = new ParsePosition(0);		Date dt1 = formatter.parse(startDate, pos);		Date dt2 = formatter.parse(endDate, pos1);		long l = dt2.getTime() - dt1.getTime();		long date = l / (60 * 60 * 24 * 1000);		return date;	}	public static void main(String[] args) throws ParseException {		Date date = parseDate("2011-12-30", "yyyy-MM-dd");		Date date1 = getDateAddHourMS(date, 5, 22, 33);		SimpleDateFormat fmtDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		date1 = fmtDate.parse(fmtDate.format(date1));		System.out.println(getDateStringByPattern(date1, "yyyy-MM-dd HH:mm:ss"));	}}