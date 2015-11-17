package cn.adwalker.ad.control.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.control.config.StatusConstant;
import cn.adwalker.ad.control.dao.AdDao;
import cn.adwalker.ad.control.dao.AdScheduleDao;
import cn.adwalker.ad.control.exception.DatabaseException;
import cn.adwalker.ad.control.util.CacheUtils;
import cn.adwalker.ad.control.util.DateUtils;
import cn.adwalker.ad.control.vo.AdScheduleVo;

@Repository("adTaskService")
public class AdTaskService {
	private static Logger log = Logger.getLogger(AdTaskService.class);
	@Resource
	private AdScheduleDao adScheduleDao;

	@Resource
	private AdDao adDao;
	
	//1、查询出广告的实时状态
	//2、根据定义的优先级规则过滤不用执行的定时任务
	//3、根据广告的实时状态过滤不用执行的定时任务
	//4、更新广告状态及清理缓存
	//系统定时任务调用 
	public void adTask() throws Exception {
		log.info("开始----adTask");
		Date date = new Date();
		dataMain(date);
		log.info("结束----adTask");
	}
	//测试调用
	public void adTask(String date) throws Exception {
		dataMain(new Date());
	}
	//广告上下线定时器
	@Transactional(rollbackFor = { Exception.class })
	public void dataMain(Date date) throws Exception {
		/*
		 * 1、查询出广告的实时状态 2、根据定义的优先级规则过滤不用执行的定时任务 3、根据广告的实时状态过滤不用执行的定时任务
		 * 4、更新广告状态及清理缓存
		 */
		List<AdScheduleVo> list = (List<AdScheduleVo>) adScheduleDao.findAll(new Object[] { date }, AdScheduleVo.class);
		List<Object[]> ads = null;
		// 定时列表存放一个map里边用来处理任务的优先级
		if (list != null && list.size() > 0) {
			ads = getAdTask(list);
		}
		if (ads != null && ads.size() > 0) {
			adDao.batchUpdate(ads);
			// 更新缓存
			for (Object o[] : ads) {
				CacheUtils.updateAdCache(Long.valueOf(o[3].toString()));
			}
			
			//媒体和广告防止作弊，缓存接口
			CacheUtils.updateAdMediaDeleteCache("alliosadnum");
		}
	}

	/**
	 * Title: getTaskLevel
	 * Description:定制广告上下线优先级
	 * @return
	 * @author cuidd
	 * @date 2013-9-14
	 * @return Map<Long,AdScheduleVo>
	 * @version 1.0
	 * @throws DatabaseException 
	 */
	private List<Object[]> getAdTask(List<AdScheduleVo> list) throws DatabaseException {
		List<Object[]> ads = null;
		/*
		 * 优先级 下线>超量下线后上线任务---按时间先后顺序 下线****上线-----按优先级
		 * 上线****超量下线后上线任务---无冲突，按时间先后顺序
		 */
		if (list != null && list.size() > 0) {
			Map<Long, AdScheduleVo> map = new HashMap<Long, AdScheduleVo>();
			List<Object[]> scheduleList = new ArrayList<Object[]>();
			ads = new ArrayList<Object[]>();
			for (AdScheduleVo vo : list) {
				// 广告优先级判断
				if (map.get(vo.getAd_id()) == null) {
					map.put(vo.getAd_id(), vo);
				} else {
					// 判断有限级
					AdScheduleVo tmp = map.get(vo.getAd_id());

					boolean flag = false;// 标识，判断是新的定时调度有用还旧的定时调度有用
					// 上线与下线
					if ((tmp.getTask_type() == 1 && vo.getTask_type() == 2)
							|| (tmp.getTask_type() == 2 && vo.getTask_type() == 1)) {
						// 判断新的时间是否>旧的时间
						if (DateUtils.compare(vo.getTask_time(),tmp.getTask_time())) {
							map.put(vo.getAd_id(), vo);// 更新要执行的任务
							flag = true;
						}
					} else if ((tmp.getTask_type() == 1 && vo.getTask_type() == 3)
							|| (tmp.getTask_type() == 3 && vo.getTask_type() == 1)) {
						// 上线与定时下线后上线
						if (DateUtils.compare(vo.getTask_time(),tmp.getTask_time())) {
							map.put(vo.getAd_id(), vo);
							flag = true;
						}
					} else if ((tmp.getTask_type() == 2 && vo.getTask_type() == 3)
							|| (tmp.getTask_type() == 3 && vo.getTask_type() == 2)) {
						// 下线与超量下线后上线
						if (vo.getTask_type() == 2) {
							map.put(vo.getAd_id(), vo);
							flag = true;
						} else {
							map.put(tmp.getAd_id(), tmp);
						}
					} else {
						
					}

					if (flag) {
						scheduleList.add(new Object[] { new Date(), -4,tmp.getId() });
					} else {
						scheduleList.add(new Object[] { new Date(), -4,vo.getId() });
					}
				}
			}

			// 根据过滤后的广告进行进行实时状态判断操作的必要性，并装配广告更新数据
			if (map != null && !map.isEmpty()) {
				AdScheduleVo vo = null;
				for (Long id : map.keySet()) {
					vo = map.get(id);
					int status = 1;
					// 上线任务
					if (vo.getType() == 1) {
						// 如果广告已经是在线状态就不用操作
						if (vo.getAd_status() != StatusConstant.AD_STATUS_ONLINE) {
							ads.add(new Object[] {StatusConstant.AD_STATUS_ONLINE,new Date(), null, vo.getAd_id() });
						} else {
							status = -1;
						}
					} else {
						if (vo.getAd_status() != StatusConstant.AD_STATUS_ONLINE) {
							// 已下线，更新定时任务
							status = -1;
						} else {
							ads.add(new Object[] {StatusConstant.AD_STATUS_OVER_TIME, null,new Date(), vo.getAd_id() });
						}
					}
					scheduleList.add(new Object[] { new Date(), status,vo.getId() });
					adScheduleDao.batchUpdate(scheduleList);
				}
			}
		}
		return ads;
	}
	

}
