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
import cn.adwalker.core.util.PinYinUtil;

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
public class TestRoleCode extends TestBase {

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

		List<String> list = FileUtils.readLines(new File(
				"D://haha/c000000000000000001.txt"), "utf-8");
		for (String s : list) {
			String arr[] = s.split("\\s+");
			System.out.println("update T_SYS_PERMISSION set code='"
					+ PinYinUtil.toPinYin(arr[1]).toUpperCase() + "' where id="
					+ arr[0] + ";");

		}

	}

}
