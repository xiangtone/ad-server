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

import cn.adwalker.core.util.DateUtil;

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
public class TestData7 {

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

	public static void main(String[] args) throws IOException {
		List<String> list = FileUtils.readLines(new File("D://haha/ios.txt"));
		System.out.println("--111111111111111");
		for (String s : list) {
			String arr[] = s.split("\\s");
			String dateString = DateUtil.formatDate(DateUtil.parseDate(arr[0],
					"yyyy/MM/d"));
			System.out
					.println("INSERT INTO T_IOS_EFFECT_IMP VALUES (SEQ_IOS_EFFECT_IMP.NEXTVAL, null, DATE_FORMAT('2013-10-22 09:21:14', '%Y-%m-%d %T'), null, '"
							+ arr[1]
							+ "', '"
							+ dateString
							+ "', '0', '"
							+ arr[5]
							+ "', '"
							+ arr[3]
							+ "', '"
							+ arr[4]
							+ "');");

		}
		System.out.println("--2222222222222");

	}

}
