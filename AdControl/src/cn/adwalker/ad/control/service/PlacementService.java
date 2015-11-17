package cn.adwalker.ad.control.service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.control.dao.PlacementDao;
import cn.adwalker.ad.control.domain.Placement;
import cn.adwalker.ad.control.exception.DatabaseException;
import cn.adwalker.ad.control.logger.PlacementLogger;
import cn.adwalker.ad.control.util.ConfigUtil;
import cn.adwalker.ad.control.util.DateUtils;
import cn.adwalker.ad.control.util.LogUtil;

@Repository("placementService")
public class PlacementService {
	private static Logger log = Logger.getLogger(PlacementService.class);
	@Resource
	private PlacementDao placementDao;
	
	/**
	 * 每天每小时的第10分钟，开始记录日志
	 * @throws DatabaseException 
	 */
	public void placementDayHourTaskLog() throws DatabaseException {
		log.info("开始--------placementDayHourTaskLog");
		List<Placement> placementList=placementDao.getPlacementList();
		Date date=DateUtils.parseDate(DateUtils.getOneHoursAgoTime(00,00),"yyyy-MM-dd HH:mm:ss");
		if (placementList != null) {
			String path = ResourceBundle.getBundle("config").getString("log4j.appender.PlacementLogger.File")+"."+DateUtils.getDateStringByPattern(date, "yyyy-MM-dd-HH");
			Logger log = LogUtil.getLogger(path,cn.adwalker.ad.control.logger.PlacementLogger.class);
			String placementInfo = PlacementLogger.getPlacementInfo(placementList);
			 if(placementInfo != null) {			 
				log.info(placementInfo);
			}
			String result = ResourceBundle.getBundle("config").getString("log4j.appender.PlacementLogger.File")+"."+DateUtils.getDayBefore(Integer.parseInt(ConfigUtil.getString("log.before.day")));
			File file =new File(result);
			if (file.isFile() && file.exists()) {
				file.delete();
			}
		}	
		log.info("结束--------placementDayHourTaskLog");
	}

}
