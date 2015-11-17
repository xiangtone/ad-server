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

import cn.adwalker.ad.api.app.form.App;
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
public class TestApp {

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
		App entity = new App();
		entity.setAppkey("EMVJQVC75KMJ2KRYWLSI0SCD0EB4QSI7GT");
		entity.setCategory_id("10002");
		entity.setCreate_time("2013-07-23 18:06:05");
		entity.setCurrency_name("积分");
		entity.setCurrency_value(100);
		entity.setDel(""+0);
		entity.setDown_time("2013-07-23 18:06:05");
		entity.setId("50098");
		entity.setKeyword("测试应用");
		entity.setLong_desc("android测试应用");
		entity.setName("测试应用1");
		entity.setOs("android");
		entity.setPackage_name("cn.adwalker.sdk");
		entity.setProject_url("www.baidu/****.apk");
		entity.setRelease_time("2013-07-23 18:06:05");
		entity.setResource_size("10000");
		entity.setUpdate_time("2013-07-23 18:06:05");
		entity.setVersion_code("v1.1.0");
		entity.setVersion_name("star");
		
		
//		dev.setCreatetime("2013-07-23 18:06:05");
//		dev.setUpdatetime("2013-07-23 19:06:05");

		System.out.println(JacksonMapper.objectToJsonString(entity));

	}

}
