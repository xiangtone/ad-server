package cn.adwalker.ad.control.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.control.dao.ActivateIosDao;
import cn.adwalker.ad.control.dao.ActivateIosDetailDao;
import cn.adwalker.ad.control.dao.CampaignDao;
import cn.adwalker.ad.control.domain.ActivateClick;
import cn.adwalker.ad.control.domain.ActivateClickByCampaignAndType;
import cn.adwalker.ad.control.domain.ActivateNumDetailIos;
import cn.adwalker.ad.control.domain.ActivateNumIos;
import cn.adwalker.ad.control.domain.Campaign;
import cn.adwalker.ad.control.domain.SimpleBean;
import cn.adwalker.ad.control.util.DateUtils;
import cn.adwalker.ad.control.vo.ActivateNumDetailIosVo;


@Repository("adEffectIosTaskService")
public class AdEffectIosTaskService {
	private static final Logger log = LoggerFactory.getLogger(AdEffectIosTaskService.class);
	@Resource //明细表dao服务
	private ActivateIosDetailDao activateNumDetailIosDao;

	@Resource //主表dao服务
	private ActivateIosDao activateNumIosDao;

	@Resource  //活动dao服务
	private CampaignDao campaignDao;

	//系统定时任务调用
	public void tranceIosData() throws Exception {
		log.info("开始----tranceIosData");
		dataMain(DateUtils.getBeforeDay(-1));
		log.info("结束----tranceIosData");
	}
	//测试调用
	public void tranceIosData(String date) throws Exception {
		dataMain(date);
	}

	public void dataMain(String date) throws Exception {
		// 0408修改代码逻辑,数据依据的准则是流水数据（不是根据广告投放记录，如果以后有走现在的再单独处理），数据处理方式临时表
		// 所有广告SELECT * from T_LOG_AD_PLACEMENT LEFT JOIN where os='ios' and
		// 1、查询广告投放记录。（渠道广告单条，平台只分到形式）：a、平台广告按形式分组查询。b、渠道广告全部。AdEffectIosTask
		//String date = DateUtils.getBeforeDay(-1);
		Map<Long, List<ActivateNumDetailIosVo>> map = new HashMap<Long, List<ActivateNumDetailIosVo>>();// 存放每个活动对应的明细
		map = getChannelAd(date, map);
		map = getPlatformAd(date, map);
		// 2、两部分数据合并处理,查询活动相关信息，活动当天的激活数与点击数，数据组合后
		if (map != null && map.size() > 0) {
			Set<Long> set = map.keySet();
			for (Long key : set) {
				List<ActivateNumDetailIosVo> tempList = map.get(key);// 处理明细数据
					if (tempList != null && tempList.size() > 0) {
						Campaign c = campaignDao.getCampaign(key);//获取活动的相关信息，比如单价、结算方式。。。。
						if(key == 1908) {
							System.out.println("adffsdf");
						}
							if(c.getConfirm_mode()!=0){
							List<ActivateClick> numList = getNumByCampaign(key, date);// 获取整个活动的点击数、激活数
							if(numList!=null){
								for (ActivateClick num : numList) {
									if (num != null) {
										ActivateNumIos total = new ActivateNumIos();
										total.setCampaign_id(key);
										total.setStatic_date(date);
										total.setCreate_time(new Date());
										total.setStatus(0);
										total.setBalance_model(c.getCharge_type());
										total.setActivate(num.getActivate());
										total.setClick(num.getClick());
										total.setPrice(num.getPrice());
										Long id = activateNumIosDao.insert(total);// 插入总数据
										for (ActivateNumDetailIosVo tmp : tempList) {
											this.insertDetail(tmp, id, date);
										}
									} else {
										System.out.println("--------" + c.getId());
									}
								}
							}else {
								System.out.println("--------" + c.getId());
							}	
							
						}else{
								ActivateNumIos total = new ActivateNumIos();
								total.setCampaign_id(key);
								total.setStatic_date(date);
								total.setCreate_time(new Date());
								total.setStatus(0);
								total.setBalance_model(c.getCharge_type());
								total.setActivate(0);
								total.setClick(0);
								total.setPrice(c.getPrice());
								Long id = activateNumIosDao.insert(total);// 插入总数据
								for (ActivateNumDetailIosVo tmp : tempList) {
									this.insertDetail(tmp, id, date);
								}
						}
						
					}
			}
		}
		
	}

	
	private void insertDetail(ActivateNumDetailIosVo tmp, Long id, String date)
			throws Exception {
		ActivateNumDetailIos entity = new ActivateNumDetailIos();
		entity.setParent_id(id);
		entity.setMedia_id(tmp.getMedia_id());// 媒体id
		entity.setStatic_date(date);// 统计日期
		entity.setType_id(tmp.getType_id());// 广告形式
		entity.setCreate_time(new Date());// 创建时间
		entity.setStatus(0);// 状态
		entity.setBlance_mode(tmp.getBlance_mode());// 计算方式
		entity.setOut_price(tmp.getOut_price());// 投放单价
		entity.setMedia_type(tmp.getMedia_type());// 媒体类型
		entity.setClick(tmp.getClick());// 点击
		entity.setActivate(tmp.getActivate());// 激活
		entity.setAd_id(tmp.getAd_id());
		activateNumDetailIosDao.insert(entity);// 插入明细数据

	}

