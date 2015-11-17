package cn.adwalker.api;

import cn.adwalker.TestBase;
import cn.adwalker.ad.api.app.form.Dev;
import cn.adwalker.ad.api.app.service.IApiService;

public class TestAppService extends TestBase {

	public void testtableupdate() {
		IApiService service = (IApiService) context.getBean(IApiService.class);
		try {
			Dev dev = new Dev();
			dev.setId(100000000001L);
			service.saveOrUpdateDev(dev);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
