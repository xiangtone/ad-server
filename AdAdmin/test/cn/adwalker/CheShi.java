/**
 * <p>Title: CheShi.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-5-23
 * @version 1.0
 */
package cn.adwalker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * <p>
 * Title: CheShi
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-5-23
 */
public class CheShi {

	@SuppressWarnings("unchecked")
	public static void main(String args[]) throws IOException {

		List<String> list = FileUtils.readLines(new File(
				"d://haha/lala/lala.txt"));
		Collection<String> c = new ArrayList<String>();
		for (String s : list) {
			String arr[] = s.split("\t");

			c.add("update t_package_activate set campaign_id=" + arr[1]
					+ " ,charge_type='" + arr[2] + "' where id=" + arr[0] + ";");
		}
		FileUtils.writeLines(new File("d://haha/lala/lala1.txt"), c);

	}
}
