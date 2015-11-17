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

import cn.adwalker.ad.api.app.vo.CallBack;
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
public class TestCallBack {

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
		CallBack callBack = new CallBack();
		callBack.setApp_id("50062");
		callBack.setCheck_msg("审核通过");
		callBack.setStatus("4");
		callBack.setScale("0.95");

		System.out.println(JacksonMapper.objectToJsonString(callBack));
//		String result = URLEncoder.encode(
//				, "utf-8");

//		String url = "http://test2012.adview.cn:8888/app/callback?res_date="
//				+ result;
//		HttpClientUtil.sendGet(url);
	}

}
