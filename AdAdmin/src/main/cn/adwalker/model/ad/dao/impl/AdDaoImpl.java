package cn.adwalker.model.ad.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.admin.ad.vo.AdAjustmentEditVo;
import cn.adwalker.ad.admin.ad.vo.AdChannelEditVo;
import cn.adwalker.ad.admin.operation.vo.OperationPlacementPackageVo;
import cn.adwalker.ad.config.StatusConstant;
import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.ad.dao.IAdDao;
import cn.adwalker.model.ad.domain.Ad;

/**
 * 
 * <p>
 * Title: AdDaoImpl
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-9
 */
@Repository("adDao")
public class AdDaoImpl extends BaseDaoImpl<Ad> implements IAdDao {

	public AdDaoImpl() {
		setTableName("T_AD");
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateStatus
	 * </p>
	 * 
	 * @param id
	 * @param manageUser
	 * @param status
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.ad.dao.IAdDao#updateStatus(java.lang.Long, cn.adwalker.SysUserVo.common.vo.ManageUserVo, java.lang.Integer)
	 */
	@Override
	public void updateStatus(List<Long> idList, Integer status) throws Exception {
		if (idList != null && idList.size() > 0) {
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE T_AD SET STATUS = ?,UPDATE_TIME=? ");
			sql.append(" where id=? and ad_type=?");
			List<Object[]> parameters = new ArrayList<Object[]>();
			for (Long adid : idList) {
				Object[] objects = { status, new Date(), adid, StatusConstant.AD_TYPE_PLATFORM };
				parameters.add(objects);
			}
			jdbcTemplate.batchUpdate(sql.toString(), parameters);
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateStatusByPlacement
	 * </p>
	 * 
	 * @param id
	 * @param i
	 * @throws Exception
	 * @see cn.adwalker.model.ad.dao.IAdDao#updateStatusByPlacement(java.lang.Long, int)
	 */
	@Override
	public void onlineAd(List<Long> onLine) throws Exception {
		if (onLine != null && onLine.size() > 0) {
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE T_AD SET STATUS = ?,ONLINE_TIME=?,UPDATE_TIME=? ");
			sql.append(" where id=?");
			List<Object[]> parameters = new ArrayList<Object[]>();
			for (Long adid : onLine) {
				Object[] objects = { 1, new Date(), new Date(), adid };
				parameters.add(objects);
			}
			jdbcTemplate.batchUpdate(sql.toString(), parameters);
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getAdsByByPlacement
	 * </p>
	 * 
	 * @param placement_id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.ad.dao.IAdDao#getAdsByByPlacement(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Ad> getAdsByByPlacement(Long placement_id) throws Exception {
		List<Ad> list = null;
		if (placement_id != null) {
			list = (List<Ad>) this.findAll("select * from T_AD where ad_type=0 and placement_id=" + placement_id, Ad.class);
		}
		return list;
	}

	@Override
	public void modifyAdPrice(Long adId, Double price) {
		String sql = "update T_AD  set BLANCE_PRICE=?,UPDATE_TIME=? where ID=?";
		jdbcTemplate.update(sql, new Object[] { price, new Date(), adId });
	}

	@Override
	public AdAjustmentEditVo findByAdId(Long adId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select a.ID id,a.PLACEMENT_ID placementID,a.START_TIME plan_start,a.END_TIME plan_end,a.PACKAGE_ID packageId,a.BLANCE_MODE charge_type,a.BLANCE_PRICE price,a.BUDGET_DAY budget_day,a.BUDGET_TYPE budget_type,a.status,a.type_id,a.fast_task,a.confirm_type from T_AD a where a.ID=?");
		List<AdAjustmentEditVo> objects = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<AdAjustmentEditVo>(AdAjustmentEditVo.class), adId);
		String packageSql = "select a.ID packageID,a.code code from T_PLACEMENT_PACKAGE a where a.PLACEMENT_ID=?";
		AdAjustmentEditVo vo = null;
		if (objects != null && objects.size() > 0) {
			vo = (AdAjustmentEditVo) objects.get(0);
			List<OperationPlacementPackageVo> packageList = (List<OperationPlacementPackageVo>) jdbcTemplate.query(packageSql.toString(), new BeanPropertyRowMapper<OperationPlacementPackageVo>(OperationPlacementPackageVo.class), vo.getPlacementID());
			vo.setList(packageList);
			return vo;
		}
		return null;
	}

	@Override
	public void updateAdAjustment(Ad entity) {
		try {
			
		StringBuilder sb = new StringBuilder();
		sb.append("update T_AD set PACKAGE_ID=?,BLANCE_MODE=?,BLANCE_PRICE=?,BUDGET_DAY= ?,BUDGET_TYPE=?,UPDATE_TIME=?,STATUS=?,FAST_TASK=?,confirm_type=? ");
		sb.append(" where ID=?");
		jdbcTemplate.update(sb.toString(), new Object[] { entity.getPackage_id(), entity.getBlance_mode(), entity.getBlance_price(), entity.getBudget_day(), entity.getBudget_type(), new Date(), entity.getStatus(),entity.getFast_task(),entity.getConfirm_type(), entity.getId() });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: adOnline
	 * </p>
	 * 
	 * @param adId
	 * @see cn.adwalker.model.ad.dao.IAdDao#adOnline(java.lang.Long)
	 */
	@Override
	public void adOnline(Long adId) {
		String sql = "update T_AD set STATUS=?,UPDATE_TIME=?,ONLINE_TIME=? where ID=?";
		jdbcTemplate.update(sql, new Object[] { StatusConstant.AD_STATUS_ONLINE, new Date(), new Date(), adId });
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: adOffline
	 * </p>
	 * 
	 * @param adId
	 * @see cn.adwalker.model.ad.dao.IAdDao#adOffline(java.lang.Long)
	 */
	@Override
	public void adOffline(Long adId) {
		String sql = "update T_AD set STATUS=?,UPDATE_TIME=?,OFFLINE_TIME=? where ID=?";
		jdbcTemplate.update(sql, new Object[] { StatusConstant.AD_STATUS_OFFLINE, new Date(), new Date(), adId });
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: adSuspend
	 * </p>
	 * 
	 * @param adId
	 * @see cn.adwalker.model.ad.dao.IAdDao#adSuspend(java.lang.Long)
	 */
	@Override
	public void adSuspend(Long adId) {
		String sql = "update T_AD set STATUS=?,UPDATE_TIME=?,OFFLINE_TIME=?,ONLINE_TIME=null where ID=?";
		jdbcTemplate.update(sql, new Object[] { StatusConstant.AD_STATUS_OFFLINE, new Date(), new Date(), adId });

	}

	/**
	 * 
	 * @see cn.adwalker.model.ad.dao.IAdManagerDao#delete(java.lang.Long)
	 */
	@Override
	public void deleteAd(Long id) throws Exception {
		String sql = " delelte from T_AD where id=?";
		jdbcTemplate.update(sql, id);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findChannelAdById
	 * </p>
	 * 
	 * @param adId
	 * @return
	 * @see cn.adwalker.model.ad.dao.IAdDao#findChannelAdById(java.lang.Long)
	 */
	@Override
	public AdChannelEditVo findChannelAdById(Long adId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select a.ID id,a.PLACEMENT_ID placementID,a.START_TIME plan_start,a.END_TIME plan_end,a.PACKAGE_ID packageId,a.BLANCE_MODE charge_type,a.BLANCE_PRICE price,a.BUDGET_DAY budget_day,a.BUDGET_TYPE budget_type,a.status from T_AD a where a.ID=?");
		List<AdChannelEditVo> objects = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<AdChannelEditVo>(AdChannelEditVo.class), adId);
		String packageSql = "select a.ID packageID,a.code code from T_PLACEMENT_PACKAGE a where a.PLACEMENT_ID=?";

		AdChannelEditVo vo = null;
		if (objects != null && objects.size() > 0) {
			vo = (AdChannelEditVo) objects.get(0);
			List<OperationPlacementPackageVo> packageList = (List<OperationPlacementPackageVo>) jdbcTemplate.query(packageSql.toString(), new BeanPropertyRowMapper<OperationPlacementPackageVo>(OperationPlacementPackageVo.class), vo.getPlacementID());
			vo.setList(packageList);
			return vo;
		}
		return null;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: adOver
	 * </p>
	 * 
	 * @param adId
	 * @see cn.adwalker.model.ad.dao.IAdDao#adOver(java.lang.Long)
	 */
	@Override
	public void adOver(Long adId) {
		String sql = "update T_AD set STATUS=?,UPDATE_TIME=? where ID=?";
		jdbcTemplate.update(sql, new Object[] { StatusConstant.AD_STATUS_OVER, new Date(), adId });
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateStatus
	 * </p>
	 * 
	 * @param status
	 * @throws Exception
	 * @see cn.adwalker.model.ad.dao.IAdDao#updateStatus(java.lang.Integer)
	 */
	@Override
	public void updateStatus(Long id, Integer status) throws Exception {
		if (id != null) {
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE T_AD SET STATUS = ?,UPDATE_TIME=? ");
			sql.append(" where id=? ");
			List<Object[]> parameters = new ArrayList<Object[]>();
			Object[] objects = { status, new Date(), id };
			parameters.add(objects);
			jdbcTemplate.batchUpdate(sql.toString(), parameters);
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: adAudit
	 * </p>
	 * 
	 * @param ad_id
	 * @see cn.adwalker.model.ad.dao.IAdDao#adAudit(java.lang.Long)
	 */
	@Override
	public void adAudit(Long ad_id) {
		String sql = "update T_AD set STATUS=? where ID=?";
		jdbcTemplate.update(sql, new Object[] { StatusConstant.AD_STATUS_OFFLINE, ad_id });
	}

}
