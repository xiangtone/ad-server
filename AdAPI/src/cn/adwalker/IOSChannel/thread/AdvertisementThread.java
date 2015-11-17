/**
* <p>Title: AdvertisementThread.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-1-16
* @version 1.0
*/
package cn.adwalker.IOSChannel.thread;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.adwalker.IOSChannel.service.IAdvertisementService;
import cn.adwalker.IOSChannel.vo.ChannelRequestResult;


/**
 * <p>Title: AdvertisementThread</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-1-16
 */
public class AdvertisementThread  extends Thread{
	private static final Logger log = LoggerFactory.getLogger(ChannelGetThread.class);
	private IAdvertisementService advertisementService;
	private ChannelRequestResult vo;
	
	/**
	* 构造器
	*/
	public AdvertisementThread(ChannelRequestResult vo,IAdvertisementService advertisementService) {
		this.advertisementService = advertisementService;
		this.vo = vo;		
	}

	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
		advertisementService.sendComfirmDate(vo);
		long endTime = System.currentTimeMillis();
		log.info("AdvertisementThread主线程执行 时间:"+(endTime-startTime)+"");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public IAdvertisementService getAdvertisementService() {
		return advertisementService;
	}

	public void setAdvertisementService(IAdvertisementService advertisementService) {
		this.advertisementService = advertisementService;
	}

	public ChannelRequestResult getVo() {
		return vo;
	}

	public void setVo(ChannelRequestResult vo) {
		this.vo = vo;
	}
	
	
	
	
	
	
	
}
