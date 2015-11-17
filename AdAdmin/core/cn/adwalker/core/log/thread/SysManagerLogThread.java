package cn.adwalker.core.log.thread;

import org.springframework.context.ApplicationContext;

import cn.adwalker.core.spring.AppContext;
import cn.adwalker.model.common.dao.ISysLogDao;
import cn.adwalker.model.log.domain.SysLog;

/**
 * 
* <p>Title: EScoreManagerLogThread</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author    cuidd
* @date       2014-3-19
 */
public class SysManagerLogThread extends Thread {

	private SysLog managerLog;

	public SysManagerLogThread() {

	}

	public SysManagerLogThread(SysLog managerLog) {
		this.managerLog = managerLog;
	}

	public void run() {
		ApplicationContext ctx = AppContext.getApplicationContext();
		ISysLogDao managerLogDao = (ISysLogDao) ctx.getBean("eScoreManagerLogDao");
		managerLogDao.save(managerLog);
	}

}
