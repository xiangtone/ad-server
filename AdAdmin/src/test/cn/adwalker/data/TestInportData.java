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
public class TestInportData {

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
		List<String> list = FileUtils.readLines(
				new File("D://haha/a00002.txt"), "gbk");
		StringBuilder sb = new StringBuilder();
		for (String s : list) {

			String arr[] = s.split("\\t");
			System.out.println(arr[8]);
			sb.append("INSERT INTO T_AD_HA VALUES ('"
					+ "2013-"
					+ DateUtil.formatDate(DateUtil.parseDate(arr[0], "M月dd日"),
							"MM-dd") + "', '" + arr[1] + "', '" + arr[2]
					+ "', '" + arr[3] + "', '" + arr[4] + "', '" + arr[5]
					+ "', '" + arr[6] + "', '" + arr[7] + "', '" + arr[8]
					+ "');");
			sb.append("\n");
		}

		FileUtils.writeStringToFile(new File("D://haha/channel.txt"),
				sb.toString(), "utf-8");

	}

}
