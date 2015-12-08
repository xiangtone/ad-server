package cn.adwalker.ad.picker.monitor;

import cn.adwalker.ad.picker.util.NumberUtil;
/**
 * @author admin
 * 控量线程类
 */
public class MonitorRunnable implements Runnable {

	private String adId;
	private String actionCode;
	public MonitorRunnable(){}
	public MonitorRunnable(String adId,String actionCode){
		this.adId=adId;
		this.actionCode=actionCode;
	}
	@Override
	public void run() {
		ActionMonitor.getInstance().putActionInMap(NumberUtil.getInteger(adId, 0), NumberUtil.getInteger(actionCode, -1));
	}

}
