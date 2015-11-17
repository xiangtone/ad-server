package cn.adwalker.ad.control.test;

import cn.adwalker.ad.control.service.ReportAdByDayTaskService;


public class TestReportAd extends TestBase {

	private ReportAdByDayTaskService service;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		service = context.getBean(ReportAdByDayTaskService.class);
	}

	public void testService() {
		assertNotNull(this.context);
		try {
			//Date date = new Date();
			for (int i = 0; i < 40; i++) {
				//service.tranceData(DateUtil.addDays(date, -i));DateUtils.getBeforeDay(-i)
				service.tranceData("2014-05-12");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}