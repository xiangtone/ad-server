package cn.adwalker.ad.util;

public class IMEIValidate {

	/**
	 * 验证手机id是否合法
	 * @param str
	 * @return
	 */
	public static boolean validate(String str){ 
		if(validateIMEI(str)){
			return true;
		}else{
			return validateMEID(str);
		}
	}
	
	/**
	 * imei的验证算法
	 * imei为15位数字码，其中前14位为串码，第15位为比对码
	 * 对前14位奇数求和，偶数乘2（如果结果大于10，则用结果乘2后的个位与十位相加）求和
	 * 奇数位和与偶数位和求和乘9的最后一位与比对码比对，相等则为真
	 * @param imei
	 * @return
	 */
	private static boolean validateIMEI(String imei){
		int digit = 0;//IMEI验证对应码
		int countOddNum = 0;// 逆向奇数位数字总和
		int countEvenNum = 0;// 逆向偶数位数字总和

//		String imei =  "864589005702995";//"351565051400684";//"860173010887184";//
		char[] chars = imei.toCharArray();
		if (chars == null || chars.length != 15)
			return false;
		if (imei.equals("000000000000000"))
			return false;

		digit = chars[chars.length - 1] - '0';

		// 通过循环，得到逆向奇数位数字总和
		for (int i = chars.length -2; i > 0; i = i - 2) {
			countOddNum += (chars[i -1] - '0');// 得到该逆向奇数位数字并进行累加
			int perEvenNum = 2 * (chars[i] - '0');// 得到该逆向偶数位数字
			if (perEvenNum > 9)// 判断该偶数位数字是否大于9
				perEvenNum -= 9;
			countEvenNum += perEvenNum;// 将逆向偶数位数字进行累加
		}
		
		String multiply = Integer.toString((countOddNum + countEvenNum) * 9);
		String lastNum = multiply.substring(multiply.length() - 1);
		if (lastNum.equals(Integer.toString(digit)))
			return true;
		else
			return false;
	}
	
	/**
	 * MEID的验证算法
	 * MEID为15位数字码，其中前14位为串码，第15位为比对码
	 * 对前14位奇数求和，偶数乘2（如果结果大于10，则用结果乘2后的个位与十位相加）求和
	 * 奇数位和与偶数位和求和乘9的最后一位与比对码比对，相等则为真
	 * 如果发送过来的MEID为14位，则直接通过
	 * @param imei
	 * @return
	 */
	public static boolean validateMEID(String meid){
		String digit = "";
		int countOddNum = 0;// 奇数位数字总和
		int countEvenNum = 0;// 偶数位数字总和

		if (meid != null && meid.length() == 14 && meid.toUpperCase().startsWith("A")){
			return true;
		}else{
			digit = meid.substring(meid.length()-1);
			meid = meid.substring(0,meid.length()-1);
			char[] chars = meid.toCharArray();
			
			for (int i = 0; i < chars.length; i = i + 2) {
				countOddNum += Integer.parseInt(chars[i] + "", 16);
				int perEvenNum = 2 * (Integer.parseInt(chars[i+1] + "", 16));// 得到该逆向偶数位数字
				String perEvenStr = Integer.toString(perEvenNum, 16);
				
				if(perEvenStr.length() == 2)
					perEvenNum = Integer.parseInt(perEvenStr.substring(0, 1), 16) + Integer.parseInt(perEvenStr.substring(1), 16);
				else
					perEvenNum = Integer.parseInt(perEvenStr, 16);
				countEvenNum += perEvenNum;// 将逆向偶数位数字进行累加
			}

			String sum16 = Integer.toString(countEvenNum+countOddNum, 16);
			int sum16gint = Integer.parseInt(sum16.substring(sum16.length()-1), 16);
			String digit_analysis = Integer.toString(16-sum16gint, 16);

			if (digit_analysis.equalsIgnoreCase(digit))
				return true;
			else
				return false;
		}
		
	}
	
	public static void main(String[] args) {
		String str = "351565051400684";//"860173010887184";//"359090049239989";//"A1000015F65178F";// "A1000023A12A7E5";//
		boolean flag = validate(str);
		System.out.println(flag);
	}
}
