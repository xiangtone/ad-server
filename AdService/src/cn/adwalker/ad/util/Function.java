package cn.adwalker.ad.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class Function {
	/**
	 * 只舍弃不入
	 * 
	 * @param f
	 * @param decimal
	 * @return
	 */
	public static double truncF(Double f, int decimal) {
		String str = f.toString();
		if (str.substring(str.indexOf(".")).length() > decimal) {
			return Double.parseDouble(str.substring(0, str.indexOf(".") + 1 + decimal));
		} else {
			return f;
		}
	}
	public static String generateUID() {
		Random randomForUid = new Random(System.currentTimeMillis());
		long current = randomForUid.nextLong();
		byte[] timeBytes = new byte[8];
		for (int i = 0; i < 8; i++) {
			timeBytes[i] = (byte) (current >> (i * 8));
		}
		UUID uuid = UUID.nameUUIDFromBytes(timeBytes);
		return uuid.toString();
	}

	/**
	 * 一天一张表的表名称
	 * 
	 * @param tableNamePrefix
	 *            表名称前缀
	 * @return
	 */
	public static String getTableNameDay(String tableNamePrefix) {
		StringBuffer result = new StringBuffer(tableNamePrefix).append("_");
		Calendar c = Calendar.getInstance();
		result.append(new java.text.SimpleDateFormat("yyyyMMdd").format(c.getTime()));
		return result.toString();
	}

	/**
	 * 一小时一张表的表名称
	 * 
	 * @param tableNamePrefix
	 *            表名称前缀
	 * @return
	 */
	public static String getTableNameHour(String tableNamePrefix) {
		StringBuffer result = new StringBuffer(tableNamePrefix).append("_");
		Calendar c = Calendar.getInstance();
		result.append(new java.text.SimpleDateFormat("yyyyMMdd").format(c.getTime()));
		result.append("_");
		result.append(c.get(Calendar.HOUR_OF_DAY) + 1);
		return result.toString();
	}

	/**
	 * BigDecimal 相加
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static Double add(double v1, double v2) {
		BigDecimal n1 = new BigDecimal(Double.toString(v1));
		BigDecimal n2 = new BigDecimal(Double.toString(v2));
		return n1.add(n2).doubleValue();
	}

	/**
	 * BigDecimal 相减
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static Double subtract(double v1, double v2) {
		BigDecimal n1 = new BigDecimal(Double.toString(v1));
		BigDecimal n2 = new BigDecimal(Double.toString(v2));
		return n1.subtract(n2).doubleValue();
	}

	/**
	 * BigDecimal 相乘
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static Double multiply(double v1, double v2) {
		BigDecimal n1 = new BigDecimal(Double.toString(v1));
		BigDecimal n2 = new BigDecimal(Double.toString(v2));
		return n1.multiply(n2).doubleValue();
	}

	/**
	 * BigDecimal 相除
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static Double divide(double v1, double v2) {
		BigDecimal n1 = new BigDecimal(Double.toString(v1));
		BigDecimal n2 = new BigDecimal(Double.toString(v2));
		return n1.divide(n2, AppConstant.MONEY_POINT, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static void main(String[] args) {
		System.out.println(divide(1, 3));
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String state_data = df.format(date);
		System.out.println(state_data);
	}

}
