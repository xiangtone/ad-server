package cn.adwalker.model.ad.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.ad.dao.IPlacementResBannerDao;
import cn.adwalker.model.ad.domain.PlacementResBanner;

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
@Repository("placementResBannerDao")
public class PlacementResBannerDaoImpl extends BaseDaoImpl<PlacementResBanner> implements IPlacementResBannerDao {
	
	public PlacementResBannerDaoImpl() {
		setTableName("T_PLACEMENT_RES_BANNER");
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
	 * @see cn.adwalker.model.ad.dao.IPlacementResBannerDao#getByPlacement(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PlacementResBanner getByPlacement(Long id) throws Exception {
		PlacementResBanner entity = null;
		if (id != null) {
			List<PlacementResBanner> list = (List<PlacementResBanner>) super.findAll("select id,response_type,response_category,redirect_url,img_url_first,img_url_second,img_url_third,placement_id,weight from T_PLACEMENT_RES_BANNER where placement_id=" + id, PlacementResBanner.class);
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
	 * @param resBanner
	 * @throws Exception
	 * @see cn.adwalker.model.ad.dao.IPlacementResBannerDao#saveOrUpdate(cn.adwalker.model.ad.domain.PlacementResBanner)
	 */
	@Override
	public void saveOrUpdate(PlacementResBanner banner) throws Exception {
		PlacementResBanner entity = this.getByPlacement(banner.getPlacement_id());
		if (entity != null) {
			if (!StringUtils.isEmpty(banner.getImg_url_first())) {
				entity.setImg_url_first(banner.getImg_url_first());
			}
			if (!StringUtils.isEmpty(banner.getImg_url_second())) {
				entity.setImg_url_second(banner.getImg_url_second());
			}
			if (!StringUtils.isEmpty(banner.getImg_url_third())) {
				entity.setImg_url_third(banner.getImg_url_third());
			}
			if (banner.getResponse_category() != null) {
				entity.setResponse_category(banner.getResponse_category());
			}
			if (banner.getResponse_type() != null) {
				entity.setResponse_type(banner.getResponse_type());
			}
			if (!StringUtils.isEmpty(banner.getRedirect_url())) {
				entity.setRedirect_url(banner.getRedirect_url());
			}
			if (banner.getWeight() != null) {
				entity.setWeight(banner.getWeight());
			}
			super.update(entity);
		} else {
			super.insert(banner);
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
		Integer integer = jdbcTemplate.queryForInt("select IFNULL(max(res.weight),0) from T_PLACEMENT_RES_BANNER res ,T_CAMPAIGN_PLACEMENT_REL rel where res.PLACEMENT_ID=rel.PLACEMENT_ID and rel.status=1", new Object[] {});
		return integer.intValue();
	}

}
