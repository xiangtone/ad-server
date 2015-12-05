package cn.adwalker.model.operation.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.admin.operation.form.AdIosEntering;
import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.operation.dao.IOperationConfirmIncomeDao;
import cn.adwalker.model.operation.domain.IncomeConfirm;

/**
 * <p>
 * Title: AdEffectFirstConfirmDaoImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-5-16
 */
@Repository("confirmIncomeDao")
public class OperationConfirmIncomeDaoImpl extends BaseDaoImpl implements IOperationConfirmIncomeDao {

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: batchDealStatus
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param map
	 * @param manageId
	 * @param description
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.operation.dao.IOperationConfirmIncomeDao#batchDealStatus(java.util.Map, java.lang.Long, java.lang.String)
	 */
	@Override
	public int batchDealStatus(String[] s_date, Long[] ad_id, Long manageId, Integer[] ch_plf_type, String description) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" update T_IOS_INCOME_NUMBER set STATUS = ? , CHECK_TIME = NOW() ");
		sql.append(" where STAT_DATE = ? ");
		sql.append(" and AD_ID = ? ");
		sql.append(" and CH_PLF_TYPE = ? ");

		List<Object[]> parameters = new ArrayList<Object[]>();
		for (int i = 0; i < ad_id.length; i++) {
			parameters.add(new Object[] { 1, s_date[i], ad_id[i], ch_plf_type[i] });
		}
		this.jdbcTemplate.batchUpdate(sql.toString(), parameters);
		return 0;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: countMoneyByDate
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param advId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.operation.dao.IOperationConfirmIncomeDao#countMoneyByDate(java.lang.Long, java.lang.String, java.lang.String)
	 */
	public double countMoneyByDate(Long advId, String startDate, String endDate) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select sum(ADV_CONFIRM_MONEY) as sum from V_AD_EFFECT_FIRST_CONFIRM where ADV_ID = ");
		sql.append(advId);
		sql.append(" and STAT_DATE >= '");
		sql.append(startDate);
		sql.append("' and stat_date < = '");
		sql.append(endDate);
		sql.append("'");

		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql.toString());

		List<Double> resList = new ArrayList<Double>();

		try {
			Iterator<Map<String, Object>> it = list.iterator();
			while (it.hasNext()) {
				Map<String, Object> map = it.next();
				BigDecimal ats = (BigDecimal) map.get("sum");
				resList.add(ats.doubleValue());
			}
		} catch (Exception e) {
			return 0;
		}

		return resList.get(0);
	}
	
	
	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findPage_id
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param page_id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.operation.dao.IOperationConfirmIncomeDao#findPage_id(java.lang.String)
	 */
	@Override
	public Integer findPage_id(String page_id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) from T_PLACEMENT_PACKAGE where CODE ='");
		if (ObjectUtils.isNotEmpty(page_id)) {
			sql.append(page_id);
			sql.append("'");
		}
		return jdbcTemplate.queryForInt(sql.toString());
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findPageCreateTime
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param page_id
	 * @param stat_date
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.operation.dao.IOperationConfirmIncomeDao#findPageCreateTime(java.lang.String, java.lang.String)
	 */
	@Override
	public Integer findPageCreateTime(String income_mac, Long campaign_id) throws Exception {
		Integer value = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" select IFNULL(STATUS,0) as STATUS from T_IOS_INCOME_NUMBER where 1=1 and INCOME_MAC ='");
		if (ObjectUtils.isNotEmpty(income_mac)) {
			sql.append(income_mac);
			sql.append("'");
		}
		if (ObjectUtils.isNotEmpty(campaign_id)) {
			sql.append(" and AD_ID='");
			sql.append(campaign_id);
			sql.append("'");
		}
		List<Map<String, Object>> resultLst = (List<Map<String, Object>>) jdbcTemplate.queryForList(sql.toString());
		if (resultLst != null && resultLst.size() > 0) {
			value = ((BigDecimal) resultLst.get(0).get("STATUS")).intValue();
		}
		return value;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: batchIncomeInsert
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param addConfirmList
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.operation.dao.IOperationConfirmIncomeDao#batchIncomeInsert(java.util.List)
	 */
	@Override
	public int insertAdEffectIosData(List<AdIosEntering> listIos) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into T_IOS_INCOME_NUMBER (INCOME_MAC,STAT_DATE,AD_ID,STATUS,MANAGER_ID,CREATE_TIME,OPENUDID,IDFA)");
		sql.append(" values(:income_mac,:stat_date,:ad_id,:status,:manager_id,:create_time,:openudid,:idfa )");
		SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(listIos.toArray());
		namedParameterJdbcTemplate.batchUpdate(sql.toString(), batch);
		return 0;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: insertIosDate
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.operation.dao.IOperationConfirmIncomeDao#insertIosDate(java.util.List)
	 */
	@Override
	public int insertIosDate(List<IncomeConfirm> list) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into T_LOG_AD_CHANNEL_EFFECT (STATIC_DATE,CONFIRM_NUM,TYPE,AD_ID,INCOME_MONEY,OS,CHANNEL_ID)");
		sql.append("values(:stat_date,:income_amount,:ch_plf_type,:ad_id,:income_money,:os,:channel_id)");
		SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(list.toArray());
		// jdbcTemplate.batchUpdate(sql.toString(), batch);
		return 0;

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findIosDate
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param s_date
	 * @param cam_id
	 * @param manageId
	 * @param ch_plf_type
	 * @param string
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.operation.dao.IOperationConfirmIncomeDao#findIosDate(java.lang.String[], java.lang.Long[], java.lang.Long, java.lang.Integer[], java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<IncomeConfirm> findIosDate(String[] s_date, Long[] ad_id, Long manageId, Integer[] ch_plf_type) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("T_IOS_INCOME_NUMBER t where 1=1  ");
		List<Object> parameters = new ArrayList<Object>();
		for (int i = 0; i < ad_id.length; i++) {
			sb.append(" and STAT_DATE = ? ");
			sb.append(" and AD_ID = ? ");
			sb.append(" and CH_PLF_TYPE = ? ");
			parameters.add(s_date[i]);
			parameters.add(ad_id[i]);
			parameters.add(ch_plf_type[i]);
		}
		sb.append("group by  t.stat_date, t.ad_id,t.status,t.ch_plf_type,t.channel_id");
		return (List<IncomeConfirm>) this.findAll("t.stat_date,t.ad_id,t.channel_id, count(1) as income_amount ,sum(t.income_money) as income_money ,t.ch_plf_type", sb.toString(), parameters.toArray(), IncomeConfirm.class);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: batchDealAdStatus
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param mapAd
	 * @param i
	 * @param manageId
	 * @see cn.adwalker.model.operation.dao.IOperationConfirmIncomeDao#batchDealAdStatus(java.util.List, int, java.lang.Long)
	 */
	@Override
	public int batchDealAdStatus(List<Long> ids, int status, Long manageId) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" update T_IOS_INCOME_NUMBER set STATUS = ? ,manager_id = ?");
		sql.append(" where id = ? ");

		List<Object[]> parameters = new ArrayList<Object[]>();
		for (Long id : ids) {
			parameters.add(new Object[] { status, manageId, id });
		}
		this.jdbcTemplate.batchUpdate(sql.toString(), parameters);
		return 0;
	}
}
