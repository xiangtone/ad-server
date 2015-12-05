package cn.adwalker.core.util.lang;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

import cn.adwalker.ad.config.AppConstant;

public class MathUtil {

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
			return Double.parseDouble(str.substring(0, str.indexOf(".") + 1
					+ decimal));
		} else {
			return f;
		}
	}
	
	/**
	 * 只舍弃不入
	 * 
	 * @param f
	 * @param decimal
	 * @return
	 */
	public static double truncF(Double f) {
		if (f != null) {
			f = Math.floor(f * 100) / 100;
		}
		return f;
	}
	
	
	
	

	/**
	 * 四舍五入
	 * 
	 * @param f
	 * @param decimal
	 * @return
	 */
	public static double truncS(Double f, int decimal) {
		BigDecimal b = new BigDecimal(f);
		f = b.setScale(decimal, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f;
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
	 * 随机产生codeCount数字的随机码
	 * 
	 * @param codeCount
	 *            随机码位数
	 * @return
	 */
	public static String getRandomCode(int codeCount) {
		// 随机码序列
		char[] RANDOM_CODE_SEQUENCE = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
				'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
				'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5',
				'6', '7', '8', '9' };
		StringBuffer randomCode = new StringBuffer();
		Random random = new Random();
		// 随机产生codeCount数字的随机码。
		for (int i = 0; i < codeCount; i++) {
			// 得到随机产生的随机码数字。
			String strRand = String.valueOf(RANDOM_CODE_SEQUENCE[random
					.nextInt(RANDOM_CODE_SEQUENCE.length)]);
			randomCode.append(strRand);
		}
		return randomCode.toString();
	}

	/**
	 * 计算时间
	 * 
	 * @param s
	 *            毫秒
	 * @return
	 */
	public static String calculateTime(long s) {
		StringBuffer res = new StringBuffer();
		s = s / 1000;
		if (s / 60 == 0) {
			res.append(s).append("秒");
		} else if (s / 3600 == 0) {
			res.append(s / 60).append("分").append(s % 60).append("秒");
		} else {
			res.append(s / 3600).append("时");
			if ((s % 3600) / 60 == 0) {
				res.append((s % 3600) / 60).append("秒");
			} else {
				res.append((s % 3600) / 60).append("分").append((s % 3600) % 60)
						.append("秒");
			}
		}
		return res.toString();
	}

	/**
	 * 计算时间-格式化
	 * 
	 * @param millisecond
	 *            毫秒数
	 * @param format
	 *            格式化样式(HH:mm:ss或HH-mm-ss)
	 * @return
	 */
	public static String calculateTime(long millisecond, String format) {
		StringBuffer res = new StringBuffer();
		int h = (int) Math.floor(millisecond / 3600000);
		int m = (int) Math.floor(millisecond % 3600000 / 60000);
		int s = (int) Math.floor(millisecond % 60000 / 1000);

		if (format.equals("hh:mm:ss") || format.equals("HH:mm:ss")
				&& ObjectUtils.isNotEmpty(format)) {
			res.append(h).append(":").append(m).append(":").append(s);
		} else if (format.equals("hh-mm-ss") || format.equals("HH-mm-ss")
				&& ObjectUtils.isNotEmpty(format)) {
			res.append(h).append("-").append(m).append("-").append(s);
		} else {
			if (h > 0) {
				res.append(h).append("小时").append(m).append("分").append(s)
						.append("秒");
			} else {
				res.append(m).append("分").append(s).append("秒");
			}
		}
		return res.toString();
	}

	/**
	 * 截取相对路径
	 * 
	 * @param url
	 *            绝对地址
	 * @return
	 */
	public static String urlInterception(String url) {
		String uri = "";
		if (url != null) {
			int beginIndex = url.lastIndexOf("/");
			uri = url.substring(beginIndex, url.length());
		}
		return uri;
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
	 * BigDecimal 相加
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static Double add(Double v1, Double v2) {
		Double d = null;
		if (v1 != null && v2 != null) {
			BigDecimal n1 = new BigDecimal(Double.toString(v1));
			BigDecimal n2 = new BigDecimal(Double.toString(v2));
			d = n1.add(n2).doubleValue();

		}
		return d;
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
	 * BigDecimal 相减
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal subtractBig(BigDecimal v1, BigDecimal v2) {
		if(v1!=null&& v2!=null){
			return v1.subtract(v2);
		}else{
			return null;
		}
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
		return n1.divide(n2, AppConstant.MONEY_POINT, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
	}
	
	public static BigDecimal divideBig(BigDecimal v1, BigDecimal v2) {
		if(v1!=null&& v2!=null){
			return v1.divide(v2, AppConstant.MONEY_POINT, BigDecimal.ROUND_HALF_UP);
		}else{
			return null;
		}
	}
	
	/**
	 * BigDecimal 相乘
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal multiplyInt(BigDecimal v1, double v2) {
		BigDecimal n2 = new BigDecimal(Double.toString(v2));
		return v1.multiply(n2);
	}
	
	/**
	 * BigDecimal比较大小
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static Integer compare(double v1, double v2) {
		BigDecimal   data1   =   new   BigDecimal(v1); 
		BigDecimal   data2   =   new   BigDecimal(v2); 
		return data1.compareTo(data2);
	}
}
