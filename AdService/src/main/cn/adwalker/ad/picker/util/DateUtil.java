package cn.adwalker.ad.picker.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class DateUtil {
	private static Long DAY_TIMES=24*60*60*1000l;
	private static Long HOUR_TIMES=60*60*1000l;
	private static Long MIN_TIMES=60*1000l;
	private static Long SECOND_TIMES=1000l;
	private static String FORMAT_YYMMDD="yyyy-MM-dd";
	private static String FORMAT_YYMMDDHHMMSS="yyyy-MM-dd HH:mm:ss";
	private static String FORMAT_YYMMDDHHMMSSSSS="yyyy-MM-dd HH:mm:ss:sss";
	private static final DateFormat[] ACCEPT_DATE_FORMATS = { 
		 new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS"),
		 new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
		 new SimpleDateFormat("yyyy-MM-dd"),
		 new SimpleDateFormat("dd/MM/yyyy"), 
		 new SimpleDateFormat("yyyy/MM/dd"),
		 new SimpleDateFormat("yyyy.MM.dd"),
		 new SimpleDateFormat("yyyyMMdd")
		};// 支持转换的日期格式
	public static Date getDate(String dateStr){
		if(dateStr==null||"".equals(dateStr)) return null;
		for (DateFormat format : ACCEPT_DATE_FORMATS) { 
			try { 
			return format.parse(dateStr);
			} catch(Exception e) { 
			continue; 
			} 
		} 
		return null;
	}
	public static Date getDate(String dateStr,String format){
		format=StringUtil.isEmpty(format)?FORMAT_YYMMDD:format;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date d = null;
	    try {
			d = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	public static Date getDate(Date cdate, String strFormat) {
		String date = getFormatDate(cdate, strFormat);
		return getDate(date, "yyyy-MM-dd HH:mm:ss");
	}
	public static String getFormatDate(Date d,String format){
		format=StringUtil.isEmpty(format)?FORMAT_YYMMDD:format;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return (d==null)?null:sdf.format(d);
	}
	public static Date get00HourDate(Date d){
		return d!=null?DateUtil.getDate(DateUtil.getFormatDate(d, "yyyy-MM-dd 00:00:00"), FORMAT_YYMMDDHHMMSS):null;
	}
	public static int getWeek(Date date) {
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}
	public static Date absoluteDate(Date date, int day) {
		if (date == null) {
			return new Date();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, day);
		return cal.getTime();
	}
	public static Date absoluteHour(Date date, int hour) {
		if (date == null) {
			return new Date();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, hour);
		return cal.getTime();
	}
	public static Date absoluteMonth(Date date, int Month) {
		if (date == null) {
			return new Date();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, Month);
        return cal.getTime();

	}
	public static Date absoluteYear(Date date, int Year) {
		if (date == null) {
			return new Date();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, Year);
        return cal.getTime();

	}
	public static int getSeansonNum(Date date) {
		int month = Integer.valueOf(getFormatDate(date, "MM")); 
		int[] seansonArr1 = {1,2,3};
		int[] seansonArr2 = {4,5,6};
		int[] seansonArr3 = {7,8,9};
		int[] seansonArr4 = {10,11,12};
		for(int i=0;i<seansonArr1.length;i++){
			if(seansonArr1[i]==month){
				return 1;
			}
		}
		for(int i=0;i<seansonArr2.length;i++){
			if(seansonArr2[i]==month){
				return 2;
			}
		}
		for(int i=0;i<seansonArr3.length;i++){
			if(seansonArr3[i]==month){
				return 3;
			}
		}
		for(int i=0;i<seansonArr4.length;i++){
			if(seansonArr4[i]==month){
				return 4;
			}
		}
		return 0;
	}
	public static Date absoluteWeek(Date date, int Week) {
		if (date == null) {
			return new Date();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.WEEK_OF_YEAR, Week);
        return cal.getTime();

	}
	public static boolean beforeDate(Date currentDate,Date comDate){
		if(comDate==null || currentDate==null){
			return false;
		}else{
			return currentDate.before(comDate);
		}
	}
	public static Date get24HourDate(Date d){
		return d!=null?DateUtil.getDate(DateUtil.getFormatDate(d, "yyyy-MM-dd 23:59:59:999"), FORMAT_YYMMDDHHMMSSSSS):null;
	}
	public static Long getNumbersByTwoDate(Date bDate,Date eDate,Long parameter){
		parameter=StringUtil.isEmpty(parameter)?DAY_TIMES:parameter;
		return (eDate.getTime()-bDate.getTime())/parameter;
	}
	public static Long getDayBytwoDate(Date bDate,Date eDate){
	    return getNumbersByTwoDate(bDate,eDate,DAY_TIMES);
	}
	public static Long getHourByTwoDate(Date bDate,Date eDate){
		return getNumbersByTwoDate(bDate,eDate,HOUR_TIMES);
	}
	public static Long getMinByTwoDate(Date bDate,Date eDate){
		return getNumbersByTwoDate(bDate, eDate, MIN_TIMES);
	}
	public static Long getSecondByTwoDate(Date bDate,Date eDate){
		return getNumbersByTwoDate(bDate,eDate,SECOND_TIMES);
	}
	public static Date getFristDayOfWeek(){
		Calendar c  = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, 1);
		return c.getTime();
	}
	public static Date getFistDayOfMonth(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}
	public static Date getFistDayOfYear(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_YEAR, 1);
		return c.getTime();
	}
	public static Date getAddNumbersDay(Date date,int i,Long parameter){
		if(date==null){
			return null;
		}else{
			parameter = StringUtil.isEmpty(parameter)?DAY_TIMES:parameter;
			date.setTime(date.getTime()+i*parameter);
			return date;	
		}
	}
	public static Date getAddDayByDay(Date date,int i){
		return getAddNumbersDay(date, i,DAY_TIMES);
	}
	public static Date getAddHourByDate(Date date,int i){
		return getAddNumbersDay(date, i,HOUR_TIMES);
	}
	public static Date getAddMinByDate(Date date,int i){
		return getAddNumbersDay(date,i,MIN_TIMES);
	}
	public static int gethourNumber(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
	    return cal.get(Calendar.HOUR_OF_DAY);
	}
	public static String getHourString(Date date){
		int h = gethourNumber(date);
		return h<10?"0"+h:String.valueOf(h);
	}
	public static String timeStampString(){
		return DateUtil.getFormatDate(new Date(), "yyyyMMddhhmmss");
	}
}
