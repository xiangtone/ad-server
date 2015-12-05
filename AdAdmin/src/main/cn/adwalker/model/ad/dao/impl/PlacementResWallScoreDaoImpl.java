package cn.adwalker.model.ad.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.ad.dao.IPlacementResWallScoreDao;
import cn.adwalker.model.ad.domain.PlacementResWallScore;

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
@Repository("placementResWallScoreDao")
public class PlacementResWallScoreDaoImpl extends BaseDaoImpl<PlacementResWallScore> implements IPlacementResWallScoreDao {
	public PlacementResWallScoreDaoImpl() {
		setTableName("T_PLACEMENT_RES_WALL_SCORE");
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
	public PlacementResWallScore getByPlacement(Long id) throws Exception {
		PlacementResWallScore entity = null;
		if (id != null) {
			List<PlacementResWallScore> list = (List<PlacementResWallScore>) super.findAll("select id,score_desc,score_long_desc,banner_url,placement_id,response_type,response_category,redirect_url,weight,score_delay,limit_time,weixin_desc from T_PLACEMENT_RES_WALL_SCORE where placement_id=" + id, PlacementResWallScore.class);
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
	public void saveOrUpdate(PlacementResWallScore wall) throws Exception {
		PlacementResWallScore entity = this.getByPlacement(wall.getPlacement_id());
		if (entity != null) {
			if (!StringUtils.isEmpty(wall.getBanner_url())) {
				entity.setBanner_url(wall.getBanner_url());
			}
			if (!StringUtils.isEmpty(wall.getScore_desc())) {
				entity.setScore_desc(wall.getScore_desc());
			}
			if (!StringUtils.isEmpty(wall.getScore_long_desc())) {
				entity.setScore_long_desc(wall.getScore_long_desc());
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
			if (wall.getScore_delay() != null) {
				entity.setScore_delay(wall.getScore_delay());
			}
			if (!StringUtils.isEmpty(wall.getWeixin_desc())) {
				entity.setWeixin_desc(wall.getWeixin_desc());
			}
			if (wall.getLimit_time()!=null) {
				entity.setLimit_time(wall.getLimit_time());
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
		Integer integer = jdbcTemplate.queryForInt("select IFNULL(max(res.weight),0) from T_PLACEMENT_RES_WALL_SCORE res ,T_CAMPAIGN_PLACEMENT_REL rel where res.PLACEMENT_ID=rel.PLACEMENT_ID and rel.status=1", new Object[] {});
		return integer.intValue();

	}

}
