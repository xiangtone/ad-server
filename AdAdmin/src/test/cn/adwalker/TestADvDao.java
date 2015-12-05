package cn.adwalker;

import java.util.Date;

import cn.adwalker.ad.admin.task.service.IiosTaskService;
import cn.adwalker.core.util.DateUtil;

public class TestADvDao extends TestBase {

	public void testtable() throws Exception {
		IiosTaskService service = (IiosTaskService) context
				.getBean(IiosTaskService.class);
		;
		service.doTask(DateUtil.addDays(new Date(), -1));
	}

}
