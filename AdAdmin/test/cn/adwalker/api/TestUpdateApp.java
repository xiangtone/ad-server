/**
 * <p>Title: TestUpdateApp.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-5-29
 * @version 1.0
 */
package cn.adwalker.api;

import java.io.File;
import java.net.URLDecoder;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;

import cn.adwalker.TestBase;
import cn.adwalker.ad.api.app.form.App;
import cn.adwalker.ad.api.app.service.IApiService;
import cn.adwalker.core.util.JacksonMapper;

/**
 * <p>
 * Title: TestUpdateApp
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-5-29
 */
public class TestUpdateApp extends TestBase {

	public void testEmail() throws NumberFormatException, Exception {
		List<String> list = FileUtils.readLines(new File(
				"D://haha/log_date.txt"), "utf-8");
		for (String s : list) {
			System.out.println(s);
			s = URLDecoder.decode(s, "utf-8");
			IApiService service = context.getBean(IApiService.class);
			ObjectMapper mapper = JacksonMapper.getInstance();
			App app = mapper.readValue(s, App.class);
			service.saveOrUpdateApp(app);
		}

	}

}
