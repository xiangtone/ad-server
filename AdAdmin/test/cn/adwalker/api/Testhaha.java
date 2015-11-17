/**
 * <p>Title: Testhaha.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-8-6
 * @version 1.0
 */
package cn.adwalker.api;

import cn.adwalker.core.util.lang.StringUtil;

/**
 * <p>
 * Title: Testhaha
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-8-6
 */
public class Testhaha {

	/**
	 * <p>
	 * Title: main
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param args
	 * @author cuidd
	 * @date 2013-8-6
	 * @return void
	 * @version 1.0
	 */

	public static void main(String[] args) {
		double d = 0.8d;

		// TODO 谁有时间改下这个地方，用format处理比较耗资源，也容易出错 mark by cdd
		// System.out.println(new java.text.DecimalFormat("0").format(scoreD));

		// System.out.println(score);

		for (int i = 0; i < 50; i++) {
			Double scoreD = d * Integer.parseInt("3");
			Integer score = Integer.parseInt(new java.text.DecimalFormat("0")
					.format(scoreD));
			score = score * 7 / 10;
			System.out.println(d + "-------------------" + score);
			d = d + 0.1;

		}

	}

}
