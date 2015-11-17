/**
 * <p>Title: TestIosEffect.java </p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author hadoop
 * @date 2013-11-13
 * @version 1.0
 */
package cn.adwalker;

import java.util.Date;

import cn.adwalker.ad.admin.operation.service.IOperationCampaignConfirmService;

/**
 * <p>
 * Title: TestIosEffect
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-11-13
 */
public class TestPlatformCost extends TestBase {

	private IOperationCampaignConfirmService service;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		service = context.getBean(IOperationCampaignConfirmService.class);
	}

	public void testService() {
		assertNotNull(this.context);
		try {
			Date date = new Date();
//			System.out.println(DateUtil.addDays(date, -4));
			 service.testPlatformCost(522L);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
