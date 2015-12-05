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

import cn.adwalker.ad.admin.task.service.IDevIncomeTaskService;
import cn.adwalker.core.util.DateUtil;

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
public class TestDevEffect extends TestBase {

	private IDevIncomeTaskService service;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		service = context.getBean(IDevIncomeTaskService.class);
	}

	public void testService() {
		assertNotNull(this.context);
		try {
			for (int i = 0; i < 200; i++) {
				System.out.println(i+".....");
				service.tranceDevEffect(DateUtil.parseDate("2013-10-29"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
