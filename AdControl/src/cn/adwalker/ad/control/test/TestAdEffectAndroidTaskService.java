package cn.adwalker.ad.control.test;


import cn.adwalker.ad.control.service.AdEffectAndroidTaskService;



public class TestAdEffectAndroidTaskService extends TestBase {

	private AdEffectAndroidTaskService service;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		service = context.getBean(AdEffectAndroidTaskService.class);
	}

	public void testService() {
		assertNotNull(this.context);
		try {
//			service.tranceAndroidData("2014-07-17");
			
			service.updatePackageActivateInfo();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}