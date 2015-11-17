package cn.adwalker.ad.control.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.control.dao.OperationDevIncomeAuditDao;
import cn.adwalker.ad.control.util.DateUtils;


@Repository("devIncomeTaskService")
public class DevIncomeTaskService {
	private static Logger log = Logger.getLogger(DevIncomeTaskService.class);
	@Resource
	private OperationDevIncomeAuditDao devIncomeAuditDao;

	//生成四天前开发者收入数据
	//1、当天内根据应用、广告分组
	//2、取出成本金额、效果数据
	//3、对现有数据进行merge操作，如果存在更新判断状态，是否已经确认，如果没确认，更新数据，已经确认原值不变。
	//系统定时任务调用
	public void tranceDevIncome() throws Exception {
		log.info("开始----tranceDevIncome");
		dataMain(DateUtils.getBeforeDay(-4));
		log.info("结束----tranceDevIncome");
	}
	//测试调用
	public void tranceDevIncome(String date) throws Exception {
		dataMain(date);
	}

	//开发者收入定时器
	public void dataMain(String date) throws Exception {		
		if (date != null) {
			devIncomeAuditDao.updateDevIncome(new Object[] {date + " 00:00:00",date + " 00:00:00",0,date});
		}
		
	}
	

}
