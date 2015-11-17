package cn.adwalker.ad.control.test;

import cn.adwalker.ad.control.service.IosStaticTaskService;

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
public class TestIosStatic extends TestBase {

	private IosStaticTaskService iosStaticTaskService;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		iosStaticTaskService = context.getBean(IosStaticTaskService.class);
	}

	public void testService() {
		assertNotNull(this.context);
		try {
			iosStaticTaskService.tranceDataEffectByDayTest("2014-07-22");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
