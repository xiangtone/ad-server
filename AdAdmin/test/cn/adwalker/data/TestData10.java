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
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.io.FileUtils;

import cn.adwalker.core.pool.ThreadPoolUtil;
import cn.adwalker.core.thread.ClearCacheTask;
import cn.adwalker.core.util.ConfigUtil;

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

	public static void main(String[] args) throws IOException {
		List<String> list = FileUtils.readLines(new File("D://haha/ios.txt"));
		for (String s:list) {
			String arr[]=s.split("\\s");
			String cacheUrl = ConfigUtil.getString("updateCahce.url");
			StringBuffer params = new StringBuffer();
			params.append(cacheUrl).append("?adIds=").append(arr[0]);
			ThreadPoolExecutor threadPool = ThreadPoolUtil.getThreadPool();
			threadPool.execute(new ClearCacheTask(params.toString()));

			
		}

	}

}
