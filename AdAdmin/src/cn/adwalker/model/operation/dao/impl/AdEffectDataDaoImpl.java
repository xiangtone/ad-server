/**
 * 
 */
package cn.adwalker.model.operation.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.adwalker.model.operation.dao.IAdEffectDataDao;
import cn.adwalker.model.operation.domain.FinanceAdEffectData;

/**
 * @author wjp
 * 
 */
@Repository("adEffectDataDao")
public class AdEffectDataDaoImpl implements IAdEffectDataDao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Resource
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;


	@Override
	public int insert(FinanceAdEffectData financeAdEffectData) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into T_FINANCE_AD_EFFECT_DATA (STAT_DATE,AD_ID,VALID_AMOUNT,CREATE_TIME) ");
		sql.append(" values (:statDate,:adId,:validAmount,:createTime) ");
		return this.namedParameterJdbcTemplate.update(sql.toString(),
				new BeanPropertySqlParameterSource(financeAdEffectData));
	}

	@Override
	public int batchInsert(List<FinanceAdEffectData> financeAdEffectData)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into T_FINANCE_AD_EFFECT_DATA (STAT_DATE,AD_ID,VALID_AMOUNT,CREATE_TIME) ");
		sql.append(" values (:statDate,:adId,:validAmount,:createTime) ");

		List<Object[]> parameters = new ArrayList<Object[]>();

		for (FinanceAdEffectData bean : financeAdEffectData) {
			parameters.add(new Object[] { bean.getStatDate(), bean.getAdId(),
					bean.getValidAmount(), bean.getCreateTime() });
		}
		jdbcTemplate.batchUpdate(sql.toString(), parameters);
		return 0;
	}

	@Override
	public int update(FinanceAdEffectData financeAdEffectData) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" update T_FINANCE_AD_EFFECT_DATA set STAT_DATE = :statDate , AD_ID = :adId , VALID_AMOUNT = :validAmount where id = :id ");

		return this.namedParameterJdbcTemplate.update(sql.toString(),
				new BeanPropertySqlParameterSource(financeAdEffectData));
	}

	@Override
	public int getCount(String con) throws Exception {
		StringBuffer sql = new StringBuffer();
		if ("".equals(con)) {
			con = "1=1";
		}
		sql.append(" select count(*) from T_FINANCE_AD_EFFECT_DATA where  ");
		sql.append(con);
		return 0;
	}

	@Override
	public FinanceAdEffectData findOne(Long adverId, Long adId, String statDate)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from T_FINANCE_AD_EFFECT_DATA where ad_id in (select id from T_ad where adv_id = ");
		sql.append(adverId + ")");
		sql.append(" and ad_id =  ");
		sql.append(adId);
		sql.append(" and stat_date = ");
		sql.append("'" + statDate + "'");
		List<FinanceAdEffectData> list = this.jdbcTemplate.query(sql
				.toString(), new BeanPropertyRowMapper<FinanceAdEffectData>(
				FinanceAdEffectData.class));
		if (list.size() != 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int deleteByAdverIdAndStatDate(Long adverId, String startDate)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from T_FINANCE_AD_EFFECT_DATA where ad_id in (select id from T_ad where adv_id = ");
		sql.append(adverId + ")");
		sql.append(" and stat_date = ");
		sql.append("'" + startDate + "'");
		return this.jdbcTemplate.update(sql.toString());
	}

	@Override
	public List<String> isMatch() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.adv_id || '_' || t.id as id from T_ad t  ");

		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql
				.toString());

		List<String> resList = new ArrayList<String>();

		Iterator<Map<String, Object>> it = list.iterator();
		while (it.hasNext()) {
			Map<String, Object> map = it.next();
			resList.add((String) map.get("id"));
		}

		return resList;
	}
	
}
