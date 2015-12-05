/**
 * <p>Title: TestIosEffect.java </p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author hadoop
 * @date 2013-11-13
 * @version 1.0
 */
package cn.adwalker;

import java.io.File;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * Title: TestIosEffect
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-11-13
 */
public class TestChannelCost extends TestCase{

	@SuppressWarnings("unchecked")
	public void testService() {
		try {
			List<String> list = FileUtils.readLines(new File(
					"D://haha/bbbb.txt"));
			for (String s : list) {
				String arr[] = s.split("\\s");
				if (!StringUtils.isEmpty(arr[0])) {
					System.out.println("update t_ios_action_log set channel='"+arr[1]+"' where id="+arr[0]+";");
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
