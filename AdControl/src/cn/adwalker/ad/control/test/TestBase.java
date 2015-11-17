/**
 * <p>Title: TestBase.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-4-7
 * @version 1.0
 */
package cn.adwalker.ad.control.test;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * <p>
 * Title: TestBase
 * </p>
 * <p>
 * Description:单元测试基类
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-4-7
 */
public class TestBase extends TestCase {

	/**
	 * 上下文信息
	 */
	protected ApplicationContext context;

	/**
	 * (non-Javadoc)
	* <p>Title: setUp</p>
	* <p>Description:初始化</p>
	* @throws Exception
	* @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		this.context = new FileSystemXmlApplicationContext(
				new String[] { "WebContent/WEB-INF/applicationContext.xml" });
		super.setUp();
	}

	public void testService() {
		assertNotNull(this.context);
	}

}
