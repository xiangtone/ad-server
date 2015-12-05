/**
 * <p>Title: TestImages.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-8-15
 * @version 1.0
 */
package cn.adwalker;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;

import cn.adwalker.core.util.ImageUtil;
import cn.adwalker.core.util.lang.StringUtil;

/**
 * <p>
 * Title: TestImages
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-8-15
 */
public class TestImages {

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
	 * @date 2013-8-15
	 * @return void
	 * @version 1.0
	 * @throws Exception 
	 */

	public static void main(String[] args) throws Exception {
		ImageUtil.catImg("D:/haha/img/1.jpg", "D:/haha/img/2.jpg", 854, 854);
		System.out
				.println(StringUtil
						.decode("sig=MjAxMy0wOC0xNSAxNToyNjo1N19hNWUyYmNiM2ZmZTZmMDE0ZDg2NmY4ODk5MDE1NWY3Zg%3D%3D&res_data=%7B%22status%22%3A%224%22%2C%22app_id%22%3A%221.0%22%2C%22scale%22%3A%221.0%22%2C%22check_time%22%3A%222013-8-15+15%3A26%3A57%22%2C%22check_msg%22%3A%22%22%7D"));

	}

}
