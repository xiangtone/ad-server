/**
 * <p>Title: TestDataHour.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-8-5
 * @version 1.0
 */
package cn.adwalker.api;

import cn.adwalker.ad.api.app.vo.FinanceByHour;
import cn.adwalker.core.util.JacksonMapper;

/**
 * <p>
 * Title: TestDataHour
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-8-5
 */
public class TestDataHour {

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
	 * @date 2013-8-5
	 * @return void
	 * @version 1.0
	 * @throws Exception 
	 */

	public static void main(String[] args) throws Exception {
		FinanceByHour result = new FinanceByHour("2013-08-04");
		result.setFilename("2013-08-04_22.csv");
		result.setHour("22");
		StringBuilder params = new StringBuilder();
		params.append("res_data=").append(
				JacksonMapper.objectToJsonString(result));
		HttpURLConnectionTest.httpRequestPost("http://www.coolad.cn/data/hour", params.toString());
		
		
//		HttpClientUtil.sendPost("http://www.coolad.cn/data/hour",
//				params.toString());

	}

}
