/**
 * <p>Title: TestData2.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-6-3
 * @version 1.0
 */
package cn.adwalker;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

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
public class TestData10 {

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

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		List<String> list = FileUtils.readLines(new File("D://haha/01.txt"));
		for (String s:list) {
			String arr[]=s.split("\\s");
			System.out.println(arr[0]+"\t"+arr[1]+"\t"+arr[4]);
			
//			String uuidStr = new StringBuffer().append(media_user).append(app_id).toString();
//			String uuid= new cn.adwalker.ad.picker.util.MD5().getMD5ofStr(uuidStr);
		}
		
		ApplicationContext context = new FileSystemXmlApplicationContext(
				new String[] { "WebRoot/WEB-INF/spring/applicationContext.xml" });
		System.out.println(context);

	}

}
