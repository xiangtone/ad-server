package cn.adwalker.ad.control.service;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.control.dao.IOSIncomNumberDao;
import cn.adwalker.ad.control.util.DateUtils;

@Repository("iosIncomNumberService")
public class IosIncomNumberService {
	private static Logger log = Logger.getLogger(IosIncomNumberService.class);
	@Resource
	private IOSIncomNumberDao reDao;

	//系统定时任务调用 
	public void tranceDataIosIncomNumber() throws Exception {
		log.info("开始--IosIncomNumberService--tranceDataIosIncomNumber");
		Date bengin = DateUtils.getDateAddHourMS(new Date(), -1, -5, 0);
		Date end = DateUtils.getDateAddHourMS(new Date(), -1, 0, 0);
		String beginTime = DateUtils.getDateStringByPattern(bengin, "yyyy-MM-dd HH:mm:ss");
		String endTime = DateUtils.getDateStringByPattern(end, "yyyy-MM-dd HH:mm:ss");
		dataMain(beginTime,endTime);
		log.info("结束--IosIncomNumberService--tranceDataIosIncomNumber");
	}
	//测试调用
	public void tranceDataIosIncomNumber(String beginTime,String endTime) throws Exception {
		if (beginTime != null && endTime !=null) {
			dataMain(beginTime + " 00:00:00",endTime + " 23:59:59");
		}
	}
	
	/**
	* <p>Title: tranceDataIosClick</p>
	* <p>Description:TODO</p>
	* @throws Exception
	* @author cuidd
	* @date 2014年6月18日
	* @return void
	* @version 1.0
	 */
	public void dataMain(String beginTime,String endTime) throws Exception {
		reDao.copyActionLog(beginTime, endTime);

	}

	

}
