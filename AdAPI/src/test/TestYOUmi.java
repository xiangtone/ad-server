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
public class TestYOUmi {

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
		List<String> list = FileUtils.readLines(new File("D://haha/01.txt"));
		for (String s:list) {
			String arr[]=s.split("\\s");
			String ad_id=arr[0];
			String url="";
			String mac=arr[1];
			String udid=arr[2];
			String	ifa=arr[3];
			String ip=arr[4];
			if (ad_id.equals("YOUMI_7936")) {
				 url="http://ios.wall.youmi.net/v2/c_goto?s=srEpHNMY4_OYrnat0-muhj_BaDeaADy2u-m5Dmb_oGzCtbClXgDZT";
			}else if (ad_id.equals("YOUMI_5746")) {
				url="http://ios.wall.youmi.net/v2/c_goto?s=srEpHNMY4_4BIH580wgrAg0e7EjOZpD2IwbbtAMCxR8_pYXy9McOc";
			}
			url=url+"&mac="+mac+"&udid="+udid+"&ifa="+ifa+"&ip="+ip+"&goto=0";
			 s=HttpClientUtils.readFromURL(url, "utf-8");
			System.out.println(s);

			
		}

	}

}
