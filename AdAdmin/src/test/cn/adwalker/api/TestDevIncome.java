/**
 * <p>Title: TestDevIncome.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-8-2
 * @version 1.0
 */
package cn.adwalker.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.adwalker.ad.api.app.vo.DevIncome;
import cn.adwalker.core.util.JacksonMapper;

/**
 * <p>
 * Title: TestDevIncome
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-8-2
 */
public class TestDevIncome {

	/**
	 * <p>
	 * Title: main
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param args
	 * @author cuidd
	 * @date 2013-8-2
	 * @return void
	 * @version 1.0
	 * @throws IOException 
	 */

	public static void main(String[] args) throws IOException {
		// "devId" : ,"userid":"qifeng998130@163.com",
		// "devId" : ,"userid":"fad@fad.com",
		// "devId" : ,"userid":"958980561@qq.com_image.png",
		// "devId" : ,"userid":"sp.wulei@gmail.com_image.gif",
		// "devId" : ,"userid":"yijie.liu@kuaiyou.com",

		List<DevIncome> list = new ArrayList<DevIncome>();
		DevIncome d = new DevIncome();
		d.setConfirmed_total(40d);
		d.setFreeze_money(0d);
		d.setDev_id("51769");
		DevIncome d2 = new DevIncome();
		d2.setConfirmed_total(40d);
		d2.setFreeze_money(1d);
		d2.setDev_id("52006");
		DevIncome d3 = new DevIncome();
		d3.setConfirmed_total(40d);
		d3.setFreeze_money(1d);
		d3.setDev_id("56254");

		DevIncome d4 = new DevIncome();
		d4.setConfirmed_total(1d);
		d4.setFreeze_money(1d);
		d4.setDev_id("59259");

		DevIncome d5 = new DevIncome();
		d5.setConfirmed_total(100d);
		d5.setFreeze_money(1d);
		d5.setDev_id("57132");
		list.add(d);
		list.add(d5);
		list.add(d2);
		list.add(d3);
		list.add(d4);

		System.out.println(JacksonMapper.objectToJsonString(list));

	}

}
