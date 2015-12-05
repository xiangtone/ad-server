/**
 * <p>Title: TestDev.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-7-23
 * @version 1.0
 */
package cn.adwalker.api;

import java.io.IOException;

import cn.adwalker.ad.api.app.form.Dev;
import cn.adwalker.core.util.JacksonMapper;

/**
 * <p>
 * Title: TestDev
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-7-23
 */
public class TestDev {

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
	 * @date 2013-7-23
	 * @return void
	 * @version 1.0
	 * @throws IOException
	 */

	public static void main(String[] args) throws IOException {
		Dev dev = new Dev();
		dev.setCardCode("证件号码");
		dev.setCompanyAddress("公司地址");
		dev.setCompanyName("公司名称");
		dev.setEmail("邮箱");
		dev.setMailingAddress("通讯地址");
		dev.setMobilePhone("手机");
		dev.setPassword("密码");
		dev.setPostCode("邮编");
		dev.setQq("QQ");
		dev.setRealName("真实姓名");
		dev.setType(0);
		dev.setCreatetime("2013-07-23 18:06:05");
//		dev.setUpdate_time("2013-07-23 19:06:05");

		System.out.println(JacksonMapper.objectToJsonString(dev));

	}

}
