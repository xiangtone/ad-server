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
import cn.adwalker.model.sys.dao.IDataDao;

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
public class TestSeq extends TestBase {

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
	 * @throws Exception
	 */

	@SuppressWarnings("unchecked")
	public void testmain() throws Exception {
		IDataDao dao = (IDataDao) context.getBean("dataDao");
		List<String> list = FileUtils.readLines(new File("D://haha/seq.txt"));
		StringBuilder sb = new StringBuilder();
		for (String s : list) {
			String arr[] = s.split("\\t");
			System.out.println("select SEQ_" + arr[0].substring(2)
					+ ".CURRVAL seq from dual"+";(select max(id) maxId from " + arr[0]
					+ ")");

			// select SEQ_APPLICATION.CURRVAL,(select max(id)from T_APPLICATION)
			// from dual

		}
		System.out.println(sb.toString());

		// FileUtils.writeStringToFile(new File("D://haha/00000000_c.txt"),
		// sb.toString());

	}

}
