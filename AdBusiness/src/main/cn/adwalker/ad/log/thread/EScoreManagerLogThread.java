package cn.adwalker.ad.log.thread;

import org.springframework.context.ApplicationContext;

import cn.adwalker.ad.core.spring.AppContext;
import cn.adwalker.ad.model.log.dao.AdwalkerActionLogDao;
import cn.adwalker.ad.model.log.domain.EScoreManagerLog;

public class EScoreManagerLogThread extends Thread {

	private EScoreManagerLog managerLog;

	public EScoreManagerLogThread() {

	}

	public EScoreManagerLogThread(EScoreManagerLog managerLog) {
		this.managerLog = managerLog;
	}

	public void run() {
		ApplicationContext ctx = AppContext.getApplicationContext();
		AdwalkerActionLogDao managerLogDao = (AdwalkerActionLogDao) ctx.getBean("eScoreManagerLogDao");
		managerLogDao.save(managerLog);
	}

}
