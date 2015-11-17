package cn.adwalker.IOSChannel.picker.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;



public class NumberUtil {
	private final static String DEFAULT_FORMAT = "0.00";

	public static int getInt(Integer value, int defVal) {
		return value == null ? defVal : value;
	}
	public static int getInt(Object value, int defVal) {
		if (value != null) {
			if (value instanceof Long) {
				return ((Long) value).intValue();
			}
			if (value instanceof Integer) {
				return (Integer) value;
			}
			if (value instanceof BigDecimal) {
				return ((BigDecimal) value).intValue();
			}
			if (value instanceof String) {
				return Integer.valueOf((String)value);
			}
			return Integer.valueOf(value.toString());
		}
		return defVal;
	}
	public static Integer getInteger(Object value, Integer defVal) {
		if (!StringUtil.isEmpty(value)) {
			if (value instanceof Long) {
				return ((Long) value).intValue();
			}
			if (value instanceof Integer) {
				return (Integer) value;
			}
			if (value instanceof BigDecimal) {
				return ((BigDecimal) value).intValue();
			}
			if (value instanceof String) {
				return Integer.valueOf((String)value);
			}
			return Integer.valueOf(value.toString());
		}
		return defVal;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Number> T getNumber(Number value, Number defVal, Class <T> toType) {
		if (value != null) {
			if (toType.isAssignableFrom(Long.class)) {
				return (T) (value instanceof Long ? value : new Long(value.longValue()));
			} else if (toType.isAssignableFrom(Integer.class)) {
				return (T) (value instanceof Integer ? value : new Integer(value.intValue()));
			} else if (toType.isAssignableFrom(Double.class)) {
				return (T) (value instanceof Double ? value : new Double(value.doubleValue()));
			} else if (toType.isAssignableFrom(Float.class)) {
				return (T) (value instanceof Float ? value : new Float(value.floatValue()));
			}
		}
		return (T) defVal;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Number> T getNumber(String value, Number defVal, Class <T> toType) {
		if (value != null) {
			if (toType.isAssignableFrom(Long.class)) {
				return (T) new Long(value);
			} else if (toType.isAssignableFrom(Integer.class)) {
				return (T) new Integer(value);
			} else if (toType.isAssignableFrom(Double.class)) {
				return (T) new Double(value);
			} else if (toType.isAssignableFrom(Float.class)) {
				return (T) new Float(value);
			}
		}
		return (T) defVal;
	}	
	public static Double getDouble(String value){
		if (value != null) {
			return Double.parseDouble(value);
		}
		return null;
	}
	public static long getLong(Long value, long defVal) {
		return value == null ? defVal : value;
	}

	public static long getLong(Integer value, long defVal) {
		return value == null ? defVal : value;
	}
	
	public static long getLong(String value, long defVal) {
		return value == null ? defVal : Long.valueOf(value);
	}
	
	public static Long parseLong(String value,Long defVal){
		try{
			return Long.parseLong(value);
		}catch(Exception e){
			return defVal;
		}
	}
	
	public static Long getLong(Object value) {
		return getLong(value, null);
	}
	
	public static Long getLong(Object value, Long defVal) {
		if (value != null) {
			if (value instanceof String) {
				return Long.parseLong((String) value);
			} else if (value instanceof Number) {
				return ((Number) value).longValue();
			}
		}
		return defVal;
	}

	public static double getDouble(Double value, double defVal) {
		return value == null ? defVal : value;
	}

	public static double getDouble(Object value, double defVal) {
		if (value != null) {
			if (value instanceof String) {
				return Double.parseDouble((String) value);
			} else if (value instanceof Number) {
				return ((Number) value).doubleValue();
			}
		}
		return defVal;
	}

	public static double format(double number, String format) {
		DecimalFormat df = new DecimalFormat(StringUtil.dealNull(format, DEFAULT_FORMAT));
		String str = df.format(number);
		return Double.valueOf(str);
	}
	
	/**
	 * @description 四舍五入保留指定小数位
	 * @author 毛朝阳
	 * @date Nov 14, 2008
	 * @param number
	 * @param bit
	 * @return
	 */
	public static double round(double number, int bit){
		double n = Math.pow(10, bit);
		int b = (int)Math.round(number * n); //小数点后n位前移，并四舍五入 
		return (double)b / n; //还原小数点后n位
	}

	public static void main(String[] args) {
		System.out.println(round(23.677, 2));
	}
}
