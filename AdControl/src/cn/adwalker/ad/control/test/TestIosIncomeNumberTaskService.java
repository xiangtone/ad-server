package cn.adwalker.ad.control.test;


import cn.adwalker.ad.control.service.IosIncomNumberService;



public class TestIosIncomeNumberTaskService extends TestBase {

	private IosIncomNumberService service;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		service = context.getBean(IosIncomNumberService.class);
	}

	public void testService() {
		assertNotNull(this.context);
		try {
			service.tranceDataIosIncomNumber("2014-05-11","2014-05-13");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}