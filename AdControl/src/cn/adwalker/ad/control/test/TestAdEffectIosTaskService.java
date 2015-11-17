package cn.adwalker.ad.control.test;


import cn.adwalker.ad.control.service.AdEffectIosTaskService;



public class TestAdEffectIosTaskService extends TestBase {

	private AdEffectIosTaskService service;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		service = context.getBean(AdEffectIosTaskService.class);
	}

	public void testService() {
		assertNotNull(this.context);
		try {
			service.tranceIosData("2014-07-22");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}