	//活动相关的点击数和激活数
	private List<ActivateClick> getNumByCampaign(Long campaign_id, String date)
			throws Exception {
		//ActivateClick result = null;
		if (campaign_id != null && date != null) {
			// 获取活动对应的配置id
			List<SimpleBean> confirmList = activateNumIosDao.getSimpleBeanList(campaign_id);
			// 如果存在配置id
			if (confirmList != null && confirmList.size() > 0) {
				List<ActivateClick> list = activateNumIosDao.getActivateClickList(date,confirmList);
				/*if (list != null && list.size() > 0) {
					result = list.get(0);
				}*/
				return list ;
			}
		}
		return new ArrayList<ActivateClick>();
	}

	
	//获取活动下的渠道广告相关信息
	private Map<Long, List<ActivateNumDetailIosVo>> getChannelAd(String date,
			Map<Long, List<ActivateNumDetailIosVo>> map) throws Exception {
		if (map != null && date != null) {
			List<ActivateNumDetailIosVo> list = activateNumIosDao.getChannelAdIosList(date);
			if (list != null && list.size() > 0) {
				for (ActivateNumDetailIosVo bean : list) {
					List<ActivateNumDetailIosVo> tmpList = null;
					if (map.get(bean.getCampaign_id()) == null) {
						tmpList = new ArrayList<ActivateNumDetailIosVo>();
					} else {
						tmpList = map.get(bean.getCampaign_id());
					}
					bean.setMedia_type(1);
					tmpList.add(bean);
					map.put(bean.getCampaign_id(), tmpList);
				}
			}
		}
		return map;
	}

	//获取活动下，平台广告的相关信息
	private Map<Long, List<ActivateNumDetailIosVo>> getPlatformAd(String date,
			Map<Long, List<ActivateNumDetailIosVo>> map) throws Exception {
		// 直接通过 T_IOS_ACTION_LOG获取平台广告的投放信息？同时还可以对广告进行过滤。。。。;会不会受线下活动的影响。。。。。
		// 初始化数据
		Map<Long, List<ActivateClickByCampaignAndType>> numMap = null;
		if (date != null) {
			List<ActivateClickByCampaignAndType> list = activateNumIosDao.getPlatformNumIosList(date+ " 23:59:59",date+ " 00:00:00");
			if (list != null && list.size() > 0) {
				numMap = new HashMap<Long, List<ActivateClickByCampaignAndType>>();
				for (ActivateClickByCampaignAndType bean : list) {
					if(bean.getCampaign_id()!=null){
						Long key = bean.getCampaign_id();
						List<ActivateClickByCampaignAndType> tmpList = null;
						if (map.get(key) != null) {
							tmpList = numMap.get(key);
						} else {
							tmpList = new ArrayList<ActivateClickByCampaignAndType>();
						}
						tmpList.add(bean);
						numMap.put(key, tmpList);
					}
					
				}
			}
		}
		return cc(date, map, numMap);
	}
	
	
	private Map<Long, List<ActivateNumDetailIosVo>>  cc(String date,Map<Long, List<ActivateNumDetailIosVo>> map,Map<Long, List<ActivateClickByCampaignAndType>> numMap)throws Exception{
		if (date != null && map != null) {
			/*
			 * 1、渠道整个平台的投放记录。
			 * 2、从统计数据中匹配数据：如果是线上确认，给赋值；否则默认是0；
			 */
			
			List<ActivateNumDetailIosVo> list = activateNumIosDao.getPlatformAdIosList(date);//获取平台投放记录
			if (list != null && list.size() > 0) {
				//循环平台投放记录
				for (ActivateNumDetailIosVo bean : list) {
					List<ActivateNumDetailIosVo> tmpList = null;
					//从历史的map里取值---历史里可能有渠道的数据集合
					if (map.get(bean.getCampaign_id()) == null) {
						tmpList = new ArrayList<ActivateNumDetailIosVo>();
					} else {
						tmpList = map.get(bean.getCampaign_id());
					}
					bean.setMedia_id(0L);// 媒体id
					bean.setMedia_type(0);// 媒体类型--平台类型都为0
					if (numMap != null&&numMap.get(bean.getCampaign_id()) != null) {
						Campaign c = campaignDao.getCampaign(bean.getCampaign_id());//获取活动的相关信息，比如单价、结算方式。。。。
						if(c!=null && c.getConfirm_mode()!=0){
							List<ActivateClickByCampaignAndType> numList = numMap.get(bean.getCampaign_id());
							for (ActivateClickByCampaignAndType num : numList) {
								if (num.getType_id().equals(bean.getType_id())) {
									bean.setClick(num.getClick());
									bean.setActivate(num.getActivate());
									break;
								}
							}
						}
					}else{
						bean.setClick(0);
						bean.setActivate(0);
					}
					tmpList.add(bean);
					map.put(bean.getCampaign_id(), tmpList);
				}
			}
		}
		return map;
		
	}

	

	
	
	
	//手动跑数据
	public static void main(String[] args) throws Exception {
		
		
			ApplicationContext context = new FileSystemXmlApplicationContext(new String[] { "WebContent/WEB-INF/applicationContext.xml" });
			AdEffectIosTaskService service = (AdEffectIosTaskService) context.getBean("adEffectIosTaskService");
			service.tranceIosData("2014-12-02");
	

	}

}
