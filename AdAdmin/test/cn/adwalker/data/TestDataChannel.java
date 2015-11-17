/**
 * <p>Title: TestData2.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-6-3
 * @version 1.0
 */
package cn.adwalker.data;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cn.adwalker.TestBase;

/**
 * <p>
 * Title: TestData2
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-6-3
 */
public class TestDataChannel extends TestBase {

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
	 * @date 2013-6-3
	 * @return void
	 * @version 1.0
	 * @throws IOException
	 */

	public static void main(String args[]) throws IOException {

		List<String> list = FileUtils.readLines(new File("D://haha/c_ios.txt"), "utf-8");
		StringBuilder sb = new StringBuilder();
		for (String s : list) {
			// System.out.println(s);
			String arr[] = s.split("\\t");
			if (arr.length <= 6) {
				System.out.println(s);
			} else {
				System.out.println("INSERT INTO T_CHANNEL VALUES (SEQ_CHANNEL.NEXTVAL, '" + arr[6] + "', 'E10ADC3949BA59ABBE56E057F20F883E', '" + arr[0] + "', '" + arr[1] + "', '" + arr[2] + "', '1', DATE_FORMAT('2013-07-06 23:24:17', '%Y-%m-%d %T'), null, '1', '25', '0', '" + arr[4] + "', '" + arr[3] + "', null,'ios');");
			}
		}
	}

}
