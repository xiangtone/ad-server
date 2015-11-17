package cn.adwalker.ad.control.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.control.dao.ReportAdByDayDao;
import cn.adwalker.ad.control.util.DateUtils;

@Repository("reportAdByDayTaskService")
public class ReportAdByDayTaskService {
	private static Logger log = Logger.getLogger(ReportAdByDayTaskService.class);
	@Resource
	private ReportAdByDayDao colligateByDayDao;

	
	//系统定时任务调用
	public void tranceData() throws Exception {
		log.info("开始----ReportAdByDayTaskService.tranceData");
		dataMain(DateUtils.getBeforeDay(-1));
		log.info("结束----ReportAdByDayTaskService.tranceData");
	}
	//测试调用
	public void tranceData(String date) throws Exception {
		dataMain(date);
	}
	
	public void dataMain(String date) {
		if (date != null) {
			// 按天增加数据 防止数据重跑，首先删除数据
			colligateByDayDao.delColligate(date);			
			colligateByDayDao.update(date);
		}
	}
	
}
