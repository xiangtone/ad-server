/**
 * <p>Title: TestUpdateApp.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-5-29
 * @version 1.0
 */
package cn.adwalker;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import cn.adwalker.core.util.ConfigUtil;
import cn.adwalker.core.util.email.SendMail;

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
public class TestEmail extends TestBase {

	public void testEmail() throws IOException {
		String s = FileUtils.readFileToString(new File(
				"D://haha/Blue_Wave/2/mailer.html"), "utf-8");
		SendMail.send("测试邮件", ConfigUtil.getString("sendMail"),
				"cuidongdong@adwalker.com.cn", s);
	}

}
