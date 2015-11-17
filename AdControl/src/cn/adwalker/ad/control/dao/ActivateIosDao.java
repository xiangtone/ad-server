package cn.adwalker.ad.control.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.control.domain.ActivateClick;
import cn.adwalker.ad.control.domain.ActivateClickByCampaignAndType;
import cn.adwalker.ad.control.domain.ActivateNumIos;
import cn.adwalker.ad.control.domain.SimpleBean;
import cn.adwalker.ad.control.vo.ActivateNumDetailIosVo;
import cn.adwalker.ad.control.vo.IosStaticNumVo;

@Repository("activateIosDao")
public class ActivateIosDao {
	@Resource(name = "namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public Long insert(ActivateNumIos activateNumIos) {
		StringBuilder sql = new StringBuilder(
				" INSERT INTO t_ios_activate_num(campaign_id,static_date,create_time,`status`,price,balance_model,activate,click)");
		sql.append(" VALUES (:campaign_id,:static_date,:create_time,:status,:price,:balance_model,:activate,:click)");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(sql.toString(),
				new BeanPropertySqlParameterSource(activateNumIos), keyHolder);
		return keyHolder.getKey().longValue();
	}

	public List<ActivateNumDetailIosVo> getChannelAdIosList(String date) {
		List<ActivateNumDetailIosVo> list = null;
		if (!StringUtils.isEmpty(date)) {
			StringBuilder sql = new StringBuilder(
					" SELECT v.id,log.media_id,v.campaign_id,log.type_id,log.AD_PRICE out_price,log.BLANCE_MODE from T_LOG_AD_PLACEMENT log ");
			sql.append("LEFT JOIN V_AD_CAMPAIGN  v on log.ad_id=v.id ");
			sql.append(" where log.os=? and  log.static_date=?  and  v.ad_type=? and log.status=?");
			list = namedParameterJdbcTemplate.getJdbcOperations().query(
					sql.toString(),
					new BeanPropertyRowMapper<ActivateNumDetailIosVo>(
							ActivateNumDetailIosVo.class), "ios", date, 1, 1);
			if (list != null && list.size() > 0) {
				Map<Long, Integer> click_map = getChannelClick(date);
				Map<Long, Integer> activite_map = getChannelActivite(date);
				if ((click_map != null && click_map.isEmpty())
						|| (activite_map != null && activite_map.isEmpty())) {
					for (ActivateNumDetailIosVo vo : list) {
						if (click_map != null && !click_map.isEmpty()) {
							vo.setClick(click_map.get(vo.getId()));
						}

						if (activite_map != null && !activite_map.isEmpty()) {
							vo.setActivate(activite_map.get(vo.getId()));
						}

					}

				}

			}
		}

		return list;
	}

	private Map<Long, Integer> getChannelClick(String date) {
		// 渠道广告id
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		if (!StringUtils.isEmpty(date)) {
			String str = "SELECT log.`ad_key` AS ad_id,COUNT(1) AS num "
					+ "FROM `t_ios_action_log` LOG "
					+ "WHERE log.CREATE_TIME >=? AND log.CREATE_TIME <=? AND  log.`channel`!='adwaker' AND log.`ad_key` IS NOT NULL  GROUP BY log.`ad_key`";
			List<IosStaticNumVo> list = namedParameterJdbcTemplate
					.getJdbcOperations().query(
							str,
							new BeanPropertyRowMapper<IosStaticNumVo>(
									IosStaticNumVo.class), date + " 00:00:00",
							date + " 23:59:59");
			if (list != null && list.size() > 0) {
				for (IosStaticNumVo vo : list) {
					map.put(vo.getAd_id(), vo.getNum());
				}
			}
		}

		return map;
	}

	private Map<Long, Integer> getChannelActivite(String date) {
		// 渠道广告id
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		if (!StringUtils.isEmpty(date)) {
			String str = "SELECT log.`ad_key` AS ad_id,COUNT(1) AS num "
					+ "FROM `t_ios_activite_log` LOG "
					+ "WHERE log.CREATE_TIME >=? AND log.CREATE_TIME <=? AND  log.`channel`!='adwaker' AND log.`ad_key` IS NOT NULL  GROUP BY log.`ad_key`";
			List<IosStaticNumVo> list = namedParameterJdbcTemplate
					.getJdbcOperations().query(
							str,
							new BeanPropertyRowMapper<IosStaticNumVo>(
									IosStaticNumVo.class), date + " 00:00:00",
							date + " 23:59:59");
			if (list != null && list.size() > 0) {
				for (IosStaticNumVo vo : list) {
					map.put(vo.getAd_id(), vo.getNum());
				}
			}
		}

		return map;
	}

