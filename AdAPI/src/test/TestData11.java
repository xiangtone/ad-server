/**
 * <p>Title: TestData2.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-6-3
 * @version 1.0
 */
package test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cn.adwalker.IOSChannel.picker.util.HttpClientUtils;

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
public class TestData11 {

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
		List<String> list = FileUtils.readLines(new File("D://haha/haha4.log"));
		for (String s:list) {
			String url=s.substring(58, s.length());
			System.out.println(url.indexOf("@"));
			 s=HttpClientUtils.readFromURL("http://localhost:9090/AdAPI/"+url.split("@")[0], "utf-8");

			
		}

	}

}
