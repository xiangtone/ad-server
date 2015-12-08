package cn.adwalker.ad.picker.system.servlet;

import org.apache.log4j.Logger;

import cn.adwalker.ad.picker.vo.InitVo;
import cn.adwalker.ad.service.InitService;
import cn.adwalker.core.spring.AppContext;

public class UuidRunnable implements Runnable {
    public static final Logger logger = Logger.getLogger(UuidRunnable.class);
	private InitVo iv;
	
	public UuidRunnable(){
		
	}
    public UuidRunnable(InitVo iv){
	   this.iv=iv;	
	}
	
	@Override
	public void run() {
		logger.info("uuid:"+iv.getUuid());
		InitService systemService = AppContext.getApplicationContext().getBean("systemService", InitService.class);
		systemService.collectUuidMsg(iv, iv.os_version);
	}
}
