package cn.adwalker.model.ad.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.ad.dao.IPlacementResWallListDao;
import cn.adwalker.model.ad.domain.PlacementResWallList;

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
@Repository("placementResWallListDao")
public class PlacementResWallListDaoImpl extends BaseDaoImpl<PlacementResWallList> implements IPlacementResWallListDao {
	public PlacementResWallListDaoImpl() {
		setTableName("T_PLACEMENT_RES_WALL_LIST");
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getByPlacement
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.ad.dao.IPlacementResWallScoreDao#getByPlacement(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PlacementResWallList getByPlacement(Long id) throws Exception {
		PlacementResWallList entity = null;
		if (id != null) {
			List<PlacementResWallList> list = (List<PlacementResWallList>) super.findAll("select id,banner_url,placement_id,response_type,response_category,redirect_url,weight from T_PLACEMENT_RES_WALL_LIST where placement_id=" + id, PlacementResWallList.class);
			if (list != null && list.size() > 0) {
				entity = list.get(0);
			}
		}
		return entity;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: saveOrUpdate
	 * </p>
	 * 
	 * @param resWall
	 * @throws Exception
	 * @see cn.adwalker.model.ad.dao.IPlacementResWallScoreDao#saveOrUpdate(cn.adwalker.model.ad.domain.PlacementResWallScore)
	 */
	@Override
	public void saveOrUpdate(PlacementResWallList wall) throws Exception {
		PlacementResWallList entity = this.getByPlacement(wall.getPlacement_id());
		if (entity != null) {
			if (!StringUtils.isEmpty(wall.getBanner_url())) {
				entity.setBanner_url(wall.getBanner_url());
			}
			if (wall.getResponse_category() != null) {
				entity.setResponse_category(wall.getResponse_category());
			}
			if (wall.getResponse_type() != null) {
				entity.setResponse_type(wall.getResponse_type());
			}
			if (!StringUtils.isEmpty(wall.getRedirect_url())) {
				entity.setRedirect_url(wall.getRedirect_url());
			}
			if (wall.getWeight() != null) {
				entity.setWeight(wall.getWeight());
			}
			super.update(entity);
		} else {
			super.insert(wall);
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getMaxWeight
	 * </p>
	 * 
	 * @see cn.adwalker.model.ad.dao.IPlacementResWallScoreDao#getMaxWeight()
	 */
	@SuppressWarnings("deprecation")
	@Override
	public int getMaxWeight() {
		Integer integer = jdbcTemplate.queryForInt("select IFNULL(max(res.weight),0) from T_PLACEMENT_RES_WALL_LIST res ,T_CAMPAIGN_PLACEMENT_REL rel where res.PLACEMENT_ID=rel.PLACEMENT_ID and rel.status=1", new Object[] {});
		return integer.intValue();
	}

}
