package cn.adwalker.ad.control.service;

import java.util.concurrent.ExecutionException;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.control.dao.OperationConfirmIncomeDao;
import cn.adwalker.ad.control.pool.SpringDatePool;
import cn.adwalker.ad.control.util.AppContext;


@Repository("refreshEscoreConfigService")
public class RefreshEscoreConfigService {
	
	@Resource
	private OperationConfirmIncomeDao confirmIncomeDao;
	
	//系统基础配置信息定时器
	public void executeInternal()
			throws ExecutionException {
		ApplicationContext ctx = AppContext.getApplicationContext();
		SpringDatePool springDatePool = (SpringDatePool) ctx.getBean("springDatePool");
		springDatePool.initPlatformConfig();
		try {
			springDatePool.initSDKConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
