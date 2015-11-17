package cn.adwalker.ad.control.service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.control.dao.UserDao;
import cn.adwalker.ad.control.domain.User;
import cn.adwalker.ad.control.exception.DatabaseException;
import cn.adwalker.ad.control.logger.UserLogger;
import cn.adwalker.ad.control.util.ConfigUtil;
import cn.adwalker.ad.control.util.DateUtils;
import cn.adwalker.ad.control.util.LogUtil;
import cn.adwalker.ad.control.util.Result;

@Repository("userService")
public class UserService {
	
	@Resource
	private UserDao userDao;
	
	/**
	 * 每天每小时的第10分钟，开始记录日志
	 * @throws DatabaseException 
	 */
	public void userDayHourTaskLog() throws DatabaseException {
		List<User> userList=userDao.getUserList(DateUtils.getOneHoursAgoTime(00,00), DateUtils.getOneHoursAgoTime(59,59));
		Date date=DateUtils.parseDate(DateUtils.getOneHoursAgoTime(00,00),"yyyy-MM-dd HH:mm:ss");
		if (userList != null) {
			String path = ResourceBundle.getBundle("config").getString("log4j.appender.UserLogger.File")+"."+DateUtils.getDateStringByPattern(date, "yyyy-MM-dd-HH");
			Logger log = LogUtil.getLogger(path,cn.adwalker.ad.control.logger.UserLogger.class);
			String userInfo = UserLogger.getUserInfo(userList);
			 if(userInfo != null) {			 
				log.info(userInfo);
			}
			String result = ResourceBundle.getBundle("config").getString("log4j.appender.UserLogger.File")+"."+DateUtils.getDayBefore(Integer.parseInt(ConfigUtil.getString("log.before.day")));
			File file =new File(result);
			if (file.isFile() && file.exists()) {
				file.delete();
			}
		}				
	}
	/**
	 * 根据每天的手动补日志量
	 * @param dateTime
	 * @param hour
	 * @return
	 * @throws DatabaseException 
	 */
	public Result userDayHourLog(String dateTime,int hour) throws DatabaseException {
		Result result = new Result();
		Date date = DateUtils.parseDate(dateTime, "yyyy-MM-dd");
		Date bdate = DateUtils.getDateAddHourMS(date, hour, 00, 00);
		Date sdate = DateUtils.getDateAddHourMS(date, hour, 59, 59);
		List<User> userList=userDao.getUserList(DateUtils.getDateStringByPattern(bdate, "yyyy-MM-dd HH:mm:ss"),DateUtils.getDateStringByPattern(sdate, "yyyy-MM-dd HH:mm:ss"));
		if (userList != null) {
			String path =ResourceBundle.getBundle("config").getString("log4j.appender.UserLogger.File")+"."+DateUtils.getDateStringByPattern(bdate, "yyyy-MM-dd-HH");	
			File file = new File(path);
			if (file.isFile() && file.exists()) {
				file.delete();
			}
			Logger log = LogUtil.getLogger(path,cn.adwalker.ad.control.logger.UserLogger.class);
			String userInfo = UserLogger.getUserInfo(userList);
			 if(userInfo != null) {			 
				log.info(userInfo);
			}
			result.setSucceed(true);
			result.setErrorMsg(DateUtils.getDateStringByPattern(bdate, "yyyy-MM-dd-HH")+"时，日志写入总数："+userList.size());
		}		
	   return result;
	}
	
}
