package cn.adwalker.IOSChannel.thread;

import cn.adwalker.IOSChannel.service.IOffLineAddScoreService;

public class OffLineAddScoreThread extends Thread{
	private IOffLineAddScoreService offLineAddScoreService;
	private int status;
    public OffLineAddScoreThread(IOffLineAddScoreService offLineAddScoreService,int status){
    	this.offLineAddScoreService=offLineAddScoreService;
    	this.status=status;
    }
	@Override
	public void run() {
		offLineAddScoreService.retScore(status);
	}
}
