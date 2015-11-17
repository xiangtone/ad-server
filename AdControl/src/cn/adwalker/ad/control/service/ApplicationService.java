package cn.adwalker.ad.control.service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.control.dao.ApplicationDao;
import cn.adwalker.ad.control.domain.Application;
import cn.adwalker.ad.control.exception.DatabaseException;
import cn.adwalker.ad.control.logger.ApplicationLogger;
import cn.adwalker.ad.control.util.ConfigUtil;
import cn.adwalker.ad.control.util.DateUtils;
import cn.adwalker.ad.control.util.LogUtil;

@Repository("applicationService")
public class ApplicationService {
	private static Logger log = Logger.getLogger(ApplicationService.class);
	@Resource
	private ApplicationDao applicationDao;
	
	/**
	 * 每天每小时的第10分钟，开始记录日志
	 * @throws DatabaseException 
	 */
	public void applicationDayHourTaskLog() throws DatabaseException {
		log.info("开始----applicationDayHourTaskLog");
		List<Application> applicationList=applicationDao.getApplicationList();
		Date date=DateUtils.parseDate(DateUtils.getOneHoursAgoTime(00,00),"yyyy-MM-dd HH:mm:ss");
		if (applicationList != null) {
			String path = ResourceBundle.getBundle("config").getString("log4j.appender.ApplicationLogger.File")+"."+DateUtils.getDateStringByPattern(date, "yyyy-MM-dd-HH");
			Logger log = LogUtil.getLogger(path,cn.adwalker.ad.control.logger.PlacementLogger.class);
			String applicationInfo = ApplicationLogger.getApplicationInfo(applicationList);
			 if(applicationInfo != null) {			 
				log.info(applicationInfo);
			}
			String result = ResourceBundle.getBundle("config").getString("log4j.appender.ApplicationLogger.File")+"."+DateUtils.getDayBefore(Integer.parseInt(ConfigUtil.getString("log.before.day")));
			File file =new File(result);
			if (file.isFile() && file.exists()) {
				file.delete();
			}
		}	
		log.info("结束----applicationDayHourTaskLog");
	}

}
