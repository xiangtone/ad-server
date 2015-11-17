package cn.adwalker.ad.control.test;

import cn.adwalker.ad.control.service.RefreshEscoreConfigService;



public class TestRefreshEscoreConfigTaskService extends TestBase {

	public void testtableupdate() {
		RefreshEscoreConfigService service = (RefreshEscoreConfigService) context.getBean(RefreshEscoreConfigService.class);
		try {			
			service.executeInternal();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}