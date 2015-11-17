/**
 * <p>Title: WeekUtil.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-8-19
 * @version 1.0
 */
package cn.adwalker.core.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


/**
 * <p>
 * Title: WeekUtil
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-8-19
 */
public abstract class WeekUtil {

	/**
	 * <p>
	 * Title: 周计算类，星期一为一周的开始，星期日为一周的结束
	 * </p>
	 * <p>
	 * Description: 在两年的交接地带还有疑问。
	 * </p>
	 * <p>
	 * 比如2006-12-29到2009-01-04，属于2008年的最后一周，
	 * </p>
	 * <p>
	 * 2009-01-05位2009年第一周的开始。
	 * </p>
	 * <p>
	 * db2种的week_iso也是这样计算的
	 * </p>
	 * <p>
	 * Copyright: Copyright (c) 2006
	 * </p>
	 * <p>
	 * DateTime: 2006-4-11 23:36:39
	 * </p>
	 * 
	 * @author gumpgz
	 * @version 1.0
	 */
	public WeekUtil() {

	}

	/**
	 * 
	 * <p>
	 * Title: getWeek
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param date
	 * @return
	 * @author cuidd
	 * @date 2013-12-29
	 * @return int
	 * @version 1.0
	 */
	public static int getWeek(Date date) {
		int week;
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);// 注意此处不能漏掉
		c.setTime(date);
		// 解决跨年问题
		if (getYear(DateUtil.addDays(c.getTime(), 7)) > getYear(c.getTime())) {
			c.add(Calendar.DATE, -7);
			System.out.println("***********" + c.get(Calendar.WEEK_OF_YEAR));
			week = c.get(Calendar.WEEK_OF_YEAR) + 1;
		} else {
			week = c.get(Calendar.WEEK_OF_YEAR);
		}

		System.out.println(DateUtil.formatDate(c.getTime()));

		return week;
	}

	public static int getYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	/**
	 * 取得当前日期是多少周
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		return c.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 得到某一年周的总数
	 * 
	 * @param year
	 * @return
	 */
	public static int getMaxWeekNumOfYear(int year) {
		Calendar c = new GregorianCalendar();
		c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
		return getWeekOfYear(c.getTime());
	}

	/**
	 * 得到某年某周的第一天
	 * 
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getFirstDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);
		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE, week * 7);
		return getFirstDayOfWeek(cal.getTime());
	}

	/**
	 * 得到某年某周的最后一天
	 * 
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getLastDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);
		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE, week * 7);
		return getLastDayOfWeek(cal.getTime());
	}

	/**
	 * 取得当前日期所在周的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime();
	}

	public static Date getFirstDayOfWeek(int week) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(new Date());
		c.set(Calendar.WEEK_OF_YEAR, week);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime();
	}

	/**
	 * 取得当前日期所在周的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}

	public static Date getLastDayOfWeek(int week) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(new Date());
		c.set(Calendar.WEEK_OF_YEAR, week);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}

	/**
	 * 
	 * <p>
	 * Title: getSeason
	 * </p>
	 * <p>
	 * Description:获取季度
	 * </p>
	 * 
	 * @param date
	 * @return
	 * @author cuidd
	 * @date 2013-8-20
	 * @return Integer
	 * @version 1.0
	 */
	public static Integer getSeason(Date date) {
		Integer season = null;
		if (date != null) {
			Calendar c = new GregorianCalendar();
			c.setTime(date);
			int month = c.get(Calendar.MONTH) + 1;
			if (month == 1 || month == 2 || month == 3) {
				season = 1;
			} else if (month == 4 || month == 5 || month == 6) {
				season = 2;
			} else if (month == 7 || month == 8 || month == 9) {
				season = 3;
			} else if (month == 10 || month == 11 || month == 12) {
				season = 4;
			}
		}
		return season;
	}

	public static Date getFirsetDayOfSeason(Integer season) {
		Date date = null;
		if (season != null) {
			Integer month = null;
			if (season == 1) {
				month = 1;
			} else if (season == 2) {
				month = 4;
			} else if (season == 3) {
				month = 7;
			} else if (season == 4) {
				month = 10;
			}
			Calendar c = new GregorianCalendar();
			c.set(Calendar.MONTH, month - 1);
			c.set(Calendar.DAY_OF_MONTH, 1);
			date = c.getTime();
		}
		return date;
	}

	public static Date getLastDayOfSeason(Integer season) {
		Date date = null;
		if (season != null) {
			Integer month = null;
			if (season == 1) {
				month = 3;
			} else if (season == 2) {
				month = 6;
			} else if (season == 3) {
				month = 9;
			} else if (season == 4) {
				month = 12;
			}
			Calendar c = new GregorianCalendar();
			c.set(Calendar.MONTH, month - 1);
			c.set(Calendar.DAY_OF_MONTH, getMonthLastDay(c.getTime()));
			date = c.getTime();

		}
		return date;
	}

	public static int getMonthLastDay(Date date) {
		Calendar a = Calendar.getInstance();
		a.setTime(date);
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	public static int getTotalWeekOfYear() {
		Calendar c = Calendar.getInstance();
		return c.getActualMaximum(Calendar.WEEK_OF_YEAR);
	}

	public static List<Integer> getWeekListBySeason(Integer season) {
		List<Integer> list = new ArrayList<Integer>();
		int s = WeekUtil.getWeek(WeekUtil.getFirsetDayOfSeason(season));
		int e = WeekUtil.getWeek(WeekUtil.getLastDayOfSeason(season));
		for (int i = s; i <= e; i++) {
			list.add(i);
		}
		return list;
	}

	public static void main(String[] args) throws Exception {
		
	}
}