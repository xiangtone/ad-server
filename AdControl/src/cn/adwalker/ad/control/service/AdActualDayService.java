package cn.adwalker.ad.control.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.control.dao.AdActualDayDao;
import cn.adwalker.ad.control.dao.AdDao;
import cn.adwalker.ad.control.dao.AdScheduleDao;
import cn.adwalker.ad.control.domain.Ad;
import cn.adwalker.ad.control.domain.AdActualDay;
import cn.adwalker.ad.control.domain.AdSchedule;
import cn.adwalker.ad.control.exception.DatabaseException;
import cn.adwalker.ad.control.logger.DownLineLogger;
import cn.adwalker.ad.control.service.factory.AdActualDayFactory;
import cn.adwalker.ad.control.util.CacheUtils;
import cn.adwalker.ad.control.util.ConfigUtil;
import cn.adwalker.ad.control.util.DateUtils;
import cn.adwalker.ad.control.util.Result;
import cn.adwalker.ad.control.util.ReturnError;
import cn.adwalker.ad.control.vo.AdActualDayVo;

@Repository("adActualDayService")
public class AdActualDayService {
	
	@Resource
	private AdActualDayDao adActualDayDao;
	
	@Resource
	private AdDao adDao;
	
	@Resource
	private AdScheduleDao adScheduleDao;

	/**
	 * 记录dataImportSucceed方法执行的小时,防止每小时重复刷新
	 */
	private static String excuteHour;
	
	/**
	 * 二分钟刷新一次缓存中的数据到数据库中,并判断广告是否超量.
	 * @throws DatabaseException 
	 */
	@Transactional(rollbackFor = { Exception.class })
	//@Scheduled(cron="30 0/2 * * * ?")
	public void refreshAdActualDay() throws DatabaseException {
		// 缓存数据写入数据库
		AdActualDayFactory adActualDayFactory = AdActualDayFactory.newInstance();
		adActualDayFactory.refresh();
		// 读取上线状态并超量的广告,进行下线处理.
		List<AdActualDay> adActualDayList = adActualDayDao.getReachActualAdIdList();
		List<Long> adIdList = new ArrayList<Long>();
		List<AdSchedule> adScheduleList = new ArrayList<AdSchedule>();
		if(adActualDayList != null && !adActualDayList.isEmpty()) {
			Date tomorrowDate = DateUtils.parseDate(DateUtils.formatDate(DateUtils.getDateAddDay(1), "yyyy-MM-dd"), "yyyy-MM-dd");
			for(AdActualDay adActualDay : adActualDayList) {
				// 写入日志,记录下线数量
				if(adActualDay != null) {
					Long adId = adActualDay.getAdId();
					// 如果广告下线时间为今天,则直接设为下线,不增加自动上线
					Ad ad = adDao.getAdById(adId);
					Date offlineTime = ad.getOfflineTime();
					String offlineDate = "";
					if(offlineTime != null) {
						offlineDate = DateUtils.getDateStringByPattern(offlineTime, "yyyy-MM-dd");
					}
					String today = DateUtils.getDateStringByPattern("yyyy-MM-dd");
					if(today.equals(offlineDate)) { // 直接下线
						adDao.updateAdStatus(adId, Integer.parseInt(ConfigUtil.getString("ad.status.downline")));
					} else {
						adIdList.add(adId);
						// 增加T_AD_SCHEDULE表记录,上系统自动在十二点以后上线.
						AdSchedule adSchedule = new AdSchedule();
						adSchedule.setType(Integer.parseInt(ConfigUtil.getString("ad.schedule.type.online")));
						adSchedule.setAdId(adId);
						adSchedule.setStatus(Integer.parseInt(ConfigUtil.getString("ad.schedule.status.not.execution")));
						adSchedule.setTaskTime(tomorrowDate);
						// adSchedule.setUpdateTime(new Date());
						adSchedule.setTaskType(Integer.parseInt(ConfigUtil.getString("ad.schedule.task.type.system")));
						adScheduleList.add(adSchedule);
					}
					// 清除缓存
					CacheUtils.updateAdCache(adId);
					//媒体和广告防止作弊，缓存接口
					CacheUtils.updateAdMediaDeleteCache("alliosadnum");
				}
			}
			DownLineLogger downLineLogger = new DownLineLogger();
			downLineLogger.log(adActualDayList);
			adDao.updateAdStatusBatch(adIdList, Integer.parseInt(ConfigUtil.getString("ad.status.exceed.downline")));
			adScheduleDao.saveBatch(adScheduleList);
		}
	}

	/**
	 * 把当天的广告数量置为无效,设定每天23:56分执行,这个时间就不需要统计下线了
	 * @throws DatabaseException 
	 * @throws NumberFormatException 
	 */
	//@Scheduled(cron="15 56 23 * * ?")
	public void invalidAdActualDay() throws NumberFormatException, DatabaseException {
		adActualDayDao.invalid(DateUtils.getDateStringByPattern("yyyy-MM-dd"));
	}
	
	/**
	 * 把广告实时控量写入缓存
	 * @param adActualDayVoList
	 * @return
	 */
	public Result putAdActualDayAll(List<AdActualDayVo> adActualDayVoList) {
		Result result = new Result();
		if(adActualDayVoList != null && !adActualDayVoList.isEmpty()) {
			AdActualDayFactory adActualDayFactory = AdActualDayFactory.newInstance();
			adActualDayFactory.putAdActualDayAll(adActualDayVoList);
		} else {
			result.setReturnError(ReturnError.PARAM_INVALID);
			result.setSucceed(false);
		}
		return result;
	}

	/**
	 * 小时数据入库成功之后,把广告实时控量更改为真实的数量
	 * @param currentDate
	 * @return
	 * @throws DatabaseException 
	 */
	public Result writeRealData() throws DatabaseException {
		Result result = new Result();
		String currentHour = DateUtils.formatDate(DateUtils.getNow(), "HH");
		// 零点不刷新,并且每个小时不能重复刷新
		if(currentHour.equals(excuteHour) || currentHour.equals("00")) {
			return result;
		}
		excuteHour = currentHour;
		String currentDate = DateUtils.getDateStringByPattern("yyyy-MM-dd");
		adActualDayDao.updateRealData(currentDate);
		adActualDayDao.updateIOSData(currentDate);
		/*List<AdActualDay> adActualDayList = staticAdByhourDao.getStaticAdByhourList(currentDate);
		if(adActualDayList != null && !adActualDayList.isEmpty()) {
			adActualDayDao.updateBatch(adActualDayList, currentDate);
		}*/
		return result;
	}
	
}
