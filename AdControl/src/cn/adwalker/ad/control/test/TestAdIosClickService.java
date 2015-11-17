package cn.adwalker.ad.control.test;


import cn.adwalker.ad.control.service.AdIosClickService;



public class TestAdIosClickService extends TestBase {

	private AdIosClickService service;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		service = context.getBean(AdIosClickService.class);
	}

	public void testService() {
		assertNotNull(this.context);
		try {
			service.tranceDataIosClick();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}