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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.control.dao.PackageActivateDao;
import cn.adwalker.ad.control.dao.PackageActivateDetailDao;
import cn.adwalker.ad.control.domain.PackageActivate;
import cn.adwalker.ad.control.domain.PackageActivateDetail;
import cn.adwalker.ad.control.domain.PackageActivateInprice;
import cn.adwalker.ad.control.domain.PackageActivateTmp;
import cn.adwalker.ad.control.exception.DatabaseException;
import cn.adwalker.ad.control.util.DateUtils;


//定时任务---生成android预确认数录入基础数据</p>
//@Service("task.adeffectandroidservice")
//Android效果录入定时器

@Service("adEffectAndroidTaskService")
public class AdEffectAndroidTaskService {
	
	private static final Logger log = LoggerFactory.getLogger(AdEffectAndroidTaskService.class);
	
	@Resource
	private PackageActivateDao packageActivateDao;	//明细表dao服务
	
	@Resource
	private PackageActivateDetailDao packageActivateDetailDao;//主表dao服务

	//系统定时任务调用
	public void tranceAndroidData() throws Exception {
		log.info("开始----tranceAndroidData");
		dataMain(DateUtils.getBeforeDay(-1));
		log.info("结束----tranceAndroidData");
	}
	//测试调用
	public void tranceAndroidData(String date) throws Exception {
		dataMain(date);
	}
	

	//生成android预确认数录入基础数据
	//1、根据广告投放记录，以包为单位，获取当天这个包的投放单价、投放的媒体（平台只按形式分类）、接入单价、平台的激活数（参考数据）
	@Transactional(rollbackFor = { Exception.class })
	public void dataMain(String dateStr) throws Exception {
		//String dateStr = DateUtils.getBeforeDay(-1);
		// 1、分别查询平台、渠道的数据 2、合并数据  3、插入数据，并维护主表与明细表关联关系
		// 查询平台数据
		Map<Long, List<PackageActivateTmp>> map = null;
		List<PackageActivateDetail> list = null;
		List<PackageActivateTmp> unionList = new ArrayList<PackageActivateTmp>();
		List<PackageActivateTmp> platformList =packageActivateDao.getPackageFlatformList(dateStr); //platform(dateStr);
		if (platformList != null) {
			unionList.addAll(platformList);
		}
		List<PackageActivateTmp> channelList = packageActivateDao.getPackageChannelList(dateStr);
		if (channelList != null) {
			unionList.addAll(channelList);
		}
		map = getMap(unionList);
		if (map != null && !map.isEmpty()) {
			Set<Long> keySet = map.keySet();
			list = new ArrayList<PackageActivateDetail>();
			PackageActivateDetail detailEntity = null;
			PackageActivate entity;
			for (Long key : keySet) {
				entity = new PackageActivate();
				entity.setCreate_time(new Date());
				entity.setPackage_id(key);
				entity.setStatic_date(dateStr);
				entity.setStatus(0);
				Long id = packageActivateDao.savePackageActivate(entity);
				List<PackageActivateTmp> tmpList = map.get(key);
				for (PackageActivateTmp tmp : tmpList) {
					detailEntity = new PackageActivateDetail();
					detailEntity.setBlance_mode(tmp.getBlance_mode());
					detailEntity.setCreate_time(new Date());
					detailEntity.setPackage_id(tmp.getPackage_id());
					detailEntity.setParent_id(id);
					detailEntity.setStatic_date(dateStr);
					detailEntity.setMedia_id(tmp.getMedia_id());
					detailEntity.setType_id(tmp.getType_id());
					detailEntity.setSys_activate(tmp.getActivate());
					detailEntity.setSys_cost(tmp.getCost());
					detailEntity.setOut_price(tmp.getOut_price());
					detailEntity.setMedia_type(tmp.getMedia_type());
					list.add(detailEntity);
				}
			}
		}
		packageActivateDetailDao.batchInsert(list);
		// 更新平台激活数总和，如果没大媒体了此步骤可以省去
		packageActivateDao.update(dateStr);
		
		this.updatePackageActivateInfo();
	}
	
	public void updatePackageActivateInfo() throws DatabaseException{
		//更新接入单价，接入单价在取数据的时候查询太麻烦，当初是想把接入单价放到父表里边，暂时没那个必要，现在没动
		List<PackageActivateInprice> listPrice = packageActivateDao.getPackagePriceList();
		if (listPrice != null && listPrice.size() > 0) {
			packageActivateDao.batchUpdate(listPrice);
		}
	}
	
	
	
	//平台、渠道数据通过包数据进行组合以便分数</p>
	private Map<Long, List<PackageActivateTmp>> getMap(
			List<PackageActivateTmp> list) {
		Map<Long, List<PackageActivateTmp>> map = null;
		if (list != null) {
			map = new HashMap<Long, List<PackageActivateTmp>>();
			List<PackageActivateTmp> tmpList = null;
			for (PackageActivateTmp tmp : list) {
				tmpList = map.get(tmp.getPackage_id());
				if (tmpList == null) {
					tmpList = new ArrayList<PackageActivateTmp>();
				}
				tmpList.add(tmp);
				map.put(tmp.getPackage_id(), tmpList);
			}
		}
		return map;
	}

}
