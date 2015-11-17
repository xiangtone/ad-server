package cn.adwalker.ad.admin.task.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.adwalker.ad.admin.task.service.IiosTaskService;
import cn.adwalker.ad.task.vo.IOS_action_log;
import cn.adwalker.core.bean.SimpleBean;
import cn.adwalker.core.util.DateUtil;
import cn.adwalker.core.util.JacksonMapper;
import cn.adwalker.model.ad.dao.ICampaignDao;

@Service
public class IosTaskServiceImpl implements IiosTaskService {

	@Resource
	private ICampaignDao campaignDao;

	@Override
	public void doTask(Date date) throws Exception {
		doTask_action(date);
		doTask_activite(date);

	}

	@SuppressWarnings("unchecked")
	private void doTask_activite(Date date) throws Exception {
		List<Object[]> params = new ArrayList<Object[]>();
		List<IOS_action_log> list = (List<IOS_action_log>) campaignDao
				.findAll(
						"select log.id,"
								+ "log.ad_id as campaign_config_id,"
								+ "log.channel as channel_config_id ,cof.placement_id,channel_config.channel_id "
								+ "from t_ios_activite_log log left join  t_campaign_config cof on log.ad_id=cof.id left join t_channel_config channel_config on channel_config.channel=log.channel "
								+ "where log.CREATE_TIME >=? AND log.CREATE_TIME <? and log.ad_key is null",
						new Object[] { DateUtil.formatDate(date) + " 00:00:00",
								DateUtil.formatDate(date) + " 23:59:59" },
						IOS_action_log.class);
		for (IOS_action_log log : list) {

			Long placement_id = getPlacementId(log.getCampaign_config_id());

			Long ad_id = getAdId(placement_id, log.getChannel_id());
			if (log != null && (ad_id != null)) {
				params.add(new Object[] { ad_id, log.getId() });
			} else {
				System.out.println(JacksonMapper.objectToJsonString(log));
			}

		}

		if (params != null && params.size() > 0) {
			campaignDao
					.batchUpdate(
							"update t_ios_activite_log set ad_key=? where id=?",
							params);
		}

	}

	@SuppressWarnings("unchecked")
	private void doTask_action(Date date) throws Exception {
		List<Object[]> params = new ArrayList<Object[]>();
		List<IOS_action_log> list = (List<IOS_action_log>) campaignDao
				.findAll(
						"select log.id,"
								+ "log.ad_id as campaign_config_id,"
								+ "log.channel as channel_config_id ,cof.placement_id,channel_config.channel_id "
								+ "from t_ios_action_log log left join  t_campaign_config cof on log.ad_id=cof.id left join t_channel_config channel_config on channel_config.channel=log.channel "
								+ "where log.CREATE_TIME >=? AND log.CREATE_TIME <? and log.ad_key is null",
						new Object[] { DateUtil.formatDate(date) + " 00:00:00",
								DateUtil.formatDate(date) + " 23:59:59" },
						IOS_action_log.class);
		for (IOS_action_log log : list) {

			Long placement_id = getPlacementId(log.getCampaign_config_id());

			Long ad_id = getAdId(placement_id, log.getChannel_id());
			if (log != null && (ad_id != null)) {
				params.add(new Object[] { ad_id, log.getId() });
			} else {
				System.out.println(JacksonMapper.objectToJsonString(log));
			}

		}

		if (params != null && params.size() > 0) {
			campaignDao.batchUpdate(
					"update t_ios_action_log set ad_key=? where id=?", params);
		}

	}

	@SuppressWarnings("unchecked")
	private Long getPlacementId(String campaign_config_id) throws Exception {
		Long placement_id = null;
		List<SimpleBean<Long>> list = (List<SimpleBean<Long>>) campaignDao
				.findAll("select id as obj from t_placement where config_id=?",
						new Object[] { campaign_config_id }, SimpleBean.class);
		if (list != null && list.size() > 0) {
			SimpleBean<Long> bean = list.get(0);
			placement_id = bean.getObj();
		}
		return placement_id;

	}

	@SuppressWarnings("unchecked")
	private Long getAdId(Long campaign_id, Long channel_id) throws Exception {
		Long ad_id = null;
		List<SimpleBean<Long>> list = (List<SimpleBean<Long>>) campaignDao
				.findAll(
						"select id as obj from t_ad where placement_id=? and media_id=? ",
						new Object[] { campaign_id, channel_id },
						SimpleBean.class);
		if (list != null && list.size() > 0) {
			SimpleBean<Long> bean = list.get(0);
			ad_id = bean.getObj();
		}
		return ad_id;
	}

}
