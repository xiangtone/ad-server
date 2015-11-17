package cn.adwalker.model.ad.dao.impl;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.ad.dao.IAdAuditLogDao;
import cn.adwalker.model.ad.domain.AdAuditLog;

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
@Repository("adAuditLogDao")
public class AdAuditLogDaoImpl extends BaseDaoImpl<AdAuditLog> implements IAdAuditLogDao {

	public AdAuditLogDaoImpl() {
		setTableName("T_AD_AUDIT_LOG");
	}
}
