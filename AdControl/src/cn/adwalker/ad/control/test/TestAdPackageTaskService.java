package cn.adwalker.ad.control.test;

import cn.adwalker.ad.control.service.AdPackageTaskService;



public class TestAdPackageTaskService extends TestBase {

	public void testtableupdate() {
		AdPackageTaskService service = (AdPackageTaskService) context.getBean(AdPackageTaskService.class);
		try {			
			service.doTask("2014-04-08");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}