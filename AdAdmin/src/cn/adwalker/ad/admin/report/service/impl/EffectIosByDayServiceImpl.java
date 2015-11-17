package cn.adwalker.ad.admin.report.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.log.Log;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.repository.impl.BaseServiceImpl;
import cn.adwalker.model.report.dao.IIosEffectByDayDao;
import cn.adwalker.ad.admin.report.bean.EffectIosByDayQuery;
import cn.adwalker.ad.admin.report.service.IEffectIosByDayService;
import cn.adwalker.ad.admin.report.vo.EffectIosByDay;

/**
 * 
 * <p>
 * Title: EffectIosByDayServiceImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-8-15
 */
@Service
public class EffectIosByDayServiceImpl extends BaseServiceImpl implements
		IEffectIosByDayService {

	@Resource
	private IIosEffectByDayDao iosEffectByDayDao;

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	@Log
	public List<EffectIosByDay> findByPage(EffectIosByDayQuery bean,
			IPageInfo pageInfo) throws Exception {
		List<EffectIosByDay> resultList = null;
		String temp_table = "";
		StringBuilder sb = new StringBuilder(" where 1=1");
		List<Object> param = new ArrayList<Object>();
		if (bean != null) {
			if (!StringUtils.isEmpty(bean.getBeginTime())) {
				sb.append(" and  static_date>=?");
				param.add(bean.getBeginTime());
			}
			if (!StringUtils.isEmpty(bean.getEndTime())) {
				sb.append(" and  static_date<=?");
				param.add(bean.getEndTime());
			}
			if (!StringUtils.isEmpty(bean.getAd_id())) {
				sb.append(" and ad_id=?");
				param.add(bean.getAd_id());
			}
			if (!StringUtils.isEmpty(bean.getChannel())) {
				sb.append(" and channel=?");
				param.add(bean.getChannel());
			}
		}

		String table = "(select static_date,ad_id,CHANNEL,sum(SYS_NUM) as sys_num,sum(CONFIRM_NUM) as confirm_num,in_price from T_IOS_EFFECT_BYDAY "
				+ sb.toString()
				+ " group by STATIC_DATE,AD_ID,CHANNEL,in_price)a "
				+ "left join t_CAMPAIGN_config c  on a.ad_id=c.id "
				+ "left join t_channel_config channel on a.channel=channel.channel "
				+ "left join v_campaign cmp on  c.placement_id=cmp.placement_id"
				+ temp_table;
		resultList = (List<EffectIosByDay>) iosEffectByDayDao
				.findByPage(
						"a.static_date,c.ad_name,a.ad_id,channel.channel_name,a.sys_num,a.confirm_num,a.in_price,IFNULL(round(confirm_num*100 / sys_num,4), 0) as ctrc",
						table, param.toArray(), "order by static_date",
						EffectIosByDay.class, pageInfo);

		return resultList;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findAll
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.report.service.IReportAdByHourService#findAll(cn.adwalker.admin.report.bean.AdByHourQuery)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findAll(EffectIosByDayQuery bean) throws Exception {
		List<Object> resultList = null;
		String temp_table = "";
		StringBuilder sb = new StringBuilder(" where t.id IS NOT NULL");
		List<Object> param = new ArrayList<Object>();
		if (bean != null) {
			if (!StringUtils.isEmpty(bean.getBeginTime())) {
				sb.append(" and  a.static_date>=?");
				param.add(bean.getBeginTime());
			}
			if (!StringUtils.isEmpty(bean.getEndTime())) {
				sb.append(" and  a.static_date<=?");
				param.add(bean.getEndTime());
			}

		}

		String table = "(select static_date,ad_id,PAGE_TYPE,MEDIA_TYPE,APP,CHANNEL,sum(SYS_NUM) as sys_num,sum(CONFIRM_NUM) as confirm_num from t_static_ad_byday where static_date BETWEEN ? and ? group by ad_id, type_id,static_date)a left join v_ad_campaign  t on t.id = a.ad_id left join t_type f on f.id=a.type_id "
				+ temp_table;
		resultList = (List<Object>) iosEffectByDayDao
				.findAll(
						"static_date,ad_id,PAGE_TYPE,MEDIA_TYPE,APP,CHANNEL,sys_num,confirm_num,IFNULL(round(confirm_num*100 / sys_num,4), 0) as ctrc)",
						table + sb.toString(), param.toArray(),
						" order by static_date", EffectIosByDay.class);

		return resultList;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findTotal
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.report.service.IReportAdByHourService#findTotal(cn.adwalker.admin.report.bean.AdByHourQuery)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EffectIosByDay findTotal(EffectIosByDayQuery bean) throws Exception {
		EffectIosByDay total = null;
		String temp_table = "";
		StringBuilder sb = new StringBuilder(" where 1=1");
		List<Object> param = new ArrayList<Object>();
		if (bean != null) {
			if (!StringUtils.isEmpty(bean.getBeginTime())) {
				sb.append(" and  static_date>=?");
				param.add(bean.getBeginTime());
			}
			if (!StringUtils.isEmpty(bean.getEndTime())) {
				sb.append(" and  static_date<=?");
				param.add(bean.getEndTime());
			}
			if (!StringUtils.isEmpty(bean.getAd_id())) {
				sb.append(" and ad_id=?");
				param.add(bean.getAd_id());
			}
			if (!StringUtils.isEmpty(bean.getChannel())) {
				sb.append(" and channel=?");
				param.add(bean.getChannel());
			}
		}

		String table = "(select static_date,ad_id,CHANNEL,SYS_NUM as sys_num,CONFIRM_NUM as confirm_num from T_IOS_EFFECT_BYDAY "
				+ sb.toString()
				+ ")a "
				+ "left join t_CAMPAIGN_config c  on a.ad_id=c.id "
				+ "left join t_channel_config channel on a.channel=channel.channel "
				+ temp_table;
		List<EffectIosByDay> list = (List<EffectIosByDay>) iosEffectByDayDao
				.findAll(
						"IFNULL(sum(a.sys_num), 0) as sys_num,IFNULL(sum(a.confirm_num), 0) as confirm_num,IFNULL(round(sum(a.confirm_num)*100 / sum(a.sys_num),4), 0) as ctrc",
						table, param.toArray(), " order by static_date",
						EffectIosByDay.class);
		if (list != null && list.size() > 0) {
			total = list.get(0);

		}
		return total;
	}
}