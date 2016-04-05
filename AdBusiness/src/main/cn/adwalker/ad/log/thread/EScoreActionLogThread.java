package cn.adwalker.ad.log.thread;

import org.springframework.context.ApplicationContext;

import cn.adwalker.ad.core.spring.AppContext;
import cn.adwalker.ad.model.log.dao.AdwalkerActionLogDao;
import cn.adwalker.ad.model.log.domain.EScoreManagerLog;

public class EScoreActionLogThread extends Thread {

	private EScoreManagerLog actionLog;

	public EScoreActionLogThread() {

	}

	public EScoreActionLogThread(EScoreManagerLog actionLog) {
		this.actionLog = actionLog;
	}

	public void run() {
		ApplicationContext ctx = AppContext.getApplicationContext();
		AdwalkerActionLogDao actionLogDao = (AdwalkerActionLogDao) ctx.getBean("adwalkerActionLogDao");
		actionLogDao.save(actionLog);
	}

}
