/**
* <p>Title: ChannelTestController.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date Dec 23, 2012
* @version 1.0
*/
package cn.adwalker.IOSChannel.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Title: ChannelTestController</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       Dec 23, 2012
 */
@Controller
public class ChannelTestController {
	@RequestMapping("/channelReceive.do")
	public void comfirmActivateCheck(HttpServletRequest request,
			HttpServletResponse response){
		String appkey = request.getParameter("appid");//APP标识
		String deviceid = request.getParameter("deviceid");//设备标识   mac地址
		String paraB1 = request.getParameter("price");
		
		System.out.println(appkey);
		System.out.println(deviceid);
		System.out.println(paraB1);
		//request.setAttribute("result","123");
	}
}
