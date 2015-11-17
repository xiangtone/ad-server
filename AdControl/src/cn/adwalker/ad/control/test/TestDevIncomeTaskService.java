package cn.adwalker.ad.control.test;


import cn.adwalker.ad.control.service.DevIncomeTaskService;
import cn.adwalker.ad.control.util.DateUtils;



public class TestDevIncomeTaskService extends TestBase {

	private DevIncomeTaskService service;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		service = context.getBean(DevIncomeTaskService.class);
	}

	public void testService() {
		assertNotNull(this.context);
		try {
			service.tranceDevIncome(DateUtils.getBeforeDay(-4));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}