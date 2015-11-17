package cn.adwalker.ad.control.test;


import cn.adwalker.ad.control.service.AdTaskService;



public class TestAdTaskService extends TestBase {

	private AdTaskService service;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		service = context.getBean(AdTaskService.class);
	}

	public void testService() {
		assertNotNull(this.context);
		try {
			service.adTask("2014-05-20");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}