package cn.adwalker.ad.picker.monitor;

import java.util.TimerTask;

import org.apache.log4j.Logger;


public class CallCenterTask extends TimerTask {
	
	private static final Logger logger = Logger.getLogger(CallCenterTask.class);
	@Override
	public void run() {
		logger.info("CallCenterTask begin------------------");
		ActionMonitor.getInstance().callCenter();
		logger.info("CallCenterTask done------------------");
	}

}
