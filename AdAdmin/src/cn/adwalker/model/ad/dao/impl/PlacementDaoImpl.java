package cn.adwalker.model.ad.dao.impl;

import org.springframework.stereotype.Repository;

import cn.adwalker.ad.config.StatusConstant;
import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.core.util.lang.StringUtil;
import cn.adwalker.model.ad.dao.IPlacementDao;
import cn.adwalker.model.ad.domain.Placement;

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
@Repository("placementDao")
public class PlacementDaoImpl extends BaseDaoImpl<Placement> implements IPlacementDao {

	public PlacementDaoImpl() {
		setTableName("T_PLACEMENT");
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: update
	 * </p>
	 * 
	 * @param id
	 * @param iconimg_url
	 * @see cn.adwalker.model.ad.dao.IPlacementDao#update(java.lang.Long, java.lang.String)
	 */
	@Override
	public void update(Long id, String iconimg_url) {
		if (id != null && !StringUtil.isEmpty(iconimg_url)) {
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE T_PLACEMENT SET ICON_URL =? where ID= ?");
			jdbcTemplate.update(sql.toString(), new Object[] { iconimg_url, id });
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getAdvEmailByPlacement
	 * </p>
	 * 
	 * @param placement_id
	 * @see cn.adwalker.model.ad.dao.IPlacementDao#getAdvEmailByPlacement(java.lang.Long)
	 */
	@Override
	public String getAdvEmailByPlacement(Long placement_id) {
		return jdbcTemplate.queryForObject("select adv.email  from  T_CAMPAIGN_PLACEMENT_REL  rel left join T_CAMPAIGN c on rel.CAMPAIGN_ID=c.id left join T_ADVERTISER adv on c.adv_id=adv.id where rel.PLACEMENT_ID=?", String.class, new Object[] { placement_id });

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: offline
	 * </p>
	 * 
	 * @param placement_id
	 * @see cn.adwalker.model.ad.dao.IPlacementDao#offline(java.lang.Long)
	 */
	@Override
	public void offline(Long placement_id) {
		String sql = "update T_CAMPAIGN_PLACEMENT_REL set STATUS=?  where id=?";
		jdbcTemplate.update(sql, new Object[] { StatusConstant.CAMPAIGN_STATUS_PLACEMENT_OFF_LINE, placement_id });

	}
	/**
	 * @author jief
	 * @param placment_id
	 */
	@Override
	public String getConfigId(Long placment_id) {
		String sql="select config_id from T_PLACEMENT where id=?";
		String configId=jdbcTemplate.queryForObject(sql, String.class, new Object[] { placment_id });
		return configId;
	}
}
