/**
* <p>Title: ChannelThread.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2012-12-21
* @version 1.0
*/
package cn.adwalker.IOSChannel.thread;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.adwalker.IOSChannel.logger.ChannelSendLogger;
import cn.adwalker.IOSChannel.service.IChannelService;
import cn.adwalker.IOSChannel.util.ObjectUtils;
import cn.adwalker.IOSChannel.vo.ChannelRequestResult;


/**
 * <p>Title: ChannelThread</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2012-12-21
 */
public class ChannelThread extends Thread {
	private static final Logger log = LoggerFactory.getLogger(ChannelThread.class);
	private IChannelService channelService;
	private String requestJson;
	public  static int threadNumbers = 0;

	/**
	* 构造器
	*/
	public ChannelThread(String requestJson,
			IChannelService channelService) {
		this.channelService = channelService;
		this.requestJson = requestJson;
	}
	
	
	
	@Override
	public void run() {
			long startTime = System.currentTimeMillis();
		try{

			if(ObjectUtils.isNotEmpty(requestJson)){
				// 解析json
				ChannelRequestResult result = new ChannelRequestResult();			
				result = this.channelService.AdRequestOpenJson(requestJson);
				
				
				//存入数据库 这儿怎么没有判断渠道是否是自己人 jierfei ask ？
				this.channelService.saveList(result);
				
				
				String s[] = {"向广告主发送数据deviceId----"+result.getDeviceId()+"  appId--"+result.getAdId()+"  source--"+result.getSource(),result.getDeviceId(),result.getAdId(),result.getSource()};
				ChannelSendLogger logger = new ChannelSendLogger();
				logger.log(s);
				
				/**
				 * 向广告主发送数据
				 */
				this.channelService.sendDate(result);
			}
			
			
				
		}catch (Exception e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		log.info("主线程执行 时间:"+(endTime-startTime)+"");
	}
}
