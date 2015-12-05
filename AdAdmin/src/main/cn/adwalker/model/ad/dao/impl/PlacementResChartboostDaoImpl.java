package cn.adwalker.model.ad.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.ad.dao.IPlacementResChartboostDao;
import cn.adwalker.model.ad.domain.PlacementResChartboost;

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
@Repository("placementResChartboostDao")
public class PlacementResChartboostDaoImpl extends BaseDaoImpl<PlacementResChartboost> implements IPlacementResChartboostDao {
	public PlacementResChartboostDaoImpl() {
		setTableName("T_PLACEMENT_RES_CHARTBOOST");
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
	 * @see cn.adwalker.model.ad.dao.IPlacementResChartboostDao#getByPlacement(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PlacementResChartboost getByPlacement(Long id) throws Exception {
		PlacementResChartboost entity = null;
		if (id != null) {
			List<PlacementResChartboost> list = (List<PlacementResChartboost>) super.findAll("select id,response_type,response_category,redirect_url,img_horizontal,img_vertical,placement_id,weight from T_PLACEMENT_RES_CHARTBOOST where placement_id=" + id, PlacementResChartboost.class);
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
	 * @param chartboost
	 * @throws Exception
	 * @see cn.adwalker.model.ad.dao.IPlacementResChartboostDao#saveOrUpdate(cn.adwalker.model.ad.domain.PlacementResChartboost)
	 */
	@Override
	public void saveOrUpdate(PlacementResChartboost chartboost) throws Exception {
		PlacementResChartboost entity = this.getByPlacement(chartboost.getPlacement_id());
		if (entity != null) {
			if (!StringUtils.isEmpty(chartboost.getImg_horizontal())) {
				entity.setImg_horizontal(chartboost.getImg_horizontal());
			}
			if (!StringUtils.isEmpty(chartboost.getImg_vertical())) {
				entity.setImg_vertical(chartboost.getImg_vertical());
			}
			if (chartboost.getResponse_category() != null) {
				entity.setResponse_category(chartboost.getResponse_category());
			}
			if (chartboost.getResponse_type() != null) {
				entity.setResponse_type(chartboost.getResponse_type());
			}
			if (!StringUtils.isEmpty(chartboost.getRedirect_url())) {
				entity.setRedirect_url(chartboost.getRedirect_url());
			}
			if (chartboost.getWeight() != null) {
				entity.setWeight(chartboost.getWeight());
			}
			super.update(entity);
		} else {
			super.insert(chartboost);
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
		Integer integer = jdbcTemplate.queryForInt("select IFNULL(max(res.weight),0) from T_PLACEMENT_RES_CHARTBOOST res,T_CAMPAIGN_PLACEMENT_REL rel where res.PLACEMENT_ID=rel.PLACEMENT_ID and rel.status=1", new Object[] {});
		return integer.intValue();
	}

}
