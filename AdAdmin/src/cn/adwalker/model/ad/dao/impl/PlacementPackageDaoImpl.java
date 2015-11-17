package cn.adwalker.model.ad.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.ad.dao.IPlacementPackageDao;
import cn.adwalker.model.ad.domain.PlacementPackage;

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
@Repository("placementPackageDao")
public class PlacementPackageDaoImpl extends BaseDaoImpl<PlacementPackage> implements IPlacementPackageDao {
	
	public PlacementPackageDaoImpl() {
		setTableName("T_PLACEMENT_PACKAGE");
	}
	
	
	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: confirmationNumber
	 * </p>
	 * 
	 * @param id
	 * @param number
	 * @throws Exception
	 * @see cn.adwalker.model.operation.dao.IPackageActivateDetailDao#confirmationNumber(java.lang.Long, java.lang.Integer)
	 */
	@Override
	public void packageremarks(Long id, String packageremarks) throws Exception {
		StringBuffer sb = new StringBuffer();
		if (id != null) {
			sb.append("update T_PLACEMENT_PACKAGE set remarks= '");
			if (ObjectUtils.isNotEmpty(packageremarks)) {
				sb.append(packageremarks);
			}
			sb.append("'  where ID=");
			sb.append(id);
		}
		jdbcTemplate.update(sb.toString());
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: deletePackage
	 * </p>
	 * 
	 * @param id
	 * @see cn.adwalker.model.ad.dao.IPlacementPackageDao#deletePackage(java.lang.Long)
	 */
	@Override
	public void deletePackage(Long id) {
		if (id != null) {
			String s = "update T_PLACEMENT_PACKAGE set status=-1 where id=?";
			jdbcTemplate.update(s, new Object[] { id });
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getByPlacement
	 * </p>
	 * 
	 * @param placement_id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.ad.dao.IPlacementPackageDao#getByPlacement(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PlacementPackage getByPlacement(Long placement_id) throws Exception {
		PlacementPackage entity = null;
		List<PlacementPackage> list = (List<PlacementPackage>) findAll("select * from T_PLACEMENT_PACKAGE where  placement_id=" + placement_id, PlacementPackage.class);
		if (list != null && list.size() > 0) {
			entity = list.get(0);
		}
		return entity;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findPackage
	 * </p>
	 * 
	 * @param placement_id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.ad.dao.IPlacementPackageDao#findPackage(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PlacementPackage> findPackage(Long placement_id) throws Exception {
		return (List<PlacementPackage>) this.findAll("SELECT  *  from  T_PLACEMENT_PACKAGE  where  PLACEMENT_ID=? and status=?", new Object[] { placement_id, 0 }, PlacementPackage.class);
	}
}
