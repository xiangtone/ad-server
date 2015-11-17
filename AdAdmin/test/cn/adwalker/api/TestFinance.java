/**
 * <p>Title: TestDev.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-7-23
 * @version 1.0
 */
package cn.adwalker.api;

import java.io.IOException;

import cn.adwalker.ad.api.app.vo.FinanceByHour;
import cn.adwalker.core.util.JacksonMapper;

/**
 * <p>
 * Title: TestDev
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-7-23
 */
public class TestFinance {

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
	 * @date 2013-7-23
	 * @return void
	 * @version 1.0
	 * @throws IOException
	 */

	public static void main(String[] args) throws IOException {
		FinanceByHour entity = new FinanceByHour();
		entity.setUrl("http://221.122.113.170/ejifenres/api/data/");
		entity.setFilename("2013-07-29_15.csv");
		entity.setHour("15");
		System.out.println(JacksonMapper.objectToJsonString(entity));

	}

}
