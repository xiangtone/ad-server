package cn.adwalker.ad.control.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.control.dao.IosEffectByDayDao;
import cn.adwalker.ad.control.util.DateUtils;

@Repository("iosStaticTaskService")
public class IosStaticTaskService {
	
	private static Logger log = Logger.getLogger(IosStaticTaskService.class);
	@Resource
	private IosEffectByDayDao iosEffectByDayDao;
	
	/**
	 * 每天每小时的第10分钟，开始记录日志
	 */
	public void tranceDataEffectByDay() throws Exception {
		log.info("开始--------tranceDataEffectByDay");
		// 生成今天数据，从今天开始半个月的数据
		// 更新ios版本，不是很准确
		// iIosEffectByDayDao.update("UPDATE T_IOS_ACTION_LOG SET OS_VERSION='IOS7' WHERE (MAC IS NULL OR length(MAC)!=12) AND OS_VERSION IS NULL ", null);
		// iIosEffectByDayDao.update("UPDATE T_IOS_ACTION_LOG SET OS_VERSION='IOS6' WHERE (MAC IS not NULL and length(MAC)=12) AND OS_VERSION IS NULL", null);
		String date = DateUtils.getBeforeDay(-1);
		// 清理掉时间区间内的数据
		iosEffectByDayDao.deleteByDay(date);
		// 计算数据
		iosEffectByDayDao.updateByDay( new Object[] { date + " 00:00:00", date + " 23:59:59", date + " 00:00:00", date + " 23:59:59" });	
		log.info("结束--------tranceDataEffectByDay");
	}
	
	public void tranceDataEffectByDayTest(String date) throws Exception {
		// 更新ios版本，不是很准确
		// iIosEffectByDayDao.update("UPDATE T_IOS_ACTION_LOG SET OS_VERSION='IOS7' WHERE (MAC IS NULL  OR length(MAC)!=12) AND OS_VERSION IS NULL ", null);
		// iIosEffectByDayDao.update("UPDATE T_IOS_ACTION_LOG SET OS_VERSION='IOS6' WHERE (MAC IS not  NULL and length(MAC)=12) AND OS_VERSION IS NULL", null);
		// 清理掉时间区间内的数据
		iosEffectByDayDao.deleteByDay(date);
		// 计算数据
		iosEffectByDayDao.updateByDay(new Object[] {date + " 00:00:00", date + " 23:59:59", date + " 00:00:00", date + " 23:59:59" });
	}
	
	
	public static void main(String[] args) throws Exception {
		
		
		ApplicationContext context = new FileSystemXmlApplicationContext(new String[] { "WebContent/WEB-INF/applicationContext.xml" });
		IosStaticTaskService service = (IosStaticTaskService) context.getBean("iosStaticTaskService");
		service.tranceDataEffectByDayTest("2014-11-06");


}

}
