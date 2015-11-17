/**
 * <p>Title: TestData2.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-6-3
 * @version 1.0
 */
package cn.adwalker.data;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cn.adwalker.core.util.ConfigUtil;
import cn.adwalker.core.util.Function;
import cn.adwalker.core.util.HttpClientUtil;

/**
 * <p>
 * Title: TestData2
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-6-3
 */
public class TestData5 {

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
	 * @date 2013-6-3
	 * @return void
	 * @version 1.0
	 * @throws IOException
	 */

	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 1000; i++) {
			HttpClientUtil
					.sendGet("http://221.122.113.163/EScore_Service/visit/sdk_InitializeE.do?m=" +URLEncoder.encode("EW0LZqdZBYlYtQQytw+Q6dZW0TvnnxYUfGYfx1fM19OFD+HRYqwXpOSZONqI 9wQJEKfwYGtRIDgKyZI7pz7JR3b0AtvxAacHW/tKZ1nxsnl4qz2EcxPRc7km G0XfF5bLPj6xrlW8BEHE+ydjed0soJcNd8GxrkNZbRitfbiSBBL3hc9hX3Bj n4RaIQ8ousMG42FUMhOV/nZZ5hqtveZmrfmEqqyBqrwwpXZdmcvvJWVbk+Ys LHv033kaQDvIbkXM5qFRN+mSOiv41+e3tFnGrw==","utf-8"));
		}

	}

}