	public List<ActivateNumDetailIosVo> getPlatformAdIosList(String date) {
		StringBuilder sql = new StringBuilder(
				"SELECT v.campaign_id,log.type_id,log.ad_id,log.ad_price as out_price,log.blance_mode from T_LOG_AD_PLACEMENT log ");
		sql.append("LEFT JOIN v_ad_cam  v on log.ad_id=v.id ");
		sql.append("where log.os=? and  log.static_date=?  and  v.ad_type=? group by v.campaign_id,log.type_id");
		return namedParameterJdbcTemplate.getJdbcOperations().query(
				sql.toString(),
				new BeanPropertyRowMapper<ActivateNumDetailIosVo>(
						ActivateNumDetailIosVo.class), "ios", date, 0);
	}

	public List<ActivateClickByCampaignAndType> getPlatformNumIosList(
			String edate, String sdate) {
		List<ActivateClickByCampaignAndType> list = null;
		if (!StringUtils.isEmpty(edate) && !StringUtils.isEmpty(sdate)) {
			String sql = "SELECT "
					+ "v.campaign_id,"
					+ "log.page_type type_id,"
					+ "log.sys_num click,"
					+ "log.confirm_num activate "
					+ "FROM t_ios_effect_byday log "
					+ "LEFT JOIN (SELECT * FROM t_ios_activite_log WHERE CREATE_TIME<=? AND CREATE_TIME>=?) act ON LOG.ad_id=act.ad_id AND log.static_date=DATE_FORMAT(act.create_time,'%Y-%m-%d') AND LOG.channel=act.channel AND LOG.in_price=act.in_price AND LOG.page_type=act.page_type " 
					+" LEFT JOIN V_AD_CAM v ON act.ad_key = v.id AND act.ad_key IS NOT NULL GROUP BY v.campaign_id,LOG.page_type";
			list = namedParameterJdbcTemplate
					.getJdbcOperations()
					.query(sql,
							new BeanPropertyRowMapper<ActivateClickByCampaignAndType>(
									ActivateClickByCampaignAndType.class),
									edate, sdate);
			
		}

		return list;
	}

	@SuppressWarnings("unused")
	private Map<Long, Integer> getPlatformClick(String sdate, String edate) {
		// 渠道广告id
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		if (!StringUtils.isEmpty(sdate) && !StringUtils.isEmpty(edate)) {
			String str = "SELECT log.`ad_key` AS ad_id,COUNT(1) AS num "
					+ "FROM `t_ios_action_log` LOG "
					+ "WHERE log.CREATE_TIME >=? AND log.CREATE_TIME <=? AND  log.`channel`='adwaker' AND log.`ad_key` IS NOT NULL  GROUP BY log.`ad_key`";
			List<IosStaticNumVo> list = namedParameterJdbcTemplate
					.getJdbcOperations().query(
							str,
							new BeanPropertyRowMapper<IosStaticNumVo>(
									IosStaticNumVo.class), sdate,
							edate + " 23:59:59");
			if (list != null && list.size() > 0) {
				for (IosStaticNumVo vo : list) {
					map.put(vo.getAd_id(), vo.getNum());
				}
			}
		}

		return map;
	}

	public List<SimpleBean> getSimpleBeanList(Long campaign_id) {
		String sql = "select t.ad_key obj from T_CAMPAIGN_CONFIG t,v_campaign v where v.config_id=t.ad_key and v.id=?";
		return namedParameterJdbcTemplate.getJdbcOperations().query(sql,
				new BeanPropertyRowMapper<SimpleBean>(SimpleBean.class),
				campaign_id);
	}

	public List<ActivateClick> getActivateClickList(String date,
			List<SimpleBean> confirmList) {
		StringBuilder sb = new StringBuilder(
				"select SUM(log.sys_num) click,SUM(log.confirm_num) activate,log.IN_PRICE as PRICE FROM t_ios_effect_byday LOG , t_campaign_config cof WHERE  log.ad_id=cof.ad_key  AND  log.static_date>='");
		sb.append(date).append("'");
		sb.append(" and log.static_date<='").append(date).append("'");
		List<Object> param = new ArrayList<Object>();
		sb.append("and cof.ad_key in ( ");
		for (SimpleBean bean : confirmList) {
			sb.append("?,");
			param.add(bean.getObj());
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		sb.append(" GROUP BY log.IN_PRICE ");
		System.out.println(sb);
		return namedParameterJdbcTemplate.getJdbcOperations().query(
				sb.toString(),
				new BeanPropertyRowMapper<ActivateClick>(ActivateClick.class),
				param.toArray());
	}

}
