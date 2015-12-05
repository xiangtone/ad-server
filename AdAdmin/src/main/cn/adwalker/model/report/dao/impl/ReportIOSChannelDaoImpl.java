package cn.adwalker.model.report.dao.impl;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.adwalker.ad.admin.report.bean.AdsageActiviteLog;
import cn.adwalker.ad.admin.report.bean.IOSChannelBean;
import cn.adwalker.ad.admin.report.util.ResultSetItor;
import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.core.util.ConfigUtil;
import cn.adwalker.core.util.DateUtil;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.report.dao.IReportIOSChannelDao;

@Repository("reportIOSChannelDao")
public class ReportIOSChannelDaoImpl extends BaseDaoImpl implements
		IReportIOSChannelDao {


	@Override
	public int getSumActivete(IOSChannelBean bean) {
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select count(1) from t_ios_activite_log where 1=1");
			if (ObjectUtils.isNotEmpty(bean.getAd_id())) {
				sql.append(" and AD_ID='" + bean.getAd_id() + "'");
			}
			if (ObjectUtils.isNotEmpty(bean.getChannel())) {
				sql.append(" and CHANNEL='" + bean.getChannel() + "'");
			}
			if (ObjectUtils.isNotEmpty(bean.getAd_name())) {
				sql.append(" and AD_NAME='" + bean.getAd_name() + "'");
			}
			if (ObjectUtils.isNotEmpty(bean.getPageType())) {
				sql.append(" and PAGE_TYPE=" + bean.getPageType() + "");
			}
			if (ObjectUtils.isNotEmpty(bean.getOpenUdid())) {
				sql.append(" and OPENUDID='" + bean.getOpenUdid() + "'");
			}
			if (ObjectUtils.isNotEmpty(bean.getPageType())) {
				sql.append(" and IDFA='" + bean.getIdfa() + "'");
			}
			if (ObjectUtils.isNotEmpty(bean.getIdfv())) {
				sql.append(" and IDFV='" + bean.getIdfv() + "'");
			}
			if (ObjectUtils.isNotEmpty(bean.getMac())) {
				if (ObjectUtils.isNotEmpty(bean.getMac())) {
					String[] stringMAC = bean.getMac().split(":");
					String macNew = "";
					for (int i = 0; i < stringMAC.length; i++) {
						macNew += stringMAC[i];
					}
					sql.append(" and MAC='" + macNew + "'");
				}
			}
			sql.append(" and ACTIVITE_STATUS=1 ");
			if("0".equals(bean.getTimeType())){
			if (ObjectUtils.isNotEmpty(bean.getStatDate_start())
					&& ObjectUtils.isNotEmpty(bean.getStatDate_end())) {
				sql.append(" and  CREATE_TIME>='"
						+ bean.getStatDate_start()
						+ "'  and  CREATE_TIME<='"
						+ bean.getStatDate_end()
						+ "'");
			}
			}else{
			if (ObjectUtils.isNotEmpty(bean.getActiveDate_start())
					&& ObjectUtils.isNotEmpty(bean.getActiveDate_end())) {
				 Date as=DateUtil.parseDate(bean.getActiveDate_start(), DateUtil.PARTTERN_DATE_TIME);
				  Date ae=DateUtil.parseDate(bean.getActiveDate_end(), DateUtil.PARTTERN_DATE_TIME);
				  sql.append(" and  ACTIVITE_DATE  >="+(as.getTime()/1000)
						+ " and ACTIVITE_DATE <="+ (ae.getTime()/1000) + "");
			  }
			}
			sql.append(" order by STAT_DATE desc ");

			return this.jdbcTemplate.queryForInt(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public List<Map<String, Object>> getChannelList() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from T_channel_config t order by t.channel_name desc");
		return jdbcTemplate.queryForList(sql.toString());
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getAdList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @see cn.adwalker.model.report.dao.IReportIOSChannelDao#getAdList()
	 */
	@Override
	public List<Map<String, Object>> getAdList() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from t_CAMPAIGN_config t order by t.create_time desc ");
		return jdbcTemplate.queryForList(sql.toString());
	}
	
	@Override
	public void copyActionLog(String beginTime, String endTime) {
		String ids = ConfigUtil.getString("t_ios_income_number_ad_id");
		if(ids == null || "".equals(ids)) {
			return;
		}
		String[] adIds = ids.split(",");
		for(String adid : adIds) {
			StringBuilder sql = new StringBuilder("insert into T_IOS_INCOME_NUMBER (INCOME_MAC,STAT_DATE,AD_ID,STATUS,MANAGER_ID,CREATE_TIME,OPENUDID,IDFA)");
			sql.append(" select mac,stat_date,ad_id,0,0,create_time,openudid,idfa from (");
			sql.append("select mac,max(stat_date) stat_date,ad_id,max(create_time) create_time,max(openudid) openudid,idfa from t_ios_action_log where ad_id='");
			sql.append(adid);
			sql.append("' and activite_status=0 and date_format(create_time,'%Y-%m-%d %T')>=? and date_format(create_time,'%Y-%m-%d %T')<? group by ad_id,mac,idfa) t1");
			jdbcTemplate.update(sql.toString(), beginTime, endTime);
		}
	}

	@Override
	public ResultSetItor<AdsageActiviteLog> getResultItor(IOSChannelBean bean) throws Exception {
		StringBuffer sql = new StringBuffer();
		String tableName="";
		if("0".equals(bean.getDataType())){
			tableName="T_IOS_ACTION_LOG";
			sql.append("SELECT  n.channel_name,g.`ad_name`,d.* FROM "
					+ "(SELECT t.id,t.ad_id,t.mac,t.openudid,t.idfa,t.idfv,t.activite_date,t.channel,t.status,t.create_time,t.activite_status,t.page_type,t.application_key FROM "+tableName+" t WHERE 1=1");
		}else{
			tableName="t_ios_activite_log";
			sql.append("SELECT  n.channel_name,g.`ad_name`,d.* FROM "
					+ "(SELECT t.id,t.ad_id,t.mac,t.openudid,t.idfa,t.idfv,t.activite_date,t.channel,t.status,t.create_time,t.activite_status,t.page_type,t.application_key,t.send  FROM "+tableName+" t WHERE 1=1 ");
		}
		//add by jief openudid,idfa,idfv
		
		if (ObjectUtils.isNotEmpty(bean.getAd_id())) {
			sql.append(" and t.AD_ID='" + bean.getAd_id() + "'");
		}

		if (ObjectUtils.isNotEmpty(bean.getChannel())) {
			sql.append(" and t.CHANNEL='" + bean.getChannel() + "'");
		}

		if (ObjectUtils.isNotEmpty(bean.getPageType())) {
			sql.append(" and t.PAGE_TYPE=" + bean.getPageType() + "");
		}
		if("1".equals(bean.getDataType()) && ObjectUtils.isNotEmpty(bean.getSend())){
			sql.append(" and t.send=" + bean.getSend() + "");
		}
		//ad by jief 根据激活时间查询 2013-11-15 start
		if("0".equals(bean.getTimeType())){
				//ad by jief 根据激活时间查询 2013-11-15 end
			if (ObjectUtils.isNotEmpty(bean.getStatDate_start())
						&& ObjectUtils.isNotEmpty(bean.getStatDate_end())) {
					sql.append(" and  t.CREATE_TIME>='"
							+ bean.getStatDate_start()
							+ "' and  t.CREATE_TIME<='"
							+ bean.getStatDate_end() + "'");
				  }
			}else{
					bean.setActiveDate_start(bean.getStatDate_start());
					bean.setActiveDate_end(bean.getStatDate_end());
				  if (ObjectUtils.isNotEmpty(bean.getActiveDate_start())
						&& ObjectUtils.isNotEmpty(bean.getActiveDate_end())) {
					  Date as=DateUtil.parseDate(bean.getActiveDate_start(), DateUtil.PARTTERN_DATE_TIME);
					  Date ae=DateUtil.parseDate(bean.getActiveDate_end(), DateUtil.PARTTERN_DATE_TIME);
					  sql.append(" and  t.ACTIVITE_DATE  >="
							+ (as.getTime()/1000)
							+ " and t.ACTIVITE_DATE <="
							+ (ae.getTime()/1000) + "");
			 }
		}
		if (ObjectUtils.isNotEmpty(bean.getSearchStatus())) {
			if ("1".equals(bean.getSearchStatus())) {
				sql.append(" and t.ACTIVITE_STATUS = 1");
			} else if ("0".equals(bean.getSearchStatus())) {
				sql.append(" and t.ACTIVITE_STATUS = 0 ");
			}
		}
		sql.append(" )d LEFT JOIN t_campaign_config g ON d.`ad_id`=g.id_bak LEFT JOIN t_channel_config n ON d.channel=n.channel ");
		System.out.println(sql);
		Connection conn=this.jdbcTemplate.getDataSource().getConnection();
		return new ResultSetItor<AdsageActiviteLog>(AdsageActiviteLog.class,conn,sql.toString());
	}
	
}
