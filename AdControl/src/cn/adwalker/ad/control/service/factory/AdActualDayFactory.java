package cn.adwalker.ad.control.service.factory;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import cn.adwalker.ad.control.dao.AdActualDayDao;
import cn.adwalker.ad.control.domain.AdActualDay;
import cn.adwalker.ad.control.exception.DatabaseException;
import cn.adwalker.ad.control.util.AppContext;
import cn.adwalker.ad.control.util.BeanUtils;
import cn.adwalker.ad.control.util.DateUtils;
import cn.adwalker.ad.control.vo.AdActualDayVo;

/**
 * 广告实时控量工厂类
 * @author liuchen
 */
public class AdActualDayFactory {

	private Log log = LogFactory.getLog(getClass());

	private AdActualDayDao adActualDayDao;
	
	/**
	 * 存储广告实时控量
	 */
	private ConcurrentHashMap<String, AdActualDay> adActualDayMap = new ConcurrentHashMap<String, AdActualDay>();

	/**
	 * 工厂类实例
	 */
	private static AdActualDayFactory self = new AdActualDayFactory();
	
	/**
	 * 私有
	 */
	private AdActualDayFactory() {
		ApplicationContext ctx = AppContext.getApplicationContext();
		adActualDayDao = (AdActualDayDao) ctx.getBean("adActualDayDao");
	}
	
	/**
	 * 刷新广告实时控量,把缓存里的数据写入数据库
	 * @throws DatabaseException 
	 */
	public synchronized void refresh() throws DatabaseException {
		ConcurrentHashMap<String, AdActualDay> temp = adActualDayMap;
		adActualDayMap = new ConcurrentHashMap<String, AdActualDay>();
		Iterator<String> iter = temp.keySet().iterator();
		while(iter.hasNext()) {
			AdActualDay adActualDay = temp.get(iter.next());
			adActualDay.setUpdateTime(new Date());
			AdActualDay adActualDayDB = adActualDayDao.getByAdIdAndDate(adActualDay.getAdId(), adActualDay.getStaticDate());
			if(adActualDayDB == null) {
				adActualDay.setStatus(0);
				adActualDayDao.save(adActualDay);
			} else {
				adActualDayDB.setImpressionsAmount(adActualDayDB.getImpressionsAmount() + adActualDay.getImpressionsAmount());
				adActualDayDB.setClickAmount(adActualDayDB.getClickAmount() + adActualDay.getClickAmount());
				adActualDayDB.setDownloadAmount(adActualDayDB.getDownloadAmount() + adActualDay.getDownloadAmount());
				adActualDayDB.setActionAmount(adActualDayDB.getActionAmount() + adActualDay.getActionAmount());
				adActualDayDB.setUpdateTime(adActualDay.getUpdateTime());
				adActualDayDao.update(adActualDayDB);
			}
		}
		log.debug("刷新广告实时控量成功...");
	}
	
	/**
	 * 下线广告,先取出到量下线的广告,更改广告状态,然后删除mencached里的数据
	 */
	public void offline() {
		log.debug("下线广告实时控量成功...");
	}
	
	/**
	 * 获取工厂类实例
	 * @return
	 */
	public static AdActualDayFactory newInstance() {
		if(null != self) {
            return self;
        } else {
            self = new AdActualDayFactory();
            return self;
        }
	}
	
	/**
	 * 写入缓存
	 * @param adActualDayList
	 */
	public synchronized void putAdActualDayAll(List<AdActualDayVo> adActualDayVoList) {
		String currentDate = DateUtils.getDateStringByPattern("yyyy-MM-dd");
		for(AdActualDayVo adActualDayVo : adActualDayVoList) {
			AdActualDay temp = adActualDayMap.get(adActualDayVo.getAdId().longValue() + currentDate);
			if(temp != null) {
				temp.setImpressionsAmount(temp.getImpressionsAmount() + adActualDayVo.getImpressionsAmount());
				temp.setClickAmount(temp.getClickAmount() + adActualDayVo.getClickAmount());
				temp.setDownloadAmount(temp.getDownloadAmount() + adActualDayVo.getDownloadAmount());
				temp.setActionAmount(temp.getActionAmount() + adActualDayVo.getActionAmount());
			} else {
				temp = new AdActualDay();
				try {
					BeanUtils.copyProperties(temp, adActualDayVo);
				} catch (Exception e) {
					log.error("复制对象出现异常");
				}
				temp.setStaticDate(currentDate);
				adActualDayMap.put(temp.getAdId().longValue() + currentDate, temp);
			}
		}
		log.debug("加入缓存广告实时控量成功...");
	}

	/**
	 * 写入缓存
	 * @param adActualDay
	 */
	public synchronized void putAdActualDay(AdActualDayVo adActualDayVo) {
		if(adActualDayVo == null) {
			return;
		}
		String currentDate = DateUtils.getDateStringByPattern("yyyy-MM-dd");
		AdActualDay temp = adActualDayMap.get(adActualDayVo.getAdId().longValue() + currentDate);
		if(temp != null) {
			temp.setImpressionsAmount(temp.getImpressionsAmount() + adActualDayVo.getImpressionsAmount());
			temp.setClickAmount(temp.getClickAmount() + adActualDayVo.getClickAmount());
			temp.setDownloadAmount(temp.getDownloadAmount() + adActualDayVo.getDownloadAmount());
			temp.setActionAmount(temp.getActionAmount() + adActualDayVo.getActionAmount());
		} else {
			temp = new AdActualDay();
			try {
				BeanUtils.copyProperties(temp, adActualDayVo);
			} catch (Exception e) {
				log.error("复制对象出现异常");
			}
			temp.setStaticDate(currentDate);
			adActualDayMap.put(temp.getAdId().longValue() + currentDate, temp);
		}
		log.debug("加入缓存广告实时控量成功...");
	}
	
	public synchronized void clearAdActualDay() {
		adActualDayMap.clear();
	}
	
}
