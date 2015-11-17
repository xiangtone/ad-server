/**
 * <p>Title: TestUpdateApp.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-5-29
 * @version 1.0
 */
package cn.adwalker.api;

import java.io.File;
import java.net.URLDecoder;

import org.apache.commons.io.FileUtils;

import cn.adwalker.TestBase;
import cn.adwalker.ad.api.app.service.IApiLogService;

/**
 * <p>
 * Title: TestUpdateApp
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-5-29
 */
public class TestLog extends TestBase {

	public void testEmail() throws NumberFormatException, Exception {
		String s = FileUtils.readFileToString(new File(
				"D://haha/app1000001.TXT"), "utf-8");
		System.out.println(s);
		s = URLDecoder.decode(s, "utf-8");
		IApiLogService service = context.getBean(IApiLogService.class);
		service.log(s);

	}

}
