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

import cn.adwalker.core.util.DevIncomeUtils;

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
public class TestDevEffect2 extends TestBase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testService() {
		assertNotNull(this.context);
		DevIncomeUtils.query();
	}

}
