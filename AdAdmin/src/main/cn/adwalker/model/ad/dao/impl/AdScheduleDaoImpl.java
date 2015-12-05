package cn.adwalker.model.ad.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.ad.dao.IAdScheduleDao;
import cn.adwalker.model.ad.domain.AdSchedule;

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
@Repository("adScheduleDao")
public class AdScheduleDaoImpl extends BaseDaoImpl<AdSchedule> implements IAdScheduleDao {

	public AdScheduleDaoImpl() {
		setTableName("T_AD_SCHEDULE");
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: saveOrUpdate
	 * </p>
	 * 
	 * @param adSchedule
	 * @throws Exception
	 * @see cn.adwalker.model.ad.dao.IAdScheduleDao#saveOrUpdate(cn.adwalker.model.ad.domain.AdSchedule)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void saveOrUpdate(AdSchedule adSchedule) throws Exception {
		if (adSchedule != null) {
			adSchedule.setUpdate_time(new Date());
			List<AdSchedule> list = (List<AdSchedule>) super.findAll("select * from T_AD_SCHEDULE where ad_id= ? and type= ?  and status= ?", new Object[] { adSchedule.getAd_id(), adSchedule.getType(), 0 }, AdSchedule.class);
			if (list != null && list.size() > 0) {
				AdSchedule entity = list.get(0);
				if (entity.getTask_time() != null) {
					entity.setTask_time(adSchedule.getTask_time());
					entity.setStatus(0);
					entity.setRun_time(null);
					entity.setUpdate_time(adSchedule.getUpdate_time());
					entity.setTask_type(adSchedule.getTask_type());
					super.update(entity);
				}
			} else {
				super.insert(adSchedule);
			}
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getAdScheduleStart
	 * </p>
	 * 
	 * @param ad_id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.ad.dao.IAdScheduleDao#getAdScheduleStart(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AdSchedule getAdScheduleStart(Long ad_id) throws Exception {
		AdSchedule adSchedule = null;
		if (ad_id != null) {
			List<AdSchedule> list = (List<AdSchedule>) findAll("select * from T_AD_SCHEDULE where ad_id=? and type=?  and status=? ", new Object[] { ad_id, 1, 0 }, AdSchedule.class);
			if (list != null && list.size() > 0) {
				adSchedule = list.get(0);
			}
		}
		return adSchedule;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getAdScheduleEnd
	 * </p>
	 * 
	 * @param ad_id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.ad.dao.IAdScheduleDao#getAdScheduleEnd(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AdSchedule getAdScheduleEnd(Long ad_id) throws Exception {
		AdSchedule adSchedule = null;
		if (ad_id != null) {
			List<AdSchedule> list = (List<AdSchedule>) findAll("select * from T_AD_SCHEDULE where ad_id=? and type=?  and status=?", new Object[] { ad_id, -1, 0 }, AdSchedule.class);
			if (list != null && list.size() > 0) {
				adSchedule = list.get(0);
			}
		}
		return adSchedule;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateStatus
	 * </p>
	 * 
	 * @param ad_id
	 * @param type
	 * @param status
	 * @throws Exception
	 * @see cn.adwalker.model.ad.dao.IAdScheduleDao#updateStatus(java.lang.Long, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void updateStatus(Long ad_id, Integer type, Integer formStatus, Integer toStatus) throws Exception {
		StringBuilder sb = new StringBuilder("update T_AD_SCHEDULE set status=? where ad_id=? and type=? ");
		if (formStatus != null) {
			sb.append(" and status= ?");
		}
		super.jdbcTemplate.update(sb.toString(), new Object[] { toStatus, ad_id, type, formStatus });

	}
}
