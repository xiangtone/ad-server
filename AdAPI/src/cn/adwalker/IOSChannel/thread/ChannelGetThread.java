/**
* <p>Title: ChannelGetThread.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2012-12-25
* @version 1.0
*/
package cn.adwalker.IOSChannel.thread;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.adwalker.IOSChannel.service.IChannelService;
import cn.adwalker.IOSChannel.vo.ChannelRequestResult;
import cn.adwalker.IOSChannel.vo.Constant;



/**
 * <p>Title: ChannelGetThread</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2012-12-25
 */
public class ChannelGetThread extends Thread{
	private static final Logger log = LoggerFactory.getLogger(ChannelGetThread.class);
	private IChannelService channelService;
	private ChannelRequestResult result; 
	
	public ChannelRequestResult getResult() {
		return result;
	}

	public void setResult(ChannelRequestResult result) {
		this.result = result;
	}

	/**
	* 构造器
	*/
	public ChannelGetThread(ChannelRequestResult result,
			IChannelService channelService) {
		this.channelService = channelService;
		this.result = result;
	}
	
	@Override
	public void run() {
	    long startTime = System.currentTimeMillis();
		try{
			//再插入之前验证一下是否ip重复 start jief 2014-02-20
			
			//再插入之前验证一下是否ip重复 start end
			//if(SystemInit.queue_is_valid){
				result.setStatus(Constant.IOS_ACTION_LOG_STATUS_UNCONFIRMED);
				result.setActiviteStatus(Constant.IOS_ACTION_LOG_ACTIVITE_STATUS_UNCONFIRMED);
				result.setCreateTime(new Date());
				//ChannelClickPool.getInstance().add(result);
			//}else{
				System.out.println("实时插入启动");
			//	this.channelService.saveList(result);
			//}
			// 向广告主发送数据
			this.channelService.sendDate(result);
		}catch (Exception e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		log.info("主线程执行 时间:"+(endTime-startTime)+"");
	}
}
