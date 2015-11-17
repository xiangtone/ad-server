package cn.adwalker.model.ad.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.ad.dao.IAdSortRelDao;
import cn.adwalker.model.ad.domain.AdCategoryRel;
import cn.adwalker.model.common.domain.Sort;

/**
 * 
 * <p>
 * Title: CampaignDaoImpl
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-2
 */
@Repository("campaignSortRelDao")
public class AdSortRelDaoImpl extends BaseDaoImpl<AdCategoryRel> implements IAdSortRelDao {

	public AdSortRelDaoImpl() {
		setTableName("t_ad_category_rel");
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateCategory
	 * </p>
	 * 
	 * @param id
	 * @param category
	 * @see cn.adwalker.model.common.dao.ISysSortDao#updateCategory(java.lang.Long, java.lang.Long[])
	 */
	@Override
	public void updateCategory(Long id, Long[] category) {
		// 可以清空
		if (id != null) {
			String str = "delete from T_CAMPAIGN_CATEGORY_REL where campaign_id=?";
			this.jdbcTemplate.update(str, new Object[] { id });
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into T_CAMPAIGN_CATEGORY_REL (CAMPAIGN_ID,CATEGORY_ID)");
			sql.append(" values(?,?)");
			List<Object[]> parameters = new ArrayList<Object[]>();
			for (Long rel : category) {
				Object[] objects = { id, rel };
				parameters.add(objects);
			}
			jdbcTemplate.batchUpdate(sql.toString(), parameters);
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findCampaignCategory
	 * </p>
	 * 
	 * @param campaign_id
	 * @param type
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.common.dao.ISysSortDao#findCampaignCategory(java.lang.Long, java.lang.Integer)
	 */
	@Override
	public List<Sort> findCampaignCategory(Long campaign_id, Integer type) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from V_CAMPAIGN_CATEGORY t  where 1=1 and campaign_id= ? and type=?");

		List<Sort> list = (List<Sort>) jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<Sort>(Sort.class), campaign_id, type);
		return list;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findCampaignCity
	 * </p>
	 * 
	 * @param id
	 * @param i
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.common.dao.ISysSortDao#findCampaignCity(java.lang.Long, int)
	 */
	@Override
	public List<Sort> findCampaignCity(Long campaign_id, Integer type) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from V_CAMPAIGN_CATEGORY t  where 1=1 and campaign_id= ? and type=?");
		List<Sort> list = (List<Sort>) jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<Sort>(Sort.class), campaign_id, type);
		return list;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: delCategory
	 * </p>
	 * 
	 * @param id
	 * @see cn.adwalker.model.ad.dao.IAdSortRelDao#delCategory(java.lang.Long)
	 */
	@Override
	public void delCategory(Long id) {
		if (id != null) {
			String str = "delete from T_CAMPAIGN_CATEGORY_REL where campaign_id=?";
			this.jdbcTemplate.update(str, new Object[] { id });
		}
	}

}
