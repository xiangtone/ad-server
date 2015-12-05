package cn.adwalker.model.ad.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.ad.dao.ICampaignPlacementRelDao;
import cn.adwalker.model.ad.domain.CampaignPlacementRel;

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
@Repository("campaignPlacementRelDao")
public class CampaignPlacementRelDaoImpl extends BaseDaoImpl<CampaignPlacementRel> implements ICampaignPlacementRelDao {

	public CampaignPlacementRelDaoImpl() {
		setTableName("T_CAMPAIGN_PLACEMENT_REL");
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getByCampaignId
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.ad.dao.ICampaignPlacementRelDao#getByCampaignId(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public CampaignPlacementRel getByCampaignId(Long id) throws Exception {
		CampaignPlacementRel rel = null;
		if (id != null) {
			List<CampaignPlacementRel> list = (List<CampaignPlacementRel>) findAll("select * from T_CAMPAIGN_PLACEMENT_REL where campaign_id=" + id, CampaignPlacementRel.class);
			if (list != null && list.size() > 0) {
				rel = list.get(0);
			}
		}

		return rel;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateStatus
	 * </p>
	 * 
	 * @param id
	 * @param status
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.ad.dao.ICampaignPlacementRelDao#updateStatus(java.lang.Long, java.lang.Integer)
	 */
	@Override
	public int updateStatus(Long id, Integer status) throws Exception {
		int result = -1;
		if (id != null && status != null) {
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE T_CAMPAIGN_PLACEMENT_REL SET STATUS = ?  where campaign_id= ?");
			result = jdbcTemplate.update(sql.toString(), new Object[] { status, id });
		}
		return result;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateStatusByPlacement
	 * </p>
	 * 
	 * @param id
	 * @param i
	 * @see cn.adwalker.model.ad.dao.ICampaignPlacementRelDao#updateStatusByPlacement(java.lang.Long, int)
	 */
	@Override
	public void updateStatusByPlacement(Long id, Integer status) {
		if (id != null && status != null) {
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE T_CAMPAIGN_PLACEMENT_REL SET STATUS = ?  where placement_id= ? ");
			jdbcTemplate.update(sql.toString(), new Object[] { status, id });
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getByPlacementId
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.ad.dao.ICampaignPlacementRelDao#getByPlacementId(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public CampaignPlacementRel getByPlacementId(Long id) throws Exception {
		CampaignPlacementRel rel = null;
		if (id != null) {
			List<CampaignPlacementRel> list = (List<CampaignPlacementRel>) findAll("select * from T_CAMPAIGN_PLACEMENT_REL where placement_id=" + id, CampaignPlacementRel.class);
			if (list != null && list.size() > 0) {
				rel = list.get(0);
			}
		}

		return rel;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: batchOffLine
	 * </p>
	 * 
	 * @param listForUpdate
	 * @see cn.adwalker.model.ad.dao.ICampaignPlacementRelDao#batchOffLine(java.util.List)
	 */
	@Override
	public void batchUpdate(List<CampaignPlacementRel> listForUpdate) {
		if (listForUpdate != null && listForUpdate.size() > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append(" UPDATE " + tableName + " SET status=? where ID=?");
			List<Object[]> parameters = new ArrayList<Object[]>();
			for (CampaignPlacementRel rel : listForUpdate) {
				parameters.add(new Object[] { rel.getStatus(), rel.getId() });
			}
			this.jdbcTemplate.batchUpdate(sb.toString(), parameters);
		}
	}
}